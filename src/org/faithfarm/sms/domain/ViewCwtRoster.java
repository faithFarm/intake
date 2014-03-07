package org.faithfarm.sms.domain;

public class ViewCwtRoster implements java.io.Serializable {

	private Long sectionId;
	private Long moduleId;
	private Long programId;
	private String moduleName;
	private String rosterDate;
	private String farmBase;
	private String archivedFlag;
	private String programName;
	private String sequence;
	
	public ViewCwtRoster() {}
	
	public ViewCwtRoster(Long moduleId, 
			String moduleName, String rosterDate, String farmBase, 
			String archivedFlag, String programName, String sequence){
		this.moduleId=moduleId;
		this.moduleName=moduleName;
		this.rosterDate=rosterDate;
		this.farmBase=farmBase;
		this.archivedFlag=archivedFlag;
		this.programName=programName;
		this.sequence=sequence;
	}
	
	public Long getProgramId() {
		return programId;
	}
	public void setProgramId(Long programId) {
		this.programId = programId;
	}
	public Long getSectionId() {
		return sectionId;
	}
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
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
	public String getRosterDate() {
		return rosterDate;
	}
	public void setRosterDate(String rosterDate) {
		this.rosterDate = rosterDate;
	}
	public String getFarmBase() {
		return farmBase;
	}
	public void setFarmBase(String farmBase) {
		this.farmBase = farmBase;
	}
	public String getArchivedFlag() {
		return archivedFlag;
	}
	public void setArchivedFlag(String archivedFlag) {
		this.archivedFlag = archivedFlag;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	
}
