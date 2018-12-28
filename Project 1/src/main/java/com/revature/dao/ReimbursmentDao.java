package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.Reimbursment;
import com.revature.util.ConnectionFactory;

public class ReimbursmentDao implements Dao<Reimbursment, Integer> {

	@Override
	public List<Reimbursment> findAll() {
		List<Reimbursment> reimbursments = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String query = "SELECT * FROM ERS_REIMBURSMENT ORDER BY REIMB_ID";
			
			Statement statement = conn.createStatement();
			
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				Reimbursment temp = new Reimbursment();
				temp.setId(rs.getInt(1));
				temp.setAmount(rs.getInt(2));
				temp.setSubmitted(rs.getString(3));
				temp.setResolved(rs.getString(4));
				temp.setDescription(rs.getString(5));
				temp.setAuthor(rs.getInt(6));
				temp.setResolver(rs.getInt(7));
				temp.setStatusId(rs.getInt(8));
				temp.setTypeId(rs.getInt(9));
				reimbursments.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbursments;
	}

	@Override
	public Reimbursment findById(Integer id) {
		Reimbursment r = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM ERS_REIMBURSMENT WHERE REIMB_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				r = new Reimbursment();
				r.setId(rs.getInt(1));
				r.setAmount(rs.getInt(2));
				r.setSubmitted(rs.getString(3));
				r.setResolved(rs.getString(4));
				r.setDescription(rs.getString(5));
				r.setAuthor(rs.getInt(6));
				r.setResolver(rs.getInt(7));
				r.setStatusId(rs.getInt(8));
				r.setTypeId(rs.getInt(9));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public Reimbursment save(Reimbursment obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			String sql = "INSERT INTO ERS_REIMBURSMENT (REIMB_ID, REIMB_AMOUNT, REIMB_SUBMITTED"
					+ "REIMB_RESOLVED, REIMB_DESCRIPTION, REIMB_AUTHOR, REIMB_RESOLVER,"
					+ " REIMB_STATUS_ID, REIMB_TYPE_ID) VALUES(?, ?,?,?,?,?,?,?,?)";
			String[] keyNames = {"REIMB_ID", "REIMB_AMOUNT", "REIMB_SUBMITTED", "REIMB_RESOLVED",
					 "REIMB_DESCRIPTION", "REIMB_AUTHOR", "REIMB_ROLVER",
					 "REIMB_STATUS_ID", "REIMB_TYPE_ID"};

			PreparedStatement ps = conn.prepareStatement(sql, keyNames);

			ps.setInt(1, obj.getId());
			ps.setInt(2, obj.getAmount());
			ps.setString(3, obj.getSubmitted());
			ps.setString(4, obj.getResolved());
			ps.setString(5, obj.getDescription());
			ps.setInt(6, obj.getAuthor());
			ps.setInt(7, obj.getResolver());
			ps.setInt(8, obj.getStatusId());
			ps.setInt(9, obj.getTypeId());
			

			int numRows = ps.executeUpdate();

			if(numRows > 0) {
				ResultSet pk = ps.getGeneratedKeys();
				while(pk.next()) {
					obj.setId(pk.getInt(1));
					obj.setAmount(pk.getInt(2));
					obj.setSubmitted(pk.getString(3));
					obj.setResolved(pk.getString(4));
					obj.setDescription(pk.getString(5));
					obj.setAuthor(pk.getInt(6));
					obj.setResolver(pk.getInt(7));
					obj.setStatusId(pk.getInt(8));
					obj.setTypeId(pk.getInt(9));
				}
				conn.commit();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Reimbursment update(Reimbursment obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "UPDATE ERS_REIMBURSMENT SET REIMB_AMOUNT = ?, REIB_SUBMITTED = ?,"
					+ "REIMB_RESOLVED = ?, REIMB_DESCRIPTION = ?, REIMB_AUTHOR = ?,"
					+ "REIMB_RESOLVER = ?, REIMB_STATUS_ID = ?, REIMB_TYPE = ?"
					+ " WHERE REIMB_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
		
			ps.setInt(1, obj.getAmount());
			ps.setString(2, obj.getSubmitted());
			ps.setString(3, obj.getResolved());
			ps.setString(4, obj.getDescription());
			ps.setInt(5, obj.getAuthor());
			ps.setInt(6, obj.getResolver());
			ps.setInt(7, obj.getStatusId());
			ps.setInt(8, obj.getTypeId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(Reimbursment obj) {
		// TODO Auto-generated method stub
		
	}

}