package com.web.soulyogaadmin.employee.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

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

		// Initialize and set Employee object
		Employee employee = setEmployee(employeeView);
		int isTeacher = employee.getIsTeacher();
		
		// Initialize and set EmployeeAccount object
		EmployeeAccount employeeAccount = setEmployeeAccount(employeeView);

		if (employeeFromDB == null) {
			int employeeId = employeeDao.addEmployee(employee);

			employeeAccount.setEmployeeId(employeeId);
			employeeDao.addEmployeeAccount(employeeAccount);

			if (isTeacher == 1) {

				// Initialize and set Teacher object
				Teacher teacher = new Teacher();
				teacher.setIntroduction(employeeView.getIntroduction());
				teacher.setCourseCategoryIds(employeeView.getCourseCategoryIds().replace(", ", ","));
				teacher.setEmployeeId(employeeId);
				employeeDao.addTeacher(teacher);
			}

		} else if (employeeFromDB.getState() == 1) {
			int employeeId = employeeFromDB.getId();

			// update Employee
			employee.setId(employeeId);
			employee.setModifiedTime(new Date());
			employee.setState(0);
			employeeDao.updEmployee(employee);

			// update EmployeeAccount
			employeeAccount.setEmployeeId(employeeId);
			employeeAccount.setState(0);
			employeeAccount.setModifiedTime(new Date());
			employeeDao.updEmployeeAccount(employeeAccount);

			if (isTeacher == 1) {
				Teacher teacherFromDB = employeeDao.findTeacherByEmployeeId(employeeId);
				Teacher teacher = new Teacher();
				teacher.setIntroduction(employeeView.getIntroduction());
				teacher.setCourseCategoryIds(employeeView.getCourseCategoryIds().replace(", ", ","));
				
				if (UtilValidate.isNotEmpty(teacherFromDB)) {
					teacher.setEmployeeId(teacherFromDB.getEmployeeId());
					teacher.setModifiedTime(new Date());
					teacher.setState(0);
					employeeDao.updTeacher(teacher);
				} else {
					teacher.setEmployeeId(employeeId);
					employeeDao.addTeacher(teacher);
				}
				
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
		
		Employee employee = setEmployee(employeeView);
		Integer employeeId = employeeView.getId();
		employee.setId(employeeId);
		employee.setModifiedTime(new Date());
		employeeDao.updEmployee(employee);
		
		EmployeeAccount employeeAccount = setEmployeeAccount(employeeView);
		employeeAccount.setId(employeeDao.findEmployeeAccountByEmployeeId(employeeId).getId());
		employeeAccount.setModifiedTime(new Date());
		employeeDao.updEmployeeAccount(employeeAccount);
		
		if (employee.getIsTeacher() == 1) {
			Teacher teacherFromDB = employeeDao.findTeacherByEmployeeId(employeeId);
			Teacher teacher = new Teacher();
			teacher.setEmployeeId(employeeId);
			teacher.setIntroduction(employeeView.getIntroduction());
			teacher.setCourseCategoryIds(employeeView.getCourseCategoryIds().replace(", ", ","));
			
			if (UtilValidate.isNotEmpty(teacherFromDB)) {
				teacher.setModifiedTime(new Date());
				teacher.setState(0);
				employeeDao.updTeacher(teacher);
			} else {
				employeeDao.addTeacher(teacher);
			}
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
		}
		
		logger.debug("End class EmployeeServiceImpl method delEmployee");
		return false;
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

	// set Employee by EmployeeView
	private Employee setEmployee(EmployeeView employeeView) {
		Employee employee = new Employee();
		
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
		if (positionId == 2) { // 设定老师的职位ID
			isTeacher = 1;
		}
		employee.setIsTeacher(isTeacher);
		
		return employee;
	}
	
	// set EmployeeAccount by EmployeeView
	private EmployeeAccount setEmployeeAccount(EmployeeView employeeView) {
		EmployeeAccount employeeAccount = new EmployeeAccount();
		
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

}
