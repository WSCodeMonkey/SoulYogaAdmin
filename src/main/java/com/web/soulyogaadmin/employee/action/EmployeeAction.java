package com.web.soulyogaadmin.employee.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.web.soulyogaadmin.employee.service.IEmployeeService;
import com.web.soulyogaadmin.util.UtilValidate;
import com.web.soulyogaadmin.vo.EmployeeView;

/**
 * Employee Struts Action
 * 
 * @author Comi Zhou
 * @version 2017-6-26
 */
@ParentPackage(value = "struts-default")
@Namespace(value = "/")
public class EmployeeAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IEmployeeService employeeService;

	private EmployeeView employeeView;
	private List<EmployeeView> employeeViews;
	private Map<Integer, String> yogaclubMap;
	private Map<Integer, String> positionMap;
	private Map<Integer, String> coursecategoryMap;
	
	private File image;
	private Integer teacherPositionId;
	

	@Action(value = "showAllEmployee", results = { @Result(name = "success", location = "/employee/employeeList.jsp") })
	public String showAllEmployee() {
		employeeViews = employeeService.findAllEmployee();
		yogaclubMap = employeeService.findAllYogaClub();
		return SUCCESS;
	}

	// 错误页面待指定
	@Action(value = "showEmployeeDetail", results = {
			@Result(name = "success", location = "/employee/employeeDetail.jsp"),
			@Result(name = "error", location = "fail.jsp") })
	public String showEmployeeDetail() {
		
		setViewObj();
		String employeeId = ServletActionContext.getRequest().getParameter("id");
		if (UtilValidate.isNotEmpty(employeeId)) {
			employeeView = employeeService.findEmployeeById(Integer.parseInt(employeeId));
			String avatarUrl = employeeView.getAvatarUrl();
			if (UtilValidate.isNotEmpty(avatarUrl)) {
				HttpServletRequest request = ServletActionContext.getRequest();
				avatarUrl = request.getRequestURL().toString().replace(request.getServletPath(), avatarUrl);
				employeeView.setAvatarUrl(avatarUrl);
			}
			
		} else {
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "findEmployeeViaCondition", results = {@Result(name = "success", location = "/employee/employeeListContent.jsp")})
	public String findEmployeeViaCondition() {
		Integer id = employeeView.getId();
		String name = employeeView.getName();
		Integer yogaclubId = employeeView.getYogaClubId();
		Integer isTeacher = employeeView.getIsTeacher();
		if (UtilValidate.isEmpty(id) && UtilValidate.isEmpty(name) && UtilValidate.isEmpty(yogaclubId)
				&& UtilValidate.isEmpty(isTeacher)) {
			employeeViews = employeeService.findAllEmployee();
		} else {
			employeeViews = employeeService.findEmployeeViaCondition(employeeView);
		}
		return SUCCESS;
	}

	@Action(value = "toAddEmployee", results = { @Result(name = "success", location = "/employee/addEmployee.jsp") })
	public String toAddEmployee() {
		
		setViewObj();

		return SUCCESS;
	}

	@Action(value = "addEmployee", results = { @Result(name = "success", type = "chain", location = "toAddEmployee") })
	public String addEmployee() {
		if (UtilValidate.isNotEmpty(image)) {
			employeeView.setAvatarUrl(uploadImage());
		}
		boolean result = employeeService.addEmployee(employeeView);
		if (result) {
			System.out.println("success");
		} else {
			System.out.println("fail");
		}
		return SUCCESS;
	}

	@Action(value = "updEmployee")
	public void updEmployee() {
		if (UtilValidate.isNotEmpty(image)) {
			employeeView.setAvatarUrl(uploadImage());
		}
		boolean result = employeeService.updEmployee(employeeView);
		if (result) {
			System.out.println("success");
		} else {
			System.out.println("fail");
		}
	}
	
	@Action(value = "delEmployee")
	public void delEmployee() {
		String employeeId = ServletActionContext.getRequest().getParameter("employeeId");
		boolean result = false;
		if (UtilValidate.isNotEmpty(employeeId)) {
			result = employeeService.delEmployee(Integer.parseInt(employeeId));
		}
		if (result) {
			System.out.println("success");
		} else {
			System.out.println("fail");
		}
	}

	private String uploadImage() {
		String absolutePath = "/uploadfile/employeeimage/";
		String realPath = ServletActionContext.getServletContext().getRealPath(absolutePath);
		File file = new File(realPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		String fileName = UUID.randomUUID().toString().replace("-", "") + ".jpg";
		try {
			FileUtils.copyFile(image, new File(realPath, fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String avatarUrl = absolutePath + fileName;
		return avatarUrl;
	}
	
	private void setViewObj() {
		yogaclubMap = employeeService.findAllYogaClub();
		positionMap = employeeService.findAllPosition();
		coursecategoryMap = employeeService.findAllCourseCategory();
		
		String tpid = getProperties().getProperty("teacherPositionId");
		if (UtilValidate.isNotEmpty(tpid)) {
			teacherPositionId = Integer.parseInt(tpid);
		}
	}

	private Properties getProperties() {
		InputStream in = EmployeeAction.class.getClassLoader().getResourceAsStream("tableFieldConfig.properties");
		Properties properties = new Properties();
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	public EmployeeView getEmployeeView() {
		return employeeView;
	}

	public void setEmployeeView(EmployeeView employeeView) {
		this.employeeView = employeeView;
	}

	public List<EmployeeView> getEmployeeViews() {
		return employeeViews;
	}

	public void setEmployeeViews(List<EmployeeView> employeeViews) {
		this.employeeViews = employeeViews;
	}

	public Map<Integer, String> getYogaclubMap() {
		return yogaclubMap;
	}

	public void setYogaclubMap(Map<Integer, String> yogaclubMap) {
		this.yogaclubMap = yogaclubMap;
	}

	public Map<Integer, String> getPositionMap() {
		return positionMap;
	}

	public void setPositionMap(Map<Integer, String> positionMap) {
		this.positionMap = positionMap;
	}

	public Map<Integer, String> getCoursecategoryMap() {
		return coursecategoryMap;
	}

	public void setCoursecategoryMap(Map<Integer, String> coursecategoryMap) {
		this.coursecategoryMap = coursecategoryMap;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public Integer getTeacherPositionId() {
		return teacherPositionId;
	}

	public void setTeacherPositionId(Integer teacherPositionId) {
		this.teacherPositionId = teacherPositionId;
	}

}
