package org.faithfarm.sms.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.faithfarm.sms.domain.JobSkill;
import org.faithfarm.sms.domain.Question;
import org.faithfarm.sms.domain.SystemUser;
import org.faithfarm.sms.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class JobSkillDao {
	private static SessionFactory factory;
	private final static Logger LOGGER = Logger.getLogger(JobSkillDao.class
			.getName());
	private static Session session;

	public JobSkillDao() {

		LOGGER.setLevel(Level.INFO);

	}

	public JobSkill findById(Integer id) {

		JobSkill jobSkill = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			tx = session.beginTransaction();
			jobSkill = (JobSkill) session.get(JobSkill.class, id);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		return jobSkill;
	}

	public List listJobSkills() {
		List<JobSkill> list = new ArrayList<JobSkill>();
		Transaction tx = null;
		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			list = session.createQuery("FROM JobSkill").list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null && session.isOpen())
				tx.rollback();

			e.printStackTrace();
		}
		return list;
	}

	/* Method to INSERT JobSkill */
	public Long addJobSkill(JobSkill obj) {
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

	/* Method to UPDATE JobSkill */
	public void updateJobSkill(JobSkill obj) {
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

	/* Method to DELETE JobSkill */
	public void deleteJobSkill(Integer key) {
		Transaction tx = null;

		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			tx = session.beginTransaction();
			JobSkill obj = (JobSkill) session.get(JobSkill.class, key);
			session.delete(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}

}
