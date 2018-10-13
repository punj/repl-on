package com.repl.common;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;





public class GenericDAO {

	private static final ThreadLocal<GenericDAO> threadLocal = new ThreadLocal<GenericDAO>();

	private static GenericDAO genericDAO = null;

	Logger logger = Logger.getLogger("GenericDAO");

	public GenericDAO() {
	}

	public static GenericDAO getInstance() {
		genericDAO = threadLocal.get();

		if (null == genericDAO) {
			genericDAO = new GenericDAO();
			threadLocal.set(genericDAO);
		}

		return genericDAO;
	}

	public List getNammedQueryData(String queryName) {
		return getNammedQueryData(queryName, null);
	}

	public List getNammedQueryData(String queryName,
			HashMap<String, Object> params) {
		try {

			Session session =HibernateClose.currentSession();

			Query query = session.getNamedQuery(queryName);
			if (null != params && params.size() > 0) {
				Object[] keys = params.keySet().toArray();
				for (int i = 0; i < keys.length; i++) {
					Object objValue = params.get(keys[i]);
					if (objValue instanceof List) {
						query.setParameterList((String) keys[i],
								(Collection) objValue);
						logger.log(Level.ALL,"QueryParam : " + keys[i] + " - "
								+ (Collection) objValue);
					} else if (objValue instanceof String[]) {
						query.setParameterList((String) keys[i],
								(String[]) objValue);
						logger.log(Level.ALL,"QueryParam : " + keys[i] + " - "
								+ Arrays.toString((String[]) objValue));
					} else if (objValue instanceof Long[]) {
						query.setParameterList((String) keys[i],
								(Long[]) objValue);
						logger.log(Level.ALL,"QueryParam : " + keys[i] + " - "
								+ Arrays.toString((Long[]) objValue));
					} else {
						query.setParameter((String) keys[i], objValue);
						logger.log(Level.ALL,"QueryParam : " + keys[i] + " - "
								+ objValue);
					}

				}
			}
			List dataList = query.list();
			System.out.println("query from hiber __>" + query );
			return dataList;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public List getDataFromQuery(String strQuery) {
		Session session =HibernateClose.currentSession();
		SQLQuery query = session.createSQLQuery(strQuery);
		List dataList = query.list();
		return dataList;
	}

}
