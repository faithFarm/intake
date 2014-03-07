package org.faithfarm.sms.struts.action;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.faithfarm.sms.application.Constants;
import org.faithfarm.sms.domain.CwtDepartment;
import org.faithfarm.sms.domain.CwtJob;
import org.faithfarm.sms.domain.CwtJobMetric;
import org.faithfarm.sms.domain.CwtMaster;
import org.faithfarm.sms.domain.CwtMetrics;
import org.faithfarm.sms.domain.CwtModuleSection;
import org.faithfarm.sms.domain.CwtModules;
import org.faithfarm.sms.domain.CwtProgram;
import org.faithfarm.sms.domain.CwtProgramMetric;
import org.faithfarm.sms.domain.CwtProgramMetricModules;
import org.faithfarm.sms.domain.CwtRoster;
import org.faithfarm.sms.domain.CwtSupervisor;
import org.faithfarm.sms.domain.SystemUser;
import org.faithfarm.sms.domain.ViewCwtRoster;
import org.faithfarm.sms.domain.ViewCwtSection;
import org.faithfarm.sms.hibernate.data.CwtDepartmentDao;
import org.faithfarm.sms.hibernate.data.CwtJobDao;
import org.faithfarm.sms.hibernate.data.CwtJobMetricDao;
import org.faithfarm.sms.hibernate.data.CwtMetricsDao;
import org.faithfarm.sms.hibernate.data.CwtModuleSectionDao;
import org.faithfarm.sms.hibernate.data.CwtModuleStudentDao;
import org.faithfarm.sms.hibernate.data.CwtModulesDao;
import org.faithfarm.sms.hibernate.data.CwtProgramDao;
import org.faithfarm.sms.hibernate.data.CwtProgramMetricDao;
import org.faithfarm.sms.hibernate.data.CwtProgramMetricModulesDao;
import org.faithfarm.sms.hibernate.data.CwtRosterDao;
import org.faithfarm.sms.hibernate.data.CwtSupervisorDao;
import org.faithfarm.sms.hibernate.data.GenericDao;
import org.faithfarm.sms.struts.form.CwtForm;
import org.faithfarm.sms.util.HtmlDropDownBuilder;
import org.faithfarm.sms.util.Validator;
import org.faithfarm.sms.validator.IntakeValidator;

public class CwtAction extends Action {
	
	private final static Logger LOGGER = Logger.getLogger(CwtAction.class.getName());
	private final static HtmlDropDownBuilder html = new HtmlDropDownBuilder();
	private final static IntakeValidator inakeValidator = new IntakeValidator();
	private final static Validator validator = new Validator();
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
		LOGGER.setLevel(Level.SEVERE);

		 HttpSession session = request.getSession(false);
		 SystemUser user = (SystemUser) session.getAttribute("system_user");
		 
