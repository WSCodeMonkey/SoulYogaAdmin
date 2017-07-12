package com.web.soulyogaadmin.course.service;

import java.util.List;

import com.web.soulyogaadmin.course.vo.CourseConditionVO;
import com.web.soulyogaadmin.course.vo.CourseView;
import com.web.soulyogaadmin.entity.CourseEntry;
import com.web.soulyogaadmin.entity.Coursecategory;
import com.web.soulyogaadmin.entity.Yogaclub;


public interface ICourseService {
	
	public List getAllCourseList();
	
	public List getSearchedCourseList(CourseEntry courseCriteria);
	
	public List<Yogaclub> getAllYogaclubList();
	
	public List<Coursecategory> getAllCoursecategoryList();
	
    void courseAdd(CourseEntry course);
    
    List<CourseView> QueryAllCourseList();
    
    CourseEntry getCourse(int id);
    
    void updateCourse(CourseEntry course);
    
    List<CourseEntry> getAllCourseListByCondition(CourseConditionVO courseCondition);
    
    Coursecategory findCoursecategoryNameById(int courseCategoryID);
    
    Yogaclub findYogaclubNameById(int yogaClubID);
    
    public void delCourse(int courseId);
}
