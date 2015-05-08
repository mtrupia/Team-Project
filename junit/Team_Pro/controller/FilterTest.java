package Team_Pro.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class FilterTest {
	String me;
	
	@Before
	public void setUp() {
		me = "";
	}
	
	@Test
	public void testFilterOneWord() {
		me = Filter.FilterComment("hate");
		assertEquals("strongly dislike", me);
	}
	
	@Test
	public void testFilterSting() {
		me = Filter.FilterComment("I hate ass and shit");
		assertEquals("I strongly dislike booty and poop", me);
	}
	
	@Test
	public void testFilterStingWithPunctuation() {
		me = Filter.FilterComment("I hate ass, and shit!");
		assertEquals("I strongly dislike booty, and poop!", me);
	}
	
	@Test
	public void testFilterStingWithTwoLines() {
		me = Filter.FilterComment("I hate ass, and\n shit!");
		assertEquals("I strongly dislike booty, and\n poop!", me);
	}
	
	@Test
	public void testFilterStringHard() {
		me = Filter.FilterComment("I hate hate\nass\nand,\nsometime I hate\nshit?");
		assertEquals("I strongly dislike strongly dislike\nbooty\nand,\nsometime I strongly dislike\npoop?", me);
	}
}