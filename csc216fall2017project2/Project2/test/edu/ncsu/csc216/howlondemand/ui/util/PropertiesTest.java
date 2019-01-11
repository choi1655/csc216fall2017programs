/**
 * 
 */
package edu.ncsu.csc216.howlondemand.ui.util;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

/**
 * JUnit test case for Properties.java
 * @author mchoi@ncsu.edu
 * @author cnlee2@ncsu.edu
 * @version 11012017
 */
public class PropertiesTest {
	
	/**
	 * JUnit test case for Properties.java
	 */
	@Test
	public void test() {
		assertEquals(Properties.LIGHTBLUE, Color.decode("#67A5bb"));
	}

}
