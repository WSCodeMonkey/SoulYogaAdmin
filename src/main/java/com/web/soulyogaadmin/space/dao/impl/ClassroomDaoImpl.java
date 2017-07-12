package com.web.soulyogaadmin.space.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.soulyogaadmin.course.dao.ICourseDao;
import com.web.soulyogaadmin.entity.Classroom;
import com.web.soulyogaadmin.entity.CourseEntry;
import com.web.soulyogaadmin.entity.Locker;
import com.web.soulyogaadmin.entity.Yogaclub;
import com.web.soulyogaadmin.entity.Yogacushion;
import com.web.soulyogaadmin.space.dao.IClassroomDao;
import com.web.soulyogaadmin.util.UtilValidate;
import com.web.soulyogaadmin.vo.ClassroomYogacushionvo;
import com.web.soulyogaadmin.vo.Yogacushionvo;

/**
 * Course Dao Implemention
 * 
 * @author Shawn Xiao
 * @version 2017-06-15
 */
@Repository("classroomDao")
@Transactional
public class ClassroomDaoImpl implements IClassroomDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session session;

	private static String className = ClassroomDaoImpl.class.getName();

	private static Logger logger = Logger.getLogger(className);

	public List<ClassroomYogacushionvo> getClassroombyYogaclub(int i) {

		String sql = null;
		session = this.sessionFactory.getCurrentSession();

		sql = "SELECT yogaclub.Name AS yogaclubName,classroom.ClassroomNo AS classroomNo,COUNT(yogacushion.YogaCushionNo) AS YogaCushionCount FROM classroom LEFT JOIN yogacushion ON classroom.id = yogacushion.ClassroomID INNER JOIN yogaclub ON classroom.YogaCludID = yogaclub.ID where classroom.State=0 and YogaCludID=?  GROUP BY classroom.ID ORDER BY yogaclub.Name , classroom.ClassroomNo";

		Query query = session.createSQLQuery(sql).addScalar("yogaclubName",StandardBasicTypes.STRING).addScalar("classroomNo",StandardBasicTypes.STRING).addScalar("YogaCushionCount",StandardBasicTypes.INTEGER)											
				.setResultTransformer(Transformers.aliasToBean(ClassroomYogacushionvo.class));
		query.setInteger(0, i);
		List<ClassroomYogacushionvo> list = query.list();
		System.out.println(list.size());
		if (list.size() > 0) {
			return list;
		} else {
			return null;

		}
	}

	@Override
	public List<ClassroomYogacushionvo> getAllClassroom() {
		// TODO Auto-generated method stub

		String sql = null;
		session = this.sessionFactory.getCurrentSession();

		sql = "SELECT yogaclub.Name as yogaclubName,classroom.ClassroomNo as classroomNo,COUNT(yogacushion.YogaCushionNo) as YogaCushionCount FROM classroom LEFT JOIN yogacushion ON classroom.id = yogacushion.ClassroomID INNER JOIN yogaclub ON classroom.YogaCludID = yogaclub.ID  where classroom.State=0 GROUP BY classroom.ID ORDER BY yogaclub.Name , classroom.ClassroomNo";

		Query query = session.createSQLQuery(sql).addScalar("yogaclubName",StandardBasicTypes.STRING).addScalar("classroomNo",StandardBasicTypes.STRING).addScalar("YogaCushionCount",StandardBasicTypes.INTEGER)											
				.setResultTransformer(Transformers.aliasToBean(ClassroomYogacushionvo.class));
		List<ClassroomYogacushionvo> list = query.list();
		return list;

	}

	@Override
	public List<Yogacushionvo> getYogacushion() {
		// TODO Auto-generated method stub
		String sql = null;
		session = this.sessionFactory.getCurrentSession();

		sql = "select  ClassroomID as , count(*) as yogachusionNo from yogacushion group by ClassroomID";

		Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Yogacushionvo.class));
		List<Yogacushionvo> list = query.list();

		return list;
	}

	@Override
	public int addClassroom(Classroom classroom) {
		 session = sessionFactory.getCurrentSession();
         session.save(classroom);
         return classroom.getId();
        
       
	}


@Override
public long  getClassroomCount(int yogaclubId) {
	 session = sessionFactory.getCurrentSession();
	  List list = null;
  
	String hql="select count(*) from Classroom as c  where  c.yogaCludId=?";
	Query query = session.createQuery(hql);
	
	System.out.println("查询内部ID是"+yogaclubId);
    query.setInteger(0, yogaclubId);   
     return (long)query.uniqueResult();
}

@Override
public void addYogacushion(Yogacushion yogacushion) {
	// TODO Auto-generated method stub
	 session = sessionFactory.getCurrentSession();
	 session.save(yogacushion);

}

@Override
public int getClassroomidbyNo(String classroomNo) {
	// TODO Auto-generated method stub
	 session = sessionFactory.getCurrentSession();
	 String hql="select c.id from Classroom as c where c.classroomNo=?";
		Query query = session.createQuery(hql);
		query.setString(0,classroomNo);
		int id=(int) query.uniqueResult();
        return id;
}

@Override
public long getYogacushionCount(int classroomId) {
	// TODO Auto-generated method stub
	 session = sessionFactory.getCurrentSession();
	  List list = null;
 
	String hql="select count(*) from Yogacushion as y  where  y.classroomId=? and y.state=0";
	Query query = session.createQuery(hql);
	
   query.setInteger(0, classroomId);   
    return (long)query.uniqueResult();

}

@Override
public Classroom getClassroombyId(int id) {
	// TODO Auto-generated method stub
	 session = sessionFactory.getCurrentSession();
	    Classroom cl= (Classroom) session.get(Classroom.class, id);
	    return cl;
}

@Override
public int deleteYogacushionbyClassroom(int id) {
	// TODO Auto-generated method stub
	 session = sessionFactory.getCurrentSession();

	String hql="delete Yogacushion where classroomId=?";
	int query=session.createQuery(hql).setInteger(0, id).executeUpdate();
	return query;
}

@Override
public void deleteClassroombyId(int id) {
	// TODO Auto-generated method stub
	session = this.sessionFactory.getCurrentSession();
Classroom classroom=getClassroombyId(id);
      classroom.setState(1);
	   session.merge(classroom);
}
public void updateClassroom(int id){
	session = this.sessionFactory.getCurrentSession();
Classroom classroom=getClassroombyId(id);
      classroom.setModifiedTime(new Date());
	   session.merge(classroom);
}



}
