<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html class="no-js">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>添加员工</title>
	<!-- Bootstrap -->
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
	<link href="assets/styles.css" rel="stylesheet" media="screen">
	<script src="vendors/jquery-1.11.2.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	
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

					<li><a href="./showAllEmployee.action"><i class="icon-chevron-right"></i>员工查询</a></li>
					<li class="active"><a href="./toAddEmployee.action"><i class="icon-chevron-right"></i>添加员工</a></li>
					
				</ul>
			</div>
			<!--/span-->
			<div class="span9" id="content">
				<div class="row-fluid">
					<div class="span12">
						<!-- block -->
						<div class="block">
							<div class="block-content collapse in">
								<form action="addEmployee.action" method="post" class="form-horizontal">
									<fieldset>
										<legend>新增员工</legend>
										<div class="control-group">
											<label class="control-label" for="surname">姓</label>
											<div class="controls">
												<input type="text" id="surname" name="employeeView.surname" placeholder="中文姓氏">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="name">名</label>
											<div class="controls">
												<input type="text" id="name" name="employeeView.name" placeholder="中文名">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="fristName">First Name</label>
											<div class="controls">
												<input type="text" id="fristName" name="employeeView.fristName" placeholder="firstname">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="lastName">Last Name</label>
											<div class="controls">
												<input type="text" id="lastName" name="employeeView.lastName" placeholder="lastname">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="mail">邮箱</label>
											<div class="controls">
												<input type="text" id="mail" name="employeeView.mail" placeholder="邮箱">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="identityId">身份证号</label>
											<div class="controls">
												<input type="text" id="identityId" name="employeeView.identityId" placeholder="身份证号">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">性别</label>
											<div class="controls">
												<label class="radio inline">
												<input type="radio" name="employeeView.gender" value="0" checked>男
												</label>
												<label class="radio inline">
													<input type="radio" name="employeeView.gender" value="1">女
												</label>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">工作分所</label>
											<div class="controls">
												<select name="employeeView.yogaClubId">
													<option value="1">1</option>
												</select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">职位</label>
											<div class="controls">
												<select name="employeeView.positionId">
													<option></option>
													<s:iterator value="positionViews" var="positionView">
														<option value="#positionView.id"><s:property value="#positionView.positionName"/></option>
													</s:iterator>
												</select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="courseCategoryIds">选择老师的课程种类</label>
											<div class="controls">
												<label class="checkbox inline">
													<input type="checkbox" name="employeeView.courseCategoryIds" id="inlineCheckbox3" value="1">1
												</label>
												<label class="checkbox inline">
													<input type="checkbox" name="employeeView.courseCategoryIds" id="inlineCheckbox3" value="2">2
												</label>
												<label class="checkbox inline">
													<input type="checkbox" name="employeeView.courseCategoryIds" id="inlineCheckbox3" value="3">3
												</label>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="introduction">老师介绍</label>
											<div class="controls">
												<textarea id="introduction" class="form-control" rows="3" name="employeeView.introduction"></textarea>
											</div>
										</div>
										<div class="control-group">
											<div class="controls">
												<button type="submit" class="btn btn-success">确认添加</button>
											</div>
										</div>
										
									</fieldset>
								</form>
							</div>
						</div>
						<!-- /block -->
					</div>

				</div>

			</div>

		</div>
		<!--/.fluid-container-->
	</div>
</body>

</html>
