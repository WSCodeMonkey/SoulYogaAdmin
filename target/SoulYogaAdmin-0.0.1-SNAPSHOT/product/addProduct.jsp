
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>jQuery bootstrap-select��������ѡ�����б���</title>
<script src="vendors/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap-select.js">
	
</script>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap-select.css">

<!-- 3.0 -->

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="bootstrap/js/bootstrap.min.js"></script>

<!-- 2.3.2
    <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet">
   

 <script src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.js"></script>
    -->

<script type="text/javascript">
	$(window).on('load', function() {

		$('.selectpicker').selectpicker({
			'selectedText' : 'cat'
		});

		// $('.selectpicker').selectpicker('hide');
	});
</script>
<script type="text/javascript">
	$(document).ready(
			function() {
				alert("页面加载")

				$.ajax({
					type : "post", //以post方式与后台沟通 
					url : "./getAllCourseList.action",
					async : false,
					data : {

					},

					success : function(data) {
						var course = JSON.parse(data);
						$.each(course, function(i, item) {
							if (item.id == 1) {
								jQuery("#classnotcontained").append(
										"<option selected value="+item.id+">"
												+ item.name + "</option>");
							}

							 
						else {  
								jQuery("#classnotcontained").append(
										"<option  value="+item.id+">"
												+ item.name + "</option>");
							     
							}

						

							});

                         
						},
					/*  		   document.location.href = "./getAllYogaclubList.action";
					 */
					error : function(data) {
						alert("update fail.");
					}
				})

			});
</script>
<script type="text/javascript">
	function showtimes(data) {

		var selectoption = data.options[data.selectedIndex].value;
		 if (selectoption == 1) {
            
			 jQuery("#times").attr('type','number');                           
        
		 }
			else {
				 jQuery("#times").attr('type','hidden')                           
			     jQuery ("#timeslabel").append("");	

				}

			
			}
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
						<li class="dropdown"><a href="#" role="button"
							class="dropdown-toggle" data-toggle="dropdown">SoulYoga Admin
								<i class="caret"></i>
						</a>
							<ul class="dropdown-menu">
								<li class=""><a href="../getAllYogaclubList.action"><i
										class="icon-chevron-right"></i>瑜伽会所查询</a></li>
								<li class=""><a href="yogaclubAdd.jsp"><i
										class="icon-chevron-right"></i>增加瑜伽会所</a></li>

								<li class=""><a href="../getAllClassroom.action"><i
										class="icon-chevron-right"></i>教室查询</a></li>
								<li class=""><a href="classroomAdd.jsp"><i
										class="icon-chevron-right"></i>增加教室</a></li>

							</ul></li>

					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<div class="span9" id="content">
		<div class="row-fluid">

			<div class="block">
				<div class="navbar navbar-inner block-header">
					<div class="muted pull-left">添加产品</div>
				</div>
				<div class="block-content collapse in">
					<div class="span12">
						<form class="form-horizontal" action="./addProduct.action"
							method="get">
							<fieldset>

								<div class="control-group">
									<label class="control-label" for="select01">产品名称</label>
									<div class="controls">
										<input type="text" id="productname" name="productname">


									</div>
								</div>

								<div class="control-group">
									<label class="control-label" for="select01">卡类型</label>
									<div class="controls">
										<select id="producttype" onclick="showtimes(this)" name="producttype">
											<option value="1">次卡</option>
											<option value="2">月卡</option>
											<option value="3">年卡</option>
										</select>

									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="select01" id="timeslabel">次数</label>
									<div class="controls">
										<input  id="times" type="hidden" name="times">
									</div>
								</div>

								<div class="control-group">
									<label class="control-label" for="select01">不包括的课程</label>
									<div class="controls">
										<select id="classnotcontained"
											class=" selectpicker bla bla bli >" multiple
											data-live-search="true" name="classnotcontained">

										</select>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="select01">不能使用时间</label>
									<div class="controls">
										<select id="timenotuse" name="timenotuse">
											<option value="1">周末</option>
											<option value="2">假期</option>
										</select>

									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="select01">激活日期类型</label>
									<div class="controls">
										<select id="activetime" name="activetime">
											<option value="1">购买当日</option>
											<option value="2">首次上课</option>
											<option value="3">自选日期</option>
										</select>

									</div>
								</div>


								<div class="control-group">
									<label class="control-label" for="select01">使用时间段</label>
									<div class="controls">
										<input id="starttime" type="datetime" name="datetime"> - <input
											id="endtime" type="datetime" name="endtime">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="select01" id="timeslabel">价格</label>
									<div class="controls">
										<input  name="price" id="price" type="number">
									</div>
								</div>


								<div class="form-actions">
									<button type="submit" class="btn btn-primary">添加</button>
									<button type="reset" class="btn">取消</button>



								</div>



							</fieldset>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>






</body>
</html>
