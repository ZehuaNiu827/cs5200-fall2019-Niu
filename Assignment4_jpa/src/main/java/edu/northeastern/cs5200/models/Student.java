package edu.northeastern.cs5200.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import edu.northeastern.cs5200.models.Enrollment;
@Entity
public class Student extends Person{
	private int gradYear;
	private long scholarship;
	
	public Student() {}
	
	
	public Student(int id, String firstName, String lastName, String username, String password, int gradYear, long scholarship) {
		super.setId(id);
		super.setFirstName(firstName);
		super.setLastName(lastName);
		super.setUsername(username);
		super.setPassword(password);
		this.gradYear = gradYear;
		this.scholarship = scholarship;
	}
	@OneToMany(mappedBy="student", fetch = FetchType.EAGER)
	
	private Set<Enrollment> hadEnrollments = new HashSet<Enrollment>();
	
	public void addEnrollments(Enrollment e) {
		this.hadEnrollments.add(e);
		if(!this.getHadEnrollments().contains(e)) {
			this.getHadEnrollments().add(e);
		}
		}
	
	


	public int getGradYear() {
		return gradYear;
	}
	public void setGradYear(int gradYear) {
		this.gradYear = gradYear;
	}
	public long getScholarship() {
		return scholarship;
	}
	public void setScholarship(long scholarship) {
		this.scholarship = scholarship;
	}
	
	public Set<Enrollment> getHadEnrollments() {
		return hadEnrollments;
	}

	public void setHadEnrollments(Set<Enrollment> hadEnrollments) {
		this.hadEnrollments = hadEnrollments;
	}
}
