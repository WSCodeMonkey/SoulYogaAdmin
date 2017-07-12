package com.web.soulyogaadmin.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.soulyogaadmin.entity.Employee;
import com.web.soulyogaadmin.entity.Member;
import com.web.soulyogaadmin.entity.Memberaccount;
import com.web.soulyogaadmin.member.dao.IMemberDao;
import com.web.soulyogaadmin.member.service.IMemberService;

@Service("iMemberService")
public class MemberServiceImpl implements IMemberService {

	@Autowired
	private IMemberDao iMemberDao;
	
	@Override
	public void addMemberInfo(Member member, Memberaccount memberaccount)throws Exception {
		iMemberDao.addMember(member);
		int id=member.getId();
		memberaccount.setMemberId(id);
		iMemberDao.addMemberaccount(memberaccount);

	}


	@Override
	public void updateMember(Member member)throws Exception {
		iMemberDao.updateMember(member);
		
	}

	@Override
	public void updateMemberaccount(Memberaccount memberaccount)throws Exception {
		iMemberDao.updateMemberaccount(memberaccount);
		
	}


	@Override
	public Member showOneMember(int id) throws Exception{
		return iMemberDao.showOneMember(id);
	}


	@Override
	public List<Member> showAllMember()throws Exception {
		return iMemberDao.showAllMember();
	}


	@Override
	public List<Employee> consultantList() throws Exception {
		int positionId=1;
		return iMemberDao.consultantList(positionId);
	}


	@Override
	public List<Member> fuzzyFindByName(String name)throws Exception {
		return iMemberDao.fuzzyFind(name);
	}


	@Override
	public void deleteMember(int id) throws Exception{
		iMemberDao.delMember(id);
	}


	@Override
	public List<Member> queryByidentityId(String identityId) throws Exception {
		return iMemberDao.queryByidentityId(identityId);
	}


	@Override
	public Member queryByMobileNo(String mobileNo) {
		return iMemberDao.queryByMobileNo(mobileNo);
	}





}
