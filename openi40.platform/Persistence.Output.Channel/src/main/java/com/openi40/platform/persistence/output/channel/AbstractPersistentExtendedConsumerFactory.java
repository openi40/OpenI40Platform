/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 */
package com.openi40.platform.persistence.output.channel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openi40.scheduler.output.model.OutputDto;

public abstract class AbstractPersistentExtendedConsumerFactory<OutType extends OutputDto> {
	protected Class<OutType> managedType = null;

	protected ObjectMapper mapper = null;

	public AbstractPersistentExtendedConsumerFactory(ObjectMapper mapper, Class<OutType> type) {
		this.managedType = type;
		this.mapper = mapper;
	}

	public abstract AbstractPersistentExtendedConsumer<OutType> create(JDBCOutputTransactionWrapper transactionWrapper);

	public Class<OutType> getManagedType() {

		return managedType;
	}

}
