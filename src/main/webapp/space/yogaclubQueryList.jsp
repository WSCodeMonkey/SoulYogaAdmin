<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html class="no-js">
    
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>查询/修改教室</title>
        <!-- Bootstrap -->
        <link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="./bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
         <link href=".assets/styles.css" rel="stylesheet" media="screen">
      <script src="./vendors/jquery-1.11.2.min.js"></script>
       <script src="./bootstrap/js/bootstrap.min.js"></script>
       
     <%--   		<script type='text/javascript'>
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
		   	       url : "getAllYogaclubList.action", 
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
     	  --%>
     	  
<%--      	  
        /*   function validateResendTimes(){
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
            } */
   	}
         
         
		 </script> --%>
		 
		 
		 <script type="text/javascript">
          function deleteYogaclub(data){
              var id=data
        	  $.ajax({
   	   	  	   type: "post",  //以post方式与后台沟通 
   	   	       url : "./deleteYogaclubbyId.action", 
   	   	       async:false,
   	   	       data : {		  
   	   	    	id:id
   	   	    	},
   	    	   error : function(data){
   	    		   alert("update fail.");
   	    	   },
   	   	       success : function(data){
   	    		 
   	    		   document.location.href = "./getAllYogaclubList.action";
    	   	          
   	    	   }  	
   	    });
          } 


		 </script>
		 		 <script type="text/javascript">
          function updateYogaclub(data){
              
              var obj=new Object();
              obj.city=$("#city_"+data).val();
              obj.name=$("#name_"+data).val();
              obj.address=$("#address_"+data).val();
              obj.phone=$("#phone_"+data).val();
              obj.linkman=$("#linkman_"+data).val();
              obj.id=data;
              var citydata=JSON.stringify(obj)
         	  $.ajax({
   	   	  	   type: "get",  //以post方式与后台沟通 
   	   	       url : "./updateYogaclub.action", 
   	   	       async:false,
   	   	       data : 		  
   	   	    	{citydata:citydata},
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
                            <a href="./addYogaclubRedirect.action"><i class="icon-chevron-right"></i>增加瑜伽会所</a>
                        </li>
                                
                             <li class="">
                            <a href="./getAllClassroom.action"><i class="icon-chevron-right"></i>教室查询</a>
                        </li>
                             <li class="">
                            <a href="./addClassroomRedirect.action"><i class="icon-chevron-right"></i>增加教室</a>
                        </li>
                             <li class="">
                            <a href="./addLockerRedirect.action"><i class="icon-chevron-right"></i>增加柜子</a>
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
                                    <div class="muted pull-left">会所列表</div>
                                    <!--<div class="pull-right"><span class="badge  badge-info">1,234</span>
									
                                    </div>-->
                                </div>
                                <div class="block-content collapse in" id="yogaclublist">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                               
                                                <th>会所号</th>
                                                <th>所属城市</th>
												<th>门店名称</th>
												<th>门店地址</th>
												<th>门店电话</th>
												<th>门店负责人</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                         <s:iterator value="#request.yogaclub_list" var="template" >
                                            <tr>
                                                <td id="id"><s:property  value="#template.id"  /></td>
                                                
												<td><input class="input-small focused" id="city_<s:property  value="#template.id"  />" type="text" value="<s:property value='#template.city' />"></input></td>
												<td><input class="input-small focused" id="name_<s:property  value="#template.id"  />" type="text" value="<s:property value="#template.name" />"></input></td>
                                                <td><input class="input-xlarge focused" id="address_<s:property  value="#template.id"  />" type="textarea" value="<s:property value="#template.address" />"></input></td>
                                                <td><input class="input-small focused" id="phone_<s:property  value="#template.id"  />" type="text" value="<s:property value="#template.phone" />"></input></td>
                                              <%--   <td><input type="hidden" value="#template.createdTime" id="createtime_<s:property value="#template.id" />"></td> --%>
                                                <td><input class="input-small focused" id="linkman_<s:property  value="#template.id"  />" type="text" value="<s:property value="#template.linkman" />"></input></td>
                                                <td><button onClick="updateYogaclub(<s:property  value="#template.id"  />)" >更 新</button></td>
                                          <td><button onClick="deleteYogaclub(<s:property  value="#template.id"  />)">删除</button> </td>
                                                <td><a href="./getClassroombyYogaclub.action?id=<s:property  value="#template.id"  />">查询会所教室 </a></td>
                                                <td><a href="./getLockerbyYogaclub.action?id=<s:property  value="#template.id"  />">查询会所衣柜 </a></td>
                                                
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