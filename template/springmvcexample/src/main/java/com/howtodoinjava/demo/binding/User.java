package com.howtodoinjava.demo.binding;

public class User {
	private String firstName;
	private String lastName;
	private ContactInfo contactInfo;

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

	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String toString() {
		return String.format("User[firstName:%s, lastName:%s, %s]", firstName, lastName, contactInfo);
	}
}
