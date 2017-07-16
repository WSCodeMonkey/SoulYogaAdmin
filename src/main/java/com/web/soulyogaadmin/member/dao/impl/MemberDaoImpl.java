package com.web.soulyogaadmin.member.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.soulyogaadmin.entity.Employee;
import com.web.soulyogaadmin.entity.Member;
import com.web.soulyogaadmin.entity.Memberaccount;
import com.web.soulyogaadmin.member.dao.IMemberDao;

@Repository("iMemberDao")
@Transactional
public class MemberDaoImpl implements IMemberDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	private Session session;
	
	private static String className = MemberDaoImpl.class.getName();
	
	private static Logger logger = Logger.getLogger(className);

	@Override
	public void addMember(Member member)throws Exception {
		Session session=sessionFactory.getCurrentSession();
		session.save(member);
	}

	@Override
	public List<Employee> consultantList(int positionId) throws Exception{
		List<Employee> list=new ArrayList<Employee>();
		String hql="from Employee e where e.positionId=? and e.state=0";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		query.setInteger(0, positionId);
		list=query.list();
		return list;
	}

	@Override
	public void addMemberaccount(Memberaccount memberaccount)throws Exception {
		Session session=sessionFactory.getCurrentSession();
		session.save(memberaccount);
	}

	@Override
	public void updateMember(Member member)throws Exception {
		Session session=sessionFactory.getCurrentSession();
		session.update(member);		
	}

	@Override
	public void updateMemberaccount(Memberaccount memberaccount)throws Exception {
		Session session=sessionFactory.getCurrentSession();
		session.update(memberaccount);
		
	}

	@Override
	public void delMember(int id) throws Exception{
		Member member=showOneMember(id);
		member.setState(1);
		updateMember(member);
	}

	@Override
	public Member showOneMember(int id) throws Exception{
		Session session=sessionFactory.getCurrentSession();
		Member member=(Member) session.get(Member.class, id);
		if(member==null){
			return null;
		}else{
			if(member.getState()==1){
				return null;
			}else{
				return member;
			}
		}
	}

	@Override
	public List<Member> showAllMember() throws Exception{
		List<Member> list=new ArrayList<Member>();
		Session session=sessionFactory.getCurrentSession();
		String hql="from Member m where m.state=0 order by m.createdTime desc ";
		Query query=session.createQuery(hql);
		list=query.list();
		return list;
	}

	@Override
	public List<Member> fuzzyFind(String name)throws Exception {
		List<Member> list=new ArrayList<Member>();
		Session session=sessionFactory.getCurrentSession();
		String hql="from Member m where m.name like ? and m.state=0";
		Query query=session.createQuery(hql);
		query.setString(0,"%"+name+"%");
		list=query.list();
		return list;
	}

	@Override
	public List<Member> queryByidentityId(String identityId) throws Exception {
		List<Member> list=new ArrayList<Member>();
		Session session=sessionFactory.getCurrentSession();
		String hql="from Member m where m.identityId=? and m.state=0";
		Query query=session.createQuery(hql);
		query.setString(0,identityId);
		list=query.list();
		return list;
	}

	@Override
	public Member queryByMobileNo(String mobileNo) {
		Session session=sessionFactory.getCurrentSession();
		String hql="from Member m where m.mobileNo=?";
		Query query=session.createQuery(hql);
		query.setString(0,mobileNo );
		Member member=(Member) query.uniqueResult();
		return member;
	}
	
	

}
