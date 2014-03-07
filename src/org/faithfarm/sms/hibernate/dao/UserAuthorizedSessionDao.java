package org.faithfarm.sms.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.faithfarm.sms.domain.UserAuthorizedSession;
import org.faithfarm.sms.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserAuthorizedSessionDao {
	private static SessionFactory factory;
	private final static Logger LOGGER = Logger.getLogger(UserAuthorizedSessionDao.class
			.getName());
	private static Session session;

	public UserAuthorizedSessionDao() {

		LOGGER.setLevel(Level.INFO);

	}

	public UserAuthorizedSession findById(String id) {
		Transaction tx = null;
		UserAuthorizedSession UserAuthorizedSession = null;
		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			UserAuthorizedSession = (UserAuthorizedSession) session.get(UserAuthorizedSession.class, id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return UserAuthorizedSession;
	}

	public UserAuthorizedSession authenticate(String username, String password) {

		Transaction tx = null;
		UserAuthorizedSession user = null;

		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			Query q = session
					.createQuery("from UserAuthorizedSession where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);

			List result = q.list();
			if (result.size() > 0)
				user = (UserAuthorizedSession) result.get(0);

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		return user;
	}

	public List listUserAuthorizedSessions() {
		LOGGER.setLevel(Level.INFO);
		List<UserAuthorizedSession> list = new ArrayList<UserAuthorizedSession>();
		Transaction tx = null;
		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			tx.begin();
			list = session.createQuery("FROM UserAuthorizedSession").list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return list;
	}

	/* Method to INSERT UserAuthorizedSession */
	public String addUserAuthorizedSession(UserAuthorizedSession obj) {
		Transaction tx = null;
		String key = null;
		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			key = (String) session.save(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		return key;
	}

	/* Method to UPDATE UserAuthorizedSession */
	public void updateUserAuthorizedSession(UserAuthorizedSession obj) {
		Transaction tx = null;
		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			session.update(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}

	/* Method to DELETE UserAuthorizedSession */
	public void deleteUserAuthorizedSession(Long key) {
		Transaction tx = null;
		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			UserAuthorizedSession obj = (UserAuthorizedSession) session.get(UserAuthorizedSession.class, key);
			session.delete(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}

}
