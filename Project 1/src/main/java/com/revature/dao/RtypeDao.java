package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.Rtype;
import com.revature.pojo.Users;
import com.revature.util.ConnectionFactory;

import oracle.jdbc.internal.OracleTypes;

public class RtypeDao implements Dao<Rtype, Integer> {

	@Override
	public List<Rtype> findAll() {
		List<Rtype> types = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String query = "SELECT * FROM ERS_REIMBURSMENT_TYPE ORDER BY REIMB_TYPE_ID";
			
			Statement statement = conn.createStatement();
			
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				Rtype temp = new Rtype();
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
	public Rtype findById(Integer id) {
		Rtype r = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM ERS_REIMBURSMENT_TYPE WHERE ERS_TYPE_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				r = new Rtype();
				r.setId(rs.getInt(1));
				r.setType(rs.getString(2));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public Rtype save(Rtype obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			String sql = "INSERT INTO ERS_REIMBURSMENT_TYPE (REIMB_TYPE_ID, REIMB_TYPE)"
					+ "VALUES(?,?)";
			String[] keyNames = {"REIMB_TYPE_ID", "REIMB_TYPE"};
			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			
			ps.setInt(1, obj.getId());
			ps.setString(2, obj.getType());

			int numRows = ps.executeUpdate();//error
			
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
	public Rtype update(Rtype obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "UPDATE ERS_REIMBURMENT_TYPE SET REIMB_TYPE = ?"
					+ " WHERE REIB_TYPE_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
		
			ps.setString(1, obj.getType());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(Rtype obj) {
		// TODO Auto-generated method stub
		
	}

}