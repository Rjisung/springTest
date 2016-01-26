package com.mycompany.DAO;

import com.mycompany.inter.ConnectionMaker;

public class AccountDao {
	
	ConnectionMaker connectionMaker;
	
	public AccountDao(ConnectionMaker connectionMaker) {
		// TODO Auto-generated constructor stub
		this.connectionMaker=connectionMaker;
	}
}
