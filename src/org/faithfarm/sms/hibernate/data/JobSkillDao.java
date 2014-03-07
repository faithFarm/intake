package org.faithfarm.sms.hibernate.data;

import java.util.List;

import org.faithfarm.sms.domain.JobSkill;
import org.hibernate.HibernateException;

public class JobSkillDao extends GenericDao {
	
	public JobSkillDao() {
        super();
    }
	
    public JobSkill find(Long id) throws HibernateException {
    	return (JobSkill) super.findById(JobSkill.class, id);
    }
    public Long save(JobSkill intake) throws HibernateException {
    	return (Long) super.save(intake);
    }
    public void update(JobSkill intake) throws HibernateException {
    	super.update(intake);
    }
    public void delete(JobSkill intake) throws HibernateException {
    	super.delete(intake);
    }
    public List list() throws HibernateException {
    	return super.findAll(JobSkill.class);
    }
    public boolean findByObjectIdAndIntakeId(Long jobSkillId, Long intakeId) throws HibernateException {
    	return super.findByIntakeIdAndObjectId(JobSkill.class,"jobSkillId",jobSkillId, intakeId);
    }
}
