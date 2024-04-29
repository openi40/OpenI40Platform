package com.openi40.scheduler.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.ICompanyStructureNode;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IAbstractSupplyDao;
import com.openi40.scheduler.model.dao.ISelector;
import com.openi40.scheduler.model.material.ISupply;
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
public abstract class AbstractSupplyDao<SupplyType extends ISupply> extends AbstractApsDataModelDao<SupplyType>
		implements IAbstractSupplyDao<SupplyType> {

	protected AbstractSupplyDao(Class<SupplyType> searchedType, DataPathCfg dataPathConfig) {
		super(searchedType, dataPathConfig);

	}

	@Override
	public <T extends SupplyType> List<T> findByItem(Product product, ApsData apsData, Class<T> type)
			throws DataModelDaoException {
		ISelector<SupplyType> selector = new ISelector<SupplyType>() {

			@Override
			public boolean isSelectable(SupplyType t) {

				return type.isAssignableFrom(t.getClass()) && t.getSuppliedItem() != null
						&& t.getSuppliedItem().equals(product);
			}
		};
		List _return = this.findBySelector(selector, apsData);
		return new ArrayList<T>(_return);
	}

	@Override
	public List<SupplyType> findByItem(Product product, ApsData apsData) throws DataModelDaoException {
		return findByItem(product, apsData, searchedType);
	}

	@Override
	public List<SupplyType> findByItem(Product product, ICompanyStructureNode context) throws DataModelDaoException {
		ISelector<SupplyType> selector = new ISelector<SupplyType>() {

			@Override
			public boolean isSelectable(SupplyType t) {

				return t.getSuppliedItem() != null && t.getSuppliedItem().equals(product);
			}
		};
		List _return = this.findBySelector(selector, context);
		return _return;
	}

}
