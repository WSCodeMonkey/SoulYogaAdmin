package com.web.soulyogaadmin.vo;
// Generated 2017-6-22 13:56:57 by Hibernate Tools 5.2.3.Final

/**
 * Employee details for UI
 * 
 * @author Comi Zhou
 * @version 2017-6-27
 */
public class EmployeeView implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private int positionId;
	private String positionName;
	private String surname;
	private String name;
	private String fristName;
	private String lastName;
	private String mail;
	private String identityId;
	private Integer gender;
	private String avatarUrl;
	private int yogaClubId;
	private String yogaClubName;
	private int isTeacher;
	private String introduction;
	private String courseCategoryIds;

	public EmployeeView() {
	}

	public EmployeeView(Integer id, int positionId, String positionName, String surname, String name, String fristName,
			String lastName, String mail, String identityId, Integer gender, String avatarUrl, int yogaClubId,
			String yogaClubName, int isTeacher, String introduction, String courseCategoryIds) {
		super();
		this.id = id;
		this.positionId = positionId;
		this.positionName = positionName;
		this.surname = surname;
		this.name = name;
		this.fristName = fristName;
		this.lastName = lastName;
		this.mail = mail;
		this.identityId = identityId;
		this.gender = gender;
		this.avatarUrl = avatarUrl;
		this.yogaClubId = yogaClubId;
		this.yogaClubName = yogaClubName;
		this.isTeacher = isTeacher;
		this.introduction = introduction;
		this.courseCategoryIds = courseCategoryIds;
	}

	public int getIsTeacher() {
		return isTeacher;
	}

	public void setIsTeacher(int isTeacher) {
		this.isTeacher = isTeacher;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getPositionId() {
		return positionId;
	}

	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFristName() {
		return fristName;
	}

	public void setFristName(String fristName) {
		this.fristName = fristName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public int getYogaClubId() {
		return yogaClubId;
	}

	public void setYogaClubId(int yogaClubId) {
		this.yogaClubId = yogaClubId;
	}

	public String getYogaClubName() {
		return yogaClubName;
	}

	public void setYogaClubName(String yogaClubName) {
		this.yogaClubName = yogaClubName;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getCourseCategoryIds() {
		return courseCategoryIds;
	}

	public void setCourseCategoryIds(String courseCategoryIds) {
		this.courseCategoryIds = courseCategoryIds;
	}

}
