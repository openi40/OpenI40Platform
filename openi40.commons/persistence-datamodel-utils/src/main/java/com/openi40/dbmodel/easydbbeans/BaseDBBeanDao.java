package com.openi40.dbmodel.easydbbeans;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;
import java.util.stream.Stream;



public class BaseDBBeanDao<dbbType extends BaseDBBean> {
	Class<dbbType> type = null;

	public BaseDBBeanDao(Class<dbbType> type) {
		this.type = type;
	}

	public static <T extends BaseDBBean> BaseDBBeanDao<T> create(Class<T> type) {
		return new BaseDBBeanDao<T>(type);
	}

	public Stream<dbbType> streamBy(Connection connection)
			throws InstantiationException, IllegalAccessException, SQLException, PersistenceException {
		return BaseDBBean.streamBy(connection, new HashMap(), null, null, new Vector(), type);
	}

	public Stream<dbbType> streamBy(Connection connection, HashMap attributesQbe)
			throws InstantiationException, IllegalAccessException, SQLException, PersistenceException {
		return streamBy(connection, attributesQbe, new Vector());
	}

	public Stream<dbbType> streamBy(Connection connection, HashMap attributesQbe, Vector orderBy)
			throws InstantiationException, IllegalAccessException, SQLException, PersistenceException {
		return BaseDBBean.streamBy(connection, attributesQbe, null, null, orderBy, type);
	}

	public Stream<dbbType> streamByWhere(Connection connection, String where, Vector params, Vector orderBy)
			throws InstantiationException, IllegalAccessException, SQLException, PersistenceException {
		return BaseDBBean.streamByWhere(connection, where, params, orderBy, type);
	}

	public Stream<dbbType> streamByWhere(Connection connection, String where) throws InstantiationException, IllegalAccessException, SQLException, PersistenceException {

		return streamByWhere(connection, where, new Vector(), new Vector()); 
	}
}
