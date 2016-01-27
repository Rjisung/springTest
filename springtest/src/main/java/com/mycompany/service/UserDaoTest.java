package com.mycompany.service;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import com.mycompany.DAO.DaoFactory;
import com.mycompany.DAO.UserDao;
import com.mycompany.user.User;

import static org.hamcrest.CoreMatchers.*;  // is, notNullValue등 사용 가능
import static org.junit.Assert.*;   // assertThat, fail 등 사용 가능

public class UserDaoTest{
	private UserDao dao;
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
	@Before //테스트 메소드를 실행하기 전에 먼저 실행하는 메소드
	public void setUp(){
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		this.dao = context.getBean("userDao",UserDao.class);
	}
	
	
	@Test
	public void addAndGet() throws SQLException,ClassNotFoundException{
		
		User user1 = new User("gyumee","박성철","springno1");
		User user2 = new User("leegw700","이길원","springno2");
		
		
		dao.deleteAll();
		assertThat(dao.getCount(),is(0));
		
		/*User user = new User();
		user.setId("gyumee");
		user.setName("박성철");
		user.setPassword("springno1");*/
		
		dao.add(user1);
		dao.add(user2);
		assertThat(dao.getCount(),is(2));
		
		User userget1= dao.get(user1.getId());
		//변경
		assertThat(user1.getName(),is(user1.getName()));
		assertThat(user1.getPassword(),is(user1.getPassword()));
		
		User userget2= dao.get(user2.getId());
		//변경
		assertThat(user2.getName(),is(user2.getName()));
		assertThat(user2.getPassword(),is(user2.getPassword()));
		
	}
	
	@Test
	public void count() throws SQLException,ClassNotFoundException{
		
		User user1 = new User("gyumee","박성철","springno1");
		User user2 = new User("leegw700","이길원","springno2");
		User user3 = new User("bujin","박범진","springno3");
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		assertThat(dao.getCount(),is(1));
		
		dao.add(user2);
		assertThat(dao.getCount(),is(2));
		
		dao.add(user3);
		assertThat(dao.getCount(),is(3));
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException, ClassNotFoundException{
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.get("unknown_id");
	}
	
	public static void main(String[] args) {
		JUnitCore.main("com.mycompany.service");		
}
}
