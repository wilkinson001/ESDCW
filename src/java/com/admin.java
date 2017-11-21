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
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
public class admin extends HttpServlet {
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;");
            
            HttpSession session = request.getSession();
            System.out.println("Here");
            ServletContext sc = request.getServletContext();
            Connection con = (Connection)sc.getAttribute("connection");
            JdbcUserQry jdbc = new JdbcUserQry();
            jdbc.connect(con);
            
            //String uname = (String)session.getAttribute("user");
//            String pword = request.getParameter("password");
            //String qry = "Select * from ROOT.users where USERS.\"id\"='"+uname+"'";
            //String res;
            //try {
            //    res = jdbc.retrieve(qry);
            //    String type = jdbc.userType(uname);
                //PrintWriter out = response.getWriter();
//                if(res==true){
//                    request.setAttribute("user", uname);
//                    request.setAttribute("type", type);
//                    RequestDispatcher view = request.getRequestDispatcher("approveNewMembers.jsp");
//                    view.forward(request,response);
//                } else {
//                    out.println("Username or Password is Incorrect");
//                }

            
            RequestDispatcher view = request.getRequestDispatcher("admindashboard.jsp");
            view.forward(request,response);
            
            //} catch (SQLException ex) {
            //    Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            //}
            
    }
          @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}



