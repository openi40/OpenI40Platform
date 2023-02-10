package com.openi40.scheduler.model.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.datapath.DataPathCfg;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsMessage;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IApsMessageDao;
import com.openi40.scheduler.model.dao.ITaskDao;
import com.openi40.scheduler.model.tasks.Task;

@Service
public class ApsMessageDataModelDao extends AbstractApsDataModelDao<ApsMessage> implements IApsMessageDao {
	@Autowired
	ITaskDao taskDao;

	public ApsMessageDataModelDao() {
		super(ApsMessage.class, new DataPathCfg(ApsMessage.class)

				.withPath(ApsData.class,
						"ApsData/ProductiveCompanies/ProductiveCompany/Plants/Plant/WorkOrders/WorkOrder/ChildTasks/Task/Messages")

				.withPath(ProductiveCompany.class,
						"ProductiveCompany/Plants/Plant/WorkOrders/WorkOrder/ChildTasks/Task/Messages")

				.withPath(Plant.class, "Plant/WorkOrders/WorkOrder/ChildTasks/Task/Messages"));

	}

	private Task retrieveRelatedTask(ApsMessage entry, ApsData context) throws DataModelDaoException {
		if (entry.getTaskCode() != null && entry.getTaskCode().trim().length() > 0) {
			Task task = taskDao.findByCode(entry.getTaskCode(), context);
			if (task == null)
				throw new DataModelDaoException(
						"Task with code " + entry.getTaskCode() + " not found for message=>" + entry.getCode());
			return task;
		} else
			throw new DataModelDaoException("Impossible to retrive related task for message=> " + entry.getCode());
	}

	@Override
	public void insert(ApsMessage entry, ApsData context) throws DataModelDaoException {
		retrieveRelatedTask(entry, context).getMessages().add(entry);
	}

	@Override
	public void delete(ApsMessage entry, ApsData context) throws DataModelDaoException {
		retrieveRelatedTask(entry, context).getMessages().remove(entry);
	}

	@Override
	public List<ApsMessage> findAllOrderByGlobalPosition(ApsData data) throws DataModelDaoException {
		TreeMap<Integer, List<ApsMessage>> orderer = new TreeMap<>();
		List<ApsMessage> allList = this.findAll(data);
		for (ApsMessage apsMessage : allList) {
			if (!orderer.containsKey(apsMessage.getGlobalPosition())) {
				orderer.put(apsMessage.getGlobalPosition(), new ArrayList<>());
			}
			orderer.get(apsMessage.getGlobalPosition()).add(apsMessage);
		}
		List<ApsMessage> outList = this.findAll(data);
		Collection<List<ApsMessage>> vectors = orderer.values();
		for (List<ApsMessage> list : vectors) {
			outList.addAll(list);
		}
		return outList;
	}

}
