<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="no-js">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Course</title>
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
						<div class="muted pull-left">更新课程</div>
					</div>
					<div class="block-content collapse in">
					<div class="row-fluid ">
						<div class="span12">
							<form class="form-horizontal" action="reupdatecourse.action"
								method="post">
								<fieldset>
     								<input type="hidden" id="course.iD" name="courseId" value="${requestScope.CourseInfo.iD}" />
     								<input type="hidden" id="course.createdTime" name="course.createdTime" value="${requestScope.CourseInfo.createdTime}" />		
									<div class="control-group">
										<label class="control-label" for="course.name">课程名称</label>
										<div class="controls">
											<input type="text" id="course.name" name="course.name" value="${requestScope.CourseInfo.name}" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="select01">课程种类</label>
										<div class="controls">
												<select id="select01" class="chzn-select"
												name="course.courseCategoryID">
												<option value="">请选择课程种类</option>
												<c:forEach items="${requestScope.courseCategory}" var="em">
													<option value="${em.id }"
													<c:if test="${requestScope.CourseInfo.courseCategoryID==em.id}">selected</c:if>
													>${em.name }</option>
												</c:forEach>
											</select>
										</div>
										
									</div>
									<div class="control-group">
										<label class="control-label" for="select02">会所</label>
										<div class="controls">
												<select id="select02" class="chzn-select"
												name="course.yogaClubID">
												<option value="">请选择会所</option>
												<c:forEach items="${requestScope.yogaClub}" var="em">
													<option value="${em.id }"
													<c:if test="${requestScope.CourseInfo.yogaClubID==em.id}">selected</c:if>
													>${em.name }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label " for="course.introduction">课程介绍</label>
										<div class="controls">
											<input type="text" id="course.introduction" name="course.introduction" value="${requestScope.CourseInfo.introduction}"/>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="course.point">扣除点数（可选择）</label>
										<div class="controls">
											<input type="text" id="course.point" name="course.point" value="${requestScope.CourseInfo.point}" />
										</div>
									</div>
									
									<div class="form-actions">
										<button type="submit" class="btn btn-primary">修改</button>
										<button type="reset" class="btn">重置</button>
									</div>
								</fieldset>
							</form>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--/.fluid-container-->
</body>

</html>