package com.web.soulyogaadmin.space.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.soulyogaadmin.course.dao.ICourseDao;
import com.web.soulyogaadmin.entity.Classroom;
import com.web.soulyogaadmin.entity.CourseEntry;
import com.web.soulyogaadmin.entity.Locker;
import com.web.soulyogaadmin.entity.Yogaclub;
import com.web.soulyogaadmin.space.dao.IClassroomDao;
import com.web.soulyogaadmin.space.dao.IYogaclubDao;
import com.web.soulyogaadmin.util.UtilValidate;

/**
 * Course Dao Implemention
 * 
 * @author Shawn Xiao
 * @version 2017-06-15
 */
@Repository("yogaclubDao")
@Transactional
public class YogaclubDaoImpl implements IYogaclubDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session session;

	private static String className = YogaclubDaoImpl.class.getName();

	private static Logger logger = Logger.getLogger(className);

	public List getAllYogaclubList() {

		String hql = null;
		session = this.sessionFactory.getCurrentSession();

		hql = "from Yogaclub y where y.state=0 ";

		Query query = session.createQuery(hql);

		// if ("All".equals(templateId) && !"All".equals(channel)) {
		// query.setParameter(0, channel);
		// } else if (!"All".equals(templateId)
		// && ("All".equals(channel))) {
		// query.setParameter(0, templateId);
		// } else if (!"All".equals(templateId)
		// && !"All".equals(channel)) {
		// query.setParameter(0, templateId);
		// query.setParameter(1, channel);
		// }

		List<Yogaclub> list = query.list();

		if (list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public void deleteYogaclubbyId(int i) {
		session = this.sessionFactory.getCurrentSession();

		Yogaclub yogaclub = getYogaclubbyId(i);
		System.out.println(yogaclub.getId());
		yogaclub.setState(1);
		session.merge(yogaclub);

	}

	@Override
	public Yogaclub getYogaclubbyId(int i) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Yogaclub where id=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, i);
		Yogaclub yoga = (Yogaclub) query.uniqueResult();
		return yoga;
	}

	@Override
	public void updateYogaclub(Yogaclub yogaclub) {
		Session session = sessionFactory.getCurrentSession();
		session.update(yogaclub);
		System.out.println("存储会所成功");
	}

	@Override
	public void addYogaclub(Yogaclub yogaclub) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(yogaclub);
		System.out.println("添加会所成功会所成功" + session.save(yogaclub));

	}

	@Override
	public long getLockerCount(int yogaclubid) {
		session = sessionFactory.getCurrentSession();
		List list = null;

		String hql = "select count(*) from Locker as l  where  l.yogaClubId=?";
		Query query = session.createQuery(hql);

		query.setInteger(0, yogaclubid);
		return (long) query.uniqueResult();

	}

	@Override
	public void addLocker(Locker locker) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(locker);
	}

	@Override
	public List<Locker> getLockerbyYogaclub(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Locker as l  where  l.yogaClubId=? and l.state=0";
		Query query = session.createQuery(hql);
		query.setInteger(0, id);
		List<Locker> list = query.list();
		return list;

	}

	@Override
	public void updateLocker(int id, int status) {
		// TODO Auto-generated method stub
		session = this.sessionFactory.getCurrentSession();
		Locker locker = (Locker) session.get(Locker.class, id);
		if (status == 1) {
			// 前端的status是1表示已经使用了
			locker.setStatus(0);
		}
		if (status == 2) {
			// 表示未使用
			locker.setStatus(1);
		}
		if (status == 3) {
			// 表示损坏
			locker.setStatus(2);
		}
		locker.setModifiedTime(new Date());

		session.saveOrUpdate(locker);
	}

	@Override
	public void deleteLockerbyYogaclub(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Locker locker = (Locker) session.get(Locker.class, id);
		locker.setState(1);
		locker.setCreatedTime(new Date());
		session.saveOrUpdate(locker);

	}

	// 获取可用柜子的第一个
	@Override
	public int getPersonalLocker(String yogaclubname, int memberid) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		int id = getYogaclubIdbyName(yogaclubname);
		String sql = " from Locker as l where l.yogaClubId=? and l.status=1 and l.state=0 order by l.id  ";
		List<Locker> list = session.createQuery(sql).setInteger(0, id).list();

		int Lockerid = list.get(0).getId();
		return Lockerid;

	}

	@Override
	public void changeLockerStatus(int lockerid, boolean flag) {
		Session session = sessionFactory.getCurrentSession();
		Locker locker = (Locker) session.get(Locker.class, lockerid);

		// flag如果是true 说明 checkin 成功 需要将柜子的status 设成 0
		if (flag) {
			locker.setStatus(0);
			session.saveOrUpdate(locker);
		} else {
			locker.setStatus(1);
			session.saveOrUpdate(locker);

		}

	}

	@Override
	public int getYogaclubIdbyName(String yogaclubname) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "select y.id from Yogaclub as y where y.name=?";
		return (int) session.createQuery(hql).setString(0, yogaclubname).uniqueResult();

	}

}
