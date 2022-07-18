package com.openi40.scheduler.model.material.timeline;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.common.utils.DateUtil;
import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.material.StockSupply;

import lombok.Getter;
import lombok.Setter;
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
public class AbstractMaterialTimeLine extends AbstractApsObject {
	TreeMap<Long, InventoryTimeNode> timeNodes = new TreeMap<>();
	Logger LOGGER = LoggerFactory.getLogger(getClass());

	public AbstractMaterialTimeLine(ApsData context, StockSupply initialStockPosition) {
		super(context);
		this.initialStockPosition = initialStockPosition;
		this.initialize();
	}

	private void initialize() {
		this.timeNodes.clear();
		long startTS = DateUtil.discretize(getContext().getSchedulingWindow().getStartDateTime()).getTime();
		long endTS = DateUtil.discretize(getContext().getSchedulingWindow().getEndDateTime()).getTime();
		InventoryTimeNode startNode = new InventoryTimeNode();
		startNode.setEventsTime(new java.sql.Date(startTS));
		InventoryTimeNode endNode = new InventoryTimeNode();
		endNode.setEventsTime(new java.sql.Date(endTS));
		InventoryTimeSegment segment = new InventoryTimeSegment();
		segment.setStartDateTime(startNode.getEventsTime());
		segment.setEndDateTime(endNode.getEventsTime());
		startNode.setStartingSegment(segment);
		endNode.setEndingSegment(segment);
		timeNodes.put(startTS, startNode);
		timeNodes.put(endTS, endNode);
		segment.setStartNode(startNode);
		segment.setEndNode(endNode);
		if (initialStockPosition != null) {
			startNode.setInventoryQty(initialStockPosition.getQtyTotal());
		}
		recalculateForward(startNode);
	}

