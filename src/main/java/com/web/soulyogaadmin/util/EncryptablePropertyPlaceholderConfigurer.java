package com.web.soulyogaadmin.util;
import java.util.Properties; 
import org.springframework.beans.BeansException; 
import org.springframework.beans.factory.BeanInitializationException; 
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory; 
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer; 

public class EncryptablePropertyPlaceholderConfigurer
		extends PropertyPlaceholderConfigurer {
	private static final String key = "0002000200020002";

	protected void processProperties(
			ConfigurableListableBeanFactory beanFactory,
			Properties props) throws BeansException {
		//System.out.println("正在解密系统文件...");
		try {
			Des des = new Des();
			String rkPassword = props
					.getProperty("jdbc_password");
			
			System.out.println("加密的連接密碼爲."+rkPassword);
			if (rkPassword != null) {
				String passwordVal = des.Decrypt(rkPassword, des.build3DesKey(key));
				System.out.println("解密的連接密碼爲" + passwordVal);
				props.setProperty("jdbc_password", passwordVal);
			}
			
			super.processProperties(beanFactory, props);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeanInitializationException(e.getMessage());
		}
	}
} 
