<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="no-js">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程checkIn/checkOut 管理</title>
<!-- Bootstrap -->
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="../bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet"
	media="screen">
<link href="../assets/styles.css" rel="stylesheet" media="screen">
<script src="../vendors/jquery-1.11.2.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
<script language="javascript" type="text/javascript"
	src="../bootstrap/js/My97DatePicker/WdatePicker.js"></script>
<link href="../bootstrap/js/My97DatePicker/skin/WdatePicker.css"
	rel="stylesheet" type="text/css">
<script type='text/javascript'>
$(document).ready(function() {
			$("#button").click(function() {
				var mobileNo=$("#mobileNo").val();
				var checkType=$("#checkType").val();
								$.ajax({
											url : "check.action",
											type : "post",
											dataType : "json",
											data : {
												mobileNo : mobileNo,
												checkType : checkType
											},
											success : function(result) {
												console.log(result)
												console.log(typeof result)
												$("#list").html("");
												$("#list").append(
														"	<thead><tr>"
														+"<th>批量操作</th>"
														+"<th>课程名称</th>"
														+"<th>所在教室</th>"
														+"<th>上课时间</th>"
														+"<th>停止checkIn时间</th>"
														+"<th>操作</th>"
														+"	</tr></thead>"
														)
												$.each(result,function(i,item) {
																	$("#list").append(
																				" <tbody><tr> <td>"
																							+ " "
																							+ "</td><td>"
																							+ item.courseName
																							+ "</td><td>"
																							+ item.classroomNo
																							+ "</td><td>"
																							+ item.startTime+"-"+item.endTime
																							+ "</td><td>"
																							+ item.checkInendTime
																							+ "</td><td><a href='updatecourse.action?courseId="
																							+ item.id
																							+ " 'class='btn btn-success' onClick='return confirm('确认CheckIn？')'>CheckIn</a></td>"
																							+ "</td> </tr> 	</tbody>")
																})
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
								<li><a tabindex="-1" href="memberlist.action">操作中心</a></li>
							</ul></li>

					</ul>
				</div>

			</div>
		</div>

		<div class="row-fluid">
			<!-- block -->
			<div class="block">
				<div class="navbar navbar-inner block-header">
					<div class="muted pull-left">课程checkIn/checkOut 管理</div>
				</div>

			</div>

		</div>
		<div class="block-content collapse in">
			<div class="row-fluid ">
				<div class="span12">
					<form class="form-horizontal" action="check.action"
						method="post">
						<fieldset>

							<div class="control-group">
								<label class="control-label" for="mobileNo">会员手机号：</label>
								<div class="controls">
									<input type="text" id="mobileNo" name="mobileNo" />
									CheckIn/Out：
									<select name="checkType" id="checkType">
									<option value="checkin">CheckIn</option>
									<option value="checkout">CheckOut</option>
									</select>
									<button type="button" class="btn btn-primary" id="button">查询</button>
								</div>
							</div>
						</fieldset>
					</form>
					
					<div class="block-content collapse in">
						<table class="table table-hover" id="list">
						</table>
					</div>
					
					
</body>

</html>