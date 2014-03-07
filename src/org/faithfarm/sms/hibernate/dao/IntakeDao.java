package org.faithfarm.sms.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.faithfarm.sms.domain.Intake;
import org.faithfarm.sms.hibernate.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

public class IntakeDao {
	private static SessionFactory factory;
	private final static Logger LOGGER = Logger.getLogger(IntakeDao.class
			.getName());
	private static Session session;

	public IntakeDao() {

		LOGGER.setLevel(Level.INFO);

		try {
			session = HibernateUtil.currentSession();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public Intake findById(Long id) {
		Transaction tx = null;
		Intake intake = null;
		try {

			session = HibernateUtil.currentSession();

			tx = session.beginTransaction();
			intake = (Intake) session.get(Intake.class, id);
			
			tx.commit();
		} catch (Exception e) {
			
				if (tx != null) {
					tx.rollback();
				}
			e.printStackTrace();
		} finally {
			
				
		}
		return intake;
	}

	public List listIntakes() {
		LOGGER.setLevel(Level.INFO);
		List<Intake> list = new ArrayList<Intake>();
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			list = session.createQuery("FROM Intake").list();
			session.flush();
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

	/* Method to INSERT Intake */
	public Long addIntake(Intake obj) {
		Transaction tx = null;
		Long key = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			key = (Long) session.save(obj);
			session.flush();
			tx.commit();
		} catch (HibernateException e) {

			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {

			
		}
		return key;
	}

	/* Method to UPDATE Intake */
	public void updateIntake(Intake obj) {
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			Intake t = (Intake) session.get(Intake.class, obj.getIntakeId());
				if (t != null) { 
				session.evict(t); 
				} 
			session.update(obj);
			session.flush();
			session.evict(obj);
			tx.commit();
		} catch (HibernateException e) {

			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {

			
		}
	}

	/* Method to DELETE Intake */
	public void deleteIntake(Long key) {
		Transaction tx = null;

		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();

			Intake obj = (Intake) session.get(Intake.class, key);
			session.delete(obj);
			session.flush();
			session.evict(obj);
			tx.commit();
		} catch (HibernateException e) {

			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {

			
		}
	}

	public List search(String entryDate, String exitDate, String lastname,
			String firstname, String ssn, String dob, String farm, String ged, String archived, String status) {

		StringBuffer query = new StringBuffer("from Intake where 1=1 ");
		
		if (lastname != null && lastname.length() > 0)
			query.append(" and lastname = :lastname ");
		if (firstname != null && firstname.length() > 0)
			query.append(" and firstname = :firstname ");
		if (ssn != null && ssn.length() > 0)
			query.append(" and ssn = :ssn ");
		if (dob != null && dob.length() > 0)
			query.append(" and dob = :dob ");
		if (farm != null && farm.length() > 0 && !farm.equals("ALL"))
			query.append(" and farmBase = :farmBase ");
		if (ged != null && ged.length() > 0)
			query.append(" and needGed = :needGed ");
		if (archived != null && archived.length() > 0)
			query.append(" and archivedFlag = :archivedFlag ");
		if (status != null && status.length() > 0)
			query.append(" and intakeStatus = :intakeStatus ");
		query.append(" Order by lastname, firstname");
		
		
		Transaction tx = null;
		List list = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			Query q = session.createQuery(query.toString());
			q.setMaxResults(200);
			if (lastname != null && lastname.length() > 0)
				q.setString("lastname", lastname);
			if (firstname != null && firstname.length() > 0)
				q.setString("firstname", firstname);
			if (ssn != null && ssn.length() > 0)
				q.setString("ssn", ssn);
			if (dob != null && dob.length() > 0)
				q.setString("dob", dob);
			if (farm != null && farm.length() > 0 && !farm.equals("ALL"))
				q.setString("farmBase", farm);
			if (ged != null && ged.length() > 0)
				q.setString("needGed", ged);
			if (archived != null && archived.length() > 0)
				q.setString("archivedFlag", archived);
			if (status != null && status.length() > 0)
				q.setString("intakeStatus", status);
			
			
			list = q.list();
			//Criteria c = session.createCriteria(Intake.class);
			//c.addOrder(Order.asc("lastname"));
			//list = c.list();
			
			tx.commit();
		} catch (HibernateException e) {

			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {

			
		}
		return list;
	}

	public List searchApplications(String entryDate, String exitDate,
			String lastname, String firstname, String ssn, String dob,
			String status, String farm) {

		StringBuffer query = new StringBuffer("from Intake where 1=1 ");
		if (lastname != null && lastname.length() > 0)
			query.append(" and lastname = :lastname ");
		if (firstname != null && firstname.length() > 0)
			query.append(" and firstname = :firstname ");
		if (ssn != null && ssn.length() > 0)
			query.append(" and ssn = :ssn ");
		if (dob != null && dob.length() > 0)
			query.append(" and dob = :dob ");
		if (farm != null && farm.length() > 0 && !farm.equals("ALL"))
			query.append(" and farmBase = :farmBase ");

		query.append(" and applicationStatus = :applicationStatus order by creation_date desc ");

		Transaction tx = null;
		List list = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			Query q = session.createQuery(query.toString());
			q.setMaxResults(200);
			
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
			else
				q.setString("applicationStatus", "Pending");

			if (farm != null && farm.length() > 0 && !farm.equals("ALL"))
				q.setString("farmBase", farm);

			list = q.list();
			
			tx.commit();
		} catch (HibernateException e) {

			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {

			
		}
		return list;
	}

	public List listClass(String classNumber, String farm) {

		List<Intake> list = new ArrayList<Intake>();
		Transaction tx = null;
		try {

			session = HibernateUtil.currentSession();
			tx = session.beginTransaction();

			StringBuffer query = new StringBuffer("from Intake where 1=1 ");
			query.append(" and class_ = :class_ ");
			query.append(" and farmBase = :farmBase ");
			query.append(" and archivedFlag = :archivedFlag ");
			query.append(" and intakeStatus = :intakeStatus order by entryDate asc ");
			Query q = session.createQuery(query.toString());
			q.setString("class_", classNumber);
			q.setString("farmBase", farm);
			q.setString("archivedFlag", "No");
			q.setString("intakeStatus", "In Program");
			list = q.list();
			
			tx.commit();
		} catch (HibernateException e) {

			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {

			
		}
		return list;
	}
}
