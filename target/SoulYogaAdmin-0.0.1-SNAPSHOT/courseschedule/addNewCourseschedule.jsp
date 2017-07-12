<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
       <script src="laydate-v1.1/laydate/laydate.js"></script>
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
		  /*加载老师id下拉选
	    $(function () {
	        $.ajax({
	            type: "post",
	            url: "../getTeacherId.action",
	            success: function (data) {
	                for (var i = 0; i < data.length; i++) {
	                    $('#teacher_id').append("<option value='" + data.get(i) + "' >" + data.get(i) + "</option>");
	                }
	            },
	            error: function () {
	                alert("加载失败");
	            }
	        });
	    });
		  */
	    /*加载课程id下拉选*/
	    $(document).ready(function(){
	    	
			$("#teacher_id").blur(
												function() {
													var teacher_id = $(
															"#teacher_id")
															.val();
													alert(teacher_id)
													$.ajax({
																url : "getCourseNameToAddPage.action",
																type : "get",
																dataType:"json",
																data : {
																	tid : teacher_id
																},
																success : function(data) {
																	//var data=JSON.parse(date);
																	//alert(wdata);
																//	alert(wdata[0].courseId );

																	//var data = $.parseJSON(wdata);
																	//var data1=eval("("+data+")");
																	//alert(data);
																	//alert(data[0].courseId );
																	// alert(data);
																	//alert(data[0]);
																	//       $('#course_id').append("<option value='' selected='selected' >" + '请选择' + "</option>");
																	$('#classroom_id').append(
																					"<option value='' selected='selected' >"
																							+ '请选择'
																							+ "</option>");
																	for (var i = 0; i < data.length; i++) {
																		$('#course_id').append(
																						"<option value='" + data[i].courseId + "' >"
																								+ data[i].courseName
																								+ "</option>");
																	}
																},
																error : function() {
																	alert("加载失败");
																}
															});
												})

							})

			/*加载课程id下拉选
			function getcourse_id() {
			 var id = $("#teacher_id").val();
			 $("#course_id").empty();
			 $("#classroom_id").empty();
			 $.ajax({
			     type: "post",
			     url: "../getCourseId.action",
			     data: {"tid": id},
			     success: function (data) {
			         $('#course_id').append("<option value='' selected='selected' >" + '请选择' + "</option>");
			         $('#classroom_id').append("<option value='' selected='selected' >" + '请选择' + "</option>");
			         for (var i = 0; i < data.length; i++) {
			             $('#course_id').append("<option value='" + data.get(i) + "' >" + data.get(i) + "</option>");
			         }
			     },
			     error: function () {
			         alert("加载失败");
			     }
			 });
			}
			;
			 */
			/*加载教室id下拉选*/
			function getclassroom_id() {
				var c_id = $("#course_id").val();
				$("#classroom_id").empty();
				$.ajax({
					type : "post",
					url : "./getClassroomNameToAddPage.action",
					dataType:"json",
					data : {
						"c_id" : c_id
					},
					success : function(data) {
						//var data = $.parseJSON(wdata);
						//alert(data);
						$('#classroom_id').append(
								"<option value='' selected='selected' >"
										+ '请选择' + "</option>");
						for (var i = 0; i < data.length; i++) {
							$('#classroom_id').append(
									"<option value='" + data[i].classroomId+ "' >"
											+ data[i].classroomName + "</option>");
						}
					},
					error : function() {
						alert("加载失败");
					}
				});
			}
			/*
			function onclick_Add(){
			 var teacher_id=$("#teacher_id").val();
			 var course_id=$("#course_id").val();
			 var classroom_id=$("#classroom_id").val();
			 var scheduleDate=$("#scheduleDate").val();
			 var startTime=$("#startTime").val();
			 var endTime=$("#endTime").val();
			var reservationStartTime=$("#reservationStartTime").val();
			var reservationEndTime=$("#reservationEndTime").val();
			var cancellationStartTime=("#cancellationStartTime").val();
			var cancellationEndTime=$("#cancellationEndTime").val();
			var checkinEndTime=$("#checkinEndTime").val();
			window.location.href="?teacher_id=";
			
			}
			 */
		</script>
    </head>
    
    <body>
          
                <!--/span-->
         
                    <div class="row-fluid" >
                        <div class="span12">
                            <!-- block -->
                            <div class="block">
                                <div class="navbar navbar-inner block-header">
                                    <div class="muted pull-left">课程安排列表</div>
                                    <!--<div class="pull-right"><span class="badge  badge-info">1,234</span>
									
                                    </div>-->
                                </div>
                                <form action="addCourse.action" method="post">
                                <div class="block-content collapse in">
                                    <table class="table table-striped">
                                        <thead>
                                        
                                          
	                                         <tr><td>老师ID</td>
					                                            <td>
					                                               <select id="teacher_id" name="teacherId" >
					                                               <option value="">请选择teacher</option>
					                                               <c:forEach items="${CList }" var="id"> 
					                                               <option value="${id.teacherId }">${id.teacherName }</option>
					                                               </c:forEach>
				                                                       
				                                                   </select>
				                                                   </td>
	                                         </tr>
                                               <tr><td>课程ID</td> 
                                                                  <td>
					                                               <select id="course_id" name="courseId" onchange="getclassroom_id() ">
				                                                        <option value="">请选择</option>
				                                                   </select>
				                                                   </td>
                                               
                                               </tr>
                                               <tr><td>所属教室ID</td> 
                                                                    <td>
					                                               <select id="classroom_id" name="classroomId" >
				                                                        <option value="">请选择</option>
				                                                   </select>
				                                                   </td>
                                               </tr>
                                               <tr><td>开始上课日期</td><td><input id="scheduleDate" type="date" name="scheduleDate"></td>
                                               
                                                </tr>
                                               <tr><td>上课时间</td> 
                                                   <td><input type="time"  id="startTime" name="startTime"></td>
                                               
                                               </tr>
                                               <tr><td>下课时间</td> 
                                                   <td><input type="time" id="endTime" name="endTime"></td>
                                                </tr>
                                               <tr><td>预约开始时间</td> 
                                                   <td><input type="datetime" id="reservationStartTime" name="reservationStartTime" onclick="laydate({	
	  elem: '#reservationStartTime',
	  format: 'YYYY-MM-DD hh:mm', // 分隔符可以任意定义，该例子表示只显示年月
	  festival: true, //显示节日
	  istime:true,
	  choose: function(datas){ //选择日期完毕的回调
	    alert('得到：'+datas);
	  }
	})"></td>
                                               </tr>
                                               <tr><td>预约结束时间</td> 
                                                 <td><input type="datetime" id="reservationEndTime" name="reservationEndTime" onclick="laydate({	
	  elem: '#reservationEndTime',
	  format: 'YYYY-MM-DD hh:mm', // 分隔符可以任意定义，该例子表示只显示年月
	  festival: true, //显示节日
	  istime:true,
	  choose: function(datas){ //选择日期完毕的回调
	    alert('得到：'+datas);
	  }
	})"></td>
                                               </tr>
                                               <tr><td>取消开始时间</td>
                                                   <td><input type="datetime" id="cancellationStartTime"  name="cancellationStartTime" onclick="laydate({	
	  elem: '#cancellationStartTime',
	  format: 'YYYY-MM-DD hh:mm', // 分隔符可以任意定义，该例子表示只显示年月
	  festival: true, //显示节日
	  istime:true,
	  choose: function(datas){ //选择日期完毕的回调
	    alert('得到：'+datas);
	  }
	})"></td>
                                               </tr>
                                               <tr><td>取消结束时间</td> 
                                                    <td><input type="datetime" id ="cancellationEndTime" name="cancellationEndTime" onclick="laydate({	
	  elem: '#cancellationEndTime',
	  format: 'YYYY-MM-DD hh:mm', // 分隔符可以任意定义，该例子表示只显示年月
	  festival: true, //显示节日
	  istime:true,
	  choose: function(datas){ //选择日期完毕的回调
	    alert('得到：'+datas);
	  }
	})"></td>
                                               </tr>
                                               <tr><td>checkin结束时间</td> 
                                                  <td><input type="time" id="checkinEndTime" name="checkinEndTime"></td>
                                               
                                               </tr>
                                           
                                        </thead>
                                        <tfoot>
                                        <tr style="align:center"><td><button type="submit">确定</button></td></tr>
                                    </tfoot>
                                    </table>
                                </div>
                                </form>
                                
                            </div>
                         
                        </div>
						
                    </div>
					
            </div>
            <hr>

        </div>


    </body>

</html>