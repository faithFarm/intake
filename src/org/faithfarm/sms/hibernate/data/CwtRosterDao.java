package org.faithfarm.sms.hibernate.data;

import java.util.List;

import org.faithfarm.sms.domain.CwtModules;
import org.faithfarm.sms.domain.CwtRoster;
import org.hibernate.HibernateException;

public class CwtRosterDao extends GenericDao {
	
	public CwtRosterDao() {
        super();
    }
	
    public CwtRoster find(Long id) throws HibernateException {
    	return (CwtRoster) super.findById(CwtRoster.class, id);
    }
    public List<CwtRoster> findAll() throws HibernateException {
    	return (List<CwtRoster>) super.findAll(CwtRoster.class);
    }
    public Long save(CwtRoster intake) throws HibernateException {
    	return (Long) super.save(intake);
    }
    public void update(CwtRoster intake) throws HibernateException {
    	super.update(intake);
    }
    public void delete(CwtRoster intake) throws HibernateException {
    	super.delete(intake);
    }
    public List list() throws HibernateException {
    	return super.findAll(CwtRoster.class);
    }
}
