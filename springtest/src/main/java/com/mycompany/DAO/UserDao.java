package com.mycompany.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.runner.JUnitCore;
import org.springframework.dao.EmptyResultDataAccessException;

import com.mycompany.user.User;

public class UserDao {
	
	private static UserDao INSTANCE;
	
	//private ConnectionMaker connectionMaker;
	private DataSource dataSource;
	private Connection c;
	private User user;
	
	/*public UserDao(ConnectionMaker connectionMaker) {
		// TODO Auto-generated constructor stub
		this.connectionMaker=connectionMaker;
	}*/
	


	public static synchronized UserDao getInstance(){
		if(INSTANCE == null) INSTANCE = new UserDao();
		return INSTANCE;
	}
	
	/*public void setConnectionMaker(ConnectionMaker connectionMaker){
		this.connectionMaker=connectionMaker;
	}*/

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public UserDao() {
		// TODO Auto-generated constructor stub
	}
	
	public void add(User user) throws ClassNotFoundException,SQLException {
		//Connection c = connectionMaker.makeConnection();
		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());

		ps.executeUpdate();

		ps.close();
		c.close();
	}

	public User get(String id) throws ClassNotFoundException,SQLException {
		//this.c = connectionMaker.makeConnection();
		this.c = dataSource.getConnection();
		
		PreparedStatement ps = c
				.prepareStatement("select * from users where id = ?");
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
		/*this.user = new User();
		this.user.setId(rs.getString("id"));
		this.user.setName(rs.getString("name"));
		this.user.setPassword(rs.getString("password"));*/
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}
		rs.close();
		ps.close();
		c.close();
		
		if(user ==null)throw new EmptyResultDataAccessException(1);
		return this.user;
	}
	
	public void deleteAll() throws SQLException{
		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement("delete from users");
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	public int getCount() throws SQLException{
		Connection c = dataSource.getConnection();
		PreparedStatement ps = c.prepareStatement("select count(*) from users");
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count= rs.getInt(1);
		
		rs.close();
		ps.close();
		c.close();
		
		return count;
	}
}
