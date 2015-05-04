package Team_Pro.controller;

import java.util.List;
import java.util.ArrayList;

import Team_Pro.model.*;
import Team_Pro.persist.DatabaseProvider;
import Team_Pro.persist.IDatabase;

public class Controller {
	private List<User> users;
	private List<Comment> comments;
	private List<Like> likes;
	private List<Flag> flags;
	IDatabase db;
	
	// Initiate Main class
	public Controller() throws Exception{
		db = DatabaseProvider.getInstance();
		comments = db.getComments();
		likes = db.getLikes();
		users = db.getUsers();
		flags = db.getFlags();
	}
	
	// create user account; check name; add to users list.
	public String createAccount(String fname, String lname, String username, String password) throws Exception{
		// Name and password base cases satisfied
		if (username.isEmpty()) {
			return "Please enter a valid username!";
		} 
		if (username.length() < 6) {
			return "Username must be at least 6 characters long!";
		}
		if (password.isEmpty()) {
			return "Please enter a valid password!";
		} 
		if (password.length() < 6) {
			return "Password must be at least 6 characters long!";
		}
		int capCount = 0;
		int numCount = 0;
		int lowerCount = 0;
		for (int i = 0; i < password.length(); i++) {
			if (Character.isUpperCase(password.charAt(i))) {
				capCount++;
			}
			if (Character.isLowerCase(password.charAt(i))) {
				lowerCount++;
			}
			if (Character.isDigit(password.charAt(i))) {
				numCount++;
			}
		}
		if (capCount == 0 || lowerCount == 0 || numCount == 0) {
			return "Password must contain at least 1 upper, lower, and digit character!";
		}
		
		// If name is already taken, return false
		for (int i = 0; i < users.size(); i++){
			if (username.equals(users.get(i).getUserName())){
				return "Account already exists";
			}
		}
		
		// Else, create user with given name and pass; add to users list; return true.
		db.addUser(fname, lname, username, password);
		users = db.getUsers();
		return "Account created! Please login!";
	}
	
	//logout current user
	public void logout(int id) throws Exception{
		for (int i = 0; i < users.size(); i++) {
			if (id == users.get(i).getId()) {
				db.updateUser(id, users.get(i).getPassword(), 0, users.get(i).getModded());
				users = db.getUsers();
			}
		}
	}

	// LogIn user, return its id
	public User login(String username, String password) throws Exception{
		for (int i = 0; i < users.size(); i++){
			if (username.equals(users.get(i).getUserName())){
				if (password.equals(users.get(i).getPassword())) {
					User user = users.get(i);
					db.updateUser(users.get(i).getId(), password, 1, users.get(i).getModded());
					return user;
				} else {
					User badPass = new User();
					badPass.setId(-2);
					return badPass;
				}
			}
		}
		User badName = new User();
		badName.setId(-1);
		return badName;
	}
	
	// Add a new post to the posts list
	public void post(String text, int userId) throws Exception{
		// Create new post
		if (text.isEmpty()) {
			return;
		}
		text = Filter.FilterComment(text + " "); //Adds space at the end so the filter can detect curses at the end of a comment.
		db.addComment(text, userId);
		comments = db.getComments();
	}
	
	// Return list with all comments by all users that are active!
	public List<Comment> allPosts(){
		List<Comment> feed = new ArrayList<Comment>();
		// all active comments
		for (int i = 0; i < comments.size(); i++){
			Comment comment = comments.get(i);
			// active?
			if (comment.getRemoved() == 0){
				feed.add(comment);
			}
		}
		return feed;
	}
	
	// Return list with all comments by user that are active!
	public List<Comment> userPosts(int userId){
		List<Comment> feed = new ArrayList<Comment>();
		// all active comments
		List<Comment> allPosts = allPosts();
		// all user comments
		for (int i = 0; i < allPosts.size(); i++){
			if (userId == allPosts.get(i).getUserId()){
				feed.add(allPosts.get(i));
			}
		}
		return feed;
	}
	
	
	// Add a new like to likes list; update Comment like counter
	public void like(int userId, int commentId){
		for (Comment posts : userPosts(userId)) {
			if (posts.getId() == commentId) {
				return;
			}
		}
		for (Comment likers : likePosts(userId)) {
			if (likers.getId() == commentId) {
				return;
			}
		}
		
		// Create new like
		db.addLike(commentId, userId);
		likes = db.getLikes();
		
		// Update comment like
		for (int i = 0; i < comments.size(); i++){
			if (commentId == comments.get(i).getId()){
				db.updateComment(commentId, comments.get(i).getLikes()+1, comments.get(i).getFlags(), comments.get(i).getRemoved());
				comments = db.getComments();
			}
		}
	}
	
	
	
