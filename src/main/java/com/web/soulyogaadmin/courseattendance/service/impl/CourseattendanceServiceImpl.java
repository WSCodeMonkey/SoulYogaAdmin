package com.web.soulyogaadmin.courseattendance.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.soulyogaadmin.courseattendance.dao.ICourseattendanceDao;
import com.web.soulyogaadmin.courseattendance.service.ICourseattendanceService;
import com.web.soulyogaadmin.courseattendance.vo.CourseattendanceView;
import com.web.soulyogaadmin.entity.Courseattendance;
import com.web.soulyogaadmin.entity.Coursereservation;
import com.web.soulyogaadmin.entity.Member;
import com.web.soulyogaadmin.member.dao.IMemberDao;
import com.web.soulyogaadmin.space.dao.IYogaclubDao;

@Service("iCourseattendanceService")
public class CourseattendanceServiceImpl implements ICourseattendanceService {
	
	@Autowired
	private ICourseattendanceDao iCourseattendanceDao;
	@Autowired
	private IMemberDao iMemberDao;
    @Autowired
	private IYogaclubDao yogaclubDao;
	
	@Override
	public List<CourseattendanceView> queryCheckInByMember(String mobileNo)  {
		 List<CourseattendanceView> listView=new  ArrayList<CourseattendanceView> ();
		Member member=iMemberDao.queryByMobileNo(mobileNo);
		List<Coursereservation> list=iCourseattendanceDao.queryByMember(member.getId(), new Date());
		CourseattendanceView cv;
		for(Coursereservation c:list){
			
			cv=new CourseattendanceView();
			String checkInendTime=checkEndTime(c.getCourseScheduleId());
			cv.setCheckInendTime(checkInendTime);
			try {
				PropertyUtils.copyProperties(cv, c);
			} catch (Exception e) {
				e.printStackTrace();
			}
			listView.add(cv);
		}
		return listView;
	}

	@Override
	public void addCheckIn(Courseattendance courseattendance) {
		
	}

	@Override
	public String checkEndTime(int courseSheduleId) {
		
		return iCourseattendanceDao.checkInEndTimeByCourseSheduleId(courseSheduleId);
	}

	@Override
	public List<CourseattendanceView> queryCheckOutByMember(String mobileNo) {
		return null;
	}

	

}
