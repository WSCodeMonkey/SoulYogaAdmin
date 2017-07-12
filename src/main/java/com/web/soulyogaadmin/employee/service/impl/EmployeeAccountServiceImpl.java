package com.web.soulyogaadmin.employee.service.impl;

import java.util.Date;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.web.soulyogaadmin.employee.dao.IEmployeeAccountDao;
import com.web.soulyogaadmin.employee.service.IEmployeeAccountService;
import com.web.soulyogaadmin.util.Des;
import com.web.soulyogaadmin.util.HttpXmlClient;
import com.web.soulyogaadmin.util.UtilProperties;
import com.web.soulyogaadmin.util.UtilSSL;
import com.web.soulyogaadmin.util.UtilValidate;

/**
 * User Service Implemention
 * @author Shawn xiao
 * @version 2017-06-15
 */


@Service("employeeAccountService")
@Transactional
public class EmployeeAccountServiceImpl implements IEmployeeAccountService {

	@Autowired
	private IEmployeeAccountDao employeeAccountDao;

	private static String className = EmployeeAccountServiceImpl.class.getName();

	private static Logger logger = Logger.getLogger(className);
	
	
	@Override
	public boolean employeeAccountLogin(String userName, String password) {
		
		logger.info("Begin admin login at " + new Date()) ;
		logger.debug("Begin admin login with account: "+userName) ;
		
		boolean result = employeeAccountDao.employeeAccountLogin(userName, password);
		
		logger.info("End admin login at " + new Date() + "with " + result) ;
		return result;
	};

	@Override
	public String employeeAccountForgetPassword(String userName) {
		
		logger.info("Begin admin forget password at " + new Date()) ;
		logger.debug("Begin admin forget password with account: "+userName) ;
		
		String newPassword = employeeAccountDao.forgetPassword(userName);
		boolean messageStatus=false;
		if (UtilValidate.isNotEmpty(newPassword)) {
			// call send email communication interface
			
				JSONObject parameterMap=new JSONObject();
				parameterMap.put("'email_address", userName);
				parameterMap.put("subscriber", "Admin");
				parameterMap.put("template_id", "ADMIN001");
				JSONArray message_content=new JSONArray();
				JSONObject contentObj=new JSONObject();
				contentObj.put("message_key", "PASSWORD");
				contentObj.put("message_value", newPassword);
				message_content.add(contentObj);
				parameterMap.put("message_content", message_content);
				logger.info("Admin send message request:-------"+parameterMap.toString());
				try {
					UtilProperties p = UtilProperties.getUtilproperties();
					String localCert = p.readProperty("localCert");	
					
					String certPassword =Des.desPassword(p.readProperty("certPassword"));	
							
					String keystoreFile = p.readProperty("keystore");		
					
					String keyStorePassword =Des.desPassword(p.readProperty("keyStorePassword"));
							
				        @SuppressWarnings("deprecation")
					SSLConnectionSocketFactory sslsf = UtilSSL.generateSSL(localCert,
							keystoreFile, certPassword, keyStorePassword);
				        
					//String responceString=	new HttpRequestor().jsonPost(UtilProperties.getUtilproperties().readValue("mpi.URL"), parameterMap);
				    String responceString=HttpXmlClient.post(UtilProperties.getUtilproperties().readValue("mpi.URL"), parameterMap.toString(), sslsf);
					logger.debug("OTP sms message response:------"+responceString);
					/*JSONObject responceJson= JSONObject.fromObject(responceString);
					if(responceJson.getString("errorCode").equals("0")){
						messageStatus=true;
					}*/
					if(responceString.contains("ok")){
						messageStatus=true;
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			
			
			
			
			logger.info("End admin forget password at " + new Date()) ;
			//logger.debug("End admin forget password with new password: "+newPassword) ;
			return newPassword;
		}
		return null;
	};

	public boolean employeeAccountResetPassword(String userName, String password) {
		
		logger.info("Begin admin reset password at " + new Date()) ;
		logger.debug("Begin admin reset password with account: "+userName) ;
		
		boolean result = employeeAccountDao.resetPassword(userName, password);
		
		logger.info("End admin reset password at " + new Date()) ;
		//logger.debug("End admin reset password with new password: "+password) ;

		
		return result;
	}


}
