package com.JDBCStudent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CP {
	
	static Connection conn;
	
	public static Connection createConn() throws ClassNotFoundException, SQLException {
		//load driver
		Class.forName("org.postgresql.Driver");
		
		//create the connection
		
		String user = "postgres";
		String password = "root";
		String url="jdbc:postgresql://localhost:5432/students";
		
		conn = DriverManager.getConnection(url,user,password);
		
		return conn;
	}
}
