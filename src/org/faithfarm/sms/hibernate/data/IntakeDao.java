package org.faithfarm.sms.hibernate.data;

import java.util.List;

import org.faithfarm.sms.domain.Intake;
import org.hibernate.HibernateException;

public class IntakeDao extends GenericDao {
	
	public IntakeDao() {
        super();
    }
	
    public Intake find(Long id) throws HibernateException {
    	return (Intake) super.findById(Intake.class, id);
    }
    public Long save(Intake intake) throws HibernateException {
    	return (Long) super.save(intake);
    }
    public void update(Intake intake) throws HibernateException {
    	super.update(intake);
    }
    public void delete(Intake intake) throws HibernateException {
    	super.delete(intake);
    }
    public List list() throws HibernateException {
    	return super.findAll(Intake.class);
    }
    public List search(String entryDate, String exitDate, String lastname,
			String firstname, String ssn, String dob, String farm, String ged, String archived, String status,
			String currentClass, Long jobId, Long supervisorId, String driverFlag, Long cwtProgramId) throws HibernateException {
    	return super.search( entryDate,  exitDate,  lastname,firstname,  ssn,  dob,  farm,  ged,  archived,  status, currentClass, jobId, supervisorId, driverFlag, cwtProgramId);
    }
    public List searchApplications(String entryDate, String exitDate,
			String lastname, String firstname, String ssn, String dob,String status, String driverFlag, String gedFlag, String farm ) throws HibernateException {
    	return super.searchApplications( entryDate,  exitDate,  lastname,firstname,  ssn,  dob,  status, driverFlag, gedFlag, farm);
    }
    public List listClass(String classNumber, String farm) throws HibernateException {
    	return super.listClass(classNumber,farm);
    }
    public List listByStatus(String status, String farm) throws HibernateException {
    	return super.listByStatus(status,farm);
    }
    public List searchRosterList(String farm) throws HibernateException {
    	return super.searchRosterList(farm);
    }
    
    
}
