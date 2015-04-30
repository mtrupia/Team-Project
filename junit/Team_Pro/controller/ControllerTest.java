package Team_Pro.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Team_Pro.controller.Controller;
import Team_Pro.model.*;

public class ControllerTest {
	private Controller cont;
	
	@Before
	public void setUp() throws Exception {
		cont = new Controller();
	}
	@Test
	public void testDeletePostAndNewPost() throws Exception {
		cont.deletePost(3);
		List<Comment> feed = cont.allPosts();
		assertEquals(7, feed.size());
		
		cont.post("Welcome Everyone", 4);
		
		feed = cont.allPosts();
		assertEquals(8, feed.size());
	}
	
	@Test
	public void testFlagAndLike() throws Exception {
		for (int i = 0; i < 20; i++){
			//cont.flag(10);
		}
		List<Comment> feed = cont.modPosts(0);
		assertEquals(4, feed.size());
		
		cont.like(1, 4);
		feed = cont.likePosts(1);
		assertEquals(4, feed.size());
	}
	/*
	@Test
	public void testLogged() {
		String mes = cont.logged("Michael", "4321");
		assertEquals("Account not found!", mes);
		mes = cont.logged("Michael", "1234");
		assertEquals("Welcome Michael", mes);
	}
	
	@Test
	public void testCreateAccount() {
		String mes = cont.createAccount("Michael", "1234");
		assertEquals("User is already taken", mes);
		
		mes = cont.createAccount("Levi", "1234");
		assertEquals("User created!", mes);
		cont.post("I'm new", "new", 2);
		List<String> feed = cont.userPosts(2);
		assertEquals("I'm new", feed.get(0));
	}

	@Test
	public void testModded() {
		cont.modded(1);
		List<String> feed = cont.modPosts(1);
		assertEquals(1, feed.size());
		assertEquals("Dumb!", feed.get(0));
	}

	@Test
	public void testAllPosts() {
		List<String> feed = cont.allPosts();
		assertEquals(2, feed.size());
		assertEquals("Hi Yall", feed.get(0));
		assertEquals("Good day", feed.get(1));
	}

	@Test
	public void testUserPosts() {
		List<String> feed = cont.userPosts(0);
		assertEquals(1, feed.size());
		assertEquals("Hi Yall", feed.get(0));
		feed = cont.userPosts(1);
		assertEquals(1, feed.size());
		assertEquals("Good day", feed.get(0));
	}

	@Test
	public void testLikePosts() {
		List<String> feed = cont.likePosts(0);
		assertEquals(1, feed.size());
		assertEquals("Good day", feed.get(0));
	}

	@Test
	public void testSearchPosts() {
		List<String> feed = cont.searchPosts("Hi");
		assertEquals(2, feed.size());
		assertEquals("Hi Yall", feed.get(0));
		assertEquals("Good day", feed.get(1));
	}

	@Test
	public void testModPosts() {
		List<String> feed = cont.modPosts(0);
		assertEquals(1, feed.size());
		assertEquals("Dumb!", feed.get(0));
		feed = cont.modPosts(1);
		assertEquals(0, feed.size());
	}
	*/
}