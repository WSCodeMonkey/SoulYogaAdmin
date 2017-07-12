package com.web.soulyogaadmin.employee.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.web.soulyogaadmin.employee.service.IEmployeeService;
import com.web.soulyogaadmin.employeeconfig.service.IEmployeeConfigService;
import com.web.soulyogaadmin.util.UtilValidate;
import com.web.soulyogaadmin.vo.EmployeeView;
import com.web.soulyogaadmin.vo.PositionView;

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
//	@Autowired
//	private IEmployeeConfigService employeeConfigService;

	private EmployeeView employeeView;
	private List<EmployeeView> employeeViews;
	private List<PositionView> positionViews;

	@Action(value = "showAllEmployee", results = {@Result(name = "success", location = "/employee/employeeList.jsp")})
	public String showAllEmployee() {
		employeeViews = employeeService.findAllEmployee();
		return SUCCESS;
	}
	
	//错误页面待指定
	@Action(value = "showEmployeeDetail", results = {@Result(name = "success", location = "/employee/employeeDetail.jsp"),@Result(name = "error", location = "fail.jsp")})
	public String showEmployeeDetail() {
		String employeeId = ServletActionContext.getRequest().getParameter("id");
		if (UtilValidate.isNotEmpty(employeeId)) {
			employeeView = employeeService.findEmployeeById(Integer.parseInt(employeeId));
		} else {
			return ERROR;
		}
		return SUCCESS;
	}
	
	@Action(value = "toAddEmployee", results = {@Result(name = "success", location = "/employee/addEmployee.jsp")})
	public String toAddEmployee() {
//		positionViews = employeeConfigService.findAllPositions();
		
		return SUCCESS;
	}
	
	@Action(value = "addEmployee", results = {@Result(name = "success", type = "chain", location = "toAddEmployee")})
	public String addEmployee() {
		System.out.println("----");
		boolean result = employeeService.addEmployee(employeeView);
		if (result) {
			System.out.println("success");
		} else {
			System.out.println("fail");
		}
		return SUCCESS;
	}
	
	@Action(value = "updEmployee", results = {@Result(name = "success", type = "chain", location = "toAddEmployee")})
	public String updEmployee() {
		boolean result = employeeService.updEmployee(employeeView);
		if (result) {
			System.out.println("success");
		} else {
			System.out.println("fail");
		}
		return SUCCESS;
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

	public List<PositionView> getPositionViews() {
		return positionViews;
	}

	public void setPositionViews(List<PositionView> positionViews) {
		this.positionViews = positionViews;
	}

}
