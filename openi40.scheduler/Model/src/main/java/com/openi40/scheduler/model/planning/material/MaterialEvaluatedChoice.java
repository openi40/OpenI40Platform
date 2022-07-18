package com.openi40.scheduler.model.planning.material;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.openi40.scheduler.common.aps.IOperation;
import com.openi40.scheduler.common.utils.CollectionUtil;
import com.openi40.scheduler.common.utils.DateUtil;
import com.openi40.scheduler.model.material.SupplyReservation;
import com.openi40.scheduler.model.planning.PlanChoice;
import com.openi40.scheduler.model.rules.MaterialRule;
import com.openi40.scheduler.model.time.TimeSegment;
import com.openi40.scheduler.model.time.TimeSegmentType;

import lombok.Data;
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
@Data
public class MaterialEvaluatedChoice extends PlanChoice {
	public MaterialEvaluatedChoice(MaterialChoice materialConstraintSatisfactionPlan, TimeSegment setupTimeRange,
			TimeSegment workTimeRange, List<IOperation> reversibleOperations,
			List<SupplyReservation> generatedReservations) {
		super(materialConstraintSatisfactionPlan, setupTimeRange, workTimeRange, reversibleOperations);
		setGeneratedReservations(generatedReservations);
		Date dt = null;
		if (!generatedReservations.isEmpty()) {
			for (SupplyReservation gr : generatedReservations) {
				dt = DateUtil.getInstance().Min(dt, gr.getMovementDate());
			}
		}
		if (dt != null) {
			this.setWork(new TimeSegment(TimeSegmentType.WORK_TIME,
					materialConstraintSatisfactionPlan.getConstraint().getTargetTask()));
			this.getWork().setStartDateTime(dt);
		}
	}

	private List<SupplyReservation> GeneratedReservations = new ArrayList<SupplyReservation>();

	@Override
	public void choose() {
		getParent().setChoosed(this);
		MaterialRule constraint = (MaterialRule) getParent().getConstraint();
		CollectionUtil.getInstance().AddCollection(constraint.getConstraintGeneratedReservations(),
				getGeneratedReservations());
		Date movementDate = getParent() != null && getParent().getConstraint() != null
				&& getParent().getConstraint().getTargetTask() != null
				&& getParent().getConstraint().getTargetTask().getWorkPhaseExecution() != null
						? getParent().getConstraint().getTargetTask().getWorkPhaseExecution().getStartDateTime()
						: null;
		for (SupplyReservation reservation : getGeneratedReservations()) {
			if (movementDate != null)
				reservation.setMovementDate(movementDate);
			constraint.getConsumer().addMaterialReservation(reservation);
			if (!constraint.getMaterialSupplies().contains(reservation.getSupply())) {
				constraint.getMaterialSupplies().add(reservation.getSupply());
			}
		}

	}
}