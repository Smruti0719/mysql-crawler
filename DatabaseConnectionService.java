package com.crawler.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.json.JSONObject;

import com.crawler.config.DbConfigLoader;

public class DatabaseConnectionService {

    public Connection getConnection() throws Exception {

        JSONObject config = DbConfigLoader.loadConfig();

        String url = config.getString("url");
        String username = config.getString("username");
        String password = config.getString("password");

    
        Class.forName("com.mysql.cj.jdbc.Driver");

       
        Connection connection = DriverManager.getConnection(url, username, password);

       

        return connection;
    }

    
    
}

