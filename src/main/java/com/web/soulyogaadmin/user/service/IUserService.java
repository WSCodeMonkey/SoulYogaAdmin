package com.web.soulyogaadmin.user.service;



/**
 * User Service Interface
 * @author Shawn xiao
 * @version 2017-06-15
 */
public interface IUserService {
	

	public boolean userLogin(String userName, String password);
	public String userForgetPassword(String userNamer);
	public boolean userResetPassword(String userName,String password);
}