		 try {
		
		/*
		 * test ddl's stored in session.  If empty, session expired, redirect to login
		 */
		ArrayList ddllist = (ArrayList)session.getAttribute("ddl_farm");
		if (ddllist==null) 
			return mapping.findForward(Constants.LOGIN);
		
		 String action=request.getParameter("action");
		 
		 CwtForm cwtForm = (CwtForm)form;
		 
		 CwtJobDao cwtJobDao = new CwtJobDao();
		 CwtSupervisorDao cwtSupervisorDao = new CwtSupervisorDao();
		 CwtDepartmentDao cwtDepartmentDao = new CwtDepartmentDao();
		 CwtProgramDao cwtProgramDao = new CwtProgramDao();
		 CwtMetricsDao cwtMetricsDao = new CwtMetricsDao();
		 CwtModulesDao cwtModulesDao = new CwtModulesDao();
		 CwtProgramMetricDao cwtProgramMetricDao = new CwtProgramMetricDao();
		 CwtProgramMetricModulesDao cwtProgramMetricModuleDao = new CwtProgramMetricModulesDao();
		 CwtModuleSectionDao cwtModuleSectionDao = new CwtModuleSectionDao();
		 CwtModuleStudentDao cwtModuleStudentDao = new CwtModuleStudentDao();
		 CwtJobMetricDao cwtJobMetricDao = new CwtJobMetricDao();
		 GenericDao dao = new GenericDao();
		 /*
		  * First check role, if Instructor then forward to search roster page
		  * 
		  */
		 if ("CwtInstructor".equals(user.getUserRole())&&!"Search".equals(action)&&!"Create Roster".equals(action)) {
			 	//this.getRosters(cwtForm,user);
			 session.setAttribute("error","");
			 cwtForm.setSearchDate("");
			 cwtForm.setModuleId(new Long(0));
			 return mapping.findForward(Constants.ROSTER_SEARCH);
		 }
		 
		 if ("Filter".equals(action)) {
			 this.listCwtSections(cwtForm, user);
			 //this.getSectionDetail(cwtForm,user);
			 return mapping.findForward(Constants.SECTIONS); 
		 } else if ("programs".equals(action)) {
			 cwtForm.setProgramList(cwtProgramDao.findAll()); //listCwtPrograms());
			 return mapping.findForward(Constants.PROGRAMS);
		 }
		 else if ("metrics".equals(action)) {
			 cwtForm.setMetricList(cwtMetricsDao.findAll());
			 cwtForm.setProgramList(cwtProgramDao.findAll());
			 return mapping.findForward(Constants.METRICS);
		 }
		 else if ("modules".equals(action)) {
			 cwtForm.setModuleList(cwtModulesDao.findAll());
			 cwtForm.setMetricList(cwtMetricsDao.findAll());
			 return mapping.findForward(Constants.MODULES);
		 }
		 else if ("departments".equals(action)) {
			 cwtForm.setDepartmentList(cwtDepartmentDao.findAll());
			 return mapping.findForward(Constants.DEPARTMENTS);
		 }
		 else if ("jobs".equals(action)) {
			 cwtForm.setJobList(cwtJobDao.findAll());
			 cwtForm.setMetricList(cwtMetricsDao.findAll());
			 return mapping.findForward(Constants.JOBS);
		 }
		 else if ("supervisors".equals(action)) {
			 List<CwtSupervisor> list = cwtSupervisorDao.findAll();
			 List<CwtMaster> masters = new ArrayList<CwtMaster>();
			 for (Iterator iterator = list.iterator();iterator.hasNext();) {
				 CwtSupervisor supervisor = (CwtSupervisor)iterator.next();
				 CwtDepartment department = cwtDepartmentDao.find(supervisor.getDepartmentId());
				 CwtJob job = cwtJobDao.find(supervisor.getJobId());
				 
				 CwtMaster master = new CwtMaster();
				 master.setCwtDepartment(department);
				 master.setCwtJob(job);
				 master.setCwtSupervisor(supervisor);
				 masters.add(master);
			 }
			 //cwtForm.setSupervisorList(cwtSupervisorDao.listCwtSupervisors());
			 cwtForm.setMasterList(masters);
			 return mapping.findForward(Constants.SUPERVISORS);
		 }
		 else if ("sections".equals(action)) {
			 //this.getSectionDetail(cwtForm,user);
			 cwtForm.setFarmBase("");
			 cwtForm.setProgramId(null);
			 this.listCwtSections(cwtForm, user);
			 return mapping.findForward(Constants.SECTIONS);
		 }
		 else if ("rotate".equals(action))
			 return mapping.findForward(Constants.ROTATE);
		 else if ("archives".equals(action)) {
			 session.setAttribute("error","");
			 cwtForm.setSearchArchivedFlag("No");
			 cwtForm.setSearchDate("");
			 cwtForm.setModuleId(new Long(0));
			 return mapping.findForward(Constants.ROSTER_SEARCH);
		 }
		 else if ("Edit".equals(action)) {
			 String id=request.getParameter("id");
			 String type=request.getParameter("type");
			 
			 if ("Job".equals(type)) {
				 cwtForm.setCwtJob(cwtJobDao.find(new Long(id)));
				 return mapping.findForward("create_job");
			 }
			 if ("Department".equals(type)) {
				 cwtForm.setCwtDepartment(cwtDepartmentDao.find(new Long(id)));
				 return mapping.findForward("create_department");
			 }
			 if ("Supervisor".equals(type)) {
				 cwtForm.setCwtSupervisor(cwtSupervisorDao.find(new Long(id)));
				 return mapping.findForward("create_supervisor");
			 }
			 if ("Metric".equals(type)) {
				 cwtForm.setCwtMetric(cwtMetricsDao.find(new Long(id)));
				 this.loadProgramMetrics(cwtForm);
				 return mapping.findForward("create_"+Constants.METRICS);
			 }
			 if ("Module".equals(type)) {
				 cwtForm.setCwtModule(cwtModulesDao.find(new Long(id)));
				 return mapping.findForward("create_"+Constants.MODULES);
			 }
			 if ("Program".equals(type)) {
				 cwtForm.setCwtProgram(cwtProgramDao.find(new Long(id)));
				 return mapping.findForward("create_"+Constants.PROGRAMS);
			 }
			 if ("Section".equals(type)) {
				 cwtForm.setCwtModuleSection(cwtModuleSectionDao.find(new Long(id)));
				 this.getMeetingDays(cwtForm);
				 return mapping.findForward("create_"+Constants.SECTIONS);
			 }
			 
		 }
		 else if ("Create Roster".equals(action)) {
			 return mapping.findForward(Constants.CREATE_ROSTER);
		 }
		 else if ("Delete".equals(action)) {
			 String id=request.getParameter("id");
			 if ("metric".equals(cwtForm.getPageSource())) {
				 CwtMetrics obj = cwtMetricsDao.find(new Long(id));
				 cwtMetricsDao.delete(obj);
			 }
			 if ("module".equals(cwtForm.getPageSource())) {
				 CwtModules obj = cwtModulesDao.find(new Long(id));
				 cwtModulesDao.delete(obj);
			 }
			 if ("program".equals(cwtForm.getPageSource())) {
				 CwtProgram obj = cwtProgramDao.find(new Long(id));
				 cwtProgramDao.delete(obj);
			 }
			 if ("section".equals(cwtForm.getPageSource())) {
				 cwtModuleSectionDao.delete(cwtForm.getCwtModuleSection());
				 this.getSectionDetail(cwtForm,user);
				 return mapping.findForward(Constants.SECTIONS);
			 }
			 return mapping.findForward("create_"+cwtForm.getPageSource());
		 }
		 else if ("Create".equals(action)) {
			 cwtForm.setModuleMetric(new String[200]);
			 cwtForm.setMetricUbit(new String[200]);
			 cwtForm.setJobMetric(new String[200]);
				
			 cwtForm.setCwtDepartment(new CwtDepartment());
			 cwtForm.setCwtJob(new CwtJob());
			 cwtForm.setCwtSupervisor(new CwtSupervisor());
			 cwtForm.setCwtProgram(new CwtProgram());
			 cwtForm.setCwtModule(new CwtModules());
			 cwtForm.setCwtMetric(new CwtMetrics());
			 cwtForm.setCwtModuleSection(new CwtModuleSection());
			 
			 cwtForm.setMetricList(cwtMetricsDao.findAll());
			 html.refresh(session);
			 return mapping.findForward("create_"+cwtForm.getPageSource());
		 }
		 else if ("Save".equals(action)) {
			 if ("program".equals(cwtForm.getPageSource())) {
				 if (cwtForm.getCwtMetric().getMetricId()==null) {
					 cwtForm.getCwtProgram().setCreatedBy(user.getUsername());
					 cwtForm.getCwtProgram().setCreationDate(validator.getEpoch()+"");
					 cwtProgramDao.save(cwtForm.getCwtProgram());
				 } else
					 cwtProgramDao.update(cwtForm.getCwtProgram());
				 
				 cwtForm.setProgramList(cwtProgramDao.findAll());
				 return mapping.findForward(Constants.PROGRAMS);
			 }
			 if ("module".equals(cwtForm.getPageSource())) {
				 if (cwtForm.getCwtModule().getModuleId()==null) {
					 cwtForm.getCwtModule().setCreatedBy(user.getUsername());
					 cwtForm.getCwtModule().setCreationDate(validator.getEpoch()+"");
					 cwtModulesDao.save(cwtForm.getCwtModule());
				 } else
					 cwtModulesDao.update(cwtForm.getCwtModule());
				 this.saveModuleMetrics(cwtForm, request);
				 cwtForm.setModuleList(cwtModulesDao.findAll());
				 return mapping.findForward(Constants.MODULES);
			 }
			 if ("metric".equals(cwtForm.getPageSource())) {
				 if (cwtForm.getCwtMetric().getMetricId()==null) {
					 cwtForm.getCwtMetric().setCreatedBy(user.getUsername());
					 cwtForm.getCwtMetric().setCreationDate(validator.getEpoch()+"");
					 long id = cwtMetricsDao.save(cwtForm.getCwtMetric());
					 cwtForm.getCwtMetric().setMetricId(id);
				 } else 
					 cwtMetricsDao.update(cwtForm.getCwtMetric());
				 
				 cwtForm.setMetricList(cwtMetricsDao.findAll());
				 this.saveProgramMetrics(cwtForm,request);
				 return mapping.findForward(Constants.METRICS);
			 }
			 if ("department".equals(cwtForm.getPageSource())) {
				 cwtForm.getCwtDepartment().setCreatedBy(user.getUsername());
				 cwtForm.getCwtDepartment().setCreationDate(validator.getEpoch()+"");
				 if (cwtForm.getCwtDepartment().getDepartmentId()==null)
					 cwtDepartmentDao.save(cwtForm.getCwtDepartment());
				 else
					 cwtDepartmentDao.update(cwtForm.getCwtDepartment());
						 
				 cwtForm.setDepartmentList(cwtDepartmentDao.findAll());
				 return mapping.findForward(Constants.DEPARTMENTS);
			 }

			 if ("job".equals(cwtForm.getPageSource())) {
				 cwtForm.getCwtJob().setCreatedBy(user.getUsername());
				 cwtForm.getCwtJob().setCreationDate(validator.getEpoch()+"");
				 if (cwtForm.getCwtJob().getJobId()==null) {
					 Long id = cwtJobDao.save(cwtForm.getCwtJob());
					 cwtForm.getCwtJob().setJobId(id);
				 } else
					 cwtJobDao.update(cwtForm.getCwtJob());
				 this.saveJobMetrics(cwtForm);
				 cwtForm.setJobList(cwtJobDao.findAll());
				 return mapping.findForward(Constants.JOBS);
			 }
			 
			 if ("supervisor".equals(cwtForm.getPageSource())) {
				 cwtForm.getCwtSupervisor().setCreatedBy(user.getUsername());
				 cwtForm.getCwtSupervisor().setCreationDate(validator.getEpoch()+"");
				 if (cwtForm.getCwtSupervisor().getSupervisorId()==null)
					 cwtSupervisorDao.save(cwtForm.getCwtSupervisor());
				 else
					 cwtSupervisorDao.update(cwtForm.getCwtSupervisor());
				 
				 cwtForm.setSupervisorList(cwtSupervisorDao.findAll());
				 return mapping.findForward(Constants.SUPERVISORS);
			 }
			 if ("section".equals(cwtForm.getPageSource())) {
				 cwtForm.getCwtModuleSection().setMeetingDays(this.convertMeetingDays(cwtForm));
				 if (cwtForm.getCwtModuleSection().getModuleOfferingId()==null) {
					 Long id = cwtModuleSectionDao.save(cwtForm.getCwtModuleSection());
					 cwtForm.getCwtModuleSection().setModuleOfferingId(id);
				 }
				 else
					 cwtModuleSectionDao.update(cwtForm.getCwtModuleSection());
				 
				 this.saveModuleMetrics(cwtForm, request);
				 cwtForm.setModuleSectionList(cwtModuleSectionDao.findAll());
				 this.getSectionDetail(cwtForm,user);
				 return mapping.findForward(Constants.SECTIONS);
			 }
			 
		 } else if ("Search".equals(action)) {
			 session.setAttribute("error","");
			 this.listRosters(cwtForm, user);
			/*
			 if (cwtForm.getModuleId()==null||cwtForm.getModuleId().equals(new Long(0))) {
				 session.setAttribute("error", "CWT Module is required");
				 return mapping.findForward(Constants.ROSTER_SEARCH);
			 }
			
			 //validate search date
			if (cwtForm.getSearchDate()!=null&&cwtForm.getSearchDate().length()>0&&cwtForm.getSearchDate().length()!=10) {
						session.setAttribute("error","class date needs to be in MM/DD/YYYY format");
						return mapping.findForward(Constants.ROSTER_SEARCH);
			}
			else if (cwtForm.getSearchDate()==null||cwtForm.getSearchDate().length()==0) {
					session.setAttribute("error","class date is required");
					return mapping.findForward(Constants.ROSTER_SEARCH);
			} 
			else
					cwtForm.setSearchDate(cwtForm.getSearchDate().replace("-", "/"));
			*/
			 
			 //this.searchRoster(cwtForm, user);
			 return mapping.findForward(Constants.ROSTER_RESULTS);
		 }
		 
		 cwtForm.reset(mapping, request);
		 return mapping.findForward(Constants.SUCCESS);
			}
			catch (Exception e) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);			
				session.setAttribute("SYSTEM_ERROR", sw.toString());
				e.printStackTrace();
				return mapping.findForward(Constants.ERROR);
			}
	}
	
	
	private void saveJobMetrics(CwtForm cwtForm) {
		
		CwtJobMetricDao dao = new CwtJobMetricDao();
		
		 /*
		  * First delete all medical conditions for given intake
		  */
		 List<CwtJobMetric> jobMetrics = dao.findByObjectId(new CwtJobMetric().getClass(), "jobId", cwtForm.getCwtJob().getJobId());		 
		 
		 for (Iterator iterator =
				 jobMetrics.iterator(); iterator.hasNext();){
			 CwtJobMetric obj = (CwtJobMetric) iterator.next();
			 dao.delete(obj);
		 }
		 
		 String jobMetric[] = cwtForm.getJobMetric();
		 int index=0;
		 for (Iterator iterator =
				 cwtForm.getMetricList().iterator(); iterator.hasNext();){
			 CwtMetrics obj = (CwtMetrics) iterator.next();
			   
	    	   if ("YES".equals(jobMetric[index])) {
	    		   CwtJobMetric cjm = new CwtJobMetric();
	    		   cjm.setJobId(cwtForm.getCwtJob().getJobId());
	    		   cjm.setMetricId(obj.getMetricId());
	    		   dao.save(cjm);
	    		 }
	    index++;
		}
	}

	
