package com.web.soulyogaadmin.space.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.interceptor.annotations.After;
import com.opensymphony.xwork2.interceptor.annotations.Before;
import com.web.soulyogaadmin.entity.Classroom;
import com.web.soulyogaadmin.entity.Locker;
import com.web.soulyogaadmin.entity.Yogaclub;
import com.web.soulyogaadmin.entity.Yogacushion;
import com.web.soulyogaadmin.space.dao.IClassroomDao;
import com.web.soulyogaadmin.space.dao.IYogaclubDao;
import com.web.soulyogaadmin.space.service.ISpaceService;
import com.web.soulyogaadmin.vo.ClassroomYogacushionvo;
@Service("spaceService")
@Transactional
public class SpaceServiceImpl implements ISpaceService {
    @Autowired
	private IYogaclubDao yogaclubDao;
    @Autowired
    private IClassroomDao classroomDao;
	@Override
	public List getAllYogaclub() {
		// TODO Auto-generated method stub
      List<Yogaclub> allYogaclubList=null;
      allYogaclubList=  yogaclubDao.getAllYogaclubList();
	return allYogaclubList;
	
	}
	@Override
	public void deleteYogaclubbyId(int i) {
		yogaclubDao.deleteYogaclubbyId(i);
	}
	@Override
	public void updateYogaclub(Yogaclub yogaclub) {
		yogaclubDao.updateYogaclub(yogaclub);
		
	}
	@Override
	public void addYogaclub(Yogaclub yogaclub) {
		// TODO Auto-generated method stub
		yogaclubDao.addYogaclub(yogaclub);
	}
	@Override
	public List<ClassroomYogacushionvo> getClassroombyYogaclub(int i) {
		// TODO Auto-generated method stub
        		
		List<ClassroomYogacushionvo> templateList = (List<ClassroomYogacushionvo>) classroomDao.getClassroombyYogaclub(i);
		return templateList;
	}
	@Override
	public Yogaclub getYogaclubbyId(int i) {
		// TODO Auto-generated method stub
		Yogaclub yogaclub=yogaclubDao.getYogaclubbyId(i);
		return yogaclub;
	}
	@Override
	public List<ClassroomYogacushionvo> getAllClassroom() {
		List<ClassroomYogacushionvo> list= classroomDao.getAllClassroom();
	return	list;
	}
	@Override
	public int addClassroom(Classroom classroom) {
		return classroomDao.addClassroom(classroom);
		
		
	
	}
    public long getClassroomCount(int yogaclubId){
    	 return classroomDao.getClassroomCount(yogaclubId);
    	
    }
	@Override
	public void addYogacushion(Yogacushion yogacushion) {
		// TODO Auto-generated method stub
		classroomDao.addYogacushion(yogacushion);
	}
	@Override
	public void updateYogacushion(int updateCount,String classroomno) {
		// TODO Auto-generated method stub
	 	int Id=classroomDao.getClassroomidbyNo(classroomno);
	 	System.out.println("查询的主键是"+Id);
	 	String ClassroomNo=classroomDao.getClassroombyId(Id).getClassroomNo();
	 	int rows=classroomDao.deleteYogacushionbyClassroom(Id);
	 	System.out.println("删除的行数"+rows);
	 	try{
	 	for(int i=0;i<updateCount;i++){
	 		Yogacushion yogacushion=new Yogacushion();
	 		yogacushion.setClassroomId(Id);
	 		yogacushion.setModifiedTime(new Date());
	 		yogacushion.setYogaCushionNo(ClassroomNo+"_"+i+"号垫子");	 	
	 		classroomDao.addYogacushion(yogacushion);	 		
	 	}
	 	classroomDao.updateClassroom(Id);
	 	}
	 	catch(Exception e){
	 		System.out.println("增加垫子失败");
	 	}
	 	
	 	
	 	
	 	
	 	
	 	/*long Cushioncount=classroomDao.getYogacushionCount(Id);
	    System.out.println(Cushioncount+"垫子数量");
	    System.out.println("更新垫子数量"+updateCount);
	 	if(updateCount>Cushioncount){
	 		//如果输入大于已有的，则在瑜伽垫表添加瑜伽垫
	    	for(int i=0;i<updateCount-Cushioncount;i++){
	    		Yogacushion yogacushion=new Yogacushion();
	    		yogacushion.setClassroomId(Id);
	    		yogacushion.setYogaCushionNo(ClassroomNo + "号教室_" + String.valueOf(i) + "号垫子");
	    		yogacushion.setCreatedTime(new Date());
	    		classroomDao.addYogacushion(yogacushion);
	    	}
	    
	    	
	    	
	    	
	    	
	    }
	 	//如果输入小于输出在则需要在表内删除瑜伽垫
		else{
    		
    		
    		
    		
    	}*/
	
	}
	@Override
	public void deleteClassroom(String classroomno) {
	 	int Id=classroomDao.getClassroomidbyNo(classroomno);
	 	classroomDao.deleteClassroombyId(Id);
        classroomDao.deleteYogacushionbyClassroom(Id);
		
	}
	@Override
	public void addLocker(int yogaClubId, int lockerCount) {
/*		List<Locker> list=new ArrayList<Locker>();
*/		for(int i=0;i<lockerCount;i++){
		Locker locker=new Locker();
		locker.setCreatedTime(new Date());
		locker.setYogaClubId(yogaClubId);
		int LockerCount=(int) yogaclubDao.getLockerCount(yogaClubId);
		String LockerNo="locker"+String.valueOf(LockerCount);
		locker.setLockerNo(LockerNo);
		yogaclubDao.addLocker(locker);
        System.out.println(locker.getLockerNo());
		}
	
	}
	@Override
	public List<Locker> getLockerbyYogaclub(int id) {
		// TODO Auto-generated method stub
	return	yogaclubDao.getLockerbyYogaclub(id);
	
	
	}
	@Override
	public void updateLocker(int id, int status) {
		// TODO Auto-generated method stub
		yogaclubDao.updateLocker(id, status);
	}
	@Override
	public void deleteLockerbyYogaclub(int id) {
		// TODO Auto-generated method stub
		yogaclubDao.deleteLockerbyYogaclub(id);
	}

}