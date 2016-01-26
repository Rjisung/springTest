package com.mycompany.service;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mycompany.DAO.DaoFactory;
import com.mycompany.DAO.UserDao;
import com.mycompany.user.User;
import static org.hamcrest.CoreMatchers.*;  // is, notNullValue등 사용 가능
import static org.junit.Assert.*;   // assertThat, fail 등 사용 가능

public class UserDaoTest {
	
	/*public static void main(String[] args) throws ClassNotFoundException,SQLException{
		
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = context.getBean("userDao",UserDao.class);
					
		User user = new User();
		user.setId("whiteship");
		user.setName("백기선");
		user.setPassword("married");
		
		dao.add(user);
		
		System.out.println(user.getId()+"등록 성공");	
		User user2= dao.get(user.getId());
		
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
		System.out.println(user2.getId()+"조회 성공");
		
		if(!user.getName().equals(user2.getName())){
			System.out.println("테스트 실패 (name)");
		}
		else if (!user.getPassword().equals(user2.getPassword())){
			System.out.println("테스트 실패 (password");
		}
		else {
			System.out.println("조회 테스트 성공");
		}
	}*/
	
	@Test
	public void addAndGet() throws SQLException,ClassNotFoundException{
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = context.getBean("userDao",UserDao.class);
		
		User user = new User();
		user.setId("whiteship");
		user.setName("백기선");
		user.setPassword("married");
		
		dao.add(user);
		
		User user2= dao.get(user.getId());
		
		assertThat(user2.getName(),is(user.getName()));
		assertThat(user2.getPassword(),is(user.getPassword()));
	}
}
