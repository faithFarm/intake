package org.faithfarm.sms.hibernate.data;

import java.util.List;

import org.faithfarm.sms.domain.CwtProgram;
import org.faithfarm.sms.domain.CwtProgramMetric;
import org.hibernate.HibernateException;

public class CwtProgramMetricDao extends GenericDao {
	
	public CwtProgramMetricDao() {
        super();
    }
	
    public CwtProgramMetric find(Long id) throws HibernateException {
    	return (CwtProgramMetric) super.findById(CwtProgramMetric.class, id);
    }
    public List<CwtProgramMetric> findAll() throws HibernateException {
    	return (List<CwtProgramMetric>) super.findAll(CwtProgramMetric.class);
    }
    public Long save(CwtProgramMetric intake) throws HibernateException {
    	return (Long) super.save(intake);
    }
    public void update(CwtProgramMetric intake) throws HibernateException {
    	super.update(intake);
    }
    public void delete(CwtProgramMetric intake) throws HibernateException {
    	super.delete(intake);
    }
    public List list() throws HibernateException {
    	return super.findAll(CwtProgramMetric.class);
    }
}
