package org.faithfarm.sms.domain;

// Generated Sep 19, 2013 1:34:58 PM by Hibernate Tools 3.4.0.CR1

/**
 * CourseInstructor generated by hbm2java
 */
public class CourseInstructor implements java.io.Serializable {

	private long courseInstructorId;
	private long courseId;
	private String creationDate;
	private String createdBy;

	public CourseInstructor() {
	}

	public CourseInstructor(long courseInstructorId, long courseId) {
		this.courseInstructorId = courseInstructorId;
		this.courseId = courseId;
	}

	public CourseInstructor(long courseInstructorId, long courseId,
			String creationDate, String createdBy) {
		this.courseInstructorId = courseInstructorId;
		this.courseId = courseId;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
	}

	public long getCourseInstructorId() {
		return this.courseInstructorId;
	}

	public void setCourseInstructorId(long courseInstructorId) {
		this.courseInstructorId = courseInstructorId;
	}

	public long getCourseId() {
		return this.courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
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
