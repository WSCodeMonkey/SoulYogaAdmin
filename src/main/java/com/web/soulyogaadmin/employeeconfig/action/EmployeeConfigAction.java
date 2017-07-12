package com.web.soulyogaadmin.employeeconfig.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.web.soulyogaadmin.employeeconfig.service.IEmployeeConfigService;
import com.web.soulyogaadmin.entity.Department;
import com.web.soulyogaadmin.vo.PositionView;

/**
 * Department Position Struts Action
 * 
 * @author Comi Zhou
 * @version 2017-06-21
 */
@ParentPackage(value = "struts-default")
@Namespace(value = "/")
public class EmployeeConfigAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IEmployeeConfigService employeeConfigService;

	private List<Department> departments;
	private List<PositionView> positionViews;

	// Department

	// list all departments
	@Action(value = "showDepartment", results = {
			@Result(name = "success", location = "/employeeconfig/departmentList.jsp") })
	public String showDepartment() {
		departments = employeeConfigService.findAllDepartments();
//		ServletActionContext.getRequest().setAttribute("departments", departments);
		return SUCCESS;
	}

	// add department
	@Action(value = "addDepartment")
	public String addDepartment() {
		String departmentName = ServletActionContext.getRequest().getParameter("departmentName");
		String resultInfo = "添加成功";
		if (departmentName != null) {
			boolean result = employeeConfigService.addDepartment(departmentName);
			if (!result) {
				resultInfo = "部门已存在，请勿重复添加！";
			}
		} else {
			resultInfo = "数据异常，请重试！";
		}
		try {
			outMsg(resultInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// delete department
	@Action(value = "delDepartment")
	public String delDepartment() {
		String id = ServletActionContext.getRequest().getParameter("id");
		String resultInfo = "删除成功！";
		if (id != null) {
			boolean result = employeeConfigService.delDepartment(Integer.parseInt(id));
			if (!result) {
				resultInfo = "删除失败！";
			}
		} else {
			resultInfo = "数据异常，请重试！";
		}
		try {
			outMsg(resultInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// modify department's name
	@Action(value = "updDepartment")
	public String updDepartment() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		String departmentName = request.getParameter("departmentName");
		String resultInfo = "修改成功！";
		if (id != null && departmentName != null) {
			boolean result = employeeConfigService.updDepartment(Integer.parseInt(id), departmentName);
			if (!result) {
				resultInfo = "修改失败！";
			}
		} else {
			resultInfo = "数据异常，请重试！";
		}
		try {
			outMsg(resultInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Position

	// list all positions
	@Action(value = "showPosition", results = {
			@Result(name = "success", location = "/employeeconfig/positionList.jsp") })
	public String showPosition() {
		positionViews = employeeConfigService.findAllPositions();
		departments = employeeConfigService.findAllDepartments();
//		HttpServletRequest request = ServletActionContext.getRequest();
//		request.setAttribute("departments", departments);
//		request.setAttribute("positionViews", positionViews);
		return SUCCESS;
	}

	// add position
	@Action(value = "addPosition")
	public String addPosition() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String departmentId = request.getParameter("departmentId");
		String positionName = request.getParameter("positionName");
		String resultInfo = "添加成功";
		if (departmentId != null && positionName != null) {
			boolean result = employeeConfigService.addPosition(Integer.parseInt(departmentId), positionName);
			if (!result) {
				resultInfo = "职位已存在，请勿重复添加！";
			}
		} else {
			resultInfo = "数据异常，请重试！";
		}
		try {
			outMsg(resultInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// delete position
	@Action(value = "delPosition")
	public String delPosition() {
		String id = ServletActionContext.getRequest().getParameter("id");
		String resultInfo = "删除成功！";
		if (id != null) {
			boolean result = employeeConfigService.delPosition(Integer.parseInt(id));
			if (!result) {
				resultInfo = "删除失败！";
			}
		} else {
			resultInfo = "数据异常，请重试！";
		}
		try {
			outMsg(resultInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// modify position's name
	@Action(value = "updPosition")
	public String updPosition() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		String departmentId = request.getParameter("departmentId");
		String positionName = request.getParameter("positionName");
		String resultInfo = "修改成功！";
		if (id != null && departmentId != null && positionName != null) {
			boolean result = employeeConfigService.updPosition(Integer.parseInt(id), Integer.parseInt(departmentId), positionName);
			if (!result) {
				resultInfo = "修改失败！";
			}
		} else {
			resultInfo = "数据异常，请重试！";
		}
		try {
			outMsg(resultInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 暂定返回信息的方法
	private void outMsg(String resultInfo) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(resultInfo);
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public List<PositionView> getPositionViews() {
		return positionViews;
	}

	public void setPositionViews(List<PositionView> positionViews) {
		this.positionViews = positionViews;
	}

}