private void saveProgramMetrics(CwtForm cwtForm, HttpServletRequest request) {
		
		CwtProgramMetricDao dao = new CwtProgramMetricDao();
		String [] metricUbit = cwtForm.getMetricUbit();
		
		
		//delete all programMetrics by metricId before reinserting the updates
		List<CwtProgramMetric> programMetrics = dao.findByObjectId(new CwtProgramMetric().getClass(),"metricId", cwtForm.getCwtMetric().getMetricId());
			 for (Iterator iterator2 =
					 programMetrics.iterator(); iterator2.hasNext();) {
				 CwtProgramMetric obj2 = (CwtProgramMetric) iterator2.next();
			 	 dao.delete(obj2);
			 }
			 
		 int index=0;		 
		 for (Iterator iterator =
				 cwtForm.getProgramList().iterator(); iterator.hasNext();){
			 CwtProgram obj = (CwtProgram) iterator.next();
			 if ("YES".equals(request.getParameter("metricUbit["+index+"]"))) {
	    		   CwtProgramMetric cpm = new CwtProgramMetric();
	    		   cpm.setMetricId(cwtForm.getCwtMetric().getMetricId());
	    		   cpm.setProgramId(obj.getProgramId());
	    		   dao.save(cpm);
	    		 }
	    index++;
			 
		}		 
		
	}


