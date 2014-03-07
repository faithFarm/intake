package org.faithfarm.sms.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.faithfarm.sms.domain.CwtDepartment;
import org.faithfarm.sms.domain.SystemUser;
import org.faithfarm.sms.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CwtDepartmentDao {
	private static SessionFactory factory;
	private final static Logger LOGGER = Logger
			.getLogger(CwtDepartmentDao.class.getName());
	private static Session session;

	public CwtDepartmentDao() {

		LOGGER.setLevel(Level.INFO);

		try {
			session = HibernateUtil.currentSession();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public CwtDepartment findById(Long id) {

		CwtDepartment CwtDepartment = (CwtDepartment) session.get(
				CwtDepartment.class, id);

		return CwtDepartment;
	}

	public List listCwtDepartments() {
		LOGGER.setLevel(Level.INFO);
		List<CwtDepartment> list = new ArrayList<CwtDepartment>();
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			tx.begin();
			list = session.createQuery("FROM CwtDepartment").list();
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

	/* Method to INSERT CwtDepartment */
	public Long addCwtDepartment(CwtDepartment obj) {
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

	/* Method to UPDATE CwtDepartment */
	public void updateCwtDepartment(CwtDepartment obj) {
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			// CwtDepartment CwtDepartment =
			// (CwtDepartment)session.get(CwtDepartment.class, CwtDepartmentID);
			// CwtDepartment.setSalary( salary );
			session.update(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {

		}
	}

	/* Method to DELETE CwtDepartment */
	public void deleteCwtDepartment(Integer key) {
		Transaction tx = null;

		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			CwtDepartment obj = (CwtDepartment) session.get(
					CwtDepartment.class, key);
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
