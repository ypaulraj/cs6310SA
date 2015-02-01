package edu.gatech.lp;

import java.util.ArrayList;
import java.util.List;

public class University {
	
	List<Student> students = new ArrayList<Student>();

	public void addStudent(Student student) {

		students.add(student);
	}

}
