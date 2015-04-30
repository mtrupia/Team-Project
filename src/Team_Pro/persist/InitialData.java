package Team_Pro.persist;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Team_Pro.model.*;

public class InitialData {
	public static List<Comment> getComments() throws IOException, ParseException {
		List<Comment> commentList = new ArrayList<Comment>();
		ReadCSV readComment = new ReadCSV("comments.csv");
		try {
			while (true) {
				List<String> tuple = readComment.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Comment comment = new Comment();
				comment.setId(Integer.parseInt(i.next()));
				comment.setText(i.next());
				comment.setUserId(Integer.parseInt(i.next()));
				comment.setLikes(Integer.parseInt(i.next()));
				comment.setFlags(Integer.parseInt(i.next()));
				comment.setTag(i.next());
				comment.setRemoved(Integer.parseInt(i.next()));
				commentList.add(comment);
			}
			return commentList;
		} finally {
			readComment.close();
		}
	}
	
	public static List<Like> getLikes() throws IOException, ParseException {
		List<Like> likeList = new ArrayList<Like>();
		ReadCSV readLike = new ReadCSV("likes.csv");
		try {
			while (true) {
				List<String> tuple = readLike.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Like like = new Like();
				like.setId(Integer.parseInt(i.next()));
				like.setCommentId(Integer.parseInt(i.next()));
				like.setUserId(Integer.parseInt(i.next()));
				likeList.add(like);
			}
			return likeList;
		} finally {
			readLike.close();
		}
	}
	
	public static List<Flag> getFlags() throws IOException, ParseException {
		List<Flag> flagList = new ArrayList<Flag>();
		ReadCSV readFlag = new ReadCSV("flags.csv");
		try {
			while (true) {
				List<String> tuple = readFlag.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Flag flag = new Flag();
				flag.setId(Integer.parseInt(i.next()));
				flag.setCommentId(Integer.parseInt(i.next()));
				flag.setUserId(Integer.parseInt(i.next()));
				flagList.add(flag);
			}
			return flagList;
		} finally {
			readFlag.close();
		}
	}
	
	public static List<User> getUsers() throws IOException, ParseException {
		List<User> userList = new ArrayList<User>();
		ReadCSV readUser = new ReadCSV("users.csv");
		try {
			while (true) {
				List<String> tuple = readUser.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				User user = new User();
				user.setId(Integer.parseInt(i.next()));
				user.setFName(i.next());
				user.setLName(i.next());
				user.setUserName(i.next());
				user.setPassword(i.next());
				user.setLogged(Integer.parseInt(i.next()));
				user.setModded(Integer.parseInt(i.next()));
				userList.add(user);
			}
			return userList;
		} finally {
			readUser.close();
		}
	}
}
