package com.web.soulyogaadmin.course.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.web.soulyogaadmin.course.service.ICourseService;
import com.web.soulyogaadmin.course.vo.CourseConditionVO;
import com.web.soulyogaadmin.course.vo.CourseView;
import com.web.soulyogaadmin.entity.CourseEntry;
import com.web.soulyogaadmin.entity.Coursecategory;
import com.web.soulyogaadmin.entity.Yogaclub;


@ParentPackage(value="struts-default")
@Namespace(value="/")
public class CourseAction extends ActionSupport implements ModelDriven<CourseEntry>{
	
	CourseEntry courseEntry = new CourseEntry();
	
	private static String className = CourseAction.class.getName();

	private static Logger logger = Logger.getLogger(className);

	@Autowired 
	private ICourseService courseService;
	 
	@Override
	public CourseEntry getModel() {
		return courseEntry;
	}
	
	private CourseEntry course;
	
	
	public CourseEntry getCourse() {
		return course;
	}


	public void setCourse(CourseEntry course) {
		this.course = course;
	}

	
	



	@SuppressWarnings("unchecked")
	@Action(value="getAllCourseList", results={@Result(name="ALLCOURSELIST", location="/course/courseQueryList.jsp")})
	public String getAllCourseList() {
		 String header=ServletActionContext.getRequest().getHeader("X-Requested-With");
		 if(header==null){
		HttpServletRequest request=ServletActionContext.getRequest();
		List<CourseView> templateList = courseService.QueryAllCourseList();
		request.setAttribute("course_list", templateList);
		List<Coursecategory> coursecategory =courseService.getAllCoursecategoryList();
		request.setAttribute("Categorylist", coursecategory);
		List<Yogaclub> yogaclub =courseService.getAllYogaclubList();
		request.setAttribute("yogaClublist", yogaclub);
		return "ALLCOURSELIST";
		 }
		 else{
				List<Coursecategory> CourseView =courseService.getAllCoursecategoryList();
                 System.out.println("ajax查询成功");
                 String json=JSONArray.toJSONString(CourseView);
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
	@Action(value="addcourse", results={@Result(name="success", location="/course/courseadd.jsp")})
	public String addCourse() {
		HttpServletRequest request=ServletActionContext.getRequest();
		List<Coursecategory> coursecategory =courseService.getAllCoursecategoryList();
		request.setAttribute("courseCategory", coursecategory);
		List<Yogaclub> yogaclub =courseService.getAllYogaclubList();
		request.setAttribute("yogaClub", yogaclub);
		return "success";
	}
	
	@Action(value="addcourseEntry", results={@Result(name="success",type="chain", location="getAllCourseList")})
	public String addCourseEntry() {
		course.setCreatedTime(new Date());
		course.setState(0);
		courseService.courseAdd(course);
		return "success";
	}
	
	
	@Action(value="ListByCondition")
	public void queryByCondition() throws Exception  {
		HttpServletRequest request=ServletActionContext.getRequest();
		CourseConditionVO courseCondition=new CourseConditionVO();
		String name= request.getParameter("Conditionname");
		String yogaClubID= request.getParameter("ConditionyogaClubID");
		String courseCategoryID= request.getParameter("ConditioncourseCategoryID");
		if(!name.equals("")){
			courseCondition.setName(name);
		}
	
		if(!yogaClubID.equals("null")){
			courseCondition.setYogaClubID(Integer.valueOf(yogaClubID));	
		}
		if(!courseCategoryID.equals("null")){
			courseCondition.setCourseCategoryID(Integer.valueOf(courseCategoryID));
		}
		
		List<CourseEntry> list=courseService.getAllCourseListByCondition(courseCondition);
		List<CourseView> listVo=new ArrayList<CourseView>();
		CourseView courseView=null;
		for(CourseEntry c:list){
			courseView=new CourseView();
			Coursecategory coursecategory=courseService.findCoursecategoryNameById(c.getCourseCategoryID());
			Yogaclub yogaclub=courseService.findYogaclubNameById(c.getYogaClubID());
			courseView.setCoursecategoryName(coursecategory.getName());
			courseView.setYogaclubName(yogaclub.getName());
			courseView.setId(c.getiD());
			PropertyUtils.copyProperties(courseView, c);
			listVo.add(courseView);
		}
        HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		String jsonlist=JSONArray.toJSONString(listVo);
		response.getWriter().write(jsonlist);
	}
	
	@Action(value="updatecourse", results={@Result(name="success", location="/course/courseupdate.jsp")})
	public String update() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String courseId=request.getParameter("courseId");
		List<Coursecategory> coursecategory =courseService.getAllCoursecategoryList();
		request.setAttribute("courseCategory", coursecategory);
		List<Yogaclub> yogaclub =courseService.getAllYogaclubList();
		request.setAttribute("yogaClub", yogaclub);
		CourseEntry courseInfo=courseService.getCourse(Integer.parseInt(courseId));
		request.setAttribute("CourseInfo", courseInfo);
		
		return "success";
	}
	
	@Action(value="reupdatecourse", results={@Result(name="success",type="chain", location="getAllCourseList")})
	public String updateCourse() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String courseId=request.getParameter("courseId");
		course.setiD(Integer.parseInt(courseId));
		course.setModifiedTime(new Date());
		courseService.updateCourse(course);
		return "success";
	}
	
	@Action(value="delcourse", results={@Result(name="success",type="chain", location="getAllCourseList")})
	public String delCourse() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String courseId=request.getParameter("courseId");
		courseService.delCourse(Integer.parseInt(courseId));
		return "success";
	}
	
	
	
	
	
	
	
}
