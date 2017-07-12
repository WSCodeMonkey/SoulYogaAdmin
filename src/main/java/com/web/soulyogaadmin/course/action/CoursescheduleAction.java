package com.web.soulyogaadmin.course.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.web.soulyogaadmin.course.service.ICoursescheduleService;
import com.web.soulyogaadmin.course.vo.CoursescheduleView;
import com.web.soulyogaadmin.entity.Courseschedule;

import oracle.net.aso.j;


@ParentPackage(value="struts-default")
@Namespace(value="/")
public class CoursescheduleAction extends ActionSupport implements ModelDriven<Courseschedule>{
	Courseschedule courseschedule =new Courseschedule();
	private static String className = CourseAction.class.getName();

	private static Logger logger = Logger.getLogger(className);
@Autowired
ICoursescheduleService coursescheduleService;
	@Override
	public Courseschedule getModel() {
		// TODO Auto-generated method stub
		return courseschedule;
	}
	@Action(value="getAllCoursesecheduleList" ,results={@Result(name="AllCoursesecheduleList",location="/courseschedule/coursescheduleQueryList.jsp")})
	public String getAllCoursesecheduletoCoursesecheduleQueryListPage(){
		HttpServletRequest request=ServletActionContext.getRequest();
		List<Courseschedule>lists=coursescheduleService.getAllCourseschedules();
		List<CoursescheduleView>coursescheduleViewList=new ArrayList<CoursescheduleView>();
		
		if(lists!=null&&lists.size()>0){
			for(Courseschedule c:lists){
				CoursescheduleView cv=new CoursescheduleView(c);
				cv.setTeacherName(coursescheduleService.getTeacherNameByTeacherId(c.getTeacherId()));
				cv.setCourseName(coursescheduleService.getCourseNameByCourseId(c.getCourseId()));
				cv.setClassroomName(coursescheduleService.getClassroomNameByClassroomId(c.getClassroomId()));
				coursescheduleViewList.add(cv);
				
			}
			System.out.println("===================getAllCoursesecheduleList()=============");
			System.out.println(coursescheduleViewList.toString());
			//request.setAttribute("allCourseschedules_list", lists);
			request.setAttribute("allCourseschedules_list", coursescheduleViewList);
		}
		return "AllCoursesecheduleList";
		
	}

