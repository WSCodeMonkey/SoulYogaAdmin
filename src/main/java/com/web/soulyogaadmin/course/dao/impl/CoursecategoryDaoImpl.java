package com.web.soulyogaadmin.course.dao.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.soulyogaadmin.course.dao.ICoursecategoryDao;
import com.web.soulyogaadmin.entity.Coursecategory;

@Repository("coursecategoryDao")
@Transactional
public class CoursecategoryDaoImpl implements ICoursecategoryDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session session;

	private static String className = CoursecategoryDaoImpl.class.getName();

	private static Logger logger = Logger.getLogger(className);

	@SuppressWarnings("unchecked")
	@Override
	public List<Coursecategory> getAllCoursecategory() {
		// TODO Auto-generated method stub
		// Configuration configiguration = new Configuration().configure();
		// ServiceRegistryBuilder builder = new
		// ServiceRegistryBuilder().applySettings(configiguration.getProperties());
		// ServiceRegistry registry = builder.buildServiceRegistry();
		// sessionFactory = configiguration.buildSessionFactory(registry);
		session = sessionFactory.openSession();

		String hql = "from Coursecategory";
		Query query = session.createQuery(hql);
		List<Coursecategory> lists = query.list();

		session.close();
		if (lists.size() > 0) {
			return lists;
		}
		return null;

	}

	@Override
	public List<Coursecategory> seperateCoursecategoryList(int page, int pageSize) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();

		String hql = "from Coursecategory";

		List<Coursecategory> lists = session.createQuery(hql).setFirstResult(1).setMaxResults(1).list();

		session.close();
		if (lists.size() > 0) {
			for (Coursecategory c : lists) {
				System.out.println(c.toString());
			}
			return lists;
		}
		return null;

	}

	@Override
	public List<Coursecategory> getSearchedCoursecategory(int state) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();

		String hql = "from Coursecategory where State=:state";
		@SuppressWarnings("unchecked")
		List<Coursecategory> lists = session.createQuery(hql).setInteger("state", state).list();

		session.close();
		if (lists.size() > 0) {

			return lists;
		}
		return null;
	}

	@Override
	public void add(Coursecategory coursecategory) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		session.save(coursecategory);
		session.close();
	}

	@Override
	public void delete(Coursecategory coursecategory) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		session.createQuery("update Coursecategory set state =:state where id = :idPara ")
				.setInteger("idPara", coursecategory.getId()).setInteger("state", (1 - coursecategory.getState()))
				.executeUpdate();
		session.close();

	}

	@Override
	public Coursecategory getCoursecategoryById(int id) {
		session = sessionFactory.openSession();
		Coursecategory returnObj = (Coursecategory) session.get(Coursecategory.class, new Integer(id + ""));
		return returnObj;

	}

	@Override
	public void modify(Coursecategory coursecategory) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		session.saveOrUpdate(coursecategory);
		session.close();

	}

	@Override
	public List<Coursecategory> getAllCoursecategoryById(int id) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();

		String hql = "from Coursecategory where id=:id";
		Query query = session.createQuery(hql).setInteger("id", id);
		List<Coursecategory> lists = query.list();

		session.close();
		if (lists.size() > 0) {
			return lists;
		}
		return null;
	}

	@Override
	public List<Coursecategory> getAllCoursecategoryByName(String name) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();

		String hql = "from Coursecategory where name=:name";
		Query query = session.createQuery(hql).setString("name", name);
		List<Coursecategory> lists = query.list();

		session.close();
		if (lists.size() > 0) {
			return lists;
		}
		return null;
	}

	@Override
	public void changeCoursecategoryModifiedTime(int id, Date modifiedTime) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();

		String hql = "update Coursecategory set modifiedTime =:modifiedTime where id = :idPara";
		session.createQuery(hql).setTimestamp("modifiedTime", modifiedTime).setInteger("idPara", id).executeUpdate();
		// List<Coursecategory> lists = query.list();

		session.close();

	}

}
