package com.openi40.scheduler.common.datapath;

import java.util.function.Consumer;
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
public interface IDataPathIndexScanner {
	public <SearchedType> void scanObjects(Object rootObject, Class rootType, Class<SearchedType> searchedType, Consumer<SearchedType> consumer, DataPathCfg pathsCfg) throws DataPathException;
}
