package org.jacys.dscas.model;

public class Dependency {

	private int courseId;
	private int depdCourseId;
	private boolean corequisite;
	private boolean prerequisite;
	
	public Dependency(int courseId, int depdCourseId, boolean corequisite, boolean prerequisite) {
		this.setCourseId(courseId);
		this.setDepdCourseId(depdCourseId);
		this.setCorequisite(corequisite);
		this.setPrerequisite(prerequisite);
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getDepdCourseId() {
		return depdCourseId;
	}

	public void setDepdCourseId(int depdCourseId) {
		this.depdCourseId = depdCourseId;
	}

	public boolean isCorequisite() {
		return corequisite;
	}

	public void setCorequisite(boolean corequisite) {
		this.corequisite = corequisite;
	}

	public boolean isPrerequisite() {
		return prerequisite;
	}

	public void setPrerequisite(boolean prerequisite) {
		this.prerequisite = prerequisite;
	}
	
}
