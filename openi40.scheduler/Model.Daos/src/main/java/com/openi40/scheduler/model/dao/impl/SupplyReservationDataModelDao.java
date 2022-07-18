package com.openi40.scheduler.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.companystructure.Warehouse;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IPeggingDao;
import com.openi40.scheduler.model.dao.IPlantDao;
import com.openi40.scheduler.model.dao.IProductiveCompanyDao;
import com.openi40.scheduler.model.dao.ISupplyReservationDao;
import com.openi40.scheduler.model.material.StockSupply;
import com.openi40.scheduler.model.material.SupplyReservation;
import com.openi40.scheduler.model.orders.Pegging;
import com.openi40.scheduler.model.orders.PurchaseOrder;
import com.openi40.scheduler.model.orders.PurchaseOrderLine;
import com.openi40.scheduler.model.orders.WorkOrder;
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
public class SupplyReservationDataModelDao extends AbstractApsDataModelDao<SupplyReservation>
		implements ISupplyReservationDao {

	public SupplyReservationDataModelDao() {
		super(SupplyReservation.class, new DataPathCfg(SupplyReservation.class).withPath(ApsData.class,
				"ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/Warehouses/Warehouse/StockSupplies/StockSupply/Reservations")
				.withPath(ProductiveCompany.class,
						"ProductiveCompany/Plants/Plant/Warehouses/Warehouse/StockSupplies/StockSupply/Reservations")
				.withPath(Plant.class, "Plant/Warehouses/Warehouse/StockSupplies/StockSupply/Reservations")
				.withPath(Warehouse.class, "Warehouse/StockSupplies/StockSupply/Reservations")
				.withPath(ApsData.class,
						"ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/WorkOrders/WorkOrder/ProductionSupply/ProductionSupply/Reservations")
				.withPath(ProductiveCompany.class,
						"ProductiveCompany/Plants/Plant/WorkOrders/WorkOrder/ProductionSupply/ProductionSupply/Reservations")
				.withPath(Plant.class, "Plant/WorkOrders/WorkOrder/ProductionSupply/ProductionSupply/Reservations")
				.withPath(WorkOrder.class, "WorkOrder/ProductionSupply/ProductionSupply/Reservations")
				.withPath(ApsData.class,
						"ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/PurchaseOrders/PurchaseOrder/OrderLines/PurchaseOrderLine/Supply/PurchaseSupply/Reservations")
				.withPath(ProductiveCompany.class,
						"ProductiveCompany/Plants/Plant/PurchaseOrders/PurchaseOrder/OrderLines/PurchaseOrderLine/Supply/PurchaseSupply/Reservations")
				.withPath(Plant.class,
						"Plant/PurchaseOrders/PurchaseOrder/OrderLines/PurchaseOrderLine/Supply/PurchaseSupply/Reservations")
				.withPath(PurchaseOrder.class,
						"PurchaseOrder/OrderLines/PurchaseOrderLine/Supply/PurchaseSupply/Reservations")
				.withPath(PurchaseOrderLine.class, "PurchaseOrderLine/Supply/PurchaseSupply/Reservations"));

	}

	@Override
	public void insert(SupplyReservation entry, ApsData context) throws DataModelDaoException {

	}

	@Override
	public void delete(SupplyReservation entry, ApsData context) throws DataModelDaoException {

	}
}