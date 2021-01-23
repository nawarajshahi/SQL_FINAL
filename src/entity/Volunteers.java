package entity;


/**
 * Authored by Nawaraj Shahi
 */

public class Volunteers {
	private int vol_id;
	private int org_id;
	private String full_name;
	private String phone;

	/** no args default constructor */
	public Volunteers(){
		//left blank intentionally

	}

	/**
	 * Volunteers(int vol_id, int org_id, String full_name, String phone) constructor
	 * @param vol_id 	volunteer id in the database
	 * @param org_id 	the organization id to which the volunteer is part of
	 * @param full_name 	full name of the volunteer
	 * @param phone 	phone of the volunteer
	 */
	public Volunteers(int vol_id, int org_id, String full_name, String phone) {
		super();
		this.vol_id = vol_id;
		this.org_id = org_id;
		this.full_name = full_name;
		this.phone = phone;
	}

	/**
	 * getVol_id() getter
	 * @return 	volunteer id in the database
	 */
	public int getVol_id() {
		return vol_id;
	}


	/**
	 * setVol_id(int vol_id) setter
	 * @param vol_id 	volunteer id in the database
	 */
	public void setVol_id(int vol_id) {
		this.vol_id = vol_id;
	}


	/**
	 * getOrg_id() getter
	 * @return	the organization id to which the volunteer is part of
	 */
	public int getOrg_id() {
		return org_id;
	}


	/**
	 * setOrg_id(int org_id) setter
	 * @param org_id	the organization id to which the volunteer is part of
	 */
	public void setOrg_id(int org_id) {
		this.org_id = org_id;
	}


	/**
	 * getFull_name() getter
	 * @return 	full name of the volunteer
	 */
	public String getFull_name() {
		return full_name;
	}

	/**
	 * setFull_name(String full_name) setter
	 * @param full_name 	full name of the volunteer
	 */
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}


	/**
	 * getPhone() getter
	 * @return 	phone of the volunteer
	 */
	public String getPhone() {
		return phone;
	}


	/**
	 * setPhone(String phone) setter
	 * @param phone 	phone of the volunteer
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}


	/**
	 * toString()
	 * @return	the complete detail of the volunteer in string format
	 */
	@Override
	public String toString() {
		return String.format("Volunteer id:\t\t%d"
				+ "\nOrganization id: \t%d "
				+ "\nVolunteer name: \t%s"
				+ "\nVolunteer phone: \t%s", 
				vol_id, org_id, 
				full_name, phone);
	}

		
	

}
