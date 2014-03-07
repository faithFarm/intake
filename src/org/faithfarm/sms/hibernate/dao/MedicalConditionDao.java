package org.faithfarm.sms.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.faithfarm.sms.domain.MedicalCondition;
import org.faithfarm.sms.domain.SystemUser;
import org.faithfarm.sms.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MedicalConditionDao {
	private static SessionFactory factory;
	private final static Logger LOGGER = Logger
			.getLogger(MedicalConditionDao.class.getName());
	private static Session session;

	public MedicalConditionDao() {

		LOGGER.setLevel(Level.INFO);

	}

	public MedicalCondition findById(Integer id) {

		MedicalCondition MedicalCondition = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			MedicalCondition = (MedicalCondition) session.get(
					MedicalCondition.class, id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		return MedicalCondition;
	}

	public List listMedicalConditions() {
		LOGGER.setLevel(Level.INFO);
		List<MedicalCondition> list = new ArrayList<MedicalCondition>();
		Transaction tx = null;
		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			list = session.createQuery("FROM MedicalCondition").list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null && session.isOpen())
				tx.rollback();
			e.printStackTrace();
		}
		return list;
	}

	/* Method to INSERT MedicalCondition */
	public Long addMedicalCondition(MedicalCondition obj) {
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

	/* Method to UPDATE MedicalCondition */
	public void updateMedicalCondition(MedicalCondition obj) {
		Transaction tx = null;
		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			// MedicalCondition MedicalCondition =
			// (MedicalCondition)session.get(MedicalCondition.class,
			// MedicalConditionID);
			// MedicalCondition.setSalary( salary );
			session.update(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}

	/* Method to DELETE MedicalCondition */
	public void deleteMedicalCondition(Integer key) {
		Transaction tx = null;

		try {
			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			MedicalCondition obj = (MedicalCondition) session.get(
					MedicalCondition.class, key);
			session.delete(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}

}
