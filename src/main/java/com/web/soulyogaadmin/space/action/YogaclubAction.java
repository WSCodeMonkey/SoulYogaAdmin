package com.web.soulyogaadmin.space.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.web.soulyogaadmin.course.service.ICourseService;
import com.web.soulyogaadmin.entity.Classroom;
import com.web.soulyogaadmin.entity.CourseEntry;
import com.web.soulyogaadmin.entity.Locker;
import com.web.soulyogaadmin.entity.Yogaclub;
import com.web.soulyogaadmin.entity.Yogacushion;
import com.web.soulyogaadmin.space.service.ISpaceService;
import com.web.soulyogaadmin.space.service.impl.SpaceServiceImpl;
import com.web.soulyogaadmin.util.UtilValidate;

@ParentPackage(value = "struts-default")
@Namespace(value = "/")
public class YogaclubAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	private HttpServletRequest request =ServletActionContext.getRequest();
	Yogaclub yogaclub = new Yogaclub();
	private HttpServletResponse response=ServletActionContext.getResponse();

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private static String className = YogaclubAction.class.getName();

	private static Logger logger = Logger.getLogger(className);

	@Autowired
	private ISpaceService spaceService;

	@SuppressWarnings("unchecked")
	@Action(value = "getAllYogaclubList", results = {@Result(name = "ALLYOGACLUBLIST", location = "/space/yogaclubQueryList.jsp"), @Result(name="ALLYOGACLUBLISTFAIL",location="/space/yogaclubAdd.jsp") })
	public String getAllYogaclubList() {
            String header=request.getHeader("X-Requested-With");
            System.out.println(header);
            if(header==null){
            List<Yogaclub> templateList = spaceService.getAllYogaclub();
		  
			System.out.println(11111);
			request.setAttribute("yogaclub_list", templateList);
			System.out.println(request.getAttribute("yogaclub_list"));
			return "ALLYOGACLUBLIST";
			
            }
            else{
            	 List<Yogaclub> templateList = spaceService.getAllYogaclub();
     		  
     			System.out.println("AJAX查询成功");
                String json=JSONArray.toJSONString(templateList);
                System.out.println(json);
     			try {
     				ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8"); 

     				ServletActionContext.getResponse().getWriter().write(json);
     				return null;
     				
     			} 
     		
     			
     			catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
     			
     			return "";          	
            }
     		  
	}
            
	
	@Action(value = "deleteYogaclubbyId", results = {
			@Result(name = "DELETESUCCESS", type="chain", location = "getAllYogaclubList") })

	public String deleteYogaclubbyId() {
		id = Integer.valueOf(request.getParameter("id"));
		spaceService.deleteYogaclubbyId(id);
		/*
		 * SpaceService.deleteYogaclubbyId(id);
		 */ return "DELETESUCCESS";
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	@Action(value = "updateYogaclub", results = {
			@Result(name = "UPDATESUCCESS", location = "/space/yogaclubQueryList.jsp") })
	public String updateYogaclub() {
		try {
			String json = request.getParameter("citydata");
			System.out.println(json);
			JSONObject yg = JSON.parseObject(json);
			Yogaclub yogaclub = new Yogaclub();
			yogaclub.setCity((String) yg.get("city"));
			yogaclub.setName((String) yg.get("name"));
			yogaclub.setAddress((String) yg.get("address"));
			yogaclub.setPhone((String) yg.get("phone"));
			yogaclub.setLinkman((String) yg.get("linkman"));
			yogaclub.setId((Integer) yg.get("id"));
			spaceService.updateYogaclub(yogaclub);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "UPDATESUCCESS";
	}

	@SuppressWarnings("unchecked")
	@Action(value = "getAllYogaclub", results = {
			@Result(name = "ALLYOGACLUBLIST", location = "/space/yogaclubQueryList.jsp") })
	public String addYogaclub() {
		String city = request.getParameter("city");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String linkman = request.getParameter("linkman");
		Yogaclub yogaclub = new Yogaclub();
		yogaclub.setName(name);
		yogaclub.setAddress(address);
		yogaclub.setLinkman(linkman);
		yogaclub.setPhone(phone);
		yogaclub.setLinkman(linkman);
		yogaclub.setCity(city);
		spaceService.addYogaclub(yogaclub);
		return "ALLYOGACLUBLIST";

	}
	@SuppressWarnings("unchecked")
	@Action(value = "addLockerRedirect", results = {
			@Result(name = "REDIRECTSUCCESS", location = "/space/lockerAdd.jsp") })
	public String addLockerRedirect() {

		
		return "REDIRECTSUCCESS";

	}
	@SuppressWarnings("unchecked")
	@Action(value = "addYogaclubRedirect", results = {
			@Result(name = "REDIRECTSUCCESS", location = "/space/yogaclubAdd.jsp") })
	public String addYogaclubRedirect() {

		
		return "REDIRECTSUCCESS";

	}
	@SuppressWarnings("unchecked")
	@Action(value = "addLocker", results = {
			@Result(name = "LOCKERADDSUCCESS", location = "/space/lockerAdd.jsp") })
	public String addLocker() {
		int lockerCount;
		List<Yogacushion> list = new ArrayList<Yogacushion>();
		int yogaClubId = Integer.valueOf(ServletActionContext.getRequest().getParameter("allYogaclub"));
		
		     if(ServletActionContext.getRequest().getParameter("lockercount").equals("")){
		    	 lockerCount=0; 
		     }
		     else{
		    	 lockerCount = Integer.valueOf(ServletActionContext.getRequest().getParameter("lockercount"));
		     }
		

		String yogaClubName = spaceService.getYogaclubbyId(yogaClubId).getName();
        String LockerCount=String.valueOf(lockerCount);
         spaceService.addLocker(yogaClubId, lockerCount);		
 		return "LOCKERADDSUCCESS";
	
		
		}
	@Action(value = "getLockerbyYogaclub", results = {
			@Result(name = "GETSUCCESS", location = "/space/lockerQuerybyYogaclub.jsp") })

	public String getLockerbyYogaclub() {
		id = Integer.valueOf(request.getParameter("id"));
           System.out.println("会所id"+id);
		List<Locker> list=spaceService.getLockerbyYogaclub(id);
		request.setAttribute("locker_list",list);
		
		/*
		 * SpaceService.deleteYogaclubbyId(id);
		 */ return "GETSUCCESS";
	}
    

	
	@Action(value = "updateLocker", results = {
			@Result(name = "UPDATESUCCESS", type="chain", location = "getLockerbyYogaclub") })

	public void updateLocker() {
		 String yogaClubId=request.getParameter("yogaClubId");
		 int id = Integer.valueOf(request.getParameter("id"));
         int status=Integer.valueOf(request.getParameter("status"));
		spaceService.updateLocker(id, status);
         try {
			response.sendRedirect("./getLockerbyYogaclub.action?id="+yogaClubId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Action(value = "deleteLockerbyYogaclub", results = {
			@Result(name = "DELETESUCCESS", location = "/space/lockerQuerybyYogaclub.jsp") })

	public String deleteLockerbyYogaclub() {
		id = Integer.valueOf(request.getParameter("id"));
           System.out.println("衣柜id"+id);
	      spaceService.deleteLockerbyYogaclub(id);
		
		/*
		 * SpaceService.deleteYogaclubbyId(id);
		 */ return "DELETESUCCESS";
	}
    
	
	
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

}
