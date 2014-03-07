package org.faithfarm.sms.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.faithfarm.sms.domain.IntakeMedicalCondition;
import org.faithfarm.sms.domain.SystemUser;
import org.faithfarm.sms.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class IntakeMedicalConditionDao {
	private static SessionFactory factory;
	private final static Logger LOGGER = Logger
			.getLogger(IntakeMedicalConditionDao.class.getName());
	private static Session session;

	public IntakeMedicalConditionDao() {

		LOGGER.setLevel(Level.INFO);

		try {
			session = HibernateUtil.currentSession();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public List findById(Long id) {

		LOGGER.setLevel(Level.INFO);
		List<IntakeMedicalCondition> list = new ArrayList<IntakeMedicalCondition>();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			tx.begin();
			StringBuffer query = new StringBuffer(
					"from IntakeMedicalCondition where intakeId = :intakeId ");
			Query q = session.createQuery(query.toString());
			q.setLong("intakeId", id);
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

	public List listIntakeMedicalConditions() {
		LOGGER.setLevel(Level.INFO);
		List<IntakeMedicalCondition> list = new ArrayList<IntakeMedicalCondition>();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			tx.begin();
			list = session.createQuery("FROM IntakeMedicalCondition").list();
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

	/* Method to INSERT IntakeMedicalCondition */
	public Long addIntakeMedicalCondition(IntakeMedicalCondition obj) {
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

	/* Method to UPDATE IntakeMedicalCondition */
	public void updateIntakeMedicalCondition(IntakeMedicalCondition obj) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// IntakeMedicalCondition IntakeMedicalCondition =
			// (IntakeMedicalCondition)session.get(IntakeMedicalCondition.class,
			// IntakeMedicalConditionID);
			// IntakeMedicalCondition.setSalary( salary );
			session.update(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			
		}
	}

	/* Method to DELETE IntakeMedicalCondition */
	public void deleteIntakeMedicalCondition(Long key) {
		Transaction tx = null;

		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			IntakeMedicalCondition obj = (IntakeMedicalCondition) session.get(
					IntakeMedicalCondition.class, key);
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
