<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html class="no-js">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>部门信息</title>
	<!-- Bootstrap -->
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
	<link href="assets/styles.css" rel="stylesheet" media="screen">
	<script src="vendors/jquery-1.11.2.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	
	<%-- <script type='text/javascript'>
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
	</script> --%>
	<script type="text/javascript">
		
		$(document).ready(function(){
			$("#addBtn").click(function(){
				$("tr:last").after("<tr><td></td><td><input class='input-small' type='text' placeholder='部门名' required='required' /></td><td></td><td></td><td><button class='btn btn-primary addSaveBtn'>保存</button> <button class='btn btn-danger backBtn'>取消</button></td></tr>");
			});
			
			$(".updBtn").click(function(){
				var input = $(this).parent().siblings().find("input");
				input.removeAttr("readonly").focus();
				$(this).parent().html("<button class='btn btn-primary updSaveBtn'>保存</button> <button class='btn btn-danger backBtn'>取消</button>");
			});
			
			$(".delBtn").click(function(){
				var id = $(this).parent().prev().prev().prev().prev().text();
				$.post("delDepartment.action", { id:id }, function(data, status){
					if(status == "success") {
						alert(data);
					} else {
						alert("删除失败！");
					}
					window.location.href = "showDepartment.action";
				});
			});
			
			$("tbody").on("click",".updSaveBtn",function(){
				var id = $(this).parent().prev().prev().prev().prev().text();
				var newVal = $(this).parent().siblings().find("input").val();
				$.post("updDepartment.action", { id:id, departmentName:newVal }, function(data, status){
					if(status == "success") {
						alert(data);
					} else {
						alert("修改失败！");
					}
					window.location.href = "showDepartment.action";
				});
			});
			$("tbody").on("click",".addSaveBtn",function(){
				var newVal = $(this).parent().siblings().find("input").val();
				$.post("addDepartment.action", { departmentName:newVal }, function(data, status){
					if(status == "success") {
						alert(data);
					} else {
						alert("添加失败！");
					}
					window.location.href = "showDepartment.action";
				});
			});
			$("tbody").on("click",".backBtn",function(){
				//待完成--------------------------------------------
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

					<li class="active"><a href="./showDepartment.action"><i class="icon-chevron-right"></i>部门查询</a></li>
					<li><a href="./showPosition.action"><i class="icon-chevron-right"></i>职位查询</a></li>

				</ul>
			</div>
			<!--/span-->
			<div class="span9" id="content">
				<div class="row-fluid">
					<div class="span12">
						<!-- block -->
						<div class="block">
							<div class="navbar navbar-inner block-header">
								<div class="muted pull-left">部门列表</div>
								<!--<div class="pull-right"><span class="badge  badge-info">1,234</span>
								</div>-->
							</div>
							<div class="block-content collapse in">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>部门ID</th>
											<th>部门名称</th>
											<th>创建时间</th>
											<th>修改时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="departments" var="department">
											<tr>
												<td><s:property value="id" /></td>
												<td><input class="input-small" type="text" value="<s:property value="#department.name" />" readonly="readonly" required="required" /></td>
												<td><s:property value="#department.createdTime" /></td>
												<td><s:property value="#department.modifiedTime" /></td>
												<td><button class="btn btn-warning updBtn">修改</button>
													<button class="btn btn-danger delBtn">删除</button></td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
								<button id="addBtn" class="btn btn-success" >添加部门</button>
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
