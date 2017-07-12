<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html class="no-js">
    
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>查询/修改教室</title>
        <!-- Bootstrap -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
         <link href="assets/styles.css" rel="stylesheet" media="screen">
      <script src="vendors/jquery-1.11.2.min.js"></script>
       <script src="bootstrap/js/bootstrap.min.js"></script>
		 		 <script type="text/javascript">
          function updateYogacushion(data){
              
              var classroomNo=data; 
              alert(classroomNo);
              var count=$("#count_"+data).val();             
     
         	  $.ajax({
   	   	  	   type: "get",  //以post方式与后台沟通 
   	   	       url : "./updateYogacushion.action", 
   	   	       async:false,
   	   	       data : 		  
   	   	    	{classroomNo:classroomNo,
   	   	    	count:count},
   	    	   error : function(data){
   	    		   alert("update fail.");
   	    	   },
   	   	       success : function(data){

   	   	    	   }  
   	    	   	
    	    });  
          } 


		 </script>
		 	 		 <script type="text/javascript">
          function deleteClassroom(data){
              
              var classroomNo=data; 
              alert(classroomNo);
              var count=$("#count_"+data).val();             
     
         	  $.ajax({
   	   	  	   type: "get",  //以post方式与后台沟通 
   	   	       url : "./deleteClassroom.action", 
   	   	       async:false,
   	   	       data : 		  
   	   	    	{classroomNo:classroomNo
   	   	    	},
   	    	   error : function(data){
   	    		   alert("update fail.");
   	    	   },
   	   	       success : function(data){
   	    		   document.location.href = "./getAllClassroom.action";
                   
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
                    <a class="brand" href="./adminLogin.jsp">Admin Panel</a>
                    <div class="nav-collapse collapse">
                        <ul class="nav pull-right">
      
                        </ul>
                        <ul class="nav">
                            <li class="dropdown">
                     <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">  SoulYoga Admin <i class="caret"></i>
            
                                </a> 
                                	<ul class="dropdown-menu">
								<li><a tabindex="-1" href="adminChangePassword.jsp">Change
										password</a></li>
								<li class="divider"></li>
								<li><a tabindex="-1" href="userLoginOut.action">Logout</a>
								</li>
							</ul>                              
							 <ul class="dropdown-menu">
                                 <li class="">
                            <a href="./getAllYogaclubList.action"><i class="icon-chevron-right"></i>瑜伽会所查询</a>
                        </li>
						 <li class="">
                            <a href="space/yogaclubAdd.jsp"><i class="icon-chevron-right"></i>增加瑜伽会所</a>
                        </li>
                                
                             <li class="">
                            <a href="./getAllYogaclubList.action"><i class="icon-chevron-right"></i>教室查询</a>
                        </li>
                                  <li class="">
                            <a href="space/classroomAdd.jsp"><i class="icon-chevron-right"></i>添加教室</a>
                        </li>
                                   
                                </ul>
                            </li>
							
                        </ul>
                    </div>
                    <!--/.nav-collapse -->
                </div>
            </div>
        </div>

             
				
                    <div class="row-fluid">
                        <div class="span12">
                            <!-- block -->
                            <div class="active">
                                <div class="navbar navbar-inner block-header">
                                    <div class="muted pull-left">所有教室列表</div>
                                    <!--<div class="pull-right"><span class="badge  badge-info">1,234</span>
									
                                    </div>-->
                                </div>
                                <div class="block-content collapse in">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                               
                                                <th>教室编号</th>
                                                <th>所属会所名称</th>
												<th>瑜伽垫数量</th>
												
                                            </tr>
                                        </thead>
            <tbody>
                                         <s:iterator value="#request.classroomQuerybyYogaclub_list" var="template" >
                                            <tr>                                                
												<td><s:property  value="#template.classroomNo"  /></td>
                                                <td><s:property  value="#template.yogaclubName"  /></td>
                                                <td> <input id="count_<s:property  value="#template.classroomNo"  />" value="<s:property  value="#template.YogaCushionCount"  />" type="number"> </td>
                                                <td>  <input id="classroomno" type="hidden" value="<s:property  value="#template.classroomNo"  />" >  </td>
                                                <td>  <button onClick="updateYogacushion('<s:property  value="#template.classroomNo"  />')" >更 新</button></td>
                                                <td>  <button onClick="deleteClassroom('<s:property  value="#template.classroomNo"  />')" >删除</button></td>
                                                
                                            
                                            </tr>
                                          </s:iterator>
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