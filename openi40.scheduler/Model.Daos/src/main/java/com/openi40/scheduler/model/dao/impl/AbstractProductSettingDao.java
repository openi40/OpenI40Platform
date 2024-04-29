package com.openi40.scheduler.model.dao.impl;

import java.util.List;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.ICompanyStructureNode;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.ISelector;
import com.openi40.scheduler.model.material.configuration.AbstractProductSetting;
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
public abstract class AbstractProductSettingDao<ProductSettingType extends AbstractProductSetting>
		extends AbstractApsDataModelDao<ProductSettingType> {

	protected AbstractProductSettingDao(Class<ProductSettingType> searchedType, DataPathCfg dataPathConfig) {
		super(searchedType, dataPathConfig);

	}
	
	public ProductSettingType findByProductCode(String productCode, ApsData context) throws DataModelDaoException {
		List<ProductSettingType> results = this.findBySelector(new ISelector<ProductSettingType>() {

			@Override
			public boolean isSelectable(ProductSettingType t) {

				return productCode == null || (t.getProductCode() != null && t.getProductCode().equals(productCode));
			}

		}, context);
		return results != null && !results.isEmpty() ? results.get(0) : null;
	}

	public ProductSettingType findByProductCode(String productCode, ICompanyStructureNode context)
			throws DataModelDaoException {
		List<ProductSettingType> results = this.findBySelector(new ISelector<ProductSettingType>() {

			@Override
			public boolean isSelectable(ProductSettingType t) {

				return productCode == null || (t.getProductCode() != null && t.getProductCode().equals(productCode));
			}

		}, context);
		return results != null && !results.isEmpty() ? results.get(0) : null;
	}
}
