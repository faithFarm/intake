package org.faithfarm.sms.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.faithfarm.sms.domain.Intake;
import org.faithfarm.sms.domain.SystemUser;
import org.faithfarm.sms.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SystemUserDao {
	private static SessionFactory factory;
	private final static Logger LOGGER = Logger.getLogger(SystemUserDao.class
			.getName());
	private static Session session;

	public SystemUserDao() {

		LOGGER.setLevel(Level.INFO);

	}

	public SystemUser findById(Long id) {
		Transaction tx = null;
		SystemUser SystemUser = null;
		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			SystemUser = (SystemUser) session.get(SystemUser.class, id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return SystemUser;
	}

	public SystemUser authenticate(String username, String password) {

		Transaction tx = null;
		SystemUser user = null;

		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			Query q = session
					.createQuery("from SystemUser where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);

			List result = q.list();
			if (result.size() > 0)
				user = (SystemUser) result.get(0);

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		return user;
	}

	public List listSystemUsers() {
		LOGGER.setLevel(Level.INFO);
		List<SystemUser> list = new ArrayList<SystemUser>();
		Transaction tx = null;
		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			tx.begin();
			list = session.createQuery("FROM SystemUser").list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return list;
	}

	/* Method to INSERT SystemUser */
	public Long addSystemUser(SystemUser obj) {
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

	/* Method to UPDATE SystemUser */
	public void updateSystemUser(SystemUser obj) {
		Transaction tx = null;
		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			SystemUser t = (SystemUser) session.get(SystemUser.class, obj.getUserId());
			if (t != null) { 
			session.evict(t); 
			} 
			session.update(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}

	/* Method to DELETE SystemUser */
	public void deleteSystemUser(Long key) {
		Transaction tx = null;
		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			SystemUser obj = (SystemUser) session.get(SystemUser.class, key);
			session.delete(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}

}
