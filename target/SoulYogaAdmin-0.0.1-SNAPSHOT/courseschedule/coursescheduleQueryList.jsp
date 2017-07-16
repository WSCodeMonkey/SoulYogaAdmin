<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html class="no-js">
    
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>排课表</title>
        <!-- Bootstrap -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
         <link href="assets/styles.css" rel="stylesheet" media="screen">
      <script src="vendors/jquery-1.11.2.min.js"></script>
       <script src="bootstrap/js/bootstrap.min.js"></script>
       
       		<script type='text/javascript'>
    (function () {
        var s = document.createElement('script');
        s.type = 'text/javascript';
        s.async = true;
        s.src = (location.protocol == 'https:' ? 'https://ssl.' : 'http://static.') + 'gridsumdissector.com/js/Clients/GWD-002498-0C1485/gs.js';
        var    firstScript = document.getElementsByTagName('script')[0];
        firstScript.parentNode.insertBefore(s, firstScript);
    })();
</script>
       
		<script type="text/javascript">
         jQuery(document).ready(function( $ ) {
        	 $.ajax({
		   	  	   type: "post",  //以post方式与后台沟通 
		   	       url : "getAllCourseList.action", 
		   	       async:false,
		   	       data : {		   	       		
		   	    	},
		    	   error : function(data){
		    		   //alert("project running error.");
		    	   },
		   	       success : function(data){
		   	    	//alert("project running error.");
		    	   }  	
		    });
         });
         
     	function onclick_Submit(dom){

   		 var $a= $(dom).parent().prev().find("input");
   		 var updateNewContent = $a.val(); 

   		 var $b = $(dom).parent().prev().prev().prev().find("input");
   		 var updateResendIntervals  = $b.val(); 
   		 
   		 var $c = $(dom).parent().prev().prev().prev().prev().find("input");
   		 var updateResendTimes = $c.val(); 
   		 
   		 var $d = $(dom).parent().prev().prev().prev().prev().prev();
   		 var updateChannel = $d.html();
   		 
   		 var $e = $(dom).parent().prev().prev().prev().prev().prev().prev();
   		 var updateTemplateId = $e.html();
  
   		 if(!(/^\d+(\.\d{2})?$/.test(updateResendTimes))){
           		alert("重发次数必须为数字！");
                   return;
           	}
         
        	if(!(/^\d+(\.\d{2})?$/.test(updateResendIntervals))){
           		alert("重发间隔必须为数字！");
                   return;
           	}

   		 
   		 
     	 $.ajax({
	   	  	   type: "post",  //以post方式与后台沟通 
	   	       url : "updateCourse.action", 
	   	       async:false,
	   	       data : {		  
	   	    	updateTemplateId:updateTemplateId,	   	 	
	   	 		updateChannel:updateChannel, 	 	
	   	 	 	updateResendTimes:updateResendTimes,	   	 	
	   	 		updateResendIntervals:updateResendIntervals,	
	   	 		updateNewContent:updateNewContent
	   	    	},
	    	   error : function(data){
	    		   alert("update fail.");
	    	   },
	   	       success : function(data){
	   	    	alert("update success.");
	    	   }  	
	    });
   		
     	  //错误提示信息
     	 var msg=""+'${request.tipMessage}'; 
     	  if(msg!=""){ 
     	     alert(msg); 
     	  } 
     	 
     	  
     	  
          function validateResendTimes(){
              var resendTimes= $("#resendTimes").val();
           	if(!(/^\d+(\.\d{2})?$/.test(resendTimes))){
           		alert("重发次数必须为数字！");
                   return false;
           	}
          }
           	
            function validateResendInterval(){
                var resendInterval= $("#resendInterval").val();
             	if(!(/^\d+(\.\d{2})?$/.test(resendInterval))){
             		alert("重发间隔必须为数字！");
                     return false;
             	}
            }
   	}
         
     	/*加载老师id下拉选 */
    	$(document).ready(
    			function() {
    				$("#teacher_id").focus(
    						
    						function() {
    							$("#teacher_id").empty();
    							$.ajax({
    								type : "post",
    								url : "getTeacherNameToQueryPage.action",
    								dataType:"json",
    								success : function(data) {
    								
    									for (var i = 0; i < data.length;i++) {
    										$('#teacher_id').append(
    														"<option value='" + data[i].teacherId + "' >"
    																+ data[i].teacherName
    																+ "</option>");
    									
    									}
    								},
    								error : function() {
    									alert("加载失败");
    								}
    							});
    						});
    			});

    	/*加载课程id下拉选*/
    	
    	$(document).ready(
    			function()  {
    				$("#teacher_id").blur(
    						function() {
    							$("#course_id").empty();
    							var teacher_id = $("#teacher_id").val();
    							//alert(teacher_id)
    							$.ajax({
    								url : "getCourseNameToQueryPage.action",
    								type : "get",
    								dataType:"json",
    								data : {
    									tid : teacher_id
    								},
    								success : function(data) {

    									$('#classroom_id').append(
    											"<option value='' selected='selected' >"
    													+ '请选择' + "</option>");
    									for (var i = 0; i < data.length;i++) {
    										$('#course_id')
    												.append(
    														"<option value='" + data[i].courseId + "' >"
    																+ data[i].courseName
    																+ "</option>");
    									
    									}
    								},
    								error : function() {
    									alert("加载失败");
    								}
    							});

    						});
    			}) 
    			/*加载教室id下拉选*/
	function getclassroom_id() {
		var c_id = $("#course_id").val();
		$("#classroom_id").empty();
		$.ajax({
			type : "post",
			url : "./getClassroomNameToQueryPage.action",
			dataType:"json",
			data : {
				"c_id" : c_id
			},
			success : function(data) {
				//var data = $.parseJSON(wdata);
				alert(data);
				$('#classroom_id').append(
						"<option value='' selected='selected' >" + '请选择'
								+ "</option>");
				for (var i = 0; i < data.length;i++ ) {
					$('#classroom_id').append(
							"<option value='" + data[i].classroomId + "' >" + data[i].classroomName
									+ "</option>");
				
				}
			},
			error : function() {
				alert("加载失败");
			}
		});
	}
		 </script>
    </head>
    
    <body>
        <div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container-fluid">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                    </a>
                    <a class="brand" href="#">Admin Panel</a>
                    <div class="nav-collapse collapse">
                        <ul class="nav pull-right">
                            <li class="dropdown">
                                <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-user"></i> SoulYoga Admin <i class="caret"></i>

                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a tabindex="-1" href="adminChangePassword.jsp">Change password</a>
                                    </li>
                                    <li class="divider"></li>
                                    <li>
                                        <a tabindex="-1" href="userLoginOut.action">Logout</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                        <ul class="nav">
                            <li class="active">
                                <a href="#">Dashboard</a>
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
                        
                        <li class="active">
                            <a href="./getAllCourseList.action"><i class="icon-chevron-right"></i> 课程查询</a>
                            
                        </li>
                        <li class="active"><a href="./getAllCoursecategory.action"><i
							class="icon-chevron-right"></i> 课程各类查询</a></li>
							 <li class="active"><a href="./getAllCoursesecheduleList.action"><i
							class="icon-chevron-right"></i> 排课表查询</a></li>
	
                    </ul>
                </div>
                <!--/span-->
                <div class="span9" id="content">
				
					<div class="row-fluid">
                        <!-- block -->
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">选择排课表查询条件</div>
                            </div>
                            <div class="block-content collapse in">
                                <div class="span12">
                                    <form class="form-horizontal"  action="getPartCoursesecheduleList.action" method="post">
                                      <fieldset>

                                        <div class="control-group">
                                          <label class="control-label" for="teacher_id">老师ID</label>
                                          <div class="controls">
                                          <select id="teacher_id" class="chzn-select" name="teacher_id_name">
                                            	<option value="">All</option>
                                            </select>
                                           
                                          </div>
                                        </div>
										<div class="control-group">
                                          <label class="control-label" for="course_id">课程ID</label>
                                          <div class="controls"> 
                                          <select id="course_id" class="chzn-select" name="course_id_name" onblur="getclassroom_id()">
                                            	<option value="">All</option>
                                            </select>
                                           
                                          </div>
                                        </div>
                                        	<div class="control-group">
                                          <label class="control-label" for="classroom_id">教室ID</label>
                                          <div class="controls">
                                           <select id="classroom_id" class="chzn-select" name="classroom_id_name">
                                            	<option value="">All</option>
                                            </select>
                                            
                                          </div>
                                        </div>
                                        <div class="form-actions">
                                          <button type="submit" class="btn btn-primary">check</button>
                                          <button type="reset" class="btn">Cancel</button>
                                        </div>
                                      </fieldset>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid" >
                        <div class="span12">
                            <!-- block -->
                            <div class="block">
                                <div class="navbar navbar-inner block-header">
                                    <div class="muted pull-left">课程安排列表</div>
                                    <!--<div class="pull-right"><span class="badge  badge-info">1,234</span>
									
                                    </div>-->
                                </div>
                                <div class="block-content collapse in">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                               
                                                <th>课程时刻表 ID</th>
                                                <th>老师ID</th>
												<th>课程ID</th>
												<th>所属教室ID</th>
												<th>开始上课日期</th>
												<th>上课时间 </th>
												<th>下课时间</th>
												<th>预约开始时间</th>
												<th>预约结束时间</th>
												<th>取消开始时间</th>
												<th>取消结束时间</th>
												<th>checkin结束时间</th>
												<!--  
												<th>创建时间</th>
												<th>修改时间</th>
												-->
												
												<th>删除</th>
												<th>修改</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        
                                         <s:iterator value="#request.allCourseschedules_list" var="template" >
                                            <tr>
                                                <td ><s:property value="#template.id"/></td>
                                                <!--  
                                                <td><s:property value="#template.teacherId" /></td>
												<td><s:property value="#template.courseId" /></td>
												<td><s:property value="#template.classroomId" /></td>
												-->
												<td><s:property value="#template.teacherName" /></td>
												<td><s:property value="#template.courseName" /></td>
												<td><s:property value="#template.classroomName" /></td>
												
                                                <td><s:property value="#template.scheduleDate" /></td>
                                                <td><s:property value="#template.startTime" /></td>
                                                <td><s:property value="#template.endTime" /></td>
                                                <td><s:property value="#template.reservationStartTime" /></td>
                                                <td><s:property value="#template.reservationEndTime" /></td>
                                                <td><s:property value="#template.cancellationStartTime" /></td>
                                                <td><s:property value="#template.cancellationEndTime" /></td>
                                                <td><s:property value="#template.checkinEndTime" /></td>
                                                <!--  
                                                <td><s:property value="#template.createdTime" /></td>
                                                 <td><s:property value="#template.modifiedTime" /></td>
                                                 -->
                                                <td><a href="deleteOneCourseshedule.action?deleteCoursescheduleId=<s:property value="#template.id"/>">删除</a></td>
                                                <td><a href="beforModify.action?modifiedId=<s:property value="#template.id"/>">修改</a></td>
                                               
                                            </tr>
                                          </s:iterator>
                                        </tbody>
                                        <tr style="align:center"><td><a href="getTeacherId.action">添加</a></td></tr>
                                    </table>
                                </div>
                            </div>
                         
                        </div>
						
                    </div>
					
            </div>
            <hr>

        </div>


    </body>

</html>