	@Action(value="getPartCoursesecheduleList",results={@Result(name="PartCoursesecheduleList",location="/courseschedule/coursescheduleQueryList.jsp")})
	public String getPartCoursesecheduleListtoCoursesecheduleQueryListPage(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String teacherId=request.getParameter("teacher_id_name");
		String classroomId=request.getParameter("classroom_id_name");
		String courseId=request.getParameter("course_id_name");
		System.out.println("===== getPartCoursesecheduleListtoCoursesecheduleQueryListPage()====");
		System.out.println(teacherId+","+classroomId+","+courseId);
		List<Courseschedule>lists=null;
		List<Courseschedule>lists1=null; 
		List<Courseschedule>lists2=null;
		 if((teacherId!=null&&!teacherId.equals(""))&&(classroomId!=null&&!classroomId.equals(""))&&(courseId!=null&&!courseId.equals(""))){
			lists=coursescheduleService.getCourseschedulesByTeacherId(Integer.parseInt(teacherId));
			lists1=coursescheduleService.getCourseschedulesByCourseId(Integer.parseInt(courseId));
			lists2=coursescheduleService.getCourseschedulesByClassroomId(Integer.parseInt(classroomId));
			lists.retainAll(lists1);
			lists.retainAll(lists2);
		}else if((teacherId==null||teacherId.equals(""))&&(classroomId!=null&&!classroomId.equals(""))&&(courseId!=null&&!courseId.equals(""))){		
			lists=coursescheduleService.getCourseschedulesByCourseId(Integer.parseInt(courseId));
			lists2=coursescheduleService.getCourseschedulesByClassroomId(Integer.parseInt(classroomId));
			lists.retainAll(lists2);
		}else if((teacherId!=null&&!teacherId.equals(""))&&(classroomId==null||classroomId.equals(""))&&(courseId!=null&&!courseId.equals(""))){
			lists=coursescheduleService.getCourseschedulesByTeacherId(Integer.parseInt(teacherId));
			lists1=coursescheduleService.getCourseschedulesByCourseId(Integer.parseInt(courseId));
			lists.retainAll(lists1);
		}else if((teacherId!=null&&!teacherId.equals(""))&&(classroomId!=null&&!classroomId.equals(""))&&(courseId==null||courseId.equals(""))){
			lists=coursescheduleService.getCourseschedulesByTeacherId(Integer.parseInt(teacherId));
			lists2=coursescheduleService.getCourseschedulesByClassroomId(Integer.parseInt(classroomId));
			lists.retainAll(lists2);
		}else if((teacherId==null||teacherId.equals(""))&&(classroomId==null||classroomId.equals(""))&&(courseId!=null&&!courseId.equals(""))){
			lists=coursescheduleService.getCourseschedulesByCourseId(Integer.parseInt(courseId));
		}else if((teacherId==null||teacherId.equals(""))&&(classroomId!=null&&!classroomId.equals(""))&&(courseId==null||courseId.equals(""))){

			lists=coursescheduleService.getCourseschedulesByClassroomId(Integer.parseInt(classroomId));
	
		}else if((teacherId!=null&&!teacherId.equals(""))&&(classroomId==null||classroomId.equals(""))&&(courseId==null||courseId.equals(""))){
			lists=coursescheduleService.getCourseschedulesByTeacherId(Integer.parseInt(teacherId));
		
		}else{
			lists=coursescheduleService.getAllCourseschedules();
		
		}
		 List<CoursescheduleView>coursescheduleViewList=new ArrayList<CoursescheduleView>();
			
			if(lists!=null&&lists.size()>0){
				for(Courseschedule c:lists){
					CoursescheduleView cv=new CoursescheduleView(c);
					cv.setTeacherName(coursescheduleService.getTeacherNameByTeacherId(c.getTeacherId()));
					cv.setCourseName(coursescheduleService.getCourseNameByCourseId(c.getCourseId()));
					cv.setClassroomName(coursescheduleService.getClassroomNameByClassroomId(c.getClassroomId()));
					coursescheduleViewList.add(cv);
					
				}
			}
			System.out.println("==============================================");
			System.out.println(coursescheduleViewList.toString());
			
			
		if(lists!=null&&lists.size()>0){
			request.removeAttribute("allCourseschedules_list");
			request.setAttribute("allCourseschedules_list", coursescheduleViewList);
		}
		return "PartCoursesecheduleList";
		
	}
			
