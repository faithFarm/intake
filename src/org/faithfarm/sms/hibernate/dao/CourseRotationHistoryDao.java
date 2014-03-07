package org.faithfarm.sms.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.faithfarm.sms.domain.CourseRotationHistory;
import org.faithfarm.sms.domain.SystemUser;
import org.faithfarm.sms.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CourseRotationHistoryDao {
	private static SessionFactory factory;
	private final static Logger LOGGER = Logger
			.getLogger(CourseRotationHistoryDao.class.getName());
	private static Session session;

	public CourseRotationHistoryDao() {

		LOGGER.setLevel(Level.INFO);

	}

	public CourseRotationHistory findById(Long id) {

		CourseRotationHistory CourseRotationHistory = (CourseRotationHistory) session
				.get(CourseRotationHistory.class, id);

		return CourseRotationHistory;
	}

	public List listCourseRotationHistorys() {
		LOGGER.setLevel(Level.INFO);
		List<CourseRotationHistory> list = new ArrayList<CourseRotationHistory>();
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();

			tx = session.beginTransaction();
			tx.begin();
			list = session.createQuery("FROM CourseRotationHistory").list();
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

	/* Method to INSERT CourseRotationHistory */
	public Long addCourseRotationHistory(CourseRotationHistory obj) {
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

	/* Method to UPDATE CourseRotationHistory */
	public void updateCourseRotationHistory(CourseRotationHistory obj) {
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			// CourseRotationHistory CourseRotationHistory =
			// (CourseRotationHistory)session.get(CourseRotationHistory.class,
			// CourseRotationHistoryID);
			// CourseRotationHistory.setSalary( salary );
			session.update(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {

		}
	}

	/* Method to DELETE CourseRotationHistory */
	public void deleteCourseRotationHistory(Long key) {
		Transaction tx = null;

		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();

			CourseRotationHistory obj = (CourseRotationHistory) session.get(
					CourseRotationHistory.class, key);
			session.delete(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {

		}
	}

	public List listCourseRotationHistoryByFarm(String farm) {

		List<CourseRotationHistory> list = new ArrayList<CourseRotationHistory>();
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();

			StringBuffer query = new StringBuffer(
					"from CourseRotationHistory where 1=1 ");
			query.append(" and farmBase = :farmBase ");
			Query q = session.createQuery(query.toString());
			q.setString("farmBase", farm);

			list = q.list();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {

		}
		return list;
	}

	public List listRosterBySection(Long sectionId) {

		List<CourseRotationHistory> list = new ArrayList<CourseRotationHistory>();
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();

			StringBuffer query = new StringBuffer(
					"from CourseRotationHistory where 1=1 ");
			query.append(" and sectionId = :sectionId ");
			Query q = session.createQuery(query.toString());
			q.setLong("sectionId", sectionId);

			list = q.list();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {

		}
		return list;
	}
}
