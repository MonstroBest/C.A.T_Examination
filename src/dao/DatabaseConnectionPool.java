package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public interface DatabaseConnectionPool {
	
	public void init();
	
	public Connection getConnection();
	
	public void close(Connection connection,ResultSet set,Statement statement);
	
}
