package com.openi40.scheduler.engine.aps;

import com.openi40.scheduler.model.aps.ApsData;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 *
 * This interface declares the signatures to be implemented  to be notified of begin/end scheduling events
 */

public interface IApsComposerListener {
	public void onStartAlgorithms(ApsData data);
	public void onDataClean(ApsData data);
	public void onEndAlgorithms(ApsData data);
}
