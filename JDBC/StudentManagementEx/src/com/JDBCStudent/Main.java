package com.JDBCStudent;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println("Welcome to student Management App");
		Scanner sc = new Scanner(System.in);
		Scanner s = new Scanner(System.in);
//		String input = sc.nextLine();
		
		
//		String createTable = "create table student"
		
		while(true) {
			System.out.println("ENTER 1 to ADD Student");
			System.out.println("ENTER 2 to Delete Student");
			System.out.println("ENTER 3 to Display Student");
			System.out.println("Enter 4 to Exit App");
			int choice = Integer.parseInt(sc.nextLine());
			
			if(choice==1) {
				//add 
//				System.out.println("Enter Student id: ");
//				int id = s.nextInt();
				
				System.out.println("Enter Student Name: ");
				String name = sc.nextLine();
				
				System.out.println("Enter Student Phone: ");
				String phone = sc.nextLine();
				
				System.out.println("Enter Student City: ");
				String city = sc.nextLine();
				
				Student st = new Student(name,phone,city);
				boolean answer = StudentDao.insertStudentToDB(st);
				if(answer) {
					System.out.println("Student is added Successfully.");
				}
				else {
					System.out.println("Something went wrong try again.");
				}
				System.out.println(st);
			}
			else if(choice == 2) {
				//delete
				System.out.println("Enter student id to delete: ");
				int userId = Integer.parseInt(sc.nextLine());
				boolean f = StudentDao.deleteStudent(userId);
				if(f) {
					System.out.println("Deleted successfully...");
				}
				else {
					System.out.println("Something went wrong.....");
				}
			}
			else if(choice==3) {
				//display
				StudentDao.showAllDetails();
			}
			else if(choice==4) {
				break;
			}
			else {
				
			}
			
		}
		
		System.out.println("Thank you for using my Application....");
		System.out.println("see you soon...bye..bye...");

	}

}
