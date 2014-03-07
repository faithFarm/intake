package org.faithfarm.sms.struts.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.faithfarm.sms.domain.CwtDepartment;
import org.faithfarm.sms.domain.CwtJob;
import org.faithfarm.sms.domain.CwtMaster;
import org.faithfarm.sms.domain.CwtMetrics;
import org.faithfarm.sms.domain.CwtModuleSection;
import org.faithfarm.sms.domain.CwtModules;
import org.faithfarm.sms.domain.CwtProgram;
import org.faithfarm.sms.domain.CwtProgramMetricModules;
import org.faithfarm.sms.domain.CwtSupervisor;
import org.faithfarm.sms.domain.ErrorMessage;
import org.faithfarm.sms.domain.ViewCwtRoster;
import org.faithfarm.sms.domain.ViewCwtSection;

public class CwtForm extends ActionForm {
	
	private List<CwtJob> jobList = new ArrayList<CwtJob>();
	private List<CwtSupervisor> supervisorList = new ArrayList<CwtSupervisor>();
	private List<CwtDepartment> departmentList = new ArrayList<CwtDepartment>();
	private List<CwtMetrics> metricList = new ArrayList<CwtMetrics>();
	private List<CwtProgram> programList = new ArrayList<CwtProgram>();
	private List<CwtProgramMetricModules> programMetricModulesList = new ArrayList<CwtProgramMetricModules>();
	private List<CwtModules> moduleList = new ArrayList<CwtModules>();
	private List<CwtModuleSection> moduleSectionList = new ArrayList<CwtModuleSection>();
	private List<ViewCwtRoster> currentRosterList = new ArrayList<ViewCwtRoster>();
	private List<ViewCwtRoster> archivedRosterList = new ArrayList<ViewCwtRoster>();
	private List<CwtMaster> masterList = new ArrayList<CwtMaster>();
	private List<ViewCwtSection> cwtSections = new ArrayList<ViewCwtSection>();
	private List<ErrorMessage> messages = new ArrayList<ErrorMessage>();
	private String messageType;
	private CwtDepartment cwtDepartment = new CwtDepartment();
	private CwtJob cwtJob = new CwtJob();
	private CwtSupervisor cwtSupervisor = new CwtSupervisor();
	private CwtProgram cwtProgram = new CwtProgram();
	private CwtModules cwtModule = new CwtModules();
	private CwtMetrics cwtMetric = new CwtMetrics();
	private CwtMaster cwtMaster = new CwtMaster();
	private CwtModuleSection cwtModuleSection = new CwtModuleSection();
	private CwtProgramMetricModules cwtProgramMetricModule = new CwtProgramMetricModules();
	
	private String pageSource;
	private String jobMetric[] = new String[200];
	private String metricUbit[] = new String[200];
	private String moduleMetric[] = new String[200];
	private String moduleSequence = "";
	
	private String monday="";
	private String tuesday="";
	private String wednesday="";
	private String thursday="";
	private String friday="";
	private String saturday="";
	private String sunday="";
	private String searchDate="";
	private String farmBase="";
	private Long moduleId;
	private Long programId;
	private String searchArchivedFlag="Yes";
	
