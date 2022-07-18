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
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.inputchannels.streaminputs.IInputDataStreamFactory;
import com.openi40.scheduler.inputchannels.streaminputs.InputDataStreamException;

public class PersistenceInputDataStreamFactory implements IInputDataStreamFactory {
	String dataSourceName = null;
	String dataSetName = null;
	String dataSetVariant = null;
	String dataSourceDescription = null;
	Boolean useJpaStreaming=false;
	Integer batchingSize=10000;
	List<AbstractPersistenceInputDataStream> persistenceStreams;
	
	
	private <DtoEntityType extends InputDto> AbstractPersistenceInputDataStream findSource(Class<DtoEntityType> requiredType) {
		AbstractPersistenceInputDataStream foundStream = null;
		for (AbstractPersistenceInputDataStream ps : this.persistenceStreams) {
			if (ps.getInputClass().equals(requiredType)) {
				foundStream = ps;
			}
		}
		if (foundStream == null)
			throw new IllegalStateException("Cannot find PersistenceInputDataStream for " + requiredType.getName());
		return foundStream;
	}

	@Override
	public <DtoEntityType extends InputDto> Stream<DtoEntityType> getStream(Class<DtoEntityType> requiredType)
			throws InputDataStreamException {

		return findSource(requiredType).stream(useJpaStreaming!=null && useJpaStreaming,batchingSize);
	}

	@Override
	public <DtoEntityType extends InputDto> Stream<DtoEntityType> getStream(Class<DtoEntityType> requiredType,
			Date modifiedAfter) throws InputDataStreamException {
		return findSource(requiredType).streamModifiedAfter(new Timestamp(modifiedAfter.getTime()),useJpaStreaming!=null && useJpaStreaming,batchingSize);
	}

	public String getDataSourceName() {
		return dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	public String getDataSetName() {
		return dataSetName;
	}

	public void setDataSetName(String dataSetName) {
		this.dataSetName = dataSetName;
	}

	public String getDataSetVariant() {
		return dataSetVariant;
	}

	public void setDataSetVariant(String dataSetVariant) {
		this.dataSetVariant = dataSetVariant;
	}

	public String getDataSourceDescription() {
		return dataSourceDescription;
	}

	public void setDataSourceDescription(String dataSourceDescription) {
		this.dataSourceDescription = dataSourceDescription;
	}

	public List<AbstractPersistenceInputDataStream> getPersistenceStreams() {
		return persistenceStreams;
	}

	public void setPersistenceStreams(List<AbstractPersistenceInputDataStream> persistenceStreams) {
		this.persistenceStreams = persistenceStreams;
	}

	public Boolean getUseJpaStreaming() {
		return useJpaStreaming;
	}

	public void setUseJpaStreaming(Boolean useStreaming) {
		this.useJpaStreaming = useStreaming;
	}

	public Integer getBatchingSize() {
		return batchingSize;
	}

	public void setBatchingSize(Integer batchingSize) {
		this.batchingSize = batchingSize;
	}

}