	public void debugLogging() {
		DateFormat df = SimpleDateFormat.getDateTimeInstance();
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin warehouse timeline log");
			Collection<InventoryTimeNode> timeSegments = timeNodes.values();
			for (InventoryTimeNode ts : timeSegments) {
				LOGGER.debug("at:" + df.format(ts.getEventsTime()) + " inventory: " + ts.getInventoryQty()
						+ " reservationBalance:" + ts.getReservationBalance());
				LOGGER.debug("Movements:");
				for (AbstractMaterialMovement<?> m : ts.getMovements()) {
					LOGGER.debug("qty:" + m.getMovementQty());
				}
				if (ts.getStartingSegment() != null) {
					LOGGER.debug("Segment from:" + df.format(ts.getStartingSegment().getStartDateTime()) + " to: "
							+ df.format(ts.getStartingSegment().getEndDateTime()) + " inventory:"
							+ ts.getStartingSegment().getInventoryQty() + " reservation:"
							+ ts.getStartingSegment().getReservationsBalance());
				}
			}
			LOGGER.debug("End warehouse timeline log");
		}
	}

	@Override
	public void resetSchedulingData() {
		this.initialize();
	}

	@Getter
	@Setter
	protected StockSupply initialStockPosition = null;

	public boolean canDoMovement(AbstractMaterialMovement<?> movement) {
		InventoryTimeSegment inventory = getInventoryAt(movement.getMovementDate());
//		return inventory != null
//				&& (inventory.getInventoryQty() + movement.getMovementQty() + inventory.getReservationsBalance()) >= 0;
		return true;
	}

	public void addMovement(List<AbstractMaterialMovement<?>> movement) throws TimeLineException {
		for (AbstractMaterialMovement<?> abstractMaterialMovement : movement) {
			addMovement(abstractMaterialMovement);
		}
	}

	public void addMovement(AbstractMaterialMovement<?> movement) throws TimeLineException {
		if (!canDoMovement(movement))
			throw new TimeLineException("Requested movement excedes phisical quantity " + movement.toString());
		InventoryTimeSegment inventory = getInventoryAt(movement.getMovementDate());
		if (inventory != null) {
			long movementTS = DateUtil.discretize(movement.getMovementDate()).getTime();
			long startTS = DateUtil.discretize(inventory.getStartDateTime()).getTime();
			long endTS = DateUtil.discretize(inventory.getEndDateTime()).getTime();
			if (movementTS == startTS) {
				inventory.getStartNode().getMovements().add(movement);
				recalculateForward(inventory.getStartNode());
			} else if (movementTS == endTS) {
				inventory.getEndNode().getMovements().add(movement);
				recalculateForward(inventory.getEndNode());
			} else if (startTS < movementTS && movementTS < endTS) {
				// new intermediate node with movement date as end and old start date as start
				// truncate actual inventory segment to movement date and setting new node with
				// this
				// movement as new node
				InventoryTimeNode intermediateNode = new InventoryTimeNode();
				intermediateNode.setEventsTime(DateUtil.discretize(movement.getMovementDate()));
				intermediateNode.setEndingSegment(inventory);
				intermediateNode.getMovements().add(movement);
				InventoryTimeNode endingNode = inventory.getEndNode();
				inventory.setEndDateTime(intermediateNode.getEventsTime());
				inventory.setEndNode(intermediateNode);
				InventoryTimeSegment newSegment = new InventoryTimeSegment();
				newSegment.setStartDateTime(intermediateNode.getEventsTime());
				newSegment.setEndDateTime(endingNode.getEventsTime());
				newSegment.setStartNode(intermediateNode);
				newSegment.setEndNode(endingNode);
				intermediateNode.setStartingSegment(newSegment);
				this.timeNodes.put(intermediateNode.getEventsTime().getTime(), intermediateNode);
				recalculateForward(intermediateNode);
			}
		}
	}

	public void removeMovement(AbstractMaterialMovement<?> movement) throws TimeLineException {
		long ts = DateUtil.dateTimeLongDiscreteRappresentation(movement.getMovementDate());
		if (!this.timeNodes.containsKey(ts))
			throw new TimeLineException(
					"Requested movement time is not coherent with actual events timetable " + movement.toString());
		InventoryTimeNode actualNode = this.timeNodes.get(ts);
		actualNode.getMovements().remove(movement);
		this.recalculateForward(actualNode);
	}

	protected void recalculateForward(InventoryTimeNode node) {
		// Going forward for phisical disponible qty recalculation
		InventoryTimeNode actualNode = node;
		while (actualNode != null) {

			double actual = 0;
			if (actualNode.getEndingSegment() != null) {
				actual = actualNode.getEndingSegment().getInventoryQty();
				actualNode.setInventoryQty(actual);
			} else {
				actual = actualNode.getInventoryQty();
			}

			for (AbstractMaterialMovement<?> m : actualNode.getMovements()) {
				actual += m.getMovementQty();
			}
			InventoryTimeNode nextNode = null;
			if (actualNode.getStartingSegment() != null) {
				actualNode.getStartingSegment().setInventoryQty(actual);
				nextNode = actualNode.getStartingSegment().getEndNode();
			}
			actualNode = nextNode;
		}
		double initialBalance = 0;
		// going backward for reservations recalculation taking reservation from next
		// time segment and recalculating backward
		InventoryTimeNode lastNode = node;
		if (lastNode != null && lastNode.getStartingSegment() != null) {
			initialBalance = lastNode.getStartingSegment().getReservationsBalance();
			lastNode.setReservationBalance(initialBalance);
		}
		while (lastNode != null) {
			for (AbstractMaterialMovement<?> m : lastNode.getMovements()) {
				initialBalance += m.getMovementQty();
			}
			if (lastNode.getEndingSegment() != null) {
				lastNode.getEndingSegment().setReservationsBalance(initialBalance);
				lastNode = lastNode.getEndingSegment().getStartNode();
			} else
				lastNode = null;

		}
	}

	public double getReservationBalance(Date startDate, int days, int hour, int minutes) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(startDate);
		Date actualDate = calendar.getTime();
		calendar.add(GregorianCalendar.DAY_OF_YEAR, days);
		calendar.add(GregorianCalendar.HOUR, hour);
		calendar.add(GregorianCalendar.MINUTE, minutes);
		Date endDate = DateUtil.discretize(calendar.getTime());
		double balance = 0.0;
		InventoryTimeSegment inventorySegment = getInventoryAt(startDate);
		if (actualDate.equals(inventorySegment.getStartDateTime())) {
			InventoryTimeNode startNode = inventorySegment.getStartNode();
			for (AbstractMaterialMovement<?> mov : startNode.getMovements()) {
				balance += mov.getMovementQty();
			}
		}
		while (inventorySegment != null && (actualDate.before(endDate) || actualDate.equals(endDate))) {
			InventoryTimeSegment nextSegment = null;
			if (inventorySegment.getEndDateTime().before(endDate)
					|| inventorySegment.getEndDateTime().equals(endDate)) {
				InventoryTimeNode endNode = inventorySegment.getEndNode();
				for (AbstractMaterialMovement<?> mov : endNode.getMovements()) {
					balance += mov.getMovementQty();
				}
				nextSegment = endNode.getStartingSegment();
			}
			actualDate = inventorySegment.getEndDateTime();
			inventorySegment = nextSegment;
		}
		return balance;
	}

	public InventoryTimeNode getInitialInventoryNode() {
		return this.timeNodes.firstEntry().getValue();
	}

	public InventoryTimeSegment getInventoryAt(Date date) {
		long _ts = DateUtil.discretize(date).getTime();
		Entry<Long, InventoryTimeNode> entry = this.timeNodes.floorEntry(_ts);
		return entry != null ? entry.getValue().getStartingSegment() : null;
	}

}
