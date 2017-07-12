package com.web.soulyogaadmin.user.dao;

import com.web.soulyogaadmin.entity.Administrator;

/**
 * User Dao Interface
 * @author Shawn xiao
 * @version 2017-06-15
 */
public interface IUserDao {

	public boolean userLogin(String userName, String password);
	public String userForgetPassword(String userName);
	public boolean userResetPassword(String userName,String password);
	public boolean userCreateOne(String userName,String password);
	public Administrator getUser(String userName);
	public boolean userDeleteOne(String userName);
}
