package com.openi40.scheduler.engine.resallocator;

import com.openi40.scheduler.model.time.TimeSegmentRequirement;

class UnavailableSolutions {
	TimeSegmentRequirement SetupTimeRangeSpec = null, WorkTimeRangeSpec = null;

	public UnavailableSolutions(TimeSegmentRequirement SetupTimeRangeSpec,
			TimeSegmentRequirement WorkTimeRangeSpec) {
		this.SetupTimeRangeSpec = SetupTimeRangeSpec;
		this.WorkTimeRangeSpec = WorkTimeRangeSpec;

	}

	public TimeSegmentRequirement getSetupTimeRangeSpec() {
		return SetupTimeRangeSpec;
	}

	public void setSetupTimeRangeSpec(TimeSegmentRequirement setupTimeRangeSpec) {
		SetupTimeRangeSpec = setupTimeRangeSpec;
	}

	public TimeSegmentRequirement getWorkTimeRangeSpec() {
		return WorkTimeRangeSpec;
	}

	public void setWorkTimeRangeSpec(TimeSegmentRequirement workTimeRangeSpec) {
		WorkTimeRangeSpec = workTimeRangeSpec;
	}
}