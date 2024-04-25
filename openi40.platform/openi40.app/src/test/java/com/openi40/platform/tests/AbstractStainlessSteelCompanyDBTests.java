package com.openi40.platform.tests;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.openi40.platform.iomessages.spooler.services.IMSGSpoolingService;
import com.openi40.platform.iomessages.spooler.services.MSGSpoolerException;
import com.openi40.scheduler.iomessages.AbstractBaseIOMessage;

public abstract class AbstractStainlessSteelCompanyDBTests extends AbstractPlatformTests {
	@Autowired DataSource dataSource;
	@Autowired
	IMSGSpoolingService spoolingService;
	static final int MAXWAIT = 10;

	public AbstractStainlessSteelCompanyDBTests() {
		// TODO Auto-generated constructor stub
	}

	protected void dropDB() throws IOException, SQLException {
		runScript(dataSource,"drop-all-tables.sql", true, false);
		runScript(dataSource,"drop-all-tables.sql", true, false);
		runScript(dataSource,"drop-all-tables.sql", true, false);
	}

	protected void prepareDB() throws IOException, SQLException {
		runScript(dataSource,"create.sql", false, true);
		runScript(dataSource,"populate.sql", false, true);
		runScript(dataSource,"alter.sql", false, true);
	}

	protected void spoolMessage(AbstractBaseIOMessage msg) throws MSGSpoolerException, JsonProcessingException {
		LOGGER.info("Begin spoolMessage(type="+msg.getClass().getSimpleName()+" content=> "+objectMapper.writeValueAsString(msg)+")");
		spoolingService.add("DB-LINK", "DB-DataSet", "DEFAULT", msg);
		LOGGER.info("End spoolMessage(..)");
	}

	protected void waitEmptySpool() {
		LOGGER.info("Begin waitEmptySpool()");
		int nIterations = 0;
		while (!spoolingService.isEmptySpoolingQueue("DB-LINK", "DB-DataSet", "DEFAULT")) {
			try {
				Thread.currentThread().sleep(10000);
	
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			nIterations++;
			LOGGER.info("Done waitEmptySpool iteration=>" + nIterations);
			if (nIterations > MAXWAIT)
				throw new RuntimeException("Waited too many time for the spooling msg queue to become empty");
		}
		LOGGER.info("End waitEmptySpool()");
	}

}
