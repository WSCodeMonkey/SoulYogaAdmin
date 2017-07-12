package com.web.soulyogaadmin.space.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.web.soulyogaadmin.entity.Classroom;
import com.web.soulyogaadmin.entity.CourseEntry;
import com.web.soulyogaadmin.entity.Yogacushion;
import com.web.soulyogaadmin.vo.ClassroomYogacushionvo;
import com.web.soulyogaadmin.vo.Yogacushionvo;

/**
 * Course Dao Interface
 * @author Shawn xiao
 * @version 
 */
/*@Repository(value="classroomDao")*/
public interface IClassroomDao {

	public List<ClassroomYogacushionvo> getClassroombyYogaclub( int i);
    public List<ClassroomYogacushionvo> getAllClassroom();
    public List<Yogacushionvo> getYogacushion();
    public int addClassroom(Classroom classroom);
    public long getClassroomCount(int yogaclubId);
    public void addYogacushion(Yogacushion yogacushion);
    public int getClassroomidbyNo(String classroomNo);//通过classroomNo来获取classroom的主键
    public long getYogacushionCount(int classroomId);
    public Classroom getClassroombyId(int id);
    public int deleteYogacushionbyClassroom(int id);
    public void deleteClassroombyId(int id);
    public void updateClassroom(int id);
}
