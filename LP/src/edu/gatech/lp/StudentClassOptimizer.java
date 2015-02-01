package edu.gatech.lp;

import java.io.FileNotFoundException;
import java.util.List;

public class StudentClassOptimizer {

	public static void main(String[] args) throws FileNotFoundException {

		University university = buildStudents();
		
		// Now build the GLPK objective
		
		
		// Build constraints
		
		
		// Solve the problem
		
		
		// Print the results as needed.
		

	}

	private static University buildStudents() throws FileNotFoundException {
		List<List<Integer>> studentSchedules = InputParser.readFile();

		University university = new University();

		Student student = null;
		Course course = null;

		for (List<Integer> studentSchedule : studentSchedules) {
			student = new Student();

			for (Integer courseNum : studentSchedule) {
				course = new Course(courseNum);
				student.addCourse(course);
			}
		}

		university.addStudent(student);
		return university;
	}

}
