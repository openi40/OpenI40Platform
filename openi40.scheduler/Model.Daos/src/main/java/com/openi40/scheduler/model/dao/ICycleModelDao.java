package com.openi40.scheduler.model.dao;

import java.util.List;

import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.ICompanyStructureNode;
import com.openi40.scheduler.model.cycle.CycleModel;
import com.openi40.scheduler.model.material.Product;
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
public interface ICycleModelDao extends IApsDataModelDao<CycleModel> {
	CycleModel findDefaultCycleByProducingItem(Product product, ApsData context) throws DataModelDaoException;

	CycleModel findDefaultCycleByProducingItem(Product product, ICompanyStructureNode context) throws DataModelDaoException;

	List<CycleModel> findByProducingItem(Product product, ApsData context) throws DataModelDaoException;

	List<CycleModel> findByProducingItem(Product product, ICompanyStructureNode context) throws DataModelDaoException;
}
