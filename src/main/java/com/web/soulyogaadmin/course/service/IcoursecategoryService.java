package com.web.soulyogaadmin.course.service;



import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import com.web.soulyogaadmin.entity.Coursecategory;

public interface IcoursecategoryService {
	public List<Coursecategory> getAllCoursecategory();
	public List<Coursecategory> getAllCoursecategoryById(int id);
	public List<Coursecategory> getAllCoursecategoryByName(String name);
	public List<Coursecategory> seperateCoursecategoryList(int page, int pageSize);
	public Coursecategory getCoursecategoryById(int id);
	public List<Coursecategory> getSearchedCoursecategory(int state);

	public void add(Coursecategory coursecategory);

	public void delete(Coursecategory coursecategory);
	public void changeCoursecategoryModifiedTime(int id,Date modifiedTime);
	public void modify(Coursecategory coursecategory);
}
