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
 * @author Ollie
 */
public class approve_claim extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            
            String claim_id = (String) request.getParameter("claim");
            String reject_claim_id = (String) request.getParameter("reject_claim");
            ServletContext sc = request.getServletContext();
            Connection con = (Connection) sc.getAttribute("connection");
            JdbcUserQry jdbc = new JdbcUserQry();
            jdbc.connect(con);
            
            if(claim_id!=null){
                jdbc.approveClaim(claim_id);
            }
            
            if(reject_claim_id!=null){
                jdbc.rejectClaim(reject_claim_id);
            }
            
            
            String query = "select a.*, b.\"total\" from CLAIMS a JOIN (select CLAIMS.\"mem_id\", count(CLAIMS.\"mem_id\") as \"total\" from CLAIMS where (YEAR(CURRENT_DATE)-YEAR(CLAIMS.\"date\"))=0 and (MONTH(CURRENT_DATE)-MONTH(CLAIMS.\"date\"))<12 group by CLAIMS.\"mem_id\") b on a.\"mem_id\" = b.\"mem_id\" where a.\"status\"='APPLIED' and (YEAR(CURRENT_DATE)-YEAR(a.\"date\"))=0 and (MONTH(CURRENT_DATE)-MONTH(a.\"date\"))<12 order by a.\"id\"\n";
            //add count of claims in past year to query
            String data = "";
            try {
                data = jdbc.retrieve(query);
            } catch (SQLException ex) {
                Logger.getLogger(approve_member.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            session.setAttribute("data", data);
            RequestDispatcher view = request.getRequestDispatcher("approve_claim.jsp");
            view.forward(request,response);
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
