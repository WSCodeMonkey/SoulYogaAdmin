package com.web.soulyogaadmin.employee.dao;

import java.util.List;

import com.web.soulyogaadmin.entity.Employee;
import com.web.soulyogaadmin.entity.EmployeeAccount;
import com.web.soulyogaadmin.entity.Teacher;
import com.web.soulyogaadmin.vo.EmployeeView;

/**
 * Employee Dao Interface
 * 
 * @author Comi Zhou
 * @version 2017-6-26
 */
public interface IEmployeeDao {

	// Employee

	public int addEmployee(Employee employee);

	public void updEmployee(Employee employee);

	public List<EmployeeView> findAllEmployee();

	public Employee findEmployeeById(int employeeId);

	public EmployeeView findEmployeeViewById(int employeeId);

	public Employee findEmployeeByIdentityId(String identityId);

	// Teacher

	public void addTeacher(Teacher teacher);

	public void updTeacher(Teacher teacher);

	public Teacher findTeacherByEmployeeId(int employeeId);

	// EmployeeAccount

	public void addEmployeeAccount(EmployeeAccount employeeAccount);

	public void updEmployeeAccount(EmployeeAccount employeeAccount);

	public EmployeeAccount findEmployeeAccountByEmployeeId(int employeeId);
}
