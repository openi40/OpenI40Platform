package com.openi40.scheduler.model.dao.impl;

import java.util.List;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.ICompanyStructureNode;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.ISelector;
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
public abstract class AbstractOrderLineDao<AbstractOrderLineType extends AbstractOrderLine>
		extends AbstractApsDataModelDao<AbstractOrderLineType> {

	protected AbstractOrderLineDao(Class<AbstractOrderLineType> searchedType, DataPathCfg dataPathConfig) {
		super(searchedType, dataPathConfig);

	}

	public List<AbstractOrderLineType> findByItem(Product product, ApsData apsdata) throws DataModelDaoException {
		ISelector<AbstractOrderLineType> selector = new ISelector<AbstractOrderLineType>() {

			@Override
			public boolean isSelectable(AbstractOrderLineType t) {
				return t.getProduct() != null && product != null && t.getProduct().getCode() != null
						&& product.getCode() != null && t.getProduct().getCode().equals(product.getCode());
			}

		};
		return this.findBySelector(selector, apsdata);
	}

	public List<AbstractOrderLineType> findByItem(Product product, ICompanyStructureNode context)
			throws DataModelDaoException {
		ISelector<AbstractOrderLineType> selector = new ISelector<AbstractOrderLineType>() {

			@Override
			public boolean isSelectable(AbstractOrderLineType t) {
				return t.getProduct() != null && product != null && t.getProduct().getCode() != null
						&& product.getCode() != null && t.getProduct().getCode().equals(product.getCode());
			}

		};
		return this.findBySelector(selector, context);
	}

}
