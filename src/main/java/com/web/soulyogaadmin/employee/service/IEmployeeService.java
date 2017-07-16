package com.web.soulyogaadmin.employee.service;

import java.util.List;
import java.util.Map;

import com.web.soulyogaadmin.vo.EmployeeView;

/**
 * Employee Service Interface
 * 
 * @author Comi Zhou
 * @version 2017-6-26
 */
public interface IEmployeeService {

	public boolean addEmployee(EmployeeView employeeView);

	public boolean updEmployee(EmployeeView employeeView);

	public boolean delEmployee(int employeeId);

	public List<EmployeeView> findAllEmployee();
	
	public EmployeeView findEmployeeById(int employeeId);

	public Map<Integer, String> findAllYogaClub();

	public Map<Integer, String> findAllPosition();

	public Map<Integer, String> findAllCourseCategory();
	
	public List<EmployeeView> findEmployeeViaCondition(EmployeeView employeeView);
}
