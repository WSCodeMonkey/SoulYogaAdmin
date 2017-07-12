package com.web.soulyogaadmin.user.dao;

import java.util.Random;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.owasp.esapi.ESAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.soulyogaadmin.entity.Administrator;
import com.web.soulyogaadmin.util.Encrypt;
import com.web.soulyogaadmin.util.UtilValidate;


/**
 * User Dao Implemention
 * @author Shawn xiao
 * @version 2017-06-15
 */


@Repository("userDao")
@Transactional
public class UserDaoImpl implements IUserDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session session;

	private static String className = UserDaoImpl.class.getName();

	private static Logger logger = Logger.getLogger(className);
	
	@Override
	public boolean userLogin(String userName, String password) {

		String hql = "from Administrator where adminName=? and password=? and adminStatus=?";

		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(hql);
		query.setString(0, userName);
		query.setString(1, Encrypt.e(password));
		query.setInteger(2, 0);
		Administrator result = (Administrator) query.uniqueResult();

		if (UtilValidate.isNotEmpty(result)) {
			return true;
		} else {
			return false;
		}
	};

	public Administrator getUser(String userName) {

		String hql = "from Administrator where adminName=? and adminStatus=?";

		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(hql);
		query.setString(0, userName);
		query.setInteger(1, 0);
		Administrator result = (Administrator) query.uniqueResult();

		if (UtilValidate.isNotEmpty(result)) {
			return result;
		} else {
			return null;
		}
	};

	@Override
	public String userForgetPassword(String userName) {

		String newPassword = null;

		session = this.sessionFactory.getCurrentSession();

		Administrator admin = getUser(userName);
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

	public boolean userResetPassword(String userName, String password) {

		session = this.sessionFactory.getCurrentSession();

		Administrator admin = getUser(userName);
		if (UtilValidate.isNotEmpty(admin)) {
			admin.setPassword(Encrypt.e(password));
			session.merge(admin);
			return true;
		}else{
			return false;
		}
		
	};

	public boolean userCreateOne(String userName, String password) {

		session = this.sessionFactory.getCurrentSession();

		Administrator result = getUser(userName);
		if (UtilValidate.isEmpty(result)) {
			Administrator admin = new Administrator();
			admin.setAdminName(userName);
			admin.setPassword(Encrypt.e(password));
			admin.setAdminStatus(0);
			session.save(admin);
			return true;
		} else {
			return false;
		}

	};

	public boolean userDeleteOne(String userName) {

		session = this.sessionFactory.getCurrentSession();

		Administrator admin = getUser(userName);
		if (UtilValidate.isNotEmpty(admin)) {
			session.delete(admin);
			return true;
		} else {
			return false;
		}
	};
}
