package com.kallam.middleware.model.security;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Document(collection = "users")
public class User {
	@Id
	private String id;
	private String name;
	private String username;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private List<String> roles;
	private String phoneNo;
	private String email;
	private String locale;
	private Boolean active;
	private List<Address> addressLst;

	public User() {

	}

	public User(String name, String username, String password, List<String> roles, String phoneNo,
			String email, String locale, Boolean active, List<Address> addressLst) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.phoneNo = phoneNo;
		this.email = email;
		this.locale = locale;
		this.active = active;
		this.addressLst = addressLst;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Address> getAddressLst() {
		return addressLst;
	}

	public void setAddressLst(List<Address> addressLst) {
		this.addressLst = addressLst;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", roles="
				+ roles + "]";
	}

}
