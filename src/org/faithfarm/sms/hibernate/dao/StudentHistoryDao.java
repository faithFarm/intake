package org.faithfarm.sms.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.faithfarm.sms.domain.CwtProgramMetric;
import org.faithfarm.sms.domain.IntakeJobSkill;
import org.faithfarm.sms.domain.StudentHistory;
import org.faithfarm.sms.domain.SystemUser;
import org.faithfarm.sms.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StudentHistoryDao {
	private static SessionFactory factory;
	private final static Logger LOGGER = Logger
			.getLogger(StudentHistoryDao.class.getName());
	private static Session session;

	public StudentHistoryDao() {

		LOGGER.setLevel(Level.INFO);

		try {
			session = HibernateUtil.currentSession();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public StudentHistory findById(Long id) {

		StudentHistory StudentHistory = (StudentHistory) session.get(
				StudentHistory.class, id);

		return StudentHistory;
	}

	public List findByIntakeId(Long id) {

		LOGGER.setLevel(Level.INFO);
		List<IntakeJobSkill> list = new ArrayList<IntakeJobSkill>();
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			tx.begin();
			StringBuffer query = new StringBuffer(
					"from StudentHistory where intakeId = :intakeId ");
			//query.append(" Order by studentHistoryId asc ");
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

	public List listStudentHistorys() {
		LOGGER.setLevel(Level.INFO);
		List<StudentHistory> list = new ArrayList<StudentHistory>();
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			tx.begin();
			list = session.createQuery("FROM StudentHistory").list();
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

	/* Method to INSERT StudentHistory */
	public Long addStudentHistory(StudentHistory obj) {
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

	/* Method to UPDATE StudentHistory */
	public void updateStudentHistory(StudentHistory obj) {
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			StudentHistory t = (StudentHistory) session.get(StudentHistory.class, obj.getStudentHistoryId());
			if (t != null) { 
			session.evict(t); 
			} 
			// StudentHistory StudentHistory =
			// (StudentHistory)session.get(StudentHistory.class,
			// StudentHistoryID);
			// StudentHistory.setSalary( salary );
			session.update(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			
		}
	}

	/* Method to DELETE StudentHistory */
	public void deleteStudentHistory(Long key) {
		Transaction tx = null;

		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			StudentHistory obj = (StudentHistory) session.get(
					StudentHistory.class, key);
			session.delete(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			
		}
	}

	public List search(Long key) {

		StringBuffer query = new StringBuffer("from StudentHistory");
		query.append(" where intakeId = :intakeId ");
		Query q = session.createQuery(query.toString());
		q.setLong("intakeId", key);

		List list = q.list();
		return list;
	}
}
