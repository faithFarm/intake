package org.faithfarm.sms.hibernate.data;

import java.util.List;

import org.faithfarm.sms.domain.CwtJob;
import org.faithfarm.sms.domain.CwtModules;
import org.faithfarm.sms.domain.CwtSupervisor;
import org.hibernate.HibernateException;

public class CwtSupervisorDao extends GenericDao {
	
	public CwtSupervisorDao() {
        super();
    }
	
    public CwtSupervisor find(Long id) throws HibernateException {
    	return (CwtSupervisor) super.findById(CwtSupervisor.class, id);
    }
    public List<CwtSupervisor> findAll() throws HibernateException {
    	return (List<CwtSupervisor>) super.findAll(CwtSupervisor.class);
    }
    public Long save(CwtSupervisor intake) throws HibernateException {
    	return (Long) super.save(intake);
    }
    public void update(CwtSupervisor intake) throws HibernateException {
    	super.update(intake);
    }
    public void delete(CwtSupervisor intake) throws HibernateException {
    	super.delete(intake);
    }
    public List list() throws HibernateException {
    	return super.findAll(CwtSupervisor.class);
    }
    public CwtSupervisor findSupervisorOnLikeClause(String param, String value) throws HibernateException {
    	return (CwtSupervisor) super.findByObjectIdOnLikeClause(CwtSupervisor.class, param, value);
    }
}
