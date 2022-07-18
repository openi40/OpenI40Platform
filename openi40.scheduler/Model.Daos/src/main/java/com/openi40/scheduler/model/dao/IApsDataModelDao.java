package com.openi40.scheduler.model.dao;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.ICompanyStructureNode;
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
public interface IApsDataModelDao<ApsType extends IApsObject> {

	public ApsType findByCode(String code, ApsData context) throws DataModelDaoException;

	public ApsType findById(String id, ApsData context) throws DataModelDaoException;

	public List<ApsType> findAll(ApsData context) throws DataModelDaoException;

	public void consumeAll(Consumer<ApsType> consumer, ApsData context) throws DataModelDaoException;
	public Stream<ApsType> streamAll( ApsData context) throws DataModelDaoException;
	public Stream<ApsType> streamAll( ICompanyStructureNode context) throws DataModelDaoException;
	public Stream<ApsType> streamBySelector(ISelector<ApsType> selector, ApsData context) throws DataModelDaoException;
	public Stream<ApsType> streamBySelector(ISelector<ApsType> selector, ICompanyStructureNode context) throws DataModelDaoException;
	public List<ApsType> findBySelector(ISelector<ApsType> selector, ApsData context) throws DataModelDaoException;

	public ApsType findByCode(String code, ICompanyStructureNode context) throws DataModelDaoException;

	public ApsType findById(String id, ICompanyStructureNode context) throws DataModelDaoException;

	public List<ApsType> findAll(ICompanyStructureNode context) throws DataModelDaoException;

	public void consumeAll(Consumer<ApsType> consumer, ICompanyStructureNode context) throws DataModelDaoException;

	public List<ApsType> findBySelector(ISelector<ApsType> selector, ICompanyStructureNode context) throws DataModelDaoException;
	public DataModelSnapshotCache<ApsType> loadCache(ApsData context) throws DataModelDaoException;
	public DataModelSnapshotCache<ApsType> loadCache(ICompanyStructureNode context)  throws DataModelDaoException;
	public void insert(ApsType entry,ApsData context) throws DataModelDaoException;
	public void delete(ApsType entry,ApsData context) throws DataModelDaoException;
	
}
