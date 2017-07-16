<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="no-js">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Member List</title>
<!-- Bootstrap -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet"
	media="screen">
<link href="assets/styles.css" rel="stylesheet" media="screen">
<script src="vendors/jquery-1.11.2.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>

<script type='text/javascript'>
	
</script>
</head>

<body>
	<div class="navbar navbar-fixed-top ">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="index.jsp">Admin Panel</a>
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
						<li class="active"><a href="#">Dashboard</a></li>
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
				<!--/.nav-collapse -->
			</div>
		</div>
		<div class="row-fluid ">
			<div class="span12">
				<!-- block -->

				<div class="navbar navbar-inner block-header">
					<div class="muted pull-left">
						<h4>会员列表</h4>
					</div>
					<div class="muted pull-right">
						<form action="fuzzyfind.action" method="post">
							<input type="text" class="form-control" id="fuzzyName"
								name="fuzzyName" placeholder="输入会员姓名..." /> <input
								type="submit" class="btn btn-primary" value="查找" />
						</form>
					</div>

				</div>
				<div class="block-content  ">
					<table class="table table-striped ">
						<thead>
							<tr>
								<th>会员ID</th>
								<th>会员姓名</th>
								<th>身份证号</th>
								<th>手机号</th>
								<th>联系地址</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.Member}" var="m">
								<tr>

									<td>${m.id}</td>
									<td>${m.name}</td>
									<td>${m.identityId}</td>
									<td>${m.mobileNo }</td>
									<td>${m.address }</td>
									<td><a href="memberupdate.action?id=${m.id}"
										class="btn btn-success" onClick="return confirm('确认修改？')">修改</a></td>
									<td><a href="memberdelete.action?id=${m.id}"
										class="btn btn-danger" onClick="return confirm('确认删除？')">删除</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<!-- /block -->
			</div>
		</div>
	</div>
</body>

</html>