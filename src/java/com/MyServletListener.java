/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author owilkinson
 */
public class MyServletListener implements ServletContextListener {

    
    private Connection con;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        String db = sc.getInitParameter("db-name");
        try {
            // You will need to explicitly load this driver in a web app
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/"+db, "root", "root");

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: " + e);
        }
        sc.setAttribute("connection", con);
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try{
            con.close();
        }catch(SQLException e){}
    }
}
