package org.faithfarm.sms.hibernate.data;

import java.util.List;

import org.faithfarm.sms.domain.CwtMetrics;
import org.faithfarm.sms.domain.CwtModules;
import org.hibernate.HibernateException;

public class CwtModulesDao extends GenericDao {
	
	public CwtModulesDao() {
        super();
    }
	
    public CwtModules find(Long id) throws HibernateException {
    	return (CwtModules) super.findById(CwtModules.class, id);
    }
    public List<CwtModules> findAll() throws HibernateException {
    	return (List<CwtModules>) super.findAll(CwtModules.class);
    }
    public Long save(CwtModules intake) throws HibernateException {
    	return (Long) super.save(intake);
    }
    public void update(CwtModules intake) throws HibernateException {
    	super.update(intake);
    }
    public void delete(CwtModules intake) throws HibernateException {
    	super.delete(intake);
    }
    public List list() throws HibernateException {
    	return super.findAll(CwtModules.class);
    }
}
