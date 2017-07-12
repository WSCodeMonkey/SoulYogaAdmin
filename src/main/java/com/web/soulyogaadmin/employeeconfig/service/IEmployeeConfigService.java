package com.web.soulyogaadmin.employeeconfig.service;

import java.util.List;

import com.web.soulyogaadmin.entity.Department;
import com.web.soulyogaadmin.vo.PositionView;

/**
 * EmployeeConfig Service Interface
 * 
 * @author Comi Zhou
 * @version 2017-6-22
 */
public interface IEmployeeConfigService {

	// Department

	public List<Department> findAllDepartments();

	public boolean addDepartment(String departmentName);

	public boolean updDepartment(int id, String departmentName);

	public boolean delDepartment(int id);

	// Position

	public List<PositionView> findAllPositions();

	public boolean addPosition(int departmentId, String positionName);

	public boolean updPosition(int id, int departmentId, String positionName);

	public boolean delPosition(int id);

}
