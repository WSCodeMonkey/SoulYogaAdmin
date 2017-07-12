package com.web.soulyogaadmin.course.service;

import java.util.Date;
import java.util.List;

import com.web.soulyogaadmin.entity.Courseschedule;

public interface ICoursescheduleService {
	public List<Courseschedule> getAllCourseschedules();
	public List<Courseschedule> getCourseschedulesByClassroomId(int classroomId);
	public List<Courseschedule> getCourseschedulesByCourseId(int courseId);
	public List<Courseschedule> getCourseschedulesByCourseState(int courseState);
	public void deleteCoursescheduleById(int id);
	public void addCourseschedule(Courseschedule courseschedule);
	public void modifyCourseschedule(Courseschedule Courseschedule);
	public List<Courseschedule> getCourseschedulesByTeacherId(int teacherId);
	public Courseschedule getCoursescheduleById(int id);
	public List<Integer> getAllTeacherId();
	public List<Integer> getAllcourseCategoryIDsFormTeacher(int teacherId);
	public List<Integer> getAllCourseIdOfTeacherFromCourse(List<Integer>  courseCategoryIDs);
	public List<Integer> getAllClassroomIdOfCourse(int courseId);
	public String getTeacherNameByTeacherId(int teacherId);
	public String getCourseNameByCourseId(int courseId);
	public String getClassroomNameByClassroomId(int classroomId);
}