private void loadProgramMetrics(CwtForm cwtForm) {
	
	CwtProgramMetricDao dao = new CwtProgramMetricDao();
	String [] metricUbit = new String[200];
	
	List<CwtProgramMetric> programMetrics = dao.findByObjectId(new CwtProgramMetric().getClass(), "metricId", cwtForm.getCwtMetric().getMetricId());
	
	 int index=0;		 
	 for (Iterator iterator =
			 cwtForm.getProgramList().iterator(); iterator.hasNext();){
		 CwtProgram obj = (CwtProgram) iterator.next();
		 
		 for (Iterator iterator2 =
				 programMetrics.iterator(); iterator2.hasNext();){
			 CwtProgramMetric obj2 = (CwtProgramMetric) iterator2.next();
			 if (obj2.getProgramId().equals(obj.getProgramId())) 
				 metricUbit[index]="YES";
			 	
		 }	 
	index++;
		 
	}	
	cwtForm.setMetricUbit(metricUbit);
	
}


private void listCwtSections(CwtForm cwtForm, SystemUser user){
	GenericDao dao = new GenericDao();
	
	String farm = cwtForm.getFarmBase();
	if (farm==null||farm.length()==0)
		farm=user.getFarmBase();
		
	List<ViewCwtSection> list = dao.findAll(new ViewCwtSection().getClass(), farm, cwtForm.getProgramId(), cwtForm.getModuleSequence() );
	cwtForm.setCwtSections(list);

}
private List getSectionDetail(CwtForm cwtForm, SystemUser user) { 
	
	CwtModuleSectionDao dao1 = new CwtModuleSectionDao();
	CwtSupervisorDao cwtSupervisorDao = new CwtSupervisorDao();
	CwtProgramDao cwtProgramDao = new CwtProgramDao();
	CwtMetricsDao cwtMetricsDao = new CwtMetricsDao();
	CwtModulesDao cwtModulesDao = new CwtModulesDao();
	
	List<CwtModuleSection> sections = new ArrayList<CwtModuleSection>();
	List<CwtMaster> masters = new ArrayList<CwtMaster>();
	
	
	String farm = cwtForm.getFarmBase();
	Long moduleId=null;
	if (cwtForm.getCwtModule()!=null)
		moduleId = cwtForm.getCwtModule().getModuleId();
	
	if ((farm!=null&&farm.length()>0)||moduleId!=null)
		sections = dao1.filter(farm, moduleId);
	else
		sections = dao1.findAll();

	
	for (Iterator iterator =
			 sections.iterator(); iterator.hasNext();){
		 CwtModuleSection section = (CwtModuleSection)iterator.next();
		 CwtModules module = new CwtModules();
		 CwtProgram program = new CwtProgram();
		 CwtSupervisor supervisor = new CwtSupervisor();
		 
		 if (section!=null)
			 module = cwtModulesDao.find(section.getModuleId());
		 
		 if (module!=null)
			 program = cwtProgramDao.find(module.getProgramId());
		 
		 if (section!=null)
			 supervisor = cwtSupervisorDao.find(section.getInstructorId());
		 
		 CwtMaster master = new CwtMaster();
		 master.setModule(module);
		 master.setProgram(program);
		 master.setSupervisor(new CwtSupervisor());
		 master.setSection(section);
		 masters.add(master);
		 
	}
	 cwtForm.setMasterList(masters);
	
	return null;
}

