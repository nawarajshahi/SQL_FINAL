package entity;

import java.util.List;

/**
 * Authored by Renee Dubuc
 */

public class Organizations {
	
	private int org_id;
	private String name;
	private String address;
	private String phone;
	private List<Volunteers> volunteers;
	
	//Constructor
	public Organizations(int org_id, String name, String address, String phone, List<Volunteers> volunteers) {
		this.setOrg_id(org_id);
		this.setName(name);
		this.setVolunteers(volunteers);
		this.setAddress(address);
		this.setPhone(phone);
	}

	public Organizations() {
		//no args default constructor
	}
	
	//Getters and Setters
	public int getOrg_id() {
		return org_id;
	}
	
	//We don't want people to be able to set ID because it's auto-incremement, correct?
	public void setOrg_id(int org_id) {
		this.org_id = org_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Volunteers> getVolunteers() {
		return volunteers;
	}

	public void setVolunteers(List<Volunteers> volunteers) {
		this.volunteers = volunteers;
	}
	

}
