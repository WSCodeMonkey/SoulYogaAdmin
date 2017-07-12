package com.web.soulyogaadmin.course.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.soulyogaadmin.course.dao.ICourseDao;
import com.web.soulyogaadmin.course.service.ICourseService;
import com.web.soulyogaadmin.course.vo.CourseConditionVO;
import com.web.soulyogaadmin.course.vo.CourseView;
import com.web.soulyogaadmin.entity.CourseEntry;
import com.web.soulyogaadmin.entity.Coursecategory;
import com.web.soulyogaadmin.entity.Yogaclub;


@Service("courseService")
@Transactional
public class CourseServiceImpl implements ICourseService{
	
	@Autowired
	private ICourseDao courseDao;
	
	@Override
	public List getAllCourseList(){
		List<CourseEntry> allCourseList = null;
		
		allCourseList = new ArrayList<CourseEntry>();
		
		allCourseList = courseDao.getAllCourseList();
		
		return allCourseList;
	}


	@Override
	public List getSearchedCourseList(CourseEntry courseCriteria) {
		List<CourseEntry> searchedCourseList = courseDao.getSearchedCourseList(courseCriteria);
		return searchedCourseList;
	}


	@Override
	public List<Yogaclub> getAllYogaclubList() {
		
		return courseDao.getAllYogaclubList();
	}


	@Override
	public List<Coursecategory> getAllCoursecategoryList() {
		return courseDao.getAllCoursecategoryList();
	}


	@Override
	public void courseAdd(CourseEntry course) {
		courseDao.courseAdd(course);
		
	}


	@Override
	public List<CourseView> QueryAllCourseList() {
		
		return courseDao.QueryAllCourseList();
	}


	@Override
	public CourseEntry getCourse(int id) {
		
		
		
		return courseDao.getCourse(id);
	}


	@Override
	public void updateCourse(CourseEntry course) {
		courseDao.updateCourse(course);
		
	}


	@Override
	public List<CourseEntry> getAllCourseListByCondition(CourseConditionVO courseCondition) {
		
		return courseDao.getAllCourseListByCondition(courseCondition);
	}


	@Override
	public Coursecategory findCoursecategoryNameById(int courseCategoryID) {
		
		return courseDao.findCoursecategoryNameById(courseCategoryID);
	}


	@Override
	public Yogaclub findYogaclubNameById(int yogaClubID) {
		return courseDao.findYogaclubNameById(yogaClubID);
	}


	@Override
	public void delCourse(int courseId) {
		courseDao.delCourse(courseId);
		
	}

	
}
