package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.Status;
import com.revature.pojo.UserRole;
import com.revature.util.ConnectionFactory;

public class UserRoleDao implements Dao<UserRole, Integer> {

	@Override
	public List<UserRole> findAll() {
		List<UserRole> roles = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String query = "SELECT * FROM ERS_USER_ROLES ORDER BY ERS_USER_ROLE_ID";
			
			Statement statement = conn.createStatement();
			
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				UserRole temp = new UserRole();
				temp.setId(rs.getInt(1));
				temp.setRole(rs.getString(2));
				roles.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;
	}

	@Override
	public UserRole findById(Integer id) {
		UserRole r = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM ERS_USER_ROLE WHERE ERS_USER_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				r = new UserRole();
				r.setId(rs.getInt(1));
				r.setRole(rs.getString(2));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public UserRole save(UserRole obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			String sql = "INSERT INTO ERS_USER_ROLES (ERS_USER_ID, USER_ROLE)"
					+ "VALUES(?,?)";
			String[] keyNames = {"ERS_USER_ID", "USER_ROLE"};
			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			
			ps.setInt(1, obj.getId());
			ps.setString(2, obj.getRole());

			int numRows = ps.executeUpdate();//error
			
			if(numRows > 0) {
				ResultSet pk = ps.getGeneratedKeys();
				while(pk.next()) {
					obj.setId(pk.getInt(1));
					obj.setRole(pk.getString(2));			
				}
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public UserRole update(UserRole obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "UPDATE ERS_USER_ROLES SET USER_ROLE = ?"
					+ " WHERE ERS_USER_ROLE = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, obj.getRole());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(UserRole obj) {
		// TODO Auto-generated method stub
		
	}

}