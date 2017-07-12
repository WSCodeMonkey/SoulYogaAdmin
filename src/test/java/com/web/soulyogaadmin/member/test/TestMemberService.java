package com.web.soulyogaadmin.member.test;

import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.web.soulyogaadmin.entity.Employee;
import com.web.soulyogaadmin.entity.Member;
import com.web.soulyogaadmin.entity.Memberaccount;
import com.web.soulyogaadmin.member.dao.IMemberDao;
import com.web.soulyogaadmin.member.service.IMemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml","classpath:spring-hibernate.xml"})
public class TestMemberService {

	@Autowired
	private IMemberDao iMemberDao;
	
	@Autowired
	private IMemberService iMemberService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	
	@Test
	public void test1() throws Exception {

		List<Employee> list=iMemberService.consultantList();
		System.out.println(list);
		
		
	
	}
	@Test
	public void test2() throws Exception {
		Member member=new Member();
		member.setName("Test06");
		member.setMobileNo("11111");
		member.setSalesConsultantId(1);
		member.setAddress("shanghai");
		Date date=new Date();
		member.setCreatedTime(date);
		member.setState(0);
		Memberaccount mc=new Memberaccount();
		mc.setNickName("nick");
		mc.setPassword("123");
		byte[] bt={1};
	    mc.setFingerprint(bt);
		mc.setCreatedTime(new Date());
		mc.setState(0);
		iMemberService.addMemberInfo(member, mc);
		
	}
	@Ignore
	@Test
	public void test3() throws Exception {
		Member member=new Member();
		member.setName("Test02");
		member.setMobileNo("123456789");
		member.setSalesConsultantId(1);
		member.setAddress("shanghai");
		Date date=new Date();
		member.setCreatedTime(date);
		member.setState(0);
		iMemberDao.addMember(member);
		
	}
	@Ignore
	@Test
	public void test4() throws Exception {
		Member member=new Member();
		member.setId(1);
		member.setName("tonnyTest");
		member.setMobileNo("11111");
		member.setSalesConsultantId(1);
		member.setAddress("shanghai");
		Date date=new Date();
		member.setCreatedTime(date);
		member.setState(0);
		Memberaccount mc=new Memberaccount();
		mc.setNickName("tonny");
		mc.setPassword("123");
		byte[] bt={1};
	    mc.setFingerprint(bt);
		mc.setCreatedTime(new Date());
		mc.setState(0);
		
		
	}
	@Test
	public void test5() throws Exception {
	
		List<Member> list=iMemberDao.showAllMember();
		System.out.println(list);
	}
	@Test
	public void test6() throws Exception {
	
		iMemberDao.delMember(1);
		List<Member> list=iMemberDao.showAllMember();
		System.out.println(list);
	}
	@Test
	public void test7() throws Exception {
		
		
		List<Member> list=iMemberDao.fuzzyFind("热");
		System.out.println(list);
	}
	@Test
	public void test8() throws Exception {
	
		List<Member> list=iMemberDao.queryByidentityId("3408");
		System.out.println(list.isEmpty());
	}
}
