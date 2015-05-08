package Team_Pro.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Team_Pro.model.Like;

public class LikeTest {
	private Like me;
	
	@Before
	public void setUp() {
		me = new Like();
	}
	
	@Test
	public void testSetAll() {
		me = new Like(0, 0, 0);
		assertEquals(0, me.getId());
		assertEquals(0, me.getCommentId());
		assertEquals(0, me.getUserId());
	}
	
	@Test
	public void testSetId() {
		me.setId(0);
		assertEquals(0, me.getId());
	}
	
	@Test
	public void testSetUserId() {
		me.setUserId(0);
		assertEquals(0, me.getUserId());
	}
	
	@Test
	public void testSetCommentId() {
		me.setCommentId(0);
		assertEquals(0, me.getCommentId());
	}
}
