/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.Production;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jane
 */
public class ProductionService {
    private static Connection conn;  
    private static Statement st;  
    private static ResultSet rs;  
    private static dbConnection connect;
    
    //update the inventory
    public void updateByProductName(Production productItem){
        connect=new dbConnection();
        String name=productItem.getName();
        int count=productItem.getInventory();
        String sql ="";
        sql="UPDATE PRODUCT SET INVENTORY= "+count+" WHERE PRODUCTNAME='"+name+"'";
        connect.update(sql);
    }
    
    //find the product
    public Production getByProductId(String productionId){
        connect=new dbConnection();
        ResultSet rs =connect.query("SELECT * FROM PRODUCT WHERE PRODUCTNAME='"+productionId+"'");
        Production product0=new Production();
        try{
           if(rs != null){  
            do{  
            //store everything in the production class
            product0.setId(rs.getString("pid")); 
            product0.setName(rs.getString("productName"));  
            product0.setAuthor(rs.getString("author"));  
            product0.setType(rs.getString("type")); 
            product0.setDescription(rs.getString("description"));
            product0.setPrice(rs.getDouble("price")); 
            product0.setInventory(rs.getInt("inventory"));
            product0.setUrl(rs.getString("picture"));
            product0.setSeller(rs.getString("seller"));
            }while(rs.next());  
            }
       }catch (SQLException e) {  
            System.out.println("fail to contact derby");  
            return null;  
       }
        return product0;
    }
    
    //get the data from ResultSet and store them in the List<production> 
    public static List<Production> showAllProduction(ResultSet rs){
       connect=new dbConnection();
       List<Production> results=new ArrayList<>();
       try{
           if(rs != null){  
            do{  
            //javaBean production    
            Production product0=new Production(); 
            //store everything in the production class
            product0.setId(rs.getString("pid")); 
            product0.setName(rs.getString("productName"));  
            product0.setAuthor(rs.getString("author"));  
            product0.setType(rs.getString("type")); 
            product0.setDescription(rs.getString("description"));
            product0.setPrice(rs.getDouble("price")); 
            product0.setInventory(rs.getInt("inventory"));
            product0.setUrl(rs.getString("picture"));
            product0.setSeller(rs.getString("seller"));
            results.add(product0);
            }while(rs.next());  
            }
       }catch (SQLException e) {  
            System.out.println("fail to contact derby");  
            return null;  
       }  
       return results;
    } 
}
