package com.revature.service;

import java.util.List;

import com.revature.dao.Dao;
import com.revature.dao.ReimbursmentDao;
import com.revature.dao.RtypeDao;
import com.revature.pojo.Reimbursment;
import com.revature.pojo.Rtype;

public class RtypeService {
	
	static Dao<Rtype, Integer> typeDao = new RtypeDao();
	
	public List<Rtype> getAllRtypes(){
		List<Rtype> types = typeDao.findAll();
		if(types.size() ==0 ) return null;
		return types;
	}
	
	
	public Rtype getById(int id) {
		List<Rtype> type = getAllRtypes();
		//could do enhanced for loop...
		for(Rtype r: type) {
			if(r.getId() == id) return r;
		}	
		return  null;
	}
	
	public Rtype addType(int id, String type) {
		if(id == 0) return null;
		else return typeDao.save(new Rtype(id, type));
	}

}