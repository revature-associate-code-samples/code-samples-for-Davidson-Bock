package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.Status;
import com.revature.pojo.Users;
import com.revature.util.ConnectionFactory;

public class StatusDao implements Dao<Status, Integer> {

	@Override
	public List<Status> findAll() {
		List<Status> status = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String query = "SELECT * FROM ERS_REIMBURSMENT_STATUS ORDER BY REIMB_STATUS_ID";
			
			Statement statement = conn.createStatement();
			
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				Status temp = new Status();
				temp.setId(rs.getInt(1));
				temp.setStatus(rs.getString(2));
				status.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Status findById(Integer id) {
		Status s = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM ERS_REIMBURSMENT_STATUS WHERE REIMB_STATUS_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				s = new Status();
				s.setId(rs.getInt(1));
				s.setStatus(rs.getString(2));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public Status save(Status obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			String sql = "INSERT INTO ERS_REIMBURSMENT_STATUS (REIMB_STATUS_ID, REIMB_STATUS)"
					+ "VALUES(?,?)";
			String[] keyNames = {"REIMB_STATUS_ID", "REIMB_STATUS"};
			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			
			ps.setInt(1, obj.getId());
			ps.setString(2, obj.getStatus());

			int numRows = ps.executeUpdate();//error
			
			if(numRows > 0) {
				ResultSet pk = ps.getGeneratedKeys();
				while(pk.next()) {
					obj.setId(pk.getInt(1));
					obj.setStatus(pk.getString(2));			
				}
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Status update(Status obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "UPDATE ERS_REIMBURSMENT_STATUS SET REIMB_STATUS = ?"
					+ " WHERE REIMB_STATUS_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, obj.getStatus());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(Status obj) {
		// TODO Auto-generated method stub
		
	}

	

}