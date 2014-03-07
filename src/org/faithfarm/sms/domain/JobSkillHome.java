package org.faithfarm.sms.domain;

// Generated Sep 19, 2013 1:34:58 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class JobSkill.
 * @see org.faithfarm.sms.domain.JobSkill
 * @author Hibernate Tools
 */
public class JobSkillHome {

	private static final Log log = LogFactory.getLog(JobSkillHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(JobSkill transientInstance) {
		log.debug("persisting JobSkill instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(JobSkill instance) {
		log.debug("attaching dirty JobSkill instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(JobSkill instance) {
		log.debug("attaching clean JobSkill instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(JobSkill persistentInstance) {
		log.debug("deleting JobSkill instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public JobSkill merge(JobSkill detachedInstance) {
		log.debug("merging JobSkill instance");
		try {
			JobSkill result = (JobSkill) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public JobSkill findById(java.lang.Long id) {
		log.debug("getting JobSkill instance with id: " + id);
		try {
			JobSkill instance = (JobSkill) sessionFactory.getCurrentSession()
					.get("com.yada180.sms.domain.JobSkill", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(JobSkill instance) {
		log.debug("finding JobSkill instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.yada180.sms.domain.JobSkill")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
