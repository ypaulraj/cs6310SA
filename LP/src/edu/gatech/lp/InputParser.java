package edu.gatech.lp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputParser {

	public static List<List<Integer>> readFile() throws FileNotFoundException {

		List<List<Integer>> studentSchedule = new ArrayList<List<Integer>>();
		Scanner fileScanner;
		
		fileScanner = new Scanner(new File("/home/ubuntu/workspace/LP/LP/src/student_schedule.txt"));

		String line = null;
		while (fileScanner.hasNextLine()) {

			line = fileScanner.nextLine();
			
			if (line.startsWith("%")) {
				continue;
			}

			line = line.replace(".", "");
			line = line.trim();

			String[] tokens = line.split(" ");

			List<Integer> list = new ArrayList<Integer>();
			for (String token : tokens) {
				if (token.equals(""))
					continue;
				list.add(Integer.parseInt(token));
			}
			if (!list.isEmpty()) {
				studentSchedule.add(list);
			}

			
		}
		
		return studentSchedule;
	}
	
}
