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
package com.openi40.platform.persistence.input.channel;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.openi40.scheduler.input.model.InputDto;

public class JpaStreamLoader<InputDtoType extends InputDto, JpaType extends InputDto>
		implements InputChannelStreamProvider<InputDtoType> {
	String orderClause = null;
	Class<JpaType> jpaType = null;
	Class<InputDtoType> dtoType = null;
	EntityManager entityManager = null;

	protected JpaStreamLoader(Class<JpaType> jpaType, Class<InputDtoType> dtoType, EntityManager entityManager,
			String orderClause) {
		this.orderClause = orderClause;
		this.jpaType = jpaType;
		this.dtoType = dtoType;
		this.entityManager = entityManager;

	}

	private class RelationEntry {
		Method setMethod = null;
		StreamLoadRelated annotation = null;
	}

	private List<JpaType> resolveRelated(List<JpaType> list, boolean useDBStreaming, int batchingSize) {
		if (list != null && !list.isEmpty()) {
			List<String> codes = new ArrayList<>();
			Map<String, JpaType> objectsMap = new HashMap<>();
			list.forEach(entry -> {
				codes.add(entry.getCode());
				objectsMap.put(entry.getCode(), entry);
			});

			List<RelationEntry> relations = scanRelations();
			for (RelationEntry relation : relations) {
				String _orderBy = null;
				if (relation.annotation.orderOptions() != null) {
					for (String _o : relation.annotation.orderOptions()) {
						if (_orderBy != null) {
							_orderBy += "," + _o;
						} else
							_orderBy = _o;
					}
				}
				JpaStreamLoader streamLoader = new JpaStreamLoader<>(relation.annotation.loadType(),
						relation.annotation.overriddenType(), entityManager, _orderBy);
				String query = "select t from " + relation.annotation.loadType().getSimpleName() + " t where t."
						+ relation.annotation.joinProperty() + " in ?1 "
						+ (_orderBy != null ? " order by " + _orderBy : "");
				Object params[] = { codes };
				Stream<? extends InputDto> relatedStream = streamLoader.streamAndProcessQueryWithParams(query, false,
						batchingSize, params);

				try {
					BeanInfo beanInfo = Introspector.getBeanInfo(relation.annotation.loadType());
					PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
					if (pds != null) {
						for (PropertyDescriptor pd : pds) {
							if (pd.getName().equals(relation.annotation.joinProperty())) {
								final Method readMethod = pd.getReadMethod();
								Collector<Object, ?, Map<Object, List<Object>>> collector = Collectors
										.groupingBy(entry -> {
											try {
												Object key = readMethod.invoke(entry);
												return key;
											} catch (IllegalAccessException | IllegalArgumentException
													| InvocationTargetException e) {

												e.printStackTrace();
												return null;
											}

										});
								Map<Object, List<Object>> map = relatedStream.collect(collector);
								map.forEach((k, lst) -> {
									if (objectsMap.containsKey(k)) {
										JpaType entry = objectsMap.get(k);
										try {
											relation.setMethod.invoke(entry, lst);
										} catch (IllegalAccessException | IllegalArgumentException
												| InvocationTargetException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								});

							}
						}
					}
				} catch (IntrospectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		return list;
	}

	private List<RelationEntry> scanRelations() {
		List<RelationEntry> relations = new ArrayList<>();
		BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(jpaType);
			PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
			if (properties != null) {
				for (PropertyDescriptor property : properties) {
					if (property.getWriteMethod() != null) {
						// StreamLoadRelated[] related = property.getWriteMethod()
						// .getAnnotationsByType(StreamLoadRelated.class);

						List<StreamLoadRelated> annotations = new ArrayList<>();
						StreamLoadRelated related = property.getWriteMethod().getAnnotation(StreamLoadRelated.class);
						StreamLoads multiple = property.getWriteMethod().getAnnotation(StreamLoads.class);
						if (related != null)
							annotations.add(related);
						if (multiple != null && multiple.relateds() != null) {
							for (StreamLoadRelated r : multiple.relateds()) {
								annotations.add(r);
							}
						}
						for (StreamLoadRelated rel : annotations) {
							RelationEntry entry = new RelationEntry();
							entry.annotation = rel;
							entry.setMethod = property.getWriteMethod();
							relations.add(entry);
						}

					}
				}
			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return relations;
	}

	public Stream<InputDtoType> streamAll(boolean useDBStreaming, int batchingSize) {
		String query = "select t from " + jpaType.getSimpleName() + " t "
				+ (orderClause != null ? " order by " + orderClause : "");
		Stream<JpaType> stream = this.streamQueryWithParams(useDBStreaming, query);
		return processStream(useDBStreaming, batchingSize, stream);
	}

	public Stream<InputDtoType> streamModifiedAfter(Timestamp ts, boolean useDBStreaming, int batchingSize) {
		String query = "select t from " + jpaType.getSimpleName() + " t where t.modifiedTimestamp>:1"
				+ (orderClause != null ? " order by " + orderClause : "");
		return streamAndProcessQueryWithParams(query, useDBStreaming, batchingSize, ts);
	}

	private Stream<InputDtoType> processStream(boolean useDBStreaming, int batchingSize, Stream<JpaType> stream) {
		return StreamUtils.batchProcessStream(batchingSize, stream, j -> (InputDtoType) j,
				list -> this.resolveRelated(list, useDBStreaming, batchingSize));
	}

	private Stream<JpaType> streamQueryWithParams(boolean useDBStreaming, String query, Object... params) {
		TypedQuery<JpaType> queryObject = entityManager.createQuery(query, jpaType);

		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				queryObject.setParameter(i + 1, params[i]);
			}
		}
		Stream<JpaType> stream = useDBStreaming ? queryObject.getResultStream() : queryObject.getResultList().stream();
		return stream;
	}

	protected Stream<InputDtoType> streamAndProcessQueryWithParams(String query, boolean useDBStreaming,
			int batchingSize, Object... params) {
		Stream<JpaType> stream = streamQueryWithParams(useDBStreaming, query, params);
		return processStream(useDBStreaming, batchingSize, stream);
	}
}
