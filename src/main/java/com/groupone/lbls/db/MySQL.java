package com.groupone.lbls.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQL {
    private static MySQL instance;
    private static Connection connection;
    private String host;
    private String username;
    private String password;
    private String dbname;
    private String port = "3306";

    public static MySQL getInstance() {
        if (instance == null) {
            instance = new MySQL();
        }
        return instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            this.connect();
        }
        return connection;
    }

    public static void close() {
        if (connection == null) {
            return;
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void connect() {
        this.loadProperties();
        // verifyServerCertificate is set false for suppressing the warning.
        String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" +
                this.dbname + "?autoReconnect=true&verifyServerCertificate=false&useSSL=true";

        try {
            connection = DriverManager.getConnection(url, this.username, this.password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadProperties() {
        try {
            Properties properties = new Properties();

            properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));

            this.setHost(properties.getProperty("host"));
            this.setUsername(properties.getProperty("username"));
            this.setPassword(properties.getProperty("password"));
            this.setDbname(properties.getProperty("dbname"));
            this.setPort(properties.getProperty("port"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setHost(String host) {
        this.host = host;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setDbname(String dbname) {
        this.dbname = dbname;
    }

    private void setPort(String port) {
        if (port != null) {
            this.port = port;
        }
    }
}
