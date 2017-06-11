<%-- 
    Document   : cart
    Created on : 2017-2-26, 1:12:23
    Author     : gaopingjie
--%>

<%@page import="Bean.Production"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="Bean.Record"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<title>JJ's BookStore</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/cart.css">

<body class="w3-content" style="max-width:1200px">

<!-- Sidenav/menu -->
<div class="col-sm-3 sidenav">
    <nav class="w3-sidenav w3-white w3-collapse w3-top" style="z-index:3;width:250px;text-align: center;" id="mySidenav">
      
      <!-- Logo -->  
      <div class="w3-container w3-padding-16">
          <a class="w3-wide" href="index.htm" style="text-align:left;padding-left:35px;font-size:28px;background: white"><h3><b>JJ's BookStore</b></a></h3>
      </div>

      <!-- Menu -->
      <div class="w3-padding-16 w3-large w3-text-grey" id="my-nav" style="font-weight:bold;">
        <a href="GetBookListServlet?param=all" style="margin-top:65px;padding-left:2px;" name="category" value="all">All Books</a>
        <a href="GetBookListServlet?param=children" style="margin-top:45px;padding-left:2px;" name="category" value="children">Children's Books</a>
        <a href="GetBookListServlet?param=history" style="margin-top:15px;padding-left:2px;" name="category">History</a>
        <a href="GetBookListServlet?param=literature" style="margin-top:15px;padding-left:2px;" name="category">Literature & Fiction</a>
        <a href="GetBookListServlet?param=romance" style="margin-top:15px;padding-left:2px;" name="category">Romance</a>
        <a href="GetBookListServlet?param=textbook" style="margin-top:15px;padding-left:2px;" name="category">Textbooks</a>
      </div>
      
    </nav>
</div>

<!-- Top menu on small screens -->
<header class="w3-container w3-top w3-hide-large w3-xlarge w3-padding-24">
  <span class="w3-left w3-wide"  id="small-logo"><a href="index.htm">JJ's BookStore</a></span>
  <a href="javascript:void(0)" style="font-size:24px;" class="w3-right w3-opennav" onclick="w3_open()"><i class="fa fa-bars"></i></a>
</header>

