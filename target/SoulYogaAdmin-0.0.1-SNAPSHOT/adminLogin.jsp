<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Admin Login</title>
    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <link href="assets/styles.css" rel="stylesheet" media="screen">
          <script src="vendors/jquery-1.11.2.min.js"></script>
           <script src="bootstrap/js/bootstrap.min.js"></script>
           
           		<script type='text/javascript'>
    (function () {
        var s = document.createElement('script');
        s.type = 'text/javascript';
        s.async = true;
        s.src = (location.protocol == 'https:' ? 'https://ssl.' : 'http://static.') + 'gridsumdissector.com/js/Clients/GWD-002498-0C1485/gs.js';
        var    firstScript = document.getElementsByTagName('script')[0];
        firstScript.parentNode.insertBefore(s, firstScript);
    })();
</script>

    
    <script type="text/JavaScript">
  //错误提示信息
 var msg=""+'${request.tipMessage}'; 
  if(msg!=""){ 
     alert(msg); 
  } 
  </script>  
  
  </head>
  <body id="login">
    <div class="container">

      <form class="form-signin" action="userLogin.action" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" class="input-block-level" placeholder="Email address" name="userName" value="admin">
        <input type="password" class="input-block-level" placeholder="Password" name="password" autocomplete="off" value="123456">
        <label class="checkbox">
          <input type="checkbox" value="remember-me"> Remember me
		  <a href="adminForgetpassword.jsp" style="float:right">Forget password?</a>
        </label>

        <button class="btn btn-large btn-primary" type="submit">Sign in</button>
      </form>

    </div>
  </body>
</html>