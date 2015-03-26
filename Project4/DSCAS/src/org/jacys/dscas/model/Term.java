package org.jacys.dscas.model;

import java.util.List;

public class Term implements Comparable<Term> {

	private int id;
	private List<Integer> coursesOffered;
	
	public Term(int id, List<Integer> coursesOffered) {
		this.id = id;
		this.coursesOffered = coursesOffered;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Integer> getCoursesOffered() {
		return coursesOffered;
	}

	public void setCoursesOffered(List<Integer> coursesOffered) {
		this.coursesOffered = coursesOffered;
	}
	
	public boolean isCourseOffered(Course course) {
		return coursesOffered != null?coursesOffered.contains(course.getId()):false;
	}
	
	@Override
	public int compareTo(Term o) {
		if (o.getId() < this.getId()) {
			return 1;
		} else if (o.getId() == this.getId()) {
			return 0;
		} else {
			return -1;
		}
	}

}
