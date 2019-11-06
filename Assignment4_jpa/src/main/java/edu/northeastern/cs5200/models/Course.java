package edu.northeastern.cs5200.models;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Course")
public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String label;
	
	
	public Course() {}
	public Course(int id, String label) {
		super();
		this.id = id;
		this.label = label;
	}

	@OneToMany(mappedBy="cTos", fetch=FetchType.EAGER)
	private List<Section> courseSections = new ArrayList<Section>();
	
	public void courseSections(Section section) {
		this.courseSections.add(section);
		if(section.getcTos()!=this)
			section.setcTos(this);
	}
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private Faculty author;
	
	
	
	public List<Section> getCourseSections() {
		return courseSections;
	}

	public void setCourseSections(List<Section> courseSections) {
		this.courseSections = courseSections;
	}

	public Faculty getAuthor() {
		return author;
	}

	public void setAuthor(Faculty author) {
		this.author = author;
		if(!author.getAuthoredCourses().contains(this)) {
			author.getAuthoredCourses().add(this);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
