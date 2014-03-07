package org.faithfarm.sms.hibernate.data;

import java.util.List;

import org.faithfarm.sms.domain.CwtJob;
import org.faithfarm.sms.domain.CwtProgram;
import org.hibernate.HibernateException;

public class CwtJobDao extends GenericDao {
	
	public CwtJobDao() {
        super();
    }
	
    public CwtJob find(Long id) throws HibernateException {
    	return (CwtJob) super.findById(CwtJob.class, id);
    }
    public List<CwtJob> findAll() throws HibernateException {
    	return (List<CwtJob>) super.findAll(CwtJob.class);
    }
    public Long save(CwtJob intake) throws HibernateException {
    	return (Long) super.save(intake);
    }
    public void update(CwtJob intake) throws HibernateException {
    	super.update(intake);
    }
    public void delete(CwtJob intake) throws HibernateException {
    	super.delete(intake);
    }
    public List list() throws HibernateException {
    	return super.findAll(CwtJob.class);
    }
    public CwtJob findJobOnLikeClause(String param, String value) throws HibernateException {
    	return (CwtJob) super.findByObjectIdOnLikeClause(CwtJob.class, param, value);
    }
    
}
