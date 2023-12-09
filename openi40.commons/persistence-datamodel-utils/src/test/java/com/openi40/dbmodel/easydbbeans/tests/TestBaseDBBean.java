package com.openi40.dbmodel.easydbbeans.tests;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.apache.derby.jdbc.EmbeddedDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.openi40.dbmodel.easydbbeans.BaseDBBean;
import com.openi40.dbmodel.easydbbeans.BaseDBBean.CloseablePersister;
import com.openi40.dbmodel.easydbbeans.BaseDBBeanDao;
import com.openi40.dbmodel.easydbbeans.PersistenceException;
import com.openi40.tests.AbstractDBSupportTest;

@RunWith(SpringJUnit4ClassRunner.class)

@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)

@ComponentScan("com.openi40")
public class TestBaseDBBean extends AbstractDBSupportTest {

	public TestBaseDBBean() {

	}

	
	public static class STable extends BaseDBBean {
		private String code = null;
		private Timestamp ts = null;
		private Double qty = 0.0;

		public STable() {
			super(false);
			table = "stable";
			primaryKeyProperties = new String[] { "code" };
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public Timestamp getTs() {
			return ts;
		}

		public void setTs(Timestamp ts) {
			this.ts = ts;
		}

		public Double getQty() {
			return qty;
		}

		public void setQty(Double qty) {
			this.qty = qty;
		}
	}

	@Test
	public void runTest() throws SQLException, InstantiationException, IllegalAccessException, PersistenceException {
		EmbeddedDriver driver = new EmbeddedDriver();
		Connection connection = driver.connect("jdbc:derby:memory:local;create=true", new Properties());
		connection.setAutoCommit(true);
		Statement statement = connection.createStatement();
		statement.execute("CREATE TABLE stable(code char(80) not null,ts timestamp,qty double, primary key(code))");
		List<STable> testArray = new ArrayList<STable>();

		for (int i = 0; i < 100000; i++) {
			STable entry = new STable();
			entry.code = UUID.randomUUID().toString();
			entry.ts = new Timestamp(System.currentTimeMillis());
			entry.qty = Math.random() * 10000.0;
			testArray.add(entry);
		}

		long time = System.currentTimeMillis();
		CloseablePersister<STable> consumer = BaseDBBean.getStreamPersister(connection, STable.class, false);
		testArray.stream().forEach(consumer);
		consumer.close();
		long passed = System.currentTimeMillis() - time;
		System.out.println("Inserted 100000 records in " + passed + " msec");
		BaseDBBeanDao<STable> dao = BaseDBBeanDao.create(STable.class);

		Consumer<STable> counterConsumer = new Consumer<TestBaseDBBean.STable>() {
			int nRecords = 0;

			@Override
			public void accept(STable t) {
				nRecords++;
				if ((nRecords % 1000) == 0) {
					System.out.println("Streamed " + nRecords + " objects");
				}
			}
		};
		Stream<STable> stream = dao.streamBy(connection);
		stream.forEach(counterConsumer);
		counterConsumer = new Consumer<TestBaseDBBean.STable>() {
			int nRecords = 0;

			@Override
			public void accept(STable t) {
				nRecords++;
				if ((nRecords % 100) == 0) {
					System.out.println("Streamed " + nRecords + " objects");
				}
			}
		};
		 stream = dao.streamByWhere(connection, "qty>=10 and qty<=100");
		stream.forEach(counterConsumer);
	}

}
