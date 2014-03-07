package org.faithfarm.sms.hibernate.data;

import java.util.List;

import org.faithfarm.sms.domain.CwtMetrics;
import org.faithfarm.sms.domain.CwtModuleStudent;
import org.hibernate.HibernateException;

public class CwtModuleStudentDao extends GenericDao {
	
	public CwtModuleStudentDao() {
        super();
    }
	
    public CwtModuleStudent find(Long id) throws HibernateException {
    	return (CwtModuleStudent) super.findById(CwtModuleStudent.class, id);
    }
    public List<CwtModuleStudent> findAll() throws HibernateException {
    	return (List<CwtModuleStudent>) super.findAll(CwtModuleStudent.class);
    }
    public Long save(CwtModuleStudent intake) throws HibernateException {
    	return (Long) super.save(intake);
    }
    public void update(CwtModuleStudent intake) throws HibernateException {
    	super.update(intake);
    }
    public void delete(CwtModuleStudent intake) throws HibernateException {
    	super.delete(intake);
    }
    public List list() throws HibernateException {
    	return super.findAll(CwtModuleStudent.class);
    }
}
