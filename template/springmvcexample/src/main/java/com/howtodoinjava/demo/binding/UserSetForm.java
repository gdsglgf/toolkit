package com.howtodoinjava.demo.binding;

import java.util.HashSet;
import java.util.Set;

public class UserSetForm {
	private Set<User> users = new HashSet<User>();

	public UserSetForm() {
		users.add(new User());
		users.add(new User());
		users.add(new User());
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}