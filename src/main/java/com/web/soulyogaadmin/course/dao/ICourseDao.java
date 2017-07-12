package com.web.soulyogaadmin.course.dao;

import java.util.List;

import com.web.soulyogaadmin.course.vo.CourseConditionVO;
import com.web.soulyogaadmin.course.vo.CourseView;
import com.web.soulyogaadmin.entity.CourseEntry;
import com.web.soulyogaadmin.entity.Coursecategory;
import com.web.soulyogaadmin.entity.Yogaclub;


/**
 * Course Dao Interface
 * @author Shawn xiao
 * @version 
 */
public interface ICourseDao {

	public List<CourseEntry> getAllCourseList();

	public List<CourseEntry> getSearchedCourseList(CourseEntry courseCriteria);
	
	public List<Yogaclub> getAllYogaclubList();
	
	public List<Coursecategory> getAllCoursecategoryList();
	
	public void courseAdd(CourseEntry course);
	
	List<CourseView> QueryAllCourseList();
	
	List<CourseView> QueryAllCourseList2();
	
	List QueryAllCourseList3();
	
    CourseEntry getCourse(int id);
    
    void updateCourse(CourseEntry course);
    
    List<CourseEntry> getAllCourseListByCondition(CourseConditionVO courseCondition);
     
    Coursecategory findCoursecategoryNameById(int courseCategoryID);
    
    Yogaclub findYogaclubNameById(int yogaClubID);
    
    void delCourse(int courseId);
    
    
   
    
     
}
