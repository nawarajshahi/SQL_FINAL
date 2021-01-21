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
	
	private List<String> orgOptions = Arrays.asList("Dislay organizations",
													"Display an organization",
													"Create an organization",
													"Delete an organization",
													"Return to main menu");
	
	private List<String> volOptions = Arrays.asList("Dislay volunteers",
													"Display a volunteer",
													"Create a volunteer",
													"Delete a volunteer",
													"Return to main menu");
	
	private List<String> projOptions = Arrays.asList("Dislay projects",
													"Display a project",
													"Create a project",
													"Delete a project",
													"Return to main menu");
	
	public void start() {
		String selection = "";
		do {
			System.out.println("\n**********Main-Menu**********");
			System.out.println("-----------------------------");
			printMainMenu();
			
			selection = input.nextLine();
			if(selection.equals("1")) {
//				maintainOrgTable();
			}else if(selection.equals("2")) {
				maintainVolTable();
			}else if(selection.equals("3")) {
//				maintainProjTable();
			}
			
			System.out.println("\nPlease enter to continue to the program...");
			input.nextLine();
			
		}while (!selection.equals("-1"));
	}
	
	
	
	/*==========================================================================================
	 * All the methods below belong to Volunteer Table
	 */
	
	//maintainVolTable() implementation
	private void maintainVolTable() {
		String selection = "";
		do {
			System.out.println("\n**********Volunteers Table**********");
			System.out.println("-----------------------------");
			printVolMenu();
			
			selection = input.nextLine();
			try {
				if(selection.equals("1")) {
					displayVolunteers();
				}else if(selection.equals("2")) {
					displayAVolunteer();
				}else if(selection.equals("3")) {
					createAVolunteer();
				}else if(selection.equals("4")) {
					deleteAVolunteer();
				} else{
					System.out.println("\nTry again, please select a valid option.\n");
					maintainVolTable();
					
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("Press Enter to continue...");
			input.nextLine();
			
		}while (!selection.equals("5"));
	}
	
	
	//displayAVolunteer() method implementation
	private void displayAVolunteer() throws SQLException {
		System.out.println("Please enter volunteer id:");
		int id = Integer.parseInt(input.nextLine());
		List<Volunteers> volunteer = volDao.getAVolunteerById(id);
		if(volunteer != null) {
			for (Volunteers vols: volunteer) {
				System.out.println(vols.toString()); //toString() method is implemented in Volunteers Class
			}
		}else {
			System.out.println(id + " not found, please enter a valid id.");
		}
		
	}

	//displayVolunteers() implementation
	private void displayVolunteers() throws SQLException {
		System.out.println("Printing volunteers.....");
		List<Volunteers> volunteers = volDao.getVolunteers();
		for (Volunteers vols: volunteers) {
			System.out.println(vols.toString()); //toString() method is implemented in Volunteers Class
		}
		
	}
	
	
	//createAVolunteer() method implementation
	private void createAVolunteer() {
		System.out.println("Which organization would you like the volunteer to be added to?\nPlease enter organization_id:");
		int org_id = Integer.parseInt(input.nextLine());
		/*
		 * write a code here that checks if the organization exists in the database. If it doesn't exist, 
		 * ask the user if the would like to add the organization. If organization exists then they can 
		 * simply add the new volunteer info into Volunteer database. 
		 */
		
//		if(org_id exists) {
//			//create the volunteer for this organization
//		}else {
//			//print out message dislaying that organization does not exist
//			//also prompt to see if the user would like to add the organization first?
//		}
		
	}
	
	//deleteAVolunteer() method implementation
	private void deleteAVolunteer() throws SQLException {
		System.out.println("Which volunteer would you like to delete? \nPlease enter volunteer id: ");
		int vol_id = Integer.parseInt(input.nextLine());
		int executeVal = volDao.deleteVolunteerById(vol_id);
		if(executeVal == 0) {
			System.out.println(vol_id + " could not be deleted. Please enter correct volunteer id.");
			deleteAVolunteer();
		}else {
			List<Volunteers> volunteer = volDao.getAVolunteerById(vol_id);
			for (Volunteers vols: volunteer) {
				System.out.println("Successfully deleted...");
				System.out.println(vols.toString()); 
			}
		}
		
	}
	
	
	
	/*==========================================================================================
	 * All the menu options method implementation below
	 */

	//printMainMenu() implementation
	private void printMainMenu() {
		for (int i = 0; i<tableOptions.size(); i++) {
			System.out.println(i+1 + ") " + tableOptions.get(i));
		}
		System.out.println("\nWhich table would you like to do maintenance to?");
	}
	
	//printOrgMenu() implementation
	private void printOrgMenu() {
		printMenuPattern(orgOptions);
	}
	
	//printVolMenu() implementation
	private void printVolMenu() {
		printMenuPattern(volOptions);
	}
		
	//printProjMenu() implementation
	private void printProjMenu() {
		printMenuPattern(projOptions);
	}
	
	
	//separate printMenuPattern() method for code reusability of printing different menu options
	private void printMenuPattern(List<String> menu) {
		
		for (int i = 0; i<menu.size(); i++) {
			System.out.println(i+1 + ") " + menu.get(i));
		}
		System.out.println("\nWhich maintentance would you like to perform?");
	}
	
	/*
	 * =============================================================================================
	 */
	

}






















































