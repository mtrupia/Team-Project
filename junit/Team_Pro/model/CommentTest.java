package Team_Pro.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Team_Pro.model.Comment;

public class CommentTest {
	private Comment me;
	
	@Before
	public void setUp() {
		me = new Comment();
	}
	
	@Test
	public void testSetAll() {
		me = new Comment(0, "hi", 1, 0, 0, 0, "hi");
		assertEquals(0, me.getId());
		assertEquals("hi", me.getText());
		assertEquals(1, me.getLikes());
		assertEquals(0, me.getFlags());
		assertEquals(0, me.getRemoved());
		assertEquals(0, me.getUserId());
		assertEquals("hi", me.getTag());
	}
	
	@Test
	public void testSetId() {
		me.setId(0);
		assertEquals(0, me.getId());
	}
	
	@Test
	public void testSetComment() {
		me.setText("hi");
		assertEquals("hi", me.getText());
	}
	
	@Test
	public void testSetLikes() {
		me.setLikes(1);
		assertEquals(1, me.getLikes());
	}
	
	@Test
	public void testSetTag() {
		me.setTag("hi");
		assertEquals("hi", me.getTag());
	}

	@Test
	public void testSetFlags() {
		me.setFlags(0);
		assertEquals(0, me.getFlags());
	}

	@Test
	public void testSetRemoved() {
		me.setRemoved(0);
		assertEquals(0, me.getRemoved());
	}

	@Test
	public void testSetUserId() {
		me.setUserId(0);
		assertEquals(0, me.getUserId());
	}
}
