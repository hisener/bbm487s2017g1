package com.groupone.lbls.db.impl;

import com.groupone.lbls.db.MySQL;
import com.groupone.lbls.db.UserDAO;
import com.groupone.lbls.model.User;
import com.groupone.lbls.model.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static final String table = "user";

    @Override
    public List<User> getAllUsers() {
        PreparedStatement statement;
        String query = String.format("SELECT * FROM %s", table);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            List<User> userList = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String username = resultSet.getString("username");
                UserRole userRole = UserRole.fromInt(resultSet.getInt("role"));

                User user = new User(id, email, username, userRole);

                userList.add(user);
            }

            return userList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
