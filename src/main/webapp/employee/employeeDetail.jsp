<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html class="no-js">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>详细信息</title>
	<!-- Bootstrap -->
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
	<link href="bootstrap/css/bootstrap-imageupload.min.css" rel="stylesheet" media="screen">
	<link href="assets/styles.css" rel="stylesheet" media="screen">
	<script src="vendors/jquery-1.11.2.min.js"></script>
	<script src="vendors/jquery.form.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="bootstrap/js/bootstrap-imageupload.min.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$(".reupload").hide();
			$(".imageupload").hide();
			if ("${employeeView.positionId}" != "${teacherPositionId}") {
				$(".teacherDetail").hide();
			}
			$(".backBtn").click(function() {
				history.go(-1);
			});
			$("#modifyBtn").click(function() {
				$(".reupload").show();
				$("input").removeAttr("disabled");
				$("textarea").removeAttr("disabled");
				$("select").removeAttr("disabled");
				$("#positionSelector").html("<option></option><s:iterator value='positionMap' var='position'><option value='<s:property value='key'/>'><s:property value='value'/></option></s:iterator>");
				$("#yogaClubSelector").html("<option></option><s:iterator value='yogaclubMap' var='yogaclub'><option value='<s:property value='key'/>'><s:property value='value'/></option></s:iterator>");
				$(".teacherDetail").hide();
				$("#operation").html("<button type='submit' class='btn btn-primary'>保存</button> <button type='button' class='btn btn-danger' id='cancelBtn'>取消</button>");
			});
			$("#deleteBtn").click(function() {
				$.post("delEmployee.action", {employeeId: "${employeeView.id}"}, function(data,status) {
					if (status == "success") {
						alert("success");
						history.go(-1);
					} else {
						alert("fail");
					}
			    });
			});
			$("#operation").on("click", "#cancelBtn", function() {
				window.location.reload();
			});
			$("#positionSelector").bind("change", function() {
				var positionId = $(this).val();
				if (positionId == "${teacherPositionId}") {
					$(".teacherDetail").show();
				} else {
					$(".teacherDetail").hide();
				}
			});
			$("form").submit(function() {
				$(this).ajaxSubmit({
					success: function() {
						alert("修改成功！");
						window.location.reload();
					}
				});
				return false;
			});
			$(".reupload").click(function() {
				$(".imgArea").hide();
				$(".imageupload").imageupload();
				$(".imageupload").show();
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
						<!-- block -->
						<div class="block">
							<div class="navbar navbar-inner block-header">
								<div class="muted pull-left">查看 / 修改</div>
							</div>
							<div class="block-content collapse in">
								<form action="updEmployee.action" method="post" enctype="multipart/form-data" class="form-horizontal">
									<fieldset>
										<legend><span>员工详情</span><a class="btn btn-primary pull-right backBtn"><i class="icon-chevron-left"></i>返回</a></legend>
										<div class="control-group">
											<label class="control-label" for="image">头像</label>
											<div class="controls">
												<div class="imgArea">
													<img alt="头像" class="img-thumbnail" src="<s:property value='employeeView.avatarUrl'/>" width="150px" height="150px"><br>
													<button type="button" class="btn btn-default reupload">重新上传</button>
												</div>
												<div class="imageupload panel panel-default">
													<div class="file-tab panel-body">
														<label class="btn btn-default btn-file">
															<span>浏览</span> <input id="image" type="file" name="image">
														</label>
														<button type="button" class="btn btn-default">移除</button>
														<button type="button" class="btn btn-default">取消</button>
													</div>
												</div>
											</div>
										</div>
										<div class="control-group hidden">
											<label class="control-label" for="id">ID</label>
											<div class="controls">
												<input type="text" id="id" name="employeeView.id" value="<s:property value='employeeView.id'/>" readonly="readonly"/>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="surname">姓</label>
											<div class="controls">
												<input type="text" id="surname" name="employeeView.surname" value="<s:property value='employeeView.surname'/>" disabled="disabled"/>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="name">名</label>
											<div class="controls">
												<input type="text" id="name" name="employeeView.name" value="<s:property value='employeeView.name'/>" disabled="disabled">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="fristName">英文名</label>
											<div class="controls">
												<input type="text" id="fristName" name="employeeView.fristName" value="<s:property value='employeeView.fristName'/>" disabled="disabled">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="lastName">英文姓</label>
											<div class="controls">
												<input type="text" id="lastName" name="employeeView.lastName" value="<s:property value='employeeView.lastName'/>" disabled="disabled">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="phoneNo">电话号码</label>
											<div class="controls">
												<input type="text" id="phoneNo" name="employeeView.phoneNo" value="<s:property value='employeeView.phoneNo'/>" disabled="disabled">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="mail">邮箱</label>
											<div class="controls">
												<input type="text" id="mail" name="employeeView.mail" value="<s:property value='employeeView.mail'/>" disabled="disabled">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="identityId">身份证号</label>
											<div class="controls">
												<input type="text" id="identityId" name="employeeView.identityId" value="<s:property value='employeeView.identityId'/>" disabled="disabled">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">性别</label>
											<div class="controls">
												<s:if test="%{employeeView.gender == 0}">
													<label class="radio inline">
													<input type="radio" name="employeeView.gender" value="0" checked disabled="disabled">男
													</label>
													<label class="radio inline">
														<input type="radio" name="employeeView.gender" value="1" disabled="disabled">女
													</label>
												</s:if>
												<s:else>
													<label class="radio inline">
													<input type="radio" name="employeeView.gender" value="0" disabled="disabled">男
													</label>
													<label class="radio inline">
														<input type="radio" name="employeeView.gender" value="1" checked disabled="disabled">女
													</label>
												</s:else>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">工作分所</label>
											<div class="controls">
												<select id="yogaClubSelector" name="employeeView.yogaClubId" disabled="disabled">
													<option value="employeeView.yogaClubId"><s:property value="employeeView.yogaClubName"/></option>
												</select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">职位</label>
											<div class="controls">
												<select id="positionSelector" name="employeeView.positionId" disabled="disabled">
													<option value="employeeView.positionId"><s:property value="employeeView.positionName"/></option>
												</select>
											</div>
										</div>
										<div class="control-group teacherDetail">
											<label class="control-label" for="courseCategoryIds">老师的课程种类</label>
											<div class="controls">
												<s:iterator value="coursecategoryMap" var="coursecategory">
													<label class="checkbox inline">
														<input type="checkbox" name="employeeView.courseCategoryIds" value="<s:property value="key"/>"
														
														 <s:if test="%{employeeView.courseCategoryIds.indexOf(key+'') >= 0}">checked="checked"</s:if>
														 
														 disabled="disabled"/><s:property value="value" />
														 
													</label>
												</s:iterator>
											</div>
										</div>
										<div class="control-group teacherDetail">
											<label class="control-label" for="introduction">老师介绍</label>
											<div class="controls">
												<textarea id="introduction" class="form-control" rows="3" name="employeeView.introduction" disabled="disabled"><s:property value="employeeView.introduction"/></textarea>
											</div>
										</div>
										<div class="control-group">
											<div class="controls" id="operation">
												<button type="button" class="btn btn-warning" id="modifyBtn">修改</button>
												<button type="button" class="btn btn-danger" id="deleteBtn">删除</button>
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
