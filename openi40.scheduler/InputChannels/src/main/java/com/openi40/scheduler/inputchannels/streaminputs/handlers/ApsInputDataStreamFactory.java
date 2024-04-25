package com.openi40.scheduler.inputchannels.streaminputs.handlers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.openi40.scheduler.input.model.ApsInputData;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.inputchannels.streaminputs.IInputDataStreamFactory;
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
public class ApsInputDataStreamFactory implements IInputDataStreamFactory {
	protected ApsInputData inputData = null;
	protected Map<Class, Method> perTypeListAccessor = new HashMap();
	protected boolean realtime = false;
	protected boolean canBeCached=true;
	protected boolean productionControlEnabled = false;
	public ApsInputDataStreamFactory(ApsInputData inputData) {
		this.inputData = inputData;
		this.initializePerTypeMap();
	}

	private void initializePerTypeMap() {
		Method[] methods = this.inputData.getClass().getMethods();
		for (Method method : methods) {
			if (method.getName().startsWith("get")
					&& (Collection.class.isAssignableFrom(method.getReturnType())
							|| InputDto.class.isAssignableFrom(method.getReturnType()))
					&& method.getParameterCount() == 0) {
				if (InputDto.class.isAssignableFrom(method.getReturnType())) {
					this.perTypeListAccessor.put(method.getReturnType(), method);
				} else {
					Type type = method.getGenericReturnType();
					if (type instanceof ParameterizedType) {
						ParameterizedType pt = (ParameterizedType) type;
						Type[] arguments = pt.getActualTypeArguments();
						if (arguments != null && arguments.length == 1) {
							Class _class = (Class) arguments[0];
							this.perTypeListAccessor.put(_class, method);
						}
					}
				}
			}
		}
	}

	@Override
	public String getDataSourceName() {

		return "ApsInputDataCache";
	}

	@Override
	public String getDataSetName() {

		return inputData.getDataSetName();
	}

	@Override
	public String getDataSetVariant() {

		return inputData.getDataSetVariant();
	}

	@Override
	public <DtoEntityType extends InputDto> Stream<DtoEntityType> getStream(
			Class<DtoEntityType> requiredType) {
		try {
			if (this.perTypeListAccessor.containsKey(requiredType)) {
				Method getMethod = this.perTypeListAccessor.get(requiredType);
				if (Collection.class.isAssignableFrom(getMethod.getReturnType())) {

					Collection<DtoEntityType> _list = (Collection<DtoEntityType>) getMethod.invoke(this.inputData);
					return _list.stream();

				} else {
					DtoEntityType entry = (DtoEntityType) getMethod.invoke(this.inputData);
					List<DtoEntityType> rvalues = new ArrayList();
					if (entry != null)
						rvalues.add(entry);
					return rvalues.stream();
				}
			} else
				throw new IllegalStateException(
						"Data type " + requiredType.getName() + " is not managed on ApsInputData container");
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException("impossible to call data structure accessor for ApsInputData", e);
		}
	}

	@Override
	public <DtoEntityType extends InputDto> Stream<DtoEntityType> getStream(Class<DtoEntityType> requiredType,
			Date modifiedAfter) {
		Stream<DtoEntityType> stream = this.getStream(requiredType);
		Predicate<? super DtoEntityType> predicate = (x) -> {
			return x != null && (x.getModifiedTimestamp() == null || x.getModifiedTimestamp().after(modifiedAfter));
		};

		return stream.filter(predicate);
	}

	@Override
	public String getDataSourceDescription() {

		return this.inputData.getDescription();
	}

	public boolean isRealtime() {
		return realtime;
	}

	public void setRealtime(boolean realtime) {
		this.realtime = realtime;
	}

	public boolean isProductionControlEnabled() {
		return productionControlEnabled;
	}

	public void setProductionControlEnabled(boolean productionControlEnabled) {
		this.productionControlEnabled = productionControlEnabled;
	}

	public boolean isCanBeCached() {
		return canBeCached;
	}

	public void setCanBeCached(boolean canBeCached) {
		this.canBeCached = canBeCached;
	}

}
