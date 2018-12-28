package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.revature.pojo.Rtype;
import com.revature.pojo.Users;
import com.revature.util.ConnectionFactory;

import oracle.jdbc.internal.OracleTypes;

public class UserDao implements Dao<Users, Integer> {

	@Override
	public List<Users> findAll() {
		List<Users> users = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "{ call GET_ALL_USERS(?) }";
			
			CallableStatement cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.execute();
			
			ResultSet rs = (ResultSet) cs.getObject(1);
			
			while(rs.next()) {
				Users temp = new Users();
				temp.setId(rs.getInt("ERS_USERS_ID"));
				temp.setUsername(rs.getString("ERS_USERNAME"));
				temp.setPassword(rs.getString("ERS_PASSWORD"));
				temp.setFirstName(rs.getString("USER_FIRST_NAME"));
				temp.setLastName(rs.getString("USER_LAST_NAME"));
				temp.setEmail(rs.getString("USER_EMAIL"));
				temp.setUserRoleId(rs.getInt("USER_ROLE_ID"));
				users.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public Users findById(Integer id) {
		Users u = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM ERS_USERS WHERE ERS_USERS_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u = new Users();
				u.setId(rs.getInt(1));
				u.setUsername(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setFirstName(rs.getString(4));
				u.setLastName(rs.getString(5));
				u.setEmail(rs.getString(6));
				u.setUserRoleId(rs.getInt(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public Users save(Users obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			String sql = "INSERT INTO ERS_USERS ( ERS_USERNAME, ERS_PASSWORD"
					+ "USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, USER_ROLE_ID)"
					+ "VALUES(?,?,?,?,?,?)";
			String[] keyNames = {"ERS_USERS_ID"};
			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			

			ps.setString(1, obj.getUsername());
			ps.setString(2, obj.getPassword());
			ps.setString(3, obj.getFirstName());
			ps.setString(4, obj.getLastName());
			ps.setString(5, obj.getEmail());
			ps.setInt(6, obj.getUserRoleId());
			

			int numRows = ps.executeUpdate();//error
			
			if(numRows > 0) {
				ResultSet pk = ps.getGeneratedKeys();
				while(pk.next()) {
					obj.setUsername(pk.getString(1));
					obj.setPassword(pk.getString(2));
					obj.setFirstName(pk.getString(3));
					obj.setLastName(pk.getString(4));
					obj.setEmail(pk.getString(5));
					obj.setUserRoleId(pk.getInt(6));
			
				}
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Users update(Users obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "UPDATE ERS_USERS SET ERS_USERNAME = ?, ERS_PASSWORD = ?, "
					+ "USER_FIRST_NAME = ?, USER_LAST_NAME = ?, USER_EMAIL = ? "
					+ ", USER_ROLE_ID = ? WHERE ERS_USERS_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
		
			ps.setString(1, obj.getUsername());
			ps.setString(2, obj.getPassword());
			ps.setString(3, obj.getFirstName());
			ps.setString(4, obj.getLastName());
			ps.setString(5, obj.getEmail());
			ps.setInt(6, obj.getUserRoleId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(Users obj) {
		// TODO Auto-generated method stub
		
	}

}