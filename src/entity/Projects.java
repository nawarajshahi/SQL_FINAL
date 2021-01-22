package entity;

public class Projects {
	private int proj_id;
	private int org_id;
	private String proj_name;
	private String proj_desc;
	
	
	//no args default constructor
	public Projects() {
	}

	//overloaded constructor
	public Projects(int proj_id, int org_id, String proj_name, String proj_desc) {
		this.org_id = org_id;
		this.proj_name = proj_name;
		this.proj_desc = proj_desc;
	}

	//getters and setters
	public int getProj_id() {
		return proj_id;
	}
	public void setProj_id(int proj_id) {
		this.proj_id = proj_id;
	}
	public int getOrg_id() {
		return org_id;
	}
	public void setOrg_id(int org_id) {
		this.org_id = org_id;
	}
	public String getProj_name() {
		return proj_name;
	}
	public void setProj_name(String proj_name) {
		this.proj_name = proj_name;
	}
	public String getProj_desc() {
		return proj_desc;
	}
	public void setProj_desc(String proj_desc) {
		this.proj_desc = proj_desc;
	}
	
	

}
