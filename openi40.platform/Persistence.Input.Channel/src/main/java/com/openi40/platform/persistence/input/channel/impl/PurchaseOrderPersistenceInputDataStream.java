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
package com.openi40.platform.persistence.input.channel.impl;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.openi40.platform.persistence.input.channel.AbstractPersistenceInputDataStream;
import com.openi40.platform.persistence.input.channel.JpaStreamLoader;
import com.openi40.platform.persistence.input.channel.model.PurchaseOrder;
import com.openi40.scheduler.input.model.orders.PurchaseOrderInputDto;

@Service
public class PurchaseOrderPersistenceInputDataStream extends
		AbstractPersistenceInputDataStream<PurchaseOrder, PurchaseOrderInputDto, PurchaseOrderPersistenceInputDataStream.PurchaseOrderStreamLoadRelated> {
	@Service
	public static class PurchaseOrderStreamLoadRelated extends JpaStreamLoader<PurchaseOrderInputDto, PurchaseOrder> {

		PurchaseOrderStreamLoadRelated(EntityManager entityManager) {
			super(PurchaseOrder.class, PurchaseOrderInputDto.class, entityManager, "code");

		}

	};

	public PurchaseOrderPersistenceInputDataStream(PurchaseOrderStreamLoadRelated repository) {
		super(PurchaseOrder.class, PurchaseOrderInputDto.class, repository);

	}

}
