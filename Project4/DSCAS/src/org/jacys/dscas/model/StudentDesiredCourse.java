package org.jacys.dscas.model;

import org.jacys.common.util.HashCodeUtil;

public class StudentDesiredCourse implements Comparable<StudentDesiredCourse> {

	private int studentId;
	private int courseId;

	public StudentDesiredCourse(int studentId, int courseId) {
		this.setStudentId(studentId);
		this.setCourseId(courseId);
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	@Override
	public int compareTo(StudentDesiredCourse that) {
		if (this.equals(that))
			return 0;
		if (this.studentId == that.studentId) {
			if (this.courseId < that.courseId) {
				return -1;
			} else {
				return 1;
			}
		} else if (this.studentId < that.studentId) {
			return -1;
		} else {
			return 1;
		}
	}

	/**
	 * Define equality of state.
	 */
	@Override
	public boolean equals(Object aThat) {
		if (this == aThat)
			return true;
		if (!(aThat instanceof StudentDesiredCourse))
			return false;

		StudentDesiredCourse that = (StudentDesiredCourse) aThat;
		return (this.studentId == that.studentId)
				&& (this.courseId == that.courseId);
	}

	/**
	 * A class that overrides equals must also override hashCode.
	 */
	@Override
	public int hashCode() {
		int result = HashCodeUtil.SEED;
		result = HashCodeUtil.hash(result, this.studentId);
		result = HashCodeUtil.hash(result, this.courseId);
		return result;
	}
}
