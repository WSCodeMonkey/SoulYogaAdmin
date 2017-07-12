package com.web.soulyogaadmin.employeeconfig.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.soulyogaadmin.employeeconfig.dao.IEmployeeConfigDao;
import com.web.soulyogaadmin.employeeconfig.service.IEmployeeConfigService;
import com.web.soulyogaadmin.entity.Department;
import com.web.soulyogaadmin.entity.Position;
import com.web.soulyogaadmin.vo.PositionView;

/**
 * Infrastructure Service Implemention
 * 
 * @author Comi Zhou
 * @version 2017-6-22
 */

@Service("employeeConfigService")
@Transactional
public class EmployeeConfigServiceImpl implements IEmployeeConfigService {

	@Autowired
	private IEmployeeConfigDao employeeConfigDao;

	private static String className = EmployeeConfigServiceImpl.class.getName();

	private static Logger logger = Logger.getLogger(className); // TODO 本类日志功能暂未添加

	// Department
	
	@Override
	public List<Department> findAllDepartments() {
		logger.info("Show All Departments at " + new Date());
		List<Department> departments = employeeConfigDao.findAllDepartments();
		return departments;
	}

	@Override
	public boolean addDepartment(String departmentName) {
		Department departmentFromDB = employeeConfigDao.findDepartmentByName(departmentName);
		if (departmentFromDB == null) {
			Department department = new Department();
			department.setName(departmentName);
			employeeConfigDao.addDepartment(department);
		} else if(departmentFromDB.getState() == 1) {
			departmentFromDB.setModifiedTime(new Date());
			departmentFromDB.setState(0);
			employeeConfigDao.updDepartment(departmentFromDB);
		} else {
			return false;
		}
		return true;
	}

	@Override
	public boolean updDepartment(int id, String departmentName) {
		Department department = employeeConfigDao.findDepartmentById(id);
		department.setName(departmentName);
		department.setModifiedTime(new Date());
		employeeConfigDao.updDepartment(department);
		return true;
	}

	@Override
	public boolean delDepartment(int id) {
		Department department = employeeConfigDao.findDepartmentById(id);
		department.setModifiedTime(new Date());
		department.setState(1);
		employeeConfigDao.updDepartment(department);
		return true;
	}

	// Position
	
	@Override
	public List<PositionView> findAllPositions() {
		List<PositionView> positionViews = employeeConfigDao.findAllPositions();
		return positionViews;
	}

	@Override
	public boolean addPosition(int departmentId, String positionName) {
		Position positionFromDB = employeeConfigDao.findPositionByName(departmentId, positionName);
		if (positionFromDB == null) {
			Position position = new Position();
			position.setDepartmentId(departmentId);
			position.setPositionName(positionName);
			employeeConfigDao.addPosition(position);
		} else if (positionFromDB.getState() == 1) {
			positionFromDB.setModifiedTime(new Date());
			positionFromDB.setState(0);
			employeeConfigDao.updPosition(positionFromDB);
		} else {
			return false;
		}
		return true;
	}

	@Override
	public boolean updPosition(int id, int departmentId, String positionName) {
		Position position = employeeConfigDao.findPositionById(id);
		position.setDepartmentId(departmentId);
		position.setPositionName(positionName);
		position.setModifiedTime(new Date());
		employeeConfigDao.updPosition(position);
		return true;
	}

	@Override
	public boolean delPosition(int id) {
		Position position = employeeConfigDao.findPositionById(id);
		position.setModifiedTime(new Date());
		position.setState(1);
		employeeConfigDao.updPosition(position);
		return true;
	}

}
