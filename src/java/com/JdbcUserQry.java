/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import static java.sql.Types.NULL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author me-aydin
 */
public class JdbcUserQry {
    
    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;
    String query = null;
    
    
    public JdbcUserQry(String query){
        this.query = query;
    }

    public JdbcUserQry() {
        this.query = "";
    }
    
    public void connect(Connection con){
       connection = con;
    }
    
    private ArrayList rsToList() throws SQLException {
        ArrayList aList = new ArrayList();

        int cols = rs.getMetaData().getColumnCount();
        while (rs.next()) { 
          String[] s = new String[cols];
          for (int i = 1; i <= cols; i++) {
            s[i-1] = rs.getString(i);
          } 
          aList.add(s);
        } // while    
        return aList;
    } //rsToList
 
    private String makeHtmlTable(ArrayList list) {
        StringBuilder b = new StringBuilder();
        String[] row;
        b.append("<table border=\"3\">");
        for (Object s : list) {
          b.append("<tr>");
          row = (String[]) s;
            for (String row1 : row) {
                b.append("<td>");
                b.append(row1);
                b.append("</td>");
            }
          b.append("</tr>\n");
        } // for
        b.append("</table>");
        return b.toString();
    }//makeHtmlTable
    
    private String makeTable(ArrayList list) {
        StringBuilder b = new StringBuilder();
        String[] row;
        b.append("<br>================================<br>");
        for (Object s : list) {
          b.append("\n");
          row = (String[]) s;
            for (String row1 : row) {
                b.append("\t");
                b.append(String.format("%-12s",row1));
                b.append("\t");
            }//for
         // b.append("\n");
        } // for
        b.append("\n");
        b.append("<br>================================");
        return b.toString();
    }//makeTable
  
    public void select(String query){
        Statement statement = null;
        
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            //statement.close();
        }
        catch(SQLException e) {
            System.out.println("error in: "+query+" : "+e);
        }
    }
    
    public int insert(String qry){
        Statement statement = null;
        int res = 0;
        try {
            statement = connection.createStatement();
            res = statement.executeUpdate(qry);
            //statement.close();
        }
        catch(SQLException e) {
            System.out.println(e);
            res=-1;
        }
        return res;
    }
    
    public double balance(String uname){
        double bal=0;
        Statement statement = null;
        String qry = "Select MEMBERS.\"balance\" from ROOT.members where members.\"id\"='" + uname+"'";
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(qry);
            if(rs.next()){
                bal=rs.getDouble(1);
            }
        }
        catch(SQLException e) {
            System.out.println(e);
        }
        
        return bal;
    }
    
    public String retrieve(String query) throws SQLException {
        String results="";
        select(query);

        return makeHtmlTable(rsToList());//results;
    }
    
    
    public boolean login(String query) throws SQLException{
        Statement statement = null;
        boolean login=false;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
        }
        catch(SQLException e) {
            System.out.println("login error: "+e);
        }
        
        if(rs.next()==true){
            login=true;
        }
        
        return login;
    }
    
    public String userType(String user) throws SQLException{
        String type="";
        select("Select USERS.\"status\" from ROOT.USERS where USERS.\"id\"='"+user+"' group by USERS.\"status\"");
        if(rs.next()){
            type = rs.getString(1);
            System.out.println(type);
        }
        return type;
    }

    
    public boolean exists(String user) {
        boolean bool = false;
        user=user.toLowerCase();
        try  {
            select("select USERS.\"id\" from ROOT.users where USERS.\"id\"='"+user+"'");
            if(rs.next()) {
                System.out.println(user+" exits in the DB");         
                bool = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(JdbcUserQry.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }
    
    public void approve(String user){
        String qry = "update MEMBERS set MEMBERS.\"status\"='APPROVED' where MEMBERS.\"id\"='"+user+"'";
        String qry2 = "update USERS set USERS.\"status\"=\"APPROVED\" where USERS.\"id\"='"+user+"'";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(qry);
            statement = connection.createStatement();
            statement.executeUpdate(qry2);
        }
        catch(SQLException e) {
            System.out.println("application error: "+qry+" : "+e);
        }
        
    }
    
    public void approveClaim(String claim_id){
        String qry = "update CLAIMS set CLAIMS.\"status\"='APPROVED' where CLAIMS.\"id\"='"+claim_id+"'";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(qry);
        }
        catch(SQLException e) {
            System.out.println("approve claim error: "+qry+" : "+e);
        }
        
    }
    
    public void rejectClaim(String claim_id){
        String qry = "update CLAIMS set CLAIMS.\"status\"='DENIED' where CLAIMS.\"id\"="+claim_id+"";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(qry);
        }
        catch(SQLException e) {
            System.out.println("reject claim error: "+qry+" : "+e);
        }
        
    }
    
    public double annualCharge() throws SQLException{
        double charge=0;
        String qry="select SUM(CLAIMS.\"amount\") from CLAIMS where (YEAR(CURRENT_DATE)-YEAR(CLAIMS.\"date\"))=0 and (MONTH(CURRENT_DATE)-MONTH(CLAIMS.\"date\"))<12";
        select(qry);
        if(rs.next()){
            charge = rs.getDouble(1);
            System.out.println(charge);
        }

        
        return charge;
    }
    
    public void chargeMembers(double charge) throws SQLException{
        String qry="";
        String qry1="select MEMBERS.\"id\" from MEMBERS where MEMBERS.\"status\"='APPROVED'";
        select(qry1);        
        while(rs.next()){
            String mem = rs.getString(1);
            qry="update MEMBERS SET MEMBERS.\"balance\"=MEMBERS.\"balance\"+"+charge+" where MEMBERS.\"id\"='"+mem+"'";
            
            try {
                statement = connection.createStatement();
                statement.executeUpdate(qry);
            }
            catch(SQLException e) {
                System.out.println("charge members error: "+qry+" : "+e);
            }   
        }
    }
    
    public int numMembers() throws SQLException{
        int num=0;
        String qry="select count(*) from MEMBERS where MEMBERS.\"status\"='APPROVED'";
        select(qry);
        if(rs.next()){
            num = rs.getInt(1);
            System.out.println(num);
        }
        return num;
    }
    
