package com.revature.service;

import java.util.List;

import com.revature.dao.Dao;
import com.revature.dao.StatusDao;
import com.revature.pojo.Rtype;
import com.revature.pojo.Status;

public class StatusService {
	
	static Dao<Status, Integer> statusDao = new StatusDao();
	
	public List<Status> getAllStatus(){
		List<Status> status = statusDao.findAll();
		if(status.size() ==0 ) return null;
		return status;
	}
	
	
	public Status getById(int id) {
		List<Status> status = getAllStatus();
		//could do enhanced for loop...
		for(Status s: status) {
			if(s.getId() == id) return s;
		}	
		return  null;
	}
	
	public Status addStatus(int id, String status) {
		if(id == 0) return null;
		else return statusDao.save(new Status(id, status));
	}

}