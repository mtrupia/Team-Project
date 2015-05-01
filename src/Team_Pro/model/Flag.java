package Team_Pro.model;

public class Flag {
	private int id, userId, commentId;
	
	public Flag(){
	}
	
	public Flag(int id, int userId, int commentId) {
		this.id = id;
		this.userId = userId;
		this.commentId = commentId;
	}
	
	public void setId(int id){ this.id = id; }
	public int getId(){ return id; }
	
	public void setUserId(int userId){ this.userId = userId; }
	public int getUserId(){ return userId; }
	
	public void setCommentId(int commentId){ this.commentId = commentId; }
	public int getCommentId(){ return commentId; }
}