package com.openi40.mes.datamodel.persister;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openi40.mes.datamodel.OI40DBMesAssetEvent;
import com.openi40.mes.datamodel.repositories.OI40MesAssetEventRepository;
import com.openi40.mes.metamessaging.handlers.MessageReceiver;
import com.openi40.mes.metamessaging.handlers.MessagingEnvironment;
import com.openi40.mes.metamessaging.model.AbstractOI40MetaMessage;
import com.openi40.mes.metamessaging.model.ManagedMessageType;
import com.openi40.mes.metamessaging.model.SpooledRetryEnvelopeMessage;
import com.openi40.mes.metamessaging.model.UnmanageableMessageEnvelope;

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

	private void persist(AbstractOI40MetaMessage msg, String tableName, Map<String, Object> addictionalFields,
			Connection connection) throws SQLException {

	}

	private void removeFromTable(String tableName, Long id, Connection connection) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("delete from " + tableName + " where id=" + id);
		ps.clearParameters();
		ps.setLong(1, id);
		ps.execute();
	}

	private Map<String, Object> readAdditionals(AbstractOI40MetaMessage msg) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		return map;
	}

	private void persist(AbstractOI40MetaMessage msg, Connection connection) throws SQLException {
		Map<String, Object> additionals = readAdditionals(msg);
		this.persist(msg, "MES_LOGICAL_MSG", additionals, connection);
	}

	private void persistUnmanaged(UnmanageableMessageEnvelope msg, Connection connection) throws SQLException {
		Map<String, Object> additionals = readAdditionals(msg);
		this.persist(msg, "MES_UNMANAGED_MSG", additionals, connection);
		if (msg.getContent() != null && msg.getContent().getPersistedId() != null) {
			this.removeFromTable("MES_LOGICAL_MSG", msg.getContent().getPersistedId(), connection);
		}

	}

	private void persistSpooledRetry(SpooledRetryEnvelopeMessage msg, Connection connection) throws SQLException {
		Map<String, Object> additionals = readAdditionals(msg);
		additionals.put("spool_type", "RETRYSPOOL");
		additionals.put("resend_trheshold", msg.getRetryThreshold());
		this.persist(msg, "MES_SPOOL_MSG", additionals, connection);
		if (msg.getContent() != null && msg.getContent().getPersistedId() != null) {
			this.removeFromTable("MES_LOGICAL_MSG", msg.getContent().getPersistedId(), connection);
		}

	}

	@Override
	public boolean isCanManage(AbstractOI40MetaMessage msg) {

		return true;
	}

	@Override
	public ManagedMessageType[] getHandledTypes() {

		return new ManagedMessageType[0];
	}

}
