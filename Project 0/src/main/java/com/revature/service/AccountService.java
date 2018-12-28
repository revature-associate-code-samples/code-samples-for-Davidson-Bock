package com.revature.service;

import java.util.List;

import com.revature.dao.AccountDao;
import com.revature.dao.Dao;
import com.revature.dao.UserDao;
import com.revature.pojo.Account;
import com.revature.pojo.User;

public class AccountService {
static Dao<Account, Integer> accountDao = new AccountDao();
	
	public List<Account> getAllAccounts(){
		List<Account> accounts = accountDao.findAll();
		if(accounts.size() ==0 ) return null;
		return accounts;
	}


}
