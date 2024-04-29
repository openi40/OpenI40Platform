package com.openi40.scheduler.model.dao;

import java.util.List;

import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.ICompanyStructureNode;
import com.openi40.scheduler.model.material.Product;
import com.openi40.scheduler.model.orders.AbstractOrderLine;
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
public interface IAbstractOrderLineDao<AbstractOrderLineType extends AbstractOrderLine>
		extends IApsDataModelDao<AbstractOrderLineType> {
	public List<AbstractOrderLineType> findByItem(Product product, ApsData apsdata) throws DataModelDaoException;
	public List<AbstractOrderLineType> findByItem(Product product, ICompanyStructureNode context)throws DataModelDaoException;
}
