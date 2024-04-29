package com.openi40.scheduler.model.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.ICompanyStructureNode;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.DataModelSnapshotCache;
import com.openi40.scheduler.model.dao.ISelector;
import com.openi40.scheduler.model.dao.ITaskDao;
import com.openi40.scheduler.model.dao.ITaskEdgeDao;
import com.openi40.scheduler.model.tasks.TaskEdge;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
@Service
public class TaskEdgeDao implements ITaskEdgeDao {
	ITaskDao taskDao = null;
	static Logger LOGGER = LoggerFactory.getLogger(TaskEdgeDao.class);

	public TaskEdgeDao(@Autowired ITaskDao taskDao) {
		this.taskDao = taskDao;
	}

	@Override
	public TaskEdge findByCode(String code, ApsData context) throws DataModelDaoException {
		List<TaskEdge> values = findBySelector((entry) -> {
			return entry.getCode() != null && code != null && entry.getCode().equals(code);
		}, context);
		return values.isEmpty() ? null : values.get(0);
	}

	@Override
	public TaskEdge findById(String id, ApsData context) throws DataModelDaoException {
		List<TaskEdge> values = findBySelector((entry) -> {
			return entry.getId() != null && id != null && entry.getId().equals(id);
		}, context);
		return values.isEmpty() ? null : values.get(0);
	}

	@Override
	public List<TaskEdge> findAll(ApsData context) throws DataModelDaoException {
		List<TaskEdge> outValues = new ArrayList<>();
		Consumer<TaskEdge> consumer = new Consumer<TaskEdge>() {
			@Override
			public void accept(TaskEdge t) {
				outValues.add(t);

			}
		};
		consumeAll(consumer, context);
		return outValues;
	}

	@Override
	public void consumeAll(Consumer<TaskEdge> consumer, ApsData context) throws DataModelDaoException {

		taskDao.consumeAll((task) -> {
			task.getChildTasks().forEach(consumer);
		}, context);
	}

	@Override
	public Stream<TaskEdge> streamAll(ApsData context) throws DataModelDaoException {

		return findAll(context).stream();
	}

	@Override
	public Stream<TaskEdge> streamAll(ICompanyStructureNode context) throws DataModelDaoException {

		return findAll(context).stream();
	}

	@Override
	public Stream<TaskEdge> streamBySelector(ISelector<TaskEdge> selector, ApsData context)
			throws DataModelDaoException {

		return findBySelector(selector, context).stream();
	}

	@Override
	public Stream<TaskEdge> streamBySelector(ISelector<TaskEdge> selector, ICompanyStructureNode context)
			throws DataModelDaoException {

		return findBySelector(selector, context).stream();
	}

	@Override
	public List<TaskEdge> findBySelector(ISelector<TaskEdge> selector, ApsData context) throws DataModelDaoException {
		List<TaskEdge> outValues = new ArrayList<>();
		Consumer<TaskEdge> consumer = new Consumer<TaskEdge>() {
			@Override
			public void accept(TaskEdge t) {
				if (selector.isSelectable(t)) {
					outValues.add(t);
				}

			}
		};
		consumeAll(consumer, context);
		return outValues;
	}

	@Override
	public TaskEdge findByCode(String code, ICompanyStructureNode context) throws DataModelDaoException {
		List<TaskEdge> values = findBySelector((entry) -> {
			return entry.getCode() != null && code != null && entry.getCode().equals(code);
		}, context);
		return values.isEmpty() ? null : values.get(0);
	}

	@Override
	public TaskEdge findById(String id, ICompanyStructureNode context) throws DataModelDaoException {

		List<TaskEdge> values = findBySelector((entry) -> {
			return entry.getId() != null && id != null && entry.getId().equals(id);
		}, context);
		return values.isEmpty() ? null : values.get(0);
	}

	@Override
	public List<TaskEdge> findAll(ICompanyStructureNode context) throws DataModelDaoException {

		return null;
	}

	@Override
	public void consumeAll(Consumer<TaskEdge> consumer, ICompanyStructureNode context) throws DataModelDaoException {
		taskDao.consumeAll((task) -> {
			task.getChildTasks().forEach(consumer);
		}, context);
	}

	@Override
	public List<TaskEdge> findBySelector(ISelector<TaskEdge> selector, ICompanyStructureNode context)
			throws DataModelDaoException {

		List<TaskEdge> outValues = new ArrayList<>();
		Consumer<TaskEdge> consumer = new Consumer<TaskEdge>() {
			@Override
			public void accept(TaskEdge t) {
				if (selector.isSelectable(t)) {
					outValues.add(t);
				}

			}
		};
		consumeAll(consumer, context);
		return outValues;
	}

	@Override
	public DataModelSnapshotCache<TaskEdge> loadCache(ApsData context) throws DataModelDaoException {

		return new DataModelSnapshotCache<TaskEdge>(findAll(context));
	}

	@Override
	public DataModelSnapshotCache<TaskEdge> loadCache(ICompanyStructureNode context) throws DataModelDaoException {

		return new DataModelSnapshotCache<TaskEdge>(findAll(context));
	}

	@Override
	public void insert(TaskEdge entry, ApsData context) throws DataModelDaoException {
		if (entry.getProducerTask() != null) {
			entry.getProducerTask().setParentTask(entry);
		} else {
			LOGGER.warn("The task entry=>" + entry.getCode() + " does not have a producer task");
		}
		if (entry.getConsumerTask() != null) {
			entry.getConsumerTask().getChildTasks().add(entry);
		} else {
			LOGGER.warn("The task entry=>" + entry.getCode() + " does not have a consumer task");
		}
	}

	@Override
	public void delete(TaskEdge entry, ApsData context) throws DataModelDaoException {

	}

}
