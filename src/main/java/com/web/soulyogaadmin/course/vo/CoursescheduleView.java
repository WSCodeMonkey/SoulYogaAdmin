package com.web.soulyogaadmin.course.vo;

import java.util.Date;

import com.web.soulyogaadmin.entity.Courseschedule;

public class CoursescheduleView {
	private int id;
	private int teacherId;
	private int courseId;
	private int classroomId;
	private Date scheduleDate;
	private String startTime;
	private String endTime;
	private Date reservationStartTime;
	private Date reservationEndTime;
	private Date cancellationStartTime;
	private Date cancellationEndTime;
	private String checkinEndTime;
	private Date createdTime;
	private Date modifiedTime;
	private int state;
	
private String teacherName;
private String classroomName;
private String courseName;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getTeacherId() {
	return teacherId;
}
public void setTeacherId(int teacherId) {
	this.teacherId = teacherId;
}
public int getCourseId() {
	return courseId;
}
public void setCourseId(int courseId) {
	this.courseId = courseId;
}
public int getClassroomId() {
	return classroomId;
}
public void setClassroomId(int classroomId) {
	this.classroomId = classroomId;
}
public Date getScheduleDate() {
	return scheduleDate;
}
public void setScheduleDate(Date scheduleDate) {
	this.scheduleDate = scheduleDate;
}
public String getStartTime() {
	return startTime;
}
public void setStartTime(String startTime) {
	this.startTime = startTime;
}
public String getEndTime() {
	return endTime;
}
public void setEndTime(String endTime) {
	this.endTime = endTime;
}
public Date getReservationStartTime() {
	return reservationStartTime;
}
public void setReservationStartTime(Date reservationStartTime) {
	this.reservationStartTime = reservationStartTime;
}
public Date getReservationEndTime() {
	return reservationEndTime;
}
public void setReservationEndTime(Date reservationEndTime) {
	this.reservationEndTime = reservationEndTime;
}
public Date getCancellationStartTime() {
	return cancellationStartTime;
}
public void setCancellationStartTime(Date cancellationStartTime) {
	this.cancellationStartTime = cancellationStartTime;
}
public Date getCancellationEndTime() {
	return cancellationEndTime;
}
public void setCancellationEndTime(Date cancellationEndTime) {
	this.cancellationEndTime = cancellationEndTime;
}
public String getCheckinEndTime() {
	return checkinEndTime;
}
public void setCheckinEndTime(String checkinEndTime) {
	this.checkinEndTime = checkinEndTime;
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
public String getTeacherName() {
	return teacherName;
}
public void setTeacherName(String teacherName) {
	this.teacherName = teacherName;
}
public String getClassroomName() {
	return classroomName;
}
public void setClassroomName(String classroomName) {
	this.classroomName = classroomName;
}
public String getCourseName() {
	return courseName;
}
public void setCourseName(String courseName) {
	this.courseName = courseName;
}
@Override
public String toString() {
	return "CoursescheduleView [id=" + id + ", teacherId=" + teacherId + ", courseId=" + courseId + ", classroomId="
			+ classroomId + ", scheduleDate=" + scheduleDate + ", startTime=" + startTime + ", endTime=" + endTime
			+ ", reservationStartTime=" + reservationStartTime + ", reservationEndTime=" + reservationEndTime
			+ ", cancellationStartTime=" + cancellationStartTime + ", cancellationEndTime=" + cancellationEndTime
			+ ", checkinEndTime=" + checkinEndTime + ", createdTime=" + createdTime + ", modifiedTime=" + modifiedTime
			+ ", state=" + state + ", teacherName=" + teacherName + ", classroomName=" + classroomName + ", courseName="
			+ courseName + "]";
}
public CoursescheduleView(int id, int teacherId, int courseId, int classroomId, Date scheduleDate, String startTime,
		String endTime, Date reservationStartTime, Date reservationEndTime, Date cancellationStartTime,
		Date cancellationEndTime, String checkinEndTime, Date createdTime, Date modifiedTime, int state,
		String teacherName, String classroomName, String courseName) {
	super();
	this.id = id;
	this.teacherId = teacherId;
	this.courseId = courseId;
	this.classroomId = classroomId;
	this.scheduleDate = scheduleDate;
	this.startTime = startTime;
	this.endTime = endTime;
	this.reservationStartTime = reservationStartTime;
	this.reservationEndTime = reservationEndTime;
	this.cancellationStartTime = cancellationStartTime;
	this.cancellationEndTime = cancellationEndTime;
	this.checkinEndTime = checkinEndTime;
	this.createdTime = createdTime;
	this.modifiedTime = modifiedTime;
	this.state = state;
	this.teacherName = teacherName;
	this.classroomName = classroomName;
	this.courseName = courseName;
}
public CoursescheduleView() {
	super();
}

public CoursescheduleView(Courseschedule   courseschedule) {
	super();
	this.id = courseschedule.getId();
	this.teacherId = courseschedule.getTeacherId();
	this.courseId = courseschedule.getCourseId();
	this.classroomId = courseschedule.getClassroomId();
	this.scheduleDate = courseschedule.getScheduleDate();
	this.startTime = courseschedule.getStartTime();
	this.endTime = courseschedule.getEndTime();
	this.reservationStartTime = courseschedule.getReservationEndTime();
	this.reservationEndTime = courseschedule.getReservationEndTime();
	this.cancellationStartTime = courseschedule.getCancellationStartTime();
	this.cancellationEndTime = courseschedule.getCancellationEndTime();
	this.checkinEndTime = courseschedule.getCheckinEndTime();
	this.createdTime = courseschedule.getCreatedTime();
	this.modifiedTime = courseschedule.getModifiedTime();
	this.state = courseschedule.getState();
	
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((cancellationEndTime == null) ? 0 : cancellationEndTime.hashCode());
	result = prime * result + ((cancellationStartTime == null) ? 0 : cancellationStartTime.hashCode());
	result = prime * result + ((checkinEndTime == null) ? 0 : checkinEndTime.hashCode());
	result = prime * result + classroomId;
	result = prime * result + ((classroomName == null) ? 0 : classroomName.hashCode());
	result = prime * result + courseId;
	result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
	result = prime * result + ((createdTime == null) ? 0 : createdTime.hashCode());
	result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
	result = prime * result + id;
	result = prime * result + ((modifiedTime == null) ? 0 : modifiedTime.hashCode());
	result = prime * result + ((reservationEndTime == null) ? 0 : reservationEndTime.hashCode());
	result = prime * result + ((reservationStartTime == null) ? 0 : reservationStartTime.hashCode());
	result = prime * result + ((scheduleDate == null) ? 0 : scheduleDate.hashCode());
	result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
	result = prime * result + state;
	result = prime * result + teacherId;
	result = prime * result + ((teacherName == null) ? 0 : teacherName.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	CoursescheduleView other = (CoursescheduleView) obj;
	if (cancellationEndTime == null) {
		if (other.cancellationEndTime != null)
			return false;
	} else if (!cancellationEndTime.equals(other.cancellationEndTime))
		return false;
	if (cancellationStartTime == null) {
		if (other.cancellationStartTime != null)
			return false;
	} else if (!cancellationStartTime.equals(other.cancellationStartTime))
		return false;
	if (checkinEndTime == null) {
		if (other.checkinEndTime != null)
			return false;
	} else if (!checkinEndTime.equals(other.checkinEndTime))
		return false;
	if (classroomId != other.classroomId)
		return false;
	if (classroomName == null) {
		if (other.classroomName != null)
			return false;
	} else if (!classroomName.equals(other.classroomName))
		return false;
	if (courseId != other.courseId)
		return false;
	if (courseName == null) {
		if (other.courseName != null)
			return false;
	} else if (!courseName.equals(other.courseName))
		return false;
	if (createdTime == null) {
		if (other.createdTime != null)
			return false;
	} else if (!createdTime.equals(other.createdTime))
		return false;
	if (endTime == null) {
		if (other.endTime != null)
			return false;
	} else if (!endTime.equals(other.endTime))
		return false;
	if (id != other.id)
		return false;
	if (modifiedTime == null) {
		if (other.modifiedTime != null)
			return false;
	} else if (!modifiedTime.equals(other.modifiedTime))
		return false;
	if (reservationEndTime == null) {
		if (other.reservationEndTime != null)
			return false;
	} else if (!reservationEndTime.equals(other.reservationEndTime))
		return false;
	if (reservationStartTime == null) {
		if (other.reservationStartTime != null)
			return false;
	} else if (!reservationStartTime.equals(other.reservationStartTime))
		return false;
	if (scheduleDate == null) {
		if (other.scheduleDate != null)
			return false;
	} else if (!scheduleDate.equals(other.scheduleDate))
		return false;
	if (startTime == null) {
		if (other.startTime != null)
			return false;
	} else if (!startTime.equals(other.startTime))
		return false;
	if (state != other.state)
		return false;
	if (teacherId != other.teacherId)
		return false;
	if (teacherName == null) {
		if (other.teacherName != null)
			return false;
	} else if (!teacherName.equals(other.teacherName))
		return false;
	return true;
}

}
