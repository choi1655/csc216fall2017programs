/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * FacultyRecordIO provides static methods that support reading in faculty
 * records from a file and writing faculty records to a file
 * 
 * @author mchoi@ncsu.edu
 * @author jballie@ncsu.edu
 */
public class FacultyRecordIO {

	/**
	 * Reads faculty records from a file passed in. Throws FileNotFoundException if
	 * file is not found.
	 * 
	 * @param fileName
	 *            file to read
	 * @return facultyRecords records of faculty
	 * @throws FileNotFoundException
	 *             thrown if file is not found
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		Scanner record = new Scanner(new FileInputStream(new File(fileName)));
		LinkedList<Faculty> facultyRecords = new LinkedList<Faculty>();

		while (record.hasNextLine()) {
			@SuppressWarnings("resource")
			Scanner line = new Scanner(record.nextLine()).useDelimiter(",");
			try {
				String firstName = line.next();
				String lastName = line.next();
				String id = line.next();
				String email = line.next();
				String password = line.next();
				int maxCredits = line.nextInt();

				Faculty faculty = new Faculty(firstName, lastName, id, email, password, maxCredits);
				facultyRecords.add(faculty);
			} catch (Exception e) {
				// do nothing
			}

			line.close();
		}

		record.close();

		return facultyRecords;
	}

	/**
	 * Writes faculty records to a file that is passed in.
	 * 
	 * @param fileName
	 *            file to write in
	 * @param list
	 *            list of faculty to take in
	 * @throws IOException
	 *             thrown for exception in input/output
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> list) throws IOException {
		PrintStream file = new PrintStream(new File(fileName));
		for (int i = 0; i < list.size(); i++) {
			file.println(list.get(i).toString());
		}
		file.close();
	}
}
