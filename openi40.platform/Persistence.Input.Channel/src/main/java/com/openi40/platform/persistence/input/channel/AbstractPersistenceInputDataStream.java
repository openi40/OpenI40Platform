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
package com.openi40.platform.persistence.input.channel;

import java.sql.Timestamp;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.openi40.scheduler.input.model.InputDto;

public abstract class AbstractPersistenceInputDataStream<PClass extends InputClass, InputClass extends InputDto, InputChannelRepo extends InputChannelStreamProvider<InputClass>> {
	private Class<PClass> pclass;
	private Class<InputClass> inputClass;
	private InputChannelRepo repository;
	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	protected AbstractPersistenceInputDataStream(Class<PClass> pclass, Class<InputClass> inputClass,
			InputChannelRepo repository) {
		this.pclass = pclass;
		this.inputClass = inputClass;
		this.repository = repository;
	}

	public Class<PClass> getPclass() {
		return pclass;
	}

	public Class<InputClass> getInputClass() {
		return inputClass;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)

	public Stream<InputClass> stream(boolean useDBStreaming, int batchingSize) {
		LOGGER.info("Begin stream(" + useDBStreaming + "," + batchingSize + ")");
		Stream<InputClass> stream = this.repository.streamAll(useDBStreaming, batchingSize).map(o -> o);
		LOGGER.info("End stream(" + useDBStreaming + "," + batchingSize + ")");
		return stream;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Stream<InputClass> streamModifiedAfter(Timestamp ts, boolean useDBStreaming, int batchingSize) {
		LOGGER.info("Begin streamModifiedAfter(" + ts + "," + useDBStreaming + "," + batchingSize + ")");
		Stream<InputClass> stream = this.repository.streamModifiedAfter(ts, useDBStreaming, batchingSize).map(o -> o);
		LOGGER.info("End streamModifiedAfter(" + ts + "," + useDBStreaming + "," + batchingSize + ")");
		return stream;
	}
}
