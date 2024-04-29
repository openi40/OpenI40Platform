package com.openi40.scheduler.engine.equipment.allocation;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.model.time.TimesheetReservation;
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
public class MatchingSecondaryResourcePhasesAllocations extends MatchingSecondaryResourcePhases {
	List<TimesheetReservation> executionReservations=new ArrayList<>();
	List<TimesheetReservation> preparationReservations=new ArrayList<>();
	List<AllocationTimeSegments> timeRelocations=new ArrayList<>();
	public MatchingSecondaryResourcePhasesAllocations(MatchingSecondaryResourcePhases rp) {
		this.execution = rp.execution;
		this.preparation = rp.preparation;
	}
	boolean resourceRequirementMet=false;
}
