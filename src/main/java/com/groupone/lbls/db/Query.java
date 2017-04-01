package com.groupone.lbls.db;

import com.groupone.lbls.model.User;
import com.groupone.lbls.model.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Query {

    private static final String userTable = "user";

    public static User getUser(String username, String password) {
        PreparedStatement statement;
        String query = String.format("SELECT * FROM %s WHERE username=? AND password=?", userTable);

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

}
