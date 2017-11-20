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
 * @author Ashley
 */
public class member extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;");

        ServletContext sc = request.getServletContext();
        Connection con = (Connection) sc.getAttribute("connection");
        JdbcUserQry jdbc = new JdbcUserQry();
        jdbc.connect(con);

        String uname = request.getParameter("username");
        String bal = "0";
        String qry = "Select balance from ROOT.members where members.\"id\"='" + uname;
        String qryClaim = "Select * from ROOT.claims where claims.\"mem_id\"='" + uname;
        
        String res,res2;
        try {
            res = jdbc.retrieve(qry);
            res2 = jdbc.retrieve(qryClaim);
//                String type = jdbc.userType(uname);
            PrintWriter out = response.getWriter();
            
                request.setAttribute("user", uname);
                request.setAttribute("balance", res);
                request.setAttribute("claims", res2);
//                    request.setAttribute("type", type);
                    RequestDispatcher view = request.getRequestDispatcher("memberDashboard.jsp");
                    view.forward(request,response);
            
        } catch (SQLException ex) {
            Logger.getLogger(member.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet member</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet member at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
