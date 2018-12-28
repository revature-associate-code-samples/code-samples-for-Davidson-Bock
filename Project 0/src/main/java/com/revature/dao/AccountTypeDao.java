package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.Account;
import com.revature.pojo.AccountType;
import com.revature.util.ConnectionFactory;

public class AccountTypeDao implements Dao<AccountType, Integer>{

	@Override
	public List<AccountType> findAll() {
		List<AccountType> types = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from AccountType order by TypeId";
			
			Statement statement = conn.createStatement();
			
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				AccountType temp = new AccountType();
				temp.setId(rs.getInt(1));
				temp.setType(rs.getString(2));
				types.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return types;
	}

	@Override
	public AccountType findById(Integer id) {
		AccountType t = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select * from AccountType where TypeId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				t = new AccountType();
				t.setId(rs.getInt(1));
				t.setType(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public AccountType save(AccountType obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			String sql = "INSERT INTO AccountType (TypeId, BankType) VALUES(?, ?)";
			String[] keyNames = {"TypeId", "BankType"};

			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			ps.setInt(1, obj.getId());
			ps.setString(2, obj.getType());

			int numRows = ps.executeUpdate();

			if(numRows > 0) {
				ResultSet pk = ps.getGeneratedKeys();
				while(pk.next()) {
					obj.setId(pk.getInt(1));
					obj.setType(pk.getString(2));
				}
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public AccountType update(AccountType obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "update AccountType set BankType = ? where TypeId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
	
			ps.setString(1, obj.getType());
			ps.setInt(2, obj.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(AccountType obj) {
		// TODO Auto-generated method stub
		
	}

}
