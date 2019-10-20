package edu.northeastern.cs5200.models;

public class Developers {
	private String title;
	private String imdbId;
	
	public String toTring() {
		return imdbId+ " " + title;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImdbId() {
		return imdbId;
	}
	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}
	public Developers(String title, String imdbId) {
		super();
		this.title = title;
		this.imdbId = imdbId;
	}
	public Developers() {
		super();
	}
	
	
}
