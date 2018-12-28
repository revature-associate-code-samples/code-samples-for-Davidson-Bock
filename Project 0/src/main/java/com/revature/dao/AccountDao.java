package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.Account;
import com.revature.pojo.User;
import com.revature.util.ConnectionFactory;

public class AccountDao implements Dao<Account, Integer>{

	
	@Override
	public List<Account> findAll() {
		List<Account> accounts = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from UserAccount order by AccountId";
			
			Statement statement = conn.createStatement();
			
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				Account temp = new Account();
				temp.setId(rs.getInt(1));
				temp.setType(rs.getInt(2));
				temp.setUser(rs.getInt(3));
				temp.setBalance(rs.getInt(4));
				accounts.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}
	
	
	
	@Override
	public Account findById(Integer id) {
		Account a = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select * from UserAccount where AccountId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				a = new Account();
				a.setId(rs.getInt(1));
				a.setType(rs.getInt(2));
				a.setUser(rs.getInt(3));
				a.setBalance(rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	
	
	
	

	@Override
	public Account save(Account obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			String sql = "INSERT INTO UserAccount (AccountId, AccountType, OwnerAccount, "
					+ "Balance) VALUES(?, ?, ?, ?)";
			String[] keyNames = {"AccountId", "AccountType", "OwnerAccount", "Balance"};
			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			
			ps.setInt(1, obj.getId());
			ps.setInt(2, obj.getType());
			ps.setInt(3, obj.getUser());
			ps.setInt(4, obj.getBalance());
			

			int numRows = ps.executeUpdate();//error
			
			if(numRows > 0) {
				ResultSet pk = ps.getGeneratedKeys();
				while(pk.next()) {
					obj.setType(pk.getInt(1));
					obj.setUser(pk.getInt(2));
					obj.setBalance(pk.getInt(3));
				}
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Account update(Account obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "update UserAccount set Balance = ? where AccountId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
		
			ps.setInt(1, obj.getBalance());
			ps.setInt(2, obj.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(Account obj) {
		// TODO Auto-generated method stub
		
	}

}
