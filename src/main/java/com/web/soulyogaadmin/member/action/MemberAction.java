package com.web.soulyogaadmin.member.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.web.soulyogaadmin.entity.Employee;
import com.web.soulyogaadmin.entity.Member;
import com.web.soulyogaadmin.entity.Memberaccount;
import com.web.soulyogaadmin.member.service.IMemberService;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
/*
 * @Namespace(value="/member")
 */public class MemberAction extends ActionSupport {

	private static String className = MemberAction.class.getName();

	private static Logger logger = Logger.getLogger(className);
	private Member member;
	private String birthday;

	public String getBirthday()throws Exception {
		return birthday;
	}

	public void setBirthday(String birthday)throws Exception {
		this.birthday = birthday;
	}

	public Member getMember()throws Exception {
		return member;
	}

	public void setMember(Member member)throws Exception {
		this.member = member;
	}

	

	@Autowired
	private IMemberService iMemberService;

	public String defaultAction(){
		return "success";
	}
	
	
	@Action(value = "register", results = { @Result(name = "success",  type="chain",location="memberlist") })
	public String register()  {
		HttpServletRequest request = ServletActionContext.getRequest();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date bir = sdf.parse(birthday);
			member.setBirthday(bir);
			member.setCreatedTime(new Date());
			member.setState(0);
			Memberaccount mc = new Memberaccount();
			mc.setNickName(member.getName());
			mc.setPassword("123");
			byte[] bt = { 1 };
			mc.setFingerprint(bt);
			mc.setCreatedTime(new Date());
			mc.setState(0);
			iMemberService.addMemberInfo(member, mc);
			request.setAttribute("member", member);
		} catch (Exception e) {
			logger.error(e.getMessage());
		    return "error";
		}
		return "success";

	}

	@Action(value = "memberregister", results = {
			@Result(name = "success", location = "../../member/MemberRegister.jsp") })
	public String memberregister() {
		try {
			List<Employee> list = iMemberService.consultantList();
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("employee", list);
		} catch (Exception e) {
			logger.error(e.getMessage());
		    return "error";
		}
		return "success";
	}

	@Action(value = "memberlist", results = { @Result(name = "success", location = "../../member/MemberList.jsp") })
	public String memberList() {
		try {
			List<Member> list = iMemberService.showAllMember();
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("Member", list);
		} catch (Exception e) {
			logger.error(e.getMessage());
		    return "error";
		}
		return "success";
	}

	@Action(value = "memberupdate", results = { @Result(name = "success", location = "../../member/MemberUpdate.jsp") })
	public String memberupdate() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			List<Employee> list = iMemberService.consultantList();
			request.setAttribute("employee", list);
			String id = request.getParameter("id");
			Member memberUpdate = iMemberService.showOneMember(Integer.parseInt(id));
			request.setAttribute("memberUpdate", memberUpdate);
		} catch (Exception e) {
			logger.error(e.getMessage());
		    return "error";
		}
		return "success";
	}

	@Action(value = "update", results = { @Result(name = "success",  type="chain",location="memberlist") })
	public String update(){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date bir = sdf.parse(birthday);
			member.setBirthday(bir);
			member.setModifiedTime(new Date());
			iMemberService.updateMember(member);
		} catch (Exception e) {
			logger.error(e.getMessage());
		    return "error";
		}
		return "success";
	}

	@Action(value = "fuzzyfind", results = { @Result(name = "success", location = "../../member/FuzzyFind.jsp") })
	public String fuzzyfind() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String fuzzyName=request.getParameter("fuzzyName");
			List<Member> list=iMemberService.fuzzyFindByName(fuzzyName);
			request.setAttribute("fuzzyfind", list);
			if(list.isEmpty()){
				request.setAttribute("message", "未查询到结果！");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		    return "error";
		}
		return "success";
	}
	
	@Action(value = "memberdelete", results = { @Result(name = "success",  type="chain",location="memberlist") })
	public String memberdelete() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String id=request.getParameter("id");
			iMemberService.deleteMember(Integer.parseInt(id));
		} catch (Exception e) {
			logger.error(e.getMessage());
		    return "error";
		}
		return "success";
	}
	@Action(value = "identityId")
	public void validateIdentityId(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse reponse=ServletActionContext.getResponse();
			String identityId=request.getParameter("identityId");
			List<Member> list=iMemberService.queryByidentityId(identityId);
			if(list.isEmpty()){
				//集合为空时：
				reponse.getWriter().write("0");
			}else{
				//集合非空
				reponse.getWriter().write("1");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	
		
		
	}
	
	
	
	
}
