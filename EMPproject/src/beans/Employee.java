package beans;

import java.sql.Date;

public class Employee {
	
	private int employeeid;
	private String name;
	private String address;
	private Byte gender;
	private double salary;
	private String birthdate;
	private String skillname; 

public Employee() {
	// TODO Auto-generated constructor stub
}
	public Employee(String name, String address, Byte gender, double salary, String birthdate) {
		super();
		this.name = name;
		this.address = address;
		this.gender = gender;
		this.salary = salary;
		this.birthdate = birthdate;
	}



	public Employee(int employeeid, String name, String address, Byte gender, double salary, String birthdate) {
		super();
		this.employeeid = employeeid;
		this.name = name;
		this.address = address;
		this.gender = gender;
		this.salary = salary;
		this.birthdate = birthdate;
		
	}

	

	public int getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
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

	public Byte getGender() {
		return gender;
	}

	public void setGender(Byte gender) {
		this.gender = gender;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getSkillname() {
		return skillname;
	}
	public void setSkillname(String skillname) {
		this.skillname = skillname;
	}


	
	
	
	
}
