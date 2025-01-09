package com.JDBCUserProject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDAO {
    
    // Create a new user
    public void createUser(User user) {
        String sql = "INSERT INTO users(name, email) VALUES(?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            handleError(e);
        }
    }



    // Get a user by ID
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            // Check if a user is found
            if (resultSet.next()) {
                return new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email"));
            } else {
                throw new UserNotFoundException("User with ID " + id + " does not exist.");
            }
        } catch (SQLException e) {
            handleError(e);
        }

        // Optional: Throw a fallback exception if something unexpected happens
        throw new RuntimeException("Failed to retrieve user with ID " + id);
    }


    // Get all users
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY id ASC";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email")));
            }
        } catch (SQLException e) {
            handleError(e);
        }
        return users;
    }



    // Get filtered users based on a search term
    public List<User> getFilteredUsers(String searchTerm) {
        List<User> users = getAllUsers(); // Get all users from the database

        // Stream through the users and filter based on the search term
        List<User> filteredUsers = users.stream()
                .filter(user -> user.getName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                                user.getEmail().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());

        // Check if the filtered list is empty
        if (filteredUsers.isEmpty()) {
            throw new NoMatchingUsersException("No users found matching the search term: " + searchTerm);
        }

        return filteredUsers;
    }

    
    

    // Update a user
    public void updateUser(User user) {
        String checkSql = "SELECT COUNT(*) FROM users WHERE id = ?";
        String updateSql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
        
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql);
             PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
             
            // Check if the user exists
            checkStatement.setInt(1, user.getId());
            try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (resultSet.next() && resultSet.getInt(1) == 0) {
                    throw new UserNotFoundException("User with ID " + user.getId() + " does not exist.");
                }
            }
           
            // Proceed to update the user
            updateStatement.setString(1, user.getName());
            updateStatement.setString(2, user.getEmail());
            updateStatement.setInt(3, user.getId());
            updateStatement.executeUpdate();
            
        } catch (SQLException e) {
            handleError(e);
        }
    }


    // Delete a user by ID
    public void deleteUser(int id) {
        String checkSql = "SELECT COUNT(*) FROM users WHERE id = ?";
        String deleteSql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql);
             PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {
             
            // Check if the user exists
            checkStatement.setInt(1, id);
            try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (resultSet.next() && resultSet.getInt(1) == 0) {
                    throw new UserNotFoundException("User with ID " + id + " does not exist.");
                }
            }
            
            // Proceed to delete the user
            deleteStatement.setInt(1, id);
            deleteStatement.executeUpdate();
            
        } catch (SQLException e) {
            handleError(e);
        }
    }

    
    
    
 // Custom exception for user not found
    class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
    

    // Handle database errors
    private void handleError(SQLException e) {
        System.err.println("Database operation failed: " + e.getMessage());
        e.printStackTrace();
    }
    
    // Custom exception for no matching users found
    class NoMatchingUsersException extends RuntimeException {
        public NoMatchingUsersException(String message) {
            super(message);
        }
    }
}
