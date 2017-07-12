package com.web.soulyogaadmin.classroom.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.web.soulyogaadmin.entity.Yogaclub;
import com.web.soulyogaadmin.space.dao.IYogaclubDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml","classpath:spring-hibernate.xml"})
public class testYogaclubDao {
	@Autowired
	IYogaclubDao yogaclubDao;
	@Test
	public void deleteyogaclub(){
     yogaclubDao.deleteYogaclubbyId(2);		
		
		
		
     
	}
	@Test
	public void addyogaclub(){
		 Yogaclub yogaclub=new Yogaclub();
		 yogaclub.setAddress("1");
		 yogaclub.setCity("asaafas");
		 yogaclub.setLinkman("asda");
		 yogaclub.setName("asdada");
		 yogaclubDao.addYogaclub(yogaclub);	
		
		
		
	}
}
