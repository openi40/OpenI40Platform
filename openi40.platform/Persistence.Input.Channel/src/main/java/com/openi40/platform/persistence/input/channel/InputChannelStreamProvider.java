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
package com.openi40.platform.persistence.input.channel;

import java.sql.Timestamp;
import java.util.stream.Stream;

import com.openi40.scheduler.input.model.InputDto;

public interface InputChannelStreamProvider<PClass extends InputDto> {
	Stream<PClass> streamAll(boolean useDBStreaming, int batchingSize);

	
	Stream<PClass> streamModifiedAfter(Timestamp timestamp, boolean useDBStreaming, int batchingSize);
}
