/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Bean.Production;
import Bean.Record;
import Dao.ProductionService;
import Dao.shoppingService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jane
 */
public class updateProduction extends HttpServlet {

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
            out.println("<title>Servlet updateProduction</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateProduction at " + request.getContextPath() + "</h1>");
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
        
        HttpSession session =  request.getSession() ;
        String user = (String) session.getAttribute("userId");
        String wrongMessage ="";
       
        String id=request.getParameter("id");
        System.out.println(id);
        
        //before checking out, users have to sign in first
        if(user == null){
            wrongMessage="You need to register or sign in first";
            session.setAttribute("message", wrongMessage);
            response.sendRedirect("login.jsp") ;
        }else{
            List<Record> shoppings = (ArrayList)request.getSession().getAttribute("shoppings");
            ProductionService productSearch=new ProductionService();
            for(Record book:shoppings){
                Production p=productSearch.getByProductId(book.getProductName());
                //check if there is enough inventory
                if(p.getInventory()< book.getProductCount()){
                    wrongMessage=p.getName()+" doesn't have enough storage";
                    session.setAttribute("notEnoughBook", wrongMessage);
                    response.sendRedirect("cart.jsp") ; 
                    return;
                }
             }
            for(Record book:shoppings){
               Production p=productSearch.getByProductId(book.getProductName());
               int count=p.getInventory()- book.getProductCount();
               p.setInventory(count);
               productSearch.updateByProductName(p);
               shoppingService ss=new shoppingService(user);
               ss.deleteShoppingRecord(book);        
            }
            System.out.println("Shopping Complete");
            shoppings = (ArrayList)request.getSession().getAttribute("shoppings");
            session.setAttribute("message", "Your package is on the way!");
            response.sendRedirect("orderPlaced.jsp") ; 
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
