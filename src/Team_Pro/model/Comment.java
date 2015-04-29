package Team_Pro.model;

public class Comment {
	private int id, likes, flags, userId, removed;
	private String text, tag;
	
	public Comment() {
		
	}
	
	public Comment(int id, String text, int likes, int flags, int removed, int userId, String tag) {
		this.id = id;
		this.likes = likes;
		this.flags = flags;
		this.text = text;
		this.tag = tag;
		this.removed = removed;
		this.userId = userId;
	}
	
	public void setId(int id){ this.id = id; }
	public int getId(){ return id; }
	
	public void setText(String text){ this.text = text; }
	public String getText(){ return text; }
	
	public void setLikes(int likes){ this.likes = likes; }
	public int getLikes(){ return likes; }
	
	public void setTag(String tag){ this.tag = tag; }
	public String getTag(){ return tag; }
	
	public void setFlags(int flags){ this.flags = flags; }
	public int getFlags(){ return flags; }
	
	public void setRemoved(int removed){ this.removed = removed; }
	public int getRemoved(){ return removed; }
	
	public void setUserId(int userId){ this.userId = userId; }
	public int getUserId(){ return userId; }
}