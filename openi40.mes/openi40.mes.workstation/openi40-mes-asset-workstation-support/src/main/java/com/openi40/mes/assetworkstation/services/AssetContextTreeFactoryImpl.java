package com.openi40.mes.assetworkstation.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.dbmodel.java.client.api.Oi40DbDepartmentRepositoryApi;
import com.openi40.dbmodel.java.client.api.Oi40DbMachineRepositoryApi;
import com.openi40.dbmodel.java.client.api.Oi40DbPlantRepositoryApi;
import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyRepositoryApi;
import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseRepositoryApi;
import com.openi40.dbmodel.java.client.api.Oi40DbWorkCenterRepositoryApi;
import com.openi40.dbmodel.java.client.model.OI40DBDepartment;
import com.openi40.dbmodel.java.client.model.OI40DBMachine;
import com.openi40.dbmodel.java.client.model.OI40DBPlant;
import com.openi40.dbmodel.java.client.model.OI40DBProductiveCompany;
import com.openi40.dbmodel.java.client.model.OI40DBWarehouse;
import com.openi40.dbmodel.java.client.model.OI40DBWorkCenter;
import com.openi40.mes.assetworkstation.model.AssetContextObject;
import com.openi40.mes.assetworkstation.model.AssetContextObjectTree;
import com.openi40.mes.assetworkstation.model.AssetContextType;

@Singleton
@Service
public class AssetContextTreeFactoryImpl implements IAssetContextTreeFactory {
	@Autowired
	Oi40DbProductiveCompanyRepositoryApi companyApi;
	@Autowired
	Oi40DbPlantRepositoryApi plantApi;
	@Autowired
	Oi40DbDepartmentRepositoryApi departmentApi;
	@Autowired
	Oi40DbWarehouseRepositoryApi warehouseApi;
	@Autowired
	Oi40DbWorkCenterRepositoryApi workcenterApi;
	@Autowired
	Oi40DbMachineRepositoryApi machineApi;

	public AssetContextTreeFactoryImpl() {

	}

	@Override
	public List<AssetContextObjectTree> getContextRoots() {
		List<AssetContextObjectTree> rootCompanies = new ArrayList<AssetContextObjectTree>();
		List<AssetContextObjectTree> _plants = new ArrayList<AssetContextObjectTree>();
		List<OI40DBProductiveCompany> companies = companyApi.findAllListOI40DBProductiveCompany();
		for (Iterator iterator = companies.iterator(); iterator.hasNext();) {
			OI40DBProductiveCompany oi40dbProductiveCompany = (OI40DBProductiveCompany) iterator.next();
			AssetContextObjectTree item = new AssetContextObjectTree();
			item.setItem(new AssetContextObject(AssetContextType.COMPANY, oi40dbProductiveCompany.getCode(),oi40dbProductiveCompany.getDescription()));
			rootCompanies.add(item);
		}
		List<OI40DBPlant> plants = plantApi.findAllListOI40DBPlant();
		for (OI40DBPlant oi40dbPlant : plants) {
			AssetContextObjectTree item = new AssetContextObjectTree();
			item.setItem(new AssetContextObject(AssetContextType.PLANT, oi40dbPlant.getCode(),oi40dbPlant.getDescription()));
			_plants.add(item);
			for (AssetContextObjectTree company : rootCompanies) {
				if (oi40dbPlant.getProductiveCompanyCode() != null
						&& oi40dbPlant.getProductiveCompanyCode().equals(company.getItem().getObjectCode())) {
					company.getChilds().add(item);
				}
			}
		}
		List<OI40DBDepartment> departments = departmentApi.findAllListOI40DBDepartment();
		List<AssetContextObjectTree> _departments = new ArrayList<AssetContextObjectTree>();
		for (OI40DBDepartment dept : departments) {
			AssetContextObjectTree item = new AssetContextObjectTree();
			item.setItem(new AssetContextObject(AssetContextType.DEPARTMENT, dept.getCode(),dept.getDescription()));
			_departments.add(item);
			for (AssetContextObjectTree p : _plants) {
				if (p.getItem().getObjectCode().equals(dept.getPlantCode())) {
					p.getChilds().add(item);
				}
			}

		}
		List<OI40DBWorkCenter> workcenters = workcenterApi.findAllListOI40DBWorkCenter();
		List<AssetContextObjectTree> _workcenters = new ArrayList<AssetContextObjectTree>();
		for (OI40DBWorkCenter wc : workcenters) {
			AssetContextObjectTree item = new AssetContextObjectTree();
			item.setItem(new AssetContextObject(AssetContextType.WORKCENTER, wc.getCode(),wc.getDescription()));
			_workcenters.add(item);
			for (AssetContextObjectTree node : _departments) {
				if (wc.getDepartmentCode().equals(node.getItem().getObjectCode())) {
					node.getChilds().add(item);
				}
			}
		}
		List<OI40DBWarehouse> warehouses = warehouseApi.findAllListOI40DBWarehouse();
		List<AssetContextObjectTree> _warehouses = new ArrayList<AssetContextObjectTree>();
		for (OI40DBWarehouse wh : warehouses) {
			AssetContextObjectTree item = new AssetContextObjectTree();
			item.setItem(new AssetContextObject(AssetContextType.WAREHOUSE, wh.getCode(),wh.getDescription()));
			_warehouses.add(item);
			for (AssetContextObjectTree p : _plants) {

			}
		}
		List<OI40DBMachine> machines = machineApi.findAllListOI40DBMachine();
		List<AssetContextObjectTree> _machines = new ArrayList<AssetContextObjectTree>();
		for (OI40DBMachine m : machines) {
			AssetContextObjectTree item = new AssetContextObjectTree();
			item.setItem(new AssetContextObject(AssetContextType.MACHINE, m.getCode(),m.getDescription()));
			_machines.add(item);
			for (AssetContextObjectTree wc : _workcenters) {
				if (m.getWorkCenterCode().equals(wc.getItem().getObjectCode())) {
					wc.getChilds().add(item);
				}
			}
		}
		return rootCompanies;
	}

}