	@Action(value="deleteOneCourseshedule",results={@Result(name="deleteCourseshedule",type="chain",location="getAllCoursesecheduleList" )})
	public String deleteOneCourseshedule(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String id=request.getParameter("deleteCoursescheduleId");
		coursescheduleService.deleteCoursescheduleById(Integer.parseInt(id));
		return "deleteCourseshedule";
		
	}
	@Action(value="getTeacherId",results={@Result(name="TeacherId",location="/courseschedule/addNewCourseschedule.jsp")})
	public String getTeacherIdToAddPage() throws IOException{
		List<Integer> tids=coursescheduleService.getAllTeacherId();
		HttpServletResponse response =ServletActionContext.getResponse();
		HttpServletRequest request =ServletActionContext.getRequest();
		List<CoursescheduleView> listCv=new ArrayList<CoursescheduleView>();
		for(int i=0;i<tids.size();i++){
			CoursescheduleView c=new CoursescheduleView();
			c.setTeacherId(tids.get(i));
			c.setTeacherName(coursescheduleService.getTeacherNameByTeacherId(tids.get(i)));
			listCv.add(c);
		}
		//coursescheduleService.getTeacherNameByTeacherId(teacherId)s
		System.out.println("=======================");
		System.out.println(tids.get(0));
		System.out.println("=======================");
		System.out.println(tids.toString());
		//response.getWriter().print(tids);
		//request.setAttribute("teacherIdlist", tids);
		request.setAttribute("CList", listCv);
	
		//response.getWriter().print(tids);
		return "TeacherId";	
	}
	@Action(value="getTeacherNameToModifyPage")
	public void getTeacherIdToCoursesecheduleModifyPage() throws IOException{
		List<Integer> tids=coursescheduleService.getAllTeacherId();
		HttpServletResponse response =ServletActionContext.getResponse();
		HttpServletRequest request =ServletActionContext.getRequest();
		List<CoursescheduleView> listCv=new ArrayList<CoursescheduleView>();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<tids.size();i++){
			CoursescheduleView c=new CoursescheduleView();
			c.setTeacherId(tids.get(i));
			c.setTeacherName(coursescheduleService.getTeacherNameByTeacherId(tids.get(i)));
			listCv.add(c);
			jsonArray.add(c);
		}
		//coursescheduleService.getTeacherNameByTeacherId(teacherId)s
		System.out.println("=======================");
		System.out.println(tids.get(0));
		System.out.println("=======================");
		System.out.println(tids.toString());
		//response.getWriter().print(tids);
		//request.setAttribute("teacherIdlist", tids);
		//request.setAttribute("CList", listCv);
		response.getWriter().print(jsonArray);
		//response.getWriter().print(tids);
		//return "TeacherId";	
	}
	@Action(value="getTeacherNameToQueryPage")
	public void getTeacherIdToCoursesecheduleQueryPage() throws IOException{
		List<Integer> tids=coursescheduleService.getAllTeacherId();
		HttpServletResponse response =ServletActionContext.getResponse();
		HttpServletRequest request =ServletActionContext.getRequest();
		List<CoursescheduleView> listCv=new ArrayList<CoursescheduleView>();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<tids.size();i++){
			CoursescheduleView c=new CoursescheduleView();
			c.setTeacherId(tids.get(i));
			c.setTeacherName(coursescheduleService.getTeacherNameByTeacherId(tids.get(i)));
			listCv.add(c);
			jsonArray.add(c);
		}
		
		response.getWriter().print(jsonArray);		
	}
	@Action(value="getTeacherNameToAddPage")
	public void  ToCoursesecheduleAddPage() throws IOException{
		//换成teacherName
		HttpServletResponse response =ServletActionContext.getResponse();
		List<Integer>tids=coursescheduleService.getAllTeacherId();
		//String[] teacherNames=new String[(tids.size())*2];
		List<CoursescheduleView>teacherNames=new ArrayList<CoursescheduleView>();
		for(int i=0;i<tids.size();i++){
			CoursescheduleView c=new CoursescheduleView();
			c.setTeacherId(tids.get(i));
			c.setTeacherName(coursescheduleService.getTeacherNameByTeacherId(tids.get(i)));		
			teacherNames.add(c);
		}
		System.out.println("Method:() ");
		System.out.println("进入ajax方法"+"======"+tids.toString());
		System.out.println("teacherNames"+"======"+teacherNames.toString());
		response.getWriter().print(teacherNames);
		//response.getWriter().print(tids);
	}
	@Action(value="getCourseNameToModifyPage")
	public void  getCourseIdToCoursesecheduleModifyPage() throws IOException{
		try {
			System.out.println("Method:getCourseId() ");
		System.out.println("进入ajax方法");
		
		HttpServletResponse response =ServletActionContext.getResponse();
		HttpServletRequest request=ServletActionContext.getRequest();
		String teacherId=request.getParameter("tid");
		System.out.println(teacherId);
		List<Integer> list =coursescheduleService.getAllCourseIdOfTeacherFromCourse(coursescheduleService.getAllcourseCategoryIDsFormTeacher(Integer.parseInt(teacherId)));
		List<CoursescheduleView> listC=new ArrayList<CoursescheduleView>();
		System.out.println("12312312312312312312134123"+list);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<list.size();i++){
			CoursescheduleView c=new CoursescheduleView();
			c.setCourseId(list.get(i));
	       c.setCourseName(coursescheduleService.getCourseNameByCourseId(list.get(i)));
	       listC.add(c);
	       jsonArray.add(c);
		   
		}
		//String  jsonArray =  JSONArray.toJSONString(list);
		response.getWriter().print(jsonArray);
		//response.getWriter().print(list);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		//return "getCourseId";
	}
	@Action(value="getCourseNameToQueryPage")
	public void  getCourseIdToCoursesecheduleQueryPage() throws IOException{
		try {
			System.out.println("Method:getCourseId() ");
		System.out.println("进入ajax方法");
		
		HttpServletResponse response =ServletActionContext.getResponse();
		HttpServletRequest request=ServletActionContext.getRequest();
		String teacherId=request.getParameter("tid");
		System.out.println(teacherId);
		List<Integer> list =coursescheduleService.getAllCourseIdOfTeacherFromCourse(coursescheduleService.getAllcourseCategoryIDsFormTeacher(Integer.parseInt(teacherId)));
		List<CoursescheduleView> listC=new ArrayList<CoursescheduleView>();
		
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<list.size();i++){
			CoursescheduleView c=new CoursescheduleView();
			c.setCourseId(list.get(i));
	       c.setCourseName(coursescheduleService.getCourseNameByCourseId(list.get(i)));
	       listC.add(c);
	       jsonArray.add(c);
		   
		}	
		response.getWriter().print(jsonArray);	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		//return "getCourseId";
	}
	@Action(value="getCourseNameToAddPage")
	public void  getCourseNameToAddPage() throws IOException{
		try {
			System.out.println("Method:getCourseId() ");
		System.out.println("进入ajax方法");
		
		HttpServletResponse response =ServletActionContext.getResponse();
		HttpServletRequest request=ServletActionContext.getRequest();
		String teacherId=request.getParameter("tid");
		System.out.println(teacherId);
		List<Integer> list =coursescheduleService.getAllCourseIdOfTeacherFromCourse(coursescheduleService.getAllcourseCategoryIDsFormTeacher(Integer.parseInt(teacherId)));
		List<CoursescheduleView> listC=new ArrayList<CoursescheduleView>();
		System.out.println("12312312312312312312134123"+list);
		
		for(int i=0;i<list.size();i++){
			CoursescheduleView c=new CoursescheduleView();
			c.setCourseId(list.get(i));
	       c.setCourseName(coursescheduleService.getCourseNameByCourseId(list.get(i)));
	       listC.add(c);
		   
		}
		//String  jsonArray =  JSONArray.toJSONString(list);
		System.out.println(listC);
		response.setContentType("text/html;charset=utf-8");
		JSONArray jsonarray = new JSONArray();
		for(int i=0;i<listC.size();i++){
			jsonarray.add(listC.get(i)) ;	
		}
		
		
		
		response.getWriter().print(jsonarray);
		//response.getWriter().print(list);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		//return "getCourseId";
	}
	
	@Action(value="getClassroomNameToModifyPage")
	public void getClassroomidToCoursesecheduleQueryListPage() throws IOException{
		HttpServletResponse response =ServletActionContext.getResponse();
		HttpServletRequest request =ServletActionContext.getRequest();
		
		String cId=request.getParameter("c_id");
		List<Integer>list=coursescheduleService.getAllClassroomIdOfCourse(Integer.parseInt(cId));
		List<CoursescheduleView> listC=new ArrayList<CoursescheduleView>();
		JSONArray jsonArray =new JSONArray();
		for(int i=0;i<list.size();i++){
			CoursescheduleView c=new CoursescheduleView();
			c.setClassroomId(list.get(i));
			c.setClassroomName(coursescheduleService.getClassroomNameByClassroomId(list.get(i)));
			listC.add(c);
			jsonArray.add(c);
		}
		System.out.println("===============");
		System.out.println(list);		
	
		response.getWriter().print(jsonArray);			
	}
	
	@Action(value="getClassroomNameToQueryPage")
	public void getClassroomNameToCoursesecheduleQueryListPage() throws IOException{
		HttpServletResponse response =ServletActionContext.getResponse();
		HttpServletRequest request =ServletActionContext.getRequest();
		
		String cId=request.getParameter("c_id");
		List<Integer>list=coursescheduleService.getAllClassroomIdOfCourse(Integer.parseInt(cId));
		List<CoursescheduleView> listC=new ArrayList<CoursescheduleView>();
		JSONArray jsonArray =new JSONArray();
		for(int i=0;i<list.size();i++){
			CoursescheduleView c=new CoursescheduleView();
			c.setClassroomId(list.get(i));
			c.setClassroomName(coursescheduleService.getClassroomNameByClassroomId(list.get(i)));
			listC.add(c);
			jsonArray.add(c);
		}
		System.out.println("===============");
		System.out.println(list);		
	
		response.getWriter().print(jsonArray);			
	}
	
	@Action(value="getClassroomNameToAddPage")
	public void getClassroomNameToCoursesecheduleAddPage() throws IOException{
		HttpServletResponse response =ServletActionContext.getResponse();
		HttpServletRequest request =ServletActionContext.getRequest();
		
		String cId=request.getParameter("c_id");
		List<Integer>list=coursescheduleService.getAllClassroomIdOfCourse(Integer.parseInt(cId));
		List<CoursescheduleView> listC=new ArrayList<CoursescheduleView>();
		for(int i=0;i<list.size();i++){
			CoursescheduleView c=new CoursescheduleView();
			c.setClassroomId(list.get(i));
			c.setClassroomName(coursescheduleService.getClassroomNameByClassroomId(list.get(i)));
			listC.add(c);
		}
		System.out.println("===============");
		System.out.println(list);		
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<listC.size();i++){
			jsonArray.add(listC.get(i));
		}
		response.getWriter().print(jsonArray);
			
	}
	@Action(value="addCourse",results={@Result(name="addCourse",type="chain",location="getAllCoursesecheduleList")})
	public String addCourseToAddPage() throws IOException{
		System.out.println("============addCourse()=========== ");
		System.out.println(courseschedule.toString());
		coursescheduleService.addCourseschedule(courseschedule);		
		return "addCourse";

	}
	@Action(value="beforModify",results={@Result(name="beforModifyJsp",location="/courseschedule/oneCoursesheduleModify.jsp")})
	public String beforModify() throws IOException{
		HttpServletRequest request =ServletActionContext.getRequest();
		String id=request.getParameter("modifiedId");
		Courseschedule c=coursescheduleService.getCoursescheduleById(Integer.parseInt(id));
		System.out.println("method:beforModify()");
		System.out.println(c.toString());
		CoursescheduleView cv=new CoursescheduleView(c);
		cv.setTeacherName(coursescheduleService.getTeacherNameByTeacherId(c.getTeacherId()));
		cv.setCourseName(coursescheduleService.getCourseNameByCourseId(c.getCourseId()));
		cv.setClassroomName(coursescheduleService.getClassroomNameByClassroomId(c.getClassroomId()));
		
		//request.setAttribute("oneCourseschedule", c);
		request.setAttribute("oneCourseschedule", cv);
		return "beforModifyJsp";
		
		
	}
	
	@Action(value="modifyoneCourseschedule",results={@Result(name="modifiedCourseschedule",type="chain",location="getAllCoursesecheduleList")})
	public String modifyCourseschedule() throws IOException{
		//HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("============modifyCourseschedule()=========== ");
		System.out.println(courseschedule.toString());
		coursescheduleService.modifyCourseschedule(courseschedule);
		return "modifiedCourseschedule";
		
	}
}
