package com.JDBCProject;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		EmployeeDAO dao = new EmployeeDAO();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\n--- Employee Management System ---");
			System.out.println("1. Add Employee");
			System.out.println("2. Get Employee by ID");
			System.out.println("3. Get All Employees");
			System.out.println("4. Update Employee");
			System.out.println("5. Delete Employee");
			System.out.println("6. Exit");
			System.out.print("Choose an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume the newline character

			try {
				switch (choice) {
				case 1:
					System.out.print("Enter name: ");
					String name = scanner.nextLine();
					System.out.print("Enter email: ");
					String email = scanner.nextLine();
					System.out.print("Enter salary: ");
					float salary = scanner.nextFloat();
					scanner.nextLine(); // Consume the newline character

					Employee newEmployee = new Employee(name, email, salary);
					dao.addEmployee(newEmployee);
					System.out.println("Employee added successfully!");
					break;

				case 2:
					System.out.print("Enter employee ID: ");
					int id = scanner.nextInt();
					scanner.nextLine(); // Consume the newline character

					Employee fetched = dao.getEmployeeId(id);
					if (fetched != null) {
						System.out.println("Employee Details:");
						System.out.println("ID: " + fetched.getId());
						System.out.println("Name: " + fetched.getName());
						System.out.println("Email: " + fetched.getEmail());
						System.out.println("Salary: " + fetched.getSalary());
					} else {
						System.out.println("Employee not found!");
					}
					break;

				case 3:
					System.out.println("All Employees:");
					dao.getAllEmployees().forEach(emp -> {
						System.out.println("ID: " + emp.getId() + ", Name: " + emp.getName() + ", Email: "
								+ emp.getEmail() + ", Salary: " + emp.getSalary());
					});
					break;

				case 4:
					System.out.print("Enter employee ID to update: ");
					int updateId = scanner.nextInt();
					scanner.nextLine(); // Consume the newline character

					Employee employeeToUpdate = dao.getEmployeeId(updateId);
					if (employeeToUpdate != null) {
						System.out.print("Enter new name: ");
						employeeToUpdate.setName(scanner.nextLine());
						System.out.print("Enter new email: ");
						employeeToUpdate.setEmail(scanner.nextLine());
						System.out.print("Enter new salary: ");
						employeeToUpdate.setSalary(scanner.nextFloat());
						scanner.nextLine(); // Consume the newline character

						dao.updateEmployee(employeeToUpdate);
						System.out.println("Employee updated successfully!");
					} else {
						System.out.println("Employee not found!");
					}
					break;

				case 5:
					System.out.print("Enter employee ID to delete: ");
					int deleteId = scanner.nextInt();
					scanner.nextLine(); // Consume the newline character

					dao.deleteEmployee(deleteId);
					System.out.println("Employee deleted successfully!");
					break;

				case 6:
					System.out.println("Exiting...");
					scanner.close();
					return;

				default:
					System.out.println("Invalid option. Please try again.");
					break;
				}
			} catch (Exception e) {
				System.out.println("An error occurred: " + e.getMessage());
				e.printStackTrace();
			}
		}

	}

}











//
//Create a Connection: Connect to your database using DriverManager.getConnection().
//
//Define the SQL Query with Placeholders (?): Use placeholders for any dynamic values you intend to set at runtime.
//
//Prepare the Query: Use the Connection.prepareStatement() method to create a PreparedStatement object.
//
//Set Parameter Values: Use the setter methods (like setInt, setString) to provide values for the placeholders.
//
//Execute the Query: Use executeQuery() for SELECT queries or executeUpdate() for INSERT, UPDATE, DELETE queries.
//
//Process the Results (if applicable): Iterate through the ResultSet for SELECT queries.
//
//Close Resources: Close the PreparedStatement, ResultSet, and Connection.
