package com.openi40.scheduler.engine.timesheet;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.common.utils.DateUtil;
import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.engine.timesheet.TimeSegmentEvaluationResult.TimeSegmentEvaluationResultType;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.aps.ApsWindow;
import com.openi40.scheduler.model.time.EndDateTimeAlignment;
import com.openi40.scheduler.model.time.StartDateTimeAlignment;
import com.openi40.scheduler.model.time.TimeSegment;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;
import com.openi40.scheduler.model.time.TimeSegmentType;
import com.openi40.scheduler.model.time.Timesheet;
import com.openi40.scheduler.model.time.TimesheetAvailableTimeRange;
import com.openi40.scheduler.model.time.TimesheetReservation;
import com.openi40.scheduler.model.time.WorkingTimeSegment;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author architectures@openi40.org
 *
 */
@DefaultImplementation(implemented = ITimesheetLogic.class, entityClass = ITimesheetAllocableObject.class)
public class TimesheetLogicImpl extends BusinessLogic<ITimesheetAllocableObject> implements ITimesheetLogic {
	static Logger LOGGER = LoggerFactory.getLogger(TimesheetLogicImpl.class);
	static final boolean doLogging = false;

	@Override
	public Timesheet createCleanCalendar(ITimesheetAllocableObject ewcalendar) {

		LOGGER.debug("Start create clean calendar");
		IAvailableTimeRangeGenerator rangeGenerator = componentsFactory.create(IAvailableTimeRangeGenerator.class,
				ewcalendar, ewcalendar.getContext());
		ApsWindow schedulingWindow = null;
		if (ewcalendar instanceof ApsData) {
			ApsData apd = (ApsData) ewcalendar;
			schedulingWindow = apd.getSchedulingWindow();
		} else {
			schedulingWindow = ewcalendar.getContext().getSchedulingWindow();
		}
		List<TimesheetAvailableTimeRange> ranges = rangeGenerator.generateAvailableTimeRanges(ewcalendar,
				schedulingWindow);
		AdvancedTimesheet calendar = new AdvancedTimesheet(ewcalendar.getContext(), ranges);
		for (TimesheetAvailableTimeRange timesheetAvailableTimeRange : ranges) {
			calendar.getMainRange().Add(timesheetAvailableTimeRange);
		}
		LOGGER.debug("End create clean calendar");
		return calendar;
	}

	protected AdvancedTimesheet getCalendar(ITimesheetAllocableObject object) {
		AdvancedTimesheet c = null;
		LOGGER.debug("Begin getCalendar(..)");
		if (object.getTimesheet() == null) {
			object.setTimesheet(c = (AdvancedTimesheet) createCleanCalendar(object));
		}
		if (object.getTimesheet() instanceof AdvancedTimesheet) {
			c = (AdvancedTimesheet) object.getTimesheet();
		} else
			throw new IllegalStateException("Calendar of type:" + object.getTimesheet().getClass().getName());
		LOGGER.debug("End getCalendar(..)");
		return c;
	}

	@Override
	public List<TimesheetReservation> getReservations(ITimesheetAllocableObject ewcalendar) {

		return getCalendar(ewcalendar).getReservations(ewcalendar);
	}

	@Override
	public List<TimesheetReservation> getReservations(ITimesheetAllocableObject ewcalendar,
			TimeSegmentRequirement range) {

		return getCalendar(ewcalendar).getReservations(ewcalendar, range);
	}

