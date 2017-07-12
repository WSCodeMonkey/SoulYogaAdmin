package com.web.soulyogaadmin.course.dao.impl;

import java.util.ArrayList;
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

import com.sun.org.apache.bcel.internal.generic.TargetLostException;
import com.web.soulyogaadmin.course.dao.ICoursescheduleDao;
import com.web.soulyogaadmin.entity.Coursecategory;
import com.web.soulyogaadmin.entity.Courseschedule;
@Repository("coursescheduleDao")
//@Transactional
public class CoursescheduleDaoImpl implements ICoursescheduleDao{
	@Autowired
	private SessionFactory sessionFactory;

	private Session session;

	private static String className = CourseDaoImpl.class.getName();

	private static Logger logger = Logger.getLogger(className);
	@Override
	public List<Courseschedule> getAllCourseschedules() {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		String hql = "from Courseschedule where state=:state";
		Query query = session.createQuery(hql).setInteger("state", 0);
		List<Courseschedule> lists = query.list();
		tran.commit();
		session.close();
		logger.debug("use the method named :getAllCourseschedules()");
		if (lists.size() > 0) {
			return lists;
		}
		return null;
	}

	@Override
	public List<Courseschedule> getCourseschedulesByClassroomId(int classroomId) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		String hql = "from Courseschedule where classroomId=:classroomId and state=:state";		
		List<Courseschedule> lists = session.createQuery(hql).setInteger("classroomId", classroomId).setInteger("state", 0).list();
		tran.commit();
		session.close();
		logger.debug("use the method named :getCourseschedulesByClassroomId(int classroomId)");
		if (lists.size() > 0) {
			return lists;
		}
		return null;
	}

	@Override
	public List<Courseschedule> getCourseschedulesByCourseId(int courseId) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		String hql = "from Courseschedule where courseId=:courseId and state=:state";		
		List<Courseschedule> lists = session.createQuery(hql).setInteger("courseId", courseId).setInteger("state", 0).list();
		tran.commit();
		session.close();
		logger.debug("use the method named :getCourseschedulesByCourseId(int courseId)");
		if (lists.size() > 0) {
			return lists;
		}
		return null;
	}

	@Override
	public List<Courseschedule> getCourseschedulesByCourseState(int courseState) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		String hql = "from Courseschedule where State=:courseState and state =:state";		
		List<Courseschedule> lists = session.createQuery(hql).setInteger("courseState", courseState).setInteger("state", 0).list();
		tran.commit();
		session.close();
		logger.debug("use the method named :getCourseschedulesByCourseState(int courseState) ");

		if (lists.size() > 0) {
			return lists;
		}
		return null;
	}

	@Override
	public void deleteCoursescheduleById(int id) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession(); 
		Transaction transaction = session.beginTransaction();
		//session.delete(getCoursescheduleById(id));
		String hql = "update Courseschedule set state =:state ,modifiedTime=:modifiedTime where id=:id";		
		int deleteNumber= session
							.createQuery(hql)
							.setInteger("state", 1)
							.setInteger("id", id)
							.setTimestamp("modifiedTime",new Date())
							.executeUpdate();
		if(deleteNumber>0){
			logger.debug("use the method named :deleteCourseschedule(Courseschedule courseschedule) ,delete success！");
		}
		transaction.commit();
		session.close();
		

	}

	@Override
	public void addCourseschedule(Courseschedule courseschedule) {
		// 在添加之前要判断关联表是否存在？
		session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(courseschedule);
		transaction.commit();
		session.close();
		logger.debug("use the method named :addCourseschedule(Courseschedule courseschedule)");

	}

	@Override
	public void modifyCourseschedule(Courseschedule courseschedule) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql="update Courseschedule set teacherId=:teacherId"
				+", courseId=:courseId"
				+", classroomId=:classroomId"
				+", scheduleDate=:scheduleDate"
				+", startTime=:startTime"
				+", endTime=:endTime"
				+", reservationStartTime=:reservationStartTime"
				+", reservationEndTime=:reservationEndTime"
				+", cancellationStartTime=:cancellationStartTime"
				+", cancellationEndTime=:cancellationEndTime"
				+", checkinEndTime=:checkinEndTime"
				+", modifiedTime=:modifiedTime"
				+" where id=:id";
		
		int rtn=session.createQuery(hql)
		       .setInteger("teacherId", courseschedule.getTeacherId())
		       .setInteger("courseId", courseschedule.getCourseId())
		       .setInteger("classroomId", courseschedule.getClassroomId())
		       .setDate("scheduleDate", courseschedule.getScheduleDate())
		       .setString("startTime", courseschedule.getStartTime())
		       .setString("endTime",courseschedule.getEndTime())
		       .setTimestamp("reservationStartTime", courseschedule.getReservationStartTime())
		       .setTimestamp("reservationEndTime", courseschedule.getReservationEndTime())
		       .setTimestamp("cancellationStartTime", courseschedule.getCancellationStartTime())
		       .setTimestamp("cancellationEndTime", courseschedule.getCancellationEndTime())
		       .setString("checkinEndTime", courseschedule.getCheckinEndTime())
		       .setTimestamp("modifiedTime", new Date())
		       .setInteger("id", courseschedule.getId())
		       .executeUpdate();
		//session.saveOrUpdate(Courseschedule);
		if(rtn>0){
			logger.debug("use the method named :modifyCourseschedule(Courseschedule Courseschedule)");
		}
		transaction.commit();
		session.close();
		//logger.debug("use the method named :modifyCourseschedule(Courseschedule Courseschedule)");

	}

	@Override
	public Courseschedule getCoursescheduleById(int id) {
		
		session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		//Courseschedule returnObj = (Courseschedule) session.get(Courseschedule.class, new Integer(id+""));
		//不能用上面方法，因为state状态为0的才能查，否则把已经删除的也查出来，用如下方法 
		String hql = "from Courseschedule  where state =:state and id=:id";		
		List<Courseschedule> lists = session.createQuery(hql).setInteger("id", id).setInteger("state", 0).list();
		transaction.commit();
		session.close();
		logger.debug("use the method named :getCoursescheduleById(int id)");
		if (lists.size() > 0) {
			return lists.get(0);
		}
		return null;
		
	}

	@Override
	public List<Courseschedule> getCourseschedulesByTeacherId(int teacherId) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		String hql = "from Courseschedule where teacherId=:teacherId and state=:state";		
		@SuppressWarnings("unchecked")
		List<Courseschedule> lists = session.createQuery(hql).setInteger("teacherId", teacherId).setInteger("state", 0).list();
		tran.commit();
		session.close();
		logger.debug("use the method named :getCourseschedulesByTeacherId(int teacherId)");
		if (lists.size() > 0) {
			return lists;
		}
		return null;
	}

	@Override
	public List<Integer>  getAllTeacherId() {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		String hql = "select id from Teacher where  state=:state";		
		@SuppressWarnings("unchecked")
		List<Integer> lists = session.createQuery(hql).setInteger("state", 0).list();
		tran.commit();
		session.close();
		logger.debug("use the method named :getAllTeacherId()");
		if (lists.size() > 0) {
			return lists;
		}
		return null;
	}

	@Override
	//得到任一都是老师对应的课程种类Id
	public List<Integer> getAllcourseCategoryIDsFormTeacher(int teacherId) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		String hql = "select courseCategoryIds from Teacher where  state=:state and id=:teacherId";		
		@SuppressWarnings("unchecked")
		List<String> lists = session.createQuery(hql).setInteger("state", 0).setInteger("teacherId",teacherId).list();
		tran.commit();
		session.close();
		List<Integer> list =new ArrayList<Integer>();
		logger.debug("use the method named :getAllcourseCategoryIDsFormTeacher(int teacherId)");
		if (lists.size() > 0) {
			String[] target= lists.get(0).split(",");
			for(int i=0;i<target.length;i++){
				list.add(Integer.parseInt(target[i]));
			}
			
		}
		return list;
	}

	@Override
	//得到老师对应的课程Id
	public List<Integer> getAllCourseIdOfTeacherFromCourse(List<Integer>  courseCategoryIDs) {
		// TODO Auto-generated method stub
				session = sessionFactory.openSession();
				Transaction tran = session.beginTransaction();
				String hql = "select iD from CourseEntry where  state=:state and id in(:list)";		
				@SuppressWarnings("unchecked")
				List<Integer> lists = session.createQuery(hql).setInteger("state", 0).setParameterList("list", courseCategoryIDs).list();
				tran.commit();
				session.close();
				logger.debug("use the method named :getAllcourseCategoryIDsFormTeacher(int teacherId)");
				if (lists.size() > 0) {
					return lists;
				}
				return null;
	}

	@Override
	//得到任一课程对应的教室ID
	public List<Integer> getAllClassroomIdOfCourse(int courseId) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		String hql = "select id from Classroom where  state=:classroomState and yogaCludId=( select yogaClubID from CourseEntry where id=(:courseId) and state=:courseState )";		
		@SuppressWarnings("unchecked")
		List<Integer> lists = session.createQuery(hql).setInteger("classroomState", 0).setInteger("courseId", courseId).setInteger("courseState", 0).list();
		tran.commit();
		session.close();
		logger.debug("use the method named :getAllClassroomIdOfCourse(int courseId)");
		if (lists.size() > 0) {
			return lists;
		}
		return null;
	}

	@Override
	public String getTeacherNameByTeacherId(int teacherId) {
		session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		String hql = "select name from Employee where  id=( select employeeId from Teacher where id=(:teacherId)  )";		
		@SuppressWarnings("unchecked")
		String teacherName = (String) session.createQuery(hql).setInteger("teacherId", teacherId).list().get(0);
		tran.commit();
		session.close();
		logger.debug("use the method named :getAllClassroomIdOfCourse(int courseId)");
		
		return teacherName;
	}

	@Override
	public String getCourseNameByCourseId(int courseId) {
		session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		String hql = "select name from Course where  id=(:courseId) ";		
		@SuppressWarnings("unchecked")		
		List<String> courseNames = session.createSQLQuery(hql).setInteger("courseId", courseId).list();
		String courseName =courseNames.get(0);
		System.out.println("=================");
		System.out.println(courseNames.toString());
		System.out.println(courseName);
		System.out.println("=================");
		tran.commit();
		session.close();
		logger.debug("use the method named :getOneCoursesecheduleById(int courseId)");
		
		return courseName;
	}

	@Override
	public String getClassroomNameByClassroomId(int classroomId) {
		session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		String hql = "select name from Yogaclub where  id=( select yogaCludId from Classroom where id=(:classroomId) )";		
		@SuppressWarnings("unchecked")
		String classroomName = (String) session.createQuery(hql).setInteger("classroomId", classroomId).list().get(0);
		tran.commit();
		session.close();
		logger.debug("use the method named :getAllClassroomIdOfCourse(int courseId)");
		
		return classroomName;
	}

}
