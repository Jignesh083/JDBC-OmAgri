package com.JDBCProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO  {
		public void addEmployee(Employee employee) throws SQLException {
			String sql = "INSERT INTO employees(name,email,salary) VALUES (?,?,?)";
			Connection conn = DatabaseConnection.getConnection();
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, employee.getName());
			pstm.setString(2,employee.getEmail());
			pstm.setFloat(3, employee.getSalary());
			pstm.executeUpdate();
		}
		
		public Employee getEmployeeId(int id) throws SQLException {
			String sql = "SELECT * FROM employees WHERE id=?";
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			
			if(rs.next()) {
				return new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getFloat("salary"));
			}
			return null;
		}
		
		
		
		public List<Employee> getAllEmployees() throws SQLException{
			String sql = "SELECT * FROM employees";
			List<Employee> employees = new ArrayList<>();
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				employees.add(new Employee(
	                    rs.getInt("id"),
	                    rs.getString("name"),
	                    rs.getString("email"),
	                    rs.getFloat("salary")
	                ));
			}
			return employees;
			
		}
		
		
		public void updateEmployee(Employee employee) throws SQLException {
			String sql = "UPDATE employees SET name=?,email=?,salary=? WHERE id=?";
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, employee.getName());
			pstm.setString(2, employee.getEmail());
			pstm.setFloat(3, employee.getSalary());
			pstm.setInt(4, employee.getId());
			pstm.executeUpdate();
		}
		
		
		
		public void deleteEmployee(int id) throws SQLException {
			String sql = "DELETE FROM employees WHERE id = ?";
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.executeUpdate();
		}
}		



