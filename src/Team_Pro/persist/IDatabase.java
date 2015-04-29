package Team_Pro.persist;

import java.util.List;

import Team_Pro.model.*;

public interface IDatabase {
	public List<Comment> getComments();
	public List<Like> getLikes();
	public List<User> getUsers();
	
	public void addComment(final String text, final int userId, final String tag);
	public void addLike(final int commentId, final int userId);
	public void addUser(final String fname, final String lname, final String username, final String password);
	
	public void deleteComment(final int id);
	public void deleteLike(final int id);
	public void deleteUser(final int id);
	
	public void updateComment(final int id, final int likes, final int flags, final int removed);
	public void updateUser(final int id, final String password, final int logged, final int modded);
	
	public void backUpData();
	public void restoreData();
}