package org.faithfarm.sms.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.faithfarm.sms.domain.MedicalCondition;
import org.faithfarm.sms.domain.State;
import org.faithfarm.sms.domain.SystemUser;
import org.faithfarm.sms.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StateDao {
	private static SessionFactory factory;
	private final static Logger LOGGER = Logger.getLogger(StateDao.class
			.getName());
	private static Session session;

	public StateDao() {

		LOGGER.setLevel(Level.INFO);

	}

	public State findById(Integer id) {
		State State = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			State = (State) session.get(State.class, id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null && session.isOpen())
				tx.rollback();
			e.printStackTrace();
		}

		return State;
	}

	public List listStates() {
		LOGGER.setLevel(Level.INFO);
		List<State> list = new ArrayList<State>();
		Transaction tx = null;
		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			list = session.createQuery("FROM State").list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return list;
	}

	/* Method to INSERT State */
	public Long addState(State obj) {
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
		}
		return key;
	}

	/* Method to UPDATE State */
	public void updateState(State obj) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			
		}
	}

	/* Method to DELETE State */
	public void deleteState(Integer key) {
		Transaction tx = null;

		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			State obj = (State) session.get(State.class, key);
			session.delete(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}

}
