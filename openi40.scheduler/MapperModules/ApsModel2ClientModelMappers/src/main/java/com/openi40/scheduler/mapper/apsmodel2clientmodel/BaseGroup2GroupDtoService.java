package com.openi40.scheduler.mapper.apsmodel2clientmodel;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.openi40.scheduler.client.model.companystructure.AbstractGroupDto;
import com.openi40.scheduler.engine.contextualplugarch.ILazyContextualBusinessLogicFactoryLoader;
import com.openi40.scheduler.mapper.AbstractReflectorMapper;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.equipment.Group;
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
public class BaseGroup2GroupDtoService<GType extends Group,GDtoType extends AbstractGroupDto> extends AbstractReflectorMapper<GType,GDtoType>
		implements IMapper<GType,GDtoType> {
	protected ILazyContextualBusinessLogicFactoryLoader lcbFLoader=null;

	public BaseGroup2GroupDtoService(@Autowired AutowireCapableBeanFactory beanFactory,Class<GType> gType,Class<GDtoType> gDtoType,ILazyContextualBusinessLogicFactoryLoader lcbFLoader ) {
		super(beanFactory, gType, gDtoType, ApsModel2ClientModelConfiguration.typeMap);
		this.lcbFLoader=lcbFLoader;
	}
	@Override
	public void copyValue(GType originalObject, GDtoType targetObject, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		
	}
	
}