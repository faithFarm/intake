package org.faithfarm.sms.struts.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.faithfarm.sms.domain.CwtMaster;
import org.faithfarm.sms.domain.CwtMetrics;
import org.faithfarm.sms.domain.CwtModuleSection;
import org.faithfarm.sms.domain.CwtModules;
import org.faithfarm.sms.domain.CwtProgram;
import org.faithfarm.sms.domain.CwtRoster;
import org.faithfarm.sms.domain.CwtRosterNotes;
import org.faithfarm.sms.domain.ErrorMessage;
import org.faithfarm.sms.domain.Intake;

public class CwtRosterForm extends ActionForm {

	private CwtProgram cwtProgram = new CwtProgram();
	private CwtModules cwtModule = new CwtModules();
	private CwtMetrics cwtMetric = new CwtMetrics();
	private CwtMaster cwtMaster = new CwtMaster();
	private CwtModuleSection cwtModuleSection = new CwtModuleSection();
	private CwtRosterNotes cwtRosterNotes = new CwtRosterNotes();
	private List<ErrorMessage> messages = new ArrayList<ErrorMessage>();
    
	private List<Intake> intakeList = new ArrayList<Intake>();
	private List<CwtMaster> masterList = new ArrayList<CwtMaster>();
	private List<CwtRoster> rosterList = new ArrayList<CwtRoster>();
	private String students[] = new String[200];
	private String enrollFlag[] = new String[200];
	private String attendFlag[] = new String[200];
	private String examScore[] = new String[200];
	private String status[] = new String[200];
	private String pageSource;
	private String notes;
	private String archiveFlag;
	private String rosterDate;
	private String messageType = "";
	private String classDate = "";
	private Long moduleId;
	
	
	
	public String getClassDate() {
		return classDate;
	}
	public void setClassDate(String classDate) {
		this.classDate = classDate;
	}
	public Long getModuleId() {
		return moduleId;
	}
	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}
	public String[] getStatus() {
		return status;
	}
	public void setStatus(String[] status) {
		this.status = status;
	}
	public String[] getAttendFlag() {
		return attendFlag;
	}
	public void setAttendFlag(String[] attendFlag) {
		this.attendFlag = attendFlag;
	}
	public String[] getExamScore() {
		return examScore;
	}
	public void setExamScore(String[] examScore) {
		this.examScore = examScore;
	}
	public String[] getEnrollFlag() {
		return enrollFlag;
	}
	public void setEnrollFlag(String[] enrollFlag) {
		this.enrollFlag = enrollFlag;
	}
	public List<CwtMaster> getMasterList() {
		return masterList;
	}
	public void setMasterList(List<CwtMaster> masterList) {
		this.masterList = masterList;
	}
	public List<Intake> getIntakeList() {
		return intakeList;
	}
	public void setIntakeList(List<Intake> intakeList) {
		this.intakeList = intakeList;
	}
	public CwtProgram getCwtProgram() {
		return cwtProgram;
	}
	public void setCwtProgram(CwtProgram cwtProgram) {
		this.cwtProgram = cwtProgram;
	}
	public CwtModules getCwtModule() {
		return cwtModule;
	}
	public void setCwtModule(CwtModules cwtModule) {
		this.cwtModule = cwtModule;
	}
	public CwtMetrics getCwtMetric() {
		return cwtMetric;
	}
	public void setCwtMetric(CwtMetrics cwtMetric) {
		this.cwtMetric = cwtMetric;
	}
	public CwtMaster getCwtMaster() {
		return cwtMaster;
	}
	public void setCwtMaster(CwtMaster cwtMaster) {
		this.cwtMaster = cwtMaster;
	}
	public CwtModuleSection getCwtModuleSection() {
		return cwtModuleSection;
	}
	public void setCwtModuleSection(CwtModuleSection cwtModuleSection) {
		this.cwtModuleSection = cwtModuleSection;
	}
	public String[] getStudents() {
		return students;
	}
	public void setStudents(String[] students) {
		this.students = students;
	}
	public String getPageSource() {
		return pageSource;
	}
	public void setPageSource(String pageSource) {
		this.pageSource = pageSource;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getArchiveFlag() {
		return archiveFlag;
	}
	public void setArchiveFlag(String archiveFlag) {
		this.archiveFlag = archiveFlag;
	}
	public String getRosterDate() {
		return rosterDate;
	}
	public void setRosterDate(String rosterDate) {
		this.rosterDate = rosterDate;
	}
	public List<ErrorMessage> getMessages() {
		return messages;
	}
	public void setMessages(List<ErrorMessage> messages) {
		this.messages = messages;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public List<CwtRoster> getRosterList() {
		return rosterList;
	}
	public void setRosterList(List<CwtRoster> rosterList) {
		this.rosterList = rosterList;
	}
	public CwtRosterNotes getCwtRosterNotes() {
		return cwtRosterNotes;
	}
	public void setCwtRosterNotes(CwtRosterNotes cwtRosterNotes) {
		this.cwtRosterNotes = cwtRosterNotes;
	}
	
	
	
}
