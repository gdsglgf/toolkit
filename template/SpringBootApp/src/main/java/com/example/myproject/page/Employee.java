package com.example.myproject.page;

public class Employee {
	private String firstName;
	private String lastName;;
	private String position;
	private String office;
	private String startDate;
	private String salary;
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	public Employee(String firstName, String lastName, String position, String office, String startDate,
			String salary) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
		this.office = office;
		this.startDate = startDate;
		this.salary = salary;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	public String toString() {
		return String.format("[firstName:%s, lastName:%s, position:%s, office:%s, startDate:%s, salary:%s]", 
				firstName, lastName, position, office, startDate, salary);
	}
}
