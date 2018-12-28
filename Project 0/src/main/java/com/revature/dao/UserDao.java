package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.User;
import com.revature.util.ConnectionFactory;

import oracle.jdbc.internal.OracleTypes;

public class UserDao implements Dao<User, Integer> {

	/*@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from BankUser order by UserId";
			
			Statement statement = conn.createStatement();
			
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				User temp = new User();
				temp.setId(rs.getInt(1));
				temp.setFirstName(rs.getString(2));
				temp.setLastName(rs.getString(3));
				temp.setUserName(rs.getString(4));
				temp.setPassword(rs.getString(5));
				users.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}*/
	
	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "{ call get_all_users(?) }";
			
			CallableStatement cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.execute();
			
			ResultSet rs = (ResultSet) cs.getObject(1);
			
			while(rs.next()) {
				User temp = new User();
				temp.setId(rs.getInt("UserId"));
				temp.setFirstName(rs.getString(2));
				temp.setLastName(rs.getString(3));
				temp.setUserName(rs.getString(4));
				temp.setPassword(rs.getString(5));
				users.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User findById(Integer id) {
		User u = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select * from BankUser where UserId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u = new User();
				u.setId(rs.getInt(1));
				u.setFirstName(rs.getString(2));
				u.setLastName(rs.getString(3));
				u.setUserName(rs.getString(4));
				u.setPassword(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	
	public User findByUsername(String username, String password) {
		User u = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select * from BankUser where UserName = ? AND PassWordUser = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u = new User();
				u.setId(rs.getInt(1));
				u.setFirstName(rs.getString(2));
				u.setLastName(rs.getString(3));
				u.setUserName(rs.getString(4));
				u.setPassword(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public User save(User obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			String sql = "INSERT INTO BankUser (UserId, FirstName, LastName, UserName,"
					+ " PasswordUser) VALUES(?, ?, ?, ?, ?)";
			String[] keyNames = {"UserId", "FirstName", "LastName", "UserName", "PasswordUser"};

			PreparedStatement ps = conn.prepareStatement(sql, keyNames);

			ps.setInt(1, obj.getId());
			ps.setString(2, obj.getFirstName());
			ps.setString(3, obj.getLastName());
			ps.setString(4, obj.getUserName());
			ps.setString(5, obj.getPassword());

			int numRows = ps.executeUpdate();

			if(numRows > 0) {
				ResultSet pk = ps.getGeneratedKeys();
				while(pk.next()) {
					obj.setId(pk.getInt(1));
					obj.setFirstName(pk.getString(2));
					obj.setLastName(pk.getString(3));
					obj.setUserName(pk.getString(4));
					obj.setPassword(pk.getString(5));
				}
				conn.commit();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public User update(User obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "update BankUser set FirstName = ?, LastName = ?,"
					+ " UserName = ?, PasswordUser = ? where UserId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, obj.getFirstName());
			ps.setString(2, obj.getLastName());
			ps.setString(3, obj.getUserName());
			ps.setString(4, obj.getPassword());
			ps.setInt(5, obj.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(User obj) {
		// TODO Auto-generated method stub
		
	}

}
