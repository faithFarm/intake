package org.faithfarm.sms.domain;

// Generated Sep 23, 2013 2:58:31 PM by Hibernate Tools 3.4.0.CR1

/**
 * CwtModuleStudent generated by hbm2java
 */
public class CwtModuleStudent implements java.io.Serializable {

	private Long moduleRosterId;
	private Long moduleOfferingId;
	private Long intakeId;
	private String status;
	private String meetingDate;
	private String completionFlag;
	private String creationDate;
	private String createdBy;

	public CwtModuleStudent() {
	}

	public CwtModuleStudent(Long moduleOfferingId, Long intakeId,
			String status, String meetingDate, String completionFlag,
			String creationDate, String createdBy) {
		this.moduleOfferingId = moduleOfferingId;
		this.intakeId = intakeId;
		this.status = status;
		this.meetingDate = meetingDate;
		this.completionFlag = completionFlag;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
	}

	public Long getModuleRosterId() {
		return this.moduleRosterId;
	}

	public void setModuleRosterId(Long moduleRosterId) {
		this.moduleRosterId = moduleRosterId;
	}

	public Long getModuleOfferingId() {
		return this.moduleOfferingId;
	}

	public void setModuleOfferingId(Long moduleOfferingId) {
		this.moduleOfferingId = moduleOfferingId;
	}

	public Long getIntakeId() {
		return this.intakeId;
	}

	public void setIntakeId(Long intakeId) {
		this.intakeId = intakeId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMeetingDate() {
		return this.meetingDate;
	}

	public void setMeetingDate(String meetingDate) {
		this.meetingDate = meetingDate;
	}

	public String getCompletionFlag() {
		return this.completionFlag;
	}

	public void setCompletionFlag(String completionFlag) {
		this.completionFlag = completionFlag;
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
