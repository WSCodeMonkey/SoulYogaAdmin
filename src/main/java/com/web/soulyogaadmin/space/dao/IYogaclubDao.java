package com.web.soulyogaadmin.space.dao;

import java.util.List;

import com.web.soulyogaadmin.entity.Locker;
import com.web.soulyogaadmin.entity.Yogaclub;

public interface IYogaclubDao {
	public List<Yogaclub> getAllYogaclubList();
	public void deleteYogaclubbyId(int i);
    public Yogaclub getYogaclubbyId(int i);
    public void updateYogaclub(Yogaclub yogaclub);
    public void addYogaclub(Yogaclub yogaclub);
    public long getLockerCount(int yogaclubid);
    public void addLocker(Locker locker);
    public  List<Locker> getLockerbyYogaclub(int id );
    public void updateLocker(int id,int status);
    public void deleteLockerbyYogaclub(int id);
    public int getPersonalLocker(String yogaclubname,int memberid) throws Exception;
    public void changeLockerStatus(int lockerid,boolean flag)throws Exception;//flag 判断checkin或者checkout
    public int getYogaclubIdbyName(String yogaclubname);
}  
