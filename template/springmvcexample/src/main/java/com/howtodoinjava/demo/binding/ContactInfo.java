package com.howtodoinjava.demo.binding;

public class ContactInfo {
	private String tel;
	private String address;

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String toString() {
		return String.format("ContactInfo[tel:%s, address:%s]", tel, address);
	}
}