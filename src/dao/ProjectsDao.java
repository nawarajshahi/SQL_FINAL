package dao;
import entity.Projects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProjectsDao {
    public static Object scanner;
    private Connection connection;
    //  private ProjectsDao ProjectsDao;
    private final String GET_PROJ_QUERY = "SELECT * FROM projects";
    private final String GET_PROJ_BY_ID_QUERY = "SELECT * FROM projects WHERE proj_id = ?";
    private final String ADD_NEW_PROJ_QUERY = "INSERT INTO projects VALUES (?,?,?)";
    private final String UPDATE_PROJ_BY_ID_QUERY = "UPDATE projects SET name = ?, address = ?, phone = ? WHERE proj_id = ?";
    private final String DELETE_PROJ_BY_ID_QUERY = "DELETE FROM projects WHERE proj_id = ?";
    private final String GET_projects_QUERY = "SELECT * FROM Projects";

    public ProjectsDao() {
        connection = DBConnection.getConnection();
//      ProjectsDao = new ProjectsDao();
    }


    public List<Projects> getProjs() throws SQLException{
        ResultSet rs = connection.prepareStatement(GET_PROJ_QUERY).executeQuery();
        List<Projects> Projects = new ArrayList<Projects>();

        while(rs.next()) {
            Projects.add(populateProject(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
        }

        return Projects;
    }

    public Projects getProjById(int proj_id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(GET_PROJ_BY_ID_QUERY);
        ps.setInt(1, proj_id);
        ResultSet rs = ps.executeQuery();
        rs.next();

        //sean please fix this cz it doesn't align with the Projects Entity.
//        return populateProjects(rs.getInt(1), rs.getInt(2),rs.getString(3), rs.getString(4));
        return null;
    }

    public void addNewProj(int org_id, String proj_name, String proj_desc) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(ADD_NEW_PROJ_QUERY);
        ps.setInt(1, org_id);
        ps.setString(2, proj_name);
        ps.setString(3, proj_desc);
        ps.executeUpdate();
    }
    public void updateProjById(String proj_name, String proj_desc) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(UPDATE_PROJ_BY_ID_QUERY);
        ps.setString(1, proj_name);
        ps.setString(2, proj_desc);
        ps.executeUpdate();
    }

    public void deleteProjectsByProjId(int proj_id) throws SQLException {
//      ProjectsDao.deleteProjectsByProjId(proj_id);
        PreparedStatement ps = connection.prepareStatement(DELETE_PROJ_BY_ID_QUERY);
        ps.setInt(1, proj_id);
        ps.executeUpdate();
    }

    private Projects populateProjects(int proj_id, int org_id, String proj_name, String proj_desc) throws SQLException {
        return new Projects(proj_id, org_id, proj_name, proj_desc);
    }
    public List<Projects> getProj() throws SQLException {

        ResultSet rs = connection.prepareStatement(GET_projects_QUERY).executeQuery();
        List<Projects> project = new ArrayList<Projects>();

        while (rs.next()) {
            project.add(populateProject(rs.getInt(1), rs.getInt(2),  rs.getString(3),  rs.getString(4)));
        }

        return project;

    }
    //  public List<Team> getTeams() throws SQLException {
//      ResultSet rs = connection.prepareStatement(GET_TEAMS_QUERY).executeQuery();
//      List<Team> teams = new ArrayList<Team>();
//
//      while (rs.next()) {
//          teams.add(populateTeam(rs.getInt(1), rs.getString(2)));
//      }
//
//      return teams;
    //}
    private Projects populateProject(int id, int orgid, String Projname, String Projdesc) throws SQLException {
        return new Projects(id, orgid, Projname, Projdesc);
    }

}
