<%-- 
    Document   : login
    Created on : 2017-4-13, 21:02:57
    Author     : gaopingjie
--%>

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
<link rel="stylesheet" href="css/login.css">


<body class="w3-content" style="max-width:1200px">

<!--Logo-->   
<div class="w3-container w3-padding-16 w3-center">
    <a class="w3-wide" href="index.htm" style="text-align:left;font-size:28px;background: white"><h3><b>JJ's BookStore</b></a></h3>
</div>

<!-- !PAGE CONTENT! -->
<div class="w3-main">

  <!-- Sign In -->
  <div class="sign-in-register"> 
      
    <form action="LoginServlet" method="post" name="formSignIn">
        <!-- User ID -->
        <div class="customer-info">
            <p>User ID:</p>
            <input class="w3-input w3-border w3-round" type="text" name="userId"/>
        </div>

        <!-- Password -->
        <div class="customer-info">
            <p>Password:</p>
            <input class="w3-input w3-border w3-round" type="password" name="password"/>                  
        </div>

        <input class="sign-reg-button" type="submit" value="Log In" onClick="return validateLogin()" />

        <p style="text-align:center; margin-top:-30px;">Not have an account? <a href="register.jsp" style="font-style:italic;text-decoration: underline;">click here to register</a>.</p>
    </form>
    
    <!-- Validation of Log In -->
    <script language="javascript">
        function validateLogin(){
            var sUserId = document.formSignIn.userId.value;
            var sPassword = document.formSignIn.password.value;
            if(sUserId==""){
                alert("Please enter in user ID!");
                return false;
            }
            if(sPassword==""){
                alert("Please enter in password!");
                return false;
            }   
        }
    </script>    
  

  </div>

  <!-- Footer -->
    <footer class="container-fluid text-center" id="foot">
      <div class="col-sm-12">
        <hr/>
        <p style="margin-top:15px;font-size: 17px;">Powered by Pingjie Gao & Mingjia Hang</p>
        <p>pig7@pitt.edu / mih90@pitt.edu</p>
      </div>
    </footer>
  <!-- End page content -->
  
</div>

</body>
</html>

