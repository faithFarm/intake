package org.faithfarm.sms.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.faithfarm.sms.domain.CwtModuleStudent;
import org.faithfarm.sms.domain.SystemUser;
import org.faithfarm.sms.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CwtModuleStudentDao {
	private static SessionFactory factory;
	private final static Logger LOGGER = Logger
			.getLogger(CwtModuleStudentDao.class.getName());
	private static Session session;

	public CwtModuleStudentDao() {

		LOGGER.setLevel(Level.INFO);

		try {
			session = HibernateUtil.currentSession();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public CwtModuleStudent findById(Integer id) {

		CwtModuleStudent CwtModuleStudent = (CwtModuleStudent) session.get(
				CwtModuleStudent.class, id);

		return CwtModuleStudent;
	}

	public List listCwtModuleStudents() {
		LOGGER.setLevel(Level.INFO);
		List<CwtModuleStudent> list = new ArrayList<CwtModuleStudent>();
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			tx.begin();
			list = session.createQuery("FROM CwtModuleStudent").list();
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

	/* Method to INSERT CwtModuleStudent */
	public Long addCwtModuleStudent(CwtModuleStudent obj) {
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

	/* Method to UPDATE CwtModuleStudent */
	public void updateCwtModuleStudent(CwtModuleStudent obj) {
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			// CwtModuleStudent CwtModuleStudent =
			// (CwtModuleStudent)session.get(CwtModuleStudent.class,
			// CwtModuleStudentID);
			// CwtModuleStudent.setSalary( salary );
			session.update(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			
		}
	}

	/* Method to DELETE CwtModuleStudent */
	public void deleteCwtModuleStudent(Integer key) {
		Transaction tx = null;

		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			CwtModuleStudent obj = (CwtModuleStudent) session.get(
					CwtModuleStudent.class, key);
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
