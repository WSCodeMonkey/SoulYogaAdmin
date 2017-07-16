package com.web.soulyogaadmin.courseattendance.vo;

import java.util.Date;

import com.web.soulyogaadmin.entity.Coursereservation;

public class CourseattendanceView extends Coursereservation {

	private String  checkInendTime;
	private Integer courseattendanceId;
	private int productPurchaseId;
	private Date checkInTime;
	
	
	
	public String getCheckInendTime() {
		return checkInendTime;
	}
	public void setCheckInendTime(String checkInendTime) {
		this.checkInendTime = checkInendTime;
	}
	public Integer getCourseattendanceId() {
		return courseattendanceId;
	}
	public void setCourseattendanceId(Integer courseattendanceId) {
		this.courseattendanceId = courseattendanceId;
	}
	public int getProductPurchaseId() {
		return productPurchaseId;
	}
	public void setProductPurchaseId(int productPurchaseId) {
		this.productPurchaseId = productPurchaseId;
	}
	public Date getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}


	
	
}
