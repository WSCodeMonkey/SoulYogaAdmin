<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html class="no-js">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>员工信息</title>
	<!-- Bootstrap -->
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
	<link href="assets/styles.css" rel="stylesheet" media="screen">
	<script src="vendors/jquery-1.11.2.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	
	<script type='text/javascript'>
		(function() {
			var s = document.createElement('script');
			s.type = 'text/javascript';
			s.async = true;
			s.src = (location.protocol == 'https:' ? 'https://ssl.'
					: 'http://static.')
					+ 'gridsumdissector.com/js/Clients/GWD-002498-0C1485/gs.js';
			var firstScript = document.getElementsByTagName('script')[0];
			firstScript.parentNode.insertBefore(s, firstScript);
		})();
	</script>
	<script type="text/javascript">
		
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
						<li class="dropdown">
							<a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">
							<i class="icon-user"></i> SoulYoga Admin <i class="caret"></i></a>
							<ul class="dropdown-menu">
								<li><a tabindex="-1" href="adminChangePassword.jsp">Change password</a></li>
								<li class="divider"></li>
								<li><a tabindex="-1" href="userLoginOut.action">Logout</a></li>
							</ul>
						</li>
					</ul>
					<ul class="nav">
						<li class="active"><a href="#">Dashboard</a></li>
						<li><a href="./showAllEmployee.action">员工管理</a></li>
						<li class="dropdown">
							<a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">
								员工配置
								<i class="caret"></i>
							</a>
							<ul class="dropdown-menu">
								<li><a href="./showDepartment.action">部门管理</a></li>
								<li><a href="./showPosition.action">职位管理</a></li>
							</ul>
						</li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3" id="sidebar">
				<ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">

					<li class="active"><a href="./showAllEmployee.action"><i class="icon-chevron-right"></i>员工查询</a></li>
					<li><a href="./toAddEmployee.action"><i class="icon-chevron-right"></i>添加员工</a></li>

				</ul>
			</div>
			<!--/span-->
			<div class="span9" id="content">
				<div class="row-fluid">
					<div class="span12">
						<!-- block -->
						<div class="block">
							<div class="navbar navbar-inner block-header">
								<div class="muted pull-left">员工列表</div>
								<div class="pull-right"><span class="badge badge-info">1,234</span>
								</div>
							</div>
							<div class="block-content collapse in">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>员工ID</th>
											<th>中文名</th>
											<th>英文名</th>
											<th>性别</th>
											<th>邮箱</th>
											<th>职位</th>
											<th>工作分所</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="employeeViews" var="employeeView">
											<tr>
												<td><s:property value="#employeeView.id" /></td>
												<td><s:property value="#employeeView.surname" /><s:property value="#employeeView.name" /></td>
												<td><s:property value="#employeeView.fristName" /><s:property value="#employeeView.lastName" /></td>
												<td><s:if test="%{#employeeView.gender == 0}">男</s:if><s:else>女</s:else></td>
												<td><s:property value="#employeeView.mail" /></td>
												<td><s:property value="#employeeView.positionName" /></td>
												<td><s:property value="#employeeView.yogaClubName" /></td>
												<td><a class="btn btn-primary getDtlBtn" href="./showEmployeeDetail.action?id=<s:property value='#employeeView.id' />">查看</a></td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</div>
						</div>
						<!-- /block -->
					</div>

				</div>

			</div>
			<hr>

		</div>
		<!--/.fluid-container-->
	</div>
</body>

</html>
