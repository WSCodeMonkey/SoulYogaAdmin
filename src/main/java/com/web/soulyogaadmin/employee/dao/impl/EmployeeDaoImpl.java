package com.web.soulyogaadmin.employee.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.soulyogaadmin.employee.dao.IEmployeeDao;
import com.web.soulyogaadmin.entity.Coursecategory;
import com.web.soulyogaadmin.entity.Employee;
import com.web.soulyogaadmin.entity.EmployeeAccount;
import com.web.soulyogaadmin.entity.Position;
import com.web.soulyogaadmin.entity.Teacher;
import com.web.soulyogaadmin.entity.Yogaclub;
import com.web.soulyogaadmin.util.UtilValidate;
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

		String sql = "select e.ID id, e.PhoneNo phoneNo, e.Surname surname, e.Name name, e.FristName fristName, e.LastName lastName, e.Mail mail, e.Gender gender, p.PositionName positionName, y.Name yogaClubName"
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
		logger.debug("Entry class EmployeeDaoImpl method findEmployeeById");
		
		String hql = "from Employee where id = ?";
		session = sessionFactory.getCurrentSession();
		Employee employee = (Employee) session.createQuery(hql).setInteger(0, employeeId).uniqueResult();
		
		logger.debug("Entry class EmployeeDaoImpl method findEmployeeById");
		return employee;
	}

	@Override
	public EmployeeView findEmployeeViewById(int employeeId) {
		logger.debug("Entry class EmployeeDaoImpl method findEmployeeViewById");

		String sql = "select e.ID id, e.PhoneNo phoneNo, e.Surname surname, e.Name name, e.FristName fristName, e.LastName lastName, e.Mail mail, e.IdentityID identityId, e.Gender gender, e.AvatarUrl avatarUrl, e.IsTeacher isTeacher, p.ID positionId, p.PositionName positionName, y.Name yogaClubName"
				+ " from position p right join employee e on e.PositionID = p.ID left join yogaclub y on e.YogaClubID = y.ID where e.state = 0 and e.id = ?";
		session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(sql);
		query.setResultTransformer(Transformers.aliasToBean(EmployeeView.class));
		query.setInteger(0, employeeId);
		EmployeeView employeeView = (EmployeeView) query.uniqueResult();

		logger.debug("End class EmployeeDaoImpl method findEmployeeViewById");
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
		session.merge(employeeAccount);
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

	@Override
	public Map<Integer, String> findAllYogaClub() {
		logger.debug("Entry class EmployeeDaoImpl method findAllYogaClub");

		String sql = "select id, name from yogaclub where state = 0";
		session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Yogaclub> yogaclubs = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Yogaclub.class)).list();
		Map<Integer, String> yogaclubMap = new HashMap<>();
		if (UtilValidate.isNotEmpty(yogaclubs)) {
			for(Yogaclub yogaclub : yogaclubs) {
				yogaclubMap.put(yogaclub.getId(), yogaclub.getName());
			}
			logger.debug("End class EmployeeDaoImpl method findAllYogaClub");
			return yogaclubMap;
		} else {
			logger.debug("End class EmployeeDaoImpl method findAllYogaClub");
			return null;
		}
	}

	@Override
	public Map<Integer, String> findAllPosition() {
		logger.debug("Entry class EmployeeDaoImpl method findAllPosition");

		String sql = "select id, positionName from position where state = 0";
		session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Position> positions = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Position.class)).list();
		Map<Integer, String> positionMap = new HashMap<>();
		if (UtilValidate.isNotEmpty(positions)) {
			for(Position position : positions) {
				positionMap.put(position.getId(), position.getPositionName());
			}
			logger.debug("End class EmployeeDaoImpl method findAllPosition");
			return positionMap;
		} else {
			logger.debug("End class EmployeeDaoImpl method findAllPosition");
			return null;
		}
	}
	
	@Override
	public Map<Integer, String> findAllCourseCategory() {
		logger.debug("Entry class EmployeeDaoImpl method findAllCourseCategory");
		
		String sql = "select id, name from coursecategory where state = 0";
		session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Coursecategory> coursecategories = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Coursecategory.class)).list();
		Map<Integer, String> coursecategoryMap = new HashMap<>();
		if (UtilValidate.isNotEmpty(coursecategories)) {
			for(Coursecategory coursecategory : coursecategories) {
				coursecategoryMap.put(coursecategory.getId(), coursecategory.getName());
			}
			logger.debug("End class EmployeeDaoImpl method findAllCourseCategory");
			return coursecategoryMap;
		} else {
			logger.debug("End class EmployeeDaoImpl method findAllCourseCategory");
			return null;
		}
	}

	@Override
	public List<EmployeeView> findEmployeeViaCondition(EmployeeView employeeView) {
		logger.debug("Entry class EmployeeDaoImpl method findEmployeeViaCondition");

		Integer id = employeeView.getId();
		String name = employeeView.getName();
		Integer yogaclubId = employeeView.getYogaClubId();
		Integer isTeacher = employeeView.getIsTeacher();
		StringBuffer sql = new StringBuffer("select e.ID id, e.PhoneNo phoneNo, e.Surname surname, e.Name name, e.FristName fristName, e.LastName lastName, e.Mail mail, e.Gender gender, p.PositionName positionName, y.Name yogaClubName"
						+ " from position p right join employee e on e.PositionID = p.ID left join yogaclub y on e.YogaClubID = y.ID where e.state = 0");
		if (UtilValidate.isNotEmpty(id)) {
			sql.append(" and e.id = ").append(id);
		}
		if (UtilValidate.isNotEmpty(name)) {
			String nameStr = "'%" + name + "%'";
			sql.append(" and e.surname like " + nameStr).append(" or e.name like " + nameStr).append(" or e.fristname like " + nameStr).append(" or e.lastname like " + nameStr);
		}
		if (UtilValidate.isNotEmpty(yogaclubId)) {
			sql.append(" and e.yogaclubId = ").append(yogaclubId);
		}
		if (UtilValidate.isNotEmpty(isTeacher)) {
			sql.append(" and e.isTeacher = ").append(isTeacher);
		}
		
		session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(sql.toString());
		query.setResultTransformer(Transformers.aliasToBean(EmployeeView.class));

		@SuppressWarnings("unchecked")
		List<EmployeeView> employeeViews = query.list();

		logger.debug("End class EmployeeDaoImpl method findAllEmployee");
		return employeeViews;
	}
}
