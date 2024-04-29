package com.openi40.scheduler.outputchannels.dataexporters;

import java.util.HashMap;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.openi40.scheduler.common.aps.IApsData;
import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.mapper.DefaultEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IApsDataModelDao;
import com.openi40.scheduler.output.model.OutputDto;
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
public class AbstractDataExporterFactory<ApsDataType extends IApsObject, ExportedDataType extends OutputDto, DaoType extends IApsDataModelDao<ApsDataType>>
		implements IDataExporterFactory<ApsDataType, ExportedDataType> {

	private Class<ApsDataType> apsDataType;
	private Class<ExportedDataType> exportedDataType;
	private IMapperFactory mapperFactory = null;
	private DaoType dao;

	protected AbstractDataExporterFactory(Class<ApsDataType> apsDataType, Class<ExportedDataType> exportedDataType,
			DaoType dao, IMapperFactory mapperFactory) {
		this.apsDataType = apsDataType;
		this.exportedDataType = exportedDataType;
		this.dao = dao;
		this.mapperFactory = mapperFactory;
	}

	protected class ExporterSpliterator extends Spliterators.AbstractSpliterator<ExportedDataType> {
		protected ExporterSpliterator() {
			super(Long.MAX_VALUE, 0);
		}

		@Override
		public boolean tryAdvance(Consumer<? super ExportedDataType> action) {

			return false;
		}
	};

	@Override
	public Stream<ExportedDataType> create(ApsData data) throws DataModelDaoException, MapperException {
		IMapper<ApsDataType, ExportedDataType> mapper = this.mapperFactory.createMapper(apsDataType, exportedDataType);
		HashMap environment = new HashMap<>();
		return dao.streamAll(data).map((originalType) -> {
			try {
				return mapper.mapInstance(originalType, exportedDataType, DefaultEntitiesFactory.Instance, environment,
						true);
			} catch (MapperException e) {
				throw new RuntimeException("cannot map from " + apsDataType + " to " + exportedDataType, e);
			}
		});

	}

	@Override
	public Class<ApsDataType> getApsDataType() {

		return this.apsDataType;
	}

	@Override
	public Class<ExportedDataType> getExportedType() {
		return this.exportedDataType;
	}

}
