package org.faithfarm.sms.struts.action;

import java.io.PrintWriter;
import java.io.StringWriter;
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
import org.faithfarm.sms.domain.CwtMaster;
import org.faithfarm.sms.domain.CwtMetrics;
import org.faithfarm.sms.domain.CwtModuleSection;
import org.faithfarm.sms.domain.CwtModules;
import org.faithfarm.sms.domain.CwtProgram;
import org.faithfarm.sms.domain.CwtRoster;
import org.faithfarm.sms.domain.CwtRosterNotes;
import org.faithfarm.sms.domain.CwtSupervisor;
import org.faithfarm.sms.domain.Intake;
import org.faithfarm.sms.domain.SystemUser;
import org.faithfarm.sms.domain.ViewCwtIntake;
import org.faithfarm.sms.hibernate.data.CwtDepartmentDao;
import org.faithfarm.sms.hibernate.data.CwtJobDao;
import org.faithfarm.sms.hibernate.data.CwtMetricsDao;
import org.faithfarm.sms.hibernate.data.CwtModuleSectionDao;
import org.faithfarm.sms.hibernate.data.CwtModulesDao;
import org.faithfarm.sms.hibernate.data.CwtProgramDao;
import org.faithfarm.sms.hibernate.data.CwtProgramMetricDao;
import org.faithfarm.sms.hibernate.data.CwtProgramMetricModulesDao;
import org.faithfarm.sms.hibernate.data.CwtRosterDao;
import org.faithfarm.sms.hibernate.data.CwtRosterNotesDao;
import org.faithfarm.sms.hibernate.data.CwtSupervisorDao;
import org.faithfarm.sms.hibernate.data.GenericDao;
import org.faithfarm.sms.hibernate.data.IntakeDao;
import org.faithfarm.sms.hibernate.data.StudentHistoryDao;
import org.faithfarm.sms.struts.form.CwtRosterForm;
import org.faithfarm.sms.util.HtmlDropDownBuilder;
import org.faithfarm.sms.util.PDFBuilder;
import org.faithfarm.sms.util.Validator;
import org.faithfarm.sms.validator.IntakeValidator;

public class CwtRosterAction extends Action {

	private final static Logger LOGGER = Logger.getLogger(CwtRosterAction.class
			.getName());
	private final static HtmlDropDownBuilder html = new HtmlDropDownBuilder();
	private final static IntakeValidator inakeValidator = new IntakeValidator();
	private final static Validator validator = new Validator();

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.setLevel(Level.SEVERE);

		HttpSession session = request.getSession(false);
		SystemUser user = (SystemUser) session.getAttribute("system_user");

