package entity;

public class Volunteers {
	private int vol_id;
	private int org_id;
	private String full_name;
	private String phone;
	
	
	//constructor
	public Volunteers(int vol_id, int org_id, String full_name, String phone) {
		super();
		this.vol_id = vol_id;
		this.org_id = org_id;
		this.full_name = full_name;
		this.phone = phone;
	}

	//getters and setters for Volunteers Class. 
	public void setVol_id(int vol_id) {
		this.vol_id = vol_id;
	}
	public int getOrg_id() {
		return org_id;
	}
	public void setOrg_id(int org_id) {
		this.org_id = org_id;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public int getVol_id() {
		return vol_id;
	}

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
