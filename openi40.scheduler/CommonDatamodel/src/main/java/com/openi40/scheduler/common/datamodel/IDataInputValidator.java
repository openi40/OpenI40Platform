package com.openi40.scheduler.common.datamodel;

import java.util.List;
import java.util.function.Function;
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
public interface IDataInputValidator {
	public <DataComplexType, DataBaseType> List<ValidationMessage> validate(DataComplexType data,
			Class<DataComplexType> dataComplexType, Class<DataBaseType> dataBaseType, Function<DataBaseType, String> idExtractor);
}
