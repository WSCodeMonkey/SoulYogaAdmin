<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>

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
              var yogaClubId=$("#yogaClubId").val();
              alert(yogaClubId)
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
          function updateLocker(data){
              var yogaClubId=$("#yogaClubId").val();
              alert("会所id"+yogaClubId)
              var Lockerid=data; 
              alert(Lockerid);
              var status=$("#lockerstatus_"+data).val();             
                   alert(status);
         	  $.ajax({
   	   	  	   type: "get",  //以post方式与后台沟通 
   	   	       url : "./updateLocker.action", 
   	   	       async:false,
   	   	       data : 		  
   	   	    	{id:Lockerid,
      	   	     status:status,	
      	   	yogaClubId:yogaClubId
   	   	    	},
   	    	   error : function(data){
   	    		   alert("update fail.");
   	    	   },
   	   	       success : function(data){
                   
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
                                    <div class="muted pull-left">会所所属柜子</div>
                                    <!--<div class="pull-right"><span class="badge  badge-info">1,234</span>
									
                                    </div>-->
                                </div>
                                <div class="block-content collapse in">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                               
                                                <th>柜子编号</th>
                                                <th>柜子使用情况</th>
												<th></th>
												
                                            </tr>
                                        </thead>
            <tbody>
                                         <s:iterator value="#request.locker_list" var="template" >
                                            <tr> 
												<td><s:property  value="#template.lockerNo"  /></td>
                                                <td><select name="lockerstatus_" id="lockerstatus_${template.id }">
                                                <option value="1" 
                                                <c:if test="${template.status==0 }">selected</c:if>
                                                >已使用</option>
                                                <option value="2"  <c:if test="${template.status==1 }">selected</c:if> >
                                                                                                                                                   未使用
                                                </option>
                                                      <option value="3"  <c:if test="${template.status==3}">selected</c:if> >
                                                                                                                                                          损坏                                                                                               
                                                </option>
                                                </select></td>
                                                
                                                 <td><input type="hidden" value="${template.yogaClubId}" id="yogaClubId"></td>                                              
                                                
                                        <td><button onClick="updateLocker(<s:property  value="#template.id"  />)" >更 新</button></td>
                                          <td><input type="button" onclick="location.href='deleteLockerbyYogaclub.action?id='+${template.id }" value="删除"> </td>
                                      
                                                
                                            
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