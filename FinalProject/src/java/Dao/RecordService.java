/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jane
 */
public class RecordService {
    private static Connection conn;  
    private static Statement st;  
    private static ResultSet rs; 
    private static dbConnection connect;
      
    //delete an item from shopping cart
    public void delete(String bookId){
        if(conn == null){  
            //get the connection to database
            connect=new dbConnection();
            conn = connect.getConnection();    
            if(conn == null){  
                System.out.println("Connection Fail" );  
            }  
        }  
        System.out.println("Connection Success    "+conn); 
        String sql ="";
        sql="DELETE FROM SHOPPINGCART WHERE ID='"+bookId+"'";
        System.out.println(sql);
        connect.delete(sql);
    } 
    
}
