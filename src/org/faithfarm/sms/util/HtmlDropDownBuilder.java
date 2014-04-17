package org.faithfarm.sms.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.faithfarm.sms.domain.CwtDepartment;
import org.faithfarm.sms.domain.CwtJob;
import org.faithfarm.sms.domain.CwtModuleSection;
import org.faithfarm.sms.domain.CwtModules;
import org.faithfarm.sms.domain.CwtProgram;
import org.faithfarm.sms.domain.CwtSupervisor;
import org.faithfarm.sms.domain.DropDownItem;
import org.faithfarm.sms.domain.Farm;
import org.faithfarm.sms.domain.JobSkill;
import org.faithfarm.sms.domain.MedicalCondition;
import org.faithfarm.sms.domain.Question;
import org.faithfarm.sms.domain.State;
import org.faithfarm.sms.hibernate.data.CwtDepartmentDao;
import org.faithfarm.sms.hibernate.data.CwtJobDao;
import org.faithfarm.sms.hibernate.data.CwtModuleSectionDao;
import org.faithfarm.sms.hibernate.data.CwtModulesDao;
import org.faithfarm.sms.hibernate.data.CwtProgramDao;
import org.faithfarm.sms.hibernate.data.CwtSupervisorDao;
import org.faithfarm.sms.hibernate.data.JobSkillDao;
import org.faithfarm.sms.hibernate.data.MedicalConditionDao;
import org.faithfarm.sms.hibernate.data.QuestionDao;
import org.faithfarm.sms.hibernate.data.StateDao;
import org.faithfarm.sms.struts.form.IntakeForm;
import org.faithfarm.sms.struts.form.OnlineAppForm;

public class HtmlDropDownBuilder {

