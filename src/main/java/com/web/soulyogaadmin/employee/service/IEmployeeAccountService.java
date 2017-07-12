package com.web.soulyogaadmin.employee.service;



/**
 * User Service Interface
 * @author Shawn xiao
 * @version 2017-06-15
 */
public interface IEmployeeAccountService {
	

	public boolean employeeAccountLogin(String userName, String password);
	public String employeeAccountForgetPassword(String userNamer);
	public boolean employeeAccountResetPassword(String userName,String password);
}
