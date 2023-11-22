package com.openi40.scheduler.mapper.input2ignitebinary;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ignite.Ignite;
import org.apache.ignite.binary.BinaryObject;
import org.apache.ignite.binary.BinaryObjectBuilder;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.springframework.beans.factory.annotation.Autowired;

import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.mapper.input2ignitebinary.MetaDescribeTree.CollectionAttributeDescriptor;
import com.openi40.scheduler.mapper.input2ignitebinary.MetaDescribeTree.MetaPropertyDescriptor;

public  class AbstractInputDto2IgniteBinaryObjectBuilder<InputType extends InputDto>
		implements IMapper<InputType, BinaryObjectBuilder> {
	Ignite ignite = null;
	Class<InputType> iType = null;
	MetaDescribeTree<InputType, InputDto> typeTree = null;

	public AbstractInputDto2IgniteBinaryObjectBuilder(@Autowired Ignite ignite, Class<InputType> iType)
			throws IntrospectionException {
		this.ignite = ignite;
		this.iType = iType;
		this.typeTree = MetaDescribeTree.describe(iType, InputDto.class);
	}

	@Override
	public BinaryObjectBuilder mapInstance(InputType object, Class<BinaryObjectBuilder> targetType,
			IEntitiesFactory entityFactory, Map environment, boolean recursive) throws MapperException {
		BinaryObjectBuilder builder = ignite.binary().builder(object.getClass().getName());
		this.copyValue(object, builder, entityFactory, environment, recursive);
		return builder;
	}
	private  <T extends InputDto> void writeFields(T originalObject, BinaryObjectBuilder targetObject,MetaDescribeTree<T, InputDto> metaTypeTree) throws MapperException {
		for (MetaPropertyDescriptor mpd : metaTypeTree.properties) {
			Object value=null;
			try {
				value = mpd.readMethod.invoke(originalObject);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new MapperException("Error in copyValue for type=>"+iType.getName(), e);
			}
			if (value != null) {
				if (mpd.isNestedDescribeableObject) {
					BinaryObjectBuilder builder = ignite.binary().builder(mpd.type.getName());
					writeFields((InputDto)value, builder, mpd.metaDescribeTree);
					targetObject.setField(mpd.name, builder);					
				} else {
					targetObject.setField(mpd.name, value);
				}
			}
		}
		for (CollectionAttributeDescriptor mpd : metaTypeTree.collections) {
			Object value=null;
			try {
				value = mpd.readMethod.invoke(originalObject);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new MapperException("Error in copyValue for type=>"+iType.getName(), e);
			}
			if (value != null) {
				if (mpd.isNestedDescribeableObject) {
					List<BinaryObjectBuilder> builders=new ArrayList<>();
					Collection collection=(Collection) value;
					for(Object v:collection) {
						BinaryObjectBuilder builder = ignite.binary().builder(mpd.elementType.getName());
						writeFields((InputDto)v, builder, mpd.metaDescribeTree);
						builders.add(builder);
					}	
					targetObject.setField(mpd.name, builders);
				} else {
					targetObject.setField(mpd.name, value);
				}
			}
		}
	}
	@Override
	public void copyValue(InputType originalObject, BinaryObjectBuilder targetObject, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {
		writeFields(originalObject, targetObject, this.typeTree);

	}

	@Override
	public boolean isAbleToMap(Class<InputType> originalType, Class<BinaryObjectBuilder> targetType) {

		return true;
	}

	@Override
	public Class<InputType> getOriginalType() {

		return iType;
	}

	@Override
	public Class<BinaryObjectBuilder> getTargetType() {

		return BinaryObjectBuilder.class;
	}

}
