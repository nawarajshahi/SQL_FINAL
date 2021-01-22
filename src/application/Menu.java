package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import dao.OrganizationsDao;
import dao.ProjectsDao;
import dao.VolunteersDao;
import entity.Organizations;
import entity.Projects;
import entity.Volunteers;


public class Menu {

	private ProjectsDao projectsDao = new ProjectsDao();
	private OrganizationsDao orgDao = new OrganizationsDao();
	private VolunteersDao volDao = new VolunteersDao();
	private Organizations organization = new Organizations();


	Scanner input = new Scanner(System.in);

	private List<String> tableOptions = Arrays.asList("Organizations",
													"Volunteers",
													"Projects");


	private List<String> orgOptions = Arrays.asList("Display organizations",
													"Display an organization",
													"Create an organization",
													"Update an organization",
													"Delete an organization",
													"Return to main menu");
	
	private List<String> volOptions = Arrays.asList("Display volunteers",
													"Display a volunteer",
													"Create a volunteer",
													"Delete a volunteer",
													"Update a volunteer",
													"Return to main menu");
	
	private List<String> projOptions = Arrays.asList("Create a project",
													"Display a project",
													"Update a project",
													"Delete a project",
													"Return to main menu");




	public void start() {
		String selection = "";
		do {
			System.out.println("\n**********Main-Menu**********");
			System.out.println("-----------------------------");
			printMainMenu();

			selection = input.nextLine();
			if (selection.equals("1")) {
//				maintainOrgTable();					//Renee, your sub-menu CRUD operations should belong to this method
			} else if (selection.equals("2")) {
				maintainVolTable();
			} else if (selection.equals("3")) {
			maintainProjTable();				//sean your sub-menu CRUD operations should belong to this method
			}

			System.out.println("\nPlease press enter to continue the program...");
			input.nextLine();

		} while (!selection.equals("-1"));
		System.out.println("Have a nice day.");
	}

	//printMainMenu() implementation
	private void printMainMenu() {
		for (int i = 0; i < tableOptions.size(); i++) {
			System.out.println(i + 1 + ") " + tableOptions.get(i));
		}
		System.out.println("\nWhich table would you like to do maintenance to?");
	}


	/**==========================================================================================
	 * All the methods below belong to Volunteer Table
	 */

