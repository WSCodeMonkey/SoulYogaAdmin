package com.web.soulyogaadmin.course.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.web.soulyogaadmin.course.dao.ICourseDao;
import com.web.soulyogaadmin.course.service.ICourseService;
import com.web.soulyogaadmin.course.vo.CourseConditionVO;
import com.web.soulyogaadmin.course.vo.CourseView;
import com.web.soulyogaadmin.entity.CourseEntry;
import com.web.soulyogaadmin.entity.Coursecategory;
import com.web.soulyogaadmin.entity.Yogaclub;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml","classpath:spring-hibernate.xml"})
public class TestCourse {

	@Autowired
	private ICourseDao courseDao;
	
	@Autowired
	private ICourseService courseService;
	
	@Test
	public void getAllYogaclubList() throws Exception {

		List<Yogaclub> list=courseService.getAllCourseList();
		System.out.println(list);
	}	
	@Test
	public void getAllCoursecategoryList() throws Exception {

		List<Coursecategory> list=courseDao.getAllCoursecategoryList();
		System.out.println(list);
	}
	@Test
	public void couserAdd(){
		CourseEntry course=new CourseEntry();
		course.setName("java");
		course.setCourseCategoryID(1);
		course.setYogaClubID(1);
		course.setIntroduction("for java study");
		course.setCreatedTime(new Date());
		course.setState(0);
		courseService.courseAdd(course);
		
	}
	@Test
	public void QueryAllCourseList(){
		
	List<CourseView> list=courseDao.QueryAllCourseList();
	System.out.println(list);	
	for(CourseView c:list){
		System.out.println("_--"+c.getName()+"--"+c.getCoursecategoryName()+"--"+c.getYogaclubName());
	}
	}
	
	@Test
	public void QueryAllCourseList2(){
		
	List<CourseView> list=courseDao.QueryAllCourseList2();
	System.out.println(list);	
	for(CourseView c:list){
		System.out.println("_--"+c.getName()+"--"+c.getCoursecategoryName()+"--"+c.getYogaclubName());
	}
	}
	
	@Test
	public void QueryAllCourseList3(){
		
	List list=courseDao.QueryAllCourseList3();
	System.out.println(list);	
	for(Object c:list){
		
	}
	}
	
	@Test
	public void getAllCourseListByCondition(){
		CourseConditionVO courseCondition=new CourseConditionVO();
		courseCondition.setName("java");
		courseCondition.setCourseCategoryID(2);
		courseCondition.setYogaClubID(3);
		List<CourseEntry> list=courseDao.getAllCourseListByCondition(courseCondition);
		System.out.println(list);

	
	}
	@Test
	public void findYogaclubNameById(){
		Yogaclub y=courseDao.findYogaclubNameById(2);
		System.out.println("================="+y.getName());

	
	}
	
}
