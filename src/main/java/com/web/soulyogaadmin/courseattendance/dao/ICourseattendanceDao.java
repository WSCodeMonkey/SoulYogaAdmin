package com.web.soulyogaadmin.courseattendance.dao;

import java.util.Date;
import java.util.List;

import com.web.soulyogaadmin.entity.Courseattendance;
import com.web.soulyogaadmin.entity.Coursereservation;

public interface ICourseattendanceDao {

	void addCourseattendance(Courseattendance courseattendance); 
	
	void updateCourseattendance(Courseattendance courseattendance);
	
	void delCourseattendance(Integer courseattendanceId);
	 
	List<Courseattendance> queryAllCourseattendance();
	
	Courseattendance queryBycourseattendanceId(Integer courseattendanceId);
	
	List<Coursereservation> queryByMember(int memberId,Date currentDay);
	
	String checkInEndTimeByCourseSheduleId(int courseSheduleId);
	
	Courseattendance getCourseattendanceByCourseReservationId(int reservationId);
	

}
