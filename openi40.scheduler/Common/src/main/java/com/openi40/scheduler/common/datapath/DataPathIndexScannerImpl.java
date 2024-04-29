package com.openi40.scheduler.common.datapath;

import java.util.List;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
@Service
public class DataPathIndexScannerImpl implements IDataPathIndexScanner {
	static Logger LOGGER = LoggerFactory.getLogger(DataPathIndexScannerImpl.class);
	@Autowired
	IDataPathExtractor extractor;

	@Override
	public <SearchedType> void scanObjects(Object rootObject, Class rootType, Class<SearchedType> searchedType, Consumer<SearchedType> consumer, DataPathCfg pathsCfg) throws DataPathException {
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Running DataPathIndexScannerImpl.scanObjects(rootObject," + rootType.getName() + "," + searchedType.getName() + ",consumer)");
		List<String> pathList = pathsCfg.getPathsFrom(rootType);
		if (pathList.isEmpty()) {
			String msg = "There is no path configuration for " + searchedType.getName() + " from root class=>" + searchedType.getName();
			LOGGER.error(msg);
			throw new DataPathException(msg);
		}
		String pathArray[] = pathList.toArray(new String[pathList.size()]);
		extractor.consumeByPath(rootObject, rootType, searchedType, consumer, pathArray);
		if (LOGGER.isDebugEnabled()) LOGGER.debug("Done DataPathIndexScannerImpl.scanObjects(rootObject," + rootType.getName() + "," + searchedType.getName() + ",consumer) successfully!");
	}

}
