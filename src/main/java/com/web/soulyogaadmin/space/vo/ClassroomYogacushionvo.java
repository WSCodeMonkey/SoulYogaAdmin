package com.web.soulyogaadmin.space.vo;

import com.web.soulyogaadmin.entity.Classroom;

public class ClassroomYogacushionvo {
	public int getYogaCushionCount() {
		return YogaCushionCount;
	}
	public void setYogaCushionCount(int yogaCushionCount) {
		YogaCushionCount = yogaCushionCount;
	}
	public String getYogaclubName() {
		return yogaclubName;
	}
	public void setYogaclubName(String yogaclubName) {
		this.yogaclubName = yogaclubName;
	}
	public String getClassroomNo() {
		return classroomNo;
	}
	public void setClassroomNo(String classroomNo) {
		this.classroomNo = classroomNo;
	}
	private int YogaCushionCount;
    private String yogaclubName;
    private String classroomNo;		


}
