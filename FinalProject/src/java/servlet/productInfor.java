/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Bean.Record;
import Dao.shoppingService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jane
 */
public class productInfor extends HttpServlet {

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
            out.println("<title>Servlet productInfor</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet productInfor at " + request.getContextPath() + "</h1>");
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
        HttpSession session =  request.getSession() ;
        String user = (String) session.getAttribute("userId");

        if(user == null){
           response.sendRedirect("cart.jsp"); 
        }else if(user != null){
           shoppingService sService=new shoppingService(user);
           Map<String,Record> myCart = (Map<String, Record>) request.getSession().getAttribute("MyCart"); 
           if(myCart!=null){
               Set<String> keys = myCart.keySet(); 
                java.util.Iterator<String> iterator = keys.iterator(); 
                while (iterator.hasNext()) {
                    Record p = myCart.get(iterator.next());
                    System.out.println(sService.isExistByUser_EpId(user,p.getProductName()));
                    if(sService.isExistByUser_EpId(user,p.getProductName())){ 
                        Record shoppingItem = sService.getShoppingItemByName(p.getProductName());
                        int newCount=shoppingItem.getProductCount()+p.getProductCount();
                        shoppingItem.setProductCount(newCount);
                        System.out.println(newCount);
                        sService.updateShoppingItem(shoppingItem);    
                    }else{
                        double price=p.getCost();
                        String a=p.getProductName();
                        sService.insert(a,price,p.getProductCount(),user); 
                    }              
                    request.getSession().removeAttribute("MyCart"); 
                } 
           }
           List<Record> shoppings = sService.getShoppintCartByUserName(user); 
           System.out.println("This user has "+shoppings.size()+" products");
           request.getSession().setAttribute("shoppings",shoppings);
           response.sendRedirect("cart.jsp"); 
        } 
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
        //processRequest(request,response);
        
        HttpSession session =  request.getSession() ;
        String user = (String) session.getAttribute("userId");

        if(user == null){
           System.out.println("No user is here");
           response.sendRedirect("cart.jsp"); 
        }else if(user != null){
           shoppingService sService=new shoppingService(user);
           Map<String,Record> myCart = (Map<String, Record>) request.getSession().getAttribute("MyCart"); 
           if(myCart!=null){
               Set<String> keys = myCart.keySet(); 
                java.util.Iterator<String> iterator = keys.iterator(); 
                while (iterator.hasNext()) {
                    Record p = myCart.get(iterator.next());
                    System.out.println(sService.isExistByUser_EpId(user,p.getProductName()));
                    if(sService.isExistByUser_EpId(user,p.getProductName())){ 
                        Record shoppingItem = sService.getShoppingItemByName(p.getProductName());
                        int newCount=shoppingItem.getProductCount()+p.getProductCount();
                        shoppingItem.setProductCount(newCount);
                        sService.updateShoppingItem(shoppingItem);    
                    }else{
                        double price=p.getCost();
                        String a=p.getProductName();
                        sService.insert(a,price,p.getProductCount(),user); 
                    }              
                    request.getSession().removeAttribute("MyCart"); 
                } 
           }
           List<Record> shoppings = sService.getShoppintCartByUserName(user); 
           System.out.println("This user has "+shoppings.size()+" products");
           request.getSession().setAttribute("shoppings",shoppings);
           response.sendRedirect("cart.jsp"); 
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
