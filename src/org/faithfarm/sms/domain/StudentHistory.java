package org.faithfarm.sms.domain;

// Generated Sep 19, 2013 1:34:58 PM by Hibernate Tools 3.4.0.CR1

/**
 * StudentHistory generated by hbm2java
 */
public class StudentHistory implements java.io.Serializable {

	private Long studentHistoryId;
	private long intakeId;
	private String phase;
	private String programStatus;
	private String reason;
	private String beginDate;
	private String endDate;
	private String creationDate;
	private String createdBy;
	private String farm;

	public StudentHistory() {
	}

	public StudentHistory(long intakeId) {
		this.intakeId = intakeId;
	}

	public StudentHistory(long intakeId, String phase, String programStatus,
			String reason, String beginDate, String endDate,
			String creationDate, String createdBy, String farm) {
		this.intakeId = intakeId;
		this.phase = phase;
		this.programStatus = programStatus;
		this.reason = reason;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.farm = farm;
	}

	public Long getStudentHistoryId() {
		return this.studentHistoryId;
	}

	public void setStudentHistoryId(Long studentHistoryId) {
		this.studentHistoryId = studentHistoryId;
	}

	public long getIntakeId() {
		return this.intakeId;
	}

	public void setIntakeId(long intakeId) {
		this.intakeId = intakeId;
	}

	public String getPhase() {
		return this.phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getProgramStatus() {
		return this.programStatus;
	}

	public void setProgramStatus(String programStatus) {
		this.programStatus = programStatus;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getFarm() {
		return this.farm;
	}

	public void setFarm(String farm) {
		this.farm = farm;
	}

}
