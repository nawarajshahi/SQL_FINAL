package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import entity.Volunteers;

public class VolunteersDao {
	private final String GET_VOLUNTEERS_QUERY = "SELECT * FROM volunteers LIMIT 20";
	private final String GET_VOLUNTEER_BY_ID = "SELECT * FROM volunteers WHERE vol_id = ?";
	private final String CREATE_NEW_VOLUNTEER_QUERY = "INSERT INTO volunteers(vol_id, org_id, full_name, phone) VALUES (?, ?, ?, ?)";
	private final String DELETE_VOLUNTEER_BY_ID_QUERY = "DELETE FROM volunteers WHERE vol_id = ?";
	private final String DELETE_VOLUNTEERS_BY_TEAM_ID_QUERY = "DELETE FROM members WHERE team_id = ?"; //Renee
	private final String UPDATE_VOLUNTEER_BY_ID_QUERY = "UPDATE volunteers SET org_id=?, full_name=?, phone=? WHERE vol_id=?";
	
			
	private Connection connection;
	
	//no args constructor with connection
	public VolunteersDao() {
		connection = DBConnection.getConnection();
	}
	
	//implementation for getVolunteers() method which returns LIMIT of 20 volunteers
	public List<Volunteers> getVolunteers() throws SQLException{
		ResultSet rs = connection.prepareStatement(GET_VOLUNTEERS_QUERY).executeQuery();
		List<Volunteers> volunteers = new ArrayList<Volunteers>();
		
		while(rs.next()) {
			volunteers.add(new Volunteers(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
		}
		return volunteers;
		
	}
	
	
	//implementation for getAVolunteerById() method 
	public List<Volunteers> getAVolunteerById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_VOLUNTEER_BY_ID);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		List<Volunteers> volunteer = new ArrayList<Volunteers>();
		while(rs.next()) {
			volunteer.add(new Volunteers(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
		}
		
		return volunteer;
		
	}
	
	//addNewVolunteer() method implementation
	public void addNewVolunteer(int vol_id, int org_id, String full_name, String phone) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_VOLUNTEER_QUERY);
		ps.setInt(1, vol_id);
		ps.setInt(2, org_id);
		ps.setString(3, full_name);
		ps.setString(4, phone);
		ps.executeUpdate();
	}
	
	
	//createVolunteer() method implementation
	public void createVolunteer(int vol_id, int org_id, String full_name, String phone) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_VOLUNTEER_QUERY);
		ps.setInt(1, vol_id);
		ps.setInt(2, org_id);
		ps.setString(3, full_name);
		ps.setString(4, phone);
		ps.executeUpdate();
	}
	
	//deleteVolunteerById() method implementation
	public int deleteVolunteerById(int vol_id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_VOLUNTEER_BY_ID_QUERY);
		ps.setInt(1, vol_id);
		return ps.executeUpdate();
		
	}
	
	//Renee
	public void deleteVolunteersByOrgId(int org_id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_VOLUNTEERS_BY_TEAM_ID_QUERY);
		ps.setInt(1, org_id);
		ps.executeUpdate();
	}
	
	//updateVolunteer() implementation
	public void updateVolunteer(int vol_id, int org_id, String full_name, String phone) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_VOLUNTEER_BY_ID_QUERY);
		ps.setInt(1, org_id);
		ps.setString(2, full_name);
		ps.setString(3, phone);
		ps.setInt(4, vol_id);
		ps.executeUpdate();
	}
	
	
}