		try {

			/*
			 * test ddl's stored in session. If empty, session expired, redirect
			 * to login
			 */
			ArrayList ddllist = (ArrayList) session.getAttribute("ddl_farm");
			if (ddllist == null)
				return mapping.findForward(Constants.LOGIN);

			IntakeDao intakeDao = new IntakeDao();
			CwtProgramDao cwtProgramDao = new CwtProgramDao();
			CwtMetricsDao cwtMetricsDao = new CwtMetricsDao();
			CwtModulesDao cwtModulesDao = new CwtModulesDao();
			CwtProgramMetricDao cwtProgramMetricDao = new CwtProgramMetricDao();
			CwtProgramMetricModulesDao cwtProgramMetricModuleDao = new CwtProgramMetricModulesDao();
			CwtModuleSectionDao cwtModuleSectionDao = new CwtModuleSectionDao();
			CwtDepartmentDao departmentDao = new CwtDepartmentDao();
			CwtSupervisorDao supervisorDao = new CwtSupervisorDao();
			CwtJobDao jobDao = new CwtJobDao();
			StudentHistoryDao studentHistoryDao = new StudentHistoryDao();
			CwtRosterDao rosterDao = new CwtRosterDao();

			String action = request.getParameter("action");

			CwtRosterForm cwtRosterForm = (CwtRosterForm) form;
			PDFBuilder pdf = new PDFBuilder();

			if ("View".equals(action)) {
				session.setAttribute("error","");
				cwtRosterForm.setRosterDate("");
				cwtRosterForm.setCwtMaster(new CwtMaster());
				cwtRosterForm.setCwtMetric(new CwtMetrics());
				cwtRosterForm.setCwtModule(new CwtModules());
				cwtRosterForm.setCwtModuleSection(new CwtModuleSection());
				cwtRosterForm.setCwtProgram(new CwtProgram());

				cwtRosterForm.setExamScore(new String[200]);
				cwtRosterForm.setAttendFlag(new String[200]);
				cwtRosterForm.setStatus(new String[200]);
				cwtRosterForm.setEnrollFlag(new String[200]);

				String id = request.getParameter("id");
				String farm = request.getParameter("farm");
				String archivedFlag = request.getParameter("archivedFlag");

				if (farm == null)
					farm = user.getFarmBase();

				cwtRosterForm.setCwtModuleSection(cwtModuleSectionDao
						.find(new Long(id)));
				cwtRosterForm.setCwtModule(cwtModulesDao.find(cwtRosterForm
						.getCwtModuleSection().getModuleId()));

				String rosterDate="";
				int index=0;
				List<CwtRoster> rosterList = rosterDao.findArchivedRosters(new CwtRoster().getClass(), "sectionId", new Long(id), archivedFlag);
				String attend[] = cwtRosterForm.getAttendFlag();
				String score[] = cwtRosterForm.getExamScore();
				String status[] = cwtRosterForm.getStatus();
				List<CwtMaster> masters = new ArrayList<CwtMaster>();
				for (Iterator iterator = rosterList.iterator(); iterator
						.hasNext();) {
					CwtRoster roster = (CwtRoster) iterator.next();

					CwtDepartment department = new CwtDepartment();
					/*if (roster.getDepartmentId() != null)
						department = departmentDao.find(roster
								.getDepartmentId());*/

					CwtSupervisor supervisor = new CwtSupervisor();
					/*if (roster.getSupervisorId() != null)
						supervisor = supervisorDao.find(roster
								.getSupervisorId());*/

					CwtJob job = new CwtJob();
					/*if (roster.getJobId() != null)
						job = jobDao.find(roster.getJobId());*/

					Intake intake = new Intake();
					if (roster.getIntakeId() != null)
						intake = intakeDao.find(roster.getIntakeId());

					attend[index] = roster.getAttendFlag();
					score[index] = roster.getExamScore();
					status[index] = roster.getStatus();
					CwtMaster master = new CwtMaster();
					master.setCwtSupervisor(supervisor);
					master.setCwtDepartment(department);
					master.setCwtJob(job);
					master.setIntake(intake);
					master.setRoster(roster);
					masters.add(master);
					index++;
					
					rosterDate = roster.getRosterDate();
				}
				cwtRosterForm.setMasterList(masters);
				cwtRosterForm.setAttendFlag(attend);
				cwtRosterForm.setExamScore(score);
				cwtRosterForm.setRosterDate(rosterDate);

				return mapping.findForward(Constants.VIEW_ROSTER);

			}
			else if ("Delete".equals(action)) {
				session.setAttribute("error","");
				String id = request.getParameter("id");
				String rosterDate = request.getParameter("date");
				GenericDao dao = new GenericDao();
				dao.deleteRosterSQL(new Long(id), rosterDate);
				return mapping.findForward(Constants.ROSTER_SEARCH);
			}
			else if ("Roster".equals(action)) {
				session.setAttribute("error","");
				cwtRosterForm.setRosterDate("");
				cwtRosterForm.setCwtMaster(new CwtMaster());
				cwtRosterForm.setCwtMetric(new CwtMetrics());
				cwtRosterForm.setCwtModule(new CwtModules());
				cwtRosterForm.setCwtModuleSection(new CwtModuleSection());
				cwtRosterForm.setCwtProgram(new CwtProgram());

				cwtRosterForm.setExamScore(new String[200]);
				cwtRosterForm.setAttendFlag(new String[200]);
				cwtRosterForm.setStatus(new String[200]);
				
				String[] flags = new String[] { "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes", "Yes",
						"Yes", "Yes", "Yes", "Yes" };
				cwtRosterForm.setEnrollFlag(flags);
				cwtRosterForm.setCwtRosterNotes(new CwtRosterNotes());
				String id = request.getParameter("id");
				String farm = request.getParameter("farm");
				String date = request.getParameter("date");

				if (farm == null)
					farm = user.getFarmBase();

				cwtRosterForm.setCwtModuleSection(cwtModuleSectionDao
						.find(new Long(id)));
				cwtRosterForm.setCwtModule(cwtModulesDao.find(cwtRosterForm
						.getCwtModuleSection().getModuleId()));

				//List<CwtRoster> rosterList = rosterDao.findByObjectId(
				//		new CwtRoster().getClass(), "sectionId", new Long(id)); // .listRosterBySection(new
																				// Long(id));
				List<CwtRoster> rosterList = rosterDao.findRosterBySectionIdRosterDate(new Long(id),date);
				/*
				 * If roster currently exists then set it up
				 */
				GenericDao dao = new GenericDao();
				
				int index = 0;
				String rosterDate="";
				if (rosterList.size() > 0) {
					String attend[] = cwtRosterForm.getAttendFlag();
					String score[] = cwtRosterForm.getExamScore();
					String status[] = cwtRosterForm.getStatus();
					List<CwtMaster> masters = new ArrayList<CwtMaster>();
					for (Iterator iterator = rosterList.iterator(); iterator
							.hasNext();) {
						CwtRoster roster = (CwtRoster) iterator.next();

						ViewCwtIntake intake = new ViewCwtIntake();
						if (roster.getIntakeId() != null) {
							intake = dao.findCwtIntakeByIntakeId(new ViewCwtIntake().getClass(), roster.getIntakeId() );
						
							if (intake!=null) {
								attend[index] = roster.getAttendFlag();
								score[index] = roster.getExamScore();
								status[index] = roster.getStatus();
								CwtMaster master = new CwtMaster();
								master.setCwtIntake(intake);
								master.setRoster(roster);
								masters.add(master);
								index++;								
								rosterDate = roster.getRosterDate();
							}						
						}						
					}
					cwtRosterForm.setMasterList(masters);
					cwtRosterForm.setAttendFlag(attend);
					cwtRosterForm.setExamScore(score);
					cwtRosterForm.setRosterDate(rosterDate);

					CwtRosterNotesDao notesDao = new CwtRosterNotesDao();
					List<CwtRosterNotes> notesList = new ArrayList();
					
					notesList = notesDao.findBySectionIdRosterDate(new CwtRosterNotes().getClass(), new Long(id), rosterDate);
					if (notesList!=null&&notesList.size()>0) {
						CwtRosterNotes notes = (CwtRosterNotes)notesList.get(0);
						cwtRosterForm.setCwtRosterNotes(notes);
					} else
						cwtRosterForm.setCwtRosterNotes(new CwtRosterNotes());
						
					return mapping.findForward(Constants.SUCCESS);
				} else {
					this.generateRoster(cwtRosterForm, user.getFarmBase(), new Long(id));
					return mapping.findForward(Constants.CREATE_ROSTER);
				}// end of generate roster

			} else if ("Generate Roster".equals(action)) {
				int index = 0;
				GenericDao dao = new GenericDao();
				List<CwtRoster> rosterList = new ArrayList<CwtRoster>();
				List<CwtMaster> masterList = new ArrayList<CwtMaster>();
				cwtRosterForm.setCwtRosterNotes(new CwtRosterNotes());
				
				//validate class date
				session.setAttribute("error","");
				if (cwtRosterForm.getRosterDate()!=null&&cwtRosterForm.getRosterDate().length()>0&&cwtRosterForm.getRosterDate().length()!=10) {
						session.setAttribute("error","class date needs to be in MM/DD/YYYY format");
						return mapping.findForward(Constants.CREATE_ROSTER);
					}
				else if (cwtRosterForm.getRosterDate()==null||cwtRosterForm.getRosterDate().length()==0) {
					session.setAttribute("error","class date is required");
					return mapping.findForward(Constants.CREATE_ROSTER);
				} else
					cwtRosterForm.setRosterDate(cwtRosterForm.getRosterDate().replace("-", "/"));
			
				
				//get Module 
				CwtModules module = cwtModulesDao.find(cwtRosterForm.getModuleId());
				cwtRosterForm.setCwtModule(module);
				
				//1 find sectionId for moduleId and farm
				Long sectionId = dao.findSectionIdByModuleIdAndFarm(cwtRosterForm.getModuleId(), user.getFarmBase());
				if (sectionId==null) {
					session.setAttribute("error", "Section has not been created for that module at "+user.getFarmBase());
					return mapping.findForward(Constants.CREATE_ROSTER);
				}
				CwtModuleSectionDao sectionDao = new CwtModuleSectionDao();
				CwtModuleSection cwtSection = sectionDao.find(sectionId);
				cwtRosterForm.setCwtModuleSection(cwtSection);
				
				//Check for existing Roster, throw validation error if already exists
				//List <CwtRoster> existingRosterList1 = rosterDao.searchRosters(module.getModuleId(), cwtRosterForm.getRosterDate(), "No", user.getFarmBase(), null);
				List <CwtRoster> existingRosterList = rosterDao.searchRosters(module.getModuleId(), cwtRosterForm.getRosterDate(), "Yes", user.getFarmBase(), null);
				if (existingRosterList.size()>0) {
					session.setAttribute("error", "Roster has already been generated for "+user.getFarmBase());
					return mapping.findForward(Constants.CREATE_ROSTER);
				}
				
				
				
				//2 find programId for moduleId
				Long programId = dao.findProgramIdBySectionId(sectionId);
				//3 find intakes assigned to programId
				//loop through intake and add to roster
				List<Intake> list = dao.findIntakeByCwtProgram(programId, user.getFarmBase());
				
				//for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				for (int i=0;i<list.size();i++) {
					Intake intake = (Intake)list.get(i); //iterator.next();
						CwtRoster roster = new CwtRoster();
						roster.setIntakeId(intake.getIntakeId());
						roster.setModuleId(cwtRosterForm.getModuleId());
						roster.setSectionId(sectionId);
						roster.setLastUpdatedBy(user.getUsername());
						roster.setLastUpdatedDate(validator.getEpoch() + "");
						roster.setStatus("Enrolled");
						roster.setExamDate("");
						roster.setArchivedFlag("Yes");
						roster.setRosterDate(cwtRosterForm.getRosterDate());
						rosterDao.save(roster);
					}
				
				// Roster setup
				//List<CwtRoster> rList = rosterDao.findByObjectId(
				//		new CwtRoster().getClass(), "sectionId", sectionId); // .listRosterBySection(new
				List<CwtRoster> rList = rosterDao.findRosterBySectionIdRosterDate(sectionId,cwtRosterForm.getRosterDate()); // .listRosterBySection(new

				String rosterDate="";
				String attend[] = cwtRosterForm.getAttendFlag();
				String score[] = cwtRosterForm.getExamScore();
				String status[] = cwtRosterForm.getStatus();
				List<CwtMaster> masters = new ArrayList<CwtMaster>();
				for (Iterator iterator = rList.iterator(); iterator
						.hasNext();) {
					CwtRoster roster = (CwtRoster) iterator.next();

					ViewCwtIntake intake = new ViewCwtIntake();
					if (roster.getIntakeId() != null)
						intake = dao.findCwtIntakeByIntakeId(new ViewCwtIntake().getClass(), roster.getIntakeId() );
					if (intake!=null) {				
						attend[index] = roster.getAttendFlag();
						score[index] = roster.getExamScore();
						status[index] = roster.getStatus();
						CwtMaster master = new CwtMaster();
						master.setCwtIntake(intake);
						master.setRoster(roster);
						masters.add(master);
						index++;
					}
					
					rosterDate = roster.getRosterDate();
				}
				cwtRosterForm.setMasterList(masters);
				cwtRosterForm.setAttendFlag(attend);
				cwtRosterForm.setExamScore(score);
				cwtRosterForm.setRosterDate(rosterDate);

				return mapping.findForward(Constants.ROSTER);
			}
			else if ("Save".equals(action)) {
				int index = 0;
				String status = "";
				List<CwtMaster> mlist = cwtRosterForm.getMasterList();
				List<CwtRoster> rosterList = new ArrayList<CwtRoster>();
				String enroll[] = cwtRosterForm.getEnrollFlag();
				String attend[] = cwtRosterForm.getAttendFlag();
				CwtRosterNotes notes = cwtRosterForm.getCwtRosterNotes();
				
				Long sectionId = null;
				String rosterDate = null;
				for (Iterator iterator = mlist.iterator(); iterator.hasNext();) {
					CwtMaster obj = (CwtMaster) iterator.next();
					CwtRoster roster = obj.getRoster();
					rosterDate = roster.getRosterDate();
					roster.setLastUpdatedBy(user.getUsername());
					roster.setLastUpdatedDate(validator.getEpoch() + "");

					if ("Yes".equals(request.getParameter("attendFlag[" + index
							+ "]")))
						roster.setAttendFlag("Yes");
					roster.setExamScore(request.getParameter("examScore["
							+ index + "]"));
					roster.setStatus(request.getParameter("status[" + index
							+ "]"));
					
					/*if ("Yes".equals(cwtRosterForm.getArchiveFlag()))
						roster.setArchivedFlag("Yes");
					else*/
						roster.setArchivedFlag("Yes");

					rosterDao.update(roster);
					sectionId = roster.getSectionId();
					index++;
				}

				// CwtModuleSection section =
				// cwtModuleSectionDao.find(sectionId);
				// section.setInstructorNotes(cwtRosterForm.getNotes());
				// cwtModuleSectionDao.update(cwtRosterForm.getCwtModuleSection());
				cwtRosterForm.setArchiveFlag("Yes");
				
				if (notes.getNotes()!=null&&notes.getNotes().length()>0) {
					CwtRosterNotesDao dao = new CwtRosterNotesDao();
					notes.setSectionId(sectionId);
					notes.setRosterDate(rosterDate);
					if (notes.getNotesId()==null)
						dao.save(notes);
					else
						dao.update(notes);
				}
				
				cwtRosterForm.setNotes("");
				// cwtRosterForm.setMasterList(newMasterList);
				return mapping.findForward(Constants.ROSTER_SEARCH);
			}
			return mapping.findForward(Constants.SUCCESS);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			session.setAttribute("SYSTEM_ERROR", sw.toString());
			e.printStackTrace();
			return mapping.findForward(Constants.ERROR);
		}
	}
	
	
	private void generateRoster(CwtRosterForm form, String farm, Long moduleOfferingId) {
		
		IntakeDao intakeDao = new IntakeDao();
		CwtDepartmentDao departmentDao = new CwtDepartmentDao();
		CwtSupervisorDao supervisorDao = new CwtSupervisorDao();
		CwtJobDao jobDao = new CwtJobDao();
		GenericDao dao = new GenericDao();
		
		Long cwtProgramId = dao.findProgramIdBySectionId(moduleOfferingId);
		List<CwtMaster> masters = new ArrayList<CwtMaster>();
		//List<Intake> intakes = intakeDao.search(null, null, null,
		//		null, null, null, farm, null, "No", "In Program",null,null,null,null);
		List<Intake> intakes = intakeDao.search(null, null, null, null, null, null, farm, null, "No", "In Program", null, null, null, null, cwtProgramId);				
		
		for (Iterator iterator = intakes.iterator(); iterator
				.hasNext();) {
			Intake intake = (Intake) iterator.next();

			CwtDepartment department = new CwtDepartment();
			/*if (intake.getDepartmentId() != null)
				department = departmentDao.find(intake
						.getDepartmentId());
			if (department == null)
				department = new CwtDepartment();*/

			CwtSupervisor supervisor = new CwtSupervisor();
			/*if (intake.getSupervisorId() != null)
				supervisor = supervisorDao.find(intake
						.getSupervisorId());
			if (supervisor == null)
				supervisor = new CwtSupervisor();*/

			CwtJob job = new CwtJob();
			/*if (intake.getJobId() != null)
				job = jobDao.find(intake.getJobId());
			if (job == null)
				job = new CwtJob();*/

			CwtMaster master = new CwtMaster();
			master.setCwtSupervisor(supervisor);
			master.setCwtDepartment(department);
			master.setCwtJob(job);
			master.setIntake(intake);
			masters.add(master);
		}
		form.setMasterList(masters);
		
	}

}