	@Override
	public TimesheetReservation addReservation(ITimesheetAllocableObject ewcalendar,
			TimeSegmentRequirement rangeRequirement, IApsObject reservedFrom) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Begin addReservations(" + ewcalendar.getCode() + "," + rangeRequirement + ",...)");
		TimesheetReservation planned = planReservation(ewcalendar, rangeRequirement, reservedFrom);
		if (planned != null)
			addReservation(ewcalendar, planned);
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("End addReservations(" + ewcalendar.getCode() + "," + rangeRequirement + ",...) returning=>"
					+ planned);
		return planned;
	}

	@Override
	public TimesheetReservation planReservation(ITimesheetAllocableObject ewcalendar,
			TimeSegmentRequirement rangeRequirement, IApsObject reservedFrom) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Begin planReservation(" + ewcalendar.getCode() + "," + rangeRequirement + ",...)");
		if (!rangeRequirement.isValidState()) {
			String msg = "Passed invalid rangeRequirement=" + rangeRequirement + " in planReservation on calendar=>"
					+ ewcalendar.getCode() + " for reservedFrom=>" + reservedFrom.getCode();
			LOGGER.error(msg);
			throw new OpenI40Exception(msg);
		}
		AdvancedTimesheet calendar = getCalendar(ewcalendar);
		boolean periodIsOk = rangeRequirement.isLowUpLimited()
				|| (rangeRequirement.isLowerLimited() && rangeRequirement.getAvailabilityDuration() > 0)
				|| (rangeRequirement.isUpperLimited() && rangeRequirement.getAvailabilityDuration() > 0);
		if (!periodIsOk) {
			throw new OpenI40Exception("The passed range " + rangeRequirement
					+ " must be upper and lower limited or must have AvailabilityDuration>0 and being upper or lower limited");
		}
		ApsLogicDirection direction = null;
		if (rangeRequirement.getStartAlignment() != null) {
			switch (rangeRequirement.getStartAlignment()) {
			case START_ON_START_PRECISELY:
			case START_AFTER_START_ASAP: {
				direction = ApsLogicDirection.FORWARD;
				if (!rangeRequirement.isLowerLimited())
					throw new OpenI40Exception(
							"Range requirement without start date cannot be ALIGN_START_PRECISELY or ALIGN_START_ASAP");
			}
				break;
			}
		} else if (rangeRequirement.getEndAlignment() != null) {
			switch (rangeRequirement.getEndAlignment()) {
			case END_ON_END_PRECISELY:
			case END_BEFORE_END_ASAP: {
				direction = ApsLogicDirection.BACKWARD;
				if (!rangeRequirement.isUpperLimited())
					throw new OpenI40Exception(
							"Range requirement without end date cannot be ALIGN_END_PRECISELY or ALIGN_END_ASAP");
			}
				break;
			}
		} else {
			String _msg = "NO DIRECTION SPECIFIED BY " + rangeRequirement;
			LOGGER.error(_msg);
			throw new OpenI40Exception(_msg);
		}
		TimesheetReservation reservation = null;
		// FIRST SELECT FIRST LEVEL SLOTS WITH THE CALENDAR GENERATION CRITERIA
		Date discreteStart = null;
		Date discreteEnd = null;
		ATSlot startSlot = null;
		ATSlot endSlot = null;

		if (rangeRequirement.isLowerLimited()) {
			Date startSearchSlot = null;
			startSearchSlot = DateUtil.discretize(rangeRequirement.getStartDateTime());
			discreteStart = startSearchSlot;
			startSlot = calendar.getContainingOrNearestSlot(startSearchSlot, direction);

		}
		if (rangeRequirement.isUpperLimited()) {
			Date endSearchSlot = null;
			endSearchSlot = DateUtil.discretize(rangeRequirement.getEndDateTime());
			discreteEnd = endSearchSlot;
			endSlot = calendar.getContainingOrNearestSlot(endSearchSlot, direction);
		}
		TimeSegmentRequirement discreteRequirement = new TimeSegmentRequirement(rangeRequirement);
		discreteRequirement.setStartDateTime(discreteStart);
		discreteRequirement.setEndDateTime(discreteEnd);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("req=>" + discreteRequirement + " startSlot=>" + startSlot + " endSlot=>" + endSlot);
		}
		reservation = selectContiguousFreeTime(ewcalendar, startSlot, endSlot, discreteRequirement);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End planReservation(" + ewcalendar.getCode() + "," + rangeRequirement + ",...)");
			LOGGER.debug("Returned=>" + reservation);
		}
		return reservation;
	}

	protected double countTimeUsingEfficiency(List<ATSlotIndex> indexes) {
		double globalTime = 0;
		for (ATSlotIndex si : indexes) {
			globalTime += si.getWorkTimeUsingEfficiency();
		}
		return globalTime;
	}

	protected int countTime(List<ATSlotIndex> indexes) {
		int globalTime = 0;
		for (ATSlotIndex si : indexes) {
			globalTime += si.getWorkTime();
		}
		return globalTime;
	}

	protected ATSlot nextSlot(ATSlot currentSlot, ApsLogicDirection direction) {
		switch (direction) {
		case FORWARD:
			return currentSlot.afterSlot;

		case BACKWARD:
			return currentSlot.beforeSlot;
		default:
			throw new IllegalStateException("Unknown direction:" + direction);
		}
	}

	protected int findSegmentIndexContainingOffset(ATSlot slot, ApsLogicDirection direction, int offset) {
		for (int i = 0; i < slot.freeSegments.size(); i++) {
			ATFreeSegment segment = slot.freeSegments.get(i);
			if (segment.startIndex <= offset && segment.endIndex >= offset)
				return i;
		}
		return -1;
	}

	protected int findSegmentIndexMoving(ATSlot slot, ApsLogicDirection direction, int offset) {
		return findSegmentIndexMoving(slot, direction, offset, -1);
	}

	protected int findSegmentIndexMoving(ATSlot slot, ApsLogicDirection direction, int offset, int startingFrom) {

		switch (direction) {
		case FORWARD: {
			if (startingFrom < 0)
				startingFrom = 0;
			for (int i = startingFrom; i < slot.freeSegments.size(); i++) {
				ATFreeSegment segment = slot.freeSegments.get(i);
				if (segment.startIndex >= offset)
					return i;
			}
		}
			break;

		case BACKWARD: {
			if (startingFrom < 0)
				startingFrom = slot.freeSegments.size() - 1;
			for (int i = startingFrom; i >= 0; i--) {
				ATFreeSegment segment = slot.freeSegments.get(i);
				if (segment.endIndex <= offset)
					return i;
			}
		}
			break;
		default:
			throw new IllegalStateException("Unknown direction:" + direction);
		}
		return -1;
	}

	protected int offsetOnMarginOfSegment(ATFreeSegment segment, ApsLogicDirection direction) {
		switch (direction) {
		case FORWARD:
			return segment.startIndex;
		case BACKWARD:
			return segment.endIndex;
		default:
			throw new IllegalStateException("Unknown direction:" + direction);
		}
	}

	/************************************************************************
	 * Assign margins of slotindex according to starting offset of using resource in
	 * the passed segment coherently with direction
	 * 
	 * @param slotIndex
	 * @param offset
	 * @param requiredDuration
	 * @param segment
	 * @param direction
	 * @return
	 */
	protected boolean assignSlotMarginAndSegmentUsingOffset(ATSlotIndex slotIndex, int offset, int requiredDuration,
			ATFreeSegment segment, ApsLogicDirection direction) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin assignSlotMarginAndSegmentUsingOffset(slotIndex={}," + offset + "," + requiredDuration
					+ "," + segment + "," + direction + ")");
		}
		boolean thisSegmentIsEnough = false;
		slotIndex.usedSegment = segment;
		slotIndex.startIndex = segment.startIndex;
		slotIndex.endIndex = segment.endIndex;
		switch (direction) {
		case FORWARD: {
			assert offset >= slotIndex.startIndex && offset <= slotIndex.endIndex;
			if (offset > slotIndex.startIndex) {
				slotIndex.startIndex = offset;
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Moving slotIndex.startIndex on offset value=>" + offset);
				}
			}

		}
			break;
		case BACKWARD: {
			assert offset >= slotIndex.startIndex && offset <= slotIndex.endIndex;
			if (offset < slotIndex.endIndex) {
				slotIndex.endIndex = offset;
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Moving slotIndex.endIndex on offset value=>" + offset);
				}
			}

		}
			break;
		default:
			throw new IllegalStateException("Unknown direction:" + direction);
		}
		double availableWorkTime = slotIndex.getWorkTimeUsingEfficiency();
		thisSegmentIsEnough = availableWorkTime >= requiredDuration;
		switch (direction) {
		case FORWARD: {
			if (thisSegmentIsEnough) {
				slotIndex.endIndex = slotIndex.startIndex + requiredDuration;
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("thisSegmentIsEnough=" + thisSegmentIsEnough
							+ " moving slotIndex.endIndex on (start index+ work time)=>" + slotIndex);
				}
			}
		}
			break;
		case BACKWARD: {
			if (thisSegmentIsEnough) {
				slotIndex.startIndex = slotIndex.endIndex - requiredDuration;
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("thisSegmentIsEnough=" + thisSegmentIsEnough
							+ " moving slotIndex.startIndex on (end index - work time)=>" + slotIndex);
				}
			}
		}
			break;
		default:
			throw new IllegalStateException("Unknown direction:" + direction);
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End assignSlotMarginAndSegmentUsingOffset(slotIndex=" + slotIndex + "," + offset + ","
					+ requiredDuration + "," + segment + "," + direction + ")");
			LOGGER.debug("Returning continue searching=>" + (!thisSegmentIsEnough));
		}
		return !thisSegmentIsEnough;
	}

	protected boolean isAllocatedOnSlotInitialMargin(ATSlotIndex slotIndex, ApsLogicDirection direction) {
		switch (direction) {
		case FORWARD: {
			Date date = slotIndex.getStartDate();
			return date != null && date.equals(slotIndex.slot.getStartDateTime());
		}

		case BACKWARD: {
			Date date = slotIndex.getEndDate();
			return date != null && date.equals(slotIndex.slot.getEndDateTime());
		}

		default:
			throw new IllegalStateException("Unknown direction:" + direction);
		}
	}

	/**********************************************************************************************************************************
	 * Check if the margin of this slotindex is ON the GUARD margin of the slot
	 * containing it. In other terms on the opposite margin than the direction. If
	 * direction==FORWARD is the slot endDateTime, if direction == BACKWARD is the
	 * slot startDateTime
	 * 
	 * @param slotIndex
	 * @param direction
	 * @return
	 */
	protected boolean isAllocatedOnSlotGuardMargin(ATSlotIndex slotIndex, ApsLogicDirection direction) {
		switch (direction) {
		case FORWARD: {
			Date date = slotIndex.getEndDate();
			return date != null && date.equals(slotIndex.slot.getEndDateTime());
		}

		case BACKWARD: {
			Date date = slotIndex.getStartDate();
			return date != null && date.equals(slotIndex.slot.getStartDateTime());
		}

		default:
			throw new IllegalStateException("Unknown direction:" + direction);
		}
	}

	protected boolean isReachedSearchAreaEnd(ATSlot startSlot, ATSlot endSlot, ATSlot currentSlot,
			ApsLogicDirection direction) {
		switch (direction) {
		case FORWARD: {
			return currentSlot == endSlot || currentSlot.afterSlot == null;
		}

		case BACKWARD: {
			return currentSlot == startSlot || currentSlot.beforeSlot == null;
		}

		default:
			throw new IllegalStateException("Unknown direction:" + direction);
		}
	}

	protected boolean isAllocatedEnoughTime(List<ATSlotIndex> slots, int requiredDuration) {
		return countTimeUsingEfficiency(slots) >= requiredDuration;
	}

	static final int OFFSET_ON_BORDER = -1;

	class StopForcing {
		boolean stopSearching = false;
	}

	protected boolean isClosedSegmentRequirement(TimeSegmentRequirement req) {
		boolean closedSegment = req.isLowUpLimited() && req.getAvailabilityDuration() <= 0;
		return closedSegment;
	}

	protected boolean checkClosedSegmentRequirementFitsSlots(TimeSegmentRequirement req, ATSlot startSlot, ATSlot endSlot) {
		boolean closedSegment = req.isLowUpLimited() && req.getAvailabilityDuration() <= 0;
		boolean fits = false;
		if (closedSegment) {
			if (startSlot == null || endSlot == null) {
				fits = false;
			} else {
				Date start = startSlot.getStartDateTime();
				Date end = startSlot.getEndDateTime();
				fits = (start.before(req.getStartDateTime()) || start.equals(req.getStartDateTime()))
						&& (end.after(req.getEndDateTime()) || end.equals(req.getEndDateTime()));
			}
		}
		return fits;
	}

	protected boolean isPreciseRequirement(TimeSegmentRequirement req) {
		boolean preciseRequirement = req.getAvailabilityDuration() > 0.0
				&& (req.isLowerLimited() && req.getStartAlignment() == StartDateTimeAlignment.START_ON_START_PRECISELY)
				|| (req.isUpperLimited() && req.getEndAlignment() == EndDateTimeAlignment.END_ON_END_PRECISELY);
		return preciseRequirement;
	}

	public int calculateOffset(Date referDate, Date requiredDate) {

		return Math.abs(DateUtil.calculateDiscreteStepsNr(referDate, requiredDate));

	}

	protected boolean fullyFree(ATSlot slot) {
		boolean free = slot.freeSegments.size() == 1;
		if (free) {
			ATFreeSegment segment = slot.freeSegments.get(0);
			int maxIndex = calculateOffset(slot.getStartDateTime(), slot.getEndDateTime());
			free = free && segment.startIndex == 0 && segment.endIndex == maxIndex;
		}
		return free;
	}

	protected List<ATSlotIndex> tryAcquireClosedSegmentContiguosFreeSegments(ITimesheetAllocableObject reservable,
			TimeSegmentRequirement req, ATSlot startSlot, ATSlot endSlot) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Begin tryAcquireClosedSegmentContiguosFreeSegments(" + req + "," + startSlot + "," + endSlot
					+ ",...)");
		assert isClosedSegmentRequirement(req);
		// assert (startSlot.isInRange(req.getStartDateTime()) &&
		// endSlot.isInRange(req.getEndDateTime()));
		// IN this alogrithm we go only forward
		ApsLogicDirection direction = ApsLogicDirection.FORWARD;
		List<ATSlotIndex> slots = new ArrayList<>();
		int startSlotOffset = startSlot.isInRange(req.getStartDateTime())
				? calculateOffset(req.getStartDateTime(), startSlot.getStartDateTime())
				: 0;
		int endSlotOffset = endSlot.isInRange(req.getEndDateTime())
				? calculateOffset(req.getEndDateTime(), endSlot.getStartDateTime())
				: endSlot.gridLength;
		ATFreeSegment startSegment = startSlot.getFreeSegmentWichContains(startSlotOffset);
		ATFreeSegment endSegment = endSlot.getFreeSegmentWichContains(endSlotOffset);
		if (startSlot == endSlot) {
			if (startSegment != null && endSegment != null && startSegment.startIndex == endSegment.startIndex
					&& startSegment.endIndex == endSegment.endIndex) {
				ATSlotIndex slotIndex = new ATSlotIndex();
				slotIndex.startIndex = startSlotOffset;
				slotIndex.endIndex = endSlotOffset;
				slotIndex.slot = startSlot;
				slotIndex.usedSegment = startSegment;
				slots.add(slotIndex);
				if (LOGGER.isDebugEnabled())
					LOGGER.debug("Considering single segment=>" + slotIndex);
			}
		} else if (startSegment != null && endSegment != null) {
			ATSlotIndex startSlotIndex = new ATSlotIndex();
			startSlotIndex.startIndex = startSlotOffset;
			startSlotIndex.endIndex = startSegment.endIndex;
			startSlotIndex.slot = startSlot;
			startSlotIndex.usedSegment = startSegment;
			slots.add(startSlotIndex);
			ATSlot currentSlot = startSlot;
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("Considering starting segment=>" + startSlotIndex);
			boolean allocatedIntermediateSlots = isAllocatedOnSlotGuardMargin(startSlotIndex, direction);
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("isAllocatedOnSlotGuardMargin(..) returns " + allocatedIntermediateSlots);
			while (allocatedIntermediateSlots && currentSlot.afterSlot != null && currentSlot.afterSlot != endSlot) {
				currentSlot = currentSlot.afterSlot;
				allocatedIntermediateSlots = fullyFree(currentSlot);
				if (LOGGER.isDebugEnabled())
					LOGGER.debug("fullyFree returns " + allocatedIntermediateSlots);
				if (allocatedIntermediateSlots) {
					ATSlotIndex actualSlot = new ATSlotIndex();
					actualSlot.slot = currentSlot;
					actualSlot.usedSegment = currentSlot.freeSegments.get(0);
					actualSlot.startIndex = actualSlot.usedSegment.startIndex;
					actualSlot.endIndex = actualSlot.usedSegment.endIndex;
					if (LOGGER.isDebugEnabled())
						LOGGER.debug("Considering intermediate segment=>" + actualSlot);
					slots.add(actualSlot);
					boolean allocatedOnInitial = isAllocatedOnSlotInitialMargin(actualSlot, direction);
					if (LOGGER.isDebugEnabled())
						LOGGER.debug("isAllocatedOnSlotInitialMargin(..) returns " + allocatedOnInitial);
					if (currentSlot.afterSlot != endSlot) {
						allocatedIntermediateSlots = isAllocatedOnSlotGuardMargin(actualSlot, direction);
						if (LOGGER.isDebugEnabled())
							LOGGER.debug("isAllocatedOnSlotGuardMargin(..) returns " + allocatedIntermediateSlots);
					}
				}
			}
			if (allocatedIntermediateSlots && currentSlot.afterSlot == endSlot) {
				ATSlotIndex endSlotIndex = new ATSlotIndex();
				endSlotIndex.startIndex = 0;
				endSlotIndex.endIndex = endSlotOffset;
				endSlotIndex.slot = endSlot;
				endSlotIndex.usedSegment = endSlot.freeSegments.get(0);
				if (LOGGER.isDebugEnabled())
					LOGGER.debug("Considering end segment=>" + endSlotIndex);
				boolean allocatedOnInitial = isAllocatedOnSlotInitialMargin(endSlotIndex, direction);
				if (LOGGER.isDebugEnabled())
					LOGGER.debug("isAllocatedOnSlotInitialMargin(..) returns " + allocatedOnInitial);
				if (allocatedOnInitial) {
					slots.add(endSlotIndex);
				}
			} else {
				slots.clear();
			}
		}
		if (!slots.isEmpty()) {
//			assert slots.get(0).getStartDate().equals(req.getStartDateTime())
//					&& slots.get(slots.size() - 1).getEndDate().equals(req.getEndDateTime());
		}
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("End tryAcquireClosedSegmentContiguosFreeSegments(" + req + "," + startSlot + "," + endSlot
					+ ",...)=>" + slots);
		return slots;
	}

	protected ATSlot contiguousSlot(ATSlot slot, ApsLogicDirection direction) {
		switch (direction) {
		case FORWARD:
			return slot.afterSlot;
		case BACKWARD:
			return slot.beforeSlot;
		default:
			throw new IllegalStateException("Alignment direction " + direction);
		}
	}

	protected int startingBorderOffset(ATSlot slot, ApsLogicDirection direction) {
		switch (direction) {
		case FORWARD:
			return 0;
		case BACKWARD:
			return slot.gridLength;
		default:
			throw new IllegalStateException("Alignment direction " + direction);
		}
	}

	protected List<ATSlotIndex> tryAcquireContiguosFreeSegments(ITimesheetAllocableObject reservable,
			TimeSegmentRequirement req, ATSlot startSlot, ATSlot endSlot) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin tryAcquireContiguosFreeSegments(" + req + "," + startSlot + "," + endSlot + ")");
		}
		ApsLogicDirection direction = getDirection(req);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Direction is " + direction);
		}
		// pointer to the actual slot
		ATSlot actualSlot = null;
		// offset of starting allocation on the actual slot
		int actualSlotOffsetGuard = 0;
		// guard slot, it means the slot that is the maximum border of the time segment
		ATSlot guardSlot = null;
		// guard offset located in the guard slot
		int guardSlotOffsetGuard = 0;
		switch (direction) {
		case FORWARD:
			actualSlot = startSlot;
			guardSlot = endSlot;
			if (actualSlot.getEndDateTime().equals(req.getStartDateTime()) && actualSlot != guardSlot) {
				actualSlot = actualSlot.afterSlot;
			}
			actualSlotOffsetGuard = startSlot.isInRange(req.getStartDateTime())
					? calculateOffset(startSlot.getStartDateTime(), req.getStartDateTime())
					: 0;
			if (endSlot != null && req.isUpperLimited()) {
				guardSlotOffsetGuard = endSlot.isInRange(req.getEndDateTime())
						? calculateOffset(endSlot.getStartDateTime(), req.getEndDateTime())
						: endSlot.gridLength;
			}

			break;
		case BACKWARD:
			actualSlot = endSlot;
			guardSlot = startSlot;
			if (actualSlot.getStartDateTime().equals(req.getEndDateTime()) && actualSlot != guardSlot) {
				actualSlot = actualSlot.beforeSlot;
			}
			actualSlotOffsetGuard = endSlot.isInRange(req.getEndDateTime())
					? calculateOffset(endSlot.getStartDateTime(), req.getEndDateTime())
					: endSlot.gridLength;
			if (startSlot != null && req.isLowerLimited()) {
				guardSlotOffsetGuard = startSlot.isInRange(req.getStartDateTime())
						? calculateOffset(startSlot.getStartDateTime(), req.getStartDateTime())
						: 0;
			}
			break;

		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Margin slots:");
			LOGGER.debug("StartSlot=>" + startSlot);
			LOGGER.debug("EndSlot=>" + endSlot);
			LOGGER.debug("direction=>" + direction);
			LOGGER.debug("actualSlot=>" + actualSlot);
			LOGGER.debug("guardSlot=>" + guardSlot);
		}
		List<ATSlotIndex> slots = new ArrayList<>();
		int remainingDuration = (int) Math.max(Math.round(req.getAvailabilityDuration()), 1);
		int originalDuration = remainingDuration;
		boolean go = true;
		boolean cantAcquire = false;
		boolean goingContiguous = false;
		boolean firstLoop = true;
		while (go && remainingDuration >= 0 && actualSlot != null) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("go:" + go + ",cantAcquire=" + cantAcquire + ",goingContiguous=" + goingContiguous
						+ ",firstLoop=" + firstLoop + ",remainingDuration=" + remainingDuration);
			}
			List<ATFreeSegment> thisSlotSegments = new ArrayList<>();
			// if we are in contiguous mode try to go exactly on the freesegment at the
			// offset, and after we'll check
			// that it is at the "initial" border of the time slot
			thisSlotSegments.addAll(actualSlot.getSegmentsFromOffset(actualSlotOffsetGuard, direction));
			if (goingContiguous && thisSlotSegments.isEmpty()) {
				// if the segment cannot be allocated precisely so we are no more in contiguous
				// free time and
				// we have to discard allocated slot and restart searching
				goingContiguous = false;
				slots.clear();
				remainingDuration = originalDuration;
			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Current slot:" + actualSlot);
				LOGGER.debug("analyzingSegments:" + thisSlotSegments);
				LOGGER.debug("actualSlotOffsetGuard:" + actualSlotOffsetGuard);
				LOGGER.debug("go:" + go + ",cantAcquire=" + cantAcquire + ",goingContiguous=" + goingContiguous
						+ ",firstLoop=" + firstLoop + ",remainingDuration=" + remainingDuration);
			}
			if (go) {
				for (int i = 0; i < thisSlotSegments.size(); i++) {
					ATFreeSegment segment = thisSlotSegments.get(i);
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("Analyzing segment:" + segment);
					}
					if (!(actualSlotOffsetGuard >= segment.startIndex && actualSlotOffsetGuard <= segment.endIndex)) {
						if (LOGGER.isDebugEnabled()) {
							LOGGER.debug("actualSlotOffsetGuard " + actualSlotOffsetGuard + " is not in interval:"
									+ segment);
						}
						if (firstLoop) {
							switch (direction) {
							case FORWARD: {
								if (actualSlotOffsetGuard < segment.startIndex) {
									actualSlotOffsetGuard = segment.startIndex;
									if (LOGGER.isDebugEnabled()) {
										LOGGER.debug("firstLoop=" + firstLoop
												+ " set actualSlotOffsetGuard on segment.startIndex:"
												+ actualSlotOffsetGuard);
									}

								}
							}
								break;
							case BACKWARD: {
								if (actualSlotOffsetGuard > segment.endIndex) {
									actualSlotOffsetGuard = segment.endIndex;
									if (LOGGER.isDebugEnabled()) {
										LOGGER.debug("firstLoop=" + firstLoop
												+ " set actualSlotOffsetGuard on segment.endIndex:"
												+ actualSlotOffsetGuard);
									}
								}
							}
								break;
							}
						} else {
							switch (direction) {
							case FORWARD: {
								actualSlotOffsetGuard = segment.startIndex;
								if (LOGGER.isDebugEnabled()) {
									LOGGER.debug("firstLoop=" + firstLoop
											+ " set actualSlotOffsetGuard on segment.startIndex:"
											+ actualSlotOffsetGuard);
								}
							}
								break;
							case BACKWARD: {
								actualSlotOffsetGuard = segment.endIndex;
								if (LOGGER.isDebugEnabled()) {
									LOGGER.debug(
											"firstLoop=" + firstLoop + " set actualSlotOffsetGuard on segment.endIndex:"
													+ actualSlotOffsetGuard);
								}
							}
								break;
							}
						}
						goingContiguous = false;
					}
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("actualSlotOffsetGuard:" + actualSlotOffsetGuard);
						LOGGER.debug("go:" + go + ",cantAcquire=" + cantAcquire + ",goingContiguous=" + goingContiguous
								+ ",firstLoop=" + firstLoop + ",remainingDuration=" + remainingDuration);
					}
					if (!goingContiguous) {
						if (LOGGER.isDebugEnabled()) {
							LOGGER.debug("goingContiguous false so reset search");
						}
						slots.clear();
						remainingDuration = originalDuration;
					}
					ATSlotIndex slotIndex = new ATSlotIndex();
					slotIndex.slot = actualSlot;
					go = assignSlotMarginAndSegmentUsingOffset(slotIndex, actualSlotOffsetGuard,
							(int) remainingDuration, segment, direction);
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("go:" + go + ",cantAcquire=" + cantAcquire + ",goingContiguous=" + goingContiguous
								+ ",firstLoop=" + firstLoop + ",remainingDuration=" + remainingDuration);
					}
					if (goingContiguous) {
						// if we are in contiguos time allocation than check this time slot is in the
						// initial margin of the
						// time slot, if it is not than we have to restart searching from this
						// freesegment
						goingContiguous = isAllocatedOnSlotInitialMargin(slotIndex, direction);
						if (LOGGER.isDebugEnabled()) {
							LOGGER.debug("going contiguous true so check that slot allocated on initial margin=>"
									+ goingContiguous);
						}
						if (!goingContiguous) {
							slots.clear();
							remainingDuration = originalDuration;
							LOGGER.debug("Restart searching");
						}
						if (LOGGER.isDebugEnabled()) {
							LOGGER.debug(
									"go:" + go + ",cantAcquire=" + cantAcquire + ",goingContiguous=" + goingContiguous
											+ ",firstLoop=" + firstLoop + ",remainingDuration=" + remainingDuration);
						}
					}
					// if here go is false than enough time allocated otherwise true
					int workTime = slotIndex.endIndex - slotIndex.startIndex;
					remainingDuration -= workTime;
					// for the two direction must have at the end slotIndexes in the same order so
					// in the following forward add in queue at the vector
					// but in backward treat it like a stack
					if (workTime > 0) {
						switch (direction) {
						case FORWARD: {
							slots.add(slotIndex);
						}
							break;
						case BACKWARD: {
							slots.add(0, slotIndex);
						}
							break;
						}
						if (LOGGER.isDebugEnabled()) {
							LOGGER.debug("slots:" + slots);
							LOGGER.debug("workTime:" + workTime);
							LOGGER.debug(
									"remainingDuration:" + remainingDuration + " originalDuration:" + originalDuration);
						}
						if (actualSlot == guardSlot) {
							if (LOGGER.isDebugEnabled()) {
								LOGGER.debug("Guard slot reached");
							}
							// CHECK THAT IF WE ARE IN THE GUARD SLOT, THE PROPERLY ALLOCATED time is
							// coherent with the guard slot guard offset boundary value
							// and that the go variable does not tell us that we have to add more free
							// segments
							switch (direction) {
							case FORWARD:
								cantAcquire = slotIndex.endIndex > guardSlotOffsetGuard || go;
								if (LOGGER.isDebugEnabled()) {
									LOGGER.debug(
											"cantAcquire = slotIndex.endIndex > guardSlotOffsetGuard || go,cantAcquire=>"
													+ cantAcquire);

								}
								break;
							case BACKWARD:
								cantAcquire = slotIndex.startIndex < guardSlotOffsetGuard || go;
								if (LOGGER.isDebugEnabled()) {
									LOGGER.debug(
											"cantAcquire = slotIndex.startIndex < guardSlotOffsetGuard || go,cantAcquire=>"
													+ cantAcquire);

								}
								break;
							}
							// Put go to false to end looping
							go = false;
						} else {
							boolean isOnSlotMargin = isAllocatedOnSlotGuardMargin(slotIndex, direction);
							if (LOGGER.isDebugEnabled()) {
								LOGGER.debug("slotIndex on slot guard (end) margine:" + isOnSlotMargin);
							}
							// GO==true than continue on starting border of next slot
							// but it makes sense only if the slotIndex is allocated to the final border of
							// the containing slot (isOnSlotMargin===true), otherwise other reservations are
							// on the slot and the
							// time is not contiguous.
							if (go && isOnSlotMargin) {
								if (LOGGER.isDebugEnabled()) {
									LOGGER.debug(
											"We have aquired a segment and need to go further on next and we are on the slot end margin so goingContiguous=true");
								}
								goingContiguous = true;
								// verification that we are in the border of bot date on slot margin and indexes
								if (i != thisSlotSegments.size() - 1) {
									throw new OpenI40Exception("We are on slot margin with slotIndex=>" + slotIndex
											+ " but current index=>" + i + " not like segments.size()=>"
											+ thisSlotSegments.size() + " segments=>" + thisSlotSegments);
								}
							} else if (go && !isOnSlotMargin) {
								// if the time acquired is not sufficient and we are not in the slot margin
								// so we discard acquired slots and restart searching
								slots.clear();
								remainingDuration = originalDuration;
								goingContiguous = false;
								if (LOGGER.isDebugEnabled()) {
									LOGGER.debug(
											"We have aquired a segment and need to go further but current slotIndex not on end slot margin so reset and restart searching");
								}
							}
						}
					} else {

						slots.clear();
						remainingDuration = originalDuration;
						goingContiguous = false;
						if (LOGGER.isDebugEnabled()) {
							LOGGER.debug("Resetting because of workTime==0");
						}
					}
				}
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("go:" + go + ",cantAcquire=" + cantAcquire + ",goingContiguous=" + goingContiguous
							+ ",firstLoop=" + firstLoop + ",remainingDuration=" + remainingDuration);
				}
				if (go) {
					actualSlot = contiguousSlot(actualSlot, direction);
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("Position actualSlot on next slot;" + actualSlot);
					}
					if (actualSlot != null) {
						actualSlotOffsetGuard = startingBorderOffset(actualSlot, direction);
						if (LOGGER.isDebugEnabled()) {
							LOGGER.debug("actualSlotOffsetGuard on starting border:" + actualSlotOffsetGuard);
						}
					} else {
						go = false;
						if (LOGGER.isDebugEnabled()) {
							LOGGER.debug("actualSlot is null, end of calendar reached");
						}
					}
				}
			}
			firstLoop = false;
		}
		LOGGER.debug("Not all time allocated=>" + (remainingDuration > 0) + "cantAcquire=>" + cantAcquire);
		if (remainingDuration > 0 || cantAcquire) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Reset slots no return");
			}
			slots.clear();
			remainingDuration = originalDuration;
		} else {
			assert countTimeUsingEfficiency(slots) >= originalDuration;
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(
					"End tryAcquireContiguosFreeSegments(" + req + "," + startSlot + "," + endSlot + ")=>" + slots);
		}
		return slots;
	}

	private ApsLogicDirection getDirection(TimeSegmentRequirement req) {
		if (req.isLowerLimited() && req.getStartAlignment() != null) {
			switch (req.getStartAlignment()) {
			case START_AFTER_START_ASAP:
			case START_ON_START_PRECISELY:
				return ApsLogicDirection.FORWARD;
			}
		}
		if (req.isUpperLimited() && req.getEndAlignment() != null)

		{
			switch (req.getEndAlignment())

			{
			case END_BEFORE_END_ASAP:
			case END_ON_END_PRECISELY:
				return ApsLogicDirection.BACKWARD;

			}
		}
		throw new IllegalStateException("Alignment unmanaged " + req.toString());
	}

	protected List<ATSlotIndex> tryAcquirePreciseContiguosFreeSegments(ITimesheetAllocableObject reservable,
			TimeSegmentRequirement req, ATSlot startSlot, ATSlot endSlot) {
		ApsLogicDirection direction = getDirection(req);
		// pointer to the actual slot
		ATSlot actualSlot = null;
		// offset of starting allocation on the actual slot
		int actualSlotOffsetGuard = 0;
		// guard slot, it means the slot that is the maximum border of the time segment
		ATSlot guardSlot = null;
		// guard offset located in the guard slot
		int guardSlotOffsetGuard = 0;
		switch (direction) {
		case FORWARD: {
			boolean verify = startSlot != null && (req.getStartDateTime().after(startSlot.getStartDateTime())
					|| req.getStartDateTime().equals(startSlot.getStartDateTime()));
			if (!verify) {
				throw new OpenI40Exception("in tryAcquirePreciseContiguosFreeSegments direction=>" + direction
						+ " startSlot" + startSlot + " not coherent whith: " + req);
			}
			actualSlot = startSlot;
			guardSlot = endSlot;
			actualSlotOffsetGuard = startSlot.isInRange(req.getStartDateTime())
					? calculateOffset(startSlot.getStartDateTime(), req.getStartDateTime())
					: 0;
			if (req.getStartDateTime().equals(actualSlot.getEndDateTime()) && actualSlot != guardSlot) {
				actualSlot = actualSlot.afterSlot;
				actualSlotOffsetGuard = 0;
			}
			if (endSlot != null && req.isUpperLimited()) {
				guardSlotOffsetGuard = endSlot.isInRange(req.getEndDateTime())
						? calculateOffset(endSlot.getStartDateTime(), req.getEndDateTime())
						: endSlot.gridLength;
			}
		}

			break;
		case BACKWARD: {

			boolean verify = endSlot != null && (req.getEndDateTime().before(endSlot.getEndDateTime())
					|| req.getEndDateTime().equals(endSlot.getEndDateTime()));
			if (!verify) {
				throw new OpenI40Exception("in tryAcquirePreciseContiguosFreeSegments  direction=>" + direction
						+ "  endSlot" + endSlot + " not coherent with: " + req);
			}
			actualSlot = endSlot;
			guardSlot = startSlot;
			actualSlotOffsetGuard = endSlot.isInRange(req.getEndDateTime())
					? calculateOffset(endSlot.getStartDateTime(), req.getEndDateTime())
					: endSlot.gridLength;
			if (req.getEndDateTime().equals(actualSlot.getStartDateTime()) && actualSlot != guardSlot) {
				actualSlot = actualSlot.beforeSlot;
				if (actualSlot != null)
					actualSlotOffsetGuard = actualSlot.gridLength;

			}
			if (startSlot != null && req.isLowerLimited()) {
				guardSlotOffsetGuard = startSlot.isInRange(req.getStartDateTime())
						? calculateOffset(startSlot.getStartDateTime(), req.getStartDateTime())
						: 0;
			}
		}
			break;

		}
		List<ATSlotIndex> slots = new ArrayList<>();
		double remainingDuration = req.getAvailabilityDuration();
		double originalDuration = remainingDuration;
		boolean go = true;
		boolean cantAcquire = false;
		boolean firstLoop = true;
		boolean initialMarginOk = false;
		while (go && remainingDuration >= 0 && actualSlot != null) {
			ATFreeSegment segment = actualSlot.getFreeSegmentWichContains(actualSlotOffsetGuard);
			boolean offSetInSegment = segment != null;
			if (!offSetInSegment) {
				go = false;
				cantAcquire = true;
				initialMarginOk = false;
			}
			if (firstLoop) {
				// if we are in initial loop the initialMarginOk if
				// the precise offset calculated from method parameters
				// are in a free segment
				initialMarginOk = offSetInSegment;
			}
			if (go) {
				ATSlotIndex slotIndex = new ATSlotIndex();
				slotIndex.slot = actualSlot;
				go = assignSlotMarginAndSegmentUsingOffset(slotIndex, actualSlotOffsetGuard, (int) remainingDuration,
						segment, direction);
				if (!firstLoop) {
					// After the first llop the starting of the allocation segment must be
					// coherent with the initial edge
					// of the referred slot
					initialMarginOk = isAllocatedOnSlotInitialMargin(slotIndex, direction);
				}
				if (initialMarginOk) {
					// if here go is false than enough time allocated otherwise true
					double workTime = slotIndex.getWorkTimeUsingEfficiency();
					remainingDuration -= workTime;
					// for the two direction must have at the end slotIndexes in the same order so
					// in the following forward add in queue at the vector
					// but in backward treat it like a stack
					switch (direction) {
					case FORWARD: {
						slots.add(slotIndex);
					}
						break;
					case BACKWARD: {
						slots.add(0, slotIndex);
					}
						break;
					}
					if (actualSlot == guardSlot) {
						// CHECK THAT IF WE ARE IN THE GUARD SLOT, THE PROPERLY ALLOCATED time is
						// coherent with the guard slot guard offset boundary value
						// and that the go variable does not tell us that we have to add more free
						// segments
						switch (direction) {
						case FORWARD:
							cantAcquire = slotIndex.endIndex > guardSlotOffsetGuard || go;
							break;
						case BACKWARD:
							cantAcquire = slotIndex.startIndex < guardSlotOffsetGuard || go;
							break;
						}
						// Put go to false to end looping
						go = false;
					} else {
						boolean isOnSlotMargin = isAllocatedOnSlotGuardMargin(slotIndex, direction);
						// GO==true than continue on starting border of next slot
						// but it makes sense only if the slotIndex is allocated to the final border of
						// the containing slot (isOnSlotMargin===true), otherwise other reservations are
						// on the slot and the
						// time is not contiguous.
						if (go && isOnSlotMargin) {
							// verification that we are in the border of bot date on slot margin and indexes

						} else if (go && !isOnSlotMargin) {
							go = false;
							cantAcquire = true;
						}
					}
				} else {
					go = false;
					cantAcquire = true;
				}
			}
			if (go) {
				actualSlot = contiguousSlot(actualSlot, direction);
				if (actualSlot != null) {
					actualSlotOffsetGuard = startingBorderOffset(actualSlot, direction);
				} else {
					go = false;
				}
			}
			firstLoop = false;
		}

		if (remainingDuration > 0 || cantAcquire) {
			slots.clear();
		} else {
			assert countTimeUsingEfficiency(slots) >= originalDuration;
		}
		return slots;
	}

	protected boolean isAsapRequirement(TimeSegmentRequirement req) {
		boolean asapRequirement = req.getAvailabilityDuration() > 0.0
				&& (req.isLowerLimited() && req.getStartAlignment() == StartDateTimeAlignment.START_AFTER_START_ASAP)
				|| (req.isUpperLimited() && req.getEndAlignment() == EndDateTimeAlignment.END_BEFORE_END_ASAP);
		return asapRequirement;

	}

	protected ATCalendarReservation selectContiguousFreeTime(ITimesheetAllocableObject reservable, ATSlot startSlot,
			ATSlot endSlot, TimeSegmentRequirement req) {
		ATCalendarReservation reservation = null;
		List<ATSlotIndex> slots = null;
		if (isClosedSegmentRequirement(req)) {
			// if startSlot and endSlot are not null and contains boundary limits than try
			// acquiring slots
			boolean slotsAreOK = startSlot != null && endSlot != null;
			if (slotsAreOK) {
				slots = tryAcquireClosedSegmentContiguosFreeSegments(reservable, req, startSlot, endSlot);
			}
		} else if (isPreciseRequirement(req)) {
			boolean slotsIsOk = (req.getStartAlignment() == StartDateTimeAlignment.START_ON_START_PRECISELY
					&& startSlot != null && startSlot.isInRange(req.getStartDateTime()))
					|| (req.getEndAlignment() == EndDateTimeAlignment.END_ON_END_PRECISELY && endSlot != null
							&& endSlot.isInRange(req.getEndDateTime()));
			if (slotsIsOk) {
				slots = tryAcquirePreciseContiguosFreeSegments(reservable, req, startSlot, endSlot);
			}
		} else if (isAsapRequirement(req)) {
			slots = tryAcquireContiguosFreeSegments(reservable, req, startSlot, endSlot);
		} else
			throw new IllegalStateException("Invalid unmanaged state of TimeRangeRequirement=>" + req);
		if (slots != null && !slots.isEmpty()) {
			ATPotentialSlotRange range = new ATPotentialSlotRange();
			range.slots = slots;
			reservation = new ATCalendarReservation(req.getType(), reservable, range, reservable, req);
			reservation.setReservedTime(countTime(slots));
		}
		return reservation;
	}

	protected TimeSegmentEvaluationResult calculateContiguousTime(ITimesheetAllocableObject reservable, ATSlot startSlot,
			ATSlot endSlot, TimeSegmentRequirement req) {
		TimeSegmentEvaluationResult result = null;

		if (isClosedSegmentRequirement(req)) {
			// if startSlot and endSlot are not null and contains boundary limits than try
			// acquiring slots
			boolean slotsAreOK = startSlot != null && endSlot != null && startSlot.isInRange(req.getStartDateTime())
					&& endSlot.isInRange(req.getEndDateTime());
			if (slotsAreOK) {
				TimeSegment segment = new TimeSegment(TimeSegmentType.CALENDAR_TIME, reservable);
				segment.setStartDateTime(req.getStartDateTime());
				segment.setEndDateTime(req.getEndDateTime());
				result = new TimeSegmentEvaluationResult(TimeSegmentEvaluationResultType.SUCCESSFULLY_EVALUATED,
						segment);
			}
		} else if (isPreciseRequirement(req)) {
			boolean slotsIsOk = (req.getStartAlignment() == StartDateTimeAlignment.START_ON_START_PRECISELY
					&& startSlot != null && startSlot.isInRange(req.getStartDateTime()))
					|| (req.getEndAlignment() == EndDateTimeAlignment.END_ON_END_PRECISELY && endSlot != null
							&& endSlot.isInRange(req.getEndDateTime()));
			if ((req.getStartAlignment() == StartDateTimeAlignment.START_ON_START_PRECISELY
					&& (startSlot == null || (startSlot != null && !startSlot.isInRange(req.getStartDateTime()))))) {
				result = new TimeSegmentEvaluationResult(
						TimeSegmentEvaluationResultType.LOWER_BOUND_OUT_OF_SCHEDULING_WINDOW);
			} else if ((req.getEndAlignment() == EndDateTimeAlignment.END_ON_END_PRECISELY
					&& (endSlot == null || (endSlot != null && !endSlot.isInRange(req.getEndDateTime()))))) {
				result = new TimeSegmentEvaluationResult(
						TimeSegmentEvaluationResultType.HIGHER_BOUND_OUT_OF_SCHEDULING_WINDOW);
			} else if (slotsIsOk) {
				result = measureSegments(reservable, req, startSlot, endSlot);
			}
		} else if (isAsapRequirement(req)) {
			result = measureSegments(reservable, req, startSlot, endSlot);
		} else
			throw new IllegalStateException("Invalid unmanaged state of TimeRangeRequirement=>" + req);

		return result;
	}

	protected TimeSegmentEvaluationResult measureSegments(ITimesheetAllocableObject reservable,
			TimeSegmentRequirement req, ATSlot startSlot, ATSlot endSlot) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin tryMeasureContiguosSegments(" + req + "," + startSlot + "," + endSlot + ")");
		}
		ApsLogicDirection direction = getDirection(req);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Direction is " + direction);
		}
		// pointer to the actual slot
		ATSlot actualSlot = null;
		// offset of starting allocation on the actual slot
		int actualSlotOffsetGuard = 0;
		// guard slot, it means the slot that is the maximum border of the time segment
		ATSlot guardSlot = null;
		// guard offset located in the guard slot
		int guardSlotOffsetGuard = 0;
		TimeSegment segment = new TimeSegment(TimeSegmentType.CALENDAR_TIME, reservable);
		TimeSegmentEvaluationResult result = null;
		if (req.isLowUpLimited() && startSlot != null && endSlot != null && startSlot.isInRange(req.getStartDateTime())
				&& endSlot.isInRange(req.getEndDateTime())) {
			segment.setStartDateTime(req.getStartDateTime());
			segment.setEndDateTime(req.getEndDateTime());
			result = new TimeSegmentEvaluationResult(TimeSegmentEvaluationResultType.SUCCESSFULLY_EVALUATED, segment);
		} else {
			switch (direction) {
			case FORWARD:
				actualSlot = startSlot;
				guardSlot = endSlot;
				if (actualSlot.getEndDateTime().equals(req.getStartDateTime()) && actualSlot != guardSlot) {
					actualSlot = actualSlot.afterSlot;
					if (actualSlot == null) {
						result = new TimeSegmentEvaluationResult(
								TimeSegmentEvaluationResultType.HIGHER_BOUND_OUT_OF_SCHEDULING_WINDOW);
						return result;
					}
				}
				actualSlotOffsetGuard = actualSlot.isInRange(req.getStartDateTime())
						? calculateOffset(startSlot.getStartDateTime(), req.getStartDateTime())
						: 0;
				// If going forward setting to real working slot startDateTime if
				// required startDateTime is not inside the startSlot calendar segment
				if (actualSlot.isInRange(req.getStartDateTime())) {
					segment.setStartDateTime(req.getStartDateTime());
				} else {
					segment.setStartDateTime(actualSlot.getStartDateTime());
				}
				if (guardSlot != null && req.isUpperLimited()) {
					guardSlotOffsetGuard = guardSlot.isInRange(req.getEndDateTime())
							? calculateOffset(guardSlot.getStartDateTime(), req.getEndDateTime())
							: endSlot.gridLength;
					if (guardSlot.isInRange(req.getEndDateTime())) {
						segment.setEndDateTime(req.getEndDateTime());
					}
				}

				break;
			case BACKWARD:
				actualSlot = endSlot;
				guardSlot = startSlot;
				if (actualSlot.getStartDateTime().equals(req.getEndDateTime()) && actualSlot != guardSlot) {
					actualSlot = actualSlot.beforeSlot;
					if (actualSlot == null) {
						result = new TimeSegmentEvaluationResult(
								TimeSegmentEvaluationResultType.LOWER_BOUND_OUT_OF_SCHEDULING_WINDOW);
						return result;
					}
				}
				actualSlotOffsetGuard = actualSlot.isInRange(req.getEndDateTime())
						? calculateOffset(req.getEndDateTime(), actualSlot.getEndDateTime())
						: 0;
				if (actualSlot.isInRange(req.getEndDateTime())) {
					segment.setEndDateTime(req.getEndDateTime());
				} else {
					segment.setEndDateTime(actualSlot.getEndDateTime());
				}
				if (guardSlot != null && req.isLowerLimited()) {
					guardSlotOffsetGuard = guardSlot.isInRange(req.getStartDateTime())
							? calculateOffset(guardSlot.getStartDateTime(), req.getStartDateTime())
							: 0;
				}
				break;

			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Margin slots:");
				LOGGER.debug("StartSlot=>" + startSlot);
				LOGGER.debug("EndSlot=>" + endSlot);
				LOGGER.debug("direction=>" + direction);
				LOGGER.debug("actualSlot=>" + actualSlot);
				LOGGER.debug("guardSlot=>" + guardSlot);
			}
			double remainingDuration = Math.max(Math.floor(req.getAvailabilityDuration()), 1);
			while (remainingDuration > 0 && actualSlot != null) {

				if ((actualSlot.getDurationMinutes() - actualSlotOffsetGuard) >= remainingDuration) {
					switch (direction) {
					case BACKWARD: {
						GregorianCalendar gregorian = new GregorianCalendar();
						gregorian.setTime(actualSlot.getEndDateTime());
						gregorian.add(GregorianCalendar.MINUTE,
								-(int) (Math.floor(remainingDuration) + actualSlotOffsetGuard));
						segment.setStartDateTime(gregorian.getTime());
						result = new TimeSegmentEvaluationResult(TimeSegmentEvaluationResultType.SUCCESSFULLY_EVALUATED,
								segment);
					}
						break;
					case FORWARD: {
						GregorianCalendar gregorian = new GregorianCalendar();
						gregorian.setTime(actualSlot.getStartDateTime());
						gregorian.add(GregorianCalendar.MINUTE,
								(int) (Math.floor(remainingDuration) + actualSlotOffsetGuard));
						segment.setEndDateTime(gregorian.getTime());
						result = new TimeSegmentEvaluationResult(TimeSegmentEvaluationResultType.SUCCESSFULLY_EVALUATED,
								segment);
					}
						break;
					}
				}
				remainingDuration -= (actualSlot.getDurationMinutes() - actualSlotOffsetGuard);
				switch (direction) {
				case BACKWARD: {
					actualSlot = actualSlot.beforeSlot;
					actualSlotOffsetGuard = 0;
					if (actualSlot == null && result == null) {
						result = new TimeSegmentEvaluationResult(
								TimeSegmentEvaluationResultType.LOWER_BOUND_LIMIT_REACH);
					}
				}
					break;
				case FORWARD: {
					actualSlot = actualSlot.afterSlot;
					actualSlotOffsetGuard = 0;
					if (actualSlot == null && result == null) {
						result = new TimeSegmentEvaluationResult(
								TimeSegmentEvaluationResultType.UPPER_BOUND_LIMIT_REACH);
					}
				}
					break;
				}
			}

		}
		if (result.getResultType() == TimeSegmentEvaluationResultType.SUCCESSFULLY_EVALUATED) {
			if (!result.getResult().isValid()) {
				throw new OpenI40Exception("Invalid segment " + result.getResult() + " allocated on request:" + req);
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End tryMeasureContiguosSegments(" + req + "," + startSlot + "," + endSlot + ")=>" + segment);
		}
		return result;
	}

	protected AvailableTimeSegments investigateFreeSegments(ITimesheetAllocableObject reservable,
			TimeSegmentRequirement req, ATSlot startSlot, ATSlot endSlot) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin investigateFreeSegments(" + req + "," + startSlot + "," + endSlot + ")");
		}
		AvailableTimeSegments availableTimeSegments = new AvailableTimeSegments();
		availableTimeSegments.setResource(reservable);

		// pointer to the actual slot
		ATSlot actualSlot = null;
		// offset of starting allocation on the actual slot
		int actualSlotOffsetGuard = 0;
		// guard slot, it means the slot that is the maximum border of the time segment
		ATSlot guardSlot = null;
		// guard offset located in the guard slot
		int guardSlotOffsetGuard = 0;
		TimeSegment segment = new TimeSegment(TimeSegmentType.CALENDAR_TIME, reservable);
		TimeSegmentEvaluationResult result = null;

		actualSlot = startSlot;
		guardSlot = endSlot;
		if (actualSlot.getEndDateTime().equals(req.getStartDateTime()) && actualSlot != guardSlot) {
			actualSlot = actualSlot.afterSlot;
		}
		actualSlotOffsetGuard = actualSlot.isInRange(req.getStartDateTime())
				? calculateOffset(startSlot.getStartDateTime(), req.getStartDateTime())
				: 0;
		// If going forward setting to real working slot startDateTime if
		// required startDateTime is not inside the startSlot calendar segment
		if (actualSlot.isInRange(req.getStartDateTime())) {
			segment.setStartDateTime(req.getStartDateTime());
		} else {
			segment.setStartDateTime(actualSlot.getStartDateTime());
		}
		if (guardSlot != null && req.isUpperLimited()) {
			guardSlotOffsetGuard = guardSlot.isInRange(req.getEndDateTime())
					? calculateOffset(guardSlot.getStartDateTime(), req.getEndDateTime())
					: endSlot.gridLength;
			if (guardSlot.isInRange(req.getEndDateTime())) {
				segment.setEndDateTime(req.getEndDateTime());
			}
		}
		WorkingTimeSegment currentTimeSegment = null;
		ATSlot lastSlot = null;
		ATFreeSegment lastFreeSegment = null;
		int currentWorkDuration = 0;
		while (actualSlot != null) {
			List<ATFreeSegment> freeSegments = actualSlot.freeSegments;

			boolean NoFreeSegment = freeSegments == null || freeSegments.isEmpty();
			if (NoFreeSegment) {
				// If existing currentTimeSegment than truncate it because we have an
				// interruction in
				// contiguous available time
				if (currentTimeSegment != null) {
					currentTimeSegment = null;
					currentWorkDuration = 0;
				}
			}
			if (freeSegments != null && !freeSegments.isEmpty()) {
				for (ATFreeSegment freeSegment : freeSegments) {
					boolean StartingFreeSegment = freeSegment.startIndex == 0;
					boolean EndingFreeSegment = freeSegment.endIndex == actualSlot.gridLength;
					boolean SpareFreeSegment = !StartingFreeSegment && !EndingFreeSegment;
					boolean FullFreeSlotSegment = StartingFreeSegment && EndingFreeSegment && freeSegments.size() == 1;
					int startIndex = freeSegment.startIndex, endIndex = freeSegment.endIndex;
					if (actualSlot == startSlot) {
						startIndex = Math.max(freeSegment.startIndex, actualSlotOffsetGuard);
					}
					if (actualSlot == endSlot) {
						endIndex = Math.min(freeSegment.endIndex, guardSlotOffsetGuard);
					}
					Date startDateTime = actualSlot.getIndexedDateTime(startIndex);
					Date endDateTime = actualSlot.getIndexedDateTime(endIndex);
					int actualSegmentWorkTime = endIndex - startIndex;
					boolean allocateNewSegment = currentTimeSegment == null;
					// Initialize currentTimeSegment if is null
					if (allocateNewSegment) {
						currentWorkDuration = 0;
						currentTimeSegment = new WorkingTimeSegment();
						currentTimeSegment.setStartDateTime(startDateTime);
						availableTimeSegments.getAvailableTimeSegments().add(currentTimeSegment);
					}
					if (FullFreeSlotSegment) {
						currentWorkDuration += actualSegmentWorkTime;
						if (currentTimeSegment != null) {
							currentTimeSegment.setEndDateTime(endDateTime);
							currentTimeSegment.setWorkingTime(currentWorkDuration);
						}
					} else if (StartingFreeSegment) {
						currentWorkDuration += actualSegmentWorkTime;
						currentTimeSegment.setEndDateTime(endDateTime);
						currentTimeSegment.setWorkingTime(currentWorkDuration);
						currentTimeSegment = null;
						currentWorkDuration = 0;
					} else {
						if (!allocateNewSegment) {
							currentTimeSegment = null;
							currentWorkDuration = 0;
							currentTimeSegment = new WorkingTimeSegment();
							currentTimeSegment.setStartDateTime(startDateTime);
							availableTimeSegments.getAvailableTimeSegments().add(currentTimeSegment);
						}
						currentWorkDuration += actualSegmentWorkTime;
						if (EndingFreeSegment) {
							currentTimeSegment.setEndDateTime(endDateTime);
							currentTimeSegment.setWorkingTime(currentWorkDuration);
						} else if (SpareFreeSegment) {
							currentTimeSegment.setEndDateTime(endDateTime);
							currentTimeSegment.setWorkingTime(currentWorkDuration);
							currentTimeSegment = null;
							currentWorkDuration = 0;
						}
						lastFreeSegment = freeSegment;
					}
				}
			}
			lastSlot = actualSlot;
			if (actualSlot == guardSlot) {
				actualSlot = null;
			} else
				actualSlot = actualSlot.afterSlot;
			actualSlotOffsetGuard = 0;

		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End investigateFreeSegments(" + req + "," + startSlot + "," + endSlot + ")=>" + segment);
		}
		availableTimeSegments.getAvailableTimeSegments().removeIf((entry) -> {
			return entry.getWorkingTime() < req.getAvailabilityDuration();
		});
		return availableTimeSegments;
	}

	@Override
	public void addReservation(ITimesheetAllocableObject ewcalendar, TimesheetReservation reservation) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin addReservation(" + ewcalendar + "," + reservation + ")");
		}
		AdvancedTimesheet calendar = getCalendar(ewcalendar);
		calendar.minutesTotalReserved += reservation.getReservedTime();
		if (!ewcalendar.isInfiniteCapacity()) {

			if (reservation instanceof ATCalendarReservation) {
				ATCalendarReservation calendarReservation = (ATCalendarReservation) reservation;
				// calendarReservation.
				for (ATSlotIndex _element : calendarReservation.slotRange.slots) {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("Cutting slotIndex:" + _element);
					}
					ATSlot slot = _element.slot;
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("Slot=>" + slot);
						LOGGER.debug("slot.freeSegments=>" + slot.freeSegments);
					}

					ATFreeSegment segment = _element.usedSegment;
					int startIndex = _element.startIndex;
					int endIndex = _element.endIndex;
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("Removing segment:" + segment + " from slot.freeSegments=>" + slot.freeSegments);
					}

					TreeMap<Integer, ATFreeSegment> segments = new TreeMap<>();
					for (ATFreeSegment s : slot.freeSegments) {
						if (s.startIndex <= startIndex && endIndex <= s.endIndex) {
							if (s.startIndex < startIndex) {
								ATFreeSegment freeSegment = new ATFreeSegment();
								freeSegment.startIndex = s.startIndex;
								freeSegment.endIndex = startIndex;
								freeSegment.SlotId = slot.getId();
								segments.put(freeSegment.startIndex, freeSegment);
							}
							if (endIndex < s.endIndex) {
								ATFreeSegment freeSegment = new ATFreeSegment();
								// freeSegment.startIndex = endIndex + 1;
								freeSegment.startIndex = endIndex;
								freeSegment.endIndex = s.endIndex;
								freeSegment.SlotId = slot.getId();
								segments.put(freeSegment.startIndex, freeSegment);
							}
						} else {
							segments.put(s.startIndex, s);
						}

					}

					slot.freeSegments = new ArrayList<ATFreeSegment>(segments.values());
					for (int i = 0; i < slot.freeSegments.size(); i++) {
						slot.freeSegments.get(i).position = i;
					}
					slot.reservations.add(calendarReservation);
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("Slot modified to=>" + slot);
						LOGGER.debug("slot.freeSegments=>" + slot.freeSegments);
					}
				}

			} else
				throw new RuntimeException(
						"Wrong class of CalendarReservation mixed reservations format due to some wrong execution path");
		} else {
			calendar.addInfiniteCapacityReservation(reservation);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Infinite capacity machine");
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End addReservation(" + ewcalendar + "," + reservation + ")");
		}
	}

	@Override
	public void removeReservation(ITimesheetAllocableObject ewcalendar, TimesheetReservation reservation) {
		AdvancedTimesheet calendar = getCalendar(ewcalendar);
		if (!ewcalendar.isInfiniteCapacity()) {
			if (reservation instanceof ATCalendarReservation) {
				ATCalendarReservation calendarReservation = (ATCalendarReservation) reservation;
				// calendarReservation.
				for (ATSlotIndex _element : calendarReservation.slotRange.slots) {
					ATSlot slot = _element.slot;
					ATFreeSegment segment = _element.usedSegment;
					slot.reservations.remove(calendarReservation);
					List<ATFreeSegment> segmentsToRemove = new ArrayList<ATFreeSegment>();
					for (ATFreeSegment s : slot.freeSegments) {
						if (s.startIndex >= segment.startIndex && s.endIndex <= segment.endIndex) {
							segmentsToRemove.add(s);
						}
					}
					int minIndex = 0;
					for (ATFreeSegment freeSegment : segmentsToRemove) {
						slot.freeSegments.remove(freeSegment);
						minIndex = Math.min(freeSegment.position, minIndex);
					}
					segment.position = minIndex;
					slot.freeSegments.add(minIndex, segment);
				}
			} else
				throw new RuntimeException(
						"Wrong class of CalendarReservation mixed reservations format due to some wrong execution path");
		}
	}

	@Override
	public TimeSegmentEvaluationResult calculateTimeSegmentLimits(ITimesheetAllocableObject ewcalendar,
			TimeSegmentRequirement rangeRequirement) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Begin calculateTimesegmentBounds(" + ewcalendar.getCode() + "," + rangeRequirement + ",...)");
		TimeSegment outTime = new TimeSegment(TimeSegmentType.CALENDAR_TIME, ewcalendar);
		assert rangeRequirement.isValidState();
		AdvancedTimesheet calendar = getCalendar(ewcalendar);
		boolean periodIsOk = rangeRequirement.isLowUpLimited()
				|| (rangeRequirement.isLowerLimited() && rangeRequirement.getAvailabilityDuration() > 0)
				|| (rangeRequirement.isUpperLimited() && rangeRequirement.getAvailabilityDuration() > 0);
		if (!periodIsOk) {
			throw new OpenI40Exception("The passed range " + rangeRequirement
					+ " must be upper and lower limited or must have AvailabilityDuration>0 and being upper or lower limited");
		}
		ApsLogicDirection direction = null;
		if (rangeRequirement.getStartAlignment() != null) {
			switch (rangeRequirement.getStartAlignment()) {
			case START_ON_START_PRECISELY:
			case START_AFTER_START_ASAP: {
				direction = ApsLogicDirection.FORWARD;
				if (!rangeRequirement.isLowerLimited())
					throw new OpenI40Exception(
							"Range requirement without start date cannot be ALIGN_START_PRECISELY or ALIGN_START_ASAP");
			}
				break;
			}
		} else if (rangeRequirement.getEndAlignment() != null) {
			switch (rangeRequirement.getEndAlignment()) {
			case END_ON_END_PRECISELY:
			case END_BEFORE_END_ASAP: {
				direction = ApsLogicDirection.BACKWARD;
				if (!rangeRequirement.isUpperLimited())
					throw new OpenI40Exception(
							"Range requirement without end date cannot be ALIGN_END_PRECISELY or ALIGN_END_ASAP");
			}
				break;
			}
		} else {
			String _msg = "NO DIRECTION SPECIFIED BY " + rangeRequirement;
			LOGGER.error(_msg);
			throw new OpenI40Exception(_msg);
		}

		// FIRST SELECT FIRST LEVEL SLOTS WITH THE CALENDAR GENERATION CRITERIA
		Date discreteStart = null;
		Date discreteEnd = null;
		ATSlot startSlot = null;
		ATSlot endSlot = null;

		if (rangeRequirement.isLowerLimited()) {
			Date startSearchSlot = null;
			startSearchSlot = DateUtil.discretize(rangeRequirement.getStartDateTime());
			discreteStart = startSearchSlot;
			startSlot = calendar.getContainingOrNearestSlot(startSearchSlot, direction);

		}
		if (rangeRequirement.isUpperLimited()) {
			Date endSearchSlot = null;
			endSearchSlot = DateUtil.discretize(rangeRequirement.getEndDateTime());
			discreteEnd = endSearchSlot;
			endSlot = calendar.getContainingOrNearestSlot(endSearchSlot, direction);
		}
		TimeSegmentRequirement discreteRequirement = new TimeSegmentRequirement(rangeRequirement);
		discreteRequirement.setStartDateTime(discreteStart);
		discreteRequirement.setEndDateTime(discreteEnd);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("req=>" + discreteRequirement + " startSlot=>" + startSlot + " endSlot=>" + endSlot);
		}
		if (startSlot == null && endSlot == null) {
			return new TimeSegmentEvaluationResult(
					TimeSegmentEvaluationResultType.TIMESEGMENT_REQUEST_WITH_UNMANAGED_MARGINS);
		}
		return calculateContiguousTime(ewcalendar, startSlot, endSlot, discreteRequirement);
	}

	@Override
	public long getMinutesReserved(ITimesheetAllocableObject ewcalendar) {
		AdvancedTimesheet calendar = getCalendar(ewcalendar);
		return calendar.minutesTotalReserved;
	}

	@Override
	public AvailableTimeSegments getAvailableTimeSegments(ITimesheetAllocableObject ewcalendar,
			TimeSegmentRequirement rangeRequirement) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Begin getAvailableTimeSegments(" + ewcalendar.getCode() + "," + rangeRequirement + ",...)");
		if (rangeRequirement.getAvailabilityDuration() > 1000) {
			LOGGER.warn("High time Range=>" + rangeRequirement + " for resource=>" + ewcalendar.getCode());
		}
		assert rangeRequirement.isValidState();
		AdvancedTimesheet calendar = getCalendar(ewcalendar);
		boolean periodIsOk = rangeRequirement.isLowUpLimited();
		if (!periodIsOk) {
			throw new OpenI40Exception("The passed range " + rangeRequirement + " must be upper and lower limited ");
		}
		ApsLogicDirection direction = ApsLogicDirection.FORWARD;
		TimesheetReservation reservation = null;
		Date discreteStart = null;
		Date discreteEnd = null;
		ATSlot startSlot = null;
		ATSlot endSlot = null;

		if (rangeRequirement.isLowerLimited()) {
			Date startSearchSlot = null;
			startSearchSlot = DateUtil.discretize(rangeRequirement.getStartDateTime());
			discreteStart = startSearchSlot;
			startSlot = calendar.getContainingOrNearestSlot(startSearchSlot, direction);

		}
		if (rangeRequirement.isUpperLimited()) {
			Date endSearchSlot = null;
			endSearchSlot = DateUtil.discretize(rangeRequirement.getEndDateTime());
			discreteEnd = endSearchSlot;
			endSlot = calendar.getContainingOrNearestSlot(endSearchSlot, direction);
		}
		TimeSegmentRequirement discreteRequirement = new TimeSegmentRequirement(rangeRequirement);
		discreteRequirement.setStartDateTime(discreteStart);
		discreteRequirement.setEndDateTime(discreteEnd);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("req=>" + discreteRequirement + " startSlot=>" + startSlot + " endSlot=>" + endSlot);
		}
		AvailableTimeSegments avail = investigateFreeSegments(ewcalendar, discreteRequirement, startSlot, endSlot);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End getAvailableTimeSegments(" + ewcalendar.getCode() + "," + rangeRequirement + ",...)");
			LOGGER.debug("Returned=>" + reservation);
		}
		return avail;
	}

}
