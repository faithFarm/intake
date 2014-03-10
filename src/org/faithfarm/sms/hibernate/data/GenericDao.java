package org.faithfarm.sms.hibernate.data;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.faithfarm.sms.domain.Intake;
import org.faithfarm.sms.domain.SystemUser;
import org.faithfarm.sms.domain.ViewCwtIntake;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GenericDao {

	private static SessionFactory factory;
	private final static Logger LOGGER = Logger.getLogger(IntakeDao.class
			.getName());

	public GenericDao() {
		LOGGER.setLevel(Level.SEVERE);
	}

	public Object findById(Class c, Long id) {
		Object obj = null;
		Session session = null;
		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			obj = session.get(c, id);
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return obj;
	}
	
	public List listAll(Class c) {
		Object obj = null;
		Session session = null;
		List list = new ArrayList();
		try {
			StringBuffer q = new StringBuffer("");
			q.append("from " + c.getName());
			session = HibernateFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery(q.toString());
			list = query.list();
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return list;
	}

	public List listAllByFarm(Class c, String farm) {
		Object obj = null;
		Session session = null;
		List list = new ArrayList();
		try {
			StringBuffer q = new StringBuffer("");
			q.append("from " + c.getName());
			q.append(" where farmBase = :farmBase order by currentClass, entryDate ");
			session = HibernateFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery(q.toString());
			query.setString("farmBase", farm);
			list = query.list();
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return list;
	}


	public Long save(Object obj) {
		Long key = null;
		Session session = null;
		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			key = (Long) session.save(obj);
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}

		} catch (HibernateException e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return key;
	}

	public void update(Object obj) {
		Session session = null;
		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			session.update(obj);
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (HibernateException e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

	public void delete(Object obj) {
		Session session = null;
		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			session.delete(obj);
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (HibernateException e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

	protected List findAll(Class clazz) {
		List objects = null;
		Session session = null;
		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			StringBuffer q = new StringBuffer("");
			q.append("from " + clazz.getName());
			if ("com.yada180.sms.domain.CwtJob".equals(clazz.getName()
					.toString()))
				q.append(" order by title");
			if ("com.yada180.sms.domain.CwtSupervisor".equals(clazz.getName()
					.toString()))
				q.append(" order by firstname");
			if ("com.yada180.sms.domain.CwtModuleSection".equals(clazz
					.getName().toString()))
				q.append(" where status='Active' order by farmBase, moduleOfferingId ");
			if ("com.yada180.sms.domain.CwtModules".equals(clazz.getName()
					.toString()))
				q.append(" order by moduleName");
			if ("com.yada180.sms.domain.CwtProgram".equals(clazz.getName()
					.toString()))
				q.append(" order by programName");
			Query query = session.createQuery(q.toString());
			objects = query.list();
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (HibernateException e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return objects;
	}

	protected List filterSection(Class clazz, String farm, Long moduleId) {
		List objects = null;
		Session session = null;
		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			StringBuffer q = new StringBuffer(" from " + clazz.getName()
					+ " where 1=1 ");
			if (farm != null && farm.length() > 0)
				q.append(" and farmBase = :farmBase");
			if (moduleId != null && moduleId > 0)
				q.append(" and moduleId = :moduleId");
			Query query = session.createQuery(q.toString());

			if (farm != null && farm.length() > 0)
				query.setString("farmBase", farm);
			if (moduleId != null && moduleId > 0)
				query.setLong("moduleId", moduleId);

			objects = query.list();
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (HibernateException e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return objects;
	}

	protected List findAllByFarm(Class clazz, String farm) {
		List objects = null;
		Session session = null;
		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from " + clazz.getName()
					+ " where farmBase = :farmBase ");
			query.setString("farmBase", farm);
			objects = query.list();
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (HibernateException e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return objects;
	}

	public List search(String entryDate, String exitDate, String lastname,
			String firstname, String ssn, String dob, String farm, String ged,
			String archived, String status, String currentClass, Long jobId,
			Long supervisorId, String driverFlag, Long cwtProgramId) {

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
		if (currentClass != null && currentClass.length() > 0)
			query.append(" and class_ = :class_ ");
		if (jobId != null && jobId != 0)
			query.append(" and jobId = :jobId ");
		if (supervisorId != null && supervisorId != 0)
			query.append(" and supervisorId = :supervisorId ");
		if (driverFlag != null && driverFlag.length() > 0)
			query.append(" and dlFlag = :dlFlag ");
		if (cwtProgramId != null)
			query.append(" and cwtProgramId = :cwtProgramId ");
		query.append(" Order by lastname, firstname");

		List list = null;
		Session session = null;
		try {

			session = HibernateFactory.openSession();
			session.beginTransaction();
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
			if (currentClass != null && currentClass.length() > 0)
				q.setString("class_", currentClass);
			if (jobId != null && jobId != 0)
				q.setLong("jobId", jobId);
			if (supervisorId != null && supervisorId != 0)
				q.setLong("supervisorId", supervisorId);
			if (driverFlag != null && driverFlag.length() > 0)
				q.setString("dlFlag", driverFlag);
			if (cwtProgramId != null)
				q.setLong("cwtProgramId", cwtProgramId);

			list = q.list();
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (HibernateException e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return list;
	}

	public List searchApplications(String entryDate, String exitDate,
			String lastname, String firstname, String ssn, String dob,
			String status, String driverFlag, String gedFlag, String farm) {

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
		if (driverFlag != null && driverFlag.length() > 0)
			query.append(" and dlFlag = :dlFlag ");
		if (gedFlag != null && gedFlag.length() > 0)
			query.append(" and needGed = :needGed ");
		if (status != null && status.length() > 0 && !status.equals("ALL"))
			query.append(" and applicationStatus = :applicationStatus)");
		else
			query.append(" and applicationStatus in (:applicationStatus)");
			
		query.append(" order by creation_date desc");
		List list = null;
		Session session = null;
		try {

			session = HibernateFactory.openSession();
			session.beginTransaction();
			Query q = session.createQuery(query.toString());
			q.setMaxResults(200);
			List<String> statuses = new ArrayList<String>();
			statuses.add("Pending");
			statuses.add("Accepted");
			statuses.add("Denied");
			statuses.add("Withdrawn");
			statuses.add("Waitlist");
			
			if (lastname != null && lastname.length() > 0)
				q.setString("lastname", lastname);
			if (firstname != null && firstname.length() > 0)
				q.setString("firstname", firstname);
			if (ssn != null && ssn.length() > 0)
				q.setString("ssn", ssn);
			if (dob != null && dob.length() > 0)
				q.setString("dob", dob);
			if (status != null && status.length() > 0 && !status.equals("ALL"))
				q.setString("applicationStatus", status);
			else
				q.setParameterList("applicationStatus", statuses);
			
			if (farm != null && farm.length() > 0 && !farm.equals("ALL"))
				q.setString("farmBase", farm);
			if (driverFlag != null && driverFlag.length() > 0)
				q.setString("dlFlag", driverFlag);
			if (gedFlag != null && gedFlag.length() > 0)
				q.setString("needGed", gedFlag);

			list = q.list();
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (HibernateException e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	public List listClass(String classNumber, String farm) {

		List<Intake> list = new ArrayList<Intake>();
		Transaction tx = null;
		Session session = null;
		
		try {

			session = HibernateFactory.openSession();
			session.beginTransaction();
			StringBuffer query = new StringBuffer("from Intake where 1=1 ");
			query.append(" and class_ = :class_ ");
			query.append(" and farmBase = :farmBase ");
			query.append(" and archivedFlag = :archivedFlag ");
			//if ("Fresh Start".equals(classNumber)||"Intern".equals(classNumber)||"SLS".equals(classNumber))
			//	query.append(" and intakeStatus in ( :intakeStatus )");
			//else
				query.append(" and intakeStatus IN (:intakeStatus) ");
			
			query.append(" ORDER BY STR_TO_DATE(entry_date, '%m/%d/%Y') asc ");
			Query q = session.createQuery(query.toString());
			q.setString("class_", classNumber);
			q.setString("farmBase", farm);
			q.setString("archivedFlag", "No");			
			if ("Fresh Start".equals(classNumber)) {
				List<String>status=new ArrayList();
				status.add("In Program");
				status.add("Left Prop./Graduated to Fresh Start");
				q.setParameterList("intakeStatus", status);
			}
			else if ("Intern".equals(classNumber)) {
				List<String>status=new ArrayList();
				status.add("In Program");
				status.add("Left Prop./Graduated to Intern");
				q.setParameterList("intakeStatus", status);
			}
			else if ("SLS".equals(classNumber)) {
				List<String>status=new ArrayList();
				status.add("In Program");
				status.add("Left Prop./Graduated to SLS");
				q.setParameterList("intakeStatus", status);
			}
			else {
				List<String>status=new ArrayList();
				status.add("In Program");
				q.setParameterList("intakeStatus", status);
			}
			list =q.list();
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (HibernateException e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return list;
	}
	
	public List listClassSQL(String classNumber, String farm) {

		List<Intake> list = new ArrayList<Intake>();
		Transaction tx = null;
		Session session = null;
		
		try {

			session = HibernateFactory.openSession();
			session.beginTransaction();

			StringBuffer query = new StringBuffer(" select * from intake where 1=1 ");
			query.append(" and class = :class ");
			query.append(" and farm_base = :farmBase ");
			query.append(" and archived_flag = :archivedFlag ");
			query.append(" and intake_status IN (:intakeStatus) ");
			query.append(" ORDER BY STR_TO_DATE(entry_date, '%m/%d/%Y') asc ");
			SQLQuery q = session.createSQLQuery(query.toString());
			q.setParameter("class", classNumber);
			q.setParameter("farmBase", farm);
			q.setParameter("archivedFlag", "No");			
			if ("Fresh Start".equals(classNumber)) {
				List<String>status=new ArrayList();
				status.add("In Program");
				status.add("Left Prop./Graduated to Fresh Start");
				q.setParameterList("intakeStatus", status);
			}
			else if ("Intern".equals(classNumber)) {
				List<String>status=new ArrayList();
				status.add("In Program");
				status.add("Left Prop./Graduated to Intern");
				q.setParameterList("intakeStatus", status);
			}
			else if ("SLS".equals(classNumber)) {
				List<String>status=new ArrayList();
				status.add("In Program");
				status.add("Left Prop./Graduated to SLS");
				q.setParameterList("intakeStatus", status);
			}
			else {
				List<String>status=new ArrayList();
				status.add("In Program");
				q.setParameterList("intakeStatus", status);
			}
			q.addEntity(Intake.class);
			list = q.list();
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (HibernateException e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return list;
	}

	public List listByStatus(String status, String farm) {

		List<Intake> list = new ArrayList<Intake>();
		Transaction tx = null;
		Session session = null;
		try {

			session = HibernateFactory.openSession();
			session.beginTransaction();

			StringBuffer query = new StringBuffer("from Intake where ");
			query.append(" intakeStatus = :intakeStatus ");
			query.append(" and farmBase = :farmBase ");
			query.append(" and archivedFlag = :archivedFlag ");
			Query q = session.createQuery(query.toString());

			q.setString("farmBase", farm);
			q.setString("archivedFlag", "No");
			q.setString("intakeStatus", status);
			list = q.list();
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (HibernateException e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return list;
	}

	public List findIntakeByCwtProgram(Long programId, String farm) {

		List<Intake> list = new ArrayList<Intake>();
		Transaction tx = null;
		Session session = null;
		try {

			session = HibernateFactory.openSession();
			session.beginTransaction();

			StringBuffer query = new StringBuffer("from Intake where ");
			query.append(" intakeStatus = :intakeStatus ");
			query.append(" and farmBase = :farmBase ");
			query.append(" and cwtProgramId = :cwtProgramId ");
			query.append(" and archivedFlag = :archivedFlag ");
			Query q = session.createQuery(query.toString());

			q.setString("farmBase", farm);
			q.setString("archivedFlag", "No");
			q.setString("intakeStatus", "In Program");
			q.setLong("cwtProgramId", programId);
			list = q.list();
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (HibernateException e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return list;
	}

	public List findByObjectId(Class c, String objectIdName, Long id) {

		LOGGER.setLevel(Level.INFO);
		List<Object> list = new ArrayList<Object>();
		Session session = null;
		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			StringBuffer query = new StringBuffer("from " + c.getName()
					+ " where " + objectIdName + " = :" + objectIdName);

			if ("com.yada180.sms.domain.CwtRoster".equals(c.getName()
					.toString()))
				query.append(" and archivedFlag='Yes' ");

			Query q = session.createQuery(query.toString());
			q.setLong(objectIdName, id);
			list = q.list();
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return list;
	}
	
	public List findRosterBySectionIdRosterDate(Long id, String date) {

		LOGGER.setLevel(Level.INFO);
		List<Object> list = new ArrayList<Object>();
		Session session = null;
		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			StringBuffer query = new StringBuffer("from CwtRoster "
					+ " where sectionId = :sectionId and rosterDate = :rosterDate");
			
			Query q = session.createQuery(query.toString());
			q.setLong("sectionId", id);
			q.setString("rosterDate", date);
			list = q.list();
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return list;
	}

	
	public List findBySectionIdRosterDate(Class c, Long sectionId, String rosterDate) {

		LOGGER.setLevel(Level.INFO);
		List<Object> list = new ArrayList<Object>();
		Session session = null;
		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			StringBuffer query = new StringBuffer("from " + c.getName()
					+ " where sectionId = :sectionId and rosterDate = :rosterDate");
		
			Query q = session.createQuery(query.toString());
			q.setLong("sectionId", sectionId);
			q.setString("rosterDate", rosterDate);
			list = q.list();
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return list;
	}

	public List findArchivedRosters(Class c, String objectIdName, Long id,
			String archivedFlag) {

		LOGGER.setLevel(Level.INFO);
		List<Object> list = new ArrayList<Object>();
		Session session = null;
		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			StringBuffer query = new StringBuffer("from " + c.getName()
					+ " where " + objectIdName + " = :" + objectIdName
					+ "  and archivedFlag = :archivedFlag ");
			Query q = session.createQuery(query.toString());
			q.setLong(objectIdName, id);
			if (archivedFlag != null)
				q.setString("archivedFlag", archivedFlag);
			list = q.list();
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return list;
	}

	public List findRosterHistoryByIntakeId(Long id) {

		LOGGER.setLevel(Level.INFO);
		List<Object> list = new ArrayList<Object>();
		Session session = null;
		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			StringBuffer query = new StringBuffer(
					"from CwtRoster where intakeId = :intakeId");
			Query q = session.createQuery(query.toString());
			q.setLong("intakeId", id);
			list = q.list();
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return list;
	}

	public boolean findByIntakeIdAndObjectId(Class c, String objectIdName,
			Long intakeId, Long id) {

		LOGGER.setLevel(Level.INFO);
		List<Object> list = new ArrayList<Object>();
		boolean match = false;
		Session session = null;
		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			StringBuffer query = new StringBuffer("from " + c.getName()
					+ " where " + objectIdName + " = :" + objectIdName
					+ " and intakeId = :intakeId");
			Query q = session.createQuery(query.toString());

			q.setLong(objectIdName, id);
			q.setLong("intakeId", intakeId);
			list = q.list();
			if (list.size() > 0)
				match = true;
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return match;
	}

	public Object findByObjectIdOnLikeClause(Class c, String objectName,
			String value) {

		LOGGER.setLevel(Level.INFO);
		Session session = null;
		List<Object> list = new ArrayList<Object>();
		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			StringBuffer query = new StringBuffer("from " + c.getName()
					+ " where " + objectName + " LIKE :" + objectName);
			Query q = session.createQuery(query.toString());
			q.setParameter(objectName, "%" + value + "%");
			list = q.list();
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return list.get(0);
	}

	public ViewCwtIntake findCwtIntakeByIntakeId(Class c, Long id) {
		
		LOGGER.setLevel(Level.INFO);
		Session session = null;
		List<Object> list = new ArrayList<Object>();
		ViewCwtIntake intake=null;
		
		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			StringBuffer query = new StringBuffer("from " + c.getName()
					+ " where intakeId = :intakeId ");
			Query q = session.createQuery(query.toString());
			q.setLong("intakeId", id);
			list = q.list();
			if (list.size()>0)
				intake=(ViewCwtIntake)list.get(0);
			
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return intake;
	}
	
	public List findByIntakeId(Class c, Long id) {
		LOGGER.setLevel(Level.INFO);
		Session session = null;
		List<Object> list = new ArrayList<Object>();
		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			StringBuffer query = new StringBuffer("from " + c.getName()
					+ " where intakeId = :intakeId ");
			Query q = session.createQuery(query.toString());
			q.setLong("intakeId", id);
			list = q.list();
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return list;
	}

	public SystemUser authenticate(String username, String password) {
		SystemUser user = null;
		Session session = null;
		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			Query q = session
					.createQuery("from SystemUser where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);

			List result = q.list();
			if (result.size() > 0)
				user = (SystemUser) result.get(0);
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}

		return user;
	}

	public List searchPass(String passDate1, String passDate2) {

		StringBuffer query = new StringBuffer(
				"from StudentPassHistory where 1=1 ");
		Session session = null;
		if ( (passDate1 != null && passDate1.length() > 0) && (passDate2==null||passDate2.length()==0))
			query.append(" and passDate = :passDate1 ");
		else if ( (passDate1 != null && passDate1.length() > 0) && (passDate2!=null||passDate2.length()>0))
			query.append(" and passDate between :passDate1 and :passDate2 ");

		
		List list = null;
		try {

			session = HibernateFactory.openSession();
			session.beginTransaction();
			Query q = session.createQuery(query.toString());
			q.setMaxResults(200);
			if (passDate1 != null && passDate1.length() > 0)
				q.setParameter("passDate1", passDate1);
			if (passDate2 != null && passDate2.length() > 0)
				q.setParameter("passDate2", passDate2);

			list = q.list();
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (HibernateException e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return list;
	}

	public List searchRosterList(String farm) {
		Session session = null;
		StringBuffer query = new StringBuffer("from Intake where 1=1 ");
		query.append(" and intakeStatus = :intakeStatus ");
		query.append(" and archivedFlag = :archivedFlag ");
		if (farm != null && farm.length() > 0 && !farm.equals("ALL"))
			query.append(" and farmBase = :farmBase ");
		query.append(" and class_ in ('Orientation','1','2','3','4','5','6','Fresh Start','SLS','Intern') order by firstname, lastname ");

		List list = null;
		try {

			session = HibernateFactory.openSession();
			session.beginTransaction();
			Query q = session.createQuery(query.toString());
			q.setMaxResults(200);
			if (farm != null && farm.length() > 0 && !farm.equals("ALL"))
				q.setString("farmBase", farm);
			q.setString("intakeStatus", "In Program");
			q.setString("archivedFlag", "No");

			list = q.list();
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (HibernateException e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return list;
	}

	public List findIntakeModules(Long id) {
		Session session = null;
		StringBuffer query = new StringBuffer(
				" select distinct module_id from cwt_roster where intake_id= :intakeId ");

		List list = null;
		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			SQLQuery q = session.createSQLQuery(query.toString());
			q.setParameter("intakeId", id);
			list = q.list();
			session.flush();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			session.close();
		}
		return list;
	}

	public List findIntakePrograms(Long id) {
		Session session = null;
		StringBuffer query = new StringBuffer(
				" select distinct(cwt_program.program_id) from  ");
		query.append("cwt_roster inner join cwt_modules ");
		query.append("on cwt_roster.module_id=cwt_modules.module_id ");
		query.append("inner join cwt_program ");
		query.append("on cwt_program.program_id=cwt_modules.program_id  ");
		query.append("where cwt_roster.intake_id= :intakeId ");

		List list = null;
		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			SQLQuery q = session.createSQLQuery(query.toString());
			q.setParameter("intakeId", id);
			list = q.list();
			session.flush();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			session.close();
		}
		return list;
	}

	public Long findProgramIdBySectionId(Long id) {
		Session session = null;
		StringBuffer query = new StringBuffer(" select cwt_program.PROGRAM_ID");
		query.append(" from cwt_program inner join cwt_modules");
		query.append(" on cwt_program.program_id = cwt_modules.program_id");
		query.append(" 	inner join cwt_module_section");
		query.append(" 	on cwt_modules.module_id = cwt_module_section.module_id");
		query.append(" 	where");
		query.append(" 	module_offering_id= :moduleOfferingId ");

		List list = null;
		BigInteger retId = null;

		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			SQLQuery q = session.createSQLQuery(query.toString());
			q.setParameter("moduleOfferingId", id);
			list = q.list();

			if (list.size() > 0)
				retId = (BigInteger) list.get(0);

			session.flush();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			session.close();
		}
		return retId.longValue();
	}
	
	public Long findSectionIdByModuleIdAndFarm(Long id, String farm) {
		Session session = null;
		StringBuffer query = new StringBuffer(" select cwt_module_section.module_offering_ID");
		query.append(" from cwt_module_section inner join cwt_modules");
		query.append(" on cwt_module_section.module_id = cwt_modules.module_id");
		query.append(" 	where");
		query.append(" 	cwt_modules.module_id= :moduleId and farm_base =:farmBase ");

		List list = null;
		BigInteger retId = null;
		Long lId = null;
		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			SQLQuery q = session.createSQLQuery(query.toString());
			q.setParameter("moduleId", id);
			q.setParameter("farmBase", farm);
			list = q.list();

			if (list.size() > 0) {
				retId = (BigInteger) list.get(0);
				lId=retId.longValue(); 
			}
			session.flush();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			session.close();
		}
		return lId;
	}


	public List searchRosters(Long cwtModuleId, String rosterDate, String archivedFlag, String farmBase, Long userId) {
		Session session = null;
		StringBuffer query = new StringBuffer(" select cwt_roster.roster_id ");
		query.append(" from cwt_roster inner join cwt_modules");
		query.append(" on cwt_roster.module_id = cwt_modules.module_id");
		query.append(" inner join cwt_module_section");
		query.append(" on cwt_modules.module_id = cwt_module_section.module_id");
		query.append(" inner join cwt_program");
		query.append(" on cwt_modules.program_id = cwt_program.program_id");
		query.append(" where 1=1 ");
		if (cwtModuleId!=null&&!cwtModuleId.equals(new Long(0)))
			query.append(" and cwt_modules.module_id= :cwtModuleId");
		query.append(" and cwt_roster.archived_flag= :archivedFlag");
		if (rosterDate!=null&&rosterDate.length()>0)
			query.append(" and cwt_roster.roster_date= :rosterDate");
		query.append(" and cwt_module_section.farm_base= :farmBase");
		query.append (" and cwt_module_section.module_offering_id = cwt_roster.section_id");
		if (userId!=null && !userId.equals(new Long(999)))
			query.append(" and cwt_module_section.administrator_id= :userId");
		
		List list = null;
		BigInteger retId = null;

		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			SQLQuery q = session.createSQLQuery(query.toString());
			if (cwtModuleId!=null&&!cwtModuleId.equals(new Long(0)))
				q.setParameter("cwtModuleId", cwtModuleId);
			q.setParameter("archivedFlag", archivedFlag);
			if (rosterDate!=null&&rosterDate.length()>0)
				q.setParameter("rosterDate", rosterDate);
			q.setParameter("farmBase", farmBase);
			if (userId!=null && !userId.equals(new Long(999)))
				q.setParameter("userId", userId);
			list = q.list();

			session.flush();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			session.close();
		}
		return list;
	}
	
	
	public List findAll(Class clazz, String farm, Long programId, String moduleSequence) {
		List objects = null;
		Session session = null;
		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			StringBuffer q = new StringBuffer("");
			q.append("from " + clazz.getName()+ " where farmBase = :farmBase");
			
			if (programId!=null&&!programId.equals(new Long("0")))
				q.append(" and programId = :programId");
			if (moduleSequence!=null&&moduleSequence.length()>0)
				q.append(" and moduleSeq = :moduleSeq");

			if ("com.yada180.sms.domain.ViewCwtSection".equals(clazz.getName()))
				q.append(" order by moduleSeq");

			Query query = session.createQuery(q.toString());
			query.setString("farmBase", farm);

			if (programId!=null&&!programId.equals(new Long("0")))
				query.setLong("programId", programId);
			if (moduleSequence!=null&&moduleSequence.length()>0)
				query.setString("moduleSeq", moduleSequence);
			
			objects = query.list();
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (HibernateException e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return objects;
	}
	
	public List findAll(Class clazz, String farm, Long programId, String moduleSeq, Long moduleId, String archivedFlag, String sDate) {
		List objects = null;
		Session session = null;
		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			StringBuffer q = new StringBuffer("");
			q.append("from " + clazz.getName()+ " where farmBase = :farmBase and archivedFlag = :archivedFlag");
			if (programId!=null&&!programId.equals(new Long(0)))
				q.append(" and programId = :programId");
			if (moduleSeq!=null&&moduleSeq.length()!=0)
				q.append(" and sequence = :sequence");
			if (moduleId!=null&&!moduleId.equals(new Long(0)))
				q.append(" and moduleId = :moduleId");
			if (sDate!=null&&sDate.length()>0)
				q.append(" and rosterDate = :rosterDate");
			q.append(" order by moduleName");
			Query query = session.createQuery(q.toString());
			query.setString("farmBase", farm);
			query.setString("archivedFlag", archivedFlag);
			if (programId!=null&&!programId.equals(new Long(0)))
				query.setLong("programId", programId);
			if (moduleId!=null&&!moduleId.equals(new Long(0)))
				query.setLong("moduleId", moduleId);
			if (sDate!=null&&sDate.length()>0)
				query.setString("rosterDate", sDate);
			if (moduleSeq!=null&&moduleSeq.length()!=0)
				query.setString("sequence", moduleSeq);
			objects = query.list();
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (HibernateException e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return objects;
	}
	
	public List searchViewCwtRoster(String farm, Long programId, String moduleSeq, Long cwtModuleId, String archivedFlag, String rosterDate) {
		Session session = null;
		StringBuffer query = new StringBuffer(" select * from v_cwt_rosters ");
		query.append(" where 1=1 ");
		if (cwtModuleId!=null&&!cwtModuleId.equals(new Long(0)))
			query.append(" and v_cwt_rosters.module_id= :cwtModuleId");
		if (rosterDate!=null&&rosterDate.length()>0)
			query.append(" and v_cwt_rosters.roster_date= :rosterDate");
		if (programId!=null&&!programId.equals(new Long(0)))
			query.append(" and v_cwt_rosters.program_id= :programId");
		if (moduleSeq!=null&&moduleSeq.length()>0)
			query.append(" and v_cwt_rosters.module_sequence= :moduleSeq");
		query.append(" and v_cwt_rosters.farm_base= :farmBase");
		query.append(" and v_cwt_rosters.archived_flag= :archivedFlag");
		
		List list = null;
		BigInteger retId = null;

		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			SQLQuery q = session.createSQLQuery(query.toString());
			//q.addEntity(ViewCwtRoster.class);
			if (programId!=null&&!programId.equals(new Long(0)))
				q.setParameter("programId", programId);
			if (cwtModuleId!=null&&!cwtModuleId.equals(new Long(0)))
				q.setParameter("cwtModuleId", cwtModuleId);
			if (rosterDate!=null&&rosterDate.length()>0)
				q.setParameter("rosterDate", rosterDate);
			if (moduleSeq!=null&&moduleSeq.length()!=0)
				q.setParameter("moduleSeq", moduleSeq);
			q.setParameter("farmBase", farm);
			q.setParameter("archivedFlag", "Yes");
			
			list = q.list();

			session.flush();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			session.close();
		}
		return list;
	}
	
	
	public Integer countCwtModules(Long programId) {
		Session session = null;
		StringBuffer query = new StringBuffer(" select module_id from cwt_modules where program_id= :programId");
		
		List list = null;
		BigInteger retId = null;

		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			SQLQuery q = session.createSQLQuery(query.toString());
			q.setParameter("programId", programId);
			list = q.list();

			
			
			session.flush();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			session.close();
		}
		return list.size();
	}
	
	
	public Integer countCwtModulesForIntake(Long intakeId, Long programId) {
		Session session = null;
		StringBuffer query = new StringBuffer(" select distinct cwt_roster.module_id ");
			query.append("from cwt_roster ");
			query.append("inner join cwt_modules on cwt_roster.module_id = cwt_modules.module_id ");
			query.append("where intake_id= :intakeId and cwt_modules.program_id= :programId ");
		
		List list = null;
		BigInteger retId = null;

		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			SQLQuery q = session.createSQLQuery(query.toString());
			q.setParameter("programId", programId);
			q.setParameter("intakeId", intakeId);
			list = q.list();
			session.flush();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			session.close();
		}
		return list.size();
	}
	
	public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
	    List<T> r = new ArrayList<T>(c.size());
	    for(Object o: c)
	      r.add(clazz.cast(o));
	    return r;
	}
	


}
