package Team_Pro.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import Team_Pro.model.*;

public class SqliteDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load sqlite driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;
	
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}
	
	public List<Comment> getComments() {
		return executeTransaction(new Transaction<List<Comment>>() {
			@Override
			public List<Comment> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from comments"
					);
					
					List<Comment> result = new ArrayList<Comment>();
					
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						Comment comment = new Comment();
						int index = 1;
						comment.setId(resultSet.getInt(index++));
						comment.setText(resultSet.getString(index++));
						comment.setUserId(resultSet.getInt(index++));
						comment.setLikes(resultSet.getInt(index++));
						comment.setFlags(resultSet.getInt(index++));
						comment.setTag(resultSet.getString(index++));
						comment.setRemoved(resultSet.getInt(index++));
						result.add(comment);
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public List<Like> getLikes() {
		return executeTransaction(new Transaction<List<Like>>() {
			@Override
			public List<Like> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from likes"
					);
					
					List<Like> result = new ArrayList<Like>();
					
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						Like like = new Like();
						int index = 1;
						like.setId(resultSet.getInt(index++));
						like.setCommentId(resultSet.getInt(index++));
						like.setUserId(resultSet.getInt(index++));
						result.add(like);
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public List<User> getUsers() {
		return executeTransaction(new Transaction<List<User>>() {
			@Override
			public List<User> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from users"
					);
					
					List<User> result = new ArrayList<User>();
					
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						User user = new User();
						int index = 1;
						user.setId(resultSet.getInt(index++));
						user.setFName(resultSet.getString(index++));
						user.setLName(resultSet.getString(index++));
						user.setUserName(resultSet.getString(index++));
						user.setPassword(resultSet.getString(index++));
						user.setLogged(resultSet.getInt(index++));
						user.setModded(resultSet.getInt(index++));
						result.add(user);
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public void addComment(final String text, final int userId, final String tag) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"insert into comments (text, user_Id, likes, flags, tag, removed) values (?, ?, ?, ?, ?, ?)"
					);
					
					stmt.setString(1, text);
					stmt.setInt(2, userId);
					stmt.setInt(3, 0);
					stmt.setInt(4, 0);
					stmt.setString(5, tag);
					stmt.setInt(6, 0);
					
					stmt.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public void addLike(final int commentId, final int userId) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"insert into likes (comment_Id, user_Id) values (?, ?)"
					);
					
					stmt.setInt(1, commentId);
					stmt.setInt(2, userId);
					
					stmt.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public void addUser(final String fname, final String lname, final String username, final String password) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"insert into users (fname, lname, username, password, logged, modded) values (?, ?, ?, ?, ?, ?)"
					);
					
					stmt.setString(1, fname);
					stmt.setString(2, lname);
					stmt.setString(3, username);
					stmt.setString(4, password);
					stmt.setInt(5, 0);
					stmt.setInt(6, 0);
					
					stmt.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public void deleteComment(final int id) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"delete from comments where id=?"
					);
					
					stmt.setInt(1, id);
					
					stmt.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public void deleteLike(final int id) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"delete from likes where id=?"
					);
					
					stmt.setInt(1, id);
					
					stmt.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public void deleteUser(final int id) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"delete from users where id=?"
					);
					
					stmt.setInt(1, id);
					
					stmt.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public void updateComment(final int id, final int likes, final int flags, final int removed) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"update comments set likes=?, flags=?, removed=? where id=?"
					);
					
					stmt.setInt(1, likes);
					stmt.setInt(2, flags);
					stmt.setInt(3, removed);
					stmt.setInt(4, id);
					
					stmt.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public void updateUser(final int id, final String password, final int logged, final int modded) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"update users set password=?, logged=?, modded=? where id=?"
					);
					
					stmt.setString(1, password);
					stmt.setInt(2, logged);
					stmt.setInt(3, modded);
					stmt.setInt(4, id);
					
					stmt.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public void backUpData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				Statement backup = null;
				
				try {
					backup = conn.createStatement();
					backup.executeUpdate("backup database to  disk='C:/Users/Public/backup.bak'");
					
					return true;
				} finally {
					DBUtil.closeQuietly(backup);
				}
			}
		});
	}
	
	public void restoreData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				Statement restore = null;
				
				try {
					restore = conn.createStatement();
					restore.executeUpdate("restore database from disk='C:/Users/Public/backup.bak'");
					
					return true;
				} finally {
					DBUtil.closeQuietly(restore);
				}
			}
		});
	}
	
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"create table comments (" +
							"    id integer primary key," +
							"    text varchar(5000)," +
							"    user_Id integer," +
							"    likes integer," +
							"    flags integer," +
							"    tag varchar(100)," +
							"    removed integer" +
							")");
					stmt1.executeUpdate();
					
					stmt2 = conn.prepareStatement(
							"create table likes (" +
									"    id integer primary key," +
									"    comment_Id integer," +
									"    user_Id integer" +
									")");
					stmt2.executeUpdate();
					
					stmt3 = conn.prepareStatement(
							"create table users (" +
									"    id integer primary key," +
									"    fname varchar(75)," +
									"	 lname varchar(75)," +	
									"    username varchar(75)," +
									"    password varchar(75)," +
									"    logged integer," +
									"    modded integer" +
									")");
					stmt3.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
				}
			}
		});
	}
	
	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
		
		// Set autocommit to false to allow multiple the execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<Comment> commentList;
				List<Like> likeList;
				List<User> userList;
				
				try {
					commentList = InitialData.getComments();
					likeList = InitialData.getLikes();
					userList = InitialData.getUsers();
				} catch (IOException | ParseException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertComment = null;
				PreparedStatement insertLike = null;
				PreparedStatement insertUser = null;

				try {
					insertComment = conn.prepareStatement("insert into comments values (?, ?, ?, ?, ?, ?, ?)");
					for (Comment comment : commentList) {
						insertComment.setInt(1, comment.getId());
						insertComment.setString(2, comment.getText());
						insertComment.setInt(3, comment.getUserId());
						insertComment.setInt(4, comment.getLikes());
						insertComment.setInt(5, comment.getFlags());
						insertComment.setString(6, comment.getTag());
						insertComment.setInt(7, comment.getRemoved());
						insertComment.addBatch();
					}
					insertComment.executeBatch();
					
					insertLike = conn.prepareStatement("insert into likes values (?, ?, ?)");
					for (Like like : likeList) {
						insertLike.setInt(1, like.getId());
						insertLike.setInt(2, like.getCommentId());
						insertLike.setInt(3, like.getUserId());
						insertLike.addBatch();
					}
					insertLike.executeBatch();
					
					insertUser = conn.prepareStatement("insert into users values (?, ?, ?, ?, ?, ?, ?)");
					for (User user : userList) {
						insertUser.setInt(1, user.getId());
						insertUser.setString(2, user.getFName());
						insertUser.setString(3, user.getLName());
						insertUser.setString(4, user.getUserName());
						insertUser.setString(5, user.getPassword());
						insertUser.setInt(6, user.getLogged());
						insertUser.setInt(7, user.getModded());
						insertUser.addBatch();
					}
					insertUser.executeBatch();
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertComment);
					DBUtil.closeQuietly(insertLike);
					DBUtil.closeQuietly(insertUser);
				}
			}
		});
	}
	
	// The main method creates the database tables and loads the initial data.
		public static void main(String[] args) throws IOException {
			System.out.println("Creating tables...");
			SqliteDatabase db = new SqliteDatabase();
			db.createTables();
			
			System.out.println("Loading initial data...");
			db.loadInitialData();
			
			System.out.println("Success!");
		}
}