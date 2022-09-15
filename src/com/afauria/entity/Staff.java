package com.afauria.entity;

public class Staff {
	private int id;
	private String username;
	private String passwd;
	private String name;
	private String phone;
	private String department;
	private int gender;
	private boolean isManager;
	
	
	public Staff() {
		super();
	}
	
	public Staff(int id, String username, String passwd, String name, String phone, String department, int gender,
			boolean isManager) {
		super();
		this.id = id;
		this.username = username;
		this.passwd = passwd;
		this.name = name;
		this.phone = phone;
		this.department = department;
		this.gender = gender;
		this.isManager = isManager;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public boolean isManager() {
		return isManager;
	}
	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}
}
