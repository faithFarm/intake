package org.faithfarm.sms.domain;

// Generated Sep 19, 2013 1:34:58 PM by Hibernate Tools 3.4.0.CR1

/**
 * IntakeJobHistory generated by hbm2java
 */
public class IntakeJobHistory implements java.io.Serializable {

	private long intakeJobHistoryId;
	private long jobAssignmentId;
	private long intakeId;
	private String beginDate;
	private String endDate;
	private String comments;
	private String creationDate;
	private String createdBy;

	public IntakeJobHistory() {
	}

	public IntakeJobHistory(long intakeJobHistoryId, long jobAssignmentId,
			long intakeId, String beginDate) {
		this.intakeJobHistoryId = intakeJobHistoryId;
		this.jobAssignmentId = jobAssignmentId;
		this.intakeId = intakeId;
		this.beginDate = beginDate;
	}

	public IntakeJobHistory(long intakeJobHistoryId, long jobAssignmentId,
			long intakeId, String beginDate, String endDate, String comments,
			String creationDate, String createdBy) {
		this.intakeJobHistoryId = intakeJobHistoryId;
		this.jobAssignmentId = jobAssignmentId;
		this.intakeId = intakeId;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.comments = comments;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
	}

	public long getIntakeJobHistoryId() {
		return this.intakeJobHistoryId;
	}

	public void setIntakeJobHistoryId(long intakeJobHistoryId) {
		this.intakeJobHistoryId = intakeJobHistoryId;
	}

	public long getJobAssignmentId() {
		return this.jobAssignmentId;
	}

	public void setJobAssignmentId(long jobAssignmentId) {
		this.jobAssignmentId = jobAssignmentId;
	}

	public long getIntakeId() {
		return this.intakeId;
	}

	public void setIntakeId(long intakeId) {
		this.intakeId = intakeId;
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

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

}
