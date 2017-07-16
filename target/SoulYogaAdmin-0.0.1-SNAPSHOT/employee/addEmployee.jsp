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
	<link href="bootstrap/css/bootstrap-imageupload.min.css" rel="stylesheet" media="screen">
	<link href="assets/styles.css" rel="stylesheet" media="screen">
	<script src="vendors/jquery-1.11.2.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="bootstrap/js/bootstrap-imageupload.min.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$(".imageupload").imageupload();
			
			$(".teacherDetail").hide();
			$("#positionSelector").bind("change", function() {
				var positionId = $(this).val();
				if (positionId == "${teacherPositionId}") {
					$("#image").attr("required", "required");
					$(".teacherDetail").show();
				} else {
					$("#image").removeAttr("required");
					$(".teacherDetail").hide();
				}
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
							<div class="navbar navbar-inner block-header">
								<div class="muted pull-left">新增员工</div>
							</div>
							<div class="block-content collapse in">
								<form action="addEmployee.action" method="post" enctype="multipart/form-data" class="form-horizontal">
									<fieldset>
										<legend>填写员工信息</legend>
										<div class="control-group">
											<label class="control-label" for="image">上传头像</label>
											<div class="controls">
												<div class="imageupload panel panel-default">
													<div class="file-tab panel-body">
														<label class="btn btn-default btn-file">
															<span>浏览</span> <input id="image" type="file" name="image">
														</label>
														<button type="button" class="btn btn-default">移除</button>
													</div>
												</div>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="surname">中文姓</label>
											<div class="controls">
												<input type="text" id="surname" name="employeeView.surname" placeholder="中文姓" required="required">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="name">中文名</label>
											<div class="controls">
												<input type="text" id="name" name="employeeView.name" placeholder="中文名" required="required">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="fristName">英文名</label>
											<div class="controls">
												<input type="text" id="fristName" name="employeeView.fristName" placeholder="英文名" required="required">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="lastName">英文姓</label>
											<div class="controls">
												<input type="text" id="lastName" name="employeeView.lastName" placeholder="英文姓" required="required">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="phoneNo">电话号码</label>
											<div class="controls">
												<input type="text" id="phoneNo" name="employeeView.phoneNo" placeholder="电话号码" required="required">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="mail">邮箱</label>
											<div class="controls">
												<input type="email" id="mail" name="employeeView.mail" placeholder="邮箱" required="required">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="identityId">身份证号</label>
											<div class="controls">
												<input type="text" id="identityId" name="employeeView.identityId" placeholder="身份证号" required="required">
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
													<option></option>
													<s:iterator value="yogaclubMap" var="yogaclub">
														<option value="<s:property value="key"/>"><s:property value="value"/></option>
													</s:iterator>
												</select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">职位</label>
											<div class="controls">
												<select id="positionSelector" name="employeeView.positionId">
													<option></option>
													<s:iterator value="positionMap" var="position">
														<option value="<s:property value="key"/>"><s:property value="value"/></option>
													</s:iterator>
												</select>
											</div>
										</div>
										<div class="control-group teacherDetail">
											<label class="control-label" for="courseCategoryIds">老师的课程种类</label>
											<div class="controls">
												<s:iterator value="coursecategoryMap" var="coursecategory">
													<label class="checkbox inline">
														<input type="checkbox" name="employeeView.courseCategoryIds" value="<s:property value="key"/>"><s:property value="value"/>
													</label>
												</s:iterator>
											</div>
										</div>
										<div class="control-group teacherDetail">
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
