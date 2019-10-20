package edu.northeastern.cs5200.models;
import javax.persistence.*;
@Entity

public class Phone {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String phone = null;
	boolean primary = false;
	private int personId;
	
	public Phone(int id, String phone, boolean primary, int personId) {
		this.id = id;
		this.phone = phone;
		this.primary = primary;
		this.personId = personId;
	}
	
	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isPrimary() {
		return primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}

}