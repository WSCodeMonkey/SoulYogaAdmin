<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="no-js">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询/修改课程</title>
<!-- Bootstrap -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet"
	media="screen">
<link href="assets/styles.css" rel="stylesheet" media="screen">
<script src="vendors/jquery-1.11.2.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>

<script type='text/javascript'>
	$(document)
			.ready(
					function() {
						$(".condition")
								.change(
										function() {

											var name = $("#courseConditionname")
													.val();
											var yogaClubID = $(
													"#courseConditionyogaClubID")
													.val();
											var courseCategoryID = $(
													"#courseConditioncourseCategoryID")
													.val();
											$
													.ajax({
														url : "ListByCondition.action",
														type : "post",
														dataType : "json",
														data : {
															Conditionname : name,
															ConditionyogaClubID : yogaClubID,
															ConditioncourseCategoryID : courseCategoryID
														},
														success : function(
																result) {
															console.log(result)
															console
																	.log(typeof result)
															$("#list tbody")
																	.html("");
															$
																	.each(
																			result,
																			function(
																					i,
																					item) {
																				$(
																						"#list tbody")
																						.append(
																								" <tr> <td>"
																										+ item.id
																										+ "</td><td>"
																										+ item.name
																										+ "</td><td>"
																										+ item.coursecategoryName
																										+ "</td><td>"
																										+ item.yogaclubName
																										+ "</td><td>"
																										+ item.introduction
																										+ "</td><td>"
																										+ item.point
																										+	"</td><td><a href='updatecourse.action?courseId="
																										+item.id
																										+ " 'class='btn btn-success' onClick='return confirm('确认修改？')'>修改</a></td>"
																										+"	<td><a href='delcourse.action?courseId="
																										+item.id
																										+" 'class='btn btn-danger' onClick='return confirm('确认删除？')'>删除</a></td>"
																										+ "</td> </tr>" )

																			})

														}

													});
										})

					})
</script>
</head>

<body class="table-responsive">
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
						<li class="active"><a href="#">Dashboard</a></li>
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
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<!-- block -->
				<div class="block">
					<div class="navbar navbar-inner block-header">
						<div class="muted pull-left">课程列表</div>
						<div class="muted pull-right">
							课程名称 <input type="text" class="condition"
								id="courseConditionname" name="courseCondition.name"
								placeholder="请输入要查找的课程名称...">
						</div>
						<div class="muted pull-right">
							选择会所<select name="courseCondition.yogaClubID" class="condition"
								id="courseConditionyogaClubID">
								<option value="null">All</option>
								<c:forEach items="${requestScope.yogaClublist}" var="yc">
									<option value="${yc.id }">${yc.name }</option>
								</c:forEach>

							</select>
						</div>

						<div class="muted pull-right">
							选择课程种类<select name="courseCondition.courseCategoryID"
								class="condition" id="courseConditioncourseCategoryID">
								<option value="null">All</option>
								<c:forEach items="${requestScope.Categorylist}" var="cg">
									<option value="${cg.id }">${cg.name }</option>
								</c:forEach>
							</select>
						</div>

					</div>
					<div class="block-content collapse in">
						<table class="table table-hover" id="list">
							<thead>
								<tr>

									<th>课程 ID</th>
									<th>课程名称</th>
									<th>课程种类</th>
									<th>所属会所</th>
									<th>课程介绍</th>
									<th>点数</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${requestScope.course_list}" var="m">
									<tr>
										<td>${m.id}</td>
										<td>${m.name}</td>
										<td>${m.coursecategoryName}</td>
										<td>${m.yogaclubName }</td>
										<td>${m.introduction }</td>
										<td>${m.point }</td>
										<td><a href="updatecourse.action?courseId=${m.id}"
											class="btn btn-success" onClick="return confirm('确认修改？')">修改</a></td>
										<td><a href="delcourse.action?courseId=${m.id}"
											class="btn btn-danger" onClick="return confirm('确认删除？')">删除</a></td>
									</tr>
								</c:forEach>

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


</body>

</html>