private void saveModuleMetrics(CwtForm cwtForm, HttpServletRequest request) {
	
	CwtProgramMetricModulesDao dao = new CwtProgramMetricModulesDao();
	String [] moduleMetric = cwtForm.getModuleMetric();
	
	
	//delete all moduleMetrics by metricId before reinserting the updates
	List<CwtProgramMetricModules> programModuleMetrics = dao.findByObjectId(new CwtProgramMetricModules().getClass(),"moduleId", cwtForm.getCwtProgramMetricModule().getModuleId());
		 for (Iterator iterator2 =
				 programModuleMetrics.iterator(); iterator2.hasNext();) {
			 CwtProgramMetricModules obj2 = (CwtProgramMetricModules) iterator2.next();
		 	 dao.delete(obj2);
		 }
		 
	 int index=0;		 
	 for (Iterator iterator =
			 cwtForm.getMetricList().iterator(); iterator.hasNext();){
		 CwtMetrics obj = (CwtMetrics) iterator.next();
		 //System.out.println (index+"="+request.getParameter("moduleMetric["+index+"]"));
		 if ("YES".equals(request.getParameter("moduleMetric["+index+"]"))) {
    		   CwtProgramMetricModules cpm = new CwtProgramMetricModules();
    		   cpm.setMetricId(obj.getMetricId());
    		   cpm.setModuleId(cwtForm.getCwtModuleSection().getModuleId());
    		   dao.save(cpm);
    		 }
    index++;
		 
	}		 
	 

	
}

