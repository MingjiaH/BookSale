package servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Bean.User;
import Dao.UserService;
import Dao.dbConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gaopingjie
 */
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String userName, password, confirmPassword, email, result="";
        userName=request.getParameter("userId");
        password=request.getParameter("password");
        confirmPassword=request.getParameter("confirmPassword");
        email=request.getParameter("email");

        HttpSession session = request.getSession();
        session.setAttribute("message",result);
        PrintWriter out = response.getWriter();

        //check if user ID is legitimate
        if((userName=="")||(userName==null)||(userName.length()>15)){
            try{
                result="Please enter in legitimate user ID";
                request.setAttribute("message", result);
                response.sendRedirect("register.jsp");
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }        
 
        //check if password is legitimate        
        if((password=="")||(password==null)||(password.length()>16)){
            try{
                result="Please enter in legitimate password";
                request.setAttribute("message" ,result) ;
                response.sendRedirect("register.jsp") ;
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        //check if password and confirm password are the same       
        if(!confirmPassword.equals(password)){
            try{
                result="Passwords must match";
                request.setAttribute("message" ,result) ;
                response.sendRedirect("register.jsp") ;
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        
        //check if email is legitimate        
        if(!email.contains("@")){
            try{
                result="Please enter in legitimate email address";
                request.setAttribute("message" ,result) ;
                response.sendRedirect("register.jsp") ;
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        if(result!=""){
                response.sendRedirect("register.jsp") ;
        }
        else{

            try{
                //check if user has existed
                String checkUser="select * from customer where username='"
                    +userName+"'";
                dbConnection getUser = new dbConnection();
                ResultSet rs=getUser.query(checkUser);

                if(rs!=null){ //user has existed                    
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('User has existed, please choose another user ID.');");
                    out.println("location='register.jsp';");
                    out.println("</script>");
                }
                else{ //username can be used, insert the user information into database
                    //SQL    
                    String sql="select * from customer";
                    rs=getUser.query(sql);
                    List<User> usersList=new ArrayList<>();
                    usersList=UserService.showAllUsers(rs);
                    int id=usersList.size()+1; 
                    
                    User user0=new User();
                    user0.setUserId(String.valueOf(id));
                    user0.setUserName(userName);
                    user0.setUserPassword(password);
                    user0.setUserEmail(email);
                    UserService.insertUser(user0);        
                   
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('You have successfully registered! Please login.');");
                    out.println("location='login.jsp';");
                    out.println("</script>");
                }
              
            }
            catch(Exception e){
                e.printStackTrace();
            }         
        }
        
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
