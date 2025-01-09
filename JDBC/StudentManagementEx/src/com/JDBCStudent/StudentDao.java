package com.JDBCStudent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDao {
	public static boolean insertStudentToDB(Student st) throws ClassNotFoundException, SQLException {

		boolean flag = false;

		// jdbc code...
		Connection conn = CP.createConn();
		String q = "insert into student(sname,sphone,scity) values (?,?,?)";
		// prepared statement
		PreparedStatement pstmt = conn.prepareStatement(q);

		// set the value of parameter
//		pstmt.setInt(1, st.getStudentId());
		pstmt.setString(1, st.getStudentName());
		pstmt.setString(2, st.getStudentPhone());
		pstmt.setString(3, st.getStudentCity());

		// execute
		pstmt.executeUpdate();

		flag = true;

		return flag;
	}

	public static boolean deleteStudent(int userId) throws SQLException, ClassNotFoundException {
		boolean flag = false;

		// jdbc code...
		Connection conn = CP.createConn();
		String q = "DELETE FROM student WHERE sId=?";
		// prepared statement
		PreparedStatement pstmt = conn.prepareStatement(q);

		// set the value of parameter
		pstmt.setInt(1,userId);
//execute
		pstmt.executeUpdate();

		flag = true;

		return flag;

	}

	public static boolean showAllDetails() throws SQLException, ClassNotFoundException {
		 
		boolean flag = false;

		// jdbc code...
		Connection conn = CP.createConn();
		String q = "select * from student";
		Statement stmt = conn.createStatement();
		
		ResultSet set = stmt.executeQuery(q);
		
		while(set.next()) {
			int id=set.getInt(1);
			String name = set.getString(2);
			String phone = set.getString(3);
			String city = set.getString(4);
			
			System.out.println("Id: "+id);
			System.out.println("Name: "+name);
			System.out.println("Phone: "+phone);
			System.out.println("City: "+city);
			
		}
		
		flag = true;

		return flag;
		
	}
}
