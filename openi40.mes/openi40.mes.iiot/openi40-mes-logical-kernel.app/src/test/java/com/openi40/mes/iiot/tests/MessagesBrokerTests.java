package com.openi40.mes.iiot.tests;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Vector;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.openi40.mes.datamodel.persister.MesLogicalMsg;
import com.openi40.mes.metamessaging.handlers.IMicroKernel;
import com.openi40.mes.metamessaging.model.AbstractOI40IOTApplicationMessage;

import lombok.Data;

public class MessagesBrokerTests extends AbstractMesDBSupportTest {
	@Autowired
	BeanFactory factory;
	@Autowired DataSource dataSource;
	public MessagesBrokerTests() {

	}
	protected <Type> Type doInject(Class<Type> typeclass) {
		return factory.getBean(typeclass);
	}
	@Data
	public static class ToBeSavedApplicationMessage extends AbstractOI40IOTApplicationMessage {
		private String contentField=null;
	}
	
	@Test
	public void dumpSaveMessageTest() throws IOException, SQLException {
		this.prepareDB();
		
		
		IMicroKernel microKernel=doInject(IMicroKernel.class);
		for(int i=0;i<20;i++) {
			ToBeSavedApplicationMessage message=new ToBeSavedApplicationMessage();
			message.setAssetFrom("TEST");
			message.setAssetTo("TEST");
			message.setContentField("Hi! i'm testing the microkernel msg "+i);
			microKernel.onMessage(message, null);
		}
		
		MesLogicalMsg mesLogicalMsg=new MesLogicalMsg();
		Connection connection=dataSource.getConnection();
		Vector inserted = mesLogicalMsg.findBy(connection, new Hashtable());
		assert inserted.size()==20;
		this.dropDB();
	}

}
