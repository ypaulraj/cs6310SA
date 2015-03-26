package org.jacys.dscas.persist;

//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.jacys.dscas.model.Course;
//import org.jacys.dscas.model.Student;
//import org.jacys.dscas.model.Term;

public class DataLoader {

//	public static Map<Integer, Student> loadStudentSchedule(String path)
//			throws IOException {
//
//		Map<Integer, Student> students = new HashMap<Integer, Student>();
//
//		System.out.print("Loading student schedule...");
//		BufferedReader reader = null;
//		try {
//			reader = new BufferedReader(new FileReader(path));
//			String line;
//			int studentId = 0;
//			while ((line = reader.readLine()) != null) {
//				line = line.trim();
//				// Skip any blank or comment lines
//				if (line.length() == 0 || line.startsWith("%")) {
//					continue;
//				}
//				studentId++; // starts with student id 1
//				List<String> preferences = Arrays.asList(line.replaceAll("\\.",
//						",").split("\\s*,\\s*"));
//				List<Integer> requiredCourses = new ArrayList<Integer>();
//				for (String pref : preferences) {
//					requiredCourses.add(Integer.parseInt(pref));
//				}
//
//				Student student = new Student(studentId, requiredCourses);
//
//				students.put(studentId, student);
//				//if (studentId == 5) break;
//			}
//		} finally {
//			if (reader != null) {
//				reader.close();
//			}
//		}
//		System.out.format("discovered %d requirements\n", students.size());
//		return students;
//	}
//
//	public static Map<Integer, Course> loadCourses(String coursesPath, String prerequisitesPath)
//			throws IOException {
//
//		Map<Integer, Course> courses = new HashMap<Integer, Course>();
//
//		System.out.print("Loading courses...");
//		BufferedReader reader = null;
//		try {
//			reader = new BufferedReader(new FileReader(coursesPath));
//			String line;
//			while ((line = reader.readLine()) != null) {
//				line = line.trim();
//				// Skip any blank or comment lines
//				if (line.length() == 0 || line.startsWith("%")) {
//					continue;
//				}
//				
//				String[] parts = line.split(":");				
//				Course course = new Course(Integer.parseInt(parts[0]), parts[1].trim());
//				courses.put(course.getId(), course);
//			}
//		} finally {
//			if (reader != null) {
//				reader.close();
//			}
//		}
//		System.out.format("discovered %d courses\n", courses.size());
//
//		// Load prerequisites
//		System.out.print("Loading prerequisites...");
//		reader = null;
//		try {
//			reader = new BufferedReader(new FileReader(prerequisitesPath));
//			String line;
//			while ((line = reader.readLine()) != null) {
//				line = line.trim();
//				// Skip any blank or comment lines
//				if (line.length() == 0 || line.startsWith("%")) {
//					continue;
//				}
//				
//				String[] parts = line.split(":");
//				int prereqId = Integer.parseInt(parts[0]);
//				int courseId = Integer.parseInt(parts[1]);
//				
//				Course course = courses.get(courseId);
//				course.setPrereqId(prereqId);
//			}
//		} finally {
//			if (reader != null) {
//				reader.close();
//			}
//		}
//		System.out.println("done");
//		
//		return courses;
//	}
//
//	public static Map<Integer, Term> loadTerms(String path)
//			throws IOException {
//
//		Map<Integer, Term> offerings = new HashMap<Integer, Term>();
//
//		System.out.print("Loading course offerings by term...");
//		BufferedReader reader = null;
//		int termId = 0;
//		try {
//			reader = new BufferedReader(new FileReader(path));
//			String line;
//			while ((line = reader.readLine()) != null) {
//				line = line.trim();
//				// Skip any blank or comment lines
//				if (line.length() == 0 || line.startsWith("%")) {
//					continue;
//				}
//				termId++; // starts with term 1
//				String[] parts = line.split("\\s*,\\s*");
//				List<Integer> courses = new ArrayList<Integer>();
//				for (String part : parts) {
//					courses.add(Integer.parseInt(part));
//				}
//				Term term = new Term(termId, courses);
//				offerings.put(termId, term);
//			}
//		} finally {
//			if (reader != null) {
//				reader.close();
//			}
//		}
//		System.out.println("loaded "+termId+" terms.");
//		return offerings;
//	}


}
