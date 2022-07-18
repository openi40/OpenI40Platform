package com.openi40.scheduler.common.datapath;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
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
@Service
@Scope("singleton")
public class DataPathExtractorImpl implements IDataPathExtractor {
	static Logger LOGGER = LoggerFactory.getLogger(DataPathExtractorImpl.class);

	public DataPathExtractorImpl() {

	}

	@Override
	public <ObjectType> List<ObjectType> readByPath(Object rootObject, Class rootType, Class<ObjectType> searchedTypes, String... dataPaths) throws DataPathException {
		List<ObjectType> outValues = new ArrayList<ObjectType>();
		class ThisConsumer implements Consumer<ObjectType> {

			@Override
			public void accept(ObjectType t) {
				outValues.add(t);
			}
		}
		ThisConsumer consumer = new ThisConsumer();
		consumeByPath(rootObject, rootType, searchedTypes, consumer, dataPaths);
		return outValues;
	}

	class PathCacheItem {
		String pathPart = null;

		Method edgeMethod = null;
		Class nodeType = null;
		Class pathResultType = null;
		PathCache pathCache = null;
		PathCacheItem parentNode = null;
		boolean discoverTypesRuntime = false;

		boolean isRoot() {
			return pathPart.startsWith("/") && parentNode == null;
		}
	};

	class PathCache extends TreeMap<String, PathCacheItem> {
		Class startType = null;
		PathCacheItem parentNode = null;

		PathCache(Class _root) {
			this.startType = _root;
		}

		String getNodeTag() {
			return startType.getSimpleName();
		}
	}

	Map<Class, PathCache> pathSegments = new HashMap<>();

	PathCache fullPathsExplore(Class actualType) throws DataPathException {
		PathCacheItem item = new PathCacheItem();
		item.nodeType = actualType;
		item.pathPart = actualType.getSimpleName();
		PathCache cache = new PathCache(actualType);
		cache.parentNode = item;
		item.pathCache = cache;
		Method methods[] = actualType.getMethods();
		for (Method method : methods) {
			if (Modifier.isPublic(method.getModifiers()) && method.getName().startsWith("get") && method.getParameterCount() == 0
					&& !(method.getReturnType().getName().startsWith("java.lang.") || method.getReturnType().getName().startsWith("java.sql.") || method.getReturnType().getName().startsWith("java.time.") || Date.class.isAssignableFrom(method.getReturnType()))) {
				String propertyName = method.getName().substring("get".length());
				Class propertyType = method.getReturnType();
				PathCacheItem cacheItem = new PathCacheItem();
				cacheItem.edgeMethod = method;
				cacheItem.nodeType = actualType;
				cacheItem.pathPart = propertyName;
				if (!Collection.class.isAssignableFrom(propertyType)) {
					cacheItem.pathResultType = propertyType;
				}
				cache.put(propertyName, cacheItem);
			}
		}
		return cache;
	}

	List<PathCacheItem> preparePath(String path, Class rootType, Class searchedType) throws DataPathException {
		if (LOGGER.isDebugEnabled()) LOGGER.debug("preparePath('" + path + "'," + (rootType != null ? rootType.getName() : "unknown") + "," + searchedType.getName());
		List<PathCacheItem> outvalues = new ArrayList<>();
		PathCacheItem thisNode = null;
		path = path.replace("\\", "/");
		if (path.endsWith("/")) {
			path = path.substring(0, path.length() - 1);
		}
		if (path.startsWith("/")) {
			path = path.substring(1);
		}
		String _thisPathPart = path;
		String _remainingPath = null;
		int _idx = path.indexOf("/");
		if (_idx > 0) {
			_thisPathPart = path.substring(0, _idx);
			_remainingPath = path.substring(_idx + 1);
		}

		if (rootType == null) {
			// Situation where the type is unknown (collection child)
			StringTokenizer tokenizer = new StringTokenizer(path, "/");
			if (LOGGER.isDebugEnabled()) LOGGER.debug("Remaining path: " + path);
			while (tokenizer.hasMoreTokens()) {
				PathCacheItem item = new PathCacheItem();
				item.discoverTypesRuntime = true;
				item.pathPart = tokenizer.nextToken();
				outvalues.add(item);
			}
		} else if (!rootType.equals(searchedType)) {

			PathCache map = pathSegments.get(rootType);
			if (map == null) {
				map = fullPathsExplore(rootType);
				pathSegments.put(rootType, map);
			}

			if (!_thisPathPart.equals(map.getNodeTag()))
				throw new DataPathException("First part of path " + path + " is not coherent with root type " + rootType.getSimpleName());
			outvalues.add(map.parentNode);
			if (_remainingPath != null) {
				_idx = _remainingPath.indexOf("/");
				if (_idx > 0) {
					_thisPathPart = _remainingPath.substring(0, _idx);
					_remainingPath = _remainingPath.substring(_idx + 1);
				} else {
					_thisPathPart = _remainingPath;
					_remainingPath = null;
				}
				if (map.containsKey(_thisPathPart)) {
					thisNode = map.get(_thisPathPart);
					outvalues.add(thisNode);
					Class nextType = thisNode.pathResultType;
					if (_remainingPath != null) {
						outvalues.addAll(preparePath(_remainingPath, nextType, searchedType));
					}
				}
			} else
				throw new DataPathException("Missing property edge part to express path, you entered:" + path + " but it points to a type and not a property to extract object(s)");

		}

		return outvalues;
	}

