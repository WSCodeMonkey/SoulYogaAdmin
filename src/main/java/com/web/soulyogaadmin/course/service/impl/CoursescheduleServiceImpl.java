package com.web.soulyogaadmin.course.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.web.soulyogaadmin.course.dao.ICoursescheduleDao;
import com.web.soulyogaadmin.course.service.ICoursescheduleService;
import com.web.soulyogaadmin.entity.Courseschedule;
@Service("coursescheduleService")
@Transactional
public class CoursescheduleServiceImpl implements ICoursescheduleService {
	@Autowired
	private ICoursescheduleDao coursescheduleDao;

	@Override
	public List<Courseschedule> getAllCourseschedules() {
		// TODO Auto-generated method stub
		return coursescheduleDao.getAllCourseschedules();
	}

	@Override
	public List<Courseschedule> getCourseschedulesByClassroomId(int classroomId) {
		// TODO Auto-generated method stub
		return coursescheduleDao.getCourseschedulesByClassroomId(classroomId);
	}

	@Override
	public List<Courseschedule> getCourseschedulesByCourseId(int courseId) {
		// TODO Auto-generated method stub
		return coursescheduleDao.getCourseschedulesByCourseId(courseId);
	}

	@Override
	public List<Courseschedule> getCourseschedulesByCourseState(int courseState) {
		// TODO Auto-generated method stub
		return coursescheduleDao.getCourseschedulesByCourseState(courseState);
	}

	@Override
	public void deleteCoursescheduleById(int id) {
		// TODO Auto-generated method stub
		coursescheduleDao.deleteCoursescheduleById(id);
	}

	@Override
	public void addCourseschedule(Courseschedule courseschedule) {
		// TODO Auto-generated method stub
		coursescheduleDao.addCourseschedule(courseschedule);
	}

	@Override
	public void modifyCourseschedule(Courseschedule Courseschedule) {
		// TODO Auto-generated method stub
		coursescheduleDao.modifyCourseschedule(Courseschedule);
	}

	@Override
	public Courseschedule getCoursescheduleById(int id) {
		// TODO Auto-generated method stub
		return coursescheduleDao.getCoursescheduleById(id);
	}

	@Override
	public List<Courseschedule> getCourseschedulesByTeacherId(int teacherId) {
		// TODO Auto-generated method stub
		return coursescheduleDao.getCourseschedulesByTeacherId(teacherId);
	}

	@Override
	public List<Integer> getAllTeacherId() {
		// TODO Auto-generated method stub
		return coursescheduleDao.getAllTeacherId();
	}

	@Override
	public List<Integer> getAllcourseCategoryIDsFormTeacher(int teacherId) {
		// TODO Auto-generated method stub
		return coursescheduleDao.getAllcourseCategoryIDsFormTeacher(teacherId);
	}

	@Override
	public List<Integer> getAllCourseIdOfTeacherFromCourse(List<Integer> courseCategoryIDs) {
		// TODO Auto-generated method stub
		return coursescheduleDao.getAllCourseIdOfTeacherFromCourse(courseCategoryIDs);
	}

	@Override
	public List<Integer> getAllClassroomIdOfCourse(int courseId) {
		// TODO Auto-generated method stub
		return coursescheduleDao.getAllClassroomIdOfCourse(courseId);
	}

	@Override
	public String getTeacherNameByTeacherId(int teacherId) {
		// TODO Auto-generated method stub
		return coursescheduleDao.getTeacherNameByTeacherId(teacherId);
	}

	@Override
	public String getCourseNameByCourseId(int courseId) {
		// TODO Auto-generated method stub
		return coursescheduleDao.getCourseNameByCourseId(courseId);
	}

	@Override
	public String getClassroomNameByClassroomId(int classroomId) {
		// TODO Auto-generated method stub
		return coursescheduleDao.getClassroomNameByClassroomId(classroomId);
	}

	
	
}
