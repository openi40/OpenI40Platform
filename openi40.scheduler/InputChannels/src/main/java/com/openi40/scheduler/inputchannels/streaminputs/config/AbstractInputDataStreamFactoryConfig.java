package com.openi40.scheduler.inputchannels.streaminputs.config;

import java.util.ArrayList;
import java.util.List;
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

public class AbstractInputDataStreamFactoryConfig<ETYPE extends EntityImportSetting> {
	String dataImporterId = null;
	String dataSourceName = null;
	String dataSetName = null;
	String dataSetVariant = null;
	private String description=null;
	boolean createURIByEntityName = false;
	boolean hasIncrementalUpdates=false;
	String singleApsInputUri=null;
	String relativePathTemplate = null;
	
	List<ETYPE> entitiesSetting = new ArrayList<ETYPE>();

	public String getDataImporterId() {
		return dataImporterId;
	}

	public void setDataImporterId(String dataImporterId) {
		this.dataImporterId = dataImporterId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCreateURIByEntityName() {
		return createURIByEntityName;
	}

	public void setCreateURIByEntityName(boolean createURIByEntityName) {
		this.createURIByEntityName = createURIByEntityName;
	}

	public boolean isHasIncrementalUpdates() {
		return hasIncrementalUpdates;
	}

	public void setHasIncrementalUpdates(boolean hasIncrementalUpdates) {
		this.hasIncrementalUpdates = hasIncrementalUpdates;
	}

	public String getSingleApsInputUri() {
		return singleApsInputUri;
	}

	public void setSingleApsInputUri(String singleApsInputUri) {
		this.singleApsInputUri = singleApsInputUri;
	}

	public String getRelativePathTemplate() {
		return relativePathTemplate;
	}

	public void setRelativePathTemplate(String relativePathTemplate) {
		this.relativePathTemplate = relativePathTemplate;
	}

	public List<ETYPE> getEntitiesSetting() {
		return entitiesSetting;
	}

	public void setEntitiesSetting(List<ETYPE> entitiesSetting) {
		this.entitiesSetting = entitiesSetting;
	}
}
