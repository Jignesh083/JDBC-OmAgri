package com.JDBCUserProject;


import java.util.List;
import java.util.Scanner;

public class UserService {

    private final UserDAO userDAO = new UserDAO();
    private final Scanner scanner = new Scanner(System.in);
    
    
    public void addUser() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        if (name == null || name.trim().isEmpty() || email == null || email.trim().isEmpty()) {
	        throw new IllegalArgumentException("Name and email cannot be null or empty.");
	    }
        
        User user = new User(0, name, email);  //auto generated id
        userDAO.createUser(user);
        System.out.println("User added successfully.");
    }

    
    
    public void getUser() {
        System.out.print("Enter user ID to fetch: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        User user = userDAO.getUserById(id);
        if (user != null) {
            System.out.println("User found: " + user);
        } else {
            System.out.println("User not found.");
        }
    }

    public void updateUser() {
        System.out.print("Enter user ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new email: ");
        String email = scanner.nextLine();
        
        if (name == null || name.trim().isEmpty() || email == null || email.trim().isEmpty()) {
	        throw new IllegalArgumentException("Name and email cannot be null or empty.");
	    }
        User user = new User(id, name, email);
        userDAO.updateUser(user);
        System.out.println("User updated successfully.");
    }

    public void deleteUser() {
        System.out.print("Enter user ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        userDAO.deleteUser(id);
        System.out.println("User deleted successfully.");
    }

    public void listUsers() {
        List<User> users = userDAO.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            users.forEach(System.out::println);
        }
    }
    
    
    public void filterUser() {
    	UserDAO userDAO = new UserDAO();
    	List<User> filteredUsers = userDAO.getFilteredUsers(scanner.nextLine());
    	for (User user : filteredUsers) {
    	    System.out.println(user.getName() + " - " + user.getEmail());
    	}
    }
    

    public void showMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add User");
            System.out.println("2. Get User by ID");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. List All Users");
            System.out.println("6. Filter");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    getUser();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 5:
                    listUsers();
                    break;
                case 6:
                	filterUser();
                	break;
                case 7:
                    System.out.println("Exiting application.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

