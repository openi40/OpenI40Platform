package com.openi40.platform.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openi40.platform.app.Main;
import com.openi40.scheduler.apsdatacache.IApsDataCacheAggregator;
import com.openi40.scheduler.client.model.aps.ApsDataDto;
import com.openi40.scheduler.engine.aps.ApsLogics;
import com.openi40.scheduler.engine.aps.IApsLogic;
import com.openi40.scheduler.engine.apsdata.IApsDataManager;
import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.mapper.DefaultEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.aps.ApsMessage;
import com.openi40.scheduler.model.aps.ApsMessageCategory;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IApsMessageDao;
import com.openi40.scheduler.model.dao.ITaskDao;
import com.openi40.scheduler.model.dao.IWorkOrderDao;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.model.tasks.Task;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { Main.class })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.ANY)
@ComponentScan("com.openi40")
//@DataJpaTest(showSql = true)
public abstract class AbstractPlatformTests {
	Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	DataSource dataSource;
	@Autowired
	IApsDataCacheAggregator apsDataCacheAggregator;
	@Autowired
	IContextualBusinessLogicFactory contextualBusinessLogicFactory;
	@Autowired
	ITaskDao taskDao;
	@Autowired
	IApsMessageDao apsMessageDao;
	@Autowired
	IWorkOrderDao workOrderDao;
	@Autowired
	IMapperFactory mapperFactory;
	@Autowired
	ObjectMapper objectMapper;
	protected void initializeScheduling(ApsData apsData, ApsLogicDirection direction, String logicSpec)
			throws DataModelDaoException {
		ApsSchedulingSet schedulingSet = new ApsSchedulingSet(apsData);
		schedulingSet.setAlgorithmDirection(direction);
		schedulingSet.setAlgorithmType(logicSpec);
		IApsLogic apsLogic = contextualBusinessLogicFactory.create(IApsLogic.class, schedulingSet, apsData);
		schedulingSet.setOptions(apsLogic.createDefaultOptions(schedulingSet));
		apsData.getSchedulingSets().add(schedulingSet);
		IApsDataManager dataManager = contextualBusinessLogicFactory.create(IApsDataManager.class, apsData, apsData);
		dataManager.initialize(apsData);
		dataManager.beforeScheduling(apsData);
		List<WorkOrder> wos = workOrderDao.findAll(apsData);
		for (WorkOrder workOrder : wos) {
			schedulingSet.addWorkOrder(workOrder);
		}

	}

	protected <OriginalType, TargetType> IMapper<OriginalType, TargetType> getMapper(Class<OriginalType> originalType,
			Class<TargetType> targetType) throws MapperException {
		return mapperFactory.createMapper(originalType, targetType);
	}

	protected IMapper<ApsData, ApsDataDto> getClientScenarioMapper() throws MapperException {
		return this.getMapper(ApsData.class, ApsDataDto.class);
	}

	protected void dumpScenario(ApsData data, String postfix)
			throws MapperException, JsonGenerationException, JsonMappingException, IOException {
		LOGGER.info("Begin dumpScenario(data,\"" + postfix + "\")");
		IMapper<ApsData, ApsDataDto> mapper = this.getClientScenarioMapper();
		ApsDataDto outData = mapper.mapInstance(data, ApsDataDto.class, DefaultEntitiesFactory.Instance,
				new HashMap<>(), true);
		String fileName = "logs/" + data.getDataSourceName() + "-" + data.getDataSetName() + "-"
				+ data.getDataSetVariant() + "-" + postfix + ".json";
		if (new File(fileName).exists())
			new File(fileName).delete();
		if (!new File("logs/").exists())
			new File("logs").mkdir();
		objectMapper.writeValue(new File(fileName), outData);
		LOGGER.info("End dumpScenario(data,\"" + postfix + "\")");
	}

	private void executeInputStream(InputStream is, Connection connextion, boolean avoidExitOnException,
			boolean logExceptions) throws IOException, SQLException {
		String sql = this.readAll(is);
		List<String> sqlCommands = this.splitCommands(sql);
		Statement statement = null;
		try {
			statement = connextion.createStatement();
			for (String sqlCommand : sqlCommands) {

				try {

					LOGGER.info("Executing: " + sqlCommand);
					statement.execute(sqlCommand);
					LOGGER.info("Executed OK!");
				} catch (Throwable th) {
					String msg = "Error executing:" + sqlCommand;
					if (logExceptions)
						LOGGER.error(msg, th);
					if (!avoidExitOnException) {
						throw new SQLException(msg, th);
					}
				}
			}
		} finally {
			try {
				statement.close();
			} catch (Throwable s) {
			}
		}
	}

	@Transactional(value = TxType.SUPPORTS)
	protected void runScript(String path, boolean avoidExitOnException, boolean logExceptions)
			throws IOException, SQLException {
		InputStream is = null;
		if (new File(path).exists()) {
			is = new FileInputStream(path);
		} else {
			is = this.getClass().getClassLoader().getResourceAsStream(path);
		}
		if (is == null) {
			throw new FileNotFoundException(
					"Path: " + path + " does not correspond to external path or classpath resource path");
		}
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(true);
			executeInputStream(is, connection, avoidExitOnException, logExceptions);
		} finally {
			try {
				connection.close();
			} catch (Throwable t) {
			}
		}
	}

	public AbstractPlatformTests() {

	}

	private List<String> splitCommands(String sql) {
		StringTokenizer tokenizer = new StringTokenizer(sql, ";");
		List<String> al = new ArrayList<>();
		while (tokenizer.hasMoreTokens()) {
			String s = tokenizer.nextToken();
			if (s.trim().length() > 0) {
				al.add(s.trim());
			}
		}
		return al;
	}

	private String readAll(InputStream is) throws IOException {
		byte buffer[] = new byte[4096];
		StringBuffer sb = new StringBuffer();
		int nRead = 0;
		while ((nRead = is.read(buffer)) > 0) {
			sb.append(new String(buffer, 0, nRead));
		}
		return sb.toString();
	}

	protected void verifyScheduled(ApsData data) throws DataModelDaoException {
		List<Task> tasks = taskDao.findAll(data);
		List<ApsMessage> messagesList = apsMessageDao.findAllOrderByGlobalPosition(data);
		if (!messagesList.isEmpty()) {
			LOGGER.warn("There are " + messagesList.size() + " scheduling user messages");
			for (ApsMessage apsMessage : messagesList) {
				String text = "messageCategory:" + printEnum(apsMessage.getMessageCategory()) + " msgLevel:"
						+ printEnum(apsMessage.getMsgLevel()) + " msgCode:" + apsMessage.getMessageCode()
						+ " msgDescription:" + apsMessage.getMessageDescription();
				switch (apsMessage.getMsgLevel()) {
				case SOFTWARE_EXCEPTION:
				case UNSCHEDULABLE_CONDITION: {
					LOGGER.error(text);
				}

					break;
				case WARNING: {
					LOGGER.warn(text);
				}
					break;
				default: {
					LOGGER.info(text);
				}
				}
			}
		}
		for (Task task : tasks) {
			LOGGER.info("Task " + task.getCode() + " scheduled=> " + task.isSuccessfullyScheduled());
			assertTrue(task.isSuccessfullyScheduled(), "Task " + task.getCode() + " is not successfully scheduled!");
		}
	}

	private String printEnum(Enum _enum) {

		return _enum != null ? _enum.name() : "null";
	}
}
