package org.faithfarm.sms.hibernate.data;

import java.util.List;

import org.faithfarm.sms.domain.CwtMetrics;
import org.faithfarm.sms.domain.CwtModuleSection;
import org.hibernate.HibernateException;

public class CwtModuleSectionDao extends GenericDao {
	
	public CwtModuleSectionDao() {
        super();
    }
	
    public CwtModuleSection find(Long id) throws HibernateException {
    	return (CwtModuleSection) super.findById(CwtModuleSection.class, id);
    }
    public List<CwtModuleSection> findAll() throws HibernateException {
    	return (List<CwtModuleSection>) super.findAll(CwtModuleSection.class);
    }
    public List<CwtModuleSection> findByInstructorId(Long id) throws HibernateException {
    	return (List<CwtModuleSection>) super.findByObjectId(CwtModuleSection.class,"instructorId",id);
    }
    public List<CwtModuleSection> findByAdministratorId(Long id) throws HibernateException {
    	return (List<CwtModuleSection>) super.findByObjectId(CwtModuleSection.class,"administratorId",id);
    }
    public List<CwtModuleSection> filter(String farm, Long moduleId) throws HibernateException {
    	return (List<CwtModuleSection>) super.filterSection(CwtModuleSection.class,farm,moduleId);
    }
    public Long save(CwtModuleSection intake) throws HibernateException {
    	return (Long) super.save(intake);
    }
    public void update(CwtModuleSection intake) throws HibernateException {
    	super.update(intake);
    }
    public void delete(CwtModuleSection intake) throws HibernateException {
    	super.delete(intake);
    }
    public List list() throws HibernateException {
    	return super.findAll(CwtModuleSection.class);
    }
}
