package com.web.soulyogaadmin.member.service;

import java.util.List;

import com.web.soulyogaadmin.entity.Employee;
import com.web.soulyogaadmin.entity.Member;
import com.web.soulyogaadmin.entity.Memberaccount;

public interface IMemberService {

	/**
	 * add Member information
	 */
	
	void addMemberInfo(Member member,Memberaccount memberaccount)throws Exception;
	void updateMember(Member member)throws Exception;
	void updateMemberaccount(Memberaccount memberaccount)throws Exception;
	Member showOneMember(int id)throws Exception;
	List<Member> showAllMember()throws Exception;
	List<Employee> consultantList()throws Exception;
	List<Member> fuzzyFindByName(String name)throws Exception;
	void deleteMember(int id)throws Exception;
	List<Member> queryByidentityId(String identityId)throws Exception;
	Member queryByMobileNo(String mobileNo);
}
