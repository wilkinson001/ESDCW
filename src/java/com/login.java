package com;
import java.io.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Ollie
 */
public class login extends HttpServlet {
        @Override
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
            
            response.setContentType("text/html;");
            

            ServletContext sc = request.getServletContext();
            Connection con = (Connection)sc.getAttribute("connection");
            JdbcUserQry jdbc = new JdbcUserQry();
            jdbc.connect(con);
            
            String uname = request.getParameter("username");
            String pword = request.getParameter("password");
            String qry = "Select * from ROOT.users where USERS.\"id\"='"+uname+"' and USERS.\"password\"='"+pword+"'";
            boolean res;
            try {
                res = jdbc.login(qry);
                String type = jdbc.userType(uname);
                System.out.println(type);
                PrintWriter out = response.getWriter();
                if(res==true){
                    request.setAttribute("user", uname);
                    request.setAttribute("type", type);
                    if(type.equals("ADMIN")){
                        response.sendRedirect("admin.java");
                    }else {
                        response.sendRedirect("member.java");
                    }
                } else {
                    out.println("Username or Password is Incorrect");
                }
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}

