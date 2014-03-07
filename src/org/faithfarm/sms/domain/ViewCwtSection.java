package org.faithfarm.sms.domain;

public class ViewCwtSection implements java.io.Serializable {
	private Long sectionId;
	private Long programId;
	private Long moduleId;
	private String moduleSeq;
	private String moduleName;
	private String meetingDays;
	private String meetingTimes;
	private String location;
	private String farmBase;
	private String instructor;
	
	public Long getSectionId() {
		return sectionId;
	}
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}	
	public Long getProgramId() {
		return programId;
	}
	public void setProgramId(Long programId) {
		this.programId = programId;
	}
	public Long getModuleId() {
		return moduleId;
	}
	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getMeetingDays() {
		return meetingDays;
	}
	public void setMeetingDays(String meetingDays) {
		this.meetingDays = meetingDays;
	}
	public String getMeetingTimes() {
		return meetingTimes;
	}
	public void setMeetingTimes(String meetingTimes) {
		this.meetingTimes = meetingTimes;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getFarmBase() {
		return farmBase;
	}
	public void setFarmBase(String farmBase) {
		this.farmBase = farmBase;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getModuleSeq() {
		return moduleSeq;
	}
	public void setModuleSeq(String moduleSeq) {
		this.moduleSeq = moduleSeq;
	}
	
	
	
}
