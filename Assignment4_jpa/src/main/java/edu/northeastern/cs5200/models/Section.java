package edu.northeastern.cs5200.models;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import edu.northeastern.cs5200.models.Enrollment;
@Entity

public class Section {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	private int seats;
	
	public Section() {}
	public Section(int id, String title, int seats) {
		super();
		this.id = id;
		this.title = title;
		this.seats = seats;

	}
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private Course cTos;
	
	@OneToMany(mappedBy="section", fetch = FetchType.EAGER)
	private List<Enrollment> hadEnrollments = new ArrayList<Enrollment>();
	
	public void addEnrollments(Enrollment enrollment) {
		this.hadEnrollments.add(enrollment);
		if(!this.getHadEnrollments().contains(enrollment)) {
			this.getHadEnrollments().add(enrollment);
			}
		}
	
	
	public List<Enrollment> getHadEnrollments() {
		return hadEnrollments;
	}


	public void setHadEnrollments(List<Enrollment> hadEnrollments) {
		this.hadEnrollments = hadEnrollments;
	}

	public Course getcTos() {
		return cTos;
	}
	public void setcTos(Course cTos) {
		this.cTos = cTos;
		if(!cTos.getCourseSections().contains(this)) {
			cTos.getCourseSections().add(this);
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	
}
