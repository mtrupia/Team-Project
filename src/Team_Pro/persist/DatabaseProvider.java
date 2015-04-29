package Team_Pro.persist;

public class DatabaseProvider {
	private static IDatabase instance;
	
	public static IDatabase getInstance() {
		instance = new SqliteDatabase();
		return instance;
	}
}
