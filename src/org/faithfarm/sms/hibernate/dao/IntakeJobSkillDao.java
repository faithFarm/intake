package org.faithfarm.sms.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.faithfarm.sms.domain.CwtProgramMetric;
import org.faithfarm.sms.domain.IntakeJobSkill;
import org.faithfarm.sms.domain.IntakeMedicalCondition;
import org.faithfarm.sms.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class IntakeJobSkillDao {
	private static SessionFactory factory;
	private final static Logger LOGGER = Logger
			.getLogger(IntakeJobSkillDao.class.getName());
	private static Session session;

	public IntakeJobSkillDao() {

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
		List<IntakeJobSkill> list = new ArrayList<IntakeJobSkill>();
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			tx.begin();
			StringBuffer query = new StringBuffer(
					"from IntakeJobSkill where intakeId = :intakeId ");
			Query q = session.createQuery(query.toString());
			q.setLong("intakeId", id);
			list = q.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new HibernateException (e);
		} finally {
			
		}
		return list;
	}

	public List listIntakeJobSkills() {
		LOGGER.setLevel(Level.INFO);
		List<IntakeJobSkill> list = new ArrayList<IntakeJobSkill>();
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			tx.begin();
			list = session.createQuery("FROM IntakeJobSkill").list();
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

	/* Method to INSERT IntakeJobSkill */
	public Long addIntakeJobSkill(IntakeJobSkill obj) {
		Transaction tx = null;
		Long key = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			IntakeJobSkill t = (IntakeJobSkill) session.get(IntakeJobSkill.class, obj.getIntakeJobSkillId());
			if (t != null) { 
			session.evict(t); 
			} 
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

	/* Method to UPDATE IntakeJobSkill */
	public void updateIntakeJobSkill(IntakeJobSkill obj) {
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			// IntakeJobSkill IntakeJobSkill =
			// (IntakeJobSkill)session.get(IntakeJobSkill.class,
			// IntakeJobSkillID);
			// IntakeJobSkill.setSalary( salary );
			session.update(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			
		}
	}

	public void deleteIntakeJobSkill(Long key) {
		Transaction tx = null;

		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			IntakeJobSkill obj = (IntakeJobSkill) session.get(
					IntakeJobSkill.class, key);
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
