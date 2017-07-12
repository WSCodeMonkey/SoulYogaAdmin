package com.web.soulyogaadmin.employeeconfig.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.soulyogaadmin.employeeconfig.dao.IEmployeeConfigDao;
import com.web.soulyogaadmin.entity.Department;
import com.web.soulyogaadmin.entity.Position;
import com.web.soulyogaadmin.util.UtilValidate;
import com.web.soulyogaadmin.vo.PositionView;

/**
 * EmployeeConfig Dao Implemention
 * 
 * @author Comi Zhou
 * @version 2017-6-22
 */

@Repository("employeeConfigDao")
public class EmployeeConfigDaoImpl implements IEmployeeConfigDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session session;

	private String className = EmployeeConfigDaoImpl.class.getName();

	private Logger logger = Logger.getLogger(className); // TODO 本类日志功能暂未添加

	// Department

	@Override
	public List<Department> findAllDepartments() {
		logger.info("Show All Departments");

		String hql = "from Department where state = 0";
		session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Department> departments = query.list();

		if (UtilValidate.isNotEmpty(departments)) {
			return departments;
		} else { 
			return null;
		}
	}

	@Override
	public void addDepartment(Department department) {
		session = sessionFactory.getCurrentSession();
		session.save(department);
	}

	@Override
	public void updDepartment(Department department) {
		session = sessionFactory.getCurrentSession();
		session.update(department);
	}

	@Override
	public Department findDepartmentByName(String departmentName) {
		String hql = "from Department where name = ?";
		session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, departmentName);
		Department department = (Department) query.uniqueResult();

		if (UtilValidate.isNotEmpty(department)) {
			return department;
		} else {
			return null;
		}
	}

	// Position
	
	@Override
	public Department findDepartmentById(int id) {
		String hql = "from Department where id = ?";
		session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger(0, id);
		Department department = (Department) query.uniqueResult();

		if (UtilValidate.isNotEmpty(department)) {
			return department;
		} else {
			return null;
		}
	}

	// Position

	@Override
	public List<PositionView> findAllPositions() {
		String sql = "select p.ID id,d.Name departmentName,p.PositionName positionName,p.CreatedTime createdTime,p.ModifiedTime modifiedTime "
						+ "from position p left join department d on p.departmentid = d.id"
						+ " where p.state = 0 and d.state = 0";
		session  = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(PositionView.class));
		
		@SuppressWarnings("unchecked")
		List<PositionView> positionViews = query.list();
		
		return positionViews;
	}

	@Override
	public void addPosition(Position position) {
		session = sessionFactory.getCurrentSession();
		session.save(position);
	}

	@Override
	public void updPosition(Position position) {
		session = sessionFactory.getCurrentSession();
		session.update(position);
	}

	@Override
	public Position findPositionByName(int departmentId, String positionName) {
		String hql = "from Position where departmentId = ? and positionName = ?";
		session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger(0, departmentId);
		query.setString(1, positionName);
		Position position = (Position) query.uniqueResult();
		return position;
	}

	@Override
	public Position findPositionById(int id) {
		String hql = "from Position where id = ?";
		session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger(0, id);
		Position position = (Position) query.uniqueResult();
		return position;
	}
}
