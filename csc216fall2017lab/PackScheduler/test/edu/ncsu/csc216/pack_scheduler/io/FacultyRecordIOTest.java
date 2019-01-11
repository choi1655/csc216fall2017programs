/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * JUnit test cases for FacultyRecordIO.java
 * @author mchoi@ncsu.edu
 * @author jballie@ncsu.edu
 * @author krpatel7@ncsu.edu
 */
public class FacultyRecordIOTest {

	private String validFaculty0 = "Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,pw,2";
	private String validFaculty1 = "Fiona,Meadows,fmeadow,pharetra.sed@et.org,pw,3";
	private String validFaculty2 = "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,pw,1";
	private String validFaculty3 = "Halla,Aguirre,haguirr,Fusce.dolor.quam@amalesuadaid.net,pw,3";
	private String validFaculty4 = "Kevyn,Patel,kpatel,risus@pellentesque.ca,pw,1";
	private String validFaculty5 = "Elton,Briggs,ebriggs,arcu.ac@ipsumsodalespurus.edu,pw,3";
	private String validFaculty6 = "Norman,Brady,nbrady,pede.nonummy@elitfermentum.co.uk,pw,1";
	private String validFaculty7 = "Lacey,Walls,lwalls,nascetur.ridiculus.mus@fermentum.net,pw,2";
	
	private String [] validFaculty = {validFaculty0, validFaculty1, validFaculty2, validFaculty3, validFaculty4, validFaculty5,
	        validFaculty6, validFaculty7};
	
	private String hashPW;
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * Setup function for before test
	 */
	@Before
	public void setUp() {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = new String(digest.digest());
	        for (int i = 0; i < validFaculty.length; i++) {
	            validFaculty[i] = validFaculty[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO#readFacultyRecords(java.lang.String)}.
	 */
	@Test
	public void testReadFacultyRecords() {
		try {
			LinkedList<Faculty> facultyList = FacultyRecordIO.readFacultyRecords("test-files/faculty_records.txt");
			SortedList<String> newList = new SortedList<>();
			for (int i = 0; i < facultyList.size(); i++) {
				newList.add(facultyList.get(i).toString());
			}
			for (int i = 0; i < newList.size(); i++) {
				assertNotEquals(newList.indexOf(validFaculty[i]), -1);
			}
			LinkedList<Faculty> invalidFaculty = FacultyRecordIO.readFacultyRecords("test-files/invalid_faculty_records.txt");
			assertEquals(invalidFaculty.size(), 0);
		} catch (FileNotFoundException e) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO#writeFacultyRecords(java.lang.String, edu.ncsu.csc216.pack_scheduler.util.LinkedList)}.
	 */
	@Test
	public void testWriteFacultyRecords() {
		try {
			LinkedList<Faculty> facultyList = FacultyRecordIO.readFacultyRecords("test-files/faculty_records.txt");
			FacultyRecordIO.writeFacultyRecords("test-files/test_writingStudentRecords", facultyList);
		} catch (IOException e) {
			fail();
		}
	}
}
