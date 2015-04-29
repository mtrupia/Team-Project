package Team_Pro.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Team_Pro.model.Like;
import Team_Pro.persist.DatabaseProvider;

public class LikeTest {
	private Like like;
	private List<Like> dblike;
	
	@Before
	public void setUp() {
		like = new Like(-1, -2, -2);
		dblike = DatabaseProvider.getInstance().getLikes();
		
		// print likes
				for (int i = 0; i < dblike.size(); i++) {
					System.out.printf("%s %s %s", dblike.get(i).getId(), dblike.get(i).getCommentId(), dblike.get(i).getUserId());
					System.out.println();
				}
	}
	
	@Test
	public void testSetId() {
		assertEquals(-1, like.getId());
		like.setId(0);
		assertEquals(0, like.getId());
	}
	
	public void testSetUserId() {
		assertEquals(-2, like.getUserId());
		like.setUserId(1);
		assertEquals(1, like.getUserId());
	}
	
	public void testSetCommentId() {
		assertEquals(-2, like.getCommentId());
		like.setCommentId(2);
		assertEquals(2, like.getCommentId());
	}
}
