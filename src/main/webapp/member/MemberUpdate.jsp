<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html class="no-js">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Member Update</title>
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
						<li class="active"><a href="#">Dashboard</a></li>
						<li class="dropdown"><a href="#" role="button"
							class="dropdown-toggle" data-toggle="dropdown"> 会员中心 <i
								class="caret"></i>
						</a>
							<ul class="dropdown-menu">
								<li><a tabindex="-1" href="memberregister.action">注册会员</a></li>
								<li class="divider"></li>
								<li><a tabindex="-1" href="memberlist.action">会员列表</a>
								</li>
							</ul></li>

					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
			<div class="row-fluid">
				<!-- block -->
				<div class="block">
					<div class="navbar navbar-inner block-header">
						<div class="muted pull-left">更新</div>
					</div>
					<div class="block-content collapse in">
								<div class="row-fluid ">
						<div class="span12">
							<form class="form-horizontal" action="update.action"
								method="post">
								<fieldset>

									<div class="controls">
										<input type="hidden" id="member.id" name="member.id"
											value="${memberUpdate.id }" />
									</div>
									<div class="controls">
										<input type="hidden" id="member.createdTime"
											name="member.createdTime"
											value="${memberUpdate.createdTime }" />
									</div>
									<div class="controls">
										<input type="hidden" id="member.state"
											name="member.state"
											value="${memberUpdate.state }" />
									</div>

									<div class="control-group">
										<label class="control-label" for="select01">姓名</label>
										<div class="controls">
											<input type="text" id="member.name" name="member.name"
												value="${memberUpdate.name }" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="select01">身份证号</label>
										<div class="controls">
											<input type="text" id="member.identityId"
												name="member.identityId" value="${memberUpdate.identityId }" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="select01">手机号</label>
										<div class="controls">
											<input type="text" id="member.mobileNo"
												name="member.mobileNo" value="${memberUpdate.mobileNo }" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="select01">销售顾问</label>
										<div class="controls">
											<select id="select01" class="chzn-select"
												name="member.salesConsultantId">
												<c:forEach items="${requestScope.employee}" var="em">
													<option value="${em.id }"
													<c:if test="${memberUpdate.salesConsultantId==em.id}">selected</c:if>
													>${em.surname}${em.name }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="select01">联系地址</label>
										<div class="controls">
											<input type="text" id="member.address" name="member.address"
												value="${memberUpdate.address}" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="select01">性别</label>
										<div class="controls">
										<input name="member.gender" type="radio" value="0" <c:if test="${memberUpdate.gender==0}">checked</c:if> />男 
										<input name="member.gender" type="radio" value="1" <c:if test="${memberUpdate.gender==1}">checked</c:if> />女
										</div>
									</div>

									<div class="control-group">
										<label class="control-label" for="select01">生日</label>
										<div class="controls">
										       <%-- <fmt:formatDate class="Wdate" type="text" id="to_date" name="birthday" 
										        onclick="WdatePicker()" value='${memberUpdate.birthday}' pattern='yyyy-MM-dd' />--%>
												 <input class="Wdate" type="text" id="to_date" name="birthday"
												onclick="WdatePicker()" value="${memberUpdate.birthday}"  /> 
												
										</div>
									</div>
									<div class="form-actions">
										<button type="submit" class="btn btn-primary">修改</button>
										<button type="reset" class="btn">取消</button>
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