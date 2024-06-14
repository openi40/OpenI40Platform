package com.openi40.scheduler.common.aps;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
public interface IApsObject extends Serializable {

	String getId();

	long getUniqueId();

	void setId(String value);

	String getDescription();

	void setDescription(String value);

	String getCode();

	void setCode(String value);

	String getOwner();

	void setOwner(String value);

	boolean isSimulatedItem();

	void setSimulatedItem(boolean value);

	/**
	 * @param name
	 * @return
	 */
	Object getAttribute(String name);

	/**
	 * @param name
	 * @param value
	 */
	void setAttribute(String name, Object value);

	IApsData getContext();
	public ICustomObject getCustomObject();
	public void setCustomObject(ICustomObject c);
	/**
	 * This method releases all resources/scheduling results to value required
	 * before scheduling
	 */
	void resetSchedulingData();

	Date getModifiedTimestamp();

	void setModifiedTimestamp(Date value);

	boolean isLocked();

	void setLocked(boolean locked);
}