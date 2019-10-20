package edu.northeastern.cs5200.models;

import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.Entity;

@Entity
public class User extends Person{
	private boolean userAgreement = false;

	public boolean isUserAgreement() {
		return userAgreement;
	}

	public void setUserAgreement(boolean userAgreement) {
		this.userAgreement = userAgreement;
	}
	
	public User(int id, String first, String last, String username, String pwd, String email, Date dob, Boolean userAgreement) {
		super.setId(id);
		super.setFirstName(first);
		super.setLastName(last);
		super.setUsername(username);
		super.setPassword(pwd);
		super.setEmail(email);
		super.setDob(dob);
		this.userAgreement = userAgreement;
		
	}
	public User(int id, String first, String last, String username, String pwd, String email, Date dob, Boolean userAgreement, ArrayList<Address> address, ArrayList<Phone> phone) {
		super.setId(id);
		super.setFirstName(first);
		super.setLastName(last);
		super.setUsername(username);
		super.setPassword(pwd);
		super.setEmail(email);
		super.setDob(dob);
		this.userAgreement = userAgreement;
		for(int i = 0; i < address.size(); i++) {
			address.get(i).setPersonId(id);
		}
		for(int i = 0; i < phone.size(); i++) {
			phone.get(i).setPersonId(id);
		}
		
	}
	
}