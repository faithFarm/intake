package org.faithfarm.sms.domain;

public class CwtRosterNotes implements java.io.Serializable {
	
	private Long notesId;
	private Long sectionId;
	private String rosterDate;
	private String notes;
	
	public CwtRosterNotes() {}
	
	public CwtRosterNotes(Long sectionId, String rosterDate,
			String notes) {
		this.sectionId = sectionId;
		this.rosterDate = rosterDate;
		this.notes = notes;
	}
	public Long getNotesId() {
		return notesId;
	}
	public void setNotesId(Long notesId) {
		this.notesId = notesId;
	}
	public Long getSectionId() {
		return sectionId;
	}
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}
	public String getRosterDate() {
		return rosterDate;
	}
	public void setRosterDate(String rosterDate) {
		this.rosterDate = rosterDate;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	

}
