package org.faithfarm.sms.hibernate.data;

import java.util.List;

import org.faithfarm.sms.domain.MedicalCondition;
import org.faithfarm.sms.domain.Question;
import org.hibernate.HibernateException;

public class MedicalConditionDao extends GenericDao {
	
	public MedicalConditionDao() {
        super();
    }
	
    public MedicalCondition find(Long id) throws HibernateException {
    	return (MedicalCondition) super.findById(MedicalCondition.class, id);
    }
    public Long save(MedicalCondition intake) throws HibernateException {
    	return (Long) super.save(intake);
    }
    public void update(MedicalCondition intake) throws HibernateException {
    	super.update(intake);
    }
    public void delete(MedicalCondition intake) throws HibernateException {
    	super.delete(intake);
    }
    public List list() throws HibernateException {
    	return super.findAll(MedicalCondition.class);
    }
}
