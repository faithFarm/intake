package org.faithfarm.sms.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.faithfarm.sms.domain.CwtProgramMetric;
import org.faithfarm.sms.domain.CwtRoster;
import org.faithfarm.sms.domain.SystemUser;
import org.faithfarm.sms.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CwtRosterDao {
	private static SessionFactory factory;
	private final static Logger LOGGER = Logger.getLogger(CwtRosterDao.class
			.getName());
	private static Session session;

	public CwtRosterDao() {

		LOGGER.setLevel(Level.INFO);

		try {
			session = HibernateUtil.currentSession();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public CwtRoster findById(Long id) {

		CwtRoster CwtRoster = (CwtRoster) session.get(CwtRoster.class, id);

		return CwtRoster;
	}

	public List listCwtRosters() {
		LOGGER.setLevel(Level.INFO);
		List<CwtRoster> list = new ArrayList<CwtRoster>();
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();

			tx = session.beginTransaction();
			tx.begin();
			list = session.createQuery("FROM CwtRoster").list();
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

	/* Method to INSERT CwtRoster */
	public Long addCwtRoster(CwtRoster obj) {
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

	/* Method to UPDATE CwtRoster */
	public void updateCwtRoster(CwtRoster obj) {
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			CwtRoster t = (CwtRoster) session.get(CwtRoster.class, obj.getRosterId());
			if (t != null) { 
			session.evict(t); 
			} 
			// CwtRoster CwtRoster =
			// (CwtRoster)session.get(CwtRoster.class, CwtRosterID);
			// CwtRoster.setSalary( salary );
			session.update(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			
		}
	}

	/* Method to DELETE CwtRoster */
	public void deleteCwtRoster(Long key) {
		Transaction tx = null;

		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();

			CwtRoster obj = (CwtRoster) session.get(CwtRoster.class, key);
			session.delete(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			
		}
	}

	public List search(String entryDate, String exitDate, String lastname,
			String firstname, String ssn, String dob, String status, String farm) {

		StringBuffer query = new StringBuffer("from CwtRoster where 1=1 ");
		if (lastname != null && lastname.length() > 0)
			query.append(" and lastname = :lastname ");
		if (firstname != null && firstname.length() > 0)
			query.append(" and firstname = :firstname ");
		if (ssn != null && ssn.length() > 0)
			query.append(" and ssn = :ssn ");
		if (dob != null && dob.length() > 0)
			query.append(" and dob = :dob ");
		if (status != null && status.length() > 0)
			query.append(" and applicationStatus = :applicationStatus ");
		if (farm != null && farm.length() > 0 && !farm.equals("ALL"))
			query.append(" and farmBase = :farmBase ");

		Query q = session.createQuery(query.toString());

		if (lastname != null && lastname.length() > 0)
			q.setString("lastname", lastname);
		if (firstname != null && firstname.length() > 0)
			q.setString("firstname", firstname);
		if (ssn != null && ssn.length() > 0)
			q.setString("ssn", ssn);
		if (dob != null && dob.length() > 0)
			q.setString("dob", dob);
		if (status != null && status.length() > 0)
			q.setString("applicationStatus", status);
		if (farm != null && farm.length() > 0 && !farm.equals("ALL"))
			q.setString("farmBase", farm);

		List list = q.list();
		return list;
	}

	public List listRosterBySectionAndFarm(Long sectionId, String farm) {

		List<CwtRoster> list = new ArrayList<CwtRoster>();
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();

			StringBuffer query = new StringBuffer("from CwtRoster where 1=1 ");
			query.append(" and farmBase = :farmBase ");
			query.append(" and sectionId = :sectionId ");
			Query q = session.createQuery(query.toString());
			q.setString("farmBase", farm);
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

	public List listRosterBySection(Long sectionId) {

		List<CwtRoster> list = new ArrayList<CwtRoster>();
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();

			StringBuffer query = new StringBuffer("from CwtRoster where 1=1 ");
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
