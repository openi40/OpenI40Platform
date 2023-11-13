package com.openi40.mes.iiot.tests;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.openi40.mes.logical.kernel.app.Main;
import com.openi40.tests.AbstractDBSupportTest;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { Main.class })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.ANY)
@ComponentScan("com.openi40")
public abstract class AbstractMesDBSupportTest extends AbstractDBSupportTest {

	public AbstractMesDBSupportTest() {
		
	}
	protected void prepareDB() throws IOException, SQLException {
		runScript("create.sql", false, true);
		runScript("populate.sql", false, true);
		runScript("alter.sql", false, true);
	}
	
	protected void dropDB() throws IOException,SQLException {
		runScript("drop.sql", false, true);
	}
}
