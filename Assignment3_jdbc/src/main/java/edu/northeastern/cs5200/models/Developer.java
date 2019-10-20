package edu.northeastern.cs5200.models;
import java.sql.Date;

import javax.persistence.*;
@Entity

public class Developer extends Person{
	private String developerKey;
	public String getDeveloperKey() {
		return developerKey;
	}

	public void setDeveloperKey(String developerKey) {
		this.developerKey = developerKey;
	}
	
	public Developer(String key, int id, String first, String last) {
		this.developerKey = key;
		super.setId(id);
		super.setFirstName(first);
		super.setLastName(last);
	}
	
	public Developer(String key, int id, String first, String last, String username, String pwd, String email, Date dob) {
		
		super.setId(id);
		super.setFirstName(first);
		super.setLastName(last);
		super.setUsername(username);
		super.setPassword(pwd);
		super.setEmail(email);
		super.setDob(dob);
		this.developerKey = key;
	}
	
	public Developer(String key, int id, String first, String last, String username, String pwd, String email, Date dob, Address[] address, Phone[] phone) {
		super.setId(id);
		super.setFirstName(first);
		super.setLastName(last);
		super.setUsername(username);
		super.setPassword(pwd);
		super.setEmail(email);
		super.setDob(dob);
		this.developerKey = key;
		super.setPhone(phone);
		super.setAddress(address);
	}
}