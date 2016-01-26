package com.mycompany.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import com.mycompany.inter.ConnectionMaker;

public class CountingConnectionMaker implements ConnectionMaker {

	int counter=0;
	private ConnectionMaker realConnectionMaker;
	
	public CountingConnectionMaker(ConnectionMaker realConnectionMaker){
		this.realConnectionMaker=realConnectionMaker;
	}
	
	@Override
	public Connection makeConnection() throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub
		this.counter++;
		return realConnectionMaker.makeConnection();
	}
	
	public int getCounter(){
		return this.counter;
	}

}