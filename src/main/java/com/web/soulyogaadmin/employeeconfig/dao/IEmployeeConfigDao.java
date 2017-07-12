package com.web.soulyogaadmin.employeeconfig.dao;

import java.util.List;

import com.web.soulyogaadmin.entity.Department;
import com.web.soulyogaadmin.entity.Position;
import com.web.soulyogaadmin.vo.PositionView;

/**
 * EmployeeConfig Dao Interface
 * 
 * @author Comi Zhou
 * @version 2017-6-22
 */
public interface IEmployeeConfigDao {

	// Department

	public List<Department> findAllDepartments();

	public void addDepartment(Department department);

	public void updDepartment(Department department);

	public Department findDepartmentByName(String departmentName);

	public Department findDepartmentById(int id);

	// Position

	public List<PositionView> findAllPositions();

	public void addPosition(Position position);

	public void updPosition(Position position);

	public Position findPositionByName(int departmentId, String positionName);

	public Position findPositionById(int id);

}
