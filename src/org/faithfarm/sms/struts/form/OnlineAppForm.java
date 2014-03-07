package org.faithfarm.sms.struts.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.faithfarm.sms.domain.ErrorMessage;
import org.faithfarm.sms.domain.Intake;
import org.faithfarm.sms.domain.JobSkill;
import org.faithfarm.sms.domain.MedicalCondition;
import org.faithfarm.sms.domain.Question;

public class OnlineAppForm extends ActionForm {
	
	private Intake intake = new Intake();
	private String pageSource;
	private String nextStep;
	private String previousStep;
	private String messageType;
	private List<ErrorMessage> messages = new ArrayList<ErrorMessage>();
    
	private List<Question> healthQuestions = new ArrayList<Question>();
	private List<Question> emotionalQuestions = new ArrayList<Question>();
    private List<Question> physicalQuestions = new ArrayList<Question>();
    private List<Question> mentalQuestions = new ArrayList<Question>();
    private List<MedicalCondition> medicalConditions = new ArrayList<MedicalCondition>();
    private List<JobSkill> jobSkills = new ArrayList<JobSkill>();
	private String motivationalLossFlag;
	private String shakesConvulsionsFlag;
	private String memoryLossFlag;
	private String incoherentThinkingFlag;
	private String organProblemsFlag;	

