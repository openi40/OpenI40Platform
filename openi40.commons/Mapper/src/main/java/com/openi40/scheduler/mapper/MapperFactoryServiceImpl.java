package com.openi40.scheduler.mapper;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
@Lazy
public class MapperFactoryServiceImpl implements IMapperFactory {
	static Logger LOGGER = LoggerFactory.getLogger(MapperFactoryServiceImpl.class);
	List<IMapper<?, ?>> mapperServices = null;
	DefaultMapper defaultMapper;

	public MapperFactoryServiceImpl(@Autowired List<IMapper<?, ?>> mapperServices,
			@Autowired DefaultMapper defaultMapper) {
		this.mapperServices = mapperServices;
		this.defaultMapper = defaultMapper;
	}

	@PostConstruct
	public void postConstruct() {
		LOGGER.info("MapperService started up!");
		if (mapperServices != null) {
			DefaultMapper defaultMapperBinded = null;
			for (IMapper<?, ?> iMapper : mapperServices) {
				if (iMapper instanceof DefaultMapper) {
					defaultMapperBinded = (DefaultMapper) iMapper;
				} else
					LOGGER.info("Retrieved Mapper " + iMapper.getClass().getSimpleName() + " from "
							+ iMapper.getOriginalType() + " to " + iMapper.getTargetType());
			}
			if (defaultMapperBinded != null)
				mapperServices.remove(defaultMapperBinded);
		}
		LOGGER.info("MapperService ready to map!");
	}

	@Override
	public <OriginalType, TargetType> IMapper<OriginalType, TargetType> createMapper(Class<OriginalType> originalType,
			Class<TargetType> targetType) throws MapperException {
		Class generalOriginalType = originalType;
		Class generalTargetType = targetType;
		if (mapperServices != null) {
			// Search first both mapping types
			for (IMapper<?, ?> iMapper : mapperServices) {
				if (iMapper.getOriginalType().equals(originalType) && iMapper.getTargetType().equals(targetType)) {
					if (iMapper.isAbleToMap(generalOriginalType, generalTargetType))
						return (IMapper<OriginalType, TargetType>) iMapper;
				}
			}
			for (IMapper<?, ?> iMapper : mapperServices) {
				if (iMapper.getOriginalType().isAssignableFrom(originalType)
						&& iMapper.getTargetType().isAssignableFrom(targetType)) {
					if (iMapper.isAbleToMap(generalOriginalType, generalTargetType))
						return (IMapper<OriginalType, TargetType>) iMapper;
				}
			}
		}
		if (defaultMapper != null)
			return (IMapper<OriginalType, TargetType>) defaultMapper;
		throw new MapperException("Mapper not found for " + originalType + " to " + targetType);
	}

}
