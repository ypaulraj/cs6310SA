package org.jacys.dscas.model;

import java.util.ArrayList;
import java.util.List;

import org.jacys.common.util.HashCodeUtil;

public class Student implements Comparable<Student> {

	private int studentId;
	private String name;
	private int senority;
	private List<StudentDesiredCourse> studentDesiredCourses = new ArrayList<>();
	
	public Student() {}
	
	public Student(int studentId, String name, int senority) {
		this.setStudentId(studentId);
		this.setName(name);
		this.setSenority(senority);
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSenority() {
		return senority;
	}

	public void setSenority(int senority) {
		this.senority = senority;
	}
	
	public void addStudentDesiredCourse(StudentDesiredCourse studentDesiredCourse) {
		this.studentDesiredCourses.add(studentDesiredCourse);
	}
	
	public void removeStudentDesiredCourse(StudentDesiredCourse studentDesiredCourse) {
		this.studentDesiredCourses.remove(studentDesiredCourse);
	}

	@Override
	public int compareTo(Student that) {
		if (this.studentId == that.studentId) {
				return 0;
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
		if (!(aThat instanceof Student))
			return false;

		Student that = (Student) aThat;
		return (this.studentId == that.studentId);
	}

	/**
	 * A class that overrides equals must also override hashCode.
	 */
	@Override
	public int hashCode() {
		int result = HashCodeUtil.SEED;
		result = HashCodeUtil.hash(result, this.studentId);
		return result;
	}
}