private String convertMeetingDays(CwtForm cwtForm) {
	
	String meetingDay="";
	
	if ("M".equals(cwtForm.getMonday()))
		meetingDay+="M ";
	if ("T".equals(cwtForm.getTuesday()))
		meetingDay+="T ";
	if ("W".equals(cwtForm.getWednesday()))
		meetingDay+="W ";
	if ("TH".equals(cwtForm.getThursday()))
		meetingDay+="TH ";
	if ("F".equals(cwtForm.getFriday()))
		meetingDay+="F ";
	if ("SA".equals(cwtForm.getSaturday()))
		meetingDay+="SA ";
	if ("SU".equals(cwtForm.getSunday()))
		meetingDay+="SU ";
	
	
	return meetingDay;
	
}

private void getMeetingDays(CwtForm cwtForm) {
	
	String meetingDays=cwtForm.getCwtModuleSection().getMeetingDays();
	if (meetingDays!=null) { 
		if (meetingDays.contains("M"))
			cwtForm.setMonday("M");
		if (meetingDays.contains("TU"))
			cwtForm.setTuesday("TU");
		if (meetingDays.contains("W"))
			cwtForm.setWednesday("W");
		if (meetingDays.contains("TH"))
			cwtForm.setThursday("TH");
		if (meetingDays.contains("F"))
			cwtForm.setFriday("F");
		if (meetingDays.contains("SA"))
			cwtForm.setSaturday("SA");
		if (meetingDays.contains("SU"))
			cwtForm.setSunday("SU");
	}
	
}

