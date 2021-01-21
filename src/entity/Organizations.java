package entity;

public class Organizations {
	
	//Testing 
	
	private int org_id;
	private String name;
	private String address;
	private String phone;
	
	//Constructor
	public Organizations(int org_id, String name, String address, String phone) {

		this.setOrg_id(org_id);
		this.setName(name);
		this.setAddress(address);
		this.setPhone(phone);
	}

		
		
	public Organizations() {
		//no args default constructor
	}
	

	public int getOrg_id() {
		return org_id;
	}
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
	
	

}
