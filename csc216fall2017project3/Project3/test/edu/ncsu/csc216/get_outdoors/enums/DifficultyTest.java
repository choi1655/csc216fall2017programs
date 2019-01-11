/**
 * 
 */
package edu.ncsu.csc216.get_outdoors.enums;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for Difficulty.
 * 
 * @author jballie
 * @author mchoi
 *
 */
public class DifficultyTest {

	/**
	 * Tests the Difficulty enum class.
	 */
	@Test
	public void test() {
		Difficulty easy = Difficulty.EASY;
		Difficulty moderate = Difficulty.MODERATE;
		Difficulty challenging = Difficulty.CHALLENGING;
		Difficulty extreme = Difficulty.EXTREME;
		Difficulty difficult = Difficulty.DIFFICULT;
		Difficulty veryDifficult = Difficulty.VERY_DIFFICULT;

		assertEquals(Difficulty.EASY, easy);
		assertEquals(Difficulty.MODERATE, moderate);
		assertEquals(Difficulty.CHALLENGING, challenging);
		assertEquals(Difficulty.EXTREME, extreme);
		assertEquals(Difficulty.DIFFICULT, difficult);
		assertEquals(Difficulty.VERY_DIFFICULT, veryDifficult);
		
		Difficulty.valueOf(Difficulty.EASY.toString());

	}

}
