package com.web.soulyogaadmin.courseattendance.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.soulyogaadmin.courseattendance.dao.ICourseattendanceDao;
import com.web.soulyogaadmin.entity.Courseattendance;
import com.web.soulyogaadmin.entity.Coursereservation;
import com.web.soulyogaadmin.entity.Courseschedule;

@Repository("iCourseattendanceDao")
@Transactional
public class CourseattendanceDaoImpl implements ICourseattendanceDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	private static String className = CourseattendanceDaoImpl.class.getName();
	private static Logger logger = Logger.getLogger(className);
	
	@Override
	public void addCourseattendance(Courseattendance courseattendance) {
		Session session=sessionFactory.getCurrentSession();
		session.save(courseattendance);
	}

	@Override
	public void updateCourseattendance(Courseattendance courseattendance) {
		Session session=sessionFactory.getCurrentSession();
		session.update(courseattendance);
	}

	@Override
	public void delCourseattendance(Integer courseattendanceId) {
		Session session=sessionFactory.getCurrentSession();
		Courseattendance courseattendance=queryBycourseattendanceId(courseattendanceId);
		courseattendance.setState(1);
		courseattendance.setModifiedTime(new Date());
		updateCourseattendance(courseattendance);
	}

	@Override
	public List<Courseattendance>  queryAllCourseattendance() {
		List<Courseattendance> list=new ArrayList<Courseattendance>();
		Session session=sessionFactory.getCurrentSession();
		String hql="from Courseattendance ca where ca.state=0  ";
		Query query=session.createQuery(hql);
		list=query.list();
		return list;
	}

	@Override
	public Courseattendance queryBycourseattendanceId(Integer courseattendanceId) {
		Session session=sessionFactory.getCurrentSession();
		Courseattendance courseattendance=(Courseattendance) session.get(Courseattendance.class, courseattendanceId);
		return courseattendance;
	}

	@Override
	public List<Coursereservation> queryByMember(int memberId, Date currentDay) {
		Session session=sessionFactory.getCurrentSession();
		String hql="from  Coursereservation cr where cr.state=0 and cr.memberId=? and cr.scheduleDate=? and cr.status=0 ";
		Query query=session.createQuery(hql);
		query.setInteger(0, memberId);
		query.setDate(1, currentDay);
		List<Coursereservation> list=new ArrayList<Coursereservation>();
		list=query.list();
		return list;
	}

	@Override
	public String checkInEndTimeByCourseSheduleId(int courseSheduleId) {
		Session session=sessionFactory.getCurrentSession();
		Courseschedule courseschedule=(Courseschedule) session.get(Courseschedule.class, courseSheduleId);
		String endTime=courseschedule.getCheckinEndTime();
		return endTime;
	}

	@Override
	public Courseattendance getCourseattendanceByCourseReservationId(int reservationId) {
		Session session=sessionFactory.getCurrentSession();
		String hql="from Courseattendance ca where ca.courseReservationId=? and ca.state=0 ";
		Query query=session.createQuery(hql);
		query.setInteger(0, reservationId);
		Courseattendance courseattendance=(Courseattendance) query.uniqueResult();
		return courseattendance;
	}

	
	
}
