/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author Jane
 */
public class User {
   private static String userId;
   private static String userName;
   private static String email;
   private static String password;
   
   //set userId
   public void setUserId(String userId){
      this.userId=userId; 
   }
   //get userId
   public String getUserId(){
      return userId;
   }
   //set userName
   public void setUserName(String userName){
       this.userName=userName;
   }
   //get userName
   public String getUserName(){
       return userName;
   }
   //set password
   public void setUserPassword(String password){
       this.password=password;
   }
   //get password
   public String getUserPassword(){
       return password;
   }
   //set email
   public void setUserEmail(String email){
       this.email=email;
   }
   //get email
   public String getUserEmail(){
       return email;
   }
 
}
