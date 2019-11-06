package edu.northeastern.cs5200;

import java.util.*;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.*;
import edu.northeastern.cs5200.daos.UniversityDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidateData {
	@Autowired
	UniversityDao ud;
	
	@Test
	public void testValidateUsers() {
		int n = 9;
		List<Person> users = ud.findAllPersons();
		assert(n==users.size());
	}
	
	@Test
	public void testValidateFaculty() {
		int n = 2;
		List<Faculty> users = ud.findAllFaculty();
		assert(n==users.size());
	}
	
	@Test
	public void testValidateStudent() {
		int n = 7;
		List<Student> users = ud.findAllStudents();
		assert(n==users.size());
	}
	
	@Test
	public void testValidateCourse() {
		int n = 4;
		List<Course> users = ud.findAllCourses();
		assert(n==users.size());
	}
	
	@Test
	public void testValidateSection() {
		int n = 4;
		List<Section> users = ud.findAllSections();
		assert(n==users.size());
	}
	
	@Test
	public void testValidateAuthor() {
		System.out.println("testValidateAuthor");
		List<Faculty> faculties = ud.findAllFaculty();
		for(Faculty f:faculties) {
			System.out.printf("Faculty "+ f.getFirstName()+" "+f.getLastName()+
					" authors %d courses.%n",f.getAuthoredCourses().size());
		}
	}
	
	@Test
	public void testSectionPerCourse() {
		System.out.println("testSectionPerCourse");
		List<Course> courses = ud.findAllCourses();
		for(Course c:courses) {
			System.out.printf("Course "+c.getLabel()+" has %d sections%n", c.getCourseSections().size());
		}
	}
	
	@Test
	public void testStudentPerSection() {
		System.out.println("testStudentPerSection");
		List<Section> sections = ud.findAllSections();
		for(Section s:sections) {
			System.out.printf("Section "+s.getTitle()+" is enrolled by %d students%n", s.getHadEnrollments().size());
		}
	}
	
	@Test
	public void testSectionPerStudent() {
		System.out.println("testSectionPerStudent");
		List<Student> students = ud.findAllStudents();
		for(Student s:students) {
			System.out.printf("Student "+s.getFirstName()+" "+s.getLastName()+" has %d sections%n", s.getHadEnrollments().size());
		}
	}
	
	@Test
	public void testSeatsPerSection() {
		System.out.println("testSeatsPerSection");
		List<Section> sections = ud.findAllSections();
		for(Section s:sections) {
			System.out.printf("Section "+s.getTitle()+"has %d seats%n", s.getSeats());
		}
	}
}