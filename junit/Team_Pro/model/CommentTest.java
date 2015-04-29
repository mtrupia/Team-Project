package Team_Pro.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Team_Pro.model.Comment;
import Team_Pro.persist.DatabaseProvider;

public class CommentTest {
	private List<Comment> comment;
	
	@Before
	public void setUp() {
		try {
			comment = DatabaseProvider.getInstance().getComments();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// print posts
		for (int i = 0; i < comment.size(); i++) {
			System.out.printf("%s %s %s %s", comment.get(i).getId(), comment.get(i).getText(), comment.get(i).getUserId(), comment.get(i).getRemoved());
			System.out.println();
		}
		
	}
	
	@Test
	public void testSetId() {
		assertEquals(0, comment.get(0).getId());
		comment.get(0).setId(0);
		assertEquals(0, comment.get(0).getId());
	}
	
	public void testSetComment() {
		assertEquals("This is just a test post to make sure this can go on forever and so forth, please ignore until ready to delete my man!", comment.get(0).getText());
		comment.get(0).setText("This is just a test post to make sure this can go on forever and so forth, please ignore until ready to delete my man!");
		assertEquals("This is just a test post to make sure this can go on forever and so forth, please ignore until ready to delete my man!", comment.get(0).getText());
	}
	
	public void testSetLikes() {
		assertEquals(1, comment.get(0).getLikes());
		comment.get(0).setLikes(1);
		assertEquals(1, comment.get(0).getLikes());
	}

	public void testSetTag() {
		assertEquals("ADMIN", comment.get(0).getTag());
		comment.get(0).setTag("ADMIN");
		assertEquals("ADMIN", comment.get(0).getTag());
	}

	public void testSetFlags() {
		assertEquals(0, comment.get(0).getFlags());
		comment.get(0).setFlags(0);
		assertEquals(0, comment.get(0).getFlags());
	}

	public void testSetRemoved() {
		assertEquals(0, comment.get(0).getRemoved());
		comment.get(0).setRemoved(0);
		assertEquals(0, comment.get(0).getRemoved());
	}

	public void testSetUserId() {
		assertEquals(0, comment.get(0).getUserId());
		comment.get(0).setUserId(0);
		assertEquals(0, comment.get(0).getUserId());
	}
}
