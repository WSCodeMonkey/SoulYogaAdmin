package com.web.soulyogaadmin.courseattendance.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.web.soulyogaadmin.courseattendance.dao.ICourseattendanceDao;
import com.web.soulyogaadmin.entity.Courseattendance;
import com.web.soulyogaadmin.entity.Coursereservation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml","classpath:spring-hibernate.xml"})
public class TestCourseattendance {

	@Autowired
	private ICourseattendanceDao iCourseattendanceDao;
	
	@Test
	public void addCourseattendance (){
		
	}
	
	@Test
	public void queryByMember (){
		List<Coursereservation>list=iCourseattendanceDao.queryByMember(1, new Date());
		System.out.println("******"+list);
		
	}
	
	@Test
	public void getCourseattendanceByCourseReservationId (){
		Courseattendance courseattendance=iCourseattendanceDao.getCourseattendanceByCourseReservationId(2);
		System.out.println("******"+courseattendance);
	}
	
	@Test
	public void queryAllCourseattendance (){
		List<Courseattendance> list=iCourseattendanceDao.queryAllCourseattendance(27);
		System.out.println("******"+list);
	}

}
