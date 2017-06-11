<%-- 
    Document   : register
    Created on : 2017-4-13, 22:19:41
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
<link rel="stylesheet" href="css/register.css">


<body class="w3-content" style="max-width:1200px">

<!--Logo-->   
<div class="w3-container w3-padding-16 w3-center">
    <a class="w3-wide" href="index.htm" style="text-align:left;font-size:28px;background: white"><h3><b>JJ's BookStore</b></a></h3>
</div>

<!-- !PAGE CONTENT! -->
<div class="w3-main">

  <!-- Register -->
  <div class="sign-in-register">

    <form action="RegisterServlet" method="post" name="formRegister">
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

        <!-- Confirm Password -->
        <div class="customer-info">
            <p>Confirm Password:</p>
            <input class="w3-input w3-border w3-round" type="password" name="confirmPassword"/>
        </div>

        <!-- Email -->
        <div class="customer-info">
            <p>Email:</p>
            <input class="w3-input w3-border w3-round" type="text" name="email"/>
        </div>
        
        <input class="sign-reg-button" type="submit" value="Register" onClick="return validateRegister()" />

        <p style="text-align:center; margin-top:-30px;">Already have an account? <a href="login.jsp" style="font-style:italic;text-decoration: underline;">click here to sign in</a>.</p>
    </form>

    <!-- Validation of Register -->
    <script language="javascript">
        function validateRegister(){
            var sUserId = document.formRegister.userId.value;
            var sPassword = document.formRegister.password.value;
            var sConfirmPassword = document.formRegister.confirmPassword.value;
            var sEmail = document.formRegister.email.value;
            if(sUserId==""){
                alert("Please enter in user ID!");
                return false;
            }
            if(sUserId.length>15){
                alert("User ID cannot exceed 15 characters!");
                return false;
            }         
            if(sPassword==""){
                alert("Please enter in password!");
                return false;
            }
            if(sPassword.length>16){
                alert("Password cannot exceed 16 characters!");
                return false;
            }
            if(sConfirmPassword==""){
                alert("Please enter in confirm password!");
                return false;
            }  
            if(sEmail==""){
                alert("Please enter in your email address!");
                return false;
            }            
            if(sConfirmPassword!= sPassword){
                alert("Passwords should match!");
                return false;
            }
            if(!sEmail.includes("@")){
                alert("Please enter in valid email address!");
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