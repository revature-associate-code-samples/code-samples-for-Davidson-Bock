package com.revature.service;

import java.util.List;

import com.revature.dao.Dao;
import com.revature.dao.UserDao;
import com.revature.pojo.User;


public class UserService {
static Dao<User, Integer> userDao = new UserDao();
	
	public List<User> getAllUsers(){
		List<User> users = userDao.findAll();
		if(users.size() ==0 ) return null;
		return users;
	}

}
