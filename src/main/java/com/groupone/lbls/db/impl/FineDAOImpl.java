package com.groupone.lbls.db.impl;

import com.groupone.lbls.db.FineDAO;
import com.groupone.lbls.db.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FineDAOImpl implements FineDAO {

    private static final String table = "fine";

    @Override
    public int getUsersFine(int userId) {
        PreparedStatement statement;
        String query = String.format("SELECT SUM(amount) FROM %s " +
                "WHERE user_id = ? AND amount > 0", table);

        try {
            statement = MySQL.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1, userId);

            statement.execute();
            ResultSet resultSet = statement.executeQuery();

            // check isEmpty
            if (!resultSet.next()) {
                return 0;
            }

            return resultSet.getInt("SUM(amount)");

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


}