	public CwtProgramMetricModules getCwtProgramMetricModule() {
		return cwtProgramMetricModule;
	}
	public void setCwtProgramMetricModule(
			CwtProgramMetricModules cwtProgramMetricModule) {
		this.cwtProgramMetricModule = cwtProgramMetricModule;
	}
	public List<CwtProgramMetricModules> getProgramMetricModulesList() {
		return programMetricModulesList;
	}
	public void setProgramMetricModulesList(
			List<CwtProgramMetricModules> programMetricModulesList) {
		this.programMetricModulesList = programMetricModulesList;
	}
	public CwtModuleSection getCwtModuleSection() {
		return cwtModuleSection;
	}
	public void setCwtModuleSection(CwtModuleSection cwtModuleSection) {
		this.cwtModuleSection = cwtModuleSection;
	}
	public List<CwtMaster> getMasterList() {
		return masterList;
	}
	public void setMasterList(List<CwtMaster> masterList) {
		this.masterList = masterList;
	}
	public List<CwtModuleSection> getModuleSectionList() {
		return moduleSectionList;
	}
	public void setModuleSectionList(List<CwtModuleSection> moduleSectionList) {
		this.moduleSectionList = moduleSectionList;
	}
	public CwtMaster getCwtMaster() {
		return cwtMaster;
	}
	public void setCwtMaster(CwtMaster cwtMaster) {
		this.cwtMaster = cwtMaster;
	}
	public String getMonday() {
		return monday;
	}
	public void setMonday(String monday) {
		this.monday = monday;
	}
	public String getTuesday() {
		return tuesday;
	}
	public void setTuesday(String tuesday) {
		this.tuesday = tuesday;
	}
	public String getWednesday() {
		return wednesday;
	}
	public void setWednesday(String wednesday) {
		this.wednesday = wednesday;
	}
	public String getThursday() {
		return thursday;
	}
	public void setThursday(String thursday) {
		this.thursday = thursday;
	}
	public String getFriday() {
		return friday;
	}
	public void setFriday(String friday) {
		this.friday = friday;
	}
	public String getSaturday() {
		return saturday;
	}
	public void setSaturday(String saturday) {
		this.saturday = saturday;
	}
	public String getSunday() {
		return sunday;
	}
	public void setSunday(String sunday) {
		this.sunday = sunday;
	}
	public String[] getModuleMetric() {
		return moduleMetric;
	}
	public void setModuleMetric(String[] moduleMetric) {
		this.moduleMetric = moduleMetric;
	}
	public List<CwtModules> getModuleList() {
		return moduleList;
	}
	public void setModuleList(List<CwtModules> moduleList) {
		this.moduleList = moduleList;
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
	public String[] getMetricUbit() {
		return metricUbit;
	}
	public void setMetricUbit(String[] metricUbit) {
		this.metricUbit = metricUbit;
	}
	public List<CwtProgram> getProgramList() {
		return programList;
	}
	public void setProgramList(List<CwtProgram> programList) {
		this.programList = programList;
	}
	public CwtProgram getCwtProgram() {
		return cwtProgram;
	}
	public void setCwtProgram(CwtProgram cwtProgram) {
		this.cwtProgram = cwtProgram;
	}
	public String[] getJobMetric() {
		return jobMetric;
	}
	public void setJobMetric(String[] jobMetric) {
		this.jobMetric = jobMetric;
	}
	public List<CwtMetrics> getMetricList() {
		return metricList;
	}
	public void setMetricList(List<CwtMetrics> metricList) {
		this.metricList = metricList;
	}
	public String getPageSource() {
		return pageSource;
	}
	public void setPageSource(String pageSource) {
		this.pageSource = pageSource;
	}
	public CwtDepartment getCwtDepartment() {
		return cwtDepartment;
	}
	public void setCwtDepartment(CwtDepartment cwtDepartment) {
		this.cwtDepartment = cwtDepartment;
	}
	public CwtJob getCwtJob() {
		return cwtJob;
	}
	public void setCwtJob(CwtJob cwtJob) {
		this.cwtJob = cwtJob;
	}
	public CwtSupervisor getCwtSupervisor() {
		return cwtSupervisor;
	}
	public void setCwtSupervisor(CwtSupervisor cwtSupervisor) {
		this.cwtSupervisor = cwtSupervisor;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public List<CwtJob> getJobList() {
		return jobList;
	}
	public void setJobList(List<CwtJob> jobList) {
		this.jobList = jobList;
	}
	public List<CwtSupervisor> getSupervisorList() {
		return supervisorList;
	}
	public void setSupervisorList(List<CwtSupervisor> supervisorList) {
		this.supervisorList = supervisorList;
	}
	public List<CwtDepartment> getDepartmentList() {
		return departmentList;
	}
	public void setDepartmentList(List<CwtDepartment> departmentList) {
		this.departmentList = departmentList;
	}
	public List<ErrorMessage> getMessages() {
		return messages;
	}
	public void setMessages(List<ErrorMessage> messages) {
		this.messages = messages;
	}
	public String getFarmBase() {
		return farmBase;
	}
	public void setFarmBase(String farmBase) {
		this.farmBase = farmBase;
	}
	public Long getModuleId() {
		return moduleId;
	}
	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}
	public String getSearchDate() {
		return searchDate;
	}
	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}
	public String getSearchArchivedFlag() {
		return searchArchivedFlag;
	}
	public void setSearchArchivedFlag(String searchArchivedFlag) {
		this.searchArchivedFlag = searchArchivedFlag;
	}
	public List<ViewCwtSection> getCwtSections() {
		return cwtSections;
	}
	public void setCwtSections(List<ViewCwtSection> cwtSections) {
		this.cwtSections = cwtSections;
	}
	public Long getProgramId() {
		return programId;
	}
	public void setProgramId(Long programId) {
		this.programId = programId;
	}
	public List<ViewCwtRoster> getCurrentRosterList() {
		return currentRosterList;
	}
	public void setCurrentRosterList(List<ViewCwtRoster> currentRosterList) {
		this.currentRosterList = currentRosterList;
	}
	public List<ViewCwtRoster> getArchivedRosterList() {
		return archivedRosterList;
	}
	public void setArchivedRosterList(List<ViewCwtRoster> archivedRosterList) {
		this.archivedRosterList = archivedRosterList;
	}
	public String getModuleSequence() {
		return moduleSequence;
	}
	public void setModuleSequence(String moduleSequence) {
		this.moduleSequence = moduleSequence;
	}
	
	
	
	

}
