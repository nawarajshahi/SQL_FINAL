package dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import entity.Projects;


public class ProjectsDao {
	
	private Scanner keyboard = new Scanner(System.in);
	public static Object scanner;
	private Connection connection;

	private final String GET_PROJ_QUERY = "SELECT * FROM projects";
	private final String GET_PROJ_BY_ID_QUERY = "SELECT * FROM projects WHERE proj_id = ?";
	private final String ADD_NEW_PROJ_QUERY = "INSERT INTO projects(org_id, proj_name, proj_desc) VALUES (?,?,?)";
	private final String UPDATE_PROJ_BY_ID_QUERY = "UPDATE projects SET name = ?, address = ?, phone = ? WHERE proj_id = ?";
	private final String DELETE_PROJ_BY_ID_QUERY = "DELETE FROM projects WHERE proj_id = ?";
	
	public ProjectsDao() {
		connection = DBConnection.getConnection();

	}
	
	
	public List<Projects> getProjs() throws SQLException{
		ResultSet rs = connection.prepareStatement(GET_PROJ_QUERY).executeQuery();
		List<Projects> Projects = new ArrayList<Projects>();
		
		while(rs.next()) {
			Projects.add(populateProjects(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
		}
		
		return Projects;
	}
	
	public Projects getProjById(int proj_id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_PROJ_BY_ID_QUERY);
		ps.setInt(1, proj_id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateProjects(rs.getInt(1), rs.getInt(2),rs.getString(3), rs.getString(4));
	
	}
	
	public void addNewProj(int org_id, String proj_name, String proj_desc) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(ADD_NEW_PROJ_QUERY);
		ps.setInt(1, org_id);
		ps.setString(2, proj_name);
		ps.setString(3, proj_desc);
		ps.executeUpdate();
	}

	
	public void deleteProjectsByProjId(int proj_id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_PROJ_BY_ID_QUERY);
		ps.setInt(1, proj_id);
		ps.executeUpdate();
	}
	
	private Projects populateProjects(int proj_id, int org_id, String proj_name, String proj_desc) throws SQLException {
		return new Projects(proj_id, org_id, proj_name, proj_desc);
	}


	public List<Projects> getProj() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_PROJ_QUERY).executeQuery();
		List<Projects> project = new ArrayList<Projects>();
		
		while (rs.next()) {
			project.add(populateProjects(rs.getInt(1), rs.getInt(2),  rs.getString(3),  rs.getString(4)));
		}
		
		return project;
		
	}
	
	
	
	public void getProjects() {
	
	ArrayList<Projects> projects = new ArrayList<Projects>();
	
	
	try {
			String sql = "SELECT proj_id, org_id, proj_name, proj_desc FROM volunteers.projects;";
			Connection connection = DBConnection.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				
				projects.add(new Projects(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
				
			}
			
			
					
			}
		 catch (Exception e) {
			e.printStackTrace();
			
		}
	
	System.out.println(String.format("%-20s %12s %16s %24s", "Project ID", "Org ID", "Project Name", "Project Description"));
	for(Projects p: projects)
	{
		System.out.println(String.format("%-20s %12s %16s %24s",p.getProj_id(), p.getOrg_id(), p.getProj_name(), p.getProj_desc()));
	}
		
	}


	private Projects populateProject(int id, int orgid, String Projname, String Projdesc) throws SQLException {
		return new Projects(id, orgid, Projname, Projdesc);
	}


	public boolean updateProject() throws SQLException
	{
		boolean _success = false;
		System.out.print("Enter Project ID: ");
		int projectID = keyboard.nextInt();

		Projects _project = getProjById(projectID);

		System.out.print("Update Organization ID: ");
		int OrganizationId = keyboard.nextInt();
		_project.setOrg_id(OrganizationId);
		System.out.print("Update Project Name: ");
		keyboard.nextLine();
		String ProjectsName = keyboard.nextLine();
		_project.setProj_name(ProjectsName);
		System.out.print("Update Project Description: ");
		//keyboard.nextLine();
		String ProjectsDescription = keyboard.nextLine();
		_project.setProj_desc(ProjectsDescription);
		
		try {
					String sql = "UPDATE volunteers.projects set org_id=?, proj_name=?, proj_desc =? WHERE proj_id =?";
					Connection connection = DBConnection.getConnection();
					
					PreparedStatement statement = connection.prepareStatement(sql);
					
					statement.setInt(1, _project.getOrg_id());
					statement.setString(2, _project.getProj_name());
					statement.setString(3, _project.getProj_desc());
					statement.setInt(4, projectID);
					
					int updated = statement.executeUpdate();
					
					_success = updated >=1;
					
							
					}
				 catch (Exception e) {
					e.printStackTrace();
					
				}
		
		if(_success)
			System.out.println("Project has been updated");	
		else
			System.out.println("Project has NOT been updated");
		
		return _success;
	}
		
	

		
	public Projects getProject(int id)
	{
		Projects _project = new Projects();
		try {
			Connection connection = DBConnection.getConnection();
			
			String sql = "SELECT proj_id, org_id, proj_name, proj_desc FROM volunteers.projects where proj_id =?;";		
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				
				_project = (new Projects(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
				
			}
			
			
					
			}
		 catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return _project;
		
	}
	
		
		


}
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	

