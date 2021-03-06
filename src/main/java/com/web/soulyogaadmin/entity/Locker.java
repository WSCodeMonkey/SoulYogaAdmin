package com.web.soulyogaadmin.entity;
// Generated 2017-6-22 13:56:57 by Hibernate Tools 5.2.3.Final

import java.util.Date;

/**
 * Locker generated by hbm2java
 */
public class Locker implements java.io.Serializable {

	private Integer id;
	private String lockerNo;
	private int yogaClubId;
	private int status;
	private Date createdTime;
	private Date modifiedTime;
	private int state;

	public Locker() {
	}

	public Locker(String lockerNo, int yogaClubId, int status, Date createdTime, int state) {
		this.lockerNo = lockerNo;
		this.yogaClubId = yogaClubId;
		this.status = status;
		this.createdTime = createdTime;
		this.state = state;
	}

	public Locker(String lockerNo, int yogaClubId, int status, Date createdTime, Date modifiedTime, int state) {
		this.lockerNo = lockerNo;
		this.yogaClubId = yogaClubId;
		this.status = status;
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

	public String getLockerNo() {
		return this.lockerNo;
	}

	public void setLockerNo(String lockerNo) {
		this.lockerNo = lockerNo;
	}

	public int getYogaClubId() {
		return this.yogaClubId;
	}

	public void setYogaClubId(int yogaClubId) {
		this.yogaClubId = yogaClubId;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

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
