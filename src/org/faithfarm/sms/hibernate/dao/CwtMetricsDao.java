package org.faithfarm.sms.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.faithfarm.sms.domain.CwtMetrics;
import org.faithfarm.sms.domain.SystemUser;
import org.faithfarm.sms.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CwtMetricsDao {
	private static SessionFactory factory;
	private final static Logger LOGGER = Logger.getLogger(CwtMetricsDao.class
			.getName());
	private static Session session;

	public CwtMetricsDao() {

		LOGGER.setLevel(Level.INFO);

		try {
			session = HibernateUtil.currentSession();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public CwtMetrics findById(Long id) {

		CwtMetrics CwtMetrics = (CwtMetrics) session.get(CwtMetrics.class, id);

		return CwtMetrics;
	}

	public List listCwtMetricss() {
		LOGGER.setLevel(Level.INFO);
		List<CwtMetrics> list = new ArrayList<CwtMetrics>();
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			tx.begin();
			list = session.createQuery("FROM CwtMetrics").list();
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

	/* Method to INSERT CwtMetrics */
	public Long addCwtMetrics(CwtMetrics obj) {
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

	/* Method to UPDATE CwtMetrics */
	public void updateCwtMetrics(CwtMetrics obj) {
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			// CwtMetrics CwtMetrics =
			// (CwtMetrics)session.get(CwtMetrics.class, CwtMetricsID);
			// CwtMetrics.setSalary( salary );
			session.update(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			
		}
	}

	/* Method to DELETE CwtMetrics */
	public void deleteCwtMetrics(Long key) {
		Transaction tx = null;

		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			CwtMetrics obj = (CwtMetrics) session.get(CwtMetrics.class, key);
			session.delete(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			
		}
	}

}
