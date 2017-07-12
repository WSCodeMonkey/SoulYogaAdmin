package com.web.soulyogaadmin.space.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.web.soulyogaadmin.course.service.ICourseService;
import com.web.soulyogaadmin.entity.Classroom;
import com.web.soulyogaadmin.entity.CourseEntry;
import com.web.soulyogaadmin.entity.Yogaclub;
import com.web.soulyogaadmin.entity.Yogacushion;
import com.web.soulyogaadmin.space.dao.IClassroomDao;
import com.web.soulyogaadmin.space.dao.IYogaclubDao;
import com.web.soulyogaadmin.space.service.ISpaceService;
import com.web.soulyogaadmin.util.UtilValidate;
import com.web.soulyogaadmin.vo.ClassroomYogacushionvo;

@ParentPackage(value = "struts-default")
@Namespace(value = "/")
public class ClassroomAction extends ActionSupport implements ServletRequestAware {
	private HttpServletRequest request;

	private Classroom classroom;
	private String classroomNo;
	private int yogaCludId;

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public int getYogaCludId() {
		return yogaCludId;
	}

	public void setYogaCludId(int yogaCludId) {
		this.yogaCludId = yogaCludId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static String getClassName() {
		return className;
	}

	public static void setClassName(String className) {
		ClassroomAction.className = className;
	}

	private int id;
	private static String className = ClassroomAction.class.getName();

	private static Logger logger = Logger.getLogger(className);

	@Autowired
	private ICourseService courseService;

	@Autowired
	private ISpaceService spaceService;
	/*
	 * @OverrideyogaclubDao public CourseEntry getModel() { return courseEntry;
	 * }
	 */

	@SuppressWarnings("unchecked")
	@Action(value = "getClassroombyYogaclub", results = {
			@Result(name = "CLASSROOMQUERYSUCCESS", location = "/space/classroomQuerybyYogaclub.jsp"),
			@Result(name = "CLASSROOMQUERYFAIL",type="chain", location = "getAllYogaclubList")})
	public String getClassroombyYogaclub() {
		int id = Integer.parseInt(request.getParameter("id"));
		Yogaclub yogaclub = spaceService.getYogaclubbyId(id);
		List<ClassroomYogacushionvo> templateList = spaceService.getClassroombyYogaclub(id);

		if (templateList!=null) {
			System.out.println(templateList.get(0).toString());
			request.setAttribute("classroomQuerybyYogaclub_list", templateList);
			return "CLASSROOMQUERYSUCCESS";

		}

		else {
			return "CLASSROOMQUERYFAIL";
		}
	}

	@SuppressWarnings("unchecked")
	@Action(value = "getAllClassroom", results = {
			@Result(name = "CLASSROOMQUERYSUCCESS", location = "/space/classroomQueryList.jsp"), 
			@Result(name = "CLASSROOMQUERYFAIL", location = "/space/classroomQueryList.jsp")
	})
	public String getAllClassroom() {
		List<ClassroomYogacushionvo> templateList = spaceService.getAllClassroom();
		if (templateList!=null) {
			request.setAttribute("AllClassroom_list", templateList);
			return "CLASSROOMQUERYSUCCESS";

		}
		else{
			return "CLASSROOMQUERYFAIL";
		
		}

	}

	@SuppressWarnings("unchecked")
	@Action(value = "classroomAdd", results = {
			@Result(name = "CLASSROOMADDSUCCESS",type="chain", location = "getAllClassroom") })
	public String classroomAdd() {
		int yogaCushionCount;
		List<Yogacushion> list = new ArrayList<Yogacushion>();
		int yogaClubId = Integer.valueOf(ServletActionContext.getRequest().getParameter("allYogaclub"));
		     if(ServletActionContext.getRequest().getParameter("yogacushioncount").equals("")){
		    	 yogaCushionCount=0; 
		     }
		     else{
		    	 yogaCushionCount = Integer.valueOf(ServletActionContext.getRequest().getParameter("yogacushioncount"));
		     }
		
		     System.out.println("垫子数量" + yogaCushionCount);
		// 设置classroomno前半段
		String yogaClubName = spaceService.getYogaclubbyId(yogaClubId).getName();
		System.out.println("会所ID" + yogaClubId);
		// 设置classroomno的后半段,通过计算教室数量来进行命名
		String yogaClubCount = String.valueOf(spaceService.getClassroomCount(yogaClubId));
		System.out.println("教室数目" + yogaClubCount);
		Classroom classroom = new Classroom();
		classroom.setYogaCludId(Integer.valueOf(yogaClubId));
		classroom.setClassroomNo(yogaClubName + yogaClubCount + "号教室");
		Date date = new Date();
		classroom.setCreatedTime(date);
		int primaryKey = spaceService.addClassroom(classroom);
		for (int i = 0; i < yogaCushionCount; i++) {
			Yogacushion yg = new Yogacushion();
			yg.setClassroomId(primaryKey);
			yg.setYogaCushionNo(classroom.getClassroomNo() + "号教室" + String.valueOf(i) + "号垫子");
			yg.setCreatedTime(new Date());
			spaceService.addYogacushion(yg);

		}
		return "CLASSROOMADDSUCCESS";

	}

	@SuppressWarnings("unchecked")
	@Action(value = "updateYogacushion", results = {
			@Result(name = "CLASSROOMUPDATESUCCESS", type = "chain", location = "getAllClassroom") })
	public String updateYogacushion() {
        int updateCount=Integer.valueOf(ServletActionContext.getRequest().getParameter("count"));
        String classroomNo=ServletActionContext.getRequest().getParameter("classroomNo");
      

        spaceService.updateYogacushion(updateCount, classroomNo);
        
        
        
		return "CLASSROOMUPDATESUCCESS";

	}
	@SuppressWarnings("unchecked")
	@Action(value = "deleteClassroom", results = {
			@Result(name = "CLASSROOMUPDATESUCCESS", type = "chain", location = "getAllClassroom") })
	public String deleteClassroom() {
        String classroomNo=ServletActionContext.getRequest().getParameter("classroomNo");
        System.out.println("+++++++++++"+classroomNo);
         spaceService.deleteClassroom(classroomNo);
        
        
        
		return null;

	}

	@SuppressWarnings("unchecked")
	@Action(value = "addClassroomRedirect", results = {
			@Result(name = "REDIRECTSUCCESS", location = "/space/classroomAdd.jsp") })
	public String addClassroomRedirect() {

		
		return "REDIRECTSUCCESS";

	}
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

}
