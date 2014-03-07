package org.faithfarm.sms.hibernate.data;

import java.util.List;

import org.faithfarm.sms.domain.CourseRotationHistory;
import org.hibernate.HibernateException;

public class CourseRotationHistoryDao extends GenericDao {
	
	public CourseRotationHistoryDao() {
        super();
    }
	
    public CourseRotationHistory find(Long id) throws HibernateException {
    	return (CourseRotationHistory) super.findById(CourseRotationHistory.class, id);
    }
    public List<CourseRotationHistory> findAllByFarm(String farm) throws HibernateException {
    	return (List<CourseRotationHistory>) super.findAllByFarm(CourseRotationHistory.class, farm);
    }
    public Long save(CourseRotationHistory intake) throws HibernateException {
    	return (Long) super.save(intake);
    }
    public void update(CourseRotationHistory intake) throws HibernateException {
    	super.update(intake);
    }
    public void delete(CourseRotationHistory intake) throws HibernateException {
    	super.delete(intake);
    }
    public List list() throws HibernateException {
    	return super.findAll(CourseRotationHistory.class);
    }
}
