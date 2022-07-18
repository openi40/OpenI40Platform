package com.openi40.scheduler.model.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.common.datapath.DataPathException;
import com.openi40.scheduler.common.datapath.IDataPathIndexScanner;
import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.AbstractApsObject.TreeTraversalException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.ICompanyStructureNode;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.DataModelSnapshotCache;
import com.openi40.scheduler.model.dao.IApsDataModelDao;
import com.openi40.scheduler.model.dao.ISelector;
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
public abstract class AbstractApsDataModelDao<ApsType extends IApsObject> implements IApsDataModelDao<ApsType> {
	Class<ApsType> searchedType = null;
	@Autowired
	IDataPathIndexScanner dataPathIndexScanner;

	protected class ConsumerFilter implements Consumer {
		ConsumerFilter(Consumer<ApsType> cons) {
			this.pluggedConsumer = cons;
		}

		Consumer<ApsType> pluggedConsumer = null;

		@Override
		public void accept(Object t) {
			if (t != null && searchedType.isAssignableFrom(t.getClass()) && pluggedConsumer != null) {
				pluggedConsumer.accept((ApsType) t);
			}

		}

	}

	class SelectorByCode implements ISelector<ApsType> {
		String code = null;

		SelectorByCode(String c) {
			this.code = c;
		}

		@Override
		public boolean isSelectable(ApsType t) {

			return t != null && t.getCode() != null ? t.getCode().equals(code) : false;
		}

	}

	class SelectorFilteringConsumer implements Consumer<ApsType> {
		ISelector<ApsType> selector = null;
		List<ApsType> selected = new ArrayList<>();

		@Override
		public void accept(ApsType arg0) {
			if (selector == null || selector.isSelectable(arg0)) {
				selected.add(arg0);
			}

		}

	};

	class SelectorById implements ISelector<ApsType> {
		String id = null;

		SelectorById(String c) {
			this.id = c;
		}

		@Override
		public boolean isSelectable(ApsType t) {

			return t != null && t.getId() != null ? t.getId().equals(id) : false;
		}

	}

	DataPathCfg dataPathConfig = null;

	protected AbstractApsDataModelDao(Class<ApsType> searchedType, DataPathCfg dataPathConfig) {
		this.searchedType = searchedType;
		this.dataPathConfig = dataPathConfig;
	}

	protected ApsType fullTreeFindByCode(String code, AbstractApsObject context) throws TreeTraversalException {
		ISelector<ApsType> selector = new ISelector<ApsType>() {
			@Override
			public boolean isSelectable(ApsType t) {
				if (t.getCode() != null && t.getCode().equals(code))
					return true;
				return false;
			}
		};
		List<ApsType> found = fullTreeFindBySelector(selector, context);
		if (found != null && found.size() > 1)
			throw new IllegalStateException("Two objects with the same code! " + code + " " + found);
		return found != null && !found.isEmpty() ? found.get(0) : null;
	}

	protected ApsType fullTreeFindById(String id, AbstractApsObject context) throws TreeTraversalException {
		ISelector<ApsType> selector = new ISelector<ApsType>() {

			@Override
			public boolean isSelectable(ApsType t) {
				if (t.getId() != null && t.getId().equals(id))
					return true;
				return false;
			}
		};
		List<ApsType> found = fullTreeFindBySelector(selector, context);
		if (found != null && found.size() > 1)
			throw new IllegalStateException("Two objects with the same id! " + id + " " + found);
		return found != null && !found.isEmpty() ? found.get(0) : null;
	}

	protected List<ApsType> fullTreeFindAll(AbstractApsObject context) throws TreeTraversalException {
		List<ApsType> allList = new ArrayList<ApsType>();
		fullTreeConsumeAll(new Consumer<ApsType>() {
			@Override
			public void accept(ApsType t) {
				allList.add(t);

			}
		}, context);
		return allList;
	}

	protected void fullTreeConsumeAll(Consumer<ApsType> consumer, AbstractApsObject context)
			throws TreeTraversalException {
		context.consumeTree(new ConsumerFilter(consumer), true);
	}

	protected List<ApsType> fullTreeFindBySelector(ISelector<ApsType> selector, AbstractApsObject context)
			throws TreeTraversalException {
		List<ApsType> list = new ArrayList<ApsType>();
		Consumer<ApsType> apsConsumer = new Consumer<ApsType>() {

			@Override
			public void accept(ApsType t) {
				if (selector.isSelectable(t)) {
					list.add(t);
				}

			}

		};
		context.consumeTree(apsConsumer, true);
		return list;
	}

	@Override
	public List<ApsType> findBySelector(ISelector<ApsType> selector, ICompanyStructureNode context)
			throws DataModelDaoException {
		SelectorFilteringConsumer getall = new SelectorFilteringConsumer();
		getall.selector = selector;
		consumeAll(getall, context);
		return getall.selected;
	}

	@Override
	public ApsType findByCode(String code, ICompanyStructureNode context) throws DataModelDaoException {

		ISelector<ApsType> selector = new SelectorByCode(code);
		List<ApsType> outValue = findBySelector(selector, context);
		if (outValue != null && outValue.size() > 1)
			throw new DataModelDaoException("Duplicated code=>" + code + " of type " + searchedType);
		return outValue != null && !outValue.isEmpty() ? outValue.get(0) : null;
	}

