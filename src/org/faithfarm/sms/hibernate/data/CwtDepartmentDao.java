package org.faithfarm.sms.hibernate.data;

import java.util.List;

import org.faithfarm.sms.domain.CwtDepartment;
import org.faithfarm.sms.domain.CwtJob;
import org.hibernate.HibernateException;

public class CwtDepartmentDao extends GenericDao {
	
	public CwtDepartmentDao() {
        super();
    }
	
    public CwtDepartment find(Long id) throws HibernateException {
    	return (CwtDepartment) super.findById(CwtDepartment.class, id);
    } 
    public List<CwtDepartment> findAll() throws HibernateException {
    	return (List<CwtDepartment>) super.findAll(CwtDepartment.class);
    }  
    public Long save(CwtDepartment intake) throws HibernateException {
    	return (Long) super.save(intake);
    }
    public void update(CwtDepartment intake) throws HibernateException {
    	super.update(intake);
    }
    public void delete(CwtDepartment intake) throws HibernateException {
    	super.delete(intake);
    }
    public List list() throws HibernateException {
    	return super.findAll(CwtDepartment.class);
    }
}
