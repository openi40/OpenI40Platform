package com.openi40.scheduler.mapper.input2ignitebinary;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ignite.Ignite;
import org.apache.ignite.binary.BinaryObjectBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.mapper.input2ignitebinary.MetaDescribeTree.CollectionAttributeDescriptor;
import com.openi40.scheduler.mapper.input2ignitebinary.MetaDescribeTree.MetaPropertyDescriptor;

public class AbstractBinaryObjectBuilder2InputDto<InputType extends InputDto>
		implements IMapper<BinaryObjectBuilder, InputType> {

	private Ignite ignite;
	private Class<InputType> iType;
	private MetaDescribeTree<InputType, InputDto> typeTree = null;

	public AbstractBinaryObjectBuilder2InputDto(@Autowired Ignite ignite, Class<InputType> iType)
			throws IntrospectionException {
		this.ignite = ignite;
		this.iType = iType;
		this.typeTree = MetaDescribeTree.describe(iType, InputDto.class);
	}

	private <T extends InputDto> void readFields(BinaryObjectBuilder originalObject, T targetObject,
			MetaDescribeTree<T, InputDto> metaTypeTree) throws MapperException {
		try {
			for (MetaPropertyDescriptor mpd : metaTypeTree.properties) {
				if (mpd.isNestedDescribeableObject) {
					InputDto value;
					BinaryObjectBuilder readValue = originalObject.<BinaryObjectBuilder>getField(mpd.name);
					if (readValue != null) {

						value = (InputDto) mpd.metaDescribeTree.describedType.newInstance();

						readFields(readValue, value, mpd.metaDescribeTree);
					}
				} else {
					Object value = originalObject.getField(mpd.name);
					mpd.writeMethod.invoke(targetObject, value);
				}
			}
			for (CollectionAttributeDescriptor mpd : metaTypeTree.collections) {
				// TODO: LEGGERE LE COLLEZIONI DI DATI
			}
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new MapperException("exception in readFields", e);
		}
	}

	@Override
	public InputType mapInstance(BinaryObjectBuilder object, Class<InputType> targetType,
			IEntitiesFactory entityFactory, Map environment, boolean recursive) throws MapperException {
		InputType in = null;
		if (object != null) {
			try {
				in = targetType.newInstance();
				this.copyValue(object, in, entityFactory, environment, recursive);
			} catch (InstantiationException | IllegalAccessException e) {
				throw new MapperException("exception in mapInstance", e);
			}
		}
		return in;
	}

	@Override
	public void copyValue(BinaryObjectBuilder originalObject, InputType targetObject, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {
		readFields(originalObject, targetObject, typeTree);
	}

	@Override
	public boolean isAbleToMap(Class<BinaryObjectBuilder> originalType, Class<InputType> targetType) {

		return true;
	}

	@Override
	public Class<BinaryObjectBuilder> getOriginalType() {

		return BinaryObjectBuilder.class;
	}

	@Override
	public Class<InputType> getTargetType() {

		return iType;
	}

}
