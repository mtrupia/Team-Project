package Team_Pro.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Team_Pro.model.User;

public class UserTest {
	private User me;
	
	@Before
	public void setUp() {
		me = new User();
	}
	
	@Test
	public void testAll() {
		//User(int id, String fname, String lname, String username, String password, int logged, int modded);
		me = new User(0, "Mike", "T", "mtrupia", "m1234", 0, 1);
		assertEquals(0, me.getId());
		assertEquals("Mike", me.getFName());
		assertEquals("T", me.getLName());
		assertEquals("mtrupia", me.getUserName());
		assertEquals("m1234", me.getPassword());
		assertEquals(0, me.getLogged());
		assertEquals(1, me.getModded());
	}
	
	@Test
	public void testId() {
		me.setId(0);
		assertEquals(0, me.getId());
	}
	
	@Test
	public void testFName() {
		me.setFName("Mike");
		assertEquals("Mike", me.getFName());
	}
	
	@Test
	public void testLName() {
		me.setLName("T");
		assertEquals("T", me.getLName());
	}
	
	@Test
	public void testUserName() {
		me.setUserName("mtrupia");
		assertEquals("mtrupia", me.getUserName());
	}
	
	@Test
	public void testPassword() {
		me.setPassword("m1234");
		assertEquals("m1234", me.getPassword());
	}
	
	@Test
	public void testLogged() {
		me.setLogged(0);
		assertEquals(0, me.getLogged());
	}
	
	@Test
	public void testSetMod() {
		me.setModded(1);
		assertEquals(1, me.getModded());
	}
}