private void listRosters(CwtForm cwtForm, SystemUser user) {
	
	 cwtForm.setArchivedRosterList(new ArrayList<ViewCwtRoster>());
	 cwtForm.setCurrentRosterList(new ArrayList<ViewCwtRoster>());
	 String farm=cwtForm.getFarmBase();
	 if (farm==null||farm.length()==0)
		 farm=user.getFarmBase();
	 
	 String archivedFlag="Yes";//cwtForm.getSearchArchivedFlag();
	 cwtForm.setSearchArchivedFlag("Yes");
	 Long moduleId=cwtForm.getModuleId();
	 
	 GenericDao dao = new GenericDao();
	 //List<ViewCwtRoster>list = dao.findAll(new ViewCwtRoster().getClass(), farm, cwtForm.getProgramId(), cwtForm.getModuleSequence(), moduleId, archivedFlag, cwtForm.getSearchDate());
	 List<Object[]> list = dao.searchViewCwtRoster(farm, cwtForm.getProgramId(), cwtForm.getModuleSequence(), moduleId, archivedFlag, cwtForm.getSearchDate());
	 List<ViewCwtRoster> rosters = new ArrayList();
	 
	 for (Object[] obj : list) {
		    BigInteger sectionId = (BigInteger)obj[0];	
		    BigInteger modId = (BigInteger)obj[1];	
		    String moduleName = (String)obj[2];
		    String sequence = (String)obj[3];
		    BigInteger programId = (BigInteger)obj[4];	
		    String programName = (String)obj[5];
		    String rosterDate = (String)obj[6];
		    String farmBase = (String)obj[7];
		    String archived = (String)obj[8];
		    
		    ViewCwtRoster view = new ViewCwtRoster();
		    view.setSectionId(new Long(sectionId+""));
		    view.setModuleId(new Long(modId+""));
		    view.setModuleName(moduleName);
		    view.setSequence(sequence);
		    view.setProgramId(new Long(programId+""));
		    view.setProgramName(programName);
		    view.setRosterDate(rosterDate);
		    view.setFarmBase(farmBase);
		    view.setArchivedFlag(archived);
		    rosters.add(view);
		}
	 //if ("Yes".equals(archivedFlag))
		 cwtForm.setArchivedRosterList(rosters);
	 //if ("No".equals(archivedFlag))
		 cwtForm.setCurrentRosterList(rosters);
	 
}