	//Return list with all comments that user likes
	public List<Comment> likePosts(int userId){
		List<Comment> feed = new ArrayList<Comment>();
		// all active comments
		List<Comment> allPosts = allPosts();
		// What user liked
		for (int i = 0; i < likes.size(); i++){
			if (userId == likes.get(i).getUserId()){
				// What user's liked post is 
				for (int j = 0; j < allPosts.size(); j++){
					if (likes.get(i).getCommentId() == allPosts.get(j).getId()){
						feed.add(allPosts.get(j));
					}
				}
			}
		}
		return feed;
	} 
	
	// flag a comment, if flags = 20, remove it
	public void flag(int userId, int commentId) throws Exception{
		for (Comment posts : userPosts(userId)) {
			if (posts.getId() == commentId) {
				return;
			}
		}
		for (Flag flag : db.getFlags()) {
			if (flag.getUserId() == userId) {
				if (flag.getCommentId() == commentId) {
					return;
				}
			}
		}
		
		// Create new flag
		db.addFlag(commentId, userId);
		flags = db.getFlags();
		
		// Update comment flag
		Comment post = comments.get(commentId);
		db.updateComment(commentId+1, post.getLikes(), post.getFlags()+1, post.getRemoved());
		comments = db.getComments();
		post = comments.get(commentId);
		// too many flags?
		if (post.getFlags() >= 20) {
			db.updateComment(commentId+1, post.getLikes(), post.getFlags(), 1);
			comments = db.getComments();
		}
	}
	
	// delete a comment
	public void deletePost(int commentId) throws Exception{
		Comment post = comments.get(commentId);
		db.updateComment(commentId+1, post.getLikes(), post.getFlags(), 1);
		comments = db.getComments();
	}
	
	// Search comments with specific tag
	public List<Comment> searchPosts(String search) throws Exception{
		List<Comment> feed = new ArrayList<Comment>();
		// all active comments
		List<Comment> allPosts = allPosts();
		// with this tag
		for (int i = 0; i < allPosts.size(); i ++){
			if (allPosts.get(i).getText().toLowerCase().contains(search.toLowerCase())){
				feed.add(allPosts.get(i));
			}
		}
		return feed;
	}
	
	// make user a mod; make mod a user...
	public void modded(int userId) throws Exception{
		if (users.get(userId).getModded() != 1) {
			db.updateUser(userId, users.get(userId).getPassword(), users.get(userId).getLogged(), 1);
		} else {
			db.updateUser(userId, users.get(userId).getPassword(), users.get(userId).getLogged(), 0);
		}
		users = db.getUsers();
	}
	
	// all removed posts for mods
	public List<Comment> modPosts(int userId){
		List<Comment> feed = new ArrayList<Comment>();
		if (users.get(userId).getModded() == 1) {
			for (int j = 0; j < comments.size(); j++){
				if (comments.get(j).getFlags() > 5 && (comments.get(j).getFlags() != 21)){
					feed.add(comments.get(j));
				}
			}
		}
		return feed;
	}
	public void increment(int commentID){
		
		Comment post = comments.get(commentID);
		db.updateComment(commentID, post.getLikes(), 21,  1);
		comments = db.getComments();
		
		
		
	}
	public void addtoList(int commentID){
		Comment post = comments.get(commentID);
		db.updateComment(commentID, post.getLikes(), 0,  0);
		comments = db.getComments();
	}
	public List<User> getUsers(){
		List<User> userlist = new ArrayList<User>();
		for(int k = 0; k < users.size(); k++){
			if(users.get(k).getModded() == 0){
				userlist.add(users.get(k));
		}
		}
		return userlist;
	}
}
