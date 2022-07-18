package com.openi40.scheduler.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.ISecondaryResourceDao;
import com.openi40.scheduler.model.dao.ISecondaryResourceGroupDao;
import com.openi40.scheduler.model.equipment.Resource;
import com.openi40.scheduler.model.equipment.ResourceGroup;
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
public class SecondaryResourceDataModelDao extends AbstractApsDataModelDao<Resource> implements ISecondaryResourceDao {

	public SecondaryResourceDataModelDao() {
		super(Resource.class, new DataPathCfg(Resource.class).withPath(ApsData.class,
				"ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/Departments/Department/SecondaryResourceGroups/ResourceGroup/Resources")
				.withPath(ProductiveCompany.class,
						"ProductiveCompany/Plants/Plant/Departments/Department/SecondaryResourceGroups/ResourceGroup/Resources")
				.withPath(Plant.class,
						"Plant/Departments/Department/SecondaryResourceGroups/ResourceGroup/Resources"));

	}

	@Autowired
	ISecondaryResourceGroupDao secondaryResourceGroupDao;

	@Override
	public void insert(Resource entry, ApsData context) throws DataModelDaoException {
		ResourceGroup group = findParent(entry, entry.getResourcesGroupCode(), ResourceGroup.class,
				secondaryResourceGroupDao, context);
		synchronized (context) {
			group.getResources().add(entry);
		}
	}

	@Override
	public void delete(Resource entry, ApsData context) throws DataModelDaoException {
		ResourceGroup group = findParent(entry, entry.getResourcesGroupCode(), ResourceGroup.class,
				secondaryResourceGroupDao, context);
		synchronized (context) {
			group.getResources().remove(entry);
		}

	}
}