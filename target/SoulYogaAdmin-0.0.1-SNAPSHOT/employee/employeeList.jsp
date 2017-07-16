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
	<script src="vendors/jquery.form.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			$("form").submit(function() {
				$(this).ajaxSubmit({
					success: function(data) {
						$("#employeeList").html(data);
					}
				});
				return false;
			});
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
						<div class="block">
							<div class="navbar navbar-inner block-header">
								<div class="muted pull-left">员工查询</div>
							</div>
							<div class="block-content collapse in">
								<form action="findEmployeeViaCondition.action" method="post" class="form-horizontal">
									<div class="control-group">
										<label class="control-label" for="inputID">员工ID</label>
										<div class="controls">
											<input type="text" id="inputID" placeholder="请输入员工id" name="employeeView.id">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="inputName">员工名</label>
										<div class="controls">
											<input type="text" id="inputName" placeholder="请输入名字，可输入部分" name="employeeView.name">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="inputClub">工作会所</label>
										<div class="controls">
											<select name="employeeView.yogaClubId">
												<option></option>
												<s:iterator value="yogaclubMap" var="yogaclub">
													<option value="<s:property value="key"/>"><s:property value="value" /></option>
												</s:iterator>
											</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">是否老师</label>
										<div class="controls">
											<label class="radio inline">
												<input type="radio" name="employeeView.isTeacher" value="" checked>未知
											</label>
											<label class="radio inline">
												<input type="radio" name="employeeView.isTeacher" value="1">是
											</label>
											<label class="radio inline">
												<input type="radio" name="employeeView.isTeacher" value="0">否
											</label>
										</div>
									</div>
									<div class="control-group">
										<div class="controls">
											<button type="submit" class="btn btn-primary">查询</button>
										</div>
									</div>
								</form>
							</div>
						</div>
						<!-- block -->
						<div class="block">
							<div class="navbar navbar-inner block-header">
								<div class="muted pull-left">员工列表</div>
							</div>
							<div class="block-content collapse in" id="employeeList">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>员工ID</th>
											<th>电话号码</th>
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
												<td><s:property value="#employeeView.phoneNo" /></td>
												<td><s:property value="#employeeView.surname" /><s:property value="#employeeView.name" /></td>
												<td><s:property value="#employeeView.fristName" />·<s:property value="#employeeView.lastName" /></td>
												<td><s:if test="%{#employeeView.gender == 0}">男</s:if><s:else>女</s:else></td>
												<td><s:property value="#employeeView.mail" /></td>
												<td><s:property value="#employeeView.positionName" /></td>
												<td><s:property value="#employeeView.yogaClubName" /></td>
												<td><a class="btn getDtlBtn" href="./showEmployeeDetail.action?id=<s:property value='#employeeView.id' />">查看 / 修改</a></td>
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
