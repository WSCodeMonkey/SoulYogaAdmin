package com.web.soulyogaadmin.course.vo;

import java.util.Date;

import com.web.soulyogaadmin.entity.CourseEntry;

public class CourseView  {

	
	private int id;

	private String name;
	
	private int courseCategoryID;
	
	private int yogaClubID;
	
	private String introduction;
	
	private int point;
	
	private Date createdTime;
	
	private Date modifiedTime;
	
	private int state;
	

	private String yogaclubName;
	
	private String coursecategoryName;
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCourseCategoryID() {
		return courseCategoryID;
	}

	public void setCourseCategoryID(int courseCategoryID) {
		this.courseCategoryID = courseCategoryID;
	}

	public int getYogaClubID() {
		return yogaClubID;
	}

	public void setYogaClubID(int yogaClubID) {
		this.yogaClubID = yogaClubID;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}


	public String getYogaclubName() {
		return yogaclubName;
	}

	public void setYogaclubName(String yogaclubName) {
		this.yogaclubName = yogaclubName;
	}

	public String getCoursecategoryName() {
		return coursecategoryName;
	}

	public void setCoursecategoryName(String coursecategoryName) {
		this.coursecategoryName = coursecategoryName;
	}

	@Override
	public String toString() {
		return "CourseView [id=" + id + ", name=" + name + ", courseCategoryID=" + courseCategoryID + ", yogaClubID="
				+ yogaClubID + ", introduction=" + introduction + ", point=" + point + ", createdTime=" + createdTime
				+ ", modifiedTime=" + modifiedTime + ", state=" + state + ", yogaclubName=" + yogaclubName
				+ ", coursecategoryName=" + coursecategoryName + "]";
	}



	public CourseView() {
		super();
		// TODO Auto-generated constructor stub
	}


	
}
