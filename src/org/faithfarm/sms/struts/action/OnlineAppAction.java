package org.faithfarm.sms.struts.action;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import org.apache.commons.lang3.text.WordUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.faithfarm.sms.application.Constants;
import org.faithfarm.sms.domain.Intake;
import org.faithfarm.sms.domain.IntakeJobSkill;
import org.faithfarm.sms.domain.IntakeMedicalCondition;
import org.faithfarm.sms.domain.IntakeQuestionAnswer;
import org.faithfarm.sms.domain.JobSkill;
import org.faithfarm.sms.domain.MedicalCondition;
import org.faithfarm.sms.hibernate.data.IntakeDao;
import org.faithfarm.sms.hibernate.data.IntakeJobSkillDao;
import org.faithfarm.sms.hibernate.data.IntakeMedicalConditionDao;
import org.faithfarm.sms.hibernate.data.IntakeQuestionAnswerDao;
import org.faithfarm.sms.struts.form.OnlineAppForm;
import org.faithfarm.sms.util.HtmlDropDownBuilder;
import org.faithfarm.sms.util.Validator;
import org.faithfarm.sms.validator.IntakeValidator;

public class OnlineAppAction extends Action {

	private final static Logger LOGGER = Logger.getLogger(OnlineAppAction.class
			.getName());
	private final static HtmlDropDownBuilder html = new HtmlDropDownBuilder();
	private final static Validator validator = new Validator();
	private final static IntakeValidator intakeValidator = new IntakeValidator();

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.setLevel(Level.SEVERE);

		HttpSession session = request.getSession(true);
		
