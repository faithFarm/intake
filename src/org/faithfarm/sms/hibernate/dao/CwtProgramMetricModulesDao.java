package org.faithfarm.sms.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.faithfarm.sms.domain.CwtProgramMetric;
import org.faithfarm.sms.domain.CwtProgramMetricModules;
import org.faithfarm.sms.domain.SystemUser;
import org.faithfarm.sms.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CwtProgramMetricModulesDao {
	private static SessionFactory factory;
	private final static Logger LOGGER = Logger
			.getLogger(CwtProgramMetricModulesDao.class.getName());
	private static Session session;

	public CwtProgramMetricModulesDao() {

		LOGGER.setLevel(Level.INFO);

		try {
			session = HibernateUtil.currentSession();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public CwtProgramMetricModules findById(Long id) {

		CwtProgramMetricModules CwtProgramMetricModules = (CwtProgramMetricModules) session
				.get(CwtProgramMetricModules.class, id);

		return CwtProgramMetricModules;
	}

	public List listCwtProgramMetricModuless() {
		LOGGER.setLevel(Level.INFO);
		List<CwtProgramMetricModules> list = new ArrayList<CwtProgramMetricModules>();
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			tx.begin();
			list = session.createQuery("FROM CwtProgramMetricModules").list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			
		}
		return list;
	}

	/* Method to INSERT CwtProgramMetricModules */
	public Long addCwtProgramMetricModules(CwtProgramMetricModules obj) {
		Transaction tx = null;
		Long key = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			key = (Long) session.save(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			
		}
		return key;
	}

	/* Method to UPDATE CwtProgramMetricModules */
	public void updateCwtProgramMetricModules(CwtProgramMetricModules obj) {
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			// CwtProgramMetricModules CwtProgramMetricModules =
			// (CwtProgramMetricModules)session.get(CwtProgramMetricModules.class,
			// CwtProgramMetricModulesID);
			// CwtProgramMetricModules.setSalary( salary );
			session.update(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			
		}
	}

	/* Method to DELETE CwtProgramMetricModules */
	public void deleteCwtProgramMetricModules(Long key) {
		Transaction tx = null;

		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			CwtProgramMetricModules obj = (CwtProgramMetricModules) session
					.get(CwtProgramMetricModules.class, key);
			session.delete(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			
		}
	}

	public List findByModuleId(Long id) {

		LOGGER.setLevel(Level.INFO);
		List<CwtProgramMetricModules> list = new ArrayList<CwtProgramMetricModules>();
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			tx.begin();
			StringBuffer query = new StringBuffer(
					"from CwtProgramMetricModules where moduleId = :moduleId ");
			Query q = session.createQuery(query.toString());
			q.setLong("moduleId", id);
			list = q.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			
		}
		return list;
	}

}
