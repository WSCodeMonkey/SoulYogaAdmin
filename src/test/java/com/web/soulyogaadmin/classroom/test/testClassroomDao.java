package com.web.soulyogaadmin.classroom.test;

import java.util.List;

import org.hibernate.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.web.soulyogaadmin.entity.Classroom;
import com.web.soulyogaadmin.entity.Yogaclub;
import com.web.soulyogaadmin.space.dao.IClassroomDao;
import com.web.soulyogaadmin.space.dao.IYogaclubDao;
import com.web.soulyogaadmin.space.dao.impl.ClassroomDaoImpl;
import com.web.soulyogaadmin.space.service.ISpaceService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-hibernate.xml" })
public class testClassroomDao {
	@Autowired
	IClassroomDao classroomDao;
	@Autowired
	ISpaceService spaceService;
/*
	@Test
	public void getclassroom() {
		System.out.println(classroomDao.getClassroombyYogaclub(1).size());

	}

	@Test
	public void getclassroombyYogaclub() {
		List<Classroom> classroom = SpaceService.getClassroombyYogaclub(1);

	}*/
/*	@Test
	public void getAllClassroom(){
	System.out.println(classroomDao.getAllClassroom().get(0).getYogaclubName());
		
	}
	@Test
	public void getAllClassroomService(){
		System.out.println(spaceService.getAllClassroom());
			
		}
	@Test
	public void getAllClassroombyyogaclub(){
	System.out.println(classroomDao.getClassroombyYogaclub(1).get(0).getYogaclubName());
		
	}
	@Test
	public void getAllClassroombyyogaclubService(){
	System.out.println(spaceService.getClassroombyYogaclub(1).get(0).getYogaclubName());
		
	}
	@Test
	public void addcushion(){
		System.out.println(classroomDao.addyogacushion(1,1).size());
		
	}*/

}
