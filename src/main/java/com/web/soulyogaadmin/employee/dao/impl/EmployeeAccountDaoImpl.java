package com.web.soulyogaadmin.employee.dao.impl;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.owasp.esapi.ESAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.soulyogaadmin.employee.dao.IEmployeeAccountDao;
import com.web.soulyogaadmin.entity.EmployeeAccount;
import com.web.soulyogaadmin.util.Des;
import com.web.soulyogaadmin.util.Encrypt;
import com.web.soulyogaadmin.util.UtilValidate;


/**
 * EmployeeAccountEntry Dao Implemention
 * @author Shawn xiao
 * @version 2017-06-15
 */


@Repository("employeeAccountDao")
public class EmployeeAccountDaoImpl implements IEmployeeAccountDao {

	String strKey = "0002000200020002";
	
	@Autowired
	private SessionFactory sessionFactory;

	private Session session;

	private static String className = EmployeeAccountDaoImpl.class.getName();

	private static Logger logger = Logger.getLogger(className);
	
	@Override
	public boolean employeeAccountLogin(String userName, String password) {
		
		String hql = "from EmployeeAccount where userName=? and password=? and state=?";  

		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(hql);
		query.setString(0, userName);
		try {
			query.setString(1, Des.Encrypt(password, Des.build3DesKey(strKey)));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		query.setInteger(2, 0);
		EmployeeAccount result = (EmployeeAccount) query.uniqueResult();

		if (UtilValidate.isNotEmpty(result)) {
			return true;
		} else {
			return false;
		}
	};

	public EmployeeAccount getEmployeeAccount(String userName) {

		String hql = "from EmployeeAccount where adminName=? and adminStatus=?"; 

		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(hql);
		query.setString(0, userName);
		query.setInteger(1, 0);
		EmployeeAccount result = (EmployeeAccount) query.uniqueResult();

		if (UtilValidate.isNotEmpty(result)) {
			return result;
		} else {
			return null;
		}
	};

	@Override
	public String forgetPassword(String userName) {

		String newPassword = null;

		session = this.sessionFactory.getCurrentSession();

		EmployeeAccount admin = getEmployeeAccount(userName);
		if (UtilValidate.isNotEmpty(admin)) {

			newPassword = getRandomString(6);
			admin.setPassword(Encrypt.e(newPassword));
			session.merge(admin);
		}
		return newPassword;
	};

	private static String getRandomString(int length) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {			
			int number =ESAPI.randomizer().getRandomInteger(0, 62);
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}

	public boolean resetPassword(String userName, String password) {

		session = this.sessionFactory.getCurrentSession();

		EmployeeAccount admin = getEmployeeAccount(userName);
		if (UtilValidate.isNotEmpty(admin)) {
			admin.setPassword(Encrypt.e(password));
			session.merge(admin);
			return true;
		}else{
			return false;
		}
		
	};

	public boolean employeeAccountCreateOne(EmployeeAccount employeeAccount) {

		session = this.sessionFactory.getCurrentSession();

		EmployeeAccount result = getEmployeeAccount(employeeAccount.getUserName());
		if (UtilValidate.isEmpty(result)) {
			session.save(employeeAccount);
			return true;
		} else {
			return false;
		}

	};

	public boolean employeeAccountDeleteOne(String userName) {

		session = this.sessionFactory.getCurrentSession();

		EmployeeAccount admin = getEmployeeAccount(userName);
		if (UtilValidate.isNotEmpty(admin)) {
			session.delete(admin);
			return true;
		} else {
			return false;
		}
	}

}
