package com.web.soulyogaadmin.employee.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.soulyogaadmin.employee.dao.IEmployeeDao;
import com.web.soulyogaadmin.employee.service.IEmployeeService;
import com.web.soulyogaadmin.entity.Employee;
import com.web.soulyogaadmin.entity.EmployeeAccount;
import com.web.soulyogaadmin.entity.Teacher;
import com.web.soulyogaadmin.util.Des;
import com.web.soulyogaadmin.util.UtilValidate;
import com.web.soulyogaadmin.vo.EmployeeView;

/**
 * Employee Service Implemention
 * 
 * @author Comi Zhou
 * @version 2017-6-26
 */
@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeDao employeeDao;

	private static String className = EmployeeServiceImpl.class.getName();

	private static Logger logger = Logger.getLogger(className);
	
	@Override
	public boolean addEmployee(EmployeeView employeeView) {
		logger.debug("Entry class EmployeeServiceImpl method addEmployee");

		String identityId = employeeView.getIdentityId();
		Employee employeeFromDB = employeeDao.findEmployeeByIdentityId(identityId);

		if (employeeFromDB == null) {
			Employee employee = setEmployee(employeeView, new Employee());
			int isTeacher = employee.getIsTeacher();
			
			int employeeId = employeeDao.addEmployee(employee);

			EmployeeAccount employeeAccount = setEmployeeAccount(employeeView, new EmployeeAccount());
			employeeAccount.setEmployeeId(employeeId);
			employeeDao.addEmployeeAccount(employeeAccount);

			if (isTeacher == 1) {
				// Initialize and set Teacher object
				Teacher teacher = setTeacher(employeeView, new Teacher());
				teacher.setEmployeeId(employeeId);
				employeeDao.addTeacher(teacher);
			}

		} else if (employeeFromDB.getState() == 1) {
			// update Employee
			Employee employee = setEmployee(employeeView, employeeFromDB);
			int isTeacher = employee.getIsTeacher();
			employee.setModifiedTime(new Date());
			employee.setState(0);
			employeeDao.updEmployee(employee);

			// update EmployeeAccount
			int employeeId = employeeFromDB.getId();
			EmployeeAccount employeeAccountFromDB = employeeDao.findEmployeeAccountByEmployeeId(employeeId);
			EmployeeAccount employeeAccount = setEmployeeAccount(employeeView, employeeAccountFromDB);
			employeeAccount.setModifiedTime(new Date());
			employeeAccount.setState(0);
			employeeDao.updEmployeeAccount(employeeAccount);

			if (isTeacher == 1) {
				saveOrUpdateTeacher(employeeView, employeeId);
			}
		} else {
			logger.debug("End class EmployeeServiceImpl method addEmployee");
			return false;
		}
		logger.debug("End class EmployeeServiceImpl method addEmployee");
		return true;
	}

	@Override
	public boolean updEmployee(EmployeeView employeeView) {
		logger.debug("Entry class EmployeeServiceImpl method updEmployee");
		
		Integer employeeId = employeeView.getId();
		Employee employee = setEmployee(employeeView, employeeDao.findEmployeeById(employeeId));
		employee.setModifiedTime(new Date());
		employeeDao.updEmployee(employee);
		
		EmployeeAccount employeeAccount = setEmployeeAccount(employeeView, employeeDao.findEmployeeAccountByEmployeeId(employeeId));
		employeeAccount.setModifiedTime(new Date());
		employeeDao.updEmployeeAccount(employeeAccount);
		
		if (employee.getIsTeacher() == 1) {
			saveOrUpdateTeacher(employeeView, employeeId);
		}
		
		logger.debug("End class EmployeeServiceImpl method updEmployee");
		return true;
	}

	@Override
	public boolean delEmployee(int employeeId) {
		logger.debug("Entry class EmployeeServiceImpl method delEmployee");
		
		Employee employee = employeeDao.findEmployeeById(employeeId);
		employee.setModifiedTime(new Date());
		employee.setState(1);
		employeeDao.updEmployee(employee);
		
		EmployeeAccount employeeAccount = employeeDao.findEmployeeAccountByEmployeeId(employeeId);
		employeeAccount.setModifiedTime(new Date());
		employeeAccount.setState(1);
		employeeDao.updEmployeeAccount(employeeAccount);
		
		if (employee.getIsTeacher() == 1) {
			Teacher teacher = employeeDao.findTeacherByEmployeeId(employeeId);
			teacher.setModifiedTime(new Date());
			teacher.setState(1);
			employeeDao.updTeacher(teacher);
		}
		
		logger.debug("End class EmployeeServiceImpl method delEmployee");
		return true;
	}

	@Override
	public List<EmployeeView> findAllEmployee() {
		logger.debug("Entry class EmployeeServiceImpl method findAllEmployee");
		
		List<EmployeeView> employeeViews = employeeDao.findAllEmployee();
		
		logger.debug("End class EmployeeServiceImpl method findAllEmployee");
		return employeeViews;
	}

	@Override
	public EmployeeView findEmployeeById(int employeeId) {
		logger.debug("Entry class EmployeeServiceImpl method findEmployeeById");
		
		EmployeeView employeeView = employeeDao.findEmployeeViewById(employeeId);
		if (employeeView.getIsTeacher() == 1) {
			Teacher teacher = employeeDao.findTeacherByEmployeeId(employeeId);
			employeeView.setIntroduction(teacher.getIntroduction());
			employeeView.setCourseCategoryIds(teacher.getCourseCategoryIds());
		}
		
		logger.debug("End class EmployeeServiceImpl method findEmployeeById");
		return employeeView;
	}

	@Override
	public Map<Integer, String> findAllYogaClub() {
		logger.debug("Entry class EmployeeServiceImpl method findEmployeeById");
		
		Map<Integer, String> yogaclubMap = employeeDao.findAllYogaClub();
		
		logger.debug("End class EmployeeServiceImpl method findEmployeeById");
		return yogaclubMap;
	}

	@Override
	public Map<Integer, String> findAllPosition() {
		logger.debug("Entry class EmployeeServiceImpl method findEmployeeById");

		Map<Integer, String> positionMap = employeeDao.findAllPosition();
		
		logger.debug("End class EmployeeServiceImpl method findEmployeeById");
		return positionMap;
	}

	@Override
	public Map<Integer, String> findAllCourseCategory() {
		logger.debug("Entry class EmployeeServiceImpl method findAllCourseCategory");

		Map<Integer, String> coursecategoryMap = employeeDao.findAllCourseCategory();
		
		logger.debug("End class EmployeeServiceImpl method findAllCourseCategory");
		return coursecategoryMap;
	}
	
	@Override
	public List<EmployeeView> findEmployeeViaCondition(EmployeeView employeeView) {
		logger.debug("Entry class EmployeeServiceImpl method findEmployeeById");

		List<EmployeeView> employeeViews = employeeDao.findEmployeeViaCondition(employeeView);
		
		logger.debug("Entry class EmployeeServiceImpl method findEmployeeById");
		return employeeViews;
	}

	private void saveOrUpdateTeacher(EmployeeView employeeView, Integer employeeId) {
		Teacher teacherFromDB = employeeDao.findTeacherByEmployeeId(employeeId);
		if (UtilValidate.isNotEmpty(teacherFromDB)) {
			Teacher teacher = setTeacher(employeeView, teacherFromDB);
			teacher.setModifiedTime(new Date());
			teacher.setState(0);
			employeeDao.updTeacher(teacher);
		} else {
			Teacher teacher = setTeacher(employeeView, new Teacher());
			teacher.setEmployeeId(employeeId);
			employeeDao.addTeacher(teacher);
		}
	}
	
	// set Employee by EmployeeView
	private Employee setEmployee(EmployeeView employeeView, Employee employee) {
		employee.setPhoneNo(employeeView.getPhoneNo());
		int positionId = employeeView.getPositionId();
		employee.setPositionId(positionId);
		employee.setSurname(employeeView.getSurname());
		employee.setName(employeeView.getName());
		employee.setFristName(employeeView.getFristName());
		employee.setLastName(employeeView.getLastName());
		employee.setMail(employeeView.getMail());
		employee.setIdentityId(employeeView.getIdentityId());
		employee.setGender(employeeView.getGender());
		employee.setAvatarUrl(employeeView.getAvatarUrl());
		employee.setYogaClubId(employeeView.getYogaClubId());
		int isTeacher = 0;
		InputStream in = EmployeeServiceImpl.class.getClassLoader().getResourceAsStream("tableFieldConfig.properties");
		Properties properties = new Properties();
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String teacherPositionId = properties.getProperty("teacherPositionId");
		
		if (UtilValidate.isNotEmpty(teacherPositionId)) {
			if (positionId == Integer.parseInt(teacherPositionId)) {
				isTeacher = 1;
			}
		}
		employee.setIsTeacher(isTeacher);
		
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return employee;
	}
	
	// set EmployeeAccount by EmployeeView
	private EmployeeAccount setEmployeeAccount(EmployeeView employeeView, EmployeeAccount employeeAccount) {
		employeeAccount.setUserName(employeeView.getFristName() + employeeView.getLastName());
		String identityId = employeeView.getIdentityId();
		String initialPwd = identityId.substring(identityId.length() - 6, identityId.length());
		String keyStr = "0002000200020002";
		try {
			employeeAccount.setPassword(Des.Encrypt(initialPwd, Des.build3DesKey(keyStr)));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return employeeAccount;
	}

	// set Teacher by EmployeeView
	private Teacher setTeacher(EmployeeView employeeView, Teacher teacher) {
		teacher.setIntroduction(employeeView.getIntroduction());
		teacher.setCourseCategoryIds(employeeView.getCourseCategoryIds().replace(", ", ","));
		return teacher;
	}

}
