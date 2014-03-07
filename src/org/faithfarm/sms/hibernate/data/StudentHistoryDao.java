package org.faithfarm.sms.hibernate.data;

import java.util.List;

import org.faithfarm.sms.domain.StudentHistory;
import org.hibernate.HibernateException;

public class StudentHistoryDao extends GenericDao {
	
	public StudentHistoryDao() {
        super();
    }
	
    public StudentHistory find(Long id) throws HibernateException {
    	return (StudentHistory) super.findById(StudentHistory.class, id);
    }
    public Long save(StudentHistory intake) throws HibernateException {
    	return (Long) super.save(intake);
    }
    public void update(StudentHistory intake) throws HibernateException {
    	super.update(intake);
    }
    public void delete(StudentHistory intake) throws HibernateException {
    	super.delete(intake);
    }
    public List list() throws HibernateException {
    	return super.findAll(StudentHistory.class);
    }
}
