package Team_Pro.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Team_Pro.model.User;
import Team_Pro.persist.DatabaseProvider;

public class UserTest {
	private List<User> users;
	
	@Before
	public void setUp() {
		try {
			users = DatabaseProvider.getInstance().getUsers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// print accounts
		for (int i = 0; i < users.size(); i++) {
			System.out.printf(users.get(i).getId() + " " + users.get(i).getFName() + " " + users.get(i).getPassword());
			System.out.println();
		}
		
	}
	
	@Test
	public void testId() {
		assertEquals(0, users.get(0).getId());
		users.get(0).setId(0);
		assertEquals(0, users.get(0).getId());
	}
	
	public void testFName() {
		assertEquals("Admin", users.get(0).getFName());
		users.get(0).setFName("Admin");
		assertEquals("Admin", users.get(0).getFName());
	}
	
	public void testLName() {
		assertEquals("Admin", users.get(0).getLName());
		users.get(0).setLName("Admin");
		assertEquals("Admin", users.get(0).getLName());
	}
	
	public void testUserName() {
		assertEquals("Admin", users.get(0).getUserName());
		users.get(0).setUserName("Admin");
		assertEquals("Admin", users.get(0).getUserName());
	}
	
	public void testPassword() {
		assertEquals("Admin", users.get(0).getPassword());
		users.get(0).setPassword("Admin");
		assertEquals("Admin", users.get(0).getPassword());
	}
	
	public void testLogged() {
		assertEquals(0, users.get(0).getLogged());
		users.get(0).setLogged(0);
		assertEquals(0, users.get(0).getLogged());
	}
	
	public void testSetMod() {
		assertEquals(1, users.get(0).getModded());
		users.get(0).setModded(1);
		assertEquals(1, users.get(0).getModded());
	}
}
