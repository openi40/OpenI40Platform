package com.openi40.scheduler.engine.messages.handling.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.messages.handling.ApsMessageManagementException;
import com.openi40.scheduler.engine.messages.handling.ApsMessageValidationException;
import com.openi40.scheduler.engine.messages.handling.IBaseMessagesHandler;
import com.openi40.scheduler.model.ReservableObjectAvailability;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IMachineDao;
import com.openi40.scheduler.model.dao.ISecondaryResourceDao;
import com.openi40.scheduler.model.dao.ITaskDao;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.equipment.Resource;
import com.openi40.scheduler.model.messages.AbstractBaseMessage;
import com.openi40.scheduler.model.messages.IMachineRelatedMessage;
import com.openi40.scheduler.model.messages.ISecondaryResourceRelatedMessage;
import com.openi40.scheduler.model.messages.ITaskRelatedMessage;
import com.openi40.scheduler.model.messages.handling.ApsMessageManagementResponse;
import com.openi40.scheduler.model.messages.handling.MessageHandlingErrorMessage;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.tasks.TaskStatus;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 * @param <MsgType>
 */
public abstract class AbstractSpecializedMessageHandler<MsgType extends AbstractBaseMessage>
		implements IBaseMessagesHandler<MsgType> {
	private Class<MsgType> messageType = null;
	protected Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	IMachineDao machineDao;
	@Autowired
	ITaskDao taskDao;
	@Autowired
	ISecondaryResourceDao secondaryResourceDao;
	@Autowired
	IMessagesManagementStateMachineGraphService stateMachineGraphService;

	public AbstractSpecializedMessageHandler(Class<MsgType> messageType) {
		this.messageType = messageType;
	}

	protected void checkAcquiredMachineCodeCoherency(MessageRelatedObjects contextObjects, MsgType message)
			throws ApsMessageValidationException {
		List<MessageHandlingErrorMessage> errors = new ArrayList<MessageHandlingErrorMessage>();
		if (message instanceof IMachineRelatedMessage && message instanceof ITaskRelatedMessage) {
			IMachineRelatedMessage mrmsg = (IMachineRelatedMessage) message;
			if (contextObjects.task.getAcquiredMachineCode() == null
					|| contextObjects.task.getAcquiredMachineCode().trim().length() == 0) {
				contextObjects.task.setAcquiredMachineCode(mrmsg.getMachineCode());
			} else if (!contextObjects.task.getAcquiredMachineCode().equals(mrmsg.getMachineCode())) {
				MessageHandlingErrorMessage emsg = new MessageHandlingErrorMessage();
				emsg.setErrorCode(CHANGED_MACHINE_CODE_ERROR);
				emsg.setErrorMsg("You cannot change machineCode from " + contextObjects.task.getAcquiredMachineCode()
						+ " to " + mrmsg.getMachineCode() + " in task:" + contextObjects.task.getCode()
						+ " with status=>" + contextObjects.task.getStatus().name());
				errors.add(emsg);
			}
		}
		if (!errors.isEmpty()) {
			throw new ApsMessageValidationException(errors, "checkAcquiredMachineCodeCoherency");
		}
	}

	@Override
	public ApsMessageManagementResponse handleMessage(MsgType msg, ApsData context)
			throws ApsMessageValidationException, ApsMessageManagementException {
		// Validate received task/machine/secondary resource codes &
		// actual task/machine/secondary resource state coherence versus the received
		// message
		MessageRelatedObjects contextObjects = this.basicMessageValidation(msg, context);
		// Custom validation
		this.contextObjectAwareMessageValidation(contextObjects, msg, context);
		// Validate system (machine/task/secondary resources) state VS received message
		// the system rejects messages not in the acceptable finite state machine
		// state/received messages
		// combination expressed from stateMachineGraphService
		this.validateSystemStateVsReceivedMessage(contextObjects, msg, context);
		// Change the system status on received message coherently with state machine
		// logic
		this.changeSystemStateOnReceivedMessage(contextObjects, msg, context);
		// Custom data assignations based on specific received event
		return this.messageSemanticDependentSystemStateChange(contextObjects, msg, context);
	}

	protected void changeSystemStateOnReceivedMessage(
			AbstractSpecializedMessageHandler<MsgType>.MessageRelatedObjects contextObjects, MsgType msg,
			ApsData context) {
		// Lock resources because we are processing production messages
		contextObjects.task.setProductionLock(true);
		TaskStatus nextTaskState = this.stateMachineGraphService.getNextTaskState(msg);
		ReservableObjectAvailability nextMachineState = this.stateMachineGraphService.getNextMachineStates(msg);
		if (nextTaskState != null && contextObjects.task != null) {
			contextObjects.task.setStatus(nextTaskState);
		}
		if (nextMachineState != null && contextObjects.machine != null) {
			contextObjects.machine.setAvailability(nextMachineState);
		}
		if (nextMachineState != null && contextObjects.resource != null) {
			contextObjects.resource.setAvailability(nextMachineState);
		}

	}

	/******
	 * Does message validation on II level once basic codes and domain model objects
	 * are retrieved and passed in MessageRelatedObjects
	 * 
	 * @param msg
	 * @param context
	 * @throws ApsMessageValidationException
	 */
	protected abstract void contextObjectAwareMessageValidation(MessageRelatedObjects contextObjects, MsgType message,
			ApsData context) throws ApsMessageValidationException;

	public static final String NULL_TASK_CODE_ERROR = "NULL_TASK_CODE_ERROR";
	public static final String UNKNOWN_TASK_CODE_ERROR = "UNKNOWN_TASK_CODE_ERROR";
	public static final String NULL_MACHINE_CODE_ERROR = "NULL_MACHINE_CODE_ERROR";
	public static final String UNKNOWN_MACHINE_CODE_ERROR = "UNKNOWN_MACHINE_CODE_ERROR";
	public static final String NULL_SECONDARY_RESOURCE_CODE_ERROR = "NULL_SECONDARY_RESOURCE_CODE_ERROR";
	public static final String UNKNOWN_SECONDARY_RESOURCE_CODE_ERROR = "UNKNOWN_SECONDARY_RESOURCE_CODE_ERROR";
	public static final String UNKNOWN_TASK_STATE_ERROR = "UNKNOWN_TASK_STATE_ERROR";
	public static final String UNKNOWN_MACHINE_STATE_ERROR = "UNKNOWN_MACHINE_STATE_ERROR";
	public static final String UNKNOWN_SECONDARY_RESOURCE_STATE_ERROR = "UNKNOWN_SECONDARY_RESOURCE_STATE_ERROR";
	public final static String INCOMPATIBLE_TASK_STATE_ERROR = "INCOMPATIBLE_TASK_STATE_ERROR";
	public final static String INCOMPATIBLE_MACHINE_STATE_ERROR = "INCOMPATIBLE_MACHINE_STATE_ERROR";
	public final static String NULL_MESSAGE_TIMESTAMP_ERROR = "NULL_MESSAGE_TIMESTAMP_ERROR";
	public final static String CHANGED_MACHINE_CODE_ERROR = "CHANGED_MACHINE_CODE_ERROR";
	public final static String NOT_PRODUCTION_CONTROL_ENABLED_ERROR = "NOT_PRODUCTION_CONTROL_ENABLED_ERROR";

	protected class MessageRelatedObjects {
		public Task task = null;
		public Machine machine = null;
		public Resource resource = null;
	}

	private void verifyValidMachineStates(ReservableObjectAvailability validStates[], MsgType message,
			MessageRelatedObjects contextObjects, ApsData context) throws ApsMessageValidationException {
		List<MessageHandlingErrorMessage> msgs = new ArrayList<MessageHandlingErrorMessage>();
		boolean correctMachineState = false;
		for (int i = 0; i < validStates.length; i++) {
			correctMachineState = correctMachineState || validStates[i] == contextObjects.machine.getAvailability();
		}
		if (!correctMachineState) {
			MessageHandlingErrorMessage errmessage = new MessageHandlingErrorMessage();
			errmessage.setErrorCode(INCOMPATIBLE_MACHINE_STATE_ERROR);
			errmessage.setErrorMsg("Incompatible machine " + contextObjects.machine.getAvailability().name()
					+ " on reception of StartSetupMessage for machine:" + contextObjects.machine.getCode() + " message "
					+ message.getCode());
			msgs.add(errmessage);
		}
		if (!msgs.isEmpty()) {
			throw new ApsMessageValidationException(msgs, "verifyValidTaskStates");
		}
	}

	private void verifyValidTaskStates(TaskStatus validStates[], MsgType message, MessageRelatedObjects contextObjects,
			ApsData context) throws ApsMessageValidationException {
		List<MessageHandlingErrorMessage> msgs = new ArrayList<MessageHandlingErrorMessage>();
		boolean correctTaskState = false;
		for (int i = 0; i < validStates.length; i++) {
			correctTaskState = correctTaskState || validStates[i] == contextObjects.task.getStatus();
		}
		if (!correctTaskState) {
			MessageHandlingErrorMessage errmessage = new MessageHandlingErrorMessage();
			errmessage.setErrorCode(INCOMPATIBLE_TASK_STATE_ERROR);
			errmessage.setErrorMsg("Incompatible state " + contextObjects.task.getStatus().name()
					+ " on reception of StartSetupMessage for task:" + contextObjects.task.getCode() + " message "
					+ message.getCode());

			msgs.add(errmessage);

		}
		if (!msgs.isEmpty()) {
			throw new ApsMessageValidationException(msgs, "verifyValidTaskStates");
		}

	}

	/**********************************************************************************************
	 * Verifies message data coherence (task,machine,secondary resources codes),
	 * 
	 * @param message
	 * @param context
	 * @return
	 * @throws ApsMessageValidationException
	 */
	protected MessageRelatedObjects basicMessageValidation(MsgType message, ApsData context)
			throws ApsMessageValidationException {
		MessageRelatedObjects relateds = new MessageRelatedObjects();
		List<MessageHandlingErrorMessage> messages = new ArrayList<MessageHandlingErrorMessage>();
		if (!context.isProductionControlEnabled()) {
			MessageHandlingErrorMessage msg = new MessageHandlingErrorMessage();
			msg.setErrorCode(NOT_PRODUCTION_CONTROL_ENABLED_ERROR);
			msg.setErrorMsg("This data set=> " + context.getDataSourceName() + "/" + context.getDataSetName() + "/"
					+ context.getDataSetVariant() + " has productionMessagesEnabled=false");
			messages.add(msg);
		}
		if (message.getMessageTimestamp() == null) {
			MessageHandlingErrorMessage msg = new MessageHandlingErrorMessage();
			msg.setErrorCode(NULL_MESSAGE_TIMESTAMP_ERROR);
			msg.setErrorMsg("Null messageTimestamp code in msg with code " + message.getCode());
			messages.add(msg);
		}
		if (message instanceof ITaskRelatedMessage) {
			ITaskRelatedMessage taskRelated = (ITaskRelatedMessage) message;
			String code = taskRelated.getTaskCode();
			if (code == null) {
				MessageHandlingErrorMessage msg = new MessageHandlingErrorMessage();
				msg.setErrorCode(NULL_TASK_CODE_ERROR);
				msg.setErrorMsg("Null task code in msg with code " + message.getCode());
				messages.add(msg);
			} else {
				try {
					Task task = taskDao.findByCode(code, context);
					relateds.task = task;
					if (task == null) {
						MessageHandlingErrorMessage msg = new MessageHandlingErrorMessage();
						msg.setErrorCode(UNKNOWN_TASK_CODE_ERROR);
						msg.setErrorMsg("Unknown task code=>" + code + " in msg with code " + message.getCode());
						messages.add(msg);
					} else if (task.getStatus() == null) {
						MessageHandlingErrorMessage msg = new MessageHandlingErrorMessage();
						msg.setErrorCode(UNKNOWN_TASK_STATE_ERROR);
						msg.setErrorMsg(
								"Unknown task state for code=>" + code + " in msg with code " + message.getCode());
						messages.add(msg);
					}
				} catch (DataModelDaoException e) {
					String _msg = "Exception in ITaskDao";
					LOGGER.error(_msg, e);
					throw new OpenI40Exception(_msg, e);
				}
			}
		}
		if (message instanceof IMachineRelatedMessage) {
			IMachineRelatedMessage machineRelated = (IMachineRelatedMessage) message;
			String code = machineRelated.getMachineCode();
			if (code == null) {
				MessageHandlingErrorMessage msg = new MessageHandlingErrorMessage();
				msg.setErrorCode(NULL_MACHINE_CODE_ERROR);
				msg.setErrorMsg("Null machine code in msg with code " + message.getCode());
				messages.add(msg);
			} else {
				try {
					Machine machine = machineDao.findByCode(code, context);
					relateds.machine = machine;
					if (machine == null) {
						MessageHandlingErrorMessage msg = new MessageHandlingErrorMessage();
						msg.setErrorCode(UNKNOWN_MACHINE_CODE_ERROR);
						msg.setErrorMsg("Unknown machine code=>" + code + " in msg with code " + message.getCode());
						messages.add(msg);
					} else if (machine.getAvailability() == null) {
						MessageHandlingErrorMessage msg = new MessageHandlingErrorMessage();
						msg.setErrorCode(UNKNOWN_MACHINE_STATE_ERROR);
						msg.setErrorMsg(
								"Unknown machine state for code=>" + code + " in msg with code " + message.getCode());
						messages.add(msg);
					}
				} catch (DataModelDaoException e) {
					String _msg = "Exception in IMachineDao";
					LOGGER.error(_msg, e);
					throw new OpenI40Exception(_msg, e);
				}
			}
		}
		if (message instanceof ISecondaryResourceRelatedMessage) {
			ISecondaryResourceRelatedMessage rcrelated = (ISecondaryResourceRelatedMessage) message;
			String code = rcrelated.getSecondaryResourceCode();
			if (code == null) {
				MessageHandlingErrorMessage msg = new MessageHandlingErrorMessage();
				msg.setErrorCode(NULL_SECONDARY_RESOURCE_CODE_ERROR);
				msg.setErrorMsg("Null secondary resource code in msg with code " + message.getCode());
				messages.add(msg);
			} else {
				try {
					Resource resource = secondaryResourceDao.findByCode(code, context);
					relateds.resource = resource;
					if (resource == null) {
						MessageHandlingErrorMessage msg = new MessageHandlingErrorMessage();
						msg.setErrorCode(UNKNOWN_SECONDARY_RESOURCE_CODE_ERROR);
						msg.setErrorMsg(
								"Unknown secondary resource code=>" + code + " in msg with code " + message.getCode());
						messages.add(msg);
					} else if (resource.getAvailability() == null) {
						MessageHandlingErrorMessage msg = new MessageHandlingErrorMessage();
						msg.setErrorCode(UNKNOWN_SECONDARY_RESOURCE_STATE_ERROR);
						msg.setErrorMsg("Unknown secondary resource state for code=>" + code + " in msg with code "
								+ message.getCode());
						messages.add(msg);
					}
				} catch (DataModelDaoException e) {
					String _msg = "Exception in ISecondaryResourceDao";
					LOGGER.error(_msg, e);
					throw new OpenI40Exception(_msg, e);
				}
			}
		}

		if (!messages.isEmpty()) {
			throw new ApsMessageValidationException(messages, "validation of message=>" + message.getCode());
		}
		return relateds;
	}

	/**
	 * Tests incoming message coherence to the finitestatemachine expressed with the
	 * IMessagesManagementStateMachineGraphService
	 * 
	 * @param relateds
	 * @param message
	 * @param context
	 * @throws ApsMessageValidationException
	 */
	protected void validateSystemStateVsReceivedMessage(MessageRelatedObjects relateds, MsgType message,
			ApsData context) throws ApsMessageValidationException {
		List<MessageHandlingErrorMessage> messages = new ArrayList<MessageHandlingErrorMessage>();
		ReservableObjectAvailability[] prerequisiteMachineStates = this.stateMachineGraphService
				.getPrerequisiteMachineStates(message);
		if (prerequisiteMachineStates != null && prerequisiteMachineStates.length > 0) {
			try {
				verifyValidMachineStates(prerequisiteMachineStates, message, relateds, context);
			} catch (ApsMessageValidationException exc) {
				messages.addAll(exc.getMessages());
			}
		}
		TaskStatus[] prerequisiteTaskStates = this.stateMachineGraphService.getPrerequisiteTaskStates(message);
		if (prerequisiteTaskStates != null && prerequisiteTaskStates.length > 0) {
			try {
				verifyValidTaskStates(prerequisiteTaskStates, message, relateds, context);
			} catch (ApsMessageValidationException exc) {
				messages.addAll(exc.getMessages());
			}
		}
		if (!messages.isEmpty()) {
			throw new ApsMessageValidationException(messages, "validation of message=>" + message.getCode());
		}
	}

	/*******
	 * Reports specific updates on machine/task/secondary resource/ApsData
	 * coherently with received message
	 * 
	 * @param msg
	 * @param context
	 * @return
	 * @throws ApsMessageManagementException
	 */
	protected abstract ApsMessageManagementResponse messageSemanticDependentSystemStateChange(
			MessageRelatedObjects contextObjects, MsgType message, ApsData context)
			throws ApsMessageManagementException;

	@Override
	public Class<MsgType> getHandledType() {

		return this.messageType;
	}

}
