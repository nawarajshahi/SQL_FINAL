package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import dao.OrganizationsDao;
import dao.VolunteersDao;
import entity.Organizations;
import entity.Volunteers;


public class Menu {

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
	
	private List<String> projOptions = Arrays.asList("Display projects",
													"Display a project",
													"Create a project",
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
//				maintainOrgTable();
			} else if (selection.equals("2")) {
				maintainVolTable();
			} else if (selection.equals("3")) {
//				maintainProjTable();
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

//			System.out.println("\nPress Enter to continue...");
//			input.nextLine();

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



}
























































