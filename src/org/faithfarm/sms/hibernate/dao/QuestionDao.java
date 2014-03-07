package org.faithfarm.sms.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.faithfarm.sms.domain.Question;
import org.faithfarm.sms.domain.SystemUser;
import org.faithfarm.sms.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class QuestionDao {
	private static SessionFactory factory;
	private final static Logger LOGGER = Logger.getLogger(QuestionDao.class
			.getName());
	private static Session session;

	public QuestionDao() {

		LOGGER.setLevel(Level.INFO);

	}

	public Question findById(Integer id) {

		Question question = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			question = (Question) session.get(Question.class, id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		return question;
	}

	public List listQuestions() {
		List<Question> list = new ArrayList<Question>();
		Transaction tx = null;
		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			list = session.createQuery("FROM Question").list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null && session.isOpen())
				tx.rollback();
			e.printStackTrace();
		}
		return list;
	}

	/* Method to INSERT Question */
	public Long addQuestion(Question obj) {
		Transaction tx = null;
		Long key = null;
		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			key = (Long) session.save(obj);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}

		return key;
	}

	/* Method to UPDATE Question */
	public void updateQuestion(Question obj) {
		Transaction tx = null;
		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			tx = session.beginTransaction();
			session.update(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}

	/* Method to DELETE Question */
	public void deleteQuestion(Integer key) {
		Transaction tx = null;

		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			tx = session.beginTransaction();
			Question obj = (Question) session.get(Question.class, key);
			session.delete(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}

}
