package com.aig.postsaleadmin;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.web.soulyogaadmin.employee.service.IEmployeeAccountService;



/**
 * Test User Service
 * @author Shawn xiao
 * @version 2017-06-15 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml","classpath:spring-hibernate.xml"})
public class TestUserService { 

//	IUserService userService = new UserServiceImpl();
	
//	IEmployeeAccountService employeeAccService = new EmployeeAccountServiceImpl();
	
	@Autowired
	IEmployeeAccountService employeeAccountService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

//	@Before
//	public void setUp() throws Exception {
//		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] { "classpath:spring.xml", "classpath:spring-hibernate.xml" });
//		employeeAccountService = (IEmployeeAccountService) ac.getBean("employeeAccountService");
//	} 

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUserLogin() {
		assertTrue(employeeAccountService.employeeAccountLogin("Admin", "Admin"));	
	}

	@Test
	public void testUserForgetPassword() {
//		String newPassword = userService.userForgetPassword("adminTest2");
//		assertNotNull(newPassword);
//		assertTrue(userService.userLogin("adminTest2", newPassword));	
	}

	@Test
	public void testUserResetPassword() {
//		assertTrue(userService.userResetPassword("adminTest2", "newpassword"));
//		assertTrue(employeeAccService.employeeAccountLogin("Admin", "Admin"));	
	}

}
