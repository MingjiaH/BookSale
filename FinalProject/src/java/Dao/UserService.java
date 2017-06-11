/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jane
 */
public class UserService {
    
    //register, insert an user
    public static void insertUser(User user0 ){
       dbConnection connect=new dbConnection();
       String sql="INSERT INTO CUSTOMER(USERID,USERNAME,PASSWORD,EMAIL) VALUES ('"+user0.getUserId()+"','"+user0.getUserName()+"','"+user0.getUserPassword()+"','"+user0.getUserEmail()+"')";
       System.out.println(sql);
       connect.insert(sql); 
    }
    
    //get the users, make sure they are not already in our database when they register
    public static List<User> showAllUsers(ResultSet rs){
       List<User> results=new ArrayList<>();
       try{
           if(rs != null){  
            do{  
            User user0=new User();//一个javaBean的实例化对象  
            user0.setUserId(rs.getString("userId")); 
            user0.setUserName(rs.getString("userName"));  
            user0.setUserPassword(rs.getString("passWord"));  
            user0.setUserEmail(rs.getString("email"));
            results.add(user0);
            }while(rs.next());  
            }
       }catch (SQLException e) {  
            System.out.println("fail to contact derby");  
            return null;  
       }  
       return results;
    }
}
