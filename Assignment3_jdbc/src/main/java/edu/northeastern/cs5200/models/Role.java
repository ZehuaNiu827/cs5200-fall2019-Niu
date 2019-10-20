package edu.northeastern.cs5200.models;

import javax.persistence.*;

@Entity
@Table(name = "Role")

public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String role;
	private int developerId;
	private int pageId;
	private int websiteId;
	
	public Role(int id, String role, int developerId, int pageId, int websiteId) {
		super();
		this.id = id;
		this.role = role;
		this.developerId = developerId;
		this.pageId = pageId;
		this.websiteId = websiteId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getDeveloperId() {
		return developerId;
	}
	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public int getWebsiteId() {
		return websiteId;
	}
	public void setWebsiteId(int websiteId) {
		this.websiteId = websiteId;
	}
	
	
}