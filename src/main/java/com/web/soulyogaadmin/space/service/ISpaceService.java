package com.web.soulyogaadmin.space.service;

import java.util.List;

import com.web.soulyogaadmin.entity.Classroom;
import com.web.soulyogaadmin.entity.Locker;
import com.web.soulyogaadmin.entity.Yogaclub;
import com.web.soulyogaadmin.entity.Yogacushion;
import com.web.soulyogaadmin.vo.ClassroomYogacushionvo;

public interface ISpaceService  {
	public List getAllYogaclub();
    public void deleteYogaclubbyId(int i);
    public void updateYogaclub(Yogaclub yogaclub);
    public void addYogaclub(Yogaclub yogaclub);
    public List<ClassroomYogacushionvo> getClassroombyYogaclub(int i);
    public Yogaclub getYogaclubbyId(int i);
    public List<ClassroomYogacushionvo> getAllClassroom();
    public int addClassroom(Classroom classroom);
    public long getClassroomCount(int yogaclubId);
    public void addYogacushion(Yogacushion yogacushion);
    public void updateYogacushion(int count,String classroomno);
    public void deleteClassroom(String classroomno);
    public void addLocker(int yogaClubId,int lockerCount);
    public List<Locker> getLockerbyYogaclub(int id );
    public void updateLocker(int id,int status);
    public void deleteLockerbyYogaclub(int id);
}
