/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.Record;
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
public class shoppingService {
    private static Connection conn;  
    private static Statement st;  
    private static ResultSet rs; 
    private List<Record> sB01;
    private String userName;
 
    //get the ResultSet from sql
    public shoppingService(String userName){
       this.userName = userName;
       sB01=new ArrayList<>();
       dbConnection connect = new dbConnection();
       ResultSet rs= connect.query("SELECT * FROM SHOPPINGCART WHERE USERNAME ='"+userName+"'"); 
       try{
           if(rs != null){  
            do{  
            //javaBean production    
            Record book=new Record(); 
            //store everything in the production class
            book.setId(rs.getString("ID"));
            book.setProductName(rs.getString("PRODUCTNAME"));
            book.setCost(rs.getDouble("PRODUCTPRICE"));
            book.setProductCount(rs.getInt("QUANTITY"));
            book.setUserName(rs.getString("USERNAME"));
            sB01.add(book);
            }while(rs.next());  
            }
       }catch (SQLException e) {  
            System.out.println("fail to contact derby");  
       }  
       
    } 
   
    public Record getShoppingItemByName(String name){
       List<Record> sB=getShoppintCartByUserName(userName);  
       System.out.println(sB.size());
       for(Record book01:sB){
          if(book01.getProductName().equals(name)) {
              return book01;
          } 
       }
       return null;             
    }
    
    public void deleteShoppingRecord(Record book){
        String id=book.getId();
        String sql="DELETE FROM SHOPPINGCART WHERE ID='"+id+"'";
        dbConnection connect = new dbConnection();
        connect.delete(sql);  
    }
    
    //update shopping item's quantity
    public void updateShoppingItem(Record shoppingItem){
        dbConnection connect = new dbConnection();
        String id=shoppingItem.getId();
        int count=shoppingItem.getProductCount();
        String sql="UPDATE SHOPPINGCART SET QUANTITY= "+count+" WHERE ID='"+id+"'";
        System.out.println(sql);
        connect.update(sql);
        
    }
    
    //insert a new record
    public void insert(String productionName,double price,int count,String userName){
        dbConnection connect = new dbConnection();
        String id=productionName+userName;
        String sql="INSERT INTO SHOPPINGCART(ID,PRODUCTNAME,PRODUCTPRICE,QUANTITY,USERNAME) VALUES ('"+id+"','"+productionName+"',"+price+","+count+",'"+userName+"')";
        System.out.println(sql);
        connect.insert(sql);   
    }
    
    //check if the item user wants to add to the cart is already in the cart
    public boolean isExistByUser_EpId(String userName, String productId){
        dbConnection connect = new dbConnection();
        ResultSet rs= connect.query("SELECT * FROM SHOPPINGCART WHERE USERNAME ='"+userName+"' AND PRODUCTNAME ='"+productId+"'");
        if(rs == null) return false;
        else return true;
    }
 
    //get all shopping cart items
    public List<Record> getShoppintCartByUserName(String userName){
        dbConnection connect = new dbConnection();
        List<Record> sB=new ArrayList<>();
        ResultSet rs= connect.query("SELECT * FROM SHOPPINGCART WHERE USERNAME ='"+userName+"'");
        try{
           if(rs != null){  
            do{
              Record book=new Record();
              book.setId(rs.getString("ID"));
              book.setProductName(rs.getString("PRODUCTNAME"));
              book.setCost(rs.getDouble("PRODUCTPRICE"));
              book.setProductCount(rs.getInt("QUANTITY"));
              book.setUserName(rs.getString("USERNAME"));
              sB.add(book);
            }while(rs.next());  
            }
        }catch (SQLException e) {  
            System.out.println("fail to contact derby");  
            return null;  
        }  
        for(Record book:sB){
            rs= connect.query("SELECT * FROM PRODUCT WHERE PRODUCTNAME ='"+book.getProductName()+"'");
            try{
            if(rs != null){  
            do{
              book.setProductAuthor(rs.getString("author"));
              book.setProductUrl(rs.getString("picture"));
             
            }while(rs.next());  
            }
        }catch (SQLException e) {  
            System.out.println("fail to contact derby");  
            return null;  
        }  
            
        }
        return sB;
    }
  
}