/*
private void searchRoster (CwtForm cwtForm, SystemUser user) {
	 
	 cwtForm.setArchivedRosterList(new ArrayList<CwtMaster>());
	 cwtForm.setCurrentRosterList(new ArrayList<CwtMaster>());
	 
	GenericDao dao = new GenericDao();
	 
	 String archivedFlag=cwtForm.getSearchArchivedFlag();
	 if (archivedFlag==null)
		 archivedFlag="No";
	 
	 CwtProgramDao cwtProgramDao = new CwtProgramDao();
	 CwtModulesDao cwtModulesDao = new CwtModulesDao();
	 CwtRosterDao cwtRosterDao = new CwtRosterDao();
	 CwtModuleSectionDao cwtModuleSectionDao = new CwtModuleSectionDao();
	 
	 List<CwtMaster> masters = new ArrayList<CwtMaster>();
	
	 Long searchUserId = user.getUserId();
	 if ("Administrator".equals(user.getUserRole()))
		 searchUserId=new Long(999);
	 List<BigInteger> list = dao.searchRosters(cwtForm.getModuleId(), cwtForm.getSearchDate(), archivedFlag, user.getFarmBase(),searchUserId);
	 //for (Iterator iterator = list.iterator();iterator.hasNext();) {
	 if (list.size()>0) {
		 Long rosterId =  ((BigInteger)list.get(0)).longValue();
		 CwtRoster roster = cwtRosterDao.find(rosterId);
		 CwtModuleSection obj1 = cwtModuleSectionDao.find(roster.getSectionId());
		 CwtModules obj2 = (CwtModules)cwtModulesDao.find(roster.getModuleId());
		 CwtProgram obj3 = (CwtProgram)cwtProgramDao.find(obj2.getProgramId());
		 CwtMaster master = new CwtMaster();
		 master.setSection(obj1);
		 master.setModule(obj2);
		 master.setRoster(roster);
		 if (obj3!=null)
			 master.setProgram(obj3);
		 else
			 master.setProgram(new CwtProgram());
		 masters.add(master);
	 		
	
	 if ("Yes".equals(archivedFlag))
		 cwtForm.setArchivedRosterList(masters);
	 if ("No".equals(archivedFlag))
		 cwtForm.setCurrentRosterList(masters);
	 
	 }

}


private void getRosters(CwtForm cwtForm, SystemUser user) {
	
	 CwtJobDao cwtJobDao = new CwtJobDao();
	 CwtSupervisorDao cwtSupervisorDao = new CwtSupervisorDao();
	 CwtDepartmentDao cwtDepartmentDao = new CwtDepartmentDao();
	 CwtProgramDao cwtProgramDao = new CwtProgramDao();
	 CwtMetricsDao cwtMetricsDao = new CwtMetricsDao();
	 CwtModulesDao cwtModulesDao = new CwtModulesDao();
	 CwtProgramMetricDao cwtProgramMetricDao = new CwtProgramMetricDao();
	 CwtProgramMetricModulesDao cwtProgramMetricModuleDao = new CwtProgramMetricModulesDao();
	 CwtModuleSectionDao cwtModuleSectionDao = new CwtModuleSectionDao();
	 CwtModuleStudentDao cwtModuleStudentDao = new CwtModuleStudentDao();
	 CwtJobMetricDao cwtJobMetricDao = new CwtJobMetricDao();
	 GenericDao dao = new GenericDao();

	 List<CwtMaster> masters = new ArrayList<CwtMaster>();
	 List<CwtModuleSection> list = cwtModuleSectionDao.findByAdministratorId(user.getUserId());
	 for (Iterator iterator = list.iterator();iterator.hasNext();) {
		 CwtModuleSection obj1 = (CwtModuleSection)iterator.next();
		 CwtModules obj2 = (CwtModules)cwtModulesDao.find(obj1.getModuleId());
		 CwtProgram obj3 = (CwtProgram)cwtProgramDao.find(obj2.getProgramId());
		 CwtMaster master = new CwtMaster();
		 master.setSection(obj1);
		 master.setModule(obj2);
		 if (obj3!=null)
			 master.setProgram(obj3);
		 else
			 master.setProgram(new CwtProgram());
		 masters.add(master);
	 }			 
	 cwtForm.setCurrentRosterList(masters);
	 
	 masters = new ArrayList<CwtMaster>();
	 List<CwtModuleSection> list1 = dao.findArchivedRosters(CwtModuleSection.class,"administratorId",user.getUserId(), "Yes");
	 for (Iterator iterator = list1.iterator();iterator.hasNext();) {
		 CwtModuleSection obj1 = (CwtModuleSection)iterator.next();
		 CwtModules obj2 = (CwtModules)cwtModulesDao.find(obj1.getModuleId());
		 CwtProgram obj3 = (CwtProgram)cwtProgramDao.find(obj2.getProgramId());
		 CwtMaster master = new CwtMaster();
		 master.setSection(obj1);
		 master.setModule(obj2);
		 if (obj3!=null)
			 master.setProgram(obj3);
		 else
			 master.setProgram(new CwtProgram());
		 masters.add(master);
	 }		
	 cwtForm.setArchivedRosterList(masters);

}*/
	
	
}
