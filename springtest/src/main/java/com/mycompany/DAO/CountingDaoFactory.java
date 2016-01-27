package com.mycompany.DAO;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mycompany.inter.ConnectionMaker;

@Configuration
public class CountingDaoFactory {
	/*@Bean
	public UserDao userDao(){
		return new UserDao(connectionMaker());
	}*/
	@Bean
	public ConnectionMaker connectionMaker() {
		// TODO Auto-generated method stub
		return new CountingConnectionMaker(realConnectionMaker());
	}
	@Bean
	public ConnectionMaker realConnectionMaker() {
		// TODO Auto-generated method stub
		
		return new DConnectionMaker();
	}
	
}