		try {
			
		OnlineAppForm onlineAppForm = (OnlineAppForm) form;
		String action = request.getParameter("action");
		IntakeDao intakeDao = new IntakeDao();
		IntakeMedicalConditionDao intakeMedicalConditionDao = new IntakeMedicalConditionDao();
		IntakeQuestionAnswerDao intakeQuestionAnswerDao = new IntakeQuestionAnswerDao();
		IntakeJobSkillDao intakeJobSkillDao = new IntakeJobSkillDao();

		// clear out messages
		onlineAppForm.setMessages(new ArrayList());
		onlineAppForm.setMessageType("");

		/*
		 * Load drop down values, questions, and medical conditions from
		 * database
		 */
		html.refresh(session);
		html.getOnlineApplicationQuestions(onlineAppForm);

		if ("Next".equals(action)) {
			session.removeAttribute("session_expired");
			//System.out.println ("!!!!---->"+onlineAppForm.getIntake().getFirstname()+" "+onlineAppForm.getIntake().getLastname()+" click Next on "+onlineAppForm.getPageSource()+" @ "+(new java.util.Date()));
			boolean valid=false;
			
			valid = intakeValidator.validate(onlineAppForm);
			
			if (!valid)
				 return mapping.findForward(onlineAppForm.getPageSource());
			
			
			if ("personal".equals(onlineAppForm.getPageSource())) {
				

				// auto calculate Age
					
				if (onlineAppForm.getIntake().getDob().length()==10) 
					onlineAppForm.getIntake().setAge(
							validator.calculateAge(onlineAppForm.getIntake()
									.getDob()) + "");
				
				
				if (!"true".equals((String)session.getAttribute("previous_intake"))) {
					//First check to see if ssn/name has already been to Faith Farm
					String ssn = onlineAppForm.getIntake().getSsn().replace("-", "");
					String dob = onlineAppForm.getIntake().getDob();
					String fname = onlineAppForm.getIntake().getFirstname();
					String lname = onlineAppForm.getIntake().getLastname();
					String appFarm=onlineAppForm.getIntake().getFarmBase();
					
					WordUtils wd = new WordUtils();
					onlineAppForm.getIntake().setFirstname(wd.capitalize(fname));
					onlineAppForm.getIntake().setLastname(wd.capitalize(lname));

					//First check SSN in Format xxxxxxxxx ( Intake 1.0)
					List<Intake> list = intakeDao.search(null, null, null, null, ssn, dob, null, null, null, null,null,null,null,null,null);
					
					//if list=0 check SSN in Format xxx-xx-xxxx ( Intake 2.0)
					if (list.size()==0)
						list = intakeDao.search(null, null, null, null, onlineAppForm.getIntake().getSsn(), dob, null, null, null, null,null,null,null,null,null);
					
					if (list.size()!=0) {
						Intake intake = (Intake)list.get(0);
						
						//check existing status so they can apply to different farms
						if ("Pending".equals(intake.getApplicationStatus()))
							return mapping.findForward(Constants.RELIGIOUS);
						
						intake.setReapplyFlag("Yes");
						intake.setFarmBase(appFarm);
						onlineAppForm.setIntake(intake);
						
						//set supporting values
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

						setIntakeMedicalConditions(intakeMedicalCondition, onlineAppForm);
						setIntakeQuestionAnswer(intakeQuestionAnswer, onlineAppForm);
						setIntakeJobSkills(intakeJobSkill, onlineAppForm);

						// convert phsycial effects to Flags
						this.setPhysicalEffects(onlineAppForm);
						this.setUsagePatterns(onlineAppForm);

						if (intake.getUsageLosses()!=null) {
							if (intake.getUsageLosses().contains("Job"))
								onlineAppForm.setUsageLosses1("Job");
							if (intake.getUsageLosses().contains("Marriage"))
								onlineAppForm.setUsageLosses2("Marriage");
							if (intake.getUsageLosses().contains("Friends"))
								onlineAppForm.setUsageLosses3("Friends");
							if (intake.getUsageLosses().contains("Possessions"))
								onlineAppForm.setUsageLosses4("Possessions");
							if (intake.getUsageLosses().contains("Arrests"))
								onlineAppForm.setUsageLosses5("Arrests");
							if (intake.getUsageLosses().contains("DUIs"))
								onlineAppForm.setUsageLosses6("DUIs");
							if (intake.getUsageLosses().contains("Heavy Debt"))
								onlineAppForm.setUsageLosses7("Heavy Debt");
							if (intake.getUsageLosses().contains("Health"))
								onlineAppForm.setUsageLosses8("Health");
							if (intake.getUsageLosses().contains("Incarceration"))
								onlineAppForm.setUsageLosses9("Incarceration");
						}
			
						
						session.setAttribute("previous_intake", "true");
						return mapping.findForward(Constants.PERSONAL);
					}
				}
			}
			if ("religious".equals(onlineAppForm.getNextStep()))
				return mapping.findForward(Constants.RELIGIOUS);
			else if ("health".equals(onlineAppForm.getNextStep()))
				return mapping.findForward(Constants.HEALTH);
			else if ("substance".equals(onlineAppForm.getNextStep()))
				return mapping.findForward(Constants.SUBSTANCE);
			else if ("legal".equals(onlineAppForm.getNextStep()))
				return mapping.findForward(Constants.LEGAL);
			else if ("employment".equals(onlineAppForm.getNextStep()))
				return mapping.findForward(Constants.EMPLOYMENT);
			else if ("process".equals(onlineAppForm.getNextStep()))
				return mapping.findForward(Constants.PROCESS);

		} else if ("Back".equals(action)) {
			//System.out.println ("---->"+onlineAppForm.getIntake().getFirstname()+" "+onlineAppForm.getIntake().getLastname()+" click Next on "+onlineAppForm.getPageSource()+" @ "+(new java.util.Date()));
			
			if ("personal".equals(onlineAppForm.getPreviousStep()))
				return mapping.findForward(Constants.PERSONAL);
			else if ("religious".equals(onlineAppForm.getPreviousStep()))
				return mapping.findForward(Constants.RELIGIOUS);
			else if ("health".equals(onlineAppForm.getPreviousStep()))
				return mapping.findForward(Constants.HEALTH);
			else if ("substance".equals(onlineAppForm.getPreviousStep()))
				return mapping.findForward(Constants.SUBSTANCE);
			else if ("legal".equals(onlineAppForm.getPreviousStep()))
				return mapping.findForward(Constants.LEGAL);
			else if ("employment".equals(onlineAppForm.getPreviousStep()))
				return mapping.findForward(Constants.EMPLOYMENT);

		} else if ("Submit".equals(action)) {
			//System.out.println ("---->"+onlineAppForm.getIntake().getFirstname()+" "+onlineAppForm.getIntake().getLastname()+" click Next on "+onlineAppForm.getPageSource()+" @ "+(new java.util.Date()));
			
			
			/*
			 * Prevent NULL applications being submitted after user goes away from computer
			 * returns and presses submit
			 */
			if (onlineAppForm.getIntake().getFirstname()==null) {
				session.setAttribute("session_expired", "true");
				return mapping.findForward(Constants.PERSONAL);
			}
			
			
				
			onlineAppForm.setMessageType("");
			String submitDate = Validator.convertEpoch(Validator.getEpoch());
			onlineAppForm.getIntake().setApplicationSubmissionDate(submitDate);
			String fname=onlineAppForm.getIntake().getFirstname();
			String lname=onlineAppForm.getIntake().getLastname();
			
			
			if (true) {
				onlineAppForm.getIntake().setCreationDate(
						validator.getEpoch() + "");
				onlineAppForm.getIntake().setCreatedBy("online application");
				onlineAppForm.getIntake().setApplicationStatus("Pending");
				onlineAppForm.getIntake().setIntakeStatus("Pending");
				onlineAppForm.getIntake().setArchivedFlag("No");
				
				//temp log all data before attempted save
				this.logApplicationDataOnException(onlineAppForm);
				
				saveUsagePatternAndLosses(onlineAppForm);
				
				if (onlineAppForm.getIntake().getIntakeId()==null) {
					Long id=intakeDao.save(onlineAppForm.getIntake());
					onlineAppForm.getIntake().setIntakeId(id);
				}
				else
					intakeDao.update(onlineAppForm.getIntake());
				
				
				//System.out.println ("---->"+onlineAppForm.getIntake().getFirstname()+" "+onlineAppForm.getIntake().getLastname()+" saved application with id "+onlineAppForm.getIntake().getIntakeId()+" @ "+(new java.util.Date()));
				session.removeAttribute("previous_intake");
				
				if (onlineAppForm.getIntake().getIntakeId()!=null) {
					saveMedicalConditions(onlineAppForm);
					saveIntakeQuestionAnswer(onlineAppForm);
					saveJobSkills(onlineAppForm,request);
					
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

				         message.addRecipient(Message.RecipientType.TO,
			                     new InternetAddress("itdepartment@faithfarm.org"));
				        
				         if ("Boynton Beach".equals(onlineAppForm.getIntake().getFarmBase())) {
				        	 message.addRecipient(Message.RecipientType.TO,
				                     new InternetAddress("intake.boyntonbeach@faithfarm.org"));
				        	 message.addRecipient(Message.RecipientType.TO,
				                     new InternetAddress("PZielinski@faithfarm.org"));
					         message.addRecipient(Message.RecipientType.TO,
				                     new InternetAddress("RJurisDick@faithfarm.org"));
					     }
				         if ("Fort Lauderdale".equals(onlineAppForm.getIntake().getFarmBase())) {
				        	 message.addRecipient(Message.RecipientType.TO,
				                     new InternetAddress("intake.fortlauderdale@faithfarm.org"));
				        	 message.addRecipient(Message.RecipientType.TO,
				                     new InternetAddress("sjohnson@faithfarm.org"));
					         message.addRecipient(Message.RecipientType.TO,
				                     new InternetAddress("iftl@faithfarm.org"));
					     }
				         if ("Okeechobee".equals(onlineAppForm.getIntake().getFarmBase())) {
				        	 message.addRecipient(Message.RecipientType.TO,
				                     new InternetAddress("intake.okeechobee@faithfarm.org"));
				        	 //message.addRecipient(Message.RecipientType.TO,
				             //        new InternetAddress("MMurphy@faithfarm.org"));
				         }
				         if ("Women's Home".equals(onlineAppForm.getIntake().getFarmBase())) {
				        	message.addRecipient(Message.RecipientType.TO,
				                     new InternetAddress("intake.womensministry@faithfarm.org"));
					        message.addRecipient(Message.RecipientType.TO,
				                     new InternetAddress("AGorrin@faithfarm.org"));
					        message.addRecipient(Message.RecipientType.TO,
				                     new InternetAddress("VAndres@faithfarm.org"));
				         }
				             
				         // Set Subject: header field
				         if ("Yes".equals(onlineAppForm.getIntake().getReapplyFlag()))
			        		 message.setSubject("Returning Student or Applicant has submitted an Application for "+onlineAppForm.getIntake().getFirstname()+" "+onlineAppForm.getIntake().getLastname()+" at "+onlineAppForm.getIntake().getFarmBase());
				         else		 
				        	 message.setSubject("Intake Application Received for "+onlineAppForm.getIntake().getFirstname()+" "+onlineAppForm.getIntake().getLastname()+" at "+onlineAppForm.getIntake().getFarmBase());

				         // Now set the actual message
				         message.setText("This is an automated response sent to notify you of an application submitted online. Please log into http://apps.faithfarm.org/intake to view the application.  Do not reply to this message.");
				        // Transport.send(message);
				         // Send message
				         try {
				     		String ipAddy=InetAddress.getLocalHost().getHostAddress();
				     		if ("50.63.180.165".equals(ipAddy)) {
				     				Transport.send(message);
				     				////System.out.println ("----> email sent for "+onlineAppForm.getIntake().getFirstname()+" "+onlineAppForm.getIntake().getLastname()+" @ "+(new java.util.Date()));
				     			}
				     		} catch (Exception e) {
				     			LOGGER.log(Level.SEVERE,"Error occurred in getting IP when trying to send email: "+e.getMessage());
				     		}
				         
				        }catch (MessagingException mex) {
				         mex.printStackTrace();
				         LOGGER.log(Level.SEVERE,"Error occurred sending email for application: "+mex.getMessage());
				      }
					onlineAppForm.setIntake(new Intake());
					session.invalidate(); 
					return mapping.findForward(Constants.WEBSITE);
				}
				else {
					LOGGER.log(Level.INFO,"Error occurred saving online application:  Data displayed below");
					this.logApplicationDataOnException(onlineAppForm);
					session.setAttribute("SYSTEM_ERROR", "A database error occurred saving the application!");
					return mapping.findForward(Constants.ERROR);
				}
			}

		}
		return mapping.findForward(Constants.PERSONAL);
		}
		catch (Exception e) {
				LOGGER.log(Level.INFO,"Error occurred in online application:  Data displayed below");
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);			
				session.setAttribute("SYSTEM_ERROR", sw.toString());
				e.printStackTrace();
				/*
				OnlineAppForm onlineAppForm = (OnlineAppForm) form;
				Intake intake =  onlineAppForm.getIntake();
				try {
				BeanInfo info = Introspector.getBeanInfo(intake.getClass());
				PropertyDescriptor[] props = info.getPropertyDescriptors();  
				for (int i=0;i<props.length;i++) {  
					PropertyDescriptor descriptor = props [i];
					LOGGER.log(Level.SEVERE,props[i].getDisplayName()+"="+descriptor.getReadMethod().invoke(intake, null));
					}	
			    
				}
				catch (Exception ex) { System.out.println(""); }
				*/
				return mapping.findForward(Constants.ERROR);
			}
				
	}

	private void saveIntakeQuestionAnswer(OnlineAppForm intakeForm) {
		try{
		IntakeQuestionAnswerDao dao = new IntakeQuestionAnswerDao();
		
		 /*
		  * First delete all medical conditions for given intake
		  */
		 List<IntakeQuestionAnswer> intakeQuestionAnswers = dao.findByIntakeId(new IntakeQuestionAnswer().getClass(), intakeForm.getIntake().getIntakeId());
		 for (Iterator iterator =
				 intakeQuestionAnswers.iterator(); iterator.hasNext();){
			 IntakeQuestionAnswer obj = (IntakeQuestionAnswer) iterator.next();
			 dao.delete(obj);
		 }
		 
		 String healthAnswer[] = intakeForm.getHealthAnswer();
		 String emotionalAnswer[] = intakeForm.getEmotionalAnswer();
		 String emotionalAnswerDate[] = intakeForm.getEmotionalAnswerDate();
		 String emotionalAnswerDetails[] = intakeForm.getEmotionalAnswerDetails();
		 String physicalAnswer[] = intakeForm.getPhysicalAnswer();
		 String physicalAnswerDetails[] = intakeForm.getPhysicalAnswerDetails();
		 String mentalAnswer[] = intakeForm.getMentalAnswer();
		 String mentalAnswerDate[] = intakeForm.getMentalAnswerDate();
		 String mentalAnswerDetails[] = intakeForm.getMentalAnswerDetails();
		 
		for (int index=0;index<15;index++) {
			   if ("Yes".equals(healthAnswer[index])) {
	    		   IntakeQuestionAnswer iqa = new IntakeQuestionAnswer();
	    		   iqa.setIntakeId(intakeForm.getIntake().getIntakeId());
	    		   iqa.setAnswer("Yes");	 
	    		   iqa.setQuestionId(new Long(index+1));
	    		   dao.save(iqa);
	    		 }
	    	   
	    	   if ("Yes".equals(emotionalAnswer[index])) {
	    		   IntakeQuestionAnswer iqa = new IntakeQuestionAnswer();
	    		   iqa.setIntakeId(intakeForm.getIntake().getIntakeId());
	    		   iqa.setAnswer("Yes");	
	    		   iqa.setDates(emotionalAnswerDate[index]);
	    		   iqa.setDetail(emotionalAnswerDetails[index]);
	    		   iqa.setQuestionId(new Long(index+16));
	    		   dao.save(iqa);
	    		 }
	    	   
	    	   if ("Yes".equals(physicalAnswer[index])) {
	    		   IntakeQuestionAnswer iqa = new IntakeQuestionAnswer();
	    		   iqa.setIntakeId(intakeForm.getIntake().getIntakeId());
	    		   iqa.setAnswer("Yes");	
	    		   iqa.setDetail(physicalAnswerDetails[index]);
	    		   iqa.setQuestionId(new Long(index+22));
	    		   dao.save(iqa);
	    		 }
	    	   
	    	   if ("Yes".equals(mentalAnswer[index])) {
	    		   IntakeQuestionAnswer iqa = new IntakeQuestionAnswer();
	    		   iqa.setIntakeId(intakeForm.getIntake().getIntakeId());
	    		   iqa.setAnswer("Yes");	
	    		   iqa.setDates(mentalAnswerDate[index]);
	    		   iqa.setDetail(mentalAnswerDetails[index]);
	    		   iqa.setQuestionId(new Long(index+27));
	    		   dao.save(iqa);
	    		 }
	    }
		} catch (Exception e) {
			 LOGGER.log(Level.SEVERE,"Error in saveIntakeQuestionAnswer:"+intakeForm.getIntake().getIntakeId()+":"+e.getMessage());
		 }

	}
	
	private void saveMedicalConditions(OnlineAppForm intakeForm) {
		 
		try {
			
		 IntakeMedicalConditionDao dao = new IntakeMedicalConditionDao();

		 
		 /*
		  * First delete all medical conditions for given intake
		  */
		 List<IntakeMedicalCondition> intakeMedicalConditions = dao.findByIntakeId(new IntakeMedicalCondition().getClass(), intakeForm.getIntake().getIntakeId());
		 for (Iterator iterator =
				 intakeMedicalConditions.iterator(); iterator.hasNext();){
			 IntakeMedicalCondition obj = (IntakeMedicalCondition) iterator.next();
			 dao.delete(obj);
		 }
		 
		 /*
		  * write to db medical conditions for given intake marked yes
		  */
		 List<MedicalCondition> medicalConditions = intakeForm.getMedicalConditions();
		 String medicalCondition[] = intakeForm.getMedicalCondition();
	     int index=0;  
		 for (Iterator iterator =
	    		   medicalConditions.iterator(); iterator.hasNext();){
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
		 catch (Exception e) {
			 LOGGER.log(Level.SEVERE,"Error in saveMedicalConditions:"+intakeForm.getIntake().getIntakeId()+":"+e.getMessage());
		 }
	       
                
	}
	private void saveJobSkills(OnlineAppForm intakeForm, HttpServletRequest request) {

		try {
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
			//String workExperienceFlag=request.getParameter("workExperience["+index+"]");
			if ("Yes".equals(workexperience[index])) {
				IntakeJobSkill ijs = new IntakeJobSkill();
				ijs.setIntakeId(intakeForm.getIntake().getIntakeId());
				ijs.setJobSkillId(obj.getJobSkillId());
				dao.save(ijs);//.addIntakeJobSkill(ijs);
			}
			index++;
		}
		}catch (Exception e) {
			 LOGGER.log(Level.SEVERE,"Error in saveJobSkills:"+intakeForm.getIntake().getIntakeId()+":"+e.getMessage());
		 }
	
	}

	
	private void saveUsagePatternAndLosses(OnlineAppForm intakeForm) {
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

	private void convertPhysicalEffects(OnlineAppForm intakeForm) {
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
	
	private void logApplicationDataOnException (ActionForm form) {
			LOGGER.log(Level.SEVERE,"Online application submitted:  Data displayed below");
			OnlineAppForm onlineAppForm = (OnlineAppForm) form;
			Intake intake =  onlineAppForm.getIntake();
			try {
			BeanInfo info = Introspector.getBeanInfo(intake.getClass());
			PropertyDescriptor[] props = info.getPropertyDescriptors();  
			for (int i=0;i<props.length;i++) {  
				PropertyDescriptor descriptor = props [i];
				////System.out.println(props[i].getDisplayName()+"="+descriptor.getReadMethod().invoke(intake, null));
				}			    
			}
			catch (Exception ex) { LOGGER.log(Level.INFO,"Error in logApplicationDataOnException:"+ex.getMessage()); }
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
	}
	
	private void setIntakeMedicalConditions(
			List<IntakeMedicalCondition> intakeMedicalCondition,
			OnlineAppForm intakeForm) {

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
			OnlineAppForm intakeForm) {

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
			OnlineAppForm intakeForm) {

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
	
	private void setPhysicalEffects(OnlineAppForm intakeForm) {
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

	private void setUsagePatterns(OnlineAppForm intakeForm) {
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

	public int calculateAge(String dateOfBirth) {
		int age=0;
		
		try {
		Calendar dob = Calendar.getInstance();  
		dob.setTime(new Date(dateOfBirth));
		Calendar today = Calendar.getInstance();  
		age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);  
		if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
		  age--;  
		} else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
		    && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
		  age--;  
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	return age;
	}
	
	


}