/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.UUID;
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
 * @author Ollie
 */
public class register extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;");
            

            ServletContext sc = request.getServletContext();
            Connection con = (Connection)sc.getAttribute("connection");
            JdbcUserQry jdbc = new JdbcUserQry();
            jdbc.connect(con);
            
            String fname = request.getParameter("fname");
            String sname = request.getParameter("sname");
            String address = request.getParameter("add");
            String dob = request.getParameter("dob");
            
            String name = fname.concat(" "+sname);
            
            int uname_counter = 1;
            String uname = fname.substring(0,1)+"-"+sname;
            boolean i = false;
            while(i == false){
                if(jdbc.exists(uname)){
                    uname = fname.substring(0,1)+uname_counter+"-"+sname;
                    uname_counter++;
                } else {
                    i = true;
                }
            }
            uname=uname.toLowerCase();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dor = df.format(new Date());
            String uuid = UUID.randomUUID().toString();
            String pword = uuid.replace("-", "");
            pword = pword.substring(0,8);
            
            System.out.println(name);
            System.out.println(address);
            System.out.println(pword);
            System.out.println(uname);
            System.out.println(dob);
            System.out.println(dor);
            String qry = "insert into ROOT.MEMBERS values ('"+uname+"','"+name+"','"+address+"','"+java.sql.Date.valueOf(dob)+"','"+java.sql.Date.valueOf(dor)+"','APPLIED',10.00)";
            String qry2 = "insert into ROOT.USERS values ('"+uname+"','"+pword+"','APPLIED')";
            int res1 = jdbc.insert(qry);
            int res2 = jdbc.insert(qry2);
            if(res1==-1 || res2==-1){
                System.out.println("Error in registration");
                request.setAttribute("error","error in registration");
                RequestDispatcher view = request.getRequestDispatcher("register.jsp");
                view.forward(request,response);
            }else {
                request.setAttribute("user", uname);
                request.setAttribute("pword", pword);
                RequestDispatcher view = request.getRequestDispatcher("registered.jsp");
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
