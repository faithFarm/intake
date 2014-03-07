package org.faithfarm.sms.hibernate.data;

import java.util.List;

import org.faithfarm.sms.domain.IntakeJobSkill;
import org.hibernate.HibernateException;

public class IntakeJobSkillDao extends GenericDao {
	
	public IntakeJobSkillDao() {
        super();
    }
	
    public IntakeJobSkill find(Long id) throws HibernateException {
    	return (IntakeJobSkill) super.findById(IntakeJobSkill.class, id);
    }
    public Long save(IntakeJobSkill intake) throws HibernateException {
    	return (Long) super.save(intake);
    }
    public void update(IntakeJobSkill intake) throws HibernateException {
    	super.update(intake);
    }
    public void delete(IntakeJobSkill intake) throws HibernateException {
    	super.delete(intake);
    }
    public List list() throws HibernateException {
    	return super.findAll(IntakeJobSkill.class);
    }
}
