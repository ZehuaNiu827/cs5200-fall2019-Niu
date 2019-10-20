package edu.northeastern.cs5200.models;
import javax.persistence.*;


@Entity
@Table(name = "Priviledge")
public class Priviledge {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String priviledge;
	private int developerId;
	private int pageId;
	private int websitrId;
	
	public Priviledge(int id, String priviledge, int developerId, int pageId, int websitrId) {
		super();
		this.id = id;
		this.priviledge = priviledge;
		this.developerId = developerId;
		this.pageId = pageId;
		this.websitrId = websitrId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPriviledge() {
		return priviledge;
	}
	public void setPriviledge(String priviledge) {
		this.priviledge = priviledge;
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
	public int getWebsitrId() {
		return websitrId;
	}
	public void setWebsitrId(int websitrId) {
		this.websitrId = websitrId;
	}
}