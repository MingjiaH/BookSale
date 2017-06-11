<%-- 
    Document   : bookslist
    Created on : 2017-4-13, 17:51:53
    Author     : gaopingjie
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Bean.Production"%>
<%@page import="java.util.List"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>JJ's BookStore</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/bookslist.css">

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

        
      <!-- Product grid --> 
      <%             
          List<Production> books=new ArrayList<Production>();
          books=(ArrayList<Production>)request.getAttribute("bookList");
      %>
        
      <div class="w3-row" style="margin-top:20px;">
        <!--show the book list-->
          <%             
              for(Production eachone : books){ 
          %>
            <div class="w3-col l3 s6">
                <div class="w3-container book">
                    <a href="singleBookServlet?param=<%=eachone.getUrl()%>"><img src="<%=eachone.getUrl()%>"></a>
                    <div class="namewrap">
                        <a href="singleBookServlet?param=<%=eachone.getName()%>"><%=eachone.getName()%></a>
                        <br/>
                        <div class="author" name="authorname"><%=eachone.getAuthor()%></div>
                        <b class="price" name="price">$<%=eachone.getPrice()%></b>
                    </div>
                </div>
            </div>
          <%  }
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

