package org.faithfarm.sms.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.faithfarm.sms.domain.IntakeQuestionAnswer;
import org.faithfarm.sms.domain.SystemUser;
import org.faithfarm.sms.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class IntakeQuestionAnswerDao {
	private static SessionFactory factory;
	private final static Logger LOGGER = Logger
			.getLogger(IntakeQuestionAnswerDao.class.getName());
	private static Session session;

	public IntakeQuestionAnswerDao() {

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
		List<IntakeQuestionAnswer> list = new ArrayList<IntakeQuestionAnswer>();
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			tx.begin();
			StringBuffer query = new StringBuffer(
					"from IntakeQuestionAnswer where intakeId = :intakeId ");
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

	public List listIntakeQuestionAnswers() {
		LOGGER.setLevel(Level.INFO);
		List<IntakeQuestionAnswer> list = new ArrayList<IntakeQuestionAnswer>();
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			tx.begin();
			list = session.createQuery("FROM IntakeQuestionAnswer").list();
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

	/* Method to INSERT IntakeQuestionAnswer */
	public Long addIntakeQuestionAnswer(IntakeQuestionAnswer obj) {
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

	/* Method to UPDATE IntakeQuestionAnswer */
	public void updateIntakeQuestionAnswer(IntakeQuestionAnswer obj) {
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			// IntakeQuestionAnswer IntakeQuestionAnswer =
			// (IntakeQuestionAnswer)session.get(IntakeQuestionAnswer.class,
			// IntakeQuestionAnswerID);
			// IntakeQuestionAnswer.setSalary( salary );
			session.update(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			
		}
	}

	/* Method to DELETE IntakeQuestionAnswer */
	public void deleteIntakeQuestionAnswer(Long key) {
		Transaction tx = null;

		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			IntakeQuestionAnswer obj = (IntakeQuestionAnswer) session.get(
					IntakeQuestionAnswer.class, key);
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
