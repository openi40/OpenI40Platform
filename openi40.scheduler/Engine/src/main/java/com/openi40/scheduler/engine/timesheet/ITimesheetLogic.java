package com.openi40.scheduler.engine.timesheet;

import java.util.List;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;
import com.openi40.scheduler.model.time.Timesheet;
import com.openi40.scheduler.model.time.TimesheetReservation;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 * Package
 *         com.openi40.scheduler.engine.timesheet class ITimesheetLogic.
 * 
 *         Most methods in this class require a TimeSegmentRequirement parameter
 *         with the following semantics:
 * 
 *         StartDateTime => not null EndDateTime => not null
 *         AvailabilityDuration => not considered Alignment =>
 *         ALIGN_START_PRECISELY Meaning: Search exactly from StartDateTime to
 *         EndDateTime
 *
 *         StartDateTime => not null EndDateTime => null AvailabilityDuration =>
 *         >0 Alignment => ALIGN_START_PRECISELY Meaning: Search EXACTLY
 *         allocable slot from StartDateTime to the right (forward) for
 *         availabilityDuration of working time.
 * 
 *         StartDateTime => not null EndDateTime => null AvailabilityDuration =>
 *         >0 Alignment => ALIGN_START_ASAP Meaning: Search first allocable slot
 *         from a date and time >= StartDateTime to the right (forward) for
 *         availabilityDuration minutes of working time.
 * 
 *         StartDateTime => null EndDateTime => not null AvailabilityDuration =>
 *         >0 Alignment => ALIGN_END_PRECISELY Meaning: Search EXACTLY allocable
 *         slot from EndDateTime to the left (backward) for
 *         availiabilityDuration minutes of working time.
 * 
 *         StartDateTime => null EndDateTime => not null AvailabilityDuration =>
 *         >0 Alignment => ALIGN_END_ASAP Meaning: Search first allocable slot
 *         from time <= EndDateTime to the left (backward) for
 *         availiabilityDuration minutes of working time.
 * 
 * 
 * 
 */
@BusinessInterface(entityClass = ITimesheetAllocableObject.class)
public interface ITimesheetLogic extends IBusinessLogic<ITimesheetAllocableObject> {
	/**
	 * Retrieves a clean calendar in its state before the scheduling algorithm is
	 * applied. The status of "clean calendar" means a predefined calendar rules/and
	 * availability present in the OpenScheduler database.
	 * 
	 * @param calendar
	 * @return
	 */
	Timesheet createCleanCalendar(ITimesheetAllocableObject calendar);

	/**
	 * Gets the list of already existing calendar reservations in the calendar of a
	 * specific resource in the first parameter.
	 * 
	 * @param ewcalendar
	 * @return
	 */
	List<TimesheetReservation> getReservations(ITimesheetAllocableObject ewcalendar);

	/**********************************************************************************
	 * Get the total amount of minutes actually the current item is reserved
	 * 
	 * @param ewcalendar
	 * @return
	 */
	long getMinutesReserved(ITimesheetAllocableObject ewcalendar);

	/**
	 * Gets the list of calendar reservations in the calendar of a specific resource
	 * that are in the time segment passed in the second parameter
	 * 
	 * @param ewcalendar
	 * @param range
	 * @return
	 */
	List<TimesheetReservation> getReservations(ITimesheetAllocableObject ewcalendar, TimeSegmentRequirement range);

	/**
	 * Create a reservation already applied modifiyng the calendar state
	 * 
	 * @param ewcalendar
	 * @param rangeRequirement
	 * @param reservedFrom
	 * @return
	 */
	TimesheetReservation addReservation(ITimesheetAllocableObject ewcalendar, TimeSegmentRequirement rangeRequirement,
			IApsObject reservedFrom);

	/**
	 * Plans a reservation that can be activated but is not yet changing resource
	 * usages since a applyOperation method is called on the reservation (or it is
	 * used the addReservation(..) method in this class).
	 * 
	 * @param ewcalendar
	 * @param rangeRequirement
	 * @param reservedFrom
	 */
	TimesheetReservation planReservation(ITimesheetAllocableObject ewcalendar, TimeSegmentRequirement rangeRequirement,
			IApsObject reservedFrom);

	/**
	 * Adds a reservation to the reservation list
	 * 
	 * @param ewcalendar
	 * @param reservation
	 */
	void addReservation(ITimesheetAllocableObject ewcalendar, TimesheetReservation reservation);

	/**
	 * Removes a reservation
	 * 
	 * @param ewcalendar
	 * @param reservation
	 */
	void removeReservation(ITimesheetAllocableObject ewcalendar, TimesheetReservation reservation);

	/*****************************************************************************************************
	 * Tries to fit the calendar slots to the requested TimeSegmentRequirement even
	 * if already allocated
	 * 
	 * @param ewcalendar
	 * @param rangeRequirement
	 * @return
	 */
	TimeSegmentEvaluationResult calculateTimeSegmentLimits(ITimesheetAllocableObject ewcalendar,
			TimeSegmentRequirement rangeRequirement);

	/****************************************************************************************************
	 * List of available free slots group in resource calendar that fits the
	 * rangeRequirement
	 * 
	 * @param ewcalendar
	 * @param rangeRequirement
	 * @return
	 */
	AvailableTimeSegments getAvailableTimeSegments(ITimesheetAllocableObject ewcalendar,
			TimeSegmentRequirement rangeRequirement);

}