<!-- Overlay effect when opening sidenav on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="col-sm-9">
    <div class="w3-main">

      <!-- Push down content on small screens -->
      <div class="w3-hide-large" style="margin-top:10px"></div>

      <!-- Top header -->
      <header class="w3-container w3-xlarge" style="padding-top:70px;">
        <p class="w3-right" style="font-size:16px;"> 
            
        <!-- If users have logged in, welcome message will be shown instead of "Sign in" icon -->
        <div style="text-align:right;">
        <%
            String userId = (String)session.getAttribute("userId") ;
            if((userId!=null)&&(userId!="")){ %>
                <p style="display:inline-block; font-size:16px; color:#595858;">Hi! <%=userId%></p>
                <a href="LogoutServlet" style="margin-left: 10px;font-size:16px;">Log out</a>    
                <a href="productInfor" style="margin-left: 10px;font-size:16px;">Shopping Cart</a>
            <% } else{ %>
                <a href="login.jsp" style="font-size:16px;">Sign In / Register</a>
                <a href="productInfor" style="font-size:16px; margin-left: 10px;">Shopping Cart</a>
            <% }
        %>    
        </div>
        </p>
      </header>

      <!-- Product List -->
      <div class="cart-list">
        <%
            Map<String,Record> map=(Map<String, Record>) request.getSession().getAttribute("MyCart");
            String userName=(String)request.getSession().getAttribute("userId");
            List<Record> shoppings = (List<Record>)request.getSession().getAttribute("shoppings");
            
            String message=(String)request.getSession().getAttribute("notEnoughBook");            
            double totalPrice=0;
            
            //show message if user orders too big quantity of one item
            if(message!=null) {
                System.out.println(message);
                session.removeAttribute("notEnoughBook");
        %> 
            <h2><%=message%></h2>

            <%  message=null;} %>
                        
            <!-- Introduction of each column -->
            <div class="product-intro">
                <div class="col-sm-5">
                    <h3>Product</h3>
                </div>
                <div class="col-sm-2">
                    <h3>Unit Price</h3>
                </div>
                <div class="col-sm-2">
                    <h3>Quantity</h3>
                </div>
                <div class="col-sm-2">
                    <h3>Price</h3>
                </div>
                <div class="col-sm-1">
                    <h3></h3>
                </div>
            </div> 
          
            <hr/>
          
            <!--There are no items in the shopping cart-->
            <%if(map==null && shoppings==null){%>
                <h3 style="text-align:center; margin-top:60px;">There are no items in your shopping cart</h3>
            <%}
            //There are items in the shopping cart, show them
            else{ %>
            <!-- Shoxpping Cart Products  -->
            <form action="updateProduction" method="post" name="payForEverything">
            
            <!-- When user hasn't logged in -->
            <%if(userName == null){%>   
            <%for(Map.Entry<String,Record> entry:map.entrySet()){ %>

              <div class="verticalCenter">
                  <div class="col-sm-5 w3-container book verticalCenter">
                    <div class="col-sm-6">
                        <a href="singleBookServlet?param=<%=entry.getValue().getProductUrl()%>"><img src="<%=entry.getValue().getProductUrl()%>"></a>
                    </div>
                    <div class="col-sm-6">
                        <div class="nameeeeewrap">
                            <p>Book: <a href="singleBookServlet?param=<%=entry.getValue().getProductName()%>" name="bookname"><%=entry.getValue().getProductName()%></a></p>
                            <p class="author" name="authorname">Author: <%=entry.getValue().getProductAuthor()%></p>                        
                        </div>
                    </div>
                  </div>

                <div class="col-sm-2">
                    <p>$<%=entry.getValue().getCost()%></p>
                </div>
                <div class="col-sm-2">
                    <p><%=entry.getValue().getProductCount()%></p>
                </div>
                
                <%double price=entry.getValue().getCost()*entry.getValue().getProductCount();%>
                <div class="col-sm-2">
                    <p>$<%=price%></p>
                </div>
                
                <!--calculate total price (before tax)-->
                <%totalPrice+=price;%> 
                
                <div class="col-sm-1">
                    <a class="remove glyphicon glyphicon-remove" href="deleteProduct?bookName=<%=entry.getValue().getProductName()%>"></a>
                </div>              

              </div>           

              <hr/>
            <%            
               } //end show all items in the shopping cart
            }//end user hasn't signed in
            
            else{
            %>
            <!-- When users have signed in -->
            <%for(Record book:shoppings){ %>

                <div class="verticalCenter">
                  <div class="col-sm-5 w3-container book verticalCenter">
                    <div class="col-sm-6">
                        <a href="singleBookServlet?param=<%=book.getProductUrl()%>"><img src="<%=book.getProductUrl()%>"></a>
                    </div>
                    <div class="col-sm-6">
                        <div class="nameeeeewrap">
                            <p>Book: <a href="singleBookServlet?param=<%=book.getProductName()%>" name="bookname"><%=book.getProductName()%></a></p>
                            <p class="author" name="authorname">Author: <%=book.getProductAuthor()%></p>                        
                        </div>
                    </div>
                  </div>

                    <div class="col-sm-2">
                        <p>$<%=book.getCost()%></p>
                    </div>
                    <div class="col-sm-2">
                        <p><%=book.getProductCount()%></p>
                    </div>
                    <div class="col-sm-2">
                        <%double price=book.getCost()*book.getProductCount();
                        price = Math.floor(price * 100) / 100;%>
                        <p>$<%=price%></p>
                    </div>
                    <%totalPrice+=price;%>
                    
                    <div class="col-sm-1">
                        <a class="remove glyphicon glyphicon-remove" href="deleteProduct?shoppingId=<%=book.getId()%>"></a>
                    </div>              

                </div>           

                <hr/>
            <%  } //end show all items in the shopping cart
            } //end user has signed in
            %>          

              <!-- Tax -->
              <div class="product-tax">
                  <div class="col-sm-7">
                  </div>
                  <div class="col-sm-2">
                      <h3>Tax</h3>
                  </div>
                  <div class="col-sm-2">
                      <%double tax=totalPrice*0.1;
                      tax = Math.floor(tax * 100) / 100;%>
                      <h3>$<%=tax%></h3>
                  </div>
                  <div class="col-sm-1">
                  </div>
              </div>  

              <!-- Total Price After Tax-->
              <div class="product-summary">
                  <div class="col-sm-7">
                  </div>
                  <div class="col-sm-2">
                      <h3>Total Price</h3>
                  </div>
                  <div class="col-sm-2">
                      <%totalPrice+=tax;
                      totalPrice = Math.floor(totalPrice * 100) / 100;%>
                      <h3>$<%=totalPrice%></h3>
                  </div>
                  <div class="col-sm-1">
                  </div>
              </div> 

            <!-- Check Out -->
            <div class="check-out" style="margin-top:30px;">
              <p align="right">
                  <button onClick="return validateCheckout()">Check Out</button>
              </p>    
            </div> 
            </form>
                  
            <!-- Validation of Checkout -->
            <script language="javascript">
                function validateCheckout(){
                    var totalPrice = <%=totalPrice%>; 

                    if(totalPrice == 0){
                        alert("You have no item to check out!");
                        return false;
                    } 

                }                                     
            </script>  
                      
            <%
            } //end there are items in the cart
            %> 
      </div>
          
        <!-- Footer -->
        <div class="col-sm-12">
            <footer class="container-fluid text-center" id="foot">
                <hr/>
                <p style="margin-top:15px;font-size: 17px;">Powered by Pingjie Gao & Mingjia Hang</p>
                <p>pig7@pitt.edu / mih90@pitt.edu</p>
            </footer>        
        </div> 

      <!-- End page content -->
    </div>
</div>

<script>
// Accordion 
function myAccFunc() {
    var x = document.getElementById("demoAcc");
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
    } else {
        x.className = x.className.replace(" w3-show", "");
    }
}

// Script to open and close sidenav on small screen
function w3_open() {
    document.getElementById("mySidenav").style.display = "block";
    document.getElementById("myOverlay").style.display = "block";
}
 
function w3_close() {
    document.getElementById("mySidenav").style.display = "none";
    document.getElementById("myOverlay").style.display = "none";
}
</script>

</body>
</html>
