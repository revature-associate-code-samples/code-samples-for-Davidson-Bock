package com.revature.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.revature.dao.Dao;
import com.revature.dao.ReimbursmentDao;
import com.revature.dao.UserDao;
import com.revature.pojo.Reimbursment;
import com.revature.pojo.Users;

public class ReimbursmentService {
	
static Dao<Reimbursment, Integer> reimbDao = new ReimbursmentDao();
	
	public List<Reimbursment> getAllReimbursments(){
		List<Reimbursment> reimbs = reimbDao.findAll();
		if(reimbs.size() ==0 ) return null;
		return reimbs;
	}
	
	
	public Reimbursment getById(int id) {
		List<Reimbursment> reimb = getAllReimbursments();
		//could do enhanced for loop...
		for(Reimbursment r: reimb) {
			if(r.getId() == id) return r;
		}	
		return  null;
	}
	
	public Reimbursment addReimbursment(int amount, String date2, String resolved,
			String description, int author, int resolver, int statusId, int typeId) {
		Reimbursment reimb = new Reimbursment();
		reimb.setAmount(amount);
		java.util.Date date = java.sql.Date.valueOf(date2);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String sub = df.format(date);
		reimb.setSubmitted(sub);
		reimb.setDescription(description);
		reimb.setStatusId(1);
		reimb.setTypeId(typeId);
		reimbDao.save(reimb);
		System.out.println(reimb);
		return reimb;
		
		
	}




}