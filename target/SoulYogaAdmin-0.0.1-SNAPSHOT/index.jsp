<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html class="no-js">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>瑜伽，你一生的选择</title>
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
	<div class="navbar navbar-fixed-top">
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


						<li class="dropdown"><a href="#" role="button"
							class="dropdown-toggle" data-toggle="dropdown">空间管理 <i
								class="caret"></i>
						</a>
							<ul class="dropdown-menu">
								<li class=""><a href="./getAllYogaclubList.action"><i
										class="icon-chevron-right"></i>瑜伽会所查询</a></li>
								<li class=""><a href="space/yogaclubAdd.jsp"><i
										class="icon-chevron-right"></i>增加瑜伽会所</a></li>

								<li class=""><a href="./getAllYogaclubList.action"><i
										class="icon-chevron-right"></i>教室查询</a></li>
								<li class=""><a href="space/classroomAdd.jsp"><i
										class="icon-chevron-right"></i>添加教室</a></li>

							</ul></li>


						<li class="dropdown"><a href="#" role="button"
							class="dropdown-toggle" data-toggle="dropdown">课程管理 <i
								class="caret"></i>
						</a>
							<ul class="dropdown-menu">
								<li><a tabindex="-1" href="addcourse.action">课程添加</a></li>
								<li class="divider"></li>
								<li><a tabindex="-1" href="getAllCoursecategory.action">课程种类</a></li>
								<li class="divider"></li>
								<li><a tabindex="-1" href="getAllCourseList.action">课程表</a></li>
								<li class="divider"></li>
								<li><a tabindex="-1"
									href="getAllCoursesecheduleList.action">课程安排表</a></li>

							</ul></li>



						<li class="dropdown"><a href="./showAllEmployee.action"
							role="button" class="dropdown-toggle" data-toggle="dropdown">员工管理<i
								class="caret"></i>
						</a>
							<ul class="dropdown-menu">
								<li><a tabindex="-1" href="./showAllEmployee.action">员工查询</a></li>
								<li class="divider"></li>
								<li><a tabindex="-1" href="./addEmployee.action">添加员工</a></li>

							</ul></li>

						<li class="dropdown"><a href="./showDepartment.action"
							role="button" class="dropdown-toggle" data-toggle="dropdown">员工配置<i
								class="caret"></i>
						</a>
							<ul class="dropdown-menu">
								<li><a tabindex="-1" href="./showDepartment.action">部门管理</a></li>
								<li class="divider"></li>
								<li><a tabindex="-1" href="./showPosition.action">职位管理</a></li>

							</ul></li>
						<li class="dropdown"><a href="./showDepartment.action"
							role="button" class="dropdown-toggle" data-toggle="dropdown">产品管理<i
								class="caret"></i>
						</a>
							<ul class="dropdown-menu">
								<li><a tabindex="-1" href="./addProductRedirect.action">添加产品</a></li>
								<li class="divider"></li>
								<li><a tabindex="-1" href="./showPosition.action"></a></li>

							</ul></li>
						<li class="dropdown"><a href="#" role="button"
							class="dropdown-toggle" data-toggle="dropdown">预约管理<i
								class="caret"></i>
						</a>
							<ul class="dropdown-menu">
								<li><a tabindex="-1" href="order/check.jsp">课程CheckIn/Out</a></li>
								<li class="divider"></li>
								<li><a tabindex="-1" href="./addEmployee.action">……</a></li>

							</ul></li>


					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<img alt="" src="images/Yaga.jpg">
	</div>
	<!--/.fluid-container-->
</body>

</html>