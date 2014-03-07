package org.faithfarm.sms.hibernate.data;

import java.util.List;

import org.faithfarm.sms.domain.Question;
import org.hibernate.HibernateException;

public class QuestionDao extends GenericDao {
	
	public QuestionDao() {
        super();
    }
	
    public Question find(Long id) throws HibernateException {
    	return (Question) super.findById(Question.class, id);
    }
    public List<Question> findAll(Question question) throws HibernateException {
    	return (List<Question>) super.findAll(Question.class);
    }
    public List<Question> findAllByFarm(String farm) throws HibernateException {
    	return (List<Question>) super.findAllByFarm(Question.class,farm);
    }
    public Long save(Question intake) throws HibernateException {
    	return (Long) super.save(intake);
    }
    public void update(Question intake) throws HibernateException {
    	super.update(intake);
    }
    public void delete(Question intake) throws HibernateException {
    	super.delete(intake);
    }
    public List list() throws HibernateException {
    	return super.findAll(Question.class);
    }
}
