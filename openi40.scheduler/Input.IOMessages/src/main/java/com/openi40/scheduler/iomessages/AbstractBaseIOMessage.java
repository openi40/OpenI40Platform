package com.openi40.scheduler.iomessages;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import lombok.Data;
@Data
public abstract class AbstractBaseIOMessage  implements Serializable {
	protected String code = UUID.randomUUID().toString();
	protected String description = null;
	protected Date receivedTimestamp = null;
	protected Date messageTimestamp = null;	
}
