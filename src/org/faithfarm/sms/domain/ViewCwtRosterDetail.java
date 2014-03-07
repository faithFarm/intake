package org.faithfarm.sms.domain;

public class ViewCwtRosterDetail implements java.io.Serializable {

	private Long intakeId;
	private Long programId;
	private Long rosterId;
	private Long moduleId;
	private Long sectionId;
	private String farmBase;
	private String programName;
	private String moduleName;
	private String studentName;
	private String status;
	private String classDate;
	private String examScore;
	
	public Long getIntakeId() {
		return intakeId;
	}
	public void setIntakeId(Long intakeId) {
		this.intakeId = intakeId;
	}
	public Long getProgramId() {
		return programId;
	}
	public void setProgramId(Long programId) {
		this.programId = programId;
	}
	public Long getRosterId() {
		return rosterId;
	}
	public void setRosterId(Long rosterId) {
		this.rosterId = rosterId;
	}
	public Long getModuleId() {
		return moduleId;
	}
	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}
	public Long getSectionId() {
		return sectionId;
	}
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}
	public String getFarmBase() {
		return farmBase;
	}
	public void setFarmBase(String farmBase) {
		this.farmBase = farmBase;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getClassDate() {
		return classDate;
	}
	public void setClassDate(String classDate) {
		this.classDate = classDate;
	}
	public String getExamScore() {
		return examScore;
	}
	public void setExamScore(String examScore) {
		this.examScore = examScore;
	}
	
	
	
}
