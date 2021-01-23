package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Organizations;

public class OrganizationsDao {
	
	private Connection connection;
	private VolunteersDao volunteerDao;
	private final String GET_ORGS_QUERY = "SELECT * FROM organizations";
	private final String GET_ORG_BY_ID_QUERY = "SELECT * FROM organizations WHERE org_id = ?";
	private final String ADD_NEW_ORG_QUERY = "INSERT INTO organizations (name, address, phone) VALUES (?,?,?)";
	private final String UPDATE_ORG_BY_ID_QUERY = "UPDATE organizations SET name = ?, address = ?, phone = ? WHERE org_id = ?";
	private final String DELETE_ORG_BY_ID_QUERY = "DELETE FROM organizations WHERE org_id = ?";
	
	public OrganizationsDao() {
		connection = DBConnection.getConnection();
		volunteerDao = new VolunteersDao();
	}
	
	
	public List<Organizations> getOrgs() throws SQLException{
		ResultSet rs = connection.prepareStatement(GET_ORGS_QUERY).executeQuery();
		List<Organizations> organizations = new ArrayList<Organizations>();
		
		while(rs.next()) {
			organizations.add(populateOrganization(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		}
		
		return organizations;
	}
	
	public Organizations getOrgById(int org_id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_ORG_BY_ID_QUERY);
		ps.setInt(1, org_id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateOrganization(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4));
	}
	
	public void createNewOrg(String name, String address, String phone) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(ADD_NEW_ORG_QUERY);
		ps.setString(1, name);
		ps.setString(2, address);
		ps.setString(3, phone);
		ps.executeUpdate();
	}

	public void updateOrgById(int org_id, String name, String address, String phone) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_ORG_BY_ID_QUERY);
		ps.setString(1, name);
		ps.setString(2, address);
		ps.setString(3, phone);
		ps.setInt(4, org_id);
		ps.executeUpdate();
	}
	
	public void deleteOrgById(int org_id) throws SQLException {
		volunteerDao.deleteVolunteersByOrgId(org_id);
		PreparedStatement ps = connection.prepareStatement(DELETE_ORG_BY_ID_QUERY);
		ps.setInt(1, org_id);
		ps.executeUpdate();
		System.out.println();
	}
	

	private Organizations populateOrganization(int org_id, String name, String address, String phone) throws SQLException {
		return new Organizations(org_id, name, address, phone, volunteerDao.getAVolunteerById(org_id));
	}
	
	

}