	public void getQuestions(ActionForm form) {
	      QuestionDao dao4 = new QuestionDao();
	        List<Question> questions = new ArrayList<Question>();
	        List<Question> healthQuestions = new ArrayList<Question>();
	        List<Question> emotionalQuestions = new ArrayList<Question>();
	        List<Question> physicalQuestions = new ArrayList<Question>();
	        List<Question> mentalQuestions = new ArrayList<Question>();
	        
	        IntakeForm intakeForm = (IntakeForm)form;
	        
	        int count=0;
	        questions=dao4.list();
	        for (Iterator iterator =
	    			questions.iterator(); iterator.hasNext();){
	    			Question obj = (Question) iterator.next();
	                
	    			if (count<15)
	    				healthQuestions.add(obj);
	    			else if (count>14&&count<21)
	    				emotionalQuestions.add(obj);
	    			else if (count>20&&count<26)
	    				physicalQuestions.add(obj);
	    			else if (count>25&&count<32)
	    				mentalQuestions.add(obj);
	    			
	        count++;
	        }
	       intakeForm.setHealthQuestions(healthQuestions);
	       intakeForm.setEmotionalQuestions(emotionalQuestions);
	       intakeForm.setPhysicalQuestions(physicalQuestions);
	       intakeForm.setMentalQuestions(mentalQuestions);	       
	       
	       MedicalConditionDao dao5 = new MedicalConditionDao();
	       List<MedicalCondition> medicalConditions = new ArrayList<MedicalCondition>();
	       medicalConditions = dao5.list();
	       for (Iterator iterator =
	    		   medicalConditions.iterator(); iterator.hasNext();){
	    	   MedicalCondition obj = (MedicalCondition) iterator.next();	    			
	       }
	       intakeForm.setMedicalConditions(medicalConditions);
	       
	       JobSkillDao dao6 = new JobSkillDao();
	       List<JobSkill> jobSkills = new ArrayList<JobSkill>();
	       jobSkills = dao6.list();
	       for (Iterator iterator =
	    		   jobSkills.iterator(); iterator.hasNext();){
	    	   JobSkill obj = (JobSkill) iterator.next();	    			
	       }
	       intakeForm.setJobSkills(jobSkills);
	       
	       
	 
	       
	}
	public void refresh(HttpSession session) {
	   	List<Farm> list = new ArrayList<Farm>();
    	list.add(new Farm(new Long(1),"Boynton Beach"));
    	list.add(new Farm(new Long(2),"Fort Lauderdale"));
    	list.add(new Farm(new Long(3),"Okeechobee"));
    	list.add(new Farm(new Long(4),"Women's Home"));
    	session.setAttribute("ddl_farm", list);
    	
    	
        StateDao dao3 = new StateDao();
        List<State> list3 = new ArrayList<State>();
        list3=dao3.list();
        session.setAttribute("ddl_state", list3);

        CwtSupervisorDao dao4 = new CwtSupervisorDao();
        List<CwtSupervisor> list4 = new ArrayList<CwtSupervisor>();
        list4=dao4.list();
        List<DropDownItem> temp = new ArrayList<DropDownItem>();
        for (Iterator iterator =
        		list4.iterator(); iterator.hasNext();){
	    	   CwtSupervisor obj = (CwtSupervisor) iterator.next();
	    	   DropDownItem item = new DropDownItem(obj.getSupervisorId()+"",obj.getFirstname()+" "+obj.getLastname());
	    	   temp.add(item);
	       }
        session.setAttribute("ddl_supervisor", temp);

        CwtModulesDao moduleDao = new CwtModulesDao();
        List<CwtModules> modules = new ArrayList<CwtModules>();
        modules=moduleDao.list();
        List<DropDownItem> tempModule = new ArrayList<DropDownItem>();
        for (Iterator iterator =
        		modules.iterator(); iterator.hasNext();){
	    	   CwtModules obj = (CwtModules) iterator.next();
	    	   DropDownItem item = new DropDownItem("","");
	    			  if (obj.getModuleSeq()==null)
	    				  item = new DropDownItem(obj.getModuleId()+"",obj.getModuleName());
	    			  else
	    				  item = new DropDownItem(obj.getModuleId()+"",obj.getModuleName()+" (Module "+obj.getModuleSeq()+")");
	    			    
	    	   tempModule.add(item);
	       }
        session.setAttribute("ddl_module", tempModule);

        CwtProgramDao programDao = new CwtProgramDao();
        List<CwtProgram> programs = new ArrayList<CwtProgram>();
        programs=programDao.list();
        List<DropDownItem> tempProgram = new ArrayList<DropDownItem>();
        for (Iterator iterator =
        		programs.iterator(); iterator.hasNext();){
	    	   CwtProgram obj = (CwtProgram) iterator.next();
	    	   DropDownItem item = new DropDownItem(obj.getProgramId()+"",obj.getProgramName());
	    	   tempProgram.add(item);
	       }
        session.setAttribute("ddl_program", tempProgram);

        
        CwtJobDao dao5 = new CwtJobDao();
        List<CwtJob> list5 = new ArrayList<CwtJob>();
        list5=dao5.list();
        session.setAttribute("ddl_job", list5);
        List<DropDownItem> temp1 = new ArrayList<DropDownItem>();
        for (Iterator iterator =
        		list5.iterator(); iterator.hasNext();){
	    	   CwtJob obj = (CwtJob) iterator.next();
	    	   DropDownItem item = new DropDownItem(obj.getJobId()+"",obj.getTitle());
	    	   temp1.add(item);
	       }
        session.setAttribute("ddl_jobList", temp1);
        
        CwtDepartmentDao dao6 = new CwtDepartmentDao();
        List<CwtDepartment> list6 = new ArrayList<CwtDepartment>();
        list6=dao6.list();
        session.setAttribute("ddl_department", list6);

		   List<DropDownItem> rosterStatus = new ArrayList<DropDownItem>();
		   rosterStatus.add(new DropDownItem("Enrolled","Enrolled"));
		   rosterStatus.add(new DropDownItem("Completed","Completed"));
		   rosterStatus.add(new DropDownItem("Failed","Failed"));
		   rosterStatus.add(new DropDownItem("Did Not Attend","Did Not Attend"));
		   session.setAttribute("ddl_rosterStatus", rosterStatus);

		   List<DropDownItem> moduleSeq = new ArrayList<DropDownItem>();
		   for (int i=1;i<20;i++)
			   moduleSeq.add(new DropDownItem(""+i,i+""));
		   session.setAttribute("ddl_moduleSeq", moduleSeq);

		   List<DropDownItem> stateIdType = new ArrayList<DropDownItem>();
		   stateIdType.add(new DropDownItem("State Issued Id","State Issued Id"));
		   stateIdType.add(new DropDownItem("State Government Issued Certificate of Birth","State Government Issued Certificate of Birth"));
		   stateIdType.add(new DropDownItem("US Active Duty/Retiree/Reservist Military ID Card (000 10-2)","US Active Duty/Retiree/Reservist Military ID Card (000 10-2)"));
		   stateIdType.add(new DropDownItem("Passport from any country","Passport from any country"));
		   stateIdType.add(new DropDownItem("Social Security Card","Social Security Card"));
		   stateIdType.add(new DropDownItem("Certificate of Citizenship (N560)","Certificate of Citizenship (N560)"));
		   stateIdType.add(new DropDownItem("Certificate of Naturalization (N550)","Certificate of Naturalization (N550)"));
		   stateIdType.add(new DropDownItem("Certificate of Naturalization (N550)","Certificate of Naturalization (N550)"));
		   stateIdType.add(new DropDownItem("INS I-551 Resident Alien Card Issued since 1997","INS I-551 Resident Alien Card Issued since 1997"));
		   stateIdType.add(new DropDownItem("INS 1-688 Temporary Resident Identification Card","INS 1-688 Temporary Resident Identification Card"));
		   stateIdType.add(new DropDownItem("INS I-688B, I-766 Employment Authorization Card","INS I-688B, I-766 Employment Authorization Card"));
		   session.setAttribute("ddl_stateIdType", stateIdType);
		   
		   List<DropDownItem> spiritual = new ArrayList<DropDownItem>();
		   spiritual.add(new DropDownItem("I've Never Known God","I've Never Known God"));
		   spiritual.add(new DropDownItem("I'm Open to Knowing God","I'm Open to Knowing God"));
		   spiritual.add(new DropDownItem("I've Known Him, but Backslid","I've Known Him, but Backslid"));
		   spiritual.add(new DropDownItem("I'm in Good Relationship with God","I'm in Good Relationship with God"));
		   session.setAttribute("ddl_spiritual", spiritual);

        
		   List<DropDownItem> roles = new ArrayList<DropDownItem>();
		   roles.add(new DropDownItem("Administrator","Administrator"));
		   roles.add(new DropDownItem("Intake","Intake"));
		   roles.add(new DropDownItem("Dispatch Administrator","Dispatch Administrator"));
		   roles.add(new DropDownItem("Dispatch Agent","Dispatch Agent"));
		   roles.add(new DropDownItem("Instructor","Instructor"));
		   session.setAttribute("ddl_role", roles);

        
		   List<DropDownItem> cwtStatus = new ArrayList<DropDownItem>();
		   cwtStatus.add(new DropDownItem("Active","Active"));
		   cwtStatus.add(new DropDownItem("Inactive","Inactive"));
		   session.setAttribute("ddl_cwtStatus", cwtStatus);

		   List<DropDownItem> relationship = new ArrayList<DropDownItem>();
		   relationship.add(new DropDownItem("Excellent","Excellent"));
		   relationship.add(new DropDownItem("Good","Good"));
		   relationship.add(new DropDownItem("Fair","Fair"));
		   relationship.add(new DropDownItem("Poor","Poor"));
		   relationship.add(new DropDownItem("None","None"));
		   session.setAttribute("ddl_relationship", relationship);

		  
		   List<DropDownItem> suffix = new ArrayList<DropDownItem>();
		   suffix.add(new DropDownItem("Jr.","Jr."));
		   suffix.add(new DropDownItem("Sr.","Sr"));
		   suffix.add(new DropDownItem("I","I"));
		   suffix.add(new DropDownItem("II","II"));
		   suffix.add(new DropDownItem("III","III"));
		   session.setAttribute("ddl_suffix", suffix);
		   
		   List<DropDownItem> programStatus = new ArrayList<DropDownItem>();
	        programStatus.add(new DropDownItem("In Program","In Program"));
	        programStatus.add(new DropDownItem("Walked Off","Walked Off"));
	        programStatus.add(new DropDownItem("Dismissed","Dismissed"));
	        programStatus.add(new DropDownItem("Left Prop./Did Not Graduate","Left Prop./Did Not Graduate"));
	        programStatus.add(new DropDownItem("Left Prop./Graduated","Left Prop./Graduated"));
	        programStatus.add(new DropDownItem("Left Prop./Graduated to Fresh Start","Left Prop./Graduated to Fresh Start"));
	        programStatus.add(new DropDownItem("Left Prop./Graduated to SLS","Left Prop./Graduated to SLS"));
	        programStatus.add(new DropDownItem("Left Prop./Graduated to Omega Work","Left Prop./Graduated to Omega Work"));
	        programStatus.add(new DropDownItem("Left Prop./Graduated to Omega School","Left Prop./Graduated to Omega School"));
	        programStatus.add(new DropDownItem("Left Prop./Graduated to Intern","Left Prop./Graduated to Intern"));
	        programStatus.add(new DropDownItem("Dismissed - Banned 30 days","Dismissed - Banned 30 days"));
	        programStatus.add(new DropDownItem("Dismissed - Banned 60 days","Dismissed - Banned 60 days"));
	        programStatus.add(new DropDownItem("Dismissed - Banned 90 days","Dismissed - Banned 90 days"));
	        programStatus.add(new DropDownItem("Dismissed - Banned Perm.","Dismissed - Banned Perm."));
	        session.setAttribute("ddl_programStatus", programStatus);

	        List<DropDownItem> applicationStatus = new ArrayList<DropDownItem>();
	        applicationStatus.add(new DropDownItem("Pending","Pending"));
	        applicationStatus.add(new DropDownItem("Accepted","Accepted"));
	        applicationStatus.add(new DropDownItem("Denied","Denied"));
	        applicationStatus.add(new DropDownItem("Withdrawn","Withdrawn"));
	        applicationStatus.add(new DropDownItem("Waitlist","Waitlist"));
	        session.setAttribute("ddl_applicationStatus", applicationStatus);

	        List<DropDownItem> reponseStatus = new ArrayList<DropDownItem>();
	        reponseStatus.add(new DropDownItem("Scheduled to arrive","Scheduled to arrive"));
	        reponseStatus.add(new DropDownItem("Phone interview scheduled","Phone interview scheduled"));
	        reponseStatus.add(new DropDownItem("Phone interview completed","Phone interview completed"));
	        reponseStatus.add(new DropDownItem("Called, Awaiting Response","Called, Awaiting Response"));
	        reponseStatus.add(new DropDownItem("Left Message","Left Message"));
	        reponseStatus.add(new DropDownItem("No Answer","No Answer"));
	        session.setAttribute("ddl_responseStatus", reponseStatus);

	        List<DropDownItem> phase = new ArrayList<DropDownItem>();
	        phase.add(new DropDownItem("Phase One","Phase One"));
	        phase.add(new DropDownItem("Intern","Intern"));
	        phase.add(new DropDownItem("Graduate","Graduate"));
	        phase.add(new DropDownItem("Omega Work","Omega Work"));
	        phase.add(new DropDownItem("Omega School","Omega School"));
	        phase.add(new DropDownItem("SLS","SLS"));
	        session.setAttribute("ddl_phase", phase);
	        
	        List<DropDownItem> passType = new ArrayList<DropDownItem>();
	        passType.add(new DropDownItem("Medical","Medical"));
	        passType.add(new DropDownItem("Overnight","Overnight"));
	        passType.add(new DropDownItem("Probation/Court","Probation/Court"));
	        passType.add(new DropDownItem("7.5 Hours","7.5 Hours"));
	        passType.add(new DropDownItem("12 Hour","12 Hour"));
	        passType.add(new DropDownItem("3 Days","3 Days"));
	        passType.add(new DropDownItem("Special","Special"));
	        passType.add(new DropDownItem("Sick/Lay-down","Sick/Lay-down"));
	        session.setAttribute("ddl_passType", passType);
	
	        List<DropDownItem> passHours = new ArrayList<DropDownItem>();
	        passHours.add(new DropDownItem("4","4"));
	        passHours.add(new DropDownItem("7.5","7.5"));
	        passHours.add(new DropDownItem("ASAP","ASAP"));
	       session.setAttribute("ddl_passHours", passHours);
	
	        List<DropDownItem> yesNo = new ArrayList<DropDownItem>();
	        yesNo.add(new DropDownItem("No","No"));
	        yesNo.add(new DropDownItem("Yes","Yes"));
	        session.setAttribute("ddl_yesNo", yesNo);
	        
	        List<DropDownItem> classList = new ArrayList<DropDownItem>();
	        classList.add(new DropDownItem("Orientation","Orientation"));
	        classList.add(new DropDownItem("1","1"));
	        classList.add(new DropDownItem("2","2"));
	        classList.add(new DropDownItem("3","3"));
	        classList.add(new DropDownItem("4","4"));
	        classList.add(new DropDownItem("5","5"));
	        classList.add(new DropDownItem("6","6"));
	        classList.add(new DropDownItem("Graduated","Graduated"));
	        classList.add(new DropDownItem("Fresh Start","Fresh Start"));
	        classList.add(new DropDownItem("SLS","SLS"));
	        classList.add(new DropDownItem("Intern","Intern"));
	        classList.add(new DropDownItem("Omega","Omega"));
	        classList.add(new DropDownItem("Student Teacher","Student Teacher"));
	        session.setAttribute("ddl_classList", classList);
	        
	        List<DropDownItem> military = new ArrayList<DropDownItem>();
	        military.add(new DropDownItem("Air Force","Air Force"));
	        military.add(new DropDownItem("Army","Army"));
	        military.add(new DropDownItem("Army National Guard","Army National Guard"));
	        military.add(new DropDownItem("Coast Guard","Coast Guard"));
	        military.add(new DropDownItem("Marines","Marines"));
	        military.add(new DropDownItem("Navy","Navy"));
	        session.setAttribute("ddl_military", military);
	        
	        List<DropDownItem> eyes = new ArrayList<DropDownItem>();
	        eyes.add(new DropDownItem("All the time","All the time"));
	        eyes.add(new DropDownItem("Occasionally","Occasionally"));
	        eyes.add(new DropDownItem("Reading","Reading"));
	        session.setAttribute("ddl_eyewear", eyes);
	        
	        List<DropDownItem> health = new ArrayList<DropDownItem>();
	        health.add(new DropDownItem("excellent","excellent"));
	        health.add(new DropDownItem("good","good"));
	        health.add(new DropDownItem("fair","fair"));
	        health.add(new DropDownItem("failing","failing"));
	        health.add(new DropDownItem("poor","poor"));
	        session.setAttribute("ddl_health", health);
	        
	        List<DropDownItem> claimStatus = new ArrayList<DropDownItem>();
	        claimStatus.add(new DropDownItem("open","open"));
	        claimStatus.add(new DropDownItem("closed","closed"));
	        session.setAttribute("ddl_claim_status", claimStatus);
	        
	        List<DropDownItem> religion = new ArrayList<DropDownItem>();
	        religion.add(new DropDownItem("Christian","Christian"));
	        religion.add(new DropDownItem("Baptist","Baptist"));
	        religion.add(new DropDownItem("Buddhist","Buddhist"));
	        religion.add(new DropDownItem("Catholic","Catholic"));
	        religion.add(new DropDownItem("Hindu","Hindu"));
	        religion.add(new DropDownItem("Jewish","Jewish"));
	        religion.add(new DropDownItem("New Age","New Age"));
	        religion.add(new DropDownItem("Protestant","Protestant"));
	        religion.add(new DropDownItem("Rastafarian","Rastafarian"));
	        religion.add(new DropDownItem("Santaria","Santaria"));
	        religion.add(new DropDownItem("Other","Other"));
	        session.setAttribute("ddl_religion", religion);
	        
	        List<DropDownItem> ethnicity = new ArrayList<DropDownItem>();
	        ethnicity.add(new DropDownItem("American Indian/Alaskan Native","American Indian/Alaskan Native"));
	        ethnicity.add(new DropDownItem("Asian/Pacific Islander","Asian/Pacific Islander"));
	        ethnicity.add(new DropDownItem("Black, not Hispanic","Black, not Hispanic"));
	        ethnicity.add(new DropDownItem("Hispanic","Hispanic"));
	        ethnicity.add(new DropDownItem("White, not Hispanic","White, not Hispanic"));
	        session.setAttribute("ddl_ethnicity", ethnicity);
	        
	        List<DropDownItem> maritalStatus = new ArrayList<DropDownItem>();
	        maritalStatus.add(new DropDownItem("Never Married","Never Married"));
	        maritalStatus.add(new DropDownItem("Married","Married"));
	        maritalStatus.add(new DropDownItem("Divorced","Divorced"));
	        maritalStatus.add(new DropDownItem("Separated","Separated"));
	        maritalStatus.add(new DropDownItem("Spouse Deceased","Spouse Deceased"));
	        maritalStatus.add(new DropDownItem("Lives with Girlfriend","Lives with Girlfriend"));
	        maritalStatus.add(new DropDownItem("Lives with Boyfriend","Lives with Boyfriend"));
	        maritalStatus.add(new DropDownItem("Remarried","Remarried"));
	        maritalStatus.add(new DropDownItem("Re-divorced","Re-divorced"));
	        session.setAttribute("ddl_maritalstatus", maritalStatus);
	        
	        List<DropDownItem> living = new ArrayList<DropDownItem>();
	        living.add(new DropDownItem("Own Home","Own Home"));
	        living.add(new DropDownItem("Parent's Home","Parent's Home"));
	        living.add(new DropDownItem("Friend's Home","Friend's Home"));
	        living.add(new DropDownItem("Halfway Home","Halfway Home"));
	        living.add(new DropDownItem("Hotel","Hotel"));
	        living.add(new DropDownItem("Rehab Program","Rehab Program"));
	        living.add(new DropDownItem("Vehicle","Vehicle"));
	        living.add(new DropDownItem("On Street","On Street"));
	        living.add(new DropDownItem("Other","Other"));
	        session.setAttribute("ddl_living", living);
	        
	        List<DropDownItem> education = new ArrayList<DropDownItem>();
	        education.add(new DropDownItem("1st Grade","1st Grade"));
	        education.add(new DropDownItem("2nd Grade","2nd Grade"));
	        education.add(new DropDownItem("3rd Grade","3rd Grade"));
	        education.add(new DropDownItem("4th Grade","4th Grade"));
	        education.add(new DropDownItem("5th Grade","5th Grade"));
	        education.add(new DropDownItem("6th Grade","6th Grade"));
	        education.add(new DropDownItem("7th Grade","7th Grade"));
	        education.add(new DropDownItem("8th Grade","8th Grade"));
	        education.add(new DropDownItem("9th Grade","9th Grade"));
	        education.add(new DropDownItem("10th Grade","10th Grade"));
	        education.add(new DropDownItem("11th Grade","11th Grade"));
	        education.add(new DropDownItem("12th Grade","12th Grade"));
	        education.add(new DropDownItem("GED","GED"));
	        education.add(new DropDownItem("Vocational School","Vocational School"));
	        education.add(new DropDownItem("Associates Degree","Associates Degree"));
	        education.add(new DropDownItem("Bachelor's Degree","Bachelor's Degree"));
	        education.add(new DropDownItem("Master's Degree","Master's Degree"));
	        education.add(new DropDownItem("Doctorate Degree","Doctorate Degree"));
	        session.setAttribute("ddl_education", education);
	        
	        List<DropDownItem> hairColor = new ArrayList<DropDownItem>();
	        hairColor.add(new DropDownItem("Brown","Brown"));
	        hairColor.add(new DropDownItem("Black","Black"));
	        hairColor.add(new DropDownItem("Blonde","Blonde"));
	        hairColor.add(new DropDownItem("Blonde/Strawberry","Blonde/Strawberry"));
	        hairColor.add(new DropDownItem("Gray or Partially Gray","Gray or Partially Gray"));
	        hairColor.add(new DropDownItem("Partly or Completely Bald","Partly or Completely Bald"));
	        hairColor.add(new DropDownItem("Red/Auburn","Red/Auburn"));
	        hairColor.add(new DropDownItem("Sandy","Sandy"));
	        hairColor.add(new DropDownItem("Salt & Pepper","Salt & Pepper"));
	        hairColor.add(new DropDownItem("Shaved","Shaved"));
	        hairColor.add(new DropDownItem("White","White"));
	        session.setAttribute("ddl_haircolor", hairColor);
	        
	        List<DropDownItem> eyecolor = new ArrayList<DropDownItem>();
	        eyecolor.add(new DropDownItem("Blue","Blue"));
	        eyecolor.add(new DropDownItem("Brown","Brown"));
	        eyecolor.add(new DropDownItem("Green","Green"));
	        eyecolor.add(new DropDownItem("Hazel","Hazel"));
	        eyecolor.add(new DropDownItem("Other","Other"));
	        session.setAttribute("ddl_eyecolor", eyecolor);
	        
	        List<DropDownItem> herniaSide = new ArrayList<DropDownItem>();
	        herniaSide.add(new DropDownItem("RIGHT","RIGHT"));
	        herniaSide.add(new DropDownItem("LEFT","LEFT"));
	        session.setAttribute("ddl_herniaside", herniaSide);
	        
			   List<DropDownItem> violationArea = new ArrayList<DropDownItem>();
			   violationArea.add(new DropDownItem("Dorm","Dorm"));
			   violationArea.add(new DropDownItem("Breezeway","Breezeway"));
			   violationArea.add(new DropDownItem("Kitchen","Kitchen"));
			   violationArea.add(new DropDownItem("Laundry","Laundry"));
			   violationArea.add(new DropDownItem("TV Room","TV Room"));
			   violationArea.add(new DropDownItem("Canteen","Canteen"));
			   violationArea.add(new DropDownItem("Smoke Pit","Smoke Pit"));
			   violationArea.add(new DropDownItem("Outside Smoke Pit","Outside Smoke Pit"));
			   violationArea.add(new DropDownItem("Workout Area","Workout Area"));
			   violationArea.add(new DropDownItem("Church","Church"));
			   violationArea.add(new DropDownItem("Store","Store"));
			   violationArea.add(new DropDownItem("Garage","Garage"));
			   violationArea.add(new DropDownItem("Lawn Area","Lawn Area"));
			   violationArea.add(new DropDownItem("Warehouse","Warehouse"));
			   violationArea.add(new DropDownItem("Other Area","Other Area"));
			   session.setAttribute("ddl_violationArea", violationArea);

			   List<DropDownItem> violationType = new ArrayList<DropDownItem>();
			   violationType.add(new DropDownItem("Violation of Dorm Rules Area Rules","Violation of Dorm Rules Area Rules"));
			   violationType.add(new DropDownItem("Violation of Breezeway Area Rules","Violation of Breezeway Area Rules"));
			   violationType.add(new DropDownItem("Violation of Kitchen Area Rules","Violation of Kitchen Area Rules"));
			   violationType.add(new DropDownItem("Violation of Laundry Room Area Rules","Violation of Laundry Room Area Rules"));
			   violationType.add(new DropDownItem("Violation of TV Room Area Rules","Violation of TV Room Area Rules"));
			   violationType.add(new DropDownItem("Violation of Smoke Pit Area Rules","Violation of Smoke Pit Area Rules"));
			   violationType.add(new DropDownItem("Violation of Weight Room Area Rules","Violation of Weight Room Area Rules"));
			   violationType.add(new DropDownItem("Violation of Church Area Rules","Violation of Church Area Rules"));
			   violationType.add(new DropDownItem("Violation of Visitation Area Rules","Violation of Visitation Rules Rules"));
			   violationType.add(new DropDownItem("Violation of Work Area Rules","Violation of Work Area Rules"));
			   violationType.add(new DropDownItem("Violation of Faith Farm Area Rules","Violation of Faith Farm Area Rules"));
			   violationType.add(new DropDownItem("See Comments Area","See Comments Area"));
			   session.setAttribute("ddl_violationType", violationType);

			   List<DropDownItem> warnings = new ArrayList<DropDownItem>();
			   for (int i=1;i<11;i++)
			   warnings.add(new DropDownItem(""+i,""+i));
			   session.setAttribute("ddl_warnings", warnings);

			   List<DropDownItem> fines = new ArrayList<DropDownItem>();
			   for (int i=1;i<11;i++)
				   fines.add(new DropDownItem("$ "+i+".00","$ "+i+".00"));
			   fines.add(new DropDownItem("Half of Benevolence","Half of Benevolence"));
			   fines.add(new DropDownItem("1 Week of Benevolence","1 Week of Benevolence"));
			   fines.add(new DropDownItem("2 Weeks of Benevolence","2 Weeks of Benevolence"));
			   fines.add(new DropDownItem("3 Weeks of Benevolence","3 Weeks of Benevolence"));			   
			   session.setAttribute("ddl_fines", fines);

			   List<DropDownItem> additionalConsequences = new ArrayList<DropDownItem>();
			   additionalConsequences.add(new DropDownItem("See Comments","See Comments"));
			   session.setAttribute("ddl_additionalConsequences", additionalConsequences);

		        
		        List<DropDownItem> issueType = new ArrayList<DropDownItem>();
		        issueType.add(new DropDownItem("Bug/Defect","Bug/Defect"));
		        issueType.add(new DropDownItem("Data Request","Data Request"));
		        issueType.add(new DropDownItem("Enhancement Request","Enhancement Request"));
		        issueType.add(new DropDownItem("General Request","General Request"));
		        issueType.add(new DropDownItem("Performance Issue","Performance Issue"));
		        session.setAttribute("ddl_issueType", issueType);

		        List<DropDownItem> priority = new ArrayList<DropDownItem>();
		        priority.add(new DropDownItem("Low","Low"));
		        priority.add(new DropDownItem("Medium","Medium"));
		        priority.add(new DropDownItem("High","High"));
		        priority.add(new DropDownItem("Emergency","Emergency"));
		        session.setAttribute("ddl_priority", priority);

	 
	}
	
