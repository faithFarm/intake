package org.faithfarm.sms.domain;

public class ViewFastFind implements java.io.Serializable {

	private Long intakeId;
	private String firstname;
	private String lastname;
	private String entryDate;
	private String dob;
	private String supervisor;
	private String job;
	private String cwt;
	private String currentClass;
	private String program;
	private String farmBase;
	
	public ViewFastFind() { }
	
	public Long getIntakeId() {
		return intakeId;
	}
	public void setIntakeId(Long intakeId) {
		this.intakeId = intakeId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getCwt() {
		return cwt;
	}
	public void setCwt(String cwt) {
		this.cwt = cwt;
	}
	public String getCurrentClass() {
		return currentClass;
	}
	public void setCurrentClass(String currentClass) {
		this.currentClass = currentClass;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getFarmBase() {
		return farmBase;
	}
	public void setFarmBase(String farmBase) {
		this.farmBase = farmBase;
	}
	
	
}
