package com.web.soulyogaadmin.course.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.soulyogaadmin.course.dao.ICourseDao;
import com.web.soulyogaadmin.course.vo.CourseConditionVO;
import com.web.soulyogaadmin.course.vo.CourseView;
import com.web.soulyogaadmin.entity.CourseEntry;
import com.web.soulyogaadmin.entity.Coursecategory;
import com.web.soulyogaadmin.entity.Member;
import com.web.soulyogaadmin.entity.Yogaclub;
import com.web.soulyogaadmin.util.UtilValidate;


/**
 * Course Dao Implemention
 * @author Shawn Xiao
 * @version 2017-06-15
 */
@Repository("courseDao")
@Transactional
public class CourseDaoImpl implements ICourseDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session session;

	private static String className = CourseDaoImpl.class.getName();

	private static Logger logger = Logger.getLogger(className);
	

	public List getAllCourseList() {

		String hql = null;
		session = this.sessionFactory.getCurrentSession();

		hql = "from CourseEntry m where m.state=0 ";

		Query query = session.createQuery(hql);
		
		List<CourseEntry> list = query.list();

		if (list.size() > 0) {
			return list;
		}
		return null;
	}


	@Override
	public List<CourseEntry> getSearchedCourseList(CourseEntry courseCriteria) {
		String hql = null;
		session = this.sessionFactory.getCurrentSession();
		
		hql = "from CourseEntry m where 1=1 ";
		
		if(UtilValidate.isNotEmpty(courseCriteria) && UtilValidate.isNotEmpty(courseCriteria.getCourseCategoryID())){
			hql += " and courseCategoryID = ?";
		}
			
		Query query = session.createQuery(hql);

		if(UtilValidate.isNotEmpty(courseCriteria) && UtilValidate.isNotEmpty(courseCriteria.getCourseCategoryID())){
			query.setParameter(0, courseCriteria.getCourseCategoryID());
		}

		List<CourseEntry> list = query.list();

		if (list.size() > 0) {
			return list;
		}
		return null;
	}


	@Override
	public List<Yogaclub> getAllYogaclubList() {
		List<Yogaclub> list=new ArrayList<Yogaclub>();
		session = this.sessionFactory.getCurrentSession();
		String hql="from Yogaclub y where y.state=0 ";
		Query query = session.createQuery(hql);
		list=query.list();
		return list;
	}


	@Override
	public List<Coursecategory> getAllCoursecategoryList() {
		List<Coursecategory> list=new ArrayList<Coursecategory>();
		session=this.sessionFactory.getCurrentSession();
		String hql="from Coursecategory c where c.state=0 ";
		Query query=session.createQuery(hql);
		list=query.list();
		return list;
	}


	@Override
	public void courseAdd(CourseEntry course) {
		session =this.sessionFactory.getCurrentSession();
		session.save(course);
	}


	@Override
	public List<CourseView> QueryAllCourseList() {
		session =this.sessionFactory.getCurrentSession();
		String sql="select ce.*,cc.name coursecategoryName,y.name yogaclubName from course ce left join Coursecategory cc on ce.courseCategoryID=cc.id left join Yogaclub y on ce.yogaClubID=y.id where ce.state=0 ";
		Query query=session.createSQLQuery(sql).addScalar("coursecategoryName",StandardBasicTypes.STRING).addScalar("yogaclubName",StandardBasicTypes.STRING).addScalar("name",StandardBasicTypes.STRING)
				.addScalar("id",StandardBasicTypes.INTEGER).addScalar("introduction",StandardBasicTypes.STRING).addScalar("introduction",StandardBasicTypes.STRING).addScalar("point",StandardBasicTypes.INTEGER)
				.addScalar("createdTime",StandardBasicTypes.DATE).addScalar("modifiedTime",StandardBasicTypes.DATE).addScalar("state",StandardBasicTypes.INTEGER).addScalar("courseCategoryID",StandardBasicTypes.INTEGER)
				.addScalar("yogaClubID",StandardBasicTypes.INTEGER)
				.setResultTransformer(Transformers.aliasToBean(CourseView.class));
		List<CourseView> list=query.list();
		return list;
	}
	//e.iD, ce.name, ce.courseCategoryID,ce.yogaClubID,ce.introduction,ce.point,"+
	//"ce.createdTime,ce.modifiedTime

	@Override
	public List<CourseView> QueryAllCourseList2() {
		session =this.sessionFactory.getCurrentSession();
		String sql="select ce.name as name,cc.name as coursecategoryName,y.name as yogaclubName from CourseEntry ce ,Coursecategory cc ,Yogaclub y where ce.courseCategoryID=cc.id,ce.yogaClubID=y.id  ";
		Query query=session.createQuery(sql);
		List<CourseView> list=query.setResultTransformer(Transformers.aliasToBean(CourseView.class)).list();
		return list;
	}


	@Override
	public List QueryAllCourseList3() {
		String hql=" from CourseEntry ce left join Coursecategory cc on ce.courseCategoryID=cc.id left join Yogaclub y on ce.yogaClubID=y.id ";
		Query query=session.createQuery(hql);
		List list=query.list();
		return list;
	}


	@Override
	public CourseEntry getCourse(int id) {
		session =this.sessionFactory.getCurrentSession();
		CourseEntry courseEntry=(CourseEntry) session.get(CourseEntry.class,id);
		return courseEntry;
	}


	@Override
	public void updateCourse(CourseEntry course) {
		session =this.sessionFactory.getCurrentSession();
		session.update(course);
		
	}


	@Override
	public List<CourseEntry> getAllCourseListByCondition(CourseConditionVO courseCondition) {
		session =this.sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(CourseEntry.class);
		criteria.add(Restrictions.eq("state",0));
		if(courseCondition.getName() !=null){
			criteria.add(Restrictions.like("name", "%"+courseCondition.getName()+"%"));
		}
		if(courseCondition.getCourseCategoryID() !=null){
			criteria.add(Restrictions.eq("courseCategoryID", courseCondition.getCourseCategoryID()));
		}
		if(courseCondition.getYogaClubID() !=null){
			criteria.add(Restrictions.eq("yogaClubID", courseCondition.getYogaClubID()));
		}
		List<CourseEntry> list=criteria.list();
		return list;
	}


	@Override
	public Coursecategory findCoursecategoryNameById(int courseCategoryID) {
		session =this.sessionFactory.getCurrentSession();
		Coursecategory coursecategory=(Coursecategory) session.get(Coursecategory.class,courseCategoryID);
		return coursecategory;
	}


	@Override
	public Yogaclub findYogaclubNameById(int yogaClubID) {
		session =this.sessionFactory.getCurrentSession();
		Yogaclub yogaclub=(Yogaclub) session.get(Yogaclub.class,yogaClubID);
		return yogaclub;
	}


	@Override
	public void delCourse(int courseId) {
		CourseEntry course=this.getCourse(courseId);
		course.setState(1);
		course.setModifiedTime(new Date());
		this.updateCourse(course);
	}



	
	
}