	public void getOnlineApplicationQuestions(ActionForm form) {
	      QuestionDao dao4 = new QuestionDao();
	        List<Question> questions = new ArrayList<Question>();
	        List<Question> healthQuestions = new ArrayList<Question>();
	        List<Question> emotionalQuestions = new ArrayList<Question>();
	        List<Question> physicalQuestions = new ArrayList<Question>();
	        List<Question> mentalQuestions = new ArrayList<Question>();
	        
	        OnlineAppForm onlineAppForm = (OnlineAppForm)form;
	        
	        int count=0;
	        questions=dao4.list();
	        for (Iterator iterator =
	    			questions.iterator(); iterator.hasNext();){
	    			Question obj = (Question) iterator.next();
	                
	    			if (count<15)
	    				healthQuestions.add(obj);
	    			else if (count>14&&count<21)
	    				emotionalQuestions.add(obj);
	    			else if (count>20&&count<26)
	    				physicalQuestions.add(obj);
	    			else if (count>25&&count<32)
	    				mentalQuestions.add(obj);
	    			
	        count++;
	        }
	       onlineAppForm.setHealthQuestions(healthQuestions);
	       onlineAppForm.setEmotionalQuestions(emotionalQuestions);
	       onlineAppForm.setPhysicalQuestions(physicalQuestions);
	       onlineAppForm.setMentalQuestions(mentalQuestions);	       
	       
	       MedicalConditionDao dao5 = new MedicalConditionDao();
	       List<MedicalCondition> medicalConditions = new ArrayList<MedicalCondition>();
	       medicalConditions = dao5.list();
	       for (Iterator iterator =
	    		   medicalConditions.iterator(); iterator.hasNext();){
	    	   MedicalCondition obj = (MedicalCondition) iterator.next();	    			
	       }
	       onlineAppForm.setMedicalConditions(medicalConditions);
	       
	       JobSkillDao dao6 = new JobSkillDao();
	       List<JobSkill> jobSkills = new ArrayList<JobSkill>();
	       jobSkills = dao6.list();
	       for (Iterator iterator =
	    		   jobSkills.iterator(); iterator.hasNext();){
	    	   JobSkill obj = (JobSkill) iterator.next();	    			
	       }
	       onlineAppForm.setJobSkills(jobSkills);
	 

	       
	}
}
