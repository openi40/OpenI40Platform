package com.openi40.scheduler.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.cycle.CycleModel;
import com.openi40.scheduler.model.cycle.OperationModel;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.ICycleModelDao;
import com.openi40.scheduler.model.dao.IPlantDao;
import com.openi40.scheduler.model.dao.ISelector;
import com.openi40.scheduler.model.dao.IOperationModelDao;
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
@Service
public class OperationModelDataModelDao extends AbstractApsDataModelDao<OperationModel> implements IOperationModelDao {

	public OperationModelDataModelDao() {
		super(OperationModel.class, new DataPathCfg(OperationModel.class)
				.withPath(ApsData.class, "ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/CycleModels/CycleModel/Operations")
				.withPath(ProductiveCompany.class, "ProductiveCompany/Plants/Plant/CycleModels/CycleModel/Operations")
				.withPath(Plant.class, "Plant/CycleModels/CycleModel/Operations"));

	}

	@Autowired
	ICycleModelDao cycleDao;

	@Override
	public void insert(OperationModel entry, ApsData context) throws DataModelDaoException {

		String cycleCode = entry.getCycleCode();
		CycleModel cycle = this.findParent(entry, cycleCode, CycleModel.class, cycleDao, context);
		synchronized (context) {
			cycle.getOperations().add(entry);
			cycle.restructureOperations();
		}
		
	}

	@Override
	public List<OperationModel> findByProducingItem(Product itemToSearch, ApsData apsData) throws DataModelDaoException {

		ISelector<OperationModel> selector = new ISelector<OperationModel>() {

			@Override
			public boolean isSelectable(OperationModel taskModel) {
				if (itemToSearch == null && taskModel.getItemProducedModel() != null) {
					return true;
				}
				if (itemToSearch != null && itemToSearch.getCode() != null && taskModel.getItemProducedModel() != null
						&& taskModel.getItemProducedModel().getSuppliedItem() != null
						&& taskModel.getItemProducedModel().getSuppliedItem().getCode() != null && taskModel
								.getItemProducedModel().getSuppliedItem().getCode().equals(itemToSearch.getCode())) {
					return true;
				}
				return false;
			}
		};
		return this.findBySelector(selector, apsData);
	}

	@Override
	public List<OperationModel> findByAllProducingItem(ApsData apsData) throws DataModelDaoException {
		return findByProducingItem(null, apsData);
	}

	@Override
	public void delete(OperationModel entry, ApsData context) throws DataModelDaoException {
		String cycleCode = entry.getCycleCode();
		CycleModel cycle = this.findParent(entry, cycleCode, CycleModel.class, cycleDao, context);
		synchronized (context) {
			cycle.getOperations().remove(entry);
			cycle.restructureOperations();
		}
	}

	@Override
	public List<OperationModel> findByCycleModelCode(String cycleModel, ApsData context) throws DataModelDaoException {

		return this.findBySelector(new ISelector<OperationModel>() {
			@Override
			public boolean isSelectable(OperationModel t) {

				return t.getCycleCode() != null && cycleModel != null && t.getCycleCode().equals(cycleModel);
			}
		}, context);
	}

}