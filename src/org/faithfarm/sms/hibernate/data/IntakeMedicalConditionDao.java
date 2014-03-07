package org.faithfarm.sms.hibernate.data;

import java.util.ArrayList;
import java.util.List;

import org.faithfarm.sms.domain.IntakeMedicalCondition;
import org.hibernate.HibernateException;

public class IntakeMedicalConditionDao extends GenericDao {
	
	public IntakeMedicalConditionDao() {
        super();
    }
	
    public IntakeMedicalCondition find(Long id) throws HibernateException {
    	return (IntakeMedicalCondition) super.findById(IntakeMedicalCondition.class, id);
    }
    public Long save(IntakeMedicalCondition intake) throws HibernateException {
    	return (Long) super.save(intake);
    }
    public void update(IntakeMedicalCondition intake) throws HibernateException {
    	super.update(intake);
    }
    public void delete(IntakeMedicalCondition intake) throws HibernateException {
    	super.delete(intake);
    }
    public List list() throws HibernateException {
    	return super.findAll(IntakeMedicalCondition.class);
    }
    public List<IntakeMedicalCondition> findByIntakeId(IntakeMedicalCondition condition, Long id) throws HibernateException {
    	return (List<IntakeMedicalCondition>)super.findByIntakeId(IntakeMedicalCondition.class, id);
    }
}