	Class findMatchingSimpleName(Class type, String simpleName) {
		if (type.getSimpleName().equals(simpleName))
			return type;
		Class _ifaces[] = type.getInterfaces();
		if (_ifaces != null) {
			for (Class _iface : _ifaces) {
				Class _type = findMatchingSimpleName(_iface, simpleName);
				if (_type != null)
					return _type;
			}
		}
		Class sc = type.getSuperclass();
		if (sc != null)
			return findMatchingSimpleName(sc, simpleName);
		return null;
	}

	<ObjectType> void applyPath(Object rootObject, Class rootType, Class<ObjectType> searchedTypes, List<PathCacheItem> path, Consumer<ObjectType> consumer) throws DataPathException {
		if (path.isEmpty())
			return;
		if (path.size() < 2)
			throw new DataPathException("Incoherency between rootType=" + rootType + " and path length: " + path.size());
		PathCacheItem item = path.get(0);
		PathCacheItem estractor = path.get(1);
		if (rootType != null && item.nodeType != null && !item.nodeType.equals(rootType))
			throw new DataPathException("Incoherency between rootType=" + rootType + " and path step: " + item.nodeType);
		if (item.discoverTypesRuntime) {
			Class thisType = findMatchingSimpleName(rootType, item.pathPart);
			if (thisType == null)
				throw new DataPathException("Simple name of hierarchy of " + rootType + " does not match " + item.pathPart);
			item.nodeType = thisType;
			item.pathCache = pathSegments.get(thisType);
			if (item.pathCache == null) {
				item.pathCache = fullPathsExplore(thisType);
				pathSegments.put(thisType, item.pathCache);
			}
			rootType = thisType;
			PathCacheItem es = item.pathCache.get(estractor.pathPart);
			if (es == null)
				throw new DataPathException("Wrong path parte:" + estractor.pathPart + " for target type " + searchedTypes);
			estractor = es;
		}
		List<PathCacheItem> remainingPath = path.size() > 2 ? path.subList(2, path.size()) : new ArrayList<>();
		try {
			if (estractor.edgeMethod == null)
				throw new DataPathException("Wrong state estractor without edgeMethod for " + estractor.pathPart);
			Object value = estractor.edgeMethod.invoke(rootObject);
			if (value == null)
				return;
			Collection collectionValues = null;
			if (value instanceof Collection) {
				collectionValues = (Collection) value;
			} else {
				collectionValues = new ArrayList<>();
				collectionValues.add(value);
			}
			for (Object object : collectionValues) {
				if (remainingPath.isEmpty()) {
					if (searchedTypes.isAssignableFrom(object.getClass())) {
						ObjectType _object = (ObjectType) object;
						consumer.accept(_object);
					}
				} else {
					Class _stepType = estractor.pathResultType != null ? estractor.pathResultType : object.getClass();
					this.<ObjectType>applyPath(object, _stepType, searchedTypes, remainingPath, consumer);
				}
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new DataPathException("Cannot use edgeMethod:" + estractor.edgeMethod, e);
		}

	}

	@Override
	public <ObjectType> void consumeByPath(Object rootObject, Class rootType, Class<ObjectType> searchedTypes, Consumer<ObjectType> consumer, String... dataPaths) throws DataPathException {
		if (dataPaths == null)
			throw new IllegalArgumentException("passed datapath array is null");
		List<List<PathCacheItem>> paths = new ArrayList<>();
		for (String dataPath : dataPaths) {
			paths.add(preparePath(dataPath, rootType, searchedTypes));
		}
		for (List<PathCacheItem> pathCacheItems : paths) {
			applyPath(rootObject, rootType, searchedTypes, pathCacheItems, consumer);
		}
	}

}
