/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class admin extends HttpServlet {
        @Override
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
            
            response.setContentType("text/html;");
            

            ServletContext sc = request.getServletContext();
            Connection con = (Connection)sc.getAttribute("connection");
            JdbcUserQry jdbc = new JdbcUserQry();
            jdbc.connect(con);
            
            String uname = request.getParameter("username");
//            String pword = request.getParameter("password");
            String qry = "Select * from ROOT.users where USERS.\"id\"='"+uname+"'";
            String res;
            try {
                res = jdbc.retrieve(qry);
                String type = jdbc.userType(uname);
                PrintWriter out = response.getWriter();
//                if(res==true){
//                    request.setAttribute("user", uname);
//                    request.setAttribute("type", type);
//                    RequestDispatcher view = request.getRequestDispatcher("approveNewMembers.jsp");
//                    view.forward(request,response);
//                } else {
//                    out.println("Username or Password is Incorrect");
//                }
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}



