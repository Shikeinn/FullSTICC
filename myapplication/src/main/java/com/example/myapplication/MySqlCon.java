package com.example.myapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlCon {
    // init database constants
    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://ls-987da79aacee373eb184b2d6ed1d74a0af3f20e5.cf2swf0uegzw.us-east-1.rds.amazonaws.com:3306";
    private static final String USERNAME = "dbmasteruser";
    private static final String PASSWORD = "ZRO+Cs?{J0JvL!wP?e_nm}J=TYE9m::A";
    private static final String MAX_POOL = "250";

    // init connection object
    private static Connection connection;
    // init properties object
    private static Properties properties;

    // create properties
    private static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
            properties.setProperty("MaxPooledStatements", MAX_POOL);
        }
        return properties;
    }

    // connect database
    public static Connection connect() {
        if (connection == null) {
            try {
		System.out.println("hello world");
                Class.forName(DATABASE_DRIVER);
		System.out.println("hello world");
                connection = DriverManager.getConnection(DATABASE_URL, getProperties());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    // disconnect database
	public static void disconnect() {
		if (connection != null) {
        	    try {
        	        connection.close();
        	        connection = null;
        	    } catch (SQLException e) {
        	        e.printStackTrace();
        	    }
       		 }
	}
	public static void main(String[] args)
		{
			connect();
			disconnect();
	}
}
