package com.openi40.scheduler.model.dao.impl;

import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.cycle.BomItemModel;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IBomItemModelDao;
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
@Service
public class BomItemModelDaoImpl extends AbstractApsDataModelDao<BomItemModel> implements IBomItemModelDao {

	public BomItemModelDaoImpl() {
		super(BomItemModel.class, new DataPathCfg(BomItemModel.class).withPath(ApsData.class,
				"ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/CycleModels/CycleModel/Operations/OperationModel/BomItems")
				.withPath(ProductiveCompany.class,
						"ProductiveCompany/Plants/Plant/CycleModels/CycleModel/Operations/OperationModel/BomItems")
				.withPath(Plant.class, "Plant/CycleModels/CycleModel/Operations/OperationModel/BomItems"));

	}

	@Override
	public void insert(BomItemModel entry, ApsData context) throws DataModelDaoException {

	}

	@Override
	public void delete(BomItemModel entry, ApsData context) throws DataModelDaoException {

	}

}
