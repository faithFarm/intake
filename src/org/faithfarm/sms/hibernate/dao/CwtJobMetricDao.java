package org.faithfarm.sms.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.faithfarm.sms.domain.CwtJobMetric;
import org.faithfarm.sms.domain.IntakeJobSkill;
import org.faithfarm.sms.domain.SystemUser;
import org.faithfarm.sms.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CwtJobMetricDao {
	private static SessionFactory factory;
	private final static Logger LOGGER = Logger.getLogger(CwtJobMetricDao.class
			.getName());
	private static Session session;

	public CwtJobMetricDao() {

		LOGGER.setLevel(Level.INFO);

		try {
			session = HibernateUtil.currentSession();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public CwtJobMetric findById(Integer id) {

		CwtJobMetric CwtJobMetric = (CwtJobMetric) session.get(
				CwtJobMetric.class, id);

		return CwtJobMetric;
	}

	public List listCwtJobMetrics() {
		LOGGER.setLevel(Level.INFO);
		List<CwtJobMetric> list = new ArrayList<CwtJobMetric>();
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			tx.begin();
			list = session.createQuery("FROM CwtJobMetric").list();
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

	/* Method to INSERT CwtJobMetric */
	public Long addCwtJobMetric(CwtJobMetric obj) {
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

	/* Method to UPDATE CwtJobMetric */
	public void updateCwtJobMetric(CwtJobMetric obj) {
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			// CwtJobMetric CwtJobMetric =
			// (CwtJobMetric)session.get(CwtJobMetric.class, CwtJobMetricID);
			// CwtJobMetric.setSalary( salary );
			session.update(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			
		}
	}

	/* Method to DELETE CwtJobMetric */
	public void deleteCwtJobMetric(Long key) {
		Transaction tx = null;

		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			CwtJobMetric obj = (CwtJobMetric) session.get(CwtJobMetric.class,
					key);
			session.delete(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			
		}
	}

	public List findByJobId(Long id) {

		LOGGER.setLevel(Level.INFO);
		List<IntakeJobSkill> list = new ArrayList<IntakeJobSkill>();
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			tx.begin();
			StringBuffer query = new StringBuffer(
					"from CwtJobMetric where jobId = :jobId ");
			Query q = session.createQuery(query.toString());
			q.setLong("jobId", id);
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
