package org.faithfarm.sms.domain;

public class ViewCwtIntake implements java.io.Serializable {
	private Long intakeId;
	private String name;
	private String program;
	private String farm;
	
	public Long getIntakeId() {
		return intakeId;
	}
	public void setIntakeId(Long intakeId) {
		this.intakeId = intakeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getFarm() {
		return farm;
	}
	public void setFarm(String farm) {
		this.farm = farm;
	}
	
	
}
