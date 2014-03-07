package org.faithfarm.sms.struts.action;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.faithfarm.sms.application.Constants;
import org.faithfarm.sms.domain.CwtMaster;
import org.faithfarm.sms.domain.CwtModules;
import org.faithfarm.sms.domain.CwtProgram;
import org.faithfarm.sms.domain.CwtRoster;
import org.faithfarm.sms.domain.Intake;
import org.faithfarm.sms.domain.IntakeJobSkill;
import org.faithfarm.sms.domain.IntakeMedicalCondition;
import org.faithfarm.sms.domain.IntakeQuestionAnswer;
import org.faithfarm.sms.domain.JobSkill;
import org.faithfarm.sms.domain.MedicalCondition;
import org.faithfarm.sms.domain.SearchParameter;
import org.faithfarm.sms.domain.StudentDisciplineHistory;
import org.faithfarm.sms.domain.StudentHistory;
import org.faithfarm.sms.domain.StudentPassHistory;
import org.faithfarm.sms.domain.SystemUser;
import org.faithfarm.sms.hibernate.data.CwtProgramDao;
import org.faithfarm.sms.hibernate.data.CwtRosterDao;
import org.faithfarm.sms.hibernate.data.GenericDao;
import org.faithfarm.sms.hibernate.data.IntakeDao;
import org.faithfarm.sms.hibernate.data.IntakeJobSkillDao;
import org.faithfarm.sms.hibernate.data.IntakeMedicalConditionDao;
import org.faithfarm.sms.hibernate.data.IntakeQuestionAnswerDao;
import org.faithfarm.sms.hibernate.data.StudentDisciplineHistoryDao;
import org.faithfarm.sms.hibernate.data.StudentHistoryDao;
import org.faithfarm.sms.hibernate.data.StudentPassHistoryDao;
import org.faithfarm.sms.struts.form.IntakeForm;
import org.faithfarm.sms.util.HtmlDropDownBuilder;
import org.faithfarm.sms.util.Validator;
import org.faithfarm.sms.validator.IntakeValidator;

public class IntakeAction extends Action {

	private final static Logger LOGGER = Logger.getLogger(IntakeAction.class
			.getName());
	private final static HtmlDropDownBuilder html = new HtmlDropDownBuilder();
	private final static IntakeValidator inakeValidator = new IntakeValidator();
	private final static Validator validator = new Validator();

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.setLevel(Level.SEVERE);

		HttpSession session = request.getSession(false);
		SystemUser user = (SystemUser) session.getAttribute("system_user");
		session.setAttribute("SYSTEM_ERROR", null);
		
