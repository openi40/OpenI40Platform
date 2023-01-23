package com.openi40.scheduler.model.aps;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.openi40.scheduler.model.AbstractApsObject;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author architectures@openi40.org
 *
 */
public class ApsMessage extends AbstractApsObject

{
	
	private ApsMessageCategory messageCategory = null;
	private String messageCode = null;
	private String messageDescription = null;
	private Map<String, String> messageParameters = new HashMap<>();
	private ApsMessageLevel msgLevel = ApsMessageLevel.INFO;
	private String sourceModule = null;
	private String sourceObjectClass = null;
	private Map<String, Object> objectsMap = new HashMap<String, Object>();
	private Integer position=0;
	private Integer globalPosition=0;
	private String taskCode=null;
	public ApsMessage(Object messageSource, ApsMessageConstrants msgConstant, Map<String, Object> objectsMap,
			ApsData context) {
		super(context);
		this.messageCategory = msgConstant.getMsgCategory();
		this.messageCode = msgConstant.getCode();
		this.messageDescription = msgConstant.getDescription();
		this.msgLevel = msgConstant.getMsgLevel();
		sourceObjectClass = messageSource.getClass().getName();
		int offset = getSourceObjectClass().lastIndexOf('.');
		sourceModule = getSourceObjectClass().substring(0, offset - 1);
		this.objectsMap = new HashMap<String, Object>(objectsMap);
		
	}

	public ApsMessage(Object messageSource, String msgCode, String msgDescription, ApsMessageCategory messageCategory,
			ApsMessageLevel level, Map<String, String> messageParameters, ApsData context) {
		super(context);
		messageCode = msgCode;
		messageDescription = msgDescription;
		sourceObjectClass = messageSource.getClass().getName();
		int offset = getSourceObjectClass().lastIndexOf('.');
		sourceModule = getSourceObjectClass().substring(0, offset - 1);
		this.messageParameters = new HashMap<>();
		this.msgLevel = level;
	}

	public ApsMessage(ApsData context) {
		super(context);
	}

	public String getMessageCode() {
		return messageCode;
	}

	public String getMessageDescription() {
		return this.getUserMessage();
	}

	private static Class[] ACCEPTED_PROPERTY_TYPES = new Class[] { Boolean.class, String.class, Number.class,
			Date.class };
	private static DateFormat DATEFORMAT = SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.SHORT,
			SimpleDateFormat.SHORT, Locale.ITALIAN);

	public Map<String, String> getMessageParameters() {
		Map<String, String> params = new HashMap<String, String>(messageParameters);
		if (this.objectsMap != null && !this.objectsMap.isEmpty()) {
			for (Map.Entry<String, Object> entry : this.objectsMap.entrySet()) {
				String objectName = entry.getKey();
				Object object = entry.getValue();
				if (object != null) {
					for (Method method : object.getClass().getMethods()) {
						if (method.getParameterCount() == 0
								&& ((method.getName().startsWith("is") && method.getReturnType().isPrimitive())
										|| method.getName().startsWith("get"))) {

							for (Class type : ACCEPTED_PROPERTY_TYPES) {
								if (type.isAssignableFrom(method.getReturnType())) {
									try {
										Object _value = method.invoke(object);
										if (_value != null) {
											char _propertyName[] = (method.getName().startsWith("get")
													? method.getName().substring("get".length())
													: method.getName().substring("is".length())).toCharArray();
											_propertyName[0] = Character.toLowerCase(_propertyName[0]);
											String propertyName = new String(_propertyName);
											if (_value instanceof Date) {
												_value = DATEFORMAT.format((Date) _value);
											}
											params.put(objectName + "." + propertyName, _value.toString());
										}
									} catch (Throwable th) {
									}
								}
							}
						}
					}
				}
			}
		}
		return params;
	}

	public ApsMessageLevel getMsgLevel() {
		return msgLevel;
	}

	public String getSourceModule() {
		return sourceModule;
	}

	public String getSourceObjectClass() {
		return sourceObjectClass;
	}

	public String getUserMessage() {
		String _msg = messageDescription;
		Map<String, String> params = this.getMessageParameters();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			_msg = _msg.replace("${" + entry.getKey() + "}", entry.getValue());

		}
		return _msg;
	}

	public String toString() {
		return "{messageCategory=" + messageCategory + ",msgLevel=" + msgLevel + ",code=" + messageCode
				+ ",description=" + messageDescription + ",params=" + messageParameters + "}";
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public ApsMessageCategory getMessageCategory() {
		return messageCategory;
	}

	public Integer getGlobalPosition() {
		return globalPosition;
	}

	public void setGlobalPosition(Integer globalPosition) {
		this.globalPosition = globalPosition;
	}

	public void setMessageCategory(ApsMessageCategory messageCategory) {
		this.messageCategory = messageCategory;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public void setMessageDescription(String messageDescription) {
		this.messageDescription = messageDescription;
	}

	public void setMsgLevel(ApsMessageLevel msgLevel) {
		this.msgLevel = msgLevel;
	}

	public void setSourceModule(String sourceModule) {
		this.sourceModule = sourceModule;
	}

	public void setSourceObjectClass(String sourceObjectClass) {
		this.sourceObjectClass = sourceObjectClass;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
}