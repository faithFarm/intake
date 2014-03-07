package org.faithfarm.sms.hibernate.data;

import java.util.List;

import org.faithfarm.sms.domain.CwtProgram;
import org.faithfarm.sms.domain.CwtProgramMetricModules;
import org.hibernate.HibernateException;

public class CwtProgramMetricModulesDao extends GenericDao {
	
	public CwtProgramMetricModulesDao() {
        super();
    }
	
    public CwtProgramMetricModules find(Long id) throws HibernateException {
    	return (CwtProgramMetricModules) super.findById(CwtProgramMetricModules.class, id);
    }
    public List<CwtProgramMetricModules> findAll() throws HibernateException {
    	return (List<CwtProgramMetricModules>) super.findAll(CwtProgramMetricModules.class);
    }

    public Long save(CwtProgramMetricModules intake) throws HibernateException {
    	return (Long) super.save(intake);
    }
    public void update(CwtProgramMetricModules intake) throws HibernateException {
    	super.update(intake);
    }
    public void delete(CwtProgramMetricModules intake) throws HibernateException {
    	super.delete(intake);
    }
    public List list() throws HibernateException {
    	return super.findAll(CwtProgramMetricModules.class);
    }
}
