package com.openi40.mes.datamodel.persister;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.openi40.dbmodel.easydbbeans.PersistenceException;
import com.openi40.mes.metamessaging.handlers.MessageReceiver;
import com.openi40.mes.metamessaging.handlers.MessagingEnvironment;
import com.openi40.mes.metamessaging.model.AbstractOI40IOTApplicationMessage;
import com.openi40.mes.metamessaging.model.AbstractOI40IOTMetaMessage;
import com.openi40.mes.metamessaging.model.AbstractOI40MetaMessage;
import com.openi40.mes.metamessaging.model.ManagedMessageType;
import com.openi40.mes.metamessaging.model.SpooledRetryEnvelopeMessage;
import com.openi40.mes.metamessaging.model.UnmanageableMessageEnvelope;
import com.openi40.mes.metamessaging.model.VolatileMessageType;
import com.openi40.mes.metamessaging.model.jsonutil.MetaMsgJsonUtil;

@Service
@Qualifier(value = MessageReceiver.IOT_KERNEL_RECEIVER)
public class OI40DatamodelPersisterKernelMessageReceiver implements MessageReceiver {
	static Logger LOGGER = LoggerFactory.getLogger(OI40DatamodelPersisterKernelMessageReceiver.class);
	@Autowired
	DataSource dataSource;

	public OI40DatamodelPersisterKernelMessageReceiver() {

	}

	@Override
	public void onMessage(AbstractOI40MetaMessage msg, MessagingEnvironment environment) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin onMessage([" + msg.getMsgId() + "])");
		}
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			if (msg instanceof SpooledRetryEnvelopeMessage) {
				this.persistSpooledRetry((SpooledRetryEnvelopeMessage) msg, connection);
			} else if (msg instanceof UnmanageableMessageEnvelope) {
				this.persistUnmanaged((UnmanageableMessageEnvelope) msg, connection);
			} else {
				this.persist(msg, connection);
			}
			connection.commit();
		} catch (Throwable th) {
			LOGGER.error("Persistent exception", th);
			try {
				connection.rollback();
			} catch (Throwable t1) {
			}
		} finally {
			try {
				connection.close();
			} catch (Throwable th) {
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End onMessage([" + msg.getMsgId() + "])");
		}
	}

	private void removeFromTable(String tableName, Long id, Connection connection) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("delete from " + tableName + " where id=" + id);
		ps.clearParameters();
		ps.setLong(1, id);
		ps.execute();
	}

	private void persist(AbstractOI40MetaMessage msg, Connection connection)
			throws SQLException, JsonGenerationException, JsonMappingException, IOException, PersistenceException {
		MesLogicalMsg logicalMsg = new MesLogicalMsg();
		copyAttributes(msg, logicalMsg);
		Long id = msg.getPersistedId();
		if (id != null) {
			logicalMsg.setId(id);
			logicalMsg.update(connection);
		} else {
			logicalMsg.insert(connection);
			msg.setPersistedId(logicalMsg.getId());
		}
	}

	private void persistUnmanaged(UnmanageableMessageEnvelope msg, Connection connection)
			throws SQLException, PersistenceException, JsonGenerationException, JsonMappingException, IOException {
		MesUnmanagedMsg unmanaged = new MesUnmanagedMsg();
		copyAttributes(msg, unmanaged);
		unmanaged.insert(connection);
		if (msg.getContent() != null && msg.getContent().getPersistedId() != null) {
			this.removeFromTable("MES_LOGICAL_MSG", msg.getContent().getPersistedId(), connection);
		}

	}

	private void persistSpooledRetry(SpooledRetryEnvelopeMessage msg, Connection connection)
			throws JsonGenerationException, JsonMappingException, IOException, PersistenceException, SQLException {
		MesSpoolMsg spmsg = new MesSpoolMsg();
		spmsg.setSpool_type("RETRYSPOOL");
		spmsg.setResend_trheshold(msg.getRetryThreshold());
		copyAttributes(msg, spmsg);
		spmsg.insert(connection);
		if (msg.getContent() != null && msg.getContent().getPersistedId() != null) {
			this.removeFromTable("MES_LOGICAL_MSG", msg.getContent().getPersistedId(), connection);
		}

	}

	private void copyAttributes(AbstractOI40MetaMessage msg, MesLogicalMsg _msg)
			throws JsonGenerationException, JsonMappingException, IOException {
		_msg.setMessage_uuid(msg.getMsgId());
		_msg.setMessage_time(msg.getTimestamp());
		if (_msg.getMessage_time() == null) {
			_msg.setMessage_time(new Timestamp(System.currentTimeMillis()));
		}
		if (msg instanceof AbstractOI40IOTApplicationMessage) {
			AbstractOI40IOTApplicationMessage appMsg=(AbstractOI40IOTApplicationMessage) msg;
			_msg.setMes_asset_code(appMsg.getAssetFrom());			
		}
		_msg.setTo_ref(msg.getTo());
		_msg.setFrom_ref(msg.getFrom());
		String payload = MetaMsgJsonUtil.serializeToString(msg);
		_msg.setPayload(payload);
	}

	@Override
	public boolean isCanManage(AbstractOI40MetaMessage msg) {
		boolean canManage = true;
		// Avoid VolatileMessageType annotated types
		if (msg != null && msg.getClass().isAnnotationPresent(VolatileMessageType.class)) {
			canManage = false;
		}
		return canManage;
	}

	@Override
	public ManagedMessageType[] getHandledTypes() {

		return new ManagedMessageType[0];
	}

}
