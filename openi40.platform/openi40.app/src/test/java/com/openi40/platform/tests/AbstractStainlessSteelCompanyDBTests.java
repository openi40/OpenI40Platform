package com.openi40.platform.tests;

import java.io.IOException;
import java.sql.SQLException;

public abstract class AbstractStainlessSteelCompanyDBTests extends AbstractPlatformTests {

	public AbstractStainlessSteelCompanyDBTests() {
		// TODO Auto-generated constructor stub
	}

	protected void dropDB() throws IOException, SQLException {
		runScript("drop-all-tables.sql", true, false);
		runScript("drop-all-tables.sql", true, false);
		runScript("drop-all-tables.sql", true, false);
	}

	protected void prepareDB() throws IOException, SQLException {
		runScript("create.sql", false, true);
		runScript("populate.sql", false, true);
		runScript("alter.sql", false, true);
	}

}
