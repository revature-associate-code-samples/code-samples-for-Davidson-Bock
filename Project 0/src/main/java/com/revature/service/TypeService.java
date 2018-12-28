package com.revature.service;

import java.util.List;

import com.revature.dao.AccountTypeDao;
import com.revature.dao.Dao;
import com.revature.dao.UserDao;
import com.revature.pojo.AccountType;
import com.revature.pojo.User;

public class TypeService {
static Dao<AccountType, Integer> typeDao = new AccountTypeDao();
	
	public List<AccountType> getAllTypes(){
		List<AccountType> types = typeDao.findAll();
		if(types.size() ==0 ) return null;
		return types;
	}


}
