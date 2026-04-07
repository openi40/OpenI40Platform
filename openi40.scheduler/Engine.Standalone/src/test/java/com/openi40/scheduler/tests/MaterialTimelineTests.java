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
package com.openi40.scheduler.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import com.openi40.scheduler.apsdatacache.ApsDataCacheException;
import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.engine.material.IMaterialTimeLineManager;
import com.openi40.scheduler.engine.standalone.Main;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Warehouse;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IProductDao;
import com.openi40.scheduler.model.dao.IStockSupplyDao;
import com.openi40.scheduler.model.dao.IWarehouseDao;
import com.openi40.scheduler.model.material.ISupply;
import com.openi40.scheduler.model.material.ISupplyConsumer;
import com.openi40.scheduler.model.material.Product;
import com.openi40.scheduler.model.material.PurchaseSupply;
import com.openi40.scheduler.model.material.StockSupply;
import com.openi40.scheduler.model.material.timeline.InventoryTimeNode;
import com.openi40.scheduler.model.material.timeline.InventoryTimeSegment;
import com.openi40.scheduler.model.material.timeline.TimeLineException;
import com.openi40.scheduler.model.material.timeline.WarehouseProductMaterialTimeLine;

@SpringBootTest(classes = { Main.class })
@ComponentScan("com.openi40.scheduler")
public class MaterialTimelineTests {
	@Autowired
	IContextualBusinessLogicFactory ComponentFactory;
	@Autowired
	TestsApsDataSourceUncachedFactory uncachedAccessor;
	@Autowired
	IProductDao productDao;
	@Autowired
	IStockSupplyDao stockSupplyDao;
	@Autowired
	IWarehouseDao warehouseDao;

	public MaterialTimelineTests() {

	}

	@Test
	public void checkMaterialTimeLine() throws DataModelDaoException, TimeLineException, ApsDataCacheException {
		double initialInventoryQty = 100.0;
		String productCode = "PRD0001";
		String dataSourceName = "ARBALETE-DEMO";
		String dataSetName = "ARBALETE-COMPANY";
		String dataSetVariant = "DEFAULT";
		ApsData apsData = uncachedAccessor.loadData(dataSourceName, dataSetName, dataSetVariant);
		Warehouse warehouse = warehouseDao.findAll(apsData).get(0);
		Product product = new Product(apsData);
		product.setCode(productCode);
		product.setDescription("");
		product.setCanBeProducedByScheduler(true);
		product.setCanBePurchasedByScheduler(true);
		productDao.insert(product, apsData);
		StockSupply stockSupply = new StockSupply(apsData);
		stockSupply.setCode(warehouse.getCode() + "-" + productCode);
		stockSupply.setWarehouseCode(warehouse.getCode());
		stockSupply.setSuppliedItem(product);
		stockSupply.setQtyTotal(initialInventoryQty);
		stockSupplyDao.insert(stockSupply, apsData);
		IMaterialTimeLineManager materialTimeLineManager = this.ComponentFactory.create(IMaterialTimeLineManager.class,
				apsData, apsData);
		WarehouseProductMaterialTimeLine materialTimeLine = materialTimeLineManager.getMaterialTimeLine(warehouse,
				product, apsData);
		InventoryTimeNode actualNode = materialTimeLine.getInitialInventoryNode();
		while (actualNode != null) {
			if (actualNode.getStartingSegment() != null) {
				assertTrue(actualNode.getStartingSegment().getInventoryQty() == initialInventoryQty,
						"check inventory must be " + initialInventoryQty + " start segment at "
								+ actualNode.getEventsTime() + " but is "
								+ actualNode.getStartingSegment().getInventoryQty());
			}
			assertTrue(actualNode.getInventoryQty() == initialInventoryQty,
					"check inventory must be " + initialInventoryQty + " at " + actualNode.getEventsTime() + " but is "
							+ actualNode.getInventoryQty());

			actualNode = actualNode.getStartingSegment() != null ? actualNode.getStartingSegment().getEndNode() : null;
		}
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(apsData.getSchedulingWindow().getStartDateTime());
		gc.add(GregorianCalendar.DAY_OF_YEAR, +10);
		Date deliveryDate = gc.getTime();
		List<ISupplyConsumer> consumers = new ArrayList<>();
		List<ISupply> supplies = new ArrayList<>();
		PurchaseSupply purchaseSupply = new PurchaseSupply(apsData);
		purchaseSupply.setWarehouseCode(warehouse.getCode());
		purchaseSupply.setSuppliedItem(product);
		purchaseSupply.setQtyTotal(10);
		purchaseSupply.setOrderCode("PORD001");
		purchaseSupply.setAvailabilityDateTime(deliveryDate);
		supplies.add(purchaseSupply);
		materialTimeLineManager.add(apsData, consumers, supplies);
		double reservations = materialTimeLine.getReservationBalance(
				materialTimeLine.getInitialInventoryNode().getStartingSegment().getStartDateTime(), 11, 0, 0);
		assertTrue(

				reservations == purchaseSupply.getQtyTotal(),
				"After 11 days of beginning date time of time line the reservation balance have to be "
						+ purchaseSupply.getQtyTotal() + " but is " + reservations);
		materialTimeLine = materialTimeLineManager.getMaterialTimeLine(warehouse, product, apsData);
		InventoryTimeSegment inventorySegment = materialTimeLine.getInventoryAt(deliveryDate);
		actualNode = inventorySegment.getStartNode();
		assertTrue(actualNode.getInventoryQty() == initialInventoryQty, "check inventory must be " + initialInventoryQty
				+ " at " + actualNode.getEventsTime() + " but is " + actualNode.getInventoryQty());
		double matchingInventory = initialInventoryQty + purchaseSupply.getQtyTotal();
		while (actualNode != null) {
			if (actualNode.getStartingSegment() != null) {
				assertTrue(actualNode.getStartingSegment().getInventoryQty() == matchingInventory,
						"check inventory must be " + (matchingInventory) + " start segment at "
								+ actualNode.getEventsTime() + " but is "
								+ actualNode.getStartingSegment().getInventoryQty());
			}
			actualNode = actualNode.getStartingSegment() != null ? actualNode.getStartingSegment().getEndNode() : null;
		}

	}
}