//    public void insert(String[] str){
//        PreparedStatement ps = null;
//        try {
//            ps = connection.prepareStatement("INSERT INTO users VALUES (?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
//            ps.setString(1, str[0].trim()); 
//            ps.setString(2, str[1]);
//            ps.executeUpdate();
//        
//            ps.close();
//            System.out.println("1 row added.");
//        } catch (SQLException ex) {
//            Logger.getLogger(JdbcUserQry.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         
//    }
    public void update(String[] str) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update Users Set password=? where username=?",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[1].trim()); 
            ps.setString(2, str[0].trim());
            ps.executeUpdate();
        
            ps.close();
            System.out.println("1 rows updated.");
        } catch (SQLException ex) {
            Logger.getLogger(JdbcUserQry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void payment(String user, double amount){
        String qry = "update MEMBERS set MEMBERS.\"balance\"="+amount+" where MEMBERS.\"id\"='"+user+"'";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(qry);
        }
        catch(SQLException e) {
            System.out.println("payment error: "+qry+" : "+e);
            //results = e.toString();
        }
    
    }
    
    public void claim(int id, String uname, String rat, double val, java.sql.Date date){
        String qry = "insert into CLAIMS values ("+id+",'"+uname+"','"+date+"','"+rat+"','APPLIED',"+val+")";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(qry);
        }
        catch(SQLException e) {
            System.out.println("claim error: "+qry+" : "+e);
            //results = e.toString();
        }

    }
    
    public int getMaxPayment(){
        int id=0;
        String qry = "select count(*) from PAYMENTS";
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(qry);
            if(rs.next()){
                id=rs.getInt(1);
            }
        }
        catch(SQLException e) {
            System.out.println("payment count error: "+qry+" : "+e);
            //results = e.toString();
        }
        return id;
    }
    
    public int getMaxClaim(){
        int id=0;
        String qry = "select count(*) from CLAIMS";
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(qry);
            if(rs.next()){
                id=rs.getInt(1);
            }
        }
        catch(SQLException e) {
            System.out.println("claim count error: "+qry+" : "+e);
            //results = e.toString();
        }
        return id;
    }
    
    public void delete(String user){
       
      String query = "DELETE FROM users " +
                   "WHERE username = '"+user.trim()+"'";
      
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch(SQLException e) {
            System.out.println("way way"+e);
            //results = e.toString();
        }
    }
    public void closeAll(){
        try {
            rs.close();
            statement.close(); 		
            //connection.close();                                         
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) throws SQLException {
//        String str = "select * from users";
//        String insert = "INSERT INTO `users` (`username`, `password`) VALUES ('meaydin', 'meaydin')";
//        String update = "UPDATE `users` SET `password`='eaydin' WHERE `username`='eaydin' ";
//        String db = "xyz";
//        
//        JdbcUserQry jdbc = new JdbcUserQry(str);
//        Connection conn = null;
//                try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db.trim(), "root", "root");
//        }
//        catch(ClassNotFoundException | SQLException e){
//            
//        }
//        jdbc.connect(conn);
//        String [] users = {"birgul12","han","han"};
//        System.out.println(jdbc.retrieve(str));
//        if (!jdbc.exists(users[0]))
//            jdbc.insert(users);            
//        else {
//                jdbc.update(users);
//                System.out.println("user name exists, change to another");
//        }
//        String uToDel = "aydinme";
//        if (jdbc.exists(uToDel)) {
//            jdbc.delete(uToDel);
//            System.out.println(jdbc.retrieve(str));
//        }
//        else
//            System.out.println(uToDel+" does not exit in the DB");
//        
//        jdbc.closeAll();
    }            
}
