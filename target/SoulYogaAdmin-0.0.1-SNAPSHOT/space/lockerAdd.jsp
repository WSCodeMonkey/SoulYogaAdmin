<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="no-js">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Member Management</title>
<!-- Bootstrap -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="bootstrap/css/bootstrap-responsive.min.css"
	rel="stylesheet" media="screen">
<link href="assets/styles.css" rel="stylesheet" media="screen">
<script src="vendors/jquery-1.11.2.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>

<script type='../text/javascript'>

</script>
<script type="text/javascript">
function selectYogaclub(data){

	  $.ajax({
	  	   type: "post",  //以post方式与后台沟通 
	       url : "./getAllYogaclubList.action", 
	       async:false,
	       data : {		  
	    
	    	},
 	 
	       success : function(data){
		      var yogaclub=JSON.parse(data);
		       if(jQuery("#allYogaclub").val()==null){ 
		     $.each(yogaclub,function(i,item){
			       
                   jQuery("#allYogaclub").append("<option value="+item.id+">"+item.name+"</option>");
			       } ); 
		       } 
		       },
/*  		   document.location.href = "./getAllYogaclubList.action";
 */ 	          
		       error : function(data){
		 		   alert("update fail.");
		 	   }
	  })

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
                                             <li class="">
                            <a href="../getAllYogaclubList.action"><i class="icon-chevron-right"></i>瑜伽会所查询</a>
                        </li>
						 <li class="">
                            <a href="yogaclubAdd.jsp"><i class="icon-chevron-right"></i>增加瑜伽会所</a>
                        </li>
                                
                             <li class="">
                            <a href="../getAllClassroom.action"><i class="icon-chevron-right"></i>教室查询</a>
                        </li>
                             <li class="">
                            <a href="classroomAdd.jsp"><i class="icon-chevron-right"></i>增加教室</a>
                        </li>
                                   
                                </ul>
                            </li>

					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>

		<hr>

		<div class="span9" id="content" >
			<div class="row-fluid">
				<!-- block -->
				<div class="block">
					<div class="navbar navbar-inner block-header">
						<div class="muted pull-left">添加柜子</div>
					</div>
					<div class="block-content collapse in">
						<div class="span12">
							<form class="form-horizontal" action="./addLocker.action"
								method="post">
								<fieldset>

									<div class="control-group">
										<label class="control-label" for="select01">所属门店</label>
										<div class="controls">
                                           <select name="allYogaclub"  id="allYogaclub" onclick="selectYogaclub()">                                                                                                                                                                            
                                           </select>


										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="select01">柜子数量</label>
										<div class="controls">
										<input type="number" id="lockercount" name="lockercount"/>
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
	</div>

	<!--/.fluid-container-->
</body>

</html>