package com.web.soulyogaadmin.course.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.web.soulyogaadmin.course.service.IcoursecategoryService;
import com.web.soulyogaadmin.entity.Coursecategory;
import com.web.soulyogaadmin.util.UtilValidate;

public class CoursecategoryAction extends ActionSupport implements ModelDriven<Coursecategory> {
	Coursecategory coursecategory=new Coursecategory();
	private static String className = CoursecategoryAction.class.getName();

	private static Logger logger = Logger.getLogger(className);
	@Autowired 
	private IcoursecategoryService coursecategoryService;
	@Override
	public Coursecategory getModel() {
		// TODO Auto-generated method stub
		return coursecategory;
	}
	@Action(value="getAllCoursecategory",results={@Result(name="AllCoursecategoryList",location="/coursecategory/coursecategoryQueryList.jsp")})
	public String getAllCoursecategory(){
		HttpServletRequest request=ServletActionContext.getRequest();
	//	HttpSession session=ServletActionContext.getRequest().getSession();
		//只显示没被删除的数据，即state为0的
		List<Coursecategory>list=coursecategoryService.getSearchedCoursecategory(0);
		if(UtilValidate.isNotEmpty(list))
		{
			request.setAttribute("coursecategory_list", list);
		}
		logger.debug("getAllCoursecategory.action");
		return "AllCoursecategoryList";
		
	}
	@Action(value="getCoursecategory",results={@Result(name="partCoursecategoryList",location="/coursecategory/coursecategoryQueryList.jsp")})
	public String getCoursecategory(){
		HttpServletRequest request=ServletActionContext.getRequest();
		//HttpSession session=request.getSession();
		String id=request.getParameter("coursecategoryId");
		String name=request.getParameter("coursecategoryName");
		System.out.println("=================");
		System.out.println("=================");
		System.out.println(id+","+name);
		List<Coursecategory>list=coursecategoryService.getSearchedCoursecategory(0);		
			List<Coursecategory>list2=null;	
		List<Coursecategory>list3=null;	
		if((id==null||id.equals(""))&&(name==null||name.equals(""))){
		//list=coursecategoryService.getSearchedCoursecategory(0);
			;
		}else if((id!=null||id.equals(""))&&(name==null||name.equals(""))){
			list2=coursecategoryService.getAllCoursecategoryById(Integer.parseInt(id));
			list.retainAll(list2);
		}else if((id!=null||id.equals(""))&&(name!=null||name.equals(""))){
			list2=coursecategoryService.getAllCoursecategoryById(Integer.parseInt(id));
			list3=coursecategoryService.getAllCoursecategoryByName(name);
			//List<Coursecategory>list1=coursecategoryService.getAllCoursecategoryByName(name);
			list.retainAll(list2);
			list.retainAll(list3);
		}else{
			list3=coursecategoryService.getAllCoursecategoryByName(name);
			list.retainAll(list3);
			//list=coursecategoryService.getAllCoursecategoryByName(name);
		}
		//当没找到时，没有处理空指针异常
		System.out.println("=================");
		System.out.println("=================");
		System.out.println(list.toString());
		if(UtilValidate.isNotEmpty(list))
		{
			request.setAttribute("coursecategory_list", list);
		}
		logger.debug("getCoursecategory.action");
		return "partCoursecategoryList";
		
	}
	@Action(value="deleteCoursecategory",results={@Result(name="delete",type="chain",location="getAllCoursecategory")})
	public String deleteCoursecategory(){
		//在删除的同时要修改属性modifiedTime
		HttpServletRequest request=ServletActionContext.getRequest();
		String id=request.getParameter("x");
		coursecategoryService.delete(coursecategoryService.getCoursecategoryById(Integer.parseInt(id)));
		Date d1=new Date(System.currentTimeMillis());
		coursecategoryService.changeCoursecategoryModifiedTime(Integer.parseInt(id), d1);
		return "delete";
		
	}
	@Action(value="addCoursecategory",results={@Result(name="add",type="chain",location="getAllCoursecategory")})
	public String addCoursecategory(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String name=request.getParameter("name");
		Coursecategory coursecategory=new Coursecategory();
		coursecategory.setName(name);
		coursecategoryService.add(coursecategory);
		return "add";
		
	}
	

}