	@Override
	public ApsType findById(String id, ICompanyStructureNode context) throws DataModelDaoException {

		ISelector<ApsType> selector = new SelectorById(id);
		List<ApsType> outValue = findBySelector(selector, context);
		if (outValue != null && outValue.size() > 1)
			throw new DataModelDaoException("Duplicated id=>" + id + " of type " + searchedType);
		return outValue != null && !outValue.isEmpty() ? outValue.get(0) : null;
	}

	@Override
	public List<ApsType> findAll(ICompanyStructureNode context) throws DataModelDaoException {

		ISelector<ApsType> selector = new ISelector<ApsType>() {

			@Override
			public boolean isSelectable(ApsType t) {

				return true;
			}
		};
		return findBySelector(selector, context);
	}

	@Override
	public void consumeAll(Consumer<ApsType> consumer, ICompanyStructureNode context) throws DataModelDaoException {
		Object untyped = context;
		try {
			dataPathIndexScanner.scanObjects(untyped, untyped.getClass(), searchedType, consumer, dataPathConfig);
		} catch (DataPathException e) {
			throw new DataModelDaoException("Searching all", e);
		}
	}

	@Override
	public ApsType findByCode(String code, ApsData context) throws DataModelDaoException {

		ISelector<ApsType> selector = new SelectorByCode(code);
		List<ApsType> outValue = findBySelector(selector, context);
		if (outValue != null && outValue.size() > 1)
			throw new DataModelDaoException("Duplicated code=>" + code + " of type " + searchedType);
		return outValue != null && !outValue.isEmpty() ? outValue.get(0) : null;
	}

	@Override
	public ApsType findById(String id, ApsData context) throws DataModelDaoException {
		ISelector<ApsType> selector = new SelectorById(id);
		List<ApsType> outValue = findBySelector(selector, context);
		if (outValue != null && outValue.size() > 1)
			throw new DataModelDaoException("Duplicated id=>" + id + " of type " + searchedType);
		return outValue != null && !outValue.isEmpty() ? outValue.get(0) : null;
	}

	@Override
	public List<ApsType> findAll(ApsData context) throws DataModelDaoException {
		ISelector<ApsType> selector = new ISelector<ApsType>() {

			@Override
			public boolean isSelectable(ApsType t) {

				return true;
			}
		};
		return findBySelector(selector, context);
	}

	@Override
	public void consumeAll(Consumer<ApsType> consumer, ApsData context) throws DataModelDaoException {
		try {
			dataPathIndexScanner.scanObjects(context, ApsData.class, this.searchedType, consumer, dataPathConfig);
		} catch (DataPathException e) {
			throw new DataModelDaoException("consumeAll(..)", e);
		}

	}

	@Override
	public List<ApsType> findBySelector(ISelector<ApsType> selector, ApsData context) throws DataModelDaoException {
		SelectorFilteringConsumer consumer = new SelectorFilteringConsumer();
		consumer.selector = selector;
		consumeAll(consumer, context);
		return consumer.selected;
	}

	@Override
	public DataModelSnapshotCache<ApsType> loadCache(ApsData context) throws DataModelDaoException {
		return new DataModelSnapshotCache<ApsType>(findAll(context));
	}

	@Override
	public DataModelSnapshotCache<ApsType> loadCache(ICompanyStructureNode context) throws DataModelDaoException {
		return new DataModelSnapshotCache<ApsType>(findAll(context));
	}

	protected <T extends IApsObject> T findParent(ApsType entry, String parentCode, Class<T> type,
			IApsDataModelDao<T> dao, ApsData context) throws DataModelDaoException {

		if (parentCode == null || parentCode.trim().length() == 0)
			throw new DataModelDaoException(
					"Cannot insert plant =>" + entry.getCode() + " because no parent code is assigned");
		T parent = dao.findByCode(parentCode, context);
		if (parent == null)
			throw new DataModelDaoException("Cannot insert " + entry.getClass().getSimpleName() + " =>"
					+ entry.getCode() + " because no parent object is found with code=>" + parentCode);
		return parent;
	}

	public Stream<ApsType> streamAll(ApsData context) throws DataModelDaoException {
		return this.streamBySelector(new ISelector<ApsType>() {
			@Override
			public boolean isSelectable(ApsType t) {

				return true;
			}
		}, context);
	}

	public Stream<ApsType> streamAll(ICompanyStructureNode context) throws DataModelDaoException {
		return this.streamBySelector(new ISelector<ApsType>() {
			@Override
			public boolean isSelectable(ApsType t) {

				return true;
			}
		}, context);
	}
	
	public Stream<ApsType> streamBySelector(ISelector<ApsType> selector, ApsData context) throws DataModelDaoException {
		
		return findBySelector(selector, context).stream();
	}

	public Stream<ApsType> streamBySelector(ISelector<ApsType> selector, ICompanyStructureNode context)
			throws DataModelDaoException {
		return findBySelector(selector, context).stream();
	}
}
