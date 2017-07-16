package com.web.soulyogaadmin.entity;
// Generated 2017-6-22 13:56:57 by Hibernate Tools 5.2.3.Final

import java.util.Date;

/**
 * Verificationcode generated by hbm2java
 */
public class VerificationCode implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String phoneNo;
	private String code;
	private Date expiredTime;
	private Date createdTime;
	private Date modifiedTime;
	private int state;

	public VerificationCode() {
	}

	public VerificationCode(String phoneNo, String code, Date createdTime, int state) {
		this.phoneNo = phoneNo;
		this.code = code;
		this.createdTime = createdTime;
		this.state = state;
	}

	public VerificationCode(String phoneNo, String code, Date expiredTime, Date createdTime, Date modifiedTime,
			int state) {
		this.phoneNo = phoneNo;
		this.code = code;
		this.expiredTime = expiredTime;
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

	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getExpiredTime() {
		return this.expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
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