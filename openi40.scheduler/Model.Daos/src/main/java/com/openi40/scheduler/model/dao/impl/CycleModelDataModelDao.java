package com.openi40.scheduler.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.ICompanyStructureNode;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.cycle.CycleModel;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.ICycleModelDao;
import com.openi40.scheduler.model.dao.IPlantDao;
import com.openi40.scheduler.model.dao.ISelector;
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
public class CycleModelDataModelDao extends AbstractApsDataModelDao<CycleModel> implements ICycleModelDao {

	public CycleModelDataModelDao() {
		super(CycleModel.class, new DataPathCfg(CycleModel.class)
				.withPath(ApsData.class, "ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/CycleModels")
				.withPath(ProductiveCompany.class, "ProductiveCompany/Plants/Plant/CycleModels")
				.withPath(Plant.class, "Plant/CycleModels"));

	}

	@Autowired
	IPlantDao plantDao;

	@Override
	public void insert(CycleModel entry, ApsData context) throws DataModelDaoException {
		String plantCode = entry.getPlantCode();
		Plant plant = findParent(entry, plantCode, Plant.class, plantDao, context);
		synchronized (context) {
			plant.getCycleModels().add(entry);
			entry.restructureOperations();
		}
	}

	@Override
	public void delete(CycleModel entry, ApsData context) throws DataModelDaoException {
		String plantCode = entry.getPlantCode();
		Plant plant = findParent(entry, plantCode, Plant.class, plantDao, context);
		synchronized (context) {
			plant.getCycleModels().remove(entry);
			entry.restructureOperations();
		}

	}

	@Override
	public List<CycleModel> findByProducingItem(Product product, ApsData context) throws DataModelDaoException {

		return this.findBySelector(new ISelector<CycleModel>() {
			@Override
			public boolean isSelectable(CycleModel t) {

				return product != null && product.getCode() != null && t.getProductCode() != null
						&& t.getProductCode().equals(product.getCode());
			}
		}, context);
	}

	@Override
	public List<CycleModel> findByProducingItem(Product product, ICompanyStructureNode context) throws DataModelDaoException {

		return this.findBySelector(new ISelector<CycleModel>() {
			@Override
			public boolean isSelectable(CycleModel t) {

				return product != null && product.getCode() != null && t.getProductCode() != null
						&& t.getProductCode().equals(product.getCode());
			}
		}, context);
	}

	@Override
	public CycleModel findDefaultCycleByProducingItem(Product product, ApsData context) throws DataModelDaoException {
		List<CycleModel> cycles = findByProducingItem(product, context);
		CycleModel outCycle = cycles.size() == 1 ? cycles.get(0) : null;
		if (outCycle == null) {
			for (CycleModel cycleModel : cycles) {
				if (cycleModel.isDefaultProductCycle()) {
					outCycle = cycleModel;
					break;
				}
			}
			if (outCycle == null && !cycles.isEmpty())
				throw new DataModelDaoException(
						"there are no defined default production cycle for product: " + product.getCode());
		}
		if (outCycle == null)
			throw new DataModelDaoException("there are no defined production cycle for product: " + product.getCode());
		return outCycle;
	}

	@Override
	public CycleModel findDefaultCycleByProducingItem(Product product, ICompanyStructureNode context)
			throws DataModelDaoException {
		List<CycleModel> cycles = findByProducingItem(product, context);
		CycleModel outCycle = cycles.size() == 1 ? cycles.get(0) : null;
		if (outCycle == null) {
			for (CycleModel cycleModel : cycles) {
				if (cycleModel.isDefaultProductCycle()) {
					outCycle = cycleModel;
					break;
				}
			}
			if (outCycle == null && !cycles.isEmpty())
				throw new DataModelDaoException(
						"there are no defined default production cycle for product: " + product.getCode());
		}
		if (outCycle == null)
			throw new DataModelDaoException("there are no defined production cycle for product: " + product.getCode());
		return outCycle;
	}

}
