package edu.northeastern.cs5200.models;
import java.util.List;

import javax.persistence.*;

@Entity
public class Faculty extends Person{
	private String office;
	private Boolean tenured;
	
	public Faculty() {}
	public Faculty(int id, String firstName, String lastName, String username, String password, String office, Boolean tenured) {
		super.setId(id);
		super.setFirstName(firstName);
		super.setLastName(lastName);
		super.setUsername(username);
		super.setPassword(password);
		this.office = office;
		this.tenured = tenured;
	}
	@OneToMany(mappedBy="author", fetch=FetchType.EAGER)
	private List<Course> authoredCourses;
	
	 public void authoredCourse(Course course) {
	     this.authoredCourses.add(course);
	     if(course.getAuthor() != this)
	        course.setAuthor(this);
	 }
	
	 
	public List<Course> getAuthoredCourses() {
		return authoredCourses;
	}


	public void setAuthoredCourses(List<Course> authoredCourses) {
		this.authoredCourses = authoredCourses;
	}

	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public Boolean getTenured() {
		return tenured;
	}
	public void setTenured(Boolean tenured) {
		this.tenured = tenured;
	}
}
