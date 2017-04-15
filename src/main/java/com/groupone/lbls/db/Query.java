package com.groupone.lbls.db;

import com.groupone.lbls.model.User;
import com.groupone.lbls.model.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Query {

    private static final String userTable = "user";

    public static User getUser(String username) {
        PreparedStatement statement;
        String query = String.format("SELECT * FROM %s WHERE username = ?", userTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            // check isEmpty
            if (!resultSet.next()) {
                return null;
            }

            int id = resultSet.getInt("id");
            String email = resultSet.getString("email");
            UserRole userRole = UserRole.fromInt(resultSet.getInt("role"));
            return new User(id, email, username, userRole);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static User getUser(String username, String password) {
        PreparedStatement statement;
        String query = String.format("SELECT * FROM %s WHERE " +
                "username = ? AND password = ?", userTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            // check isEmpty
            if (!resultSet.next()) {
                return null;
            }

            int id = resultSet.getInt("id");
            String email = resultSet.getString("email");
            UserRole userRole = UserRole.fromInt(resultSet.getInt("role"));
            return new User(id, email, username, userRole);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean addUser(String email, String username,
                                  String password, String userRole) {
        PreparedStatement statement;
        String query = String.format("INSERT INTO %s " +
                "(email, username, password, role) " +
                "values (?, ?, ?, ?)", userTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setString(4, userRole);

            statement.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateUser(String id, String email, String username,
                                     String password, String userRole) {
        PreparedStatement statement;
        String query = String.format("UPDATE %s SET " +
                "email = IFNULL(?, email), " +
                "username = IFNULL(?, username), " +
                "password = IFNULL(?, password), " +
                "role = IFNULL(?, role) " +
                "WHERE id = ?", userTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setString(4, userRole);
            statement.setString(5, id);

            statement.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteUser(String id) {
        PreparedStatement statement;
        String query = String.format("DELETE FROM %s WHERE id = ?", userTable);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setString(1, id);

            statement.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
