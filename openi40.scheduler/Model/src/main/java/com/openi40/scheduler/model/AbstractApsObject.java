package com.openi40.scheduler.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.common.aps.ICloneable;
import com.openi40.scheduler.model.aps.ApsData;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
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
@Data
public abstract class AbstractApsObject implements IHyerarchyAwareNode, ICloneable {
	protected String id;
	protected String description;
	protected String code;
	protected String owner;
	protected boolean simulatedItem;
	protected long uniqueId = 0l;
	static long idxCtrl = 1l;
	private Date modifiedTimestamp = null;
	private boolean locked = false;
	protected Hashtable associative = new Hashtable();
	private ApsData Context;

	public AbstractApsObject(ApsData context) {
		id = UUID.randomUUID().toString();
		this.setContext(context);
		uniqueId = idxCtrl;
		idxCtrl++;

	}

	public Object getAttribute(String name) {
		return associative.get(name);
	}

	public void setAttribute(String name, Object value) {
		associative.put(name, value);
	}

	public void resetSchedulingData() {

	}

	@Override
	public String toString() {
		return this.getClass().getName() + "{id=" + id + ",code=" + code + "}";
	}

	public ICloneable cleanClone() throws CloneNotSupportedException {
		throw new UnsupportedOperationException();
	}

	public class TreeTraversalException extends Exception {
		public TreeTraversalException(String s, Throwable t) {
			super(s, t);
		}
	}

	public void consumeTree(Consumer consumer, boolean recursive) throws TreeTraversalException {

		this.consumeSubTree(consumer, recursive, new HashMap<>());
	}

	protected void consumeSubTree(Consumer consumer, boolean recursive, HashMap<Object, List<Object>> alreadyVisited)
			throws TreeTraversalException {
		List list = alreadyVisited.get(this);
		boolean avoidTheNavigation = list != null && list.contains(this);
		if (!avoidTheNavigation) {
			if (list == null)
				alreadyVisited.put(this, list = new ArrayList<>());
			list.add(this);
		}
		if (!avoidTheNavigation) {
			consumer.accept(this);
			if (recursive) {
				Method methods[] = getClass().getMethods();
				for (Method method : methods) {
					if (Modifier.isPublic(method.getModifiers()) && method.getName().startsWith("get")
							&& method.getParameterCount() == 0
							&& (AbstractApsObject.class.isAssignableFrom(method.getReturnType())
									|| Collection.class.isAssignableFrom(method.getReturnType()))) {
						try {
							Object retValue = method.invoke(this);
							if (retValue == null)
								continue;

							if (retValue instanceof AbstractApsObject) {
								AbstractApsObject r = (AbstractApsObject) retValue;
								r.consumeSubTree(consumer, recursive, alreadyVisited);
							}
							if (retValue instanceof Collection) {
								Collection collection = (Collection) retValue;
								for (Object instances : collection) {
									if (instances instanceof AbstractApsObject) {
										AbstractApsObject r = (AbstractApsObject) instances;
										r.consumeSubTree(consumer, recursive, alreadyVisited);
									}
								}
							}
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							throw new TreeTraversalException("Cannot traverse method " + method, e);
						}
					}
				}
			}
		}
	}

	protected static <ParentType extends AbstractApsObject, ChildType extends IApsObject> IHyerarchyAwareList<ParentType, ChildType> createCleanChild(
			ParentType parent, String relationName, Class<ChildType> childType) {
		HyerarchyAwareList<ParentType, ChildType> rvalue = new HyerarchyAwareList<ParentType, ChildType>(
				(Class<ParentType>) parent.getClass(), childType, parent, relationName);
		parent.hyerarchyAwareLists
				.removeIf((IHyerarchyAwareList<? extends IHyerarchyAwareNode, ? extends IApsObject> value) -> {
					return (relationName != null && relationName.equals(value.getRelationName()));
				});
		parent.hyerarchyAwareLists.add(rvalue);
		return rvalue;
	}

	@Setter(value = AccessLevel.NONE)
	protected List<IHyerarchyAwareList<? extends IHyerarchyAwareNode, ? extends IApsObject>> hyerarchyAwareLists = new ArrayList<IHyerarchyAwareList<? extends IHyerarchyAwareNode, ? extends IApsObject>>();

	protected void finalize() throws Throwable {
		id = null;
		code = null;
		setContext(null);
		if (hyerarchyAwareLists != null) {
			for (IHyerarchyAwareList<? extends IApsObject, ? extends IApsObject> iHyerarchyAwareList : hyerarchyAwareLists) {
				iHyerarchyAwareList.clear();
			}
			hyerarchyAwareLists.clear();
		}
		hyerarchyAwareLists = null;
		associative = null;
		description = null;
	}
}