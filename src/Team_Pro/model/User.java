package Team_Pro.model;

public class User {
	private int id, logged, modded;
	private String fname, lname, username, password;
	
	public User() {
		
	}
	
	public User(int id, String fname, String lname, String username, String password, int logged, int modded){
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
		this.logged = logged;
		this.modded = modded;
	}
	
	public void setId(int id){ this.id = id; }
	public int getId(){ return id; }
	
	public void setFName(String fname){ this.fname = fname; }
	public String getFName(){ return fname; }
	
	public void setLName(String lname){ this.lname = lname; }
	public String getLName(){ return lname; }
	
	public void setUserName(String username){ this.username = username; }
	public String getUserName(){ return username; }
	
	public void setPassword(String password){this.password = password; }
	public String getPassword(){ return password; }
	
	public void setLogged(int logged){ this.logged = logged; }
	public int getLogged(){ return logged; }
	
	public void setModded(int modded){ this.modded = modded; }
	public int getModded(){ return modded; }
}
