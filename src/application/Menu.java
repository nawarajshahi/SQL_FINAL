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
                                        "Display projects",
                                        "Update a project",
                                        "Delete a project",
                                        "Return to main menu");

    /**start()
     * this method handles the main database and tables to which CRUD
     * operation can be performed
     */
    public void start() {
        String selection = "";
        do {
            System.out.println("\n**********Main-Menu**********");
            System.out.println("-----------------------------");
            printMainMenu();

            selection = input.nextLine();
            if (selection.equals("1")) {
                maintainOrgTable();
            } else if (selection.equals("2")) {
                maintainVolTable();
            } else if (selection.equals("3")) {
				maintainProjTable();
            }

            System.out.println("\nPlease press enter to continue the program...");
            input.nextLine();

        } while (!selection.equals("-1"));
        System.out.println("Have a nice day.");
    }

    /**
     * printMainMenu() method simply prints out the message to the console
     *regarding which tables the user wants to perform CRUD operations
     */

    private void printMainMenu() {
        for (int i = 0; i < tableOptions.size(); i++) {
            System.out.println(i + 1 + ") " + tableOptions.get(i));
        }
        System.out.println("\nWhich table would you like to do maintenance to?");
    }

    /**
     * ==========================================================================================
     * Menu and Methods for Organizations Table
     * Authored by Renee Dubuc
     */

    /**maintainOrgTable()
     *
     */
    private void maintainOrgTable() {
        String selection = "";
        do {
            System.out.println("\n**********Organizations Table**********");
            System.out.println("-----------------------------");
            printOrgMenu();
            selection = input.nextLine();
            try {
                if (selection.equals("1")) {
                    displayOrgs();
                } else if (selection.equals("2")) {
                    displayOrgById();
                } else if (selection.equals("3")) {
                    createOrg();
                } else if (selection.equals("4")) {
                    updateOrg();
                } else if (selection.equals("5")) {
                    deleteOrg();
                } else if (!selection.equals("6")) {
                    System.out.println("\nTry again, please select a valid option.\n");
                    maintainVolTable();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } while (!selection.equals("6"));
    }


    private void createOrg() throws SQLException {
        System.out.print("Enter name of new organization: ");
        String name = input.nextLine();
        System.out.print("Enter new address: ");
        String address = input.nextLine();
        System.out.print("Enter new phone number: ");
        String phone = input.nextLine();
        orgDao.createNewOrg(name, address, phone);
    }


    private void updateOrg () throws SQLException {
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

    private void deleteOrg() throws SQLException {
        System.out.print("Enter ID of the organization to delete: ");
        int org_id = Integer.parseInt(input.nextLine());
        orgDao.deleteOrgById(org_id);
    }

    private void displayOrgs() throws SQLException {
        List<Organizations> organizations = orgDao.getOrgs();
        for (Organizations organization : organizations) {
            System.out.println(organization.getOrg_id() + ": " + organization.getName());
        }
    }


    private void displayOrgById() throws SQLException {
        System.out.println("Enter organization ID: ");
        int org_id = Integer.parseInt(input.nextLine());
        Organizations organizations = orgDao.getOrgById(org_id);
        System.out.println(organizations.getOrg_id() + ": " + organizations.getName() +
                ", " + organizations.getAddress() + " " + organizations.getPhone());
    }


    /**==========================================================================================
     * Menu and Methods for Volunteers Table
     * Authored by Nawaraj Shahi
     */

    /**
     * maintainVolTable() maintains the Volunteers table with its CRUD operations.
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
                } else if (!selection.equals("6")) {
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
     * @throws SQLException
     */
    private void displayAVolunteer() throws SQLException {
        System.out.println("Please enter volunteer id:");
        int id = Integer.parseInt(input.nextLine());
        List<Volunteers> volunteer = volDao.getAVolunteerById(id);

        for (Volunteers vols : volunteer) {
            System.out.println(vols.toString()); //toString() method is implemented in Volunteers Class
        }
    }


    /**
     * displayVolunteers() implementation
     * @throws SQLException
     */
    private void displayVolunteers() throws SQLException {
        System.out.println("Printing volunteers.....");
        List<Volunteers> volunteers = volDao.getVolunteers();
        System.out.println("Volunteer id \tOrganization id \tVolunteer Name\t\t\tVolunteer Phone");
        for (Volunteers vols : volunteers) {
            System.out.println(String.format("\t%d"
                            + "\t\t\t\t\t%d "
                            + "\t\t\t%s "
                            + " \t\t\t%s",
                    vols.getVol_id(), vols.getOrg_id(),
                    vols.getFull_name(), vols.getPhone()));
        }
    }


    /**
     * createAVolunteer() method implementation
     * @throws SQLException
     */
    private void createAVolunteer() throws SQLException {
        System.out.println("Which organization would you like the volunteer to be added to?\nPlease enter organization_id:");
        int org_id = Integer.parseInt(input.nextLine());

        organization = orgDao.getOrgById(org_id);

        if (organization == null) {
            char decision;
            System.out.println(org_id + " does not exist in the database.\nWould you like to create the organization? (Y/N)");
            decision = input.next().charAt(0);
            if (decision == 'Y' || decision == 'y') {
                createOrg();
            }

        } else {

            String full_name, phone;

            System.out.print("Enter full name of the volunteer: ");
            full_name = input.nextLine();

            System.out.print("Enter volunteer's phone number:");
            phone = input.nextLine();

            int status = volDao.createVolunteer(org_id, full_name, phone);
            if(status == 0){
                System.out.println("\nError, could not create the volunteer");
            }else{
                System.out.println("\nSuccessfully added, and " + status + " row(s) affected.");
            }

        }
    }


    /**
     * deleteAVolunteer() method implementation
     * @throws SQLException
     */
    private void deleteAVolunteer() throws SQLException {
        System.out.println("Which volunteer would you like to delete? \nPlease enter volunteer id: ");
        int vol_id = Integer.parseInt(input.nextLine());
        int executeVal = volDao.deleteVolunteerById(vol_id);
        if (executeVal == 0) {
            System.out.println("\n"+vol_id + " could not be deleted. Please enter correct volunteer id.");
            deleteAVolunteer();
        } else {
            System.out.println("\nSuccessfully deleted, and " + executeVal + " row(s) affected.");
        }

    }


    /**
     * updateAVolunteer() method implementation
     * @throws SQLException
     */
    private void updateAVolunteer() throws SQLException {
        String full_name, phone;
        int vol_id, org_id;
        System.out.println("Which volunteer would you like to update?");
        System.out.print("Enter volunteer id: ");
        vol_id = Integer.parseInt(input.nextLine());

        System.out.print("Enter updated full name: ");
        full_name = input.nextLine();

        System.out.print("Enter organization id: ");
        org_id = Integer.parseInt(input.nextLine());

        System.out.print("Enter phone: ");
        phone = input.nextLine();
       int status = volDao.updateVolunteer(vol_id, org_id, full_name, phone);
       if(status ==0){
           System.out.println("\nError, could not update the field.");
       }else{
           System.out.println("\nSuccessfully updated, and " + status + " row(s) affected.");
       }

    }


    /**==========================================================================================
     * Menu and Methods for Projects Table
     * Authored by Sean Flaherty
     */

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
                } else if (selection.equals("3")) {
                    updateaproject();
                } else if (selection.equals("4")) {
                    deleteaproject();
                } else if (!selection.equals("5")) {
                    System.out.println("\nTry again, please select a valid option.\n");
                    maintainProjTable();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.println("Press enter to continue...");
            input.nextLine();

        } while (!selection.equals("5"));
    }


    private void addNewProj() throws SQLException {
        System.out.println("Enter Organization ID: ");
        int org_id = input.nextInt();
        System.out.println("Enter Project Name:");
        input.nextLine();
        String proj_name = input.next();
        System.out.println("Enter Project Description:");
        input.nextLine();
        String proj_desc = input.next();
        projectsDao.addNewProj(org_id, proj_name, proj_desc);
    }


    private void displayaproject() throws SQLException {

        List<Projects> projects = projectsDao.getProjs();

        System.out.println(String.format("%-20s %12s %16s %24s", "Project ID", "Org ID", "Project Name", "Project Description"));
        for (Projects p : projects) {
            System.out.println(String.format("%-20s %12s %16s %24s", p.getProj_id(), p.getOrg_id(), p.getProj_name(), p.getProj_desc()));
        }
    }

    private void updateaproject() throws SQLException {
        projectsDao.updateProject();
    }

    private void deleteaproject() throws SQLException {
        System.out.println("Enter project id to delete:");
        int id = Integer.parseInt(input.nextLine());

        projectsDao.deleteProjectsByProjId(id);

    }


    /**==========================================================================================
     * Sub-Menu functions and helping functions for all the tables in database
     */


    /**
     * printOrgMenu() implementation
     */
    private void printOrgMenu() {
        printMenuPattern(orgOptions);
    }

    /**
     * printVolMenu() implementation
     */
    private void printVolMenu() {
        printMenuPattern(volOptions);
    }

    /**
     * printProjMenu() implementation
     */
    private void printProjMenu() {
        printMenuPattern(projOptions);
    }


    /**
     * separate printMenuPattern() method for code re-usability of printing different menu options
     *
     * @param menu List of Menu options
     */
    private void printMenuPattern(List<String> menu) {
        for (int i = 0; i < menu.size(); i++) {
            System.out.println(i + 1 + ") " + menu.get(i));
        }
        System.out.println("\nWhich maintenance would you like to perform?");
    }

}


	
	
	
	
	
	
	
	

























































