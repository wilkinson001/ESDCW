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
import javax.servlet.http.*;

/**
 *
 * @author Ashley
 */
public class member extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;");

        HttpSession session = request.getSession();
        
        ServletContext sc = request.getServletContext();
        Connection con = (Connection) sc.getAttribute("connection");
        JdbcUserQry jdbc = new JdbcUserQry();
        jdbc.connect(con);

        String uname = (String) session.getAttribute("user");

        String bal = "0";
        String qryClaim = "Select claims.\"id\", claims.\"date\", claims.\"rationale\", claims.\"status\", claims.\"amount\" from ROOT.claims where claims.\"mem_id\"='" + uname+"'";
        double res;
        String res2;
        try {
            res = jdbc.balance(uname);
            res2 = jdbc.retrieve(qryClaim);
            System.out.println(uname+" "+res+" "+res2);
            PrintWriter out = response.getWriter();
            session.setAttribute("user", uname);
            session.setAttribute("balance", res);
            session.setAttribute("claims", res2);
            RequestDispatcher view = request.getRequestDispatcher("memberDashboard.jsp");
            view.forward(request,response);

        } catch (SQLException ex) {
            Logger.getLogger(member.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