		try {
			
			/*
			 * test ddl's stored in session.  If empty, session expired, redirect to login
			 */
			ArrayList list = (ArrayList)session.getAttribute("ddl_farm");
			if (list==null) 
				return mapping.findForward(Constants.LOGIN);
			
			String action = request.getParameter("action");
			IntakeForm intakeForm = (IntakeForm) form;
			IntakeDao intakeDao = new IntakeDao();
			StudentHistoryDao studentHistoryDao = new StudentHistoryDao();
			StudentDisciplineHistoryDao studentDisciplineHistoryDao = new StudentDisciplineHistoryDao();
			StudentPassHistoryDao studentPassHistoryDao = new StudentPassHistoryDao();
			IntakeMedicalConditionDao intakeMedicalConditionDao = new IntakeMedicalConditionDao();
			IntakeQuestionAnswerDao intakeQuestionAnswerDao = new IntakeQuestionAnswerDao();
			IntakeJobSkillDao intakeJobSkillDao = new IntakeJobSkillDao();

			
			// clear out messages
			intakeForm.setMessages(new ArrayList());
			intakeForm.setMessageType("");
			intakeForm.setMessage(null);
			session.setAttribute("PRINT_INTAKE", null);
			session.setAttribute("PRINT_INTAKE_NAME", null);
			session.setAttribute("PRINT_INTAKE_FARM", null);
			session.setAttribute("PRINT_INTAKE_PHONE", null);

			if (intakeForm.getEditIndex() == null)
				intakeForm.setEditIndex(new Integer(-1));

			/*
			 * First check to see if user is printing PDF application. If so,
			 * handle first so there will be no redirection, since the PDF
			 * servlet opens in a separate window. There is no need to forward.
			 */
			if ("Yes".equals(intakeForm.getUploadFileFlag())) {
				FormFile file = intakeForm.getImageFile();
				try {
					// InputStream stream = file.getInputStream();
					InputStream stream = new BufferedInputStream(
							file.getInputStream());
					final byte[] bytes = new byte[5224024];
					int read = stream.read(bytes);
					intakeForm.getIntake().setImageHeadshot(bytes);
				} catch (Exception e) {
					LOGGER.log(Level.SEVERE, e.getMessage());
				}
				intakeForm.setUploadFileFlag(null);
				return mapping.findForward(Constants.PERSONAL);
			} else
			if ("personal".equals(action))
				return mapping.findForward(Constants.PERSONAL);
			else if ("religious".equals(action))
				return mapping.findForward(Constants.RELIGIOUS);
			else if ("substance".equals(action))
				return mapping.findForward(Constants.SUBSTANCE);
			else if ("health".equals(action))
				return mapping.findForward(Constants.HEALTH);
			else if ("legal".equals(action))
				return mapping.findForward(Constants.LEGAL);
			else if ("employment".equals(action))
				return mapping.findForward(Constants.EMPLOYMENT);
			else if ("status".equals(action)) {
				intakeForm.setEditIndex(new Integer(-1));
				return mapping.findForward(Constants.STATUS);
			} else if ("cwt".equals(action)) {
				this.trackCwt(intakeForm,session);
				return mapping.findForward(Constants.STUDENT_CWT);
			} else if ("Reset Cwt".equals(action)) {
				String id=request.getParameter("deleteId");
				this.deleteCwt(new Long(id));
				this.trackCwt(intakeForm,session);
				return mapping.findForward(Constants.STUDENT_CWT);
			}else if ("PrintFull".equals(action)) {
				session.setAttribute("PRINT_INTAKE", intakeForm.getIntake());
				session.setAttribute("PRINT_INTAKE_NAME", intakeForm
						.getIntake().getFirstname()
						+ " "
						+ intakeForm.getIntake().getMi()
						+ " "
						+ intakeForm.getIntake().getLastname());
				session.setAttribute("PRINT_INTAKE_FARM", intakeForm
						.getIntake().getFarmBase());
				session.setAttribute("PRINT_INTAKE_PHONE", intakeForm
						.getIntake().getReferredByPhone());
				return mapping.findForward(Constants.PRINT);
			} else if ("pass".equals(action))
				return mapping.findForward(Constants.PASS);

			else if ("discipline".equals(action)) {
				intakeForm
						.setStudentDisciplineHistory(studentDisciplineHistoryDao.findByIntakeId(new StudentDisciplineHistory().getClass(), intakeForm.getIntake().getIntakeId()));
								//.findByIntakeId(intakeForm.getIntake()
								//		.getIntakeId()));
				return mapping.findForward(Constants.DISCIPLINE);
			} else if ("Photo".equals(action))
				return mapping.findForward(Constants.PHOTO);
			else if ("Search".equals(action)) {
				intakeForm.setIntake(new Intake());
				intakeForm.setGedFlag(null);
				intakeForm.setArchivedFlag(null);
				intakeForm.setPictureFlag(null);
				intakeForm.setProgramStatus(null);
				return mapping.findForward(Constants.SEARCH);
			} else if ("Search Students".equals(action)) {
				String entryDate = intakeForm.getIntake().getEntryDate();
				String exitDate = intakeForm.getIntake().getExitDate();
				String lastname = intakeForm.getIntake().getLastname();
				String firstname = intakeForm.getIntake().getFirstname();
				String ssn = intakeForm.getIntake().getSsn();
				String dob = intakeForm.getIntake().getDob();
				String farm = intakeForm.getIntake().getFarmBase();
				String ged = intakeForm.getGedFlag();
				String archived = intakeForm.getArchivedFlag();
				String status = intakeForm.getProgramStatus();
				String currentClass = intakeForm.getCurrentClass();
				Long jobId = intakeForm.getJobId();
				Long jobSkillId = intakeForm.getJobSkillId();
				Long supervisorId = intakeForm.getSupervisorId();
				String driverFlag = intakeForm.getDriverFlag();
				
				List intakeList = intakeDao.search(entryDate, exitDate,
						lastname, firstname, ssn, dob, farm, ged, archived,
						status, currentClass, jobId, supervisorId, driverFlag, null);
				
				if (jobSkillId!=null && jobSkillId>0) {
					
					IntakeJobSkillDao dao = new IntakeJobSkillDao();
					List<Intake> newList = new ArrayList<Intake>();
			        for (Iterator iterator =
			        		intakeList.iterator(); iterator.hasNext();){
			    			Intake obj = (Intake) iterator.next();
			    			String encryptSsn = Validator.encryptSsn(obj.getSsn());
			    			obj.setEncryptedSsn(encryptSsn);
			    			boolean match = dao.findByIntakeIdAndObjectId(IntakeJobSkill.class, "jobSkillId", obj.getIntakeId(), jobSkillId);
			    			if (match)
			    				newList.add(obj);			    	
			        }
			        intakeList=newList;		
				}
				for (int i=0;i<intakeList.size();i++) {
						Intake obj = (Intake)intakeList.get(i);
						if (obj!=null) {
							String ssn1=obj.getSsn();
							obj.setEncryptedSsn(Validator.encryptSsn(ssn1));
						}
					}
					
				
				intakeForm.setIntakeList(intakeList);

				if (intakeList.size() > 199)
					intakeForm
							.setMessage("More than 200 results were returned. Please narrow your search.");

				return mapping.findForward(Constants.RESULTS);
			} else if ("SearchApps".equals(action)) {
				intakeForm.setSearchParameter(new SearchParameter());
				return mapping.findForward(Constants.SEARCH_APPLICATIONS);
			} else if ("Search Applications".equals(action)) {
				List intakeList = intakeDao.searchApplications(intakeForm
						.getSearchParameter().getBeginDate(), intakeForm
						.getSearchParameter().getEndDate(), intakeForm
						.getSearchParameter().getLastname(), intakeForm
						.getSearchParameter().getFirstname(), intakeForm
						.getSearchParameter().getSsn(), intakeForm
						.getSearchParameter().getDob(), intakeForm
						.getSearchParameter().getApplicationStatus(),
						intakeForm.getSearchParameter().getDriverFlag(),
						intakeForm.getSearchParameter().getGedFlag(),
						intakeForm.getSearchParameter().getFarmBase());

				Long jobSkillId = intakeForm.getSearchParameter().getJobSkillId();
				if (jobSkillId!=null && jobSkillId>0) {
					
					IntakeJobSkillDao dao = new IntakeJobSkillDao();
					List<Intake> newList = new ArrayList<Intake>();
			        for (Iterator iterator =
			        		intakeList.iterator(); iterator.hasNext();){
			    			Intake obj = (Intake) iterator.next();
			    			boolean match = dao.findByIntakeIdAndObjectId(IntakeJobSkill.class, "jobSkillId", obj.getIntakeId(), jobSkillId);
			    			if (match)
			    				newList.add(obj);			    	
			        }
			        intakeList=newList;		
				}
			
				for (int i=0;i<intakeList.size();i++) {
					Intake obj = (Intake)intakeList.get(i);
					if (obj!=null) {
						String ssn=obj.getSsn();
						obj.setEncryptedSsn(Validator.encryptSsn(ssn));
					}
				}
				intakeForm.setApplicantList(intakeList);

				if (intakeList != null && intakeList.size() > 199)
					intakeForm
							.setMessage("More than 200 results were returned. Please narrow your search.");

				return mapping.findForward(Constants.APPLICATIONS);
			} else if ("Admit".equals(action)) {
				String entryDate = validator.convertEpoch(validator.getEpoch());

				intakeForm.getIntake().setApplicationStatus("In Program");
				intakeForm.getIntake().setIntakeStatus("In Program");
				intakeForm.getIntake().setClass_("Orientation");
				intakeForm.getIntake().setLastUpdatedDate(
						validator.getEpoch() + "");
				intakeForm.getIntake().setLastUpdatedBy(user.getUsername());
				intakeForm.getIntake().setFarmBase(user.getFarmBase());
				intakeForm.getIntake().setEntryDate(entryDate);
				intakeForm.getIntake().setArchivedFlag("No");
				intakeDao.update(intakeForm.getIntake());

				// create history record
				StudentHistory history = new StudentHistory();
				history.setBeginDate(entryDate);
				history.setFarm(user.getFarmBase());
				history.setPhase("Phase One");
				history.setProgramStatus("In Program");
				history.setCreatedBy(user.getUsername());
				history.setCreationDate(validator.getEpoch() + "");
				history.setIntakeId(intakeForm.getIntake().getIntakeId());

				studentHistoryDao.save(history);
				return mapping.findForward(Constants.PERSONAL);
			} else if ("Transfer".equals(action)) {
				String tfarm = request.getParameter("tfarm");
				if ("OKE".equals(tfarm))
					intakeForm.getIntake().setFarmBase("Okechobee");
				if ("BYN".equals(tfarm))
					intakeForm.getIntake().setFarmBase("Boynton Beach");
				if ("FTL".equals(tfarm))
					intakeForm.getIntake().setFarmBase("Fort Lauderdale");
				if ("EHW".equals(tfarm))
					intakeForm.getIntake().setFarmBase("Women's Home");
				intakeDao.update(intakeForm.getIntake());
				this.sendEmail(intakeForm.getIntake().getFarmBase(), user.getFarmBase(), intakeForm, request);
				List intakeList = intakeDao.searchApplications(intakeForm
						.getSearchParameter().getBeginDate(), intakeForm
						.getSearchParameter().getEndDate(), intakeForm
						.getSearchParameter().getLastname(), intakeForm
						.getSearchParameter().getFirstname(), intakeForm
						.getSearchParameter().getSsn(), intakeForm
						.getSearchParameter().getDob(), intakeForm
						.getSearchParameter().getApplicationStatus(),
						intakeForm.getSearchParameter().getDriverFlag(),
						intakeForm.getSearchParameter().getGedFlag(),
						intakeForm.getSearchParameter().getFarmBase());

				Long jobSkillId = intakeForm.getSearchParameter().getJobSkillId();
				if (jobSkillId!=null && jobSkillId>0) {
					
					IntakeJobSkillDao dao = new IntakeJobSkillDao();
					List<Intake> newList = new ArrayList<Intake>();
			        for (Iterator iterator =
			        		intakeList.iterator(); iterator.hasNext();){
			    			Intake obj = (Intake) iterator.next();
			    			boolean match = dao.findByIntakeIdAndObjectId(IntakeJobSkill.class, "jobSkillId", obj.getIntakeId(), jobSkillId);
			    			if (match)
			    				newList.add(obj);			    	
			        }
			        intakeList=newList;		
				}
				intakeForm.setApplicantList(intakeList);
				if (intakeList.size() > 199)
					intakeForm
							.setMessage("More than 200 results were returned. Please narrow your search.");

				return mapping.findForward(Constants.APPLICATIONS);
			} else if ("Waitlist".equals(action)) {
				intakeForm.getIntake().setApplicationStatus("Waitlist");
				intakeForm.getIntake().setIntakeStatus("Waitlist");
				intakeForm.getIntake().setLastUpdatedDate(
						validator.getEpoch() + "");
				intakeForm.getIntake().setLastUpdatedBy(user.getUsername());
				intakeDao.update(intakeForm.getIntake());
				return mapping.findForward(Constants.PERSONAL);
			} else if ("Withdraw".equals(action)) {
				intakeForm.getIntake().setApplicationStatus("Withdrawn");
				intakeForm.getIntake().setIntakeStatus("Withdrawn");
				intakeForm.getIntake().setLastUpdatedDate(
						validator.getEpoch() + "");
				intakeForm.getIntake().setLastUpdatedBy(user.getUsername());
				intakeDao.update(intakeForm.getIntake());
				return mapping.findForward(Constants.PERSONAL);
			} else if ("Accept".equals(action)) {
				intakeForm.getIntake().setApplicationStatus("Accepted");
				intakeForm.getIntake().setIntakeStatus("Accepted");
				intakeForm.getIntake().setLastUpdatedDate(
						validator.getEpoch() + "");
				intakeForm.getIntake().setLastUpdatedBy(user.getUsername());
				intakeDao.update(intakeForm.getIntake());
				return mapping.findForward(Constants.PERSONAL);
			} else if ("Unaccept".equals(action)) {
				intakeForm.getIntake().setApplicationStatus("Pending");
				intakeForm.getIntake().setIntakeStatus("");
				intakeForm.getIntake().setLastUpdatedDate(
						validator.getEpoch() + "");
				intakeForm.getIntake().setLastUpdatedBy(user.getUsername());
				intakeDao.update(intakeForm.getIntake());
				return mapping.findForward(Constants.PERSONAL);
			} else if ("Deny".equals(action)) {
				intakeForm.getIntake().setApplicationStatus("Denied");
				intakeForm.getIntake().setLastUpdatedDate(
						validator.getEpoch() + "");
				intakeForm.getIntake().setLastUpdatedBy(user.getUsername());
				intakeDao.update(intakeForm.getIntake());
				return mapping.findForward(Constants.PERSONAL);
			} else if ("Reinstate".equals(action)) {
				intakeForm.getIntake().setApplicationStatus("Pending");
				intakeForm.getIntake().setLastUpdatedDate(
						validator.getEpoch() + "");
				intakeForm.getIntake().setLastUpdatedBy(user.getUsername());
				intakeDao.update(intakeForm.getIntake());
				return mapping.findForward(Constants.PERSONAL);
			} else if ("Create".equals(action)) {
				this.clearForm(intakeForm);
				intakeForm.getIntake().setFarmBase(user.getFarmBase());
				intakeForm.getIntake().setArchivedFlag("No");
				return mapping.findForward(Constants.PERSONAL);
			} else if ("Home".equals(action))
				return mapping.findForward(Constants.HOME);
			else if ("Edit".equals(action)) {
				String key = request.getParameter("key");
				Intake intake = intakeDao.find(new Long(key));
				intake.setEncryptedSsn(Validator.encryptSsn(intake.getSsn()));
				List studentHistory = studentHistoryDao.findByIntakeId(new StudentHistory().getClass(), intake.getIntakeId());
				List studentPassHistory = studentPassHistoryDao.findByIntakeId(new StudentPassHistory().getClass(), intake
						.getIntakeId());
				List<IntakeMedicalCondition> intakeMedicalCondition = intakeMedicalConditionDao
						.findByIntakeId(new IntakeMedicalCondition().getClass(), intake.getIntakeId());
				List<IntakeQuestionAnswer> intakeQuestionAnswer = intakeQuestionAnswerDao.findByIntakeId(new IntakeQuestionAnswer().getClass(), intake.getIntakeId());
						//.findById(intake.getIntakeId());
				List<IntakeJobSkill> intakeJobSkill = intakeJobSkillDao.findByIntakeId(new IntakeJobSkill().getClass(), intake.getIntakeId());
						//.findById(intake.getIntakeId());

				/*
				 * Check program status and graduation date to check for 'time
				 * is up', display msg if it is
				 */

				setIntakeMedicalConditions(intakeMedicalCondition, intakeForm);
				setIntakeQuestionAnswer(intakeQuestionAnswer, intakeForm);
				setIntakeJobSkills(intakeJobSkill, intakeForm);

				intakeForm.setIntake(intake);
				intakeForm.setStudentHistory(studentHistory);
				intakeForm.setStudentPassHistory(studentPassHistory);

				// convert phsycial effects to Flags
				this.setPhysicalEffects(intakeForm);
				this.setUsagePatterns(intakeForm);

				if (intake.getUsageLosses()!=null) {
					if (intake.getUsageLosses().contains("Job"))
						intakeForm.setUsageLosses1("Job");
					if (intake.getUsageLosses().contains("Marriage"))
						intakeForm.setUsageLosses2("Marriage");
					if (intake.getUsageLosses().contains("Friends"))
						intakeForm.setUsageLosses3("Friends");
					if (intake.getUsageLosses().contains("Possessions"))
						intakeForm.setUsageLosses4("Possessions");
					if (intake.getUsageLosses().contains("Arrests"))
						intakeForm.setUsageLosses5("Arrests");
					if (intake.getUsageLosses().contains("DUIs"))
						intakeForm.setUsageLosses6("DUIs");
					if (intake.getUsageLosses().contains("Heavy Debt"))
						intakeForm.setUsageLosses7("Heavy Debt");
					if (intake.getUsageLosses().contains("Health"))
						intakeForm.setUsageLosses8("Health");
					if (intake.getUsageLosses().contains("Incarceration"))
						intakeForm.setUsageLosses9("Incarceration");
				}
				/*
				 * Find the most recent status and set it for the current
				 */
				StudentHistory currentStatus = new StudentHistory();
				for (java.util.Iterator<StudentHistory> iterator = studentHistory
						.iterator(); iterator.hasNext();) {
					currentStatus = (StudentHistory) iterator.next();
				}
				intakeForm.setCurrentStatus(currentStatus);

				if ("FastFind".equals(request.getParameter("src")))
					return mapping.findForward(Constants.STATUS);
				else
					return mapping.findForward(Constants.EDIT);
			} else if ("Delete Pass History".equals(action)) {
				Long id = intakeForm.getDeleteId();
				StudentPassHistory obj = studentPassHistoryDao.find(id);
				studentPassHistoryDao.delete(obj);
				intakeForm.setStudentPassHistory(studentPassHistoryDao
						.findByIntakeId(new StudentPassHistory().getClass(),intakeForm.getIntake().getIntakeId()));
				return mapping.findForward(Constants.PASS);
			} else if ("Delete History".equals(action)) {
				Long id = intakeForm.getDeleteId();
				StudentHistory obj = studentHistoryDao.find(id);
				studentHistoryDao.delete(obj);
				intakeForm.setStudentHistory(studentHistoryDao
						.findByIntakeId(new StudentHistory().getClass(), intakeForm.getIntake().getIntakeId()));
				StudentHistory currentStatus = new StudentHistory();
				for (java.util.Iterator<StudentHistory> iterator = intakeForm
						.getStudentHistory().iterator(); iterator.hasNext();) {
					currentStatus = (StudentHistory) iterator.next();
				}
				intakeForm.setCurrentStatus(currentStatus);
				return mapping.findForward(Constants.STATUS);
			} else if ("Edit History".equals(action)) {
				Integer id = intakeForm.getEditIndex();
				Long key = intakeForm.getEditId();
				// System.out.println ("id="+id+", key="+key);
				StudentHistory status = studentHistoryDao.find(key);
				intakeForm.setEditStatus(status);
				intakeForm.setStudentHistory(studentHistoryDao
						.findByIntakeId(new StudentHistory().getClass(), intakeForm.getIntake().getIntakeId()));
				return mapping.findForward(Constants.STATUS);
			} else if ("Save History Change".equals(action)) {
				Long key = intakeForm.getEditId();
								studentHistoryDao.update(intakeForm.getEditStatus());
				intakeForm.setEditStatus(new StudentHistory());
				intakeForm.setStudentHistory(studentHistoryDao
						.findByIntakeId(new StudentHistory().getClass(), intakeForm.getIntake().getIntakeId()));
				intakeForm.setEditId(null);
				intakeForm.setEditIndex(new Integer(-1));

				StudentHistory currentStatus = new StudentHistory();
				for (java.util.Iterator<StudentHistory> iterator = intakeForm
						.getStudentHistory().iterator(); iterator.hasNext();) {
					currentStatus = (StudentHistory) iterator.next();
				}
				intakeForm.setCurrentStatus(currentStatus);

				return mapping.findForward(Constants.STATUS);
			} else if ("Delete Discipline History".equals(action)) {
				Long id = intakeForm.getDeleteId();
				StudentDisciplineHistory obj = studentDisciplineHistoryDao.find(id);
				studentDisciplineHistoryDao.delete(obj);
				intakeForm
						.setStudentDisciplineHistory(studentDisciplineHistoryDao
								.findByIntakeId(new StudentDisciplineHistory().getClass(), intakeForm.getIntake()
										.getIntakeId()));
				return mapping.findForward(Constants.DISCIPLINE);
			} else if ("Save".equals(action)) {
				intakeForm.setMessageType("");
				
				if (intakeForm.getIntake().getFarmBase()==null||intakeForm.getIntake().getFarmBase().length()==0)
					intakeForm.getIntake().setFarmBase(user.getFarmBase());
				
				boolean valid = inakeValidator.validate(intakeForm);

				if (intakeForm.getIntake().getDob().length() == 10) {
					intakeForm.getIntake().setDob(
							intakeForm.getIntake().getDob().replace("-", "/"));

					// auto calculate Age
					intakeForm.getIntake().setAge(
							validator.calculateAge(intakeForm.getIntake()
									.getDob()) + "");
				}
				if (intakeForm.getIntake().getEntryDate() != null
						&& intakeForm.getIntake().getEntryDate().length() == 10)
					intakeForm.getIntake().setEntryDate(
							intakeForm.getIntake().getEntryDate()
									.replace("-", "/"));

				if (valid) {

					if (intakeForm.getIntake().getIntakeId() != null) {
						intakeForm.getIntake().setLastUpdatedDate(
								validator.getEpoch() + "");
						intakeForm.getIntake().setLastUpdatedBy(
								user.getUsername());
						if ("health".equals(intakeForm.getPageSource()))
							saveMedicalConditions(intakeForm);
						if ("substance".equals(intakeForm.getPageSource()))
							saveUsagePatternAndLosses(intakeForm);
						if ("employment".equals(intakeForm.getPageSource()))
							saveJobSkills(intakeForm,request);
						if ("status".equals(intakeForm.getPageSource()))
							saveStatus(intakeForm, user);
						if ("cwt".equals(intakeForm.getPageSource()))
							saveCwt(intakeForm, user, session);
						if ("pass".equals(intakeForm.getPageSource()))
							savePass(intakeForm, user);
						if ("discipline".equals(intakeForm.getPageSource()))
							saveDiscipline(intakeForm, user.getUsername());
						intakeDao.update(intakeForm.getIntake());
						//intakeForm.getIntake().setEncryptedSsn(Validator.encryptSsn(intakeForm.getIntake().getSsn()));
						saveIntakeQuestionAnswer(intakeForm);

					} else {

						intakeForm.getIntake().setCreationDate(
								validator.getEpoch() + "");
						intakeForm.getIntake().setCreatedBy(user.getUsername());

						if ("personal".equals(intakeForm.getPageSource())) {
							intakeForm.getIntake()
									.setIntakeStatus("In Program");
							intakeForm.getIntake().setApplicationStatus(
									"In Program");
							intakeForm.getIntake().setClass_("Orientation");
							//intakeForm.getIntake().setEncryptedSsn(Validator.encryptSsn(intakeForm.getIntake().getSsn()));
						}
						if ("health".equals(intakeForm.getPageSource()))
							saveMedicalConditions(intakeForm);
						if ("substance".equals(intakeForm.getPageSource()))
							saveUsagePatternAndLosses(intakeForm);
						if ("employment".equals(intakeForm.getPageSource()))
							saveJobSkills(intakeForm,request);
						if ("status".equals(intakeForm.getPageSource()))
							saveStatus(intakeForm, user);
						if ("cwt".equals(intakeForm.getPageSource()))
							saveCwt(intakeForm, user, session);
						if ("pass".equals(intakeForm.getPageSource()))
							savePass(intakeForm, user);
						if ("discipline".equals(intakeForm.getPageSource()))
							saveDiscipline(intakeForm, user.getUsername());

						if (intakeForm.getIntake().getFarmBase() == null)
							intakeForm.getIntake().setFarmBase(
									user.getFarmBase());

						Long id = intakeDao.save(intakeForm.getIntake());
						intakeForm.getIntake().setIntakeId(id);
						saveIntakeQuestionAnswer(intakeForm);

						if ("personal".equals(intakeForm.getPageSource())) {
							this.setInitialStatus(intakeForm, user);
							List studentHistory = studentHistoryDao
									.findByIntakeId(new StudentHistory().getClass(), intakeForm.getIntake()
											.getIntakeId());
							intakeForm.setStudentHistory(studentHistory);
							intakeForm.getIntake().setArchivedFlag("No");
							/*
							 * Find the most recent status and set it for the
							 * current
							 */
							StudentHistory currentStatus = new StudentHistory();
							for (java.util.Iterator<StudentHistory> iterator = studentHistory
									.iterator(); iterator.hasNext();) {
								currentStatus = (StudentHistory) iterator
										.next();
							}
							intakeForm.setCurrentStatus(currentStatus);

						}

					}

				}
				return mapping.findForward(intakeForm.getPageSource());

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

	private void setIntakeMedicalConditions(
			List<IntakeMedicalCondition> intakeMedicalCondition,
			IntakeForm intakeForm) {

		String medicalCondition[] = new String[] { "No", "No", "No", "No",
				"No", "No", "No", "No", "No", "No", "No", "No", "No", "No",
				"No", "No", "No", "No", "No", "No", "No", "No", "No", "No",
				"No", "No", "No", "No", "No", "No", "No", "No", "No", "No" };

		for (java.util.Iterator<IntakeMedicalCondition> iterator = intakeMedicalCondition
				.iterator(); iterator.hasNext();) {
			IntakeMedicalCondition obj = (IntakeMedicalCondition) iterator
					.next();
			medicalCondition[obj.getMedicalConditionId().intValue() - 1] = "Yes";
		}

		intakeForm.setMedicalCondition(medicalCondition);
	}

	private void setIntakeQuestionAnswer(
			List<IntakeQuestionAnswer> intakeQuestionAnswer,
			IntakeForm intakeForm) {

		String healthAnswer[] = new String[] { "No", "No", "No", "No", "No",
				"No", "No", "No", "No", "No", "No", "No", "No", "No", "No",
				"No", "No", "No", "No", "No", "No", "No", "No", "No", "No",
				"No", "No", "No", "No", "No", "No", "No", "No", "No" };

		String emotionalAnswer[] = new String[] { "No", "No", "No", "No", "No",
				"No", "No", "No", "No", "No", "No", "No", "No", "No", "No",
				"No", "No", "No", "No", "No", "No", "No", "No", "No", "No",
				"No", "No", "No", "No", "No", "No", "No", "No", "No" };

		String physicalAnswer[] = new String[] { "No", "No", "No", "No", "No",
				"No", "No", "No", "No", "No", "No", "No", "No", "No", "No",
				"No", "No", "No", "No", "No", "No", "No", "No", "No", "No",
				"No", "No", "No", "No", "No", "No", "No", "No", "No" };

		String mentalAnswer[] = new String[] { "No", "No", "No", "No", "No",
				"No", "No", "No", "No", "No", "No", "No", "No", "No", "No",
				"No", "No", "No", "No", "No", "No", "No", "No", "No", "No",
				"No", "No", "No", "No", "No", "No", "No", "No", "No" };

		String emotionalAnswerDate[] = new String[] { "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "" };

		String emotionalAnswerDetails[] = new String[] { "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "" };

		String mentalAnswerDate[] = new String[] { "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "" };

		String mentalAnswerDetails[] = new String[] { "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "" };

		String physicalAnswerDetails[] = new String[] { "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "" };

		String questionAnswerDates[] = new String[] { "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "" };

		for (java.util.Iterator<IntakeQuestionAnswer> iterator = intakeQuestionAnswer
				.iterator(); iterator.hasNext();) {
			IntakeQuestionAnswer obj = (IntakeQuestionAnswer) iterator.next();
			int id = (int) obj.getQuestionId() - 1;
			if (id < 15)
				healthAnswer[id] = "Yes";
			else if (id > 14 && id < 21) {
				emotionalAnswer[id - 15] = "Yes";
				emotionalAnswerDate[id - 15] = obj.getDates();
				emotionalAnswerDetails[id - 15] = obj.getDetail();
			} else if (id > 20 && id < 26) {
				physicalAnswer[id - 21] = "Yes";
				physicalAnswerDetails[id - 21] = obj.getDetail();
			} else if (id > 25 && id < 32) {
				mentalAnswer[id - 26] = "Yes";
				mentalAnswerDate[id - 26] = obj.getDates();
				mentalAnswerDetails[id - 26] = obj.getDetail();
			}

		}

		intakeForm.setHealthAnswer(healthAnswer);
		intakeForm.setMentalAnswer(mentalAnswer);
		intakeForm.setMentalAnswerDate(mentalAnswerDate);
		intakeForm.setMentalAnswerDetails(mentalAnswerDetails);
		intakeForm.setPhysicalAnswer(physicalAnswer);
		intakeForm.setPhysicalAnswerDetails(physicalAnswerDetails);
		intakeForm.setEmotionalAnswer(emotionalAnswer);
		intakeForm.setEmotionalAnswerDate(emotionalAnswerDate);
		intakeForm.setEmotionalAnswerDetails(emotionalAnswerDetails);
	}

	private void setIntakeJobSkills(List<IntakeJobSkill> intakeJobSkill,
			IntakeForm intakeForm) {

		String workExperience[] = new String[] { "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "" };

		for (java.util.Iterator<IntakeJobSkill> iterator = intakeJobSkill
				.iterator(); iterator.hasNext();) {
			IntakeJobSkill obj = (IntakeJobSkill) iterator.next();
			workExperience[obj.getJobSkillId().intValue() - 1] = "Yes";
		} 

		intakeForm.setWorkExperience(workExperience);
	

	}

	private void saveIntakeQuestionAnswer(IntakeForm intakeForm) {

		IntakeQuestionAnswerDao dao = new IntakeQuestionAnswerDao();

		/*
		 * First delete all answers for given intake
		 */
		List<IntakeQuestionAnswer> intakeQuestionAnswers = dao.findByIntakeId(new IntakeQuestionAnswer().getClass(), intakeForm.getIntake().getIntakeId());
				//.findById(intakeForm.getIntake().getIntakeId());
		for (Iterator iterator = intakeQuestionAnswers.iterator(); iterator
				.hasNext();) {
			IntakeQuestionAnswer obj = (IntakeQuestionAnswer) iterator.next();
			dao.delete(obj); //obj.getIntakeQuestionAnswerId());
			//dao.deleteIntakeQuestionAnswer(obj.getIntakeQuestionAnswerId());
		}

		String healthAnswer[] = intakeForm.getHealthAnswer();
		String emotionalAnswer[] = intakeForm.getEmotionalAnswer();
		String emotionalAnswerDate[] = intakeForm.getEmotionalAnswerDate();
		String emotionalAnswerDetails[] = intakeForm
				.getEmotionalAnswerDetails();
		String physicalAnswer[] = intakeForm.getPhysicalAnswer();
		String physicalAnswerDetails[] = intakeForm.getPhysicalAnswerDetails();
		String mentalAnswer[] = intakeForm.getMentalAnswer();
		String mentalAnswerDate[] = intakeForm.getMentalAnswerDate();
		String mentalAnswerDetails[] = intakeForm.getMentalAnswerDetails();

		for (int index = 0; index < 15; index++) {

			if ("Yes".equals(healthAnswer[index])) {
				IntakeQuestionAnswer iqa = new IntakeQuestionAnswer();
				iqa.setIntakeId(intakeForm.getIntake().getIntakeId());
				iqa.setAnswer("Yes");
				iqa.setQuestionId(new Long(index + 1));
				//dao.addIntakeQuestionAnswer(iqa);
				dao.save(iqa);
			}

			if ("Yes".equals(emotionalAnswer[index])) {
				IntakeQuestionAnswer iqa = new IntakeQuestionAnswer();
				iqa.setIntakeId(intakeForm.getIntake().getIntakeId());
				iqa.setAnswer("Yes");
				iqa.setDates(emotionalAnswerDate[index]);
				iqa.setDetail(emotionalAnswerDetails[index]);
				iqa.setQuestionId(new Long(index + 16));
				//dao.addIntakeQuestionAnswer(iqa);
				dao.save(iqa);
			}

			if ("Yes".equals(physicalAnswer[index])) {
				IntakeQuestionAnswer iqa = new IntakeQuestionAnswer();
				iqa.setIntakeId(intakeForm.getIntake().getIntakeId());
				iqa.setAnswer("Yes");
				iqa.setDetail(physicalAnswerDetails[index]);
				iqa.setQuestionId(new Long(index + 22));
				//dao.addIntakeQuestionAnswer(iqa);
				dao.save(iqa);
			}

			if ("Yes".equals(mentalAnswer[index])) {
				IntakeQuestionAnswer iqa = new IntakeQuestionAnswer();
				iqa.setIntakeId(intakeForm.getIntake().getIntakeId());
				iqa.setAnswer("Yes");
				iqa.setDates(mentalAnswerDate[index]);
				iqa.setDetail(mentalAnswerDetails[index]);
				iqa.setQuestionId(new Long(index + 27));
				//dao.addIntakeQuestionAnswer(iqa);
				dao.save(iqa);
			}
		}

	}

	private void saveMedicalConditions(IntakeForm intakeForm) {

		IntakeMedicalConditionDao dao = new IntakeMedicalConditionDao();

		/*
		 * First delete all medical conditions for given intake
		 */
		List<IntakeMedicalCondition> intakeMedicalConditions = dao
				.findByIntakeId(new IntakeMedicalCondition().getClass(), intakeForm.getIntake().getIntakeId());
		for (Iterator iterator = intakeMedicalConditions.iterator(); iterator
				.hasNext();) {
			IntakeMedicalCondition obj = (IntakeMedicalCondition) iterator
					.next();
			dao.delete(obj); //.getIntakeMedicalConditionId());
		}

		/*
		 * write to db medical conditions for given intake marked yes
		 */
		List<MedicalCondition> medicalConditions = intakeForm
				.getMedicalConditions();
		String medicalCondition[] = intakeForm.getMedicalCondition();
		int index = 0;
		for (Iterator iterator = medicalConditions.iterator(); iterator
				.hasNext();) {
			MedicalCondition obj = (MedicalCondition) iterator.next();

			if ("Yes".equals(medicalCondition[index])) {
				IntakeMedicalCondition imc = new IntakeMedicalCondition();
				imc.setIntakeId(intakeForm.getIntake().getIntakeId());
				imc.setMedicalConditionId(obj.getMedicalConditionId());
				imc.setAnswer("Yes");
				dao.save(imc);
			}

			index++;
		}

	}

	private void saveJobSkills(IntakeForm intakeForm, HttpServletRequest request) {

		IntakeJobSkillDao dao = new IntakeJobSkillDao();

		/*
		 * First delete all Job SKills for given intake
		 */
		List<IntakeJobSkill> intakeJobSkills = dao.findByIntakeId(new IntakeJobSkill().getClass(), intakeForm.getIntake().getIntakeId());
				//.findById(intakeForm.getIntake().getIntakeId());
		for (Iterator iterator = intakeJobSkills.iterator(); iterator.hasNext();) {
			IntakeJobSkill obj = (IntakeJobSkill) iterator.next();
			dao.delete(obj);//deleteIntakeJobSkill(obj.getIntakeJobSkillId());
		}

		/*
		 * write to db job skills for given intake marked yes
		 */
		List<JobSkill> jobSkills = intakeForm.getJobSkills();
		String workexperience[] = intakeForm.getWorkExperience();
		int index = 0;
		for (Iterator iterator = jobSkills.iterator(); iterator
				.hasNext();) {
			JobSkill obj = (JobSkill) iterator.next();
			String workExperienceFlag=request.getParameter("workExperience["+index+"]");
			if ("Yes".equals(workExperienceFlag)) {
				IntakeJobSkill ijs = new IntakeJobSkill();
				ijs.setIntakeId(intakeForm.getIntake().getIntakeId());
				ijs.setJobSkillId(obj.getJobSkillId());
				dao.save(ijs);//.addIntakeJobSkill(ijs);
				workexperience[index]="Yes";
			} else
				workexperience[index]="No";

			index++;
		}
		
		intakeForm.setWorkExperience(workexperience);
	}

	private void saveUsagePatternAndLosses(IntakeForm intakeForm) {
		intakeForm.getIntake().setUsageLosses(
				intakeForm.getUsageLosses1() + intakeForm.getUsageLosses2()
						+ intakeForm.getUsageLosses3()
						+ intakeForm.getUsageLosses4()
						+ intakeForm.getUsageLosses5()
						+ intakeForm.getUsageLosses6()
						+ intakeForm.getUsageLosses7()
						+ intakeForm.getUsageLosses8()
						+ intakeForm.getUsageLosses9());

		intakeForm.getIntake().setUsagePattern(
				intakeForm.getUsagePattern1() + intakeForm.getUsagePattern2()
						+ intakeForm.getUsagePattern3()
						+ intakeForm.getUsagePattern4()
						+ intakeForm.getUsagePattern5()
						+ intakeForm.getUsagePattern6());

		this.convertPhysicalEffects(intakeForm);
	}

	private void convertPhysicalEffects(IntakeForm intakeForm) {
		String physicalEffects = "";
		if ("Yes".equals(intakeForm.getMotivationalLossFlag()))
			physicalEffects += "motivational loss,";
		if ("Yes".equals(intakeForm.getShakesConvulsionsFlag()))
			physicalEffects += "shakes-convulsions,";
		if ("Yes".equals(intakeForm.getMemoryLossFlag()))
			physicalEffects += "memory loss,";
		if ("Yes".equals(intakeForm.getIncoherentThinkingFlag()))
			physicalEffects += "incoherent thinking,";
		if ("Yes".equals(intakeForm.getOrganProblemsFlag()))
			physicalEffects += "organ problems,";

		intakeForm.getIntake().setPhysicalEffects(physicalEffects);
	}

	private void setPhysicalEffects(IntakeForm intakeForm) {
		String physicalEffects = intakeForm.getIntake().getPhysicalEffects();

		if (physicalEffects != null) {
			if (physicalEffects.contains("motivational"))
				intakeForm.setMotivationalLossFlag("Yes");
			if (physicalEffects.contains("shakes"))
				intakeForm.setShakesConvulsionsFlag("Yes");
			if (physicalEffects.contains("memory"))
				intakeForm.setMemoryLossFlag("Yes");
			if (physicalEffects.contains("incoherent"))
				intakeForm.setIncoherentThinkingFlag("Yes");
			if (physicalEffects.contains("organ"))
				intakeForm.setOrganProblemsFlag("Yes");
		}
	}

	private void setUsagePatterns(IntakeForm intakeForm) {
		String usagePattern = intakeForm.getIntake().getUsagePattern();

		if (usagePattern != null) {
			if (usagePattern.contains("Constantly"))
				intakeForm.setUsagePattern1("Yes");
			if (usagePattern.contains("Weekends"))
				intakeForm.setUsagePattern2("Yes");
			if (usagePattern.contains("Special Occasions"))
				intakeForm.setUsagePattern3("Yes");
			if (usagePattern.contains("Whenever available"))
				intakeForm.setUsagePattern4("Yes");
			if (usagePattern.contains("When Things Get Tough"))
				intakeForm.setUsagePattern5("Yes");
			if (usagePattern.contains("A Week/Off A Week"))
				intakeForm.setUsagePattern6("Yes");
		}
	}

	private void saveStatus(IntakeForm intakeForm, SystemUser user) {

		StudentHistoryDao dao = new StudentHistoryDao();
		IntakeDao intakeDao = new IntakeDao();
		StudentPassHistoryDao dao1 = new StudentPassHistoryDao();
		StudentPassHistory passHistory = intakeForm.getPassHistory();
		StudentHistory studentHistory = intakeForm.getHistory();

		if (studentHistory.getFarm().length() > 0
				&& studentHistory.getPhase().length() > 0
				&& studentHistory.getProgramStatus().length() > 0
				&& studentHistory.getBeginDate().length() > 0
				|| studentHistory.getEndDate().length() > 0) {
			studentHistory.setIntakeId(intakeForm.getIntake().getIntakeId());
			studentHistory.setCreationDate(validator.getEpoch() + "");
			studentHistory.setCreatedBy(user.getUsername());
			dao.save(studentHistory);

			// update intakeStatus to program status
			intakeForm.getIntake().setIntakeStatus(
					studentHistory.getProgramStatus());
			intakeForm.getIntake().setApplicationStatus(
					studentHistory.getProgramStatus());
			intakeForm.getIntake().setFarmBase(studentHistory.getFarm());
			intakeDao.update(intakeForm.getIntake());

		}

		/*
		 * if (passHistory.getPassDate().length()>0 &&
		 * passHistory.getHours().length()>0 &&
		 * passHistory.getPassType().length()>0) {
		 * passHistory.setIntakeId(intakeForm.getIntake().getIntakeId());
		 * passHistory.setCreationDate(validator.getEpoch()+"");
		 * passHistory.setCreatedBy(user.getUsername());
		 * dao1.addStudentPassHistory(passHistory); }
		 */

		// intakeForm.setPassHistory(new StudentPassHistory());
		intakeForm.setHistory(new StudentHistory());
		intakeForm.setCurrentStatus(studentHistory);
		intakeForm.setStudentHistory(dao.findByIntakeId(new StudentHistory().getClass(), intakeForm.getIntake()
				.getIntakeId()));
		// intakeForm.setStudentPassHistory(dao1.findByIntakeId(intakeForm.getIntake().getIntakeId()));

	}
	
	private void saveCwt(IntakeForm intakeForm, SystemUser user, HttpSession session) {

		IntakeDao intakeDao = new IntakeDao();
		CwtRosterDao rosterDao = new CwtRosterDao();
		GenericDao dao = new GenericDao();
		
		if (intakeForm.getCwtModuleId()!=null&&!intakeForm.getCwtModuleId().equals(new Long(0)) ) {
			Long sectionId = dao.findSectionIdByModuleIdAndFarm(intakeForm.getCwtModuleId(), user.getFarmBase());
			
			if (sectionId!=null) {
				CwtRoster roster = new CwtRoster();
				
				if (intakeForm.getExamScore()==null)
					intakeForm.setExamScore("");
				
				roster.setExamScore(intakeForm.getExamScore());
				roster.setRosterDate(intakeForm.getClassDate());
				roster.setAttendFlag(intakeForm.getAttended());
				roster.setModuleId(intakeForm.getCwtModuleId());
				roster.setStatus(intakeForm.getCwtStatus());
				roster.setSectionId(sectionId);
				roster.setIntakeId(intakeForm.getIntake().getIntakeId());
				roster.setArchivedFlag("Yes");
				roster.setLastUpdatedBy(user.getUsername());
				roster.setLastUpdatedDate(validator.getEpoch()+"");
				rosterDao.save(roster);
				//refresh cwt list
				this.trackCwt(intakeForm, session);
				
				intakeForm.setCwtModuleId(new Long(0));
				intakeForm.setClassDate("");
				intakeForm.setAttended("");
				intakeForm.setCwtStatus("");
				intakeForm.setExamScore("");
			}
		}
		intakeDao.update(intakeForm.getIntake());

		}


	private void savePass(IntakeForm intakeForm, SystemUser user) {

		StudentPassHistoryDao dao1 = new StudentPassHistoryDao();
		StudentPassHistory passHistory = intakeForm.getPassHistory();

		if (passHistory.getPassDate().length() > 0
				&& passHistory.getHours().length() > 0
				&& passHistory.getPassType().length() > 0) {
			passHistory.setIntakeId(intakeForm.getIntake().getIntakeId());
			passHistory.setCreationDate(validator.getEpoch() + "");
			passHistory.setCreatedBy(user.getUsername());
			dao1.save(passHistory);
		}

		intakeForm.setPassHistory(new StudentPassHistory());
		intakeForm.setStudentPassHistory(dao1.findByIntakeId(new StudentPassHistory().getClass(), intakeForm
				.getIntake().getIntakeId()));
	} 

	private void clearForm(IntakeForm intakeForm) {
		intakeForm.setCurrentStatus(new StudentHistory());
		intakeForm.setEmotionalAnswer(new String[15]);
		intakeForm.setEmotionalAnswerDate(new String[15]);
		intakeForm.setEmotionalAnswerDetails(new String[15]);
		intakeForm.setMentalAnswer(new String[15]);
		intakeForm.setMentalAnswerDate(new String[15]);
		intakeForm.setMentalAnswerDetails(new String[15]);
		intakeForm.setPhysicalAnswer(new String[15]);
		intakeForm.setPhysicalAnswerDetails(new String[15]);
		intakeForm.setHealthAnswer(new String[15]);
		intakeForm.setHistory(new StudentHistory());
		intakeForm.setStudentHistory(new ArrayList<StudentHistory>());
		intakeForm.setStudentPassHistory(null);// new
												// ArrayList<StudentPassHistory>());
		intakeForm.setPassHistory(new StudentPassHistory());
		intakeForm.setWorkExperience(new String[30]);
		intakeForm.setIntake(new Intake());
		intakeForm.setUsageLosses1("");
		intakeForm.setUsageLosses2("");
		intakeForm.setUsageLosses3("");
		intakeForm.setUsageLosses4("");
		intakeForm.setUsageLosses5("");
		intakeForm.setUsageLosses6("");
		intakeForm.setUsageLosses7("");
		intakeForm.setUsageLosses8("");
		intakeForm.setUsageLosses9("");
		intakeForm.setUsagePattern1("");
		intakeForm.setUsagePattern2("");
		intakeForm.setUsagePattern3("");
		intakeForm.setUsagePattern4("");
		intakeForm.setUsagePattern5("");
		intakeForm.setUsagePattern6("");
		intakeForm.setMotivationalLossFlag("");
		intakeForm.setShakesConvulsionsFlag("");
		intakeForm.setMemoryLossFlag("");
		intakeForm.setIncoherentThinkingFlag("");
		intakeForm.setOrganProblemsFlag("");
	}

	private void saveDiscipline(IntakeForm intakeForm, String username) {

		StudentDisciplineHistoryDao dao = new StudentDisciplineHistoryDao();
		intakeForm.getDisciplineHistory().setIntakeId(
				intakeForm.getIntake().getIntakeId());
		intakeForm.getDisciplineHistory().setCreatedBy(username);
		intakeForm.getDisciplineHistory().setCreationDate(
				validator.getEpoch() + "");
		dao.save(intakeForm.getDisciplineHistory());
		intakeForm.setDisciplineHistory(new StudentDisciplineHistory());

	}

	private String flagStudentStay(IntakeForm intakeForm) {
		String msg = "";

		StudentHistoryDao dao = new StudentHistoryDao();
		Intake intake = intakeForm.getIntake();

		List<StudentHistory> list = dao.findByIntakeId(new StudentHistory().getClass(), intake.getIntakeId());

		StudentHistory history = new StudentHistory();

		if (list != null)
			history = (StudentHistory) list.get(0);

		String phase = history.getPhase();
		String status = history.getProgramStatus();
		String beginDate = history.getBeginDate();

		if ("In Program".equals(status)) {
			if ("Graduate".equals(phase)) {

			}
			if ("Omega Work".equals(phase)) {

			}
			if ("Omega School".equals(phase)) {

			}
		}

		return msg;
	}

	private void setInitialStatus(IntakeForm intakeForm, SystemUser user) {
		StudentHistoryDao dao = new StudentHistoryDao();
		Intake intake = intakeForm.getIntake();
		StudentHistory history = new StudentHistory();

		GregorianCalendar calendar = new GregorianCalendar();
		Date now = calendar.getTime();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String beginDate = df.format(now);

		history.setProgramStatus("In Program");
		history.setPhase("Phase One");
		history.setFarm(user.getFarmBase());
		history.setCreatedBy(user.getUsername());
		history.setCreationDate(validator.getEpoch() + "");
		history.setBeginDate(beginDate);
		history.setIntakeId(intake.getIntakeId());

		dao.save(history);

	}
	
	private void sendEmail (String tfarm, String ffarm, IntakeForm intakeForm, HttpServletRequest request) {
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		Session mailSession = Session.getDefaultInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("faithfarm.intake@gmail.com","It0525Ff");
					}
				});
		try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(mailSession);

	         // Set From: header field of the header.
	         //message.setFrom(new InternetAddress("donnotreply@faithfarm.org"));

	         // Set To: header field of the header.
	         
	         //temp for testing purposes
	         message.addRecipient(Message.RecipientType.TO,
                     new InternetAddress("itdepartment@faithfarm.org"));
	         
	         if ("Boynton Beach".equals(tfarm)) {
	        	 message.addRecipient(Message.RecipientType.TO,
	                     new InternetAddress("intake.boyntonbeach@faithfarm.org"));
	        	 message.addRecipient(Message.RecipientType.TO,
	                     new InternetAddress("PZielinski@faithfarm.org"));
		         message.addRecipient(Message.RecipientType.TO,
	                     new InternetAddress("RJurisDick@faithfarm.org"));
		     }
	         if ("Fort Lauderdale".equals(tfarm)) {
	        	 message.addRecipient(Message.RecipientType.TO,
	                     new InternetAddress("intake.fortlauderdale@faithfarm.org"));
	        	 message.addRecipient(Message.RecipientType.TO,
	                     new InternetAddress("sjohnson@faithfarm.org"));
		         message.addRecipient(Message.RecipientType.TO,
	                     new InternetAddress("iftl@faithfarm.org"));
		     }
	         if ("Okeechobee".equals(tfarm)) {
	        	 message.addRecipient(Message.RecipientType.TO,
	                     new InternetAddress("intake.okeechobee@faithfarm.org"));
	         }
	         if ("Women's Home".equals(tfarm)) {
	        	message.addRecipient(Message.RecipientType.TO,
	                     new InternetAddress("intake.womensministry@faithfarm.org"));
		        message.addRecipient(Message.RecipientType.TO,
	                     new InternetAddress("AGorrin@faithfarm.org"));
		        message.addRecipient(Message.RecipientType.TO,
	                     new InternetAddress("VAndres@faithfarm.org"));
	         }
	              
	         // Set Subject: header field
	         message.setSubject("Intake Application transferred to "+tfarm+" from "+ffarm+" for "+intakeForm.getIntake().getFirstname()+" "+intakeForm.getIntake().getLastname());

	         // Now set the actual message
	         message.setText("This is an automated response sent to notify you of an application submitted online. Please log into http://apps.faithfarm.org/intake to view the application.  Do not reply to this message.");

	         // Send message
	         if (!"127.0.0.1".equals(request.getRemoteAddr()))
	        	 Transport.send(message);
	         
	       }catch (MessagingException mex) {
	         mex.printStackTrace();
	         LOGGER.log(Level.SEVERE,"Error occurred sending email for application: "+mex.getMessage());
	      }
		
		
		
	}
	
	private void uploadFile(IntakeForm intakeForm) {
		FormFile file = intakeForm.getImageFile();
		try {
			// InputStream stream = file.getInputStream();
			InputStream stream = new BufferedInputStream(
					file.getInputStream());
			final byte[] bytes = new byte[5224024];
			int read = stream.read(bytes);
			intakeForm.getIntake().setImageHeadshot(bytes);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
	}
	
	
	private void trackCwt(IntakeForm form, HttpSession session) {
	
		GenericDao  dao = new GenericDao();
		CwtProgramDao cwtProgramDao = new CwtProgramDao();
		
		List programs = dao.findIntakePrograms(form.getIntake().getIntakeId());
		List<CwtModules> modules = new ArrayList<CwtModules>();
		List<CwtMaster> masters = new ArrayList<CwtMaster>();
		List<CwtRoster> rosters = dao.findRosterHistoryByIntakeId(form.getIntake().getIntakeId());
			 	
		for (Iterator iterator =
				 programs.iterator(); iterator.hasNext();){
			 
				
			 	BigInteger id = (BigInteger) iterator.next();
			 	CwtProgram program = cwtProgramDao.find(id.longValue());
			 	
			 	modules = dao.findByObjectId(CwtModules.class, "programId", id.longValue());
			 	CwtMaster master=null;
			 	
			 	for (Iterator iterator1 =
						 modules.iterator(); iterator1.hasNext();){
					 	CwtModules module = (CwtModules) iterator1.next();
					 	
					 	master = new CwtMaster();
					 	master.setProgram(program);
			 		    master.setModule(module);
			 		    CwtRoster blank = new CwtRoster();
			 			blank.setAttendDate(""); 
			 			blank.setStatus("Not Completed");
			 			
					 	for (int i=0;i<rosters.size();i++) {
					 		CwtRoster roster = (CwtRoster)rosters.get(i);
					 			if (roster.getModuleId().equals(module.getModuleId())) {
					 				String sdate =roster.getRosterDate();
					 				blank.setAttendDate(sdate);
					 				blank.setStatus(roster.getStatus());
					 				blank.setRosterId(roster.getRosterId());
					 			}
					 			master.setRoster(blank);
						 }
					 	
						masters.add(master);
				 }
			 
	   }
		session.setAttribute("cwtmasters", masters);

		 
	}
	
	private void deleteCwt(Long id) {
		CwtRoster obj = null;
		GenericDao  dao = new GenericDao();
		List<CwtRoster> list = dao.findByObjectId(new CwtRoster().getClass(),"rosterId", id);
		if (list!=null&&list.size()>0) {
			obj = (CwtRoster)list.get(0);
			dao.delete(obj);
		}
		
	}

}
