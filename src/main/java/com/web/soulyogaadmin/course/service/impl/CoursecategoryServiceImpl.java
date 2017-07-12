package com.web.soulyogaadmin.course.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.soulyogaadmin.course.dao.ICoursecategoryDao;
import com.web.soulyogaadmin.course.service.IcoursecategoryService;
import com.web.soulyogaadmin.entity.Coursecategory;
@Service("coursecategoryService")
@Transactional
public class CoursecategoryServiceImpl implements IcoursecategoryService {
@Autowired
	private ICoursecategoryDao coursecategoryDao;
	@Override
	public List<Coursecategory> getAllCoursecategory() {
		// TODO Auto-generated method stub
		return coursecategoryDao.getAllCoursecategory();
	}

	@Override
	public List<Coursecategory> seperateCoursecategoryList(int page, int pageSize) {
		// TODO Auto-generated method stub
		return coursecategoryDao.seperateCoursecategoryList(page, pageSize);
	}

	@Override
	public List<Coursecategory> getSearchedCoursecategory(int state) {
		// TODO Auto-generated method stub
		return coursecategoryDao.getSearchedCoursecategory(state);
	}

	@Override
	public void add(Coursecategory coursecategory) {
		// TODO Auto-generated method stub
		coursecategoryDao.add(coursecategory);
		
	}

	@Override
	public void delete(Coursecategory coursecategory) {
		// TODO Auto-generated method stub
		coursecategoryDao.delete(coursecategory);
	}

	@Override
	public void modify(Coursecategory coursecategory) {
		// TODO Auto-generated method stub
		coursecategoryDao.modify(coursecategory);
	}

	@Override
	public List<Coursecategory> getAllCoursecategoryById(int id) {
		// TODO Auto-generated method stub
		return coursecategoryDao.getAllCoursecategoryById(id);
	}

	@Override
	public List<Coursecategory> getAllCoursecategoryByName(String name) {
		// TODO Auto-generated method stub
		return coursecategoryDao.getAllCoursecategoryByName(name);
	}

	@Override
	public Coursecategory getCoursecategoryById(int id) {
		// TODO Auto-generated method stub
		return coursecategoryDao.getCoursecategoryById(id);
	}

	@Override
	public void changeCoursecategoryModifiedTime(int id,Date modifiedTime) {
		// TODO Auto-generated method stub
		coursecategoryDao.changeCoursecategoryModifiedTime(id, modifiedTime);
	}

}
