package com.web.soulyogaadmin.courseattendance.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONArray;
import com.web.soulyogaadmin.courseattendance.service.ICourseattendanceService;
import com.web.soulyogaadmin.courseattendance.vo.CourseattendanceView;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
public class CourseattendanceAction {

	@Autowired
	private ICourseattendanceService iCourseattendanceService;
	
	private String mobileNo;
	
	private String checkType;

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	
	@Action(value="check")
	public void chenkIn() throws IOException{
		if(checkType.equals("checkin")){
			List<CourseattendanceView> list=iCourseattendanceService.queryCheckInByMember(mobileNo);
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			String jsonlist=JSONArray.toJSONString(list);
			response.getWriter().write(jsonlist);
		}else if(checkType.equals("checkout")){
			
		}
		
	}
	
	
	
}
