package org.faithfarm.sms.domain;

public class SearchParameter implements java.io.Serializable {
	
	private String firstname;
	private String lastname;
	private String ssn;
	private String dob;
	private String beginDate;
	private String endDate;
	private String isArchived;
	private String farmBase;
	private String applicationStatus;
	private Long jobSkillId;
	private String driverFlag;
	private String gedFlag;
	
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
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getIsArchived() {
		return isArchived;
	}
	public void setIsArchived(String isArchived) {
		this.isArchived = isArchived;
	}
	public String getFarmBase() {
		return farmBase;
	}
	public void setFarmBase(String farmBase) {
		this.farmBase = farmBase;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public Long getJobSkillId() {
		return jobSkillId;
	}
	public void setJobSkillId(Long jobSkillId) {
		this.jobSkillId = jobSkillId;
	}
	public String getDriverFlag() {
		return driverFlag;
	}
	public void setDriverFlag(String driverFlag) {
		this.driverFlag = driverFlag;
	}
	public String getGedFlag() {
		return gedFlag;
	}
	public void setGedFlag(String gedFlag) {
		this.gedFlag = gedFlag;
	}
	
	

}
