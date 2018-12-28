package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.Dao;
import com.revature.dao.UserDao;

import com.revature.pojo.Users;


public class UserService {
	
	static Dao<Users, Integer> userDao = new UserDao();
	static ArrayList<Users> users = new ArrayList<Users>();
	
	public List<Users> getAllUsers(){
		List<Users> users = userDao.findAll();
		if(users.size() ==0 ) return null;
		return users;
	}
	
	
	
	//Users u = userDao.save(new Users( "dbock11994", "123", "David", "Bock", "dbock1994@gmail.com", 2));
	
	
	public ArrayList<Users> getUsers(){
		
		return users;
	}
	
	
	public Users getByUsername(String name) {
		List<Users> users =userDao.findAll();
		System.out.println("Users: " + users);
		//could do enhanced for loop...
		if(users == null) {
			return null;
		}
		for(Users u: users) {
			if(u.getUsername().equals(name)) return u;
		}	
		return  null;
	}
	
	public Users validiateUser(String name, String password) {
		System.out.println("Testing valid1: " + name );
		System.out.println("Sleepy: " + userDao.findAll());
		Users u = getByUsername(name);
		System.out.println("Testing valid2: " + u);
		if(u == null) return null;
		else if(u.getPassword().equals(password)) return u; //only case where user is logged in
		else {
			return null;
		}
	}
	

	
	public Users validiateNewUser(String name) throws NullPointerException {
		Users u = new Users();
		u.setUsername(name);
		System.out.println(u);
		System.out.println(u.getUsername());
		boolean b = isUnique(u.getUsername());
		if(b) {
			return u;
		}
		else {
			return null;
		}
	
		
		
	}

	
	public Users addUser(int id, String username, String password, String firstName, 
			String lastName, String email, int roleId) {
		if(username == null) return null;
		else return userDao.save(new Users(username, password, firstName, lastName,
				email, roleId));
	}

	public boolean isUnique(String name) throws NullPointerException {
		List<Users> users = userDao.findAll();
		System.out.println("isUnique: " + name);
		if(users == null) {
			return true;
		}
		for(Users u : users ) {
			if(name.equals(u.getUsername())) {
				return false;
			}
		}
		return true;
			
	}
	
	public Users getThatUser(String name) {
		Users u = new Users();
		u.setUsername(name);
		System.out.println(u.getUsername());
		return u;
	}
	
	public Users getAccountInfo(String username, String password, String firstName, String lastName, String email, int role) {
		Users u = new Users();
		u.setUsername(username);
		u.setPassword(password);
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setEmail(email);
		u.setUserRoleId(role);
		System.out.println("1 Test: " +  u);
		return u;
		
		
	}
	


}