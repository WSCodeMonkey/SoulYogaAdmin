package com.web.soulyogaadmin.entity;
// Generated 2017-6-22 13:56:57 by Hibernate Tools 5.2.3.Final

import java.util.Date;

import javax.persistence.Column;

/**
 * Yogacushion generated by hbm2java
 */
public class Yogacushion implements java.io.Serializable {

	private Integer id;
	private String yogaCushionNo;
	private int classroomId;
	private Date createdTime;
	private Date modifiedTime;
	private int state;

	public Yogacushion() {
	}

	public Yogacushion(String yogaCushionNo, int classroomId, Date createdTime, int state) {
		this.yogaCushionNo = yogaCushionNo;
		this.classroomId = classroomId;
		this.createdTime = createdTime;
		this.state = state;
	}

	public Yogacushion(String yogaCushionNo, int classroomId, Date createdTime, Date modifiedTime, int state) {
		this.yogaCushionNo = yogaCushionNo;
		this.classroomId = classroomId;
		this.createdTime = createdTime;
		this.modifiedTime = modifiedTime;
		this.state = state;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getYogaCushionNo() {
		return this.yogaCushionNo;
	}

	public void setYogaCushionNo(String yogaCushionNo) {
		this.yogaCushionNo = yogaCushionNo;
	}

	public int getClassroomId() {
		return this.classroomId;
	}

	public void setClassroomId(int classroomId) {
		this.classroomId = classroomId;
	}
    @Column(updatable=false)
	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getModifiedTime() {
		return this.modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

}