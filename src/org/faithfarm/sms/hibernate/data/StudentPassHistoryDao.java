package org.faithfarm.sms.hibernate.data;

import java.util.List;

import org.faithfarm.sms.domain.StudentPassHistory;
import org.hibernate.HibernateException;

public class StudentPassHistoryDao extends GenericDao {
	
	public StudentPassHistoryDao() {
        super();
    }
	
    public StudentPassHistory find(Long id) throws HibernateException {
    	return (StudentPassHistory) super.findById(StudentPassHistory.class, id);
    }
    public Long save(StudentPassHistory intake) throws HibernateException {
    	return (Long) super.save(intake);
    }
    public void update(StudentPassHistory intake) throws HibernateException {
    	super.update(intake);
    }
    public void delete(StudentPassHistory intake) throws HibernateException {
    	super.delete(intake);
    }
    public List list() throws HibernateException {
    	return super.findAll(StudentPassHistory.class);
    }
	public List search(String passDate1, String passDate2) throws HibernateException {
		return super.searchPasses(passDate1,passDate2);
	}
}
