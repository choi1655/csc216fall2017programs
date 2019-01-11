package edu.ncsu.csc216.pack_scheduler.io;

import java.io.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;

/**
 * Class to provides I/O methods for the Course POJO
 * 
 * @author Dominic Brown
 */
public class CourseRecordIO {

	/**
	 * Writes the given list of Courses to the specified file
	 * 
	 * @param fileName
	 *            The name of the file to write to
	 * @param courses
	 *            An ArrayList of Course to write to file
	 * @throws IOException
	 *             If the filename is invalid
	 */
	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < courses.size(); i++) {
			fileWriter.println(courses.get(i).toString());
		}

		fileWriter.close();
	}

	/**
	 * Reads course records from a file and generates a list of valid Courses.
	 * Any invalid Courses are ignored. If the file to read cannot be found or
	 * the permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName
	 *            file to read Course records from
	 * @return a list of valid Courses
	 * @throws FileNotFoundException
	 *             if the file cannot be found or read
	 */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Course> courses = new SortedList<Course>();
		while (fileReader.hasNextLine()) {
			try {
				Course course = readCourse(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < courses.size(); i++) {
					Course c = courses.get(i);
					if (course.getName().equals(c.getName()) && course.getSection().equals(c.getSection())) {
						// it's a duplicate
						duplicate = true;
					}
				}
				if (!duplicate) {
					courses.add(course);
				}
			} catch (IllegalArgumentException e) {
				// skip the line
			}
		}
		fileReader.close();
		return courses;
	}

	/**
	 * Reads in the course object from the passed in line from the text file.
	 * Checks if there is matching faculty with the course - if so adds the
	 * faculty to the course. Returns the created the course object.
	 * 
	 * @param line
	 *            the line containing the course record from the file.
	 * @return the created course object.
	 */
	private static Course readCourse(String line) {
		Scanner lineScanner = new Scanner(line);
		lineScanner.useDelimiter(",");
		try {
			String name = lineScanner.next();
			String title = lineScanner.next();
			String section = lineScanner.next();
			int credits = lineScanner.nextInt();
			String instructorId = lineScanner.next();

			int enrollmentCap = lineScanner.nextInt();
			String meetingDays = lineScanner.next();
			if (meetingDays.equals("A")) {
				if (lineScanner.hasNext()) {
					lineScanner.close();
					throw new IllegalArgumentException();
				}
				lineScanner.close();
				Course c = new Course(name, title, section, credits, null, enrollmentCap, meetingDays);

				Faculty f = RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId);

				if (f != null && !f.isOverloaded()) {
					f.getSchedule().addCourseToSchedule(c);
				}

				return c;
			}
			int startTime = lineScanner.nextInt();
			int endTime = lineScanner.nextInt();

			lineScanner.close();
			Course c = new Course(name, title, section, credits, null, enrollmentCap, meetingDays, startTime, endTime);

			Faculty f = RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId);

			if (f != null && !f.isOverloaded()) {
				f.getSchedule().addCourseToSchedule(c);
			}

			return c;

		} catch (InputMismatchException e) {
			lineScanner.close();
			throw new IllegalArgumentException();
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}
	}
}
