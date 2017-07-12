package com.web.soulyogaadmin.employee.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.web.soulyogaadmin.employee.service.IEmployeeAccountService;
import com.web.soulyogaadmin.util.UtilValidate;

/**
 * EmployeeAccount User Login/Reset Password Struts Action
 * @author Shawn Xiao
 * @version 2017-06-15
 */



@ParentPackage(value="struts-default")
@Namespace(value="/")
public class EmployeeAccountAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	
	private String userName;
	
	private String password;
	
	private String newPassword;
	
	private String newPasswordAgain;
	
	@Autowired 
	private IEmployeeAccountService employeeAccountService;
	
	@Action(value="userLogin", results={@Result(name="LOGINSUCCESS", location="../../index.jsp"), @Result(name="LOGINFAIL", location="./adminLogin.jsp")})
	public String userLogin() {
		String  message = null;
		 HttpServletRequest request = ServletActionContext.getRequest();
		if(UtilValidate.isEmpty(userName)||UtilValidate.isEmpty(password))
		{
			message="缺少参数 ";
			request.setAttribute("tipMessage", message); 
			return "LOGINFAIL";
		}
		 boolean loginResult =employeeAccountService.employeeAccountLogin(userName,password);
		 if (loginResult)
		{
			 request.getSession().setAttribute("admin", "admin");
			 return "LOGINSUCCESS"; 
		}
		 else{			 
			request.setAttribute("tipMessage", message); 
			 return "LOGINFAIL";
		 }
	}

	@Action(value="userLoginOut", results={@Result(name="LOGINOUTSUCCESS", location="/adminLogin.jsp")})
	public String userLoginOut() {

			 HttpServletRequest request = ServletActionContext.getRequest();
			 request.getSession().removeAttribute("admin");
			 return "LOGINOUTSUCCESS";
	}
	
	@Action(value="changePassword", results={@Result(name="CHANGESUCCESS", location="/adminLogin.jsp"),@Result(name="CHANGEFAIL", location="/adminChangePassword.jsp")})
	public String resetPassword() {
		
		if(newPassword.equals(newPasswordAgain))
		{
		 boolean loginResult =employeeAccountService.employeeAccountResetPassword(userName,newPassword);

		 if (loginResult)
		{
			 return "CHANGESUCCESS";
		}
		 else{
			 return "CHANGEFAIL";
		 }
		}else
		{
			return "CHANGEFAIL";
		}
	}
	
	
	@Action(value="forgetPassword", results={@Result(name="UPDATESUCCESS", location="/adminLogin.jsp"),@Result(name="UPDATEFAIL", location="/adminForgetPassword.jsp")})
	public String forgetPassword() {
		 String  result =employeeAccountService.employeeAccountForgetPassword(userName);

		 if (UtilValidate.isNotEmpty(result)) 
		{
			 return "UPDATESUCCESS";
		}
		 else{
			 return "UPDATEFAIL";
		 }
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getNewPassword() {
		return newPassword;
	}


	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


	public String getNewPasswordAgain() {
		return newPasswordAgain;
	}


	public void setNewPasswordAgain(String newPasswordAgain) {
		this.newPasswordAgain = newPasswordAgain;
	}

	/*
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	*/
}