	/**
	 * maintainVolTable() implementation
	 */
	private void maintainVolTable() {
		String selection = "";
		do {
			System.out.println("\n**********Volunteers Table**********");
			System.out.println("-----------------------------");
			printVolMenu();

			selection = input.nextLine();
			try {
				if (selection.equals("1")) {
					displayVolunteers();
				} else if (selection.equals("2")) {
					displayAVolunteer();
				} else if (selection.equals("3")) {
					createAVolunteer();
				} else if (selection.equals("4")) {
					deleteAVolunteer();
				} else if (selection.equals("5")) {
					updateAVolunteer();
				}else if (!selection.equals("6")){
					System.out.println("\nTry again, please select a valid option.\n");
					maintainVolTable();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} while (!selection.equals("6"));
	}



	/**
	 * displayAVolunteer() method implementation
	 *
	 * @throws SQLException
	 */
	private void displayAVolunteer() throws SQLException {
		System.out.println("Please enter volunteer id:");
		int id = Integer.parseInt(input.nextLine());
		List<Volunteers> volunteer = volDao.getAVolunteerById(id);
		if (volunteer != null) {
			for (Volunteers vols : volunteer) {
				System.out.println(vols.toString()); //toString() method is implemented in Volunteers Class
			}
		} else {
			System.out.println(id + " not found, please enter a valid id.");
		}

	}



	/**
	 * displayVolunteers() implementation
	 *
	 * @throws SQLException
	 */
	private void displayVolunteers() throws SQLException {
		System.out.println("Printing volunteers.....");
		List<Volunteers> volunteers = volDao.getVolunteers();
		for (Volunteers vols : volunteers) {
			System.out.println(String.format("Volunteer id: %d"
							+ " \tOrganization id: %d "
							+ " \tVolunteer name: %s"
							+ "\t\t\tVolunteer phone: %s",
							vols.getVol_id(), vols.getOrg_id(),
							vols.getFull_name(), vols.getPhone()));
		}
	}



	/**
	 * createAVolunteer() method implementation
	 *
	 * @throws SQLException
	 */
	private void createAVolunteer() throws SQLException {
		System.out.println("Which organization would you like the volunteer to be added to?\nPlease enter organization_id:");
		int org_id = Integer.parseInt(input.nextLine());

		Organizations organization = new Organizations();
		organization = orgDao.getOrgById(org_id);

		if (organization == null) {
			char decision;
			System.out.println(org_id + " does not exist in the database.\nWould you like to create the organization? (Y/N)");
			decision = input.next().charAt(0);
			if (decision == 'Y' || decision == 'y') {
				//create an organization
				createOrg();
			}

		} else {

			String full_name, phone;
			int vol_id;
			System.out.print("Enter volunteer id: ");
			vol_id = Integer.parseInt(input.nextLine());

			System.out.print("Enter full name of the volunteer: ");
			full_name = input.nextLine();

			System.out.print("Enter volunteer's phone number:");
			phone = input.nextLine();

			volDao.createVolunteer(vol_id, org_id, full_name, phone);
		}
	}


	/**
	 * deleteAVolunteer() method implementation
	 * @throws SQLException
	 */
	private void deleteAVolunteer () throws SQLException {
			System.out.println("Which volunteer would you like to delete? \nPlease enter volunteer id: ");
			int vol_id = Integer.parseInt(input.nextLine());
			int executeVal = volDao.deleteVolunteerById(vol_id);
			if (executeVal == 0) {
				System.out.println(vol_id + " could not be deleted. Please enter correct volunteer id.");
				deleteAVolunteer();
			} else {
				List<Volunteers> volunteer = volDao.getAVolunteerById(vol_id);
				for (Volunteers vols : volunteer) {
					System.out.println("Successfully deleted...");
					System.out.println(vols.toString());
				}
			}

		}


	/**
	 * updateAVolunteer() method implementation
	 * @throws SQLException
	 */
	private void updateAVolunteer () throws SQLException {
			String full_name, phone;
			int vol_id, org_id;
			System.out.print("Enter volunteer id: ");
			vol_id = Integer.parseInt(input.nextLine());

			System.out.print("Enter updated full name: ");
			full_name = input.nextLine();

			System.out.print("Enter organization id: ");
			org_id = Integer.parseInt(input.nextLine());

			System.out.print("Enter phone: ");
			phone = input.nextLine();
			volDao.updateVolunteer(vol_id, org_id, full_name, phone);

		}



	/**==========================================================================================
	 * All the menu options method implementation below
	 */


	/**
	 * printOrgMenu() implementation
 	 */
	private void printOrgMenu () {
			printMenuPattern(orgOptions);
		}

	/**
	 * printVolMenu() implementation
	 */
	private void printVolMenu () {
			printMenuPattern(volOptions);
		}

	/**
	 * printProjMenu() implementation
	 */
	private void printProjMenu () {
			printMenuPattern(projOptions);
		}


	/**
	 * separate printMenuPattern() method for code re-usability of printing different menu options
	 * @param menu List of Menu options
	 */
	private void printMenuPattern (List < String > menu) {

			for (int i = 0; i < menu.size(); i++) {
				System.out.println(i + 1 + ") " + menu.get(i));
			}
			System.out.println("\nWhich maintenance would you like to perform?");
		}


	/**
	 * =============================================================================================
	 * All the organizations menu methods implemented
	 */

	private void displayOrgs () throws SQLException {
			List<Organizations> organizations = orgDao.getOrgs();
			for (Organizations organization : organizations) {
				System.out.println(organization.getOrg_id() + ": " + organization.getName());
			}
		}

	private void displayOrgById () throws SQLException {
			System.out.println("Enter organization ID: ");
			int org_id = Integer.parseInt(input.nextLine());
			Organizations organizations = orgDao.getOrgById(org_id);
			System.out.println(organizations.getOrg_id() + ": " + organizations.getName());
			for (Volunteers volunteers : organizations.getVolunteers()) {
				System.out.println("\tOrganization ID: " + volunteers.getVol_id() + " - Name: " + volunteers.getFull_name() + " " + volunteers.getPhone());
			}
		}

	private void createOrg () throws SQLException {
			System.out.print("Enter name of new organization: ");
			String name = input.nextLine();
			System.out.print("Enter new address: ");
			String address = input.nextLine();
			System.out.print("Enter new phone number: ");
			String phone = input.nextLine();

			orgDao.createNewOrg(name, address, phone);
		}

	private void updateOrganization () throws SQLException {
			System.out.println("Enter ID of the organization to update: ");
			int org_id = Integer.parseInt(input.nextLine());
			System.out.println("Enter updated name: ");
			String name = input.nextLine();
			System.out.println("Enter updated address: ");
			String address = input.nextLine();
			System.out.println("Enter updated phone number: ");
			String phone = input.nextLine();

			orgDao.updateOrgById(org_id, name, address, phone);
		}

	private void deleteOrganization () throws SQLException {
			System.out.print("Enter ID of the organization to delete: ");
			int org_id = Integer.parseInt(input.nextLine());
			orgDao.deleteOrgById(org_id);
		}



	/**
	 * Sean below is is your code that you had written so far. Please modify as needed to function
	 * within the menu
	 */

	/*
	private ProjectsDao projectsDao = new ProjectsDao();
    private input input = new input(System.in);


        //private TeamDao teamDao = new TeamDao();
        //private MemberDao memberDao = new MemberDao();



        private List <String> options = Arrays.asList(
                "Add a new project",
                "Display a project",
                "Update a project",
                "Delete a project");

        public void start() {
            String selection = "";

            do {
                printMenu();
                selection = input.nextLine();

                try {
                if (selection.equals("1")) {
                    addNewProj();
                } else if (selection.equals("2")) {
                    displayaproject();
                }else if (selection.equals("3")) {
                    updateateam();
                }else if (selection.equals("4")) {
                    deleteaproject();
                }
                }catch (SQLException e) {
                    e.printStackTrace();
                }

                System.out.println("Press enter to continue...");
                input.nextLine();
            } while (!selection.equals("-1"));
        }
        private void printMenu() {
            System.out.println("Select an Option:\n-------------------");
            for (int i = 0; i < options.size(); i++) {
                System.out.println(i + 1 + ") " + options.get(i));

            }
        }


private void addNewProj() throws SQLException {
    System.out.print("Enter Organization ID: ");
    int org_id = input.nextInt();
    System.out.print("Enter Project Name:");
    String proj_name = input.nextLine();
    System.out.print("Enter Project Description:");
    String proj_desc = input.nextLine();
    //String proj_desc = String.parseInt(input.nextLine());
    projectsDao.addNewProj(org_id, proj_name, proj_desc);}


private void displayaproject() throws SQLException {
List<Project> projects = projectsDao.getProjs();
for (Project project : projects)
    System.out.println(projectsDao.getProjById() + ": " + project.getProj_name());
}
//private void displayTeams() throws SQLException {
//  List<Team> teams = teamDao.getTeams();
//  for (Team team : teams)
//      System.out.println(team.getTeamId() + ": " + team.getName());
//
//}

private void updateaproject() throws SQLException {

    try {
        rs.updateInt( "ID", newID );
        rs.updateString( "last_Name", last );
        rs.updateString( "Job_Title", job );
        rs.updateRow( );
        JOptionPane.showMessageDialog(Project.this, "Updated");
        }
        catch (SQLException err) {
        System.out.println(err.getMessage() );
        }

}

private void deleteaproject() throws SQLException {
System.out.print("Enter project id to delete:");
int id = Integer.parseInt(input.nextLine());
projectsDao.deleteProjectsByProjId(id);
//public void deleteTeamById(int id) throws SQLException {
//  memberDao.deleteMembersByteamId(id);
//  PreparedStatement ps = connection.prepareStatement(DELETE_TEAM_BY_ID_QUERY);
//  ps.setInt(1, id);
//  ps.executeUpdate();
//private void deleteTeam() throws SQLException {
//System.out.print("Enter team id to delete:");
//int id = Integer.parseInt(input.nextLine());
//teamDao.deleteTeamById(id);
}

}
	 */

	
	private List <String> options = Arrays.asList(
			"Add a new project", 
			"Display a project", 
			"Update a project",
			"Delete a project");
	
	public void maintainProjTable() {
		String selection = "";
		
		do {
			System.out.println("\n**********Projects Table**********");
			System.out.println("-----------------------------");
			printProjMenu();
			selection = input.nextLine();
			
			try {
				if (selection.equals("1")) {
					addNewProj();
				} else if (selection.equals("2")) {
					displayaproject();
				}else if (selection.equals("3")) {
					updateaproject();
				}else if (selection.equals("4")) {
					deleteaproject();
				}
				else if (!selection.equals("5")){
					System.out.println("\nTry again, please select a valid option.\n");
					maintainProjTable();
				}
			}catch (SQLException e) {
				e.printStackTrace();
				}
				
			

			
			System.out.println("Press enter to continue...");
			input.nextLine();
		} while (!selection.equals("5"));
	}

////	private void printMenu() {
//		System.out.println("Select an Option:\n-------------------");
//		for (int i = 0; i < options.size(); i++) {
//			System.out.println(i + 1 + ") " + options.get(i));
//		
//		}
//		System.out.println("Enter Option: ");
//	}

//	
	
	
	
	
	
	
	
	
	
	
	private void addNewProj() throws SQLException {
		System.out.println("Enter Organization ID: ");
		int org_id = input.nextInt();
		System.out.println("Enter Project Name:");
		String proj_name = input.next();
		System.out.println("Enter Project Description:");
		String proj_desc = input.next();
		//String proj_desc = String.parseInt(input.nextLine());
		projectsDao.addNewProj(org_id, proj_name, proj_desc);}

			
		
	private void displayaproject() throws SQLException {

		List<Projects> projects = projectsDao.getProjs();

		System.out.println(String.format("%-20s %12s %16s %24s", "Project ID", "Org ID", "Project Name", "Project Description"));
		for(Projects p: projects)
		{
			System.out.println(String.format("%-20s %12s %16s %24s",p.getProj_id(), p.getOrg_id(), p.getProj_name(), p.getProj_desc()));
		}
	}


	//private void displayTeams() throws SQLException {
	//List<Team> teams = teamDao.getTeams();
	//for (Team team : teams)
//		System.out.println(team.getTeamId() + ": " + team.getName());
	//
	//}

	private void updateaproject() throws SQLException {
		projectsDao.updateProject();

	}




	private void deleteaproject() throws SQLException {
		System.out.println("Enter project id to delete:");
		int id = Integer.parseInt(input.nextLine());

		projectsDao.deleteProjectsByProjId(id);

		}
	}
	
	
	
	
	
	
	
	

























































