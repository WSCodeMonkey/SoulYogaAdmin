package com.web.soulyogaadmin.employee.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.soulyogaadmin.employee.dao.IEmployeeDao;
import com.web.soulyogaadmin.entity.Employee;
import com.web.soulyogaadmin.entity.EmployeeAccount;
import com.web.soulyogaadmin.entity.Teacher;
import com.web.soulyogaadmin.vo.EmployeeView;

/**
 * Employee Dao Implemention
 * 
 * @author Comi Zhou
 * @version 2017-6-26
 */
@Repository("employeeDao")
public class EmployeeDaoImpl implements IEmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session session;

	private String className = EmployeeDaoImpl.class.getName();

	private Logger logger = Logger.getLogger(className);

	// Employee

	@Override
	public int addEmployee(Employee employee) {
		logger.debug("Entry class EmployeeDaoImpl method addEmployee");
		
		session = sessionFactory.getCurrentSession();
		session.save(employee);
		
		logger.debug("End class EmployeeDaoImpl method addEmployee");
		return employee.getId();
	}

	@Override
	public void updEmployee(Employee employee) {
		logger.debug("Entry class EmployeeDaoImpl method updEmployee");
		
		session = sessionFactory.getCurrentSession();
		session.update(employee);
		
		logger.debug("End class EmployeeDaoImpl method updEmployee");
	}

	@Override
	public List<EmployeeView> findAllEmployee() {
		logger.debug("Entry class EmployeeDaoImpl method findAllEmployee");
		
		String sql = "select e.ID id, e.Surname surname, e.Name name, e.FristName fristName, e.LastName lastName, e.Mail mail, e.Gender gender, p.PositionName positionName, y.Name yogaClubName"
				+ " from position p right join employee e on e.PositionID = p.ID left join yogaclub y on e.YogaClubID = y.ID where e.state = 0";
		session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(sql);
		query.setResultTransformer(Transformers.aliasToBean(EmployeeView.class));
		
		@SuppressWarnings("unchecked")
		List<EmployeeView> employeeViews = query.list();
		
		logger.debug("End class EmployeeDaoImpl method findAllEmployee");
		return employeeViews;
	}

	@Override
	public Employee findEmployeeById(int employeeId) {
		String hql = "from Employee where id = ?";
		session = sessionFactory.getCurrentSession();
		Employee employee = (Employee) session.createQuery(hql).setInteger(0, employeeId).uniqueResult();
		return employee;
	}
	
	@Override
	public EmployeeView findEmployeeViewById(int employeeId) {
		logger.debug("Entry class EmployeeDaoImpl method findEmployeeById");
		
		String sql = "select e.ID id, e.Surname surname, e.Name name, e.FristName fristName, e.LastName lastName, e.Mail mail, e.IdentityID identityId, e.Gender gender, e.AvatarUrl avatarUrl, e.IsTeacher isTeacher, p.PositionName positionName, y.Name yogaClubName"
				+ " from position p right join employee e on e.PositionID = p.ID left join yogaclub y on e.YogaClubID = y.ID where e.state = 0 and e.id = ?";
		session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(sql);
		query.setResultTransformer(Transformers.aliasToBean(EmployeeView.class));
		query.setInteger(0, employeeId);
		EmployeeView employeeView = (EmployeeView) query.uniqueResult();
		
		logger.debug("End class EmployeeDaoImpl method findEmployeeById");
		return employeeView;
	}

	@Override
	public Employee findEmployeeByIdentityId(String identityId) {
		logger.debug("Entry class EmployeeDaoImpl method findEmployeeByIdentityId");
		
		String hql = "from Employee where identityId = ?";
		session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql).setString(0, identityId);
		Employee employee = (Employee) query.uniqueResult();
		
		logger.debug("End class EmployeeDaoImpl method findEmployeeByIdentityId");
		return employee;
	}

	// Teacher

	@Override
	public void addTeacher(Teacher teacher) {
		logger.debug("Entry class EmployeeDaoImpl method addTeacher");
		
		session = sessionFactory.getCurrentSession();
		session.save(teacher);
		
		logger.debug("End class EmployeeDaoImpl method addTeacher");
	}

	@Override
	public void updTeacher(Teacher teacher) {
		logger.debug("Entry class EmployeeDaoImpl method updTeacher");
		
		session = sessionFactory.getCurrentSession();
		session.update(teacher);
		
		logger.debug("End class EmployeeDaoImpl method updTeacher");
	}

	@Override
	public Teacher findTeacherByEmployeeId(int employeeId) {
		logger.debug("Entry class EmployeeDaoImpl method findTeacherByEmployeeId");
		
		String hql = "from Teacher where employeeId = ?";
		session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger(0, employeeId);
		Teacher teacher = (Teacher) query.uniqueResult();
		
		logger.debug("End class EmployeeDaoImpl method findTeacherByEmployeeId");
		return teacher;
	}

	// EmployeeAccount
	
	@Override
	public void addEmployeeAccount(EmployeeAccount employeeAccount) {
		logger.debug("Entry class EmployeeDaoImpl method addEmployeeAccount");
		
		session = sessionFactory.getCurrentSession();
		session.save(employeeAccount);
		
		logger.debug("End class EmployeeDaoImpl method addEmployeeAccount");
	}

	@Override
	public void updEmployeeAccount(EmployeeAccount employeeAccount) {
		logger.debug("Entry class EmployeeDaoImpl method updEmployeeAccount");
		
		session = sessionFactory.getCurrentSession();
		session.update(employeeAccount);
		
		logger.debug("End class EmployeeDaoImpl method updEmployeeAccount");
	}

	@Override
	public EmployeeAccount findEmployeeAccountByEmployeeId(int employeeId) {
		logger.debug("Entry class EmployeeDaoImpl method findEmployeeAccountByEmployeeId");
		
		String hql = "from EmployeeAccount where employeeId = ?";
		session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger(0, employeeId);
		EmployeeAccount employeeAccount = (EmployeeAccount) query.uniqueResult();
		
		logger.debug("End class EmployeeDaoImpl method findEmployeeAccountByEmployeeId");
		return employeeAccount;
	}

	
}
