/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;


import Bean.Production;
import Bean.Record;
import Dao.ProductionService;
import Dao.VisitorService;
import Dao.dbConnection;
import Dao.shoppingService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jane
 */
public class shoppingCart extends HttpServlet {

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
            out.println("<title>Servlet shoppingCart</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet shoppingCart at " + request.getContextPath() + "</h1>");
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
        //get the shopping cart list
        response.setContentType("text/html;charset=utf-8"); 
        request.setCharacterEncoding("utf-8"); 
        VisitorService ps = new VisitorService();
        PrintWriter out = response.getWriter(); 
        String path=request.getContextPath(); 
        String a = (String)request.getSession().getAttribute("book"); 
        double price=(Double)request.getSession().getAttribute("price"); 
        String aUrl = (String)request.getSession().getAttribute("url");
        String author = (String)request.getSession().getAttribute("author");
        int count = Integer.parseInt(request.getParameter("quantity"));  
        String userName = (String)request.getSession().getAttribute("userId"); 
        
        if(a == null||a.equals("")){
            if(userName == null){
                 
            }else{
                shoppingService sService=new shoppingService(userName);
                List<Record> shoppings = sService.getShoppintCartByUserName(userName);
                Map<String,Record> myCart = (Map<String, Record>) request.getSession().getAttribute("MyCart"); 
                if(myCart!=null){
                    
                }
                request.getSession().setAttribute("shoppings",shoppings);
            }
            response.sendRedirect("cart.jsp") ;
        }else{
            if(userName == null){  
            Map<String,Record> myCart = (Map<String, Record>) request.getSession().getAttribute("MyCart"); 
            if(!a.equals("") ){ 
                Record shoppingBook = ps.getByProductionName(a);
                shoppingBook.setProductCount(count);
                shoppingBook.setCost(price);
                shoppingBook.setProductUrl(aUrl);
                shoppingBook.setProductAuthor(author);
                if(myCart == null){ 
                    myCart = new HashMap<String, Record>();
                    myCart.put(a, shoppingBook); 
                }else{ 
                    if(myCart.containsKey(a)){  
                        Record shoppingBook01 = myCart.get(a); 
                        System.out.println("This book is existing");
                        int newCount=shoppingBook01.getProductCount()+count;
                        System.out.println("This new Count is"+newCount);
                        shoppingBook01.setProductCount(newCount); 
                        System.out.println("This book is existing");
                        myCart.put(a, shoppingBook01); 
                    }else{ 
                        myCart.put(a, shoppingBook);
                    }
                }
            } 
            request.getSession().setAttribute("MyCart", myCart); 
            request.getSession().setMaxInactiveInterval(10*60); 
            response.sendRedirect("cart.jsp") ;
            
        }else{ 
            Map<String,Record> myCart = (Map<String, Record>)request.getSession().getAttribute("MyCart");
            shoppingService sService=new shoppingService(userName);
            if(myCart == null){ 
                System.out.println("the shopping cart is empty");
                if(!a.equals("")){  
                    Record shoppingItem = sService.getShoppingItemByName(a);
                    if(shoppingItem == null){ 
                        sService.insert(a,price,count,userName); 
                    }else{ 
                        System.out.println("This item already exists");
                        shoppingItem.setProductCount(shoppingItem.getProductCount()+count); 
                        sService.updateShoppingItem(shoppingItem);
                    }
                } 
            }else{ 
                Set<String> keys = myCart.keySet(); 
                java.util.Iterator<String> iterator = keys.iterator(); 
                while (iterator.hasNext()) {
                    Record p = myCart.get(iterator.next());
                    System.out.println(sService.isExistByUser_EpId(userName,p.getProductName()));
                    if(sService.isExistByUser_EpId(userName,p.getProductName())){ 
                        Record shoppingItem = sService.getShoppingItemByName(p.getProductName());
                        int newCount=shoppingItem.getProductCount()+p.getProductCount();
                        shoppingItem.setProductCount(newCount);
                        System.out.println(newCount);
                        sService.updateShoppingItem(shoppingItem);    
                    }else{ 
                        sService.insert(a,price,p.getProductCount(),userName); 
                    }              
                    request.getSession().removeAttribute("MyCart"); 
                } 
            } 
            List<Record> shoppings = sService.getShoppintCartByUserName(userName); 
            request.getSession().setAttribute("shoppings",shoppings);
            response.sendRedirect("cart.jsp") ;
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
