package Dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jane
 */
public class dbConnection {
    private static Connection conn;  
    private static Statement st;  
    private static ResultSet rs; 
    
    public Connection getConnection() {  
        //build connection to database
        Connection con = null;  
        DataSource ds = null;  
        //database url(the same as the url in the glassfish-resources.xml)
        String connectionURL = "jdbc:derby://localhost:1527/Ebiz";
        try {  
            //user:root1, password:root
            con=DriverManager.getConnection(connectionURL, "root1", "root"); 
            return con;
        } catch (Exception e1) {  
            System.out.println("Fail"+e1);     
            return null;  
        } 
    } 
    
    //delete,insert and update
    public void update(String sql ) {  
         if(conn == null){  
            //get the connection to database
            conn = getConnection();    
            if(conn == null){  
                System.out.println("Connection Fail" );  
            }  
        }  
        System.out.println("Connection Success    "+conn);  
        //insert a new user
        System.out.println(sql);
        try {  
            st = (Statement) conn.createStatement();         
            st.executeUpdate(sql);           
        } catch (SQLException e) { 
            System.out.println(sql+"fail to contact derby");  
        }       
    }  
    
    public ResultSet query(String sql ) {  
        if(conn == null){  
            //get the connection to database
            conn = getConnection();      
            if(conn == null){  
                System.out.println("Connection Fail" );  
                return null;  
            }  
        }  
        System.out.println("Connection Success"+conn);  
        System.out.println(sql);
        try {  
            st = (Statement) conn.createStatement();         
            rs = (ResultSet)st.executeQuery(sql);   
            if(!rs.next()){  
                System.out.println(sql+"No data exists");  
                return null;  
            }         
            System.out.println("Get the data Already");            
          return rs;  
        } catch (SQLException e) {  
            System.out.println(sql+"fail to contact derby");  
            return null;  
        }  
         
    } 
    
    public void delete(String sql){
        if(conn == null){  
            //get the connection to database
            conn = getConnection();    
            if(conn == null){  
                System.out.println("Connection Fail" );  
            }  
        }  
        System.out.println("Connection Success    "+conn);  
        //insert a new user
        System.out.println(sql);
        try {  
            st = (Statement) conn.createStatement();         
            st.executeUpdate(sql);           
        } catch (SQLException e) { 
            System.out.println(sql+"fail to contact derby");  
        }          
    }
    
    public void insert(String sql){
        if(conn == null){  
            //get the connection to database
            conn = getConnection();    
            if(conn == null){  
                System.out.println("Connection Fail" );  
            }  
        }  
        System.out.println("Connection Success    "+conn);  
        //insert a new user
        System.out.println(sql);
        try {  
            st = (Statement) conn.createStatement();         
            st.executeUpdate(sql);           
        } catch (SQLException e) { 
            System.out.println(sql+"fail to contact derby");  
        }          
    }
    
    public void closeDB() throws SQLException{  
        if(null != rs)  
            rs.close();  
        if(null != st)  
            st.close();  
        if(null != conn)  
            conn.close();  
    } 
    
}
