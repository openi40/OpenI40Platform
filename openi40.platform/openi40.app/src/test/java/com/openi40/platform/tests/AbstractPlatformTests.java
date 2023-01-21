package com.openi40.platform.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

import com.openi40.platform.app.Main;
import com.openi40.scheduler.apsdatacache.IApsDataCacheAggregator;
import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.ITaskDao;
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
		for (Task task : tasks) {
			LOGGER.info("Task " + task.getCode() + " scheduled=> " + task.isSuccessfullyScheduled());
			assertTrue(task.isSuccessfullyScheduled(), "Task " + task.getCode() + " is not successfully scheduled!");
		}
	}
}
