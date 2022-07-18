package com.openi40.scheduler.common.datapath;

import java.util.List;
import java.util.function.Consumer;
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
public interface IDataPathExtractor {
	public <ObjectType> List<ObjectType> readByPath(Object rootObject, Class rootType, Class<ObjectType> searchedTypes, String... dataPaths) throws DataPathException;

	public <ObjectType> void consumeByPath(Object rootObject, Class rootType, Class<ObjectType> searchedTypes, Consumer<ObjectType> consumer, String... dataPaths) throws DataPathException;
}
