package com.web.soulyogaadmin.courseattendance.service;

import java.util.Date;
import java.util.List;

import com.web.soulyogaadmin.courseattendance.vo.CourseattendanceView;
import com.web.soulyogaadmin.entity.Courseattendance;
import com.web.soulyogaadmin.entity.Coursereservation;

public interface ICourseattendanceService {

	/**
	 * 根据会员手机号查询当日的已预约课程表(用于checkIn查询)
	 * @param mobileNo
	 * @param currentDay
	 * @return
	 */
	List<CourseattendanceView> queryCheckInByMember(String mobileNo);
	
	/**
	 * 根据会员手机号查询当日的已预约课程表(用于checkOut查询)
	 * @param mobileNo
	 * @param currentDay
	 * @return
	 */
	List<CourseattendanceView> queryCheckOutByMember(String mobileNo);
	
	void addCheckIn(Courseattendance courseattendance);
	
	String checkEndTime(int courseSheduleId);
}
