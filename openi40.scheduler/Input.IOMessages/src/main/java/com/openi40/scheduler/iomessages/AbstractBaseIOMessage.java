package com.openi40.scheduler.iomessages;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public abstract class AbstractBaseIOMessage  implements Serializable {
	protected String code = null;
	protected String description = null;
	protected Date receivedTimestamp = null;
	protected Date messageTimestamp = null;	
}
