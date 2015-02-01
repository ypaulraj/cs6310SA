package edu.gatech.lp;

import java.util.ArrayList;
import java.util.List;

public class Student {
	
	private List<Course> courses  = new ArrayList<Course>();

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public void addCourse(Course course) {
		courses.add(course);
	}

}
