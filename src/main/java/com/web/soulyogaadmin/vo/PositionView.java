package com.web.soulyogaadmin.vo;

import java.util.Date;

/**
 * Position details for UI
 * 
 * @author Comi Zhou
 * @version 2017-6-23
 */
public class PositionView implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer departmentId;
	private String departmentName;
	private String positionName;
	private Date createdTime;
	private Date modifiedTime;

	public PositionView() {
	}

	public PositionView(Integer id, Integer departmentId, String departmentName, String positionName, Date createdTime,
			Date modifiedTime) {
		super();
		this.id = id;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.positionName = positionName;
		this.createdTime = createdTime;
		this.modifiedTime = modifiedTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
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

}
