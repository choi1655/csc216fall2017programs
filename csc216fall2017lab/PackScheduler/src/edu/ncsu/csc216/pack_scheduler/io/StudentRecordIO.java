package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * StudentRecordIO provides static methods that support reading in student
 * records from a file and writing student records to a file
 * 
 * @author Dominic Brown
 * @author Hamzah Chaudhry
 * @author Anna Chernikov
 */

public class StudentRecordIO {

	/**
	 * Reads Students from a file, and returns the list of Student objects in an
	 * SortedList
	 * 
	 * @throws FileNotFoundException
	 *             if the filename inputed is not readable
	 * @param fileName
	 *            The name of the file
	 * @return an array list of student records
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner record = new Scanner(new FileInputStream(new File(fileName)));
		SortedList<Student> studentRecords = new SortedList<>();

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

				Student student = new Student(firstName, lastName, id, email, password, maxCredits);
				studentRecords.add(student);
			} catch (Exception e) {
				// do nothing
			}

			line.close();
		}

		record.close();

		return studentRecords;
	}

	/**
	 * Takes a filename and an array list of students and writes the list of
	 * students to a file.
	 * 
	 * @param fileName
	 *            The name of the file
	 * @param studentDirectory
	 *            array list containing all students
	 * @throws IOException
	 *             if the filename is invalid or unreadable
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream file = new PrintStream(new File(fileName));
		for (int i = 0; i < studentDirectory.size(); i++) {
			file.println(studentDirectory.get(i).toString());
		}
		file.close();
	}

}