 	private String usagePattern1 = "";
	private String usagePattern2 = "";
	private String usagePattern3 = "";
	private String usagePattern4 = "";
	private String usagePattern5 = "";
	private String usagePattern6 = "";
	private String usageLosses1 = "";
	private String usageLosses2 = "";
	private String usageLosses3 = "";
	private String usageLosses4 = "";
	private String usageLosses5 = "";
	private String usageLosses6 = "";
	private String usageLosses7 = "";
	private String usageLosses8 = "";
	private String usageLosses9 = "";
	private String healthAnswer[] = new String[] { "No","No","No","No","No","No","No","No","No","No","No","No","No","No","No" };
	private String emotionalAnswer[] = new String[] { "No","No","No","No","No","No","No","No","No","No","No","No","No","No","No" };
	private String physicalAnswer[] = new String[] { "No","No","No","No","No","No","No","No","No","No","No","No","No","No","No" };
	private String mentalAnswer[] = new String[16];
	private String emotionalAnswerDate[] = new String[16];
	private String emotionalAnswerDetails[] = new String[16];
	private String mentalAnswerDate[] = new String[16];
	private String mentalAnswerDetails[] = new String[16];
	private String physicalAnswerDetails[] = new String[16];
	private String questionAnswerDates[] = new String[16];
	private String medicalCondition[] = { "No", "No", "No", "No",
			"No", "No", "No", "No", "No", "No", "No", "No", "No", "No", "No",
			"No", "No", "No", "No", "No", "No", "No", "No", "No", "No", "No",
			"No", "No", "No", "No", "No", "No", "No", "No" };
	private String workExperience[] = new String[45];
	
	
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public List<ErrorMessage> getMessages() {
		return messages;
	}
	public void setMessages(List<ErrorMessage> messages) {
		this.messages = messages;
	}
	public List<Question> getHealthQuestions() {
		return healthQuestions;
	}
	public void setHealthQuestions(List<Question> healthQuestions) {
		this.healthQuestions = healthQuestions;
	}
	public List<Question> getEmotionalQuestions() {
		return emotionalQuestions;
	}
	public void setEmotionalQuestions(List<Question> emotionalQuestions) {
		this.emotionalQuestions = emotionalQuestions;
	}
	public List<Question> getPhysicalQuestions() {
		return physicalQuestions;
	}
	public void setPhysicalQuestions(List<Question> physicalQuestions) {
		this.physicalQuestions = physicalQuestions;
	}
	public List<Question> getMentalQuestions() {
		return mentalQuestions;
	}
	public void setMentalQuestions(List<Question> mentalQuestions) {
		this.mentalQuestions = mentalQuestions;
	}
	public List<MedicalCondition> getMedicalConditions() {
		return medicalConditions;
	}
	public void setMedicalConditions(List<MedicalCondition> medicalConditions) {
		this.medicalConditions = medicalConditions;
	}
	public List<JobSkill> getJobSkills() {
		return jobSkills;
	}
	public void setJobSkills(List<JobSkill> jobSkills) {
		this.jobSkills = jobSkills;
	}
	public String getUsagePattern1() {
		return usagePattern1;
	}
	public void setUsagePattern1(String usagePattern1) {
		this.usagePattern1 = usagePattern1;
	}
	public String getUsagePattern2() {
		return usagePattern2;
	}
	public void setUsagePattern2(String usagePattern2) {
		this.usagePattern2 = usagePattern2;
	}
	public String getUsagePattern3() {
		return usagePattern3;
	}
	public void setUsagePattern3(String usagePattern3) {
		this.usagePattern3 = usagePattern3;
	}
	public String getUsagePattern4() {
		return usagePattern4;
	}
	public void setUsagePattern4(String usagePattern4) {
		this.usagePattern4 = usagePattern4;
	}
	public String getUsagePattern5() {
		return usagePattern5;
	}
	public void setUsagePattern5(String usagePattern5) {
		this.usagePattern5 = usagePattern5;
	}
	public String getUsagePattern6() {
		return usagePattern6;
	}
	public void setUsagePattern6(String usagePattern6) {
		this.usagePattern6 = usagePattern6;
	}
	public String getUsageLosses1() {
		return usageLosses1;
	}
	public void setUsageLosses1(String usageLosses1) {
		this.usageLosses1 = usageLosses1;
	}
	public String getUsageLosses2() {
		return usageLosses2;
	}
	public void setUsageLosses2(String usageLosses2) {
		this.usageLosses2 = usageLosses2;
	}
	public String getUsageLosses3() {
		return usageLosses3;
	}
	public void setUsageLosses3(String usageLosses3) {
		this.usageLosses3 = usageLosses3;
	}
	public String getUsageLosses4() {
		return usageLosses4;
	}
	public void setUsageLosses4(String usageLosses4) {
		this.usageLosses4 = usageLosses4;
	}
	public String getUsageLosses5() {
		return usageLosses5;
	}
	public void setUsageLosses5(String usageLosses5) {
		this.usageLosses5 = usageLosses5;
	}
	public String getUsageLosses6() {
		return usageLosses6;
	}
	public void setUsageLosses6(String usageLosses6) {
		this.usageLosses6 = usageLosses6;
	}
	public String getUsageLosses7() {
		return usageLosses7;
	}
	public void setUsageLosses7(String usageLosses7) {
		this.usageLosses7 = usageLosses7;
	}
	public String getUsageLosses8() {
		return usageLosses8;
	}
	public void setUsageLosses8(String usageLosses8) {
		this.usageLosses8 = usageLosses8;
	}
	public String getUsageLosses9() {
		return usageLosses9;
	}
	public void setUsageLosses9(String usageLosses9) {
		this.usageLosses9 = usageLosses9;
	}
	public String[] getHealthAnswer() {
		return healthAnswer;
	}
	public void setHealthAnswer(String[] healthAnswer) {
		this.healthAnswer = healthAnswer;
	}
	public String[] getEmotionalAnswer() {
		return emotionalAnswer;
	}
	public void setEmotionalAnswer(String[] emotionalAnswer) {
		this.emotionalAnswer = emotionalAnswer;
	}
	public String[] getPhysicalAnswer() {
		return physicalAnswer;
	}
	public void setPhysicalAnswer(String[] physicalAnswer) {
		this.physicalAnswer = physicalAnswer;
	}
	public String[] getMentalAnswer() {
		return mentalAnswer;
	}
	public void setMentalAnswer(String[] mentalAnswer) {
		this.mentalAnswer = mentalAnswer;
	}
	public String[] getEmotionalAnswerDate() {
		return emotionalAnswerDate;
	}
	public void setEmotionalAnswerDate(String[] emotionalAnswerDate) {
		this.emotionalAnswerDate = emotionalAnswerDate;
	}
	public String[] getEmotionalAnswerDetails() {
		return emotionalAnswerDetails;
	}
	public void setEmotionalAnswerDetails(String[] emotionalAnswerDetails) {
		this.emotionalAnswerDetails = emotionalAnswerDetails;
	}
	public String[] getMentalAnswerDate() {
		return mentalAnswerDate;
	}
	public void setMentalAnswerDate(String[] mentalAnswerDate) {
		this.mentalAnswerDate = mentalAnswerDate;
	}
	public String[] getMentalAnswerDetails() {
		return mentalAnswerDetails;
	}
	public void setMentalAnswerDetails(String[] mentalAnswerDetails) {
		this.mentalAnswerDetails = mentalAnswerDetails;
	}
	public String[] getPhysicalAnswerDetails() {
		return physicalAnswerDetails;
	}
	public void setPhysicalAnswerDetails(String[] physicalAnswerDetails) {
		this.physicalAnswerDetails = physicalAnswerDetails;
	}
	public String[] getQuestionAnswerDates() {
		return questionAnswerDates;
	}
	public void setQuestionAnswerDates(String[] questionAnswerDates) {
		this.questionAnswerDates = questionAnswerDates;
	}
	public String[] getMedicalCondition() {
		return medicalCondition;
	}
	public void setMedicalCondition(String[] medicalCondition) {
		this.medicalCondition = medicalCondition;
	}
	public String[] getWorkExperience() {
		return workExperience;
	}
	public void setWorkExperience(String[] workExperience) {
		this.workExperience = workExperience;
	}
	public Intake getIntake() {
		return intake;
	}
	public void setIntake(Intake intake) {
		this.intake = intake;
	}
	public String getPageSource() {
		return pageSource;
	}
	public void setPageSource(String pageSource) {
		this.pageSource = pageSource;
	}
	public String getNextStep() {
		return nextStep;
	}
	public void setNextStep(String nextStep) {
		this.nextStep = nextStep;
	}	
	public String getPreviousStep() {
		return previousStep;
	}
	public void setPreviousStep(String previousStep) {
		this.previousStep = previousStep;
	}
	public String getMotivationalLossFlag() {
		return motivationalLossFlag;
	}
	public void setMotivationalLossFlag(String motivationalLossFlag) {
		this.motivationalLossFlag = motivationalLossFlag;
	}
	public String getShakesConvulsionsFlag() {
		return shakesConvulsionsFlag;
	}
	public void setShakesConvulsionsFlag(String shakesConvulsionsFlag) {
		this.shakesConvulsionsFlag = shakesConvulsionsFlag;
	}
	public String getMemoryLossFlag() {
		return memoryLossFlag;
	}
	public void setMemoryLossFlag(String memoryLossFlag) {
		this.memoryLossFlag = memoryLossFlag;
	}
	public String getIncoherentThinkingFlag() {
		return incoherentThinkingFlag;
	}
	public void setIncoherentThinkingFlag(String incoherentThinkingFlag) {
		this.incoherentThinkingFlag = incoherentThinkingFlag;
	}
	public String getOrganProblemsFlag() {
		return organProblemsFlag;
	}
	public void setOrganProblemsFlag(String organProblemsFlag) {
		this.organProblemsFlag = organProblemsFlag;
	}
	
	
	

}
