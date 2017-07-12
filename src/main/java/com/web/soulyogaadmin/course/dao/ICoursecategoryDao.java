package com.web.soulyogaadmin.course.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import com.web.soulyogaadmin.entity.Coursecategory;

public interface ICoursecategoryDao {
	public List<Coursecategory> getAllCoursecategory();
	public List<Coursecategory> getAllCoursecategoryById(int id);
	public List<Coursecategory> getAllCoursecategoryByName(String name);
	public List<Coursecategory> seperateCoursecategoryList(int page, int pageSize);
	public void changeCoursecategoryModifiedTime(int id,Date modifiedTime);
	public List<Coursecategory> getSearchedCoursecategory(int state);

	public void add(Coursecategory coursecategory);

	public void delete(Coursecategory coursecategory);
	public Coursecategory getCoursecategoryById(int id);
	public void modify(Coursecategory coursecategory);
}
