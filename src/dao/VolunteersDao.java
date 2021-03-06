package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Volunteers;

/**
 * Authored by Nawaraj Shahi
 */

public class VolunteersDao {

	private final String GET_VOLUNTEERS_QUERY = "SELECT * FROM volunteers LIMIT 20";
	private final String GET_VOLUNTEER_BY_ID_QUERY = "SELECT * FROM volunteers WHERE vol_id = ?";
	private final String CREATE_NEW_VOLUNTEER_QUERY = "INSERT INTO volunteers(org_id, full_name, phone) VALUES (?, ?, ?)";
	private final String DELETE_VOLUNTEER_BY_ID_QUERY = "DELETE FROM volunteers WHERE vol_id = ?";
	private final String DELETE_VOLUNTEERS_BY_TEAM_ID_QUERY = "DELETE FROM volunteers WHERE org_id = ?"; //Renee
	private final String UPDATE_VOLUNTEER_BY_ID_QUERY = "UPDATE volunteers SET org_id=?, full_name=?, phone=? WHERE vol_id=?";

	
			
	private Connection connection;

	/** no args constructor with connection */
	public VolunteersDao() {
		connection = DBConnection.getConnection();
	}

	/**
	 * getVolunteers() method which returns LIMIT of 20 volunteers
	 * @return 	List of volunteers in the given database
	 * @throws SQLException
	 */
	public List<Volunteers> getVolunteers() throws SQLException{
		ResultSet rs = connection.prepareStatement(GET_VOLUNTEERS_QUERY).executeQuery();
		List<Volunteers> volunteers = new ArrayList<Volunteers>();
		
		while(rs.next()) {
			volunteers.add(new Volunteers(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
		}
		return volunteers;
	}


	/**
	 * implementation for getAVolunteerById() method
	 * @param vol_id 	volunteer id in the database
	 * @return		List of volunteer with provided vol_id
	 * @throws SQLException
	 */
	public List<Volunteers> getAVolunteerById(int vol_id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_VOLUNTEER_BY_ID_QUERY);
		ps.setInt(1, vol_id);
		
		ResultSet rs = ps.executeQuery();
		List<Volunteers> volunteer = new ArrayList<Volunteers>();
		while(rs.next()) {
			volunteer.add(new Volunteers(rs.getInt(1), rs.getInt(2), rs.getString(3),
					rs.getString(4)));
		}
		return volunteer;
	}


	/**
	 * createVolunteer() method implementation
	 * @param org_id 	the organization id to which the volunteer is part of
	 * @param full_name 	full name of the volunteer
	 * @param phone 	phone of the volunteer
	 * @return			an int count of number of rows that were created or 0 for no addition
	 * @throws SQLException
	 */
	public int createVolunteer(int org_id, String full_name, String phone) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_VOLUNTEER_QUERY);
		ps.setInt(1, org_id);
		ps.setString(2, full_name);
		ps.setString(3, phone);
		return ps.executeUpdate();
	}


	/**
	 * deleteVolunteerById() method implementation
	 * @param vol_id	volunteer id in the database
	 * @return			an int count of number of rows that were deleted or 0 for no deletion
	 * @throws SQLException
	 */
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


	/**
	 * updateVolunteer() implementation
	 * @param vol_id 	volunteer id in the database
	 * @param org_id 	the organization id to which the volunteer is part of
	 * @param full_name 	full name of the volunteer
	 * @param phone 	phone of the volunteer
	 * @return			an int count of number of rows that were updated or 0 for no update
	 * @throws SQLException
	 */
	public int updateVolunteer(int vol_id, int org_id, String full_name, String phone) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_VOLUNTEER_BY_ID_QUERY);
		ps.setInt(1, org_id);
		ps.setString(2, full_name);
		ps.setString(3, phone);
		ps.setInt(4, vol_id);
		return ps.executeUpdate();
	}
	
}
