package com.web.soulyogaadmin.member.dao;

import java.util.List;

import com.web.soulyogaadmin.entity.Employee;
import com.web.soulyogaadmin.entity.Member;
import com.web.soulyogaadmin.entity.Memberaccount;

public interface IMemberDao {

	/**
	 * add member
	 */
	void addMember(Member member)throws Exception;
	
	/**
	 * add addMemberaccount
	 * @param memberaccount
	 */
	
	void addMemberaccount(Memberaccount memberaccount)throws Exception;
	
	
	/**
	 * Sales Consultant List
	 */
	
	List<Employee> consultantList(int positionId)throws Exception;
	
	void updateMember(Member member)throws Exception;
	
	void updateMemberaccount(Memberaccount memberaccount)throws Exception;
	
	void delMember(int id)throws Exception;
	
	Member showOneMember(int id)throws Exception;
	
	List<Member> showAllMember()throws Exception;
	
	List<Member> fuzzyFind(String name)throws Exception;
	
	List<Member> queryByidentityId(String identityId)throws Exception;
	
	Member queryByMobileNo(String mobileNo);
}
