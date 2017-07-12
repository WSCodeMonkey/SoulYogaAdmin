<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="no-js">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Member Management</title>
<!-- Bootstrap -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet"
	media="screen">
<link href="assets/styles.css" rel="stylesheet" media="screen">
<script src="vendors/jquery-1.11.2.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script language="javascript" type="text/javascript"
	src="bootstrap/js/My97DatePicker/WdatePicker.js"></script>
<link href="bootstrap/js/My97DatePicker/skin/WdatePicker.css"
	rel="stylesheet" type="text/css">
<script type='text/javascript'>
	
 $(document).ready(function(){
		$("#identityId").blur(function() {
			var identityId = $("#identityId").val();
			$.ajax({
				url : "identityId.action",
				type : "post",
				dataType : "json",
				data : {
					identityId : identityId
				},
				success : function(result) {
					if(result==1) {
						$("#identityIdFiled").html("该身份证号已存在！");
					}
				}
			});

		})
 })

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
				<div class="nav-collapse collapse">
					<ul class="nav pull-right">
						<li class="dropdown"><a href="#" role="button"
							class="dropdown-toggle" data-toggle="dropdown"> <i
								class="icon-user"></i> SoulYoga Admin <i class="caret"></i>

						</a>
							<ul class="dropdown-menu">
								<li><a tabindex="-1" href="adminChangePassword.jsp">Change
										password</a></li>
								<li class="divider"></li>
								<li><a tabindex="-1" href="userLoginOut.action">Logout</a>
								</li>
							</ul></li>
					</ul>
					<ul class="nav">
						<li class="active"><a href="index.jsp">Dashboard</a></li>
						<li class="dropdown"><a href="#" role="button"
							class="dropdown-toggle" data-toggle="dropdown"> 会员中心 <i
								class="caret"></i>
						</a>
							<ul class="dropdown-menu">
								<li><a tabindex="-1" href="memberregister.action">注册会员</a></li>
								<li class="divider"></li>
								<li><a tabindex="-1" href="memberlist.action">会员列表</a></li>
							</ul></li>

					</ul>
				</div>
		
			</div>
		</div>
	
			<div class="row-fluid">
				<!-- block -->
				<div class="block">
					<div class="navbar navbar-inner block-header">
						<div class="muted pull-left">Register</div>
					</div>
					<div class="block-content collapse in">
					<div class="row-fluid ">
						<div class="span12">
							<form class="form-horizontal" action="register.action"
								method="post">
								<fieldset>

									<div class="control-group">
										<label class="control-label" for="member.name">姓名</label>
										<div class="controls">
											<input type="text" id="member.name" name="member.name" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="identityId">身份证号</label>
										<div class="controls">
											<input type="text" id="identityId" name="member.identityId" />
											<span id="identityIdFiled"></span>
										</div>
										
									</div>
									<div class="control-group">
										<label class="control-label" for="member.mobileNo">手机号</label>
										<div class="controls">
											<input type="text" id="member.mobileNo"
												name="member.mobileNo" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label " for="select01">销售顾问</label>
										<div class="controls">
											<select id="select01" class="chzn-select"
												name="member.salesConsultantId">
												<option value="">请选择销售顾问</option>
												<c:forEach items="${requestScope.employee}" var="em">
													<option value="${em.id }">${em.surname}${em.name }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="member.address">联系地址</label>
										<div class="controls">
											<input type="text" id="member.address" name="member.address" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="member.gender">性别</label>
										<div class="controls">
											<input name="member.gender" type="radio" value="0"
												checked="checked" />男 <input name="member.gender"
												type="radio" value="1" />女
										</div>
									</div>

									<div class="control-group">
										<label class="control-label" for="to_date">生日</label>
										<div class="controls">
											<input class="Wdate" type="text" id="to_date" name="birthday"
												onclick="WdatePicker()" />
										</div>
									</div>
									<div class="form-actions">
										<button type="submit" class="btn btn-primary">注册</button>
										<button type="reset" class="btn">取消</button>
									</div>
								</fieldset>
							</form>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
	<!--/.fluid-container-->
</body>

</html>