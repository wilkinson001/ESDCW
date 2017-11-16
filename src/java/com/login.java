package com;
import java.io.*;
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
            
            String uname = request.getParameter("username");
            String pword = request.getParameter("password");
            JdbcUserQry jdbc = new JdbcUserQry();
            String qry = "Select * from users where username='"+uname+"' and password='"+pword+"'";
            String res;
            try {
                res = jdbc.retrieve(qry);
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}