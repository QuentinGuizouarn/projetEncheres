package dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {
	
	public static DataSource dataSource;
	
	static {
		Context cont;
		try {
			cont = new InitialContext();
			dataSource = (DataSource)cont.lookup("java:comp/env/jdbc/encheres");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

}
