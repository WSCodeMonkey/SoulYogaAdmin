<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html class="no-js">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>重置密码</title>
	<!-- Bootstrap -->
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
	<link href="assets/styles.css" rel="stylesheet" media="screen">
	<script src="vendors/jquery-1.11.2.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
	
</head>

<body>
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="#">Admin Panel</a>
				
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<!-- block -->
				<div class="block">
					<div class="navbar navbar-inner block-header">
						<div class="muted pull-left">重置密码</div>
					</div>
					<div class="block-content collapse in">
						<form action="verifyPhoneNo.action" method="post" class="form-horizontal">
							<div class="control-group">
								<label class="control-label" for="password">密码</label>
								<div class="controls">
									<input type="text" id="password" name="password" placeholder="请输入密码" required="required">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="repassword">确认密码</label>
								<div class="controls">
									<input type="text" id="repassword" name="repassword" placeholder="请再次输入密码" required="required">
								</div>
							</div>
							<div class="control-group">
								<div class="controls">
									<input type="submit" value="确认重置" class="btn btn-success" />
								</div>
							</div>
						</form>		
					</div>
				</div>
				<!-- /block -->
			</div>
		</div>
		<!--/.fluid-container-->
	</div>
</body>

</html>
