package com.revature.service;

import java.util.List;

import com.revature.dao.Dao;
import com.revature.dao.UserRoleDao;
import com.revature.pojo.Rtype;
import com.revature.pojo.UserRole;

public class RoleService {
	
static Dao<UserRole, Integer> roleDao = new UserRoleDao();
	
	public List<UserRole> getAllRoles(){
		List<UserRole> roles = roleDao.findAll();
		if(roles.size() ==0 ) return null;
		return roles;
	}
	
	
	public UserRole getById(int id) {
		List<UserRole> role = getAllRoles();
		//could do enhanced for loop...
		for(UserRole r: role) {
			if(r.getId() == id) return r;
		}	
		return  null;
	}
	
	public UserRole addRole(int id, String role) {
		if(id == 0) return null;
		else return roleDao.save(new UserRole(id, role));
	}

}