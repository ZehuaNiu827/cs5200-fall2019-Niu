package edu.northeastern.cs5200;

import java.util.List;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.northeastern.cs5200.daos.UniversityDao;
import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.repositories.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestOverall {
	
	@Autowired
	PersonRepository personRepository;
	@Autowired
	FacultyRepository facultyRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	SectionRepository sectionRepository;
	@Autowired
	EnrollmentRepository enrollmentRepository;
	@Autowired
	UniversityDao udao;
	
	
	private static boolean initialized = false;
	
	@Before
	public void A_testDelete() {
		if (!initialized) {
			udao.truncateDatabase();
			initialized = true;
		}
	}
	
	
	@Test
	public void B_testCreateFaculty() {
		Faculty Alan = new Faculty(1, "Alan", "Turin", "alan", "password", "123A", true);
		Faculty Ada = new Faculty(1, "Ada", "Lovelace", "ada", "password", "123B", true);
		udao.createFaculty(Alan);
		udao.createFaculty(Ada);
		
	}
	
	
	
	
	@Test
	public void C_testCreateStudent() {
	//	UniversityDao udao = UniversityDao.getInstance();
		
		Student Alice = new Student(1, "Alice", "Wonderland", "alice", "password", 2020, 12000);
		Student Bob = new Student(1, "Bob", "Hope", "bob", "password", 2021, 23000);
		Student Charlie = new Student(1, "Charlie", "Brown", "charlie", "password", 2019, 21000);
		Student Dan = new Student(1, "Dan", "Craig", "dan", "password", 2019, 0);
		Student Edward = new Student(1, "Edward", "Scissorhands", "edward", "password", 2022, 11000);
		Student Frank = new Student(1, "Frank", "Herbert", "frank", "password", 2018, 0);
		Student Gregory = new Student(1, "Gregory", "Peck", "edward", "password", 2023, 10000);
		udao.createStudent(Alice);
		udao.createStudent(Bob);
		udao.createStudent(Charlie);
		udao.createStudent(Dan);
		udao.createStudent(Edward);
		udao.createStudent(Frank);
		udao.createStudent(Gregory);
	}
	
	@Test
	public void D_testCreateCourse() {

		Course CS1234 = new Course(1, "CS1234");
		Course CS2345 = new Course(1, "CS2345");
		Course CS3456 = new Course(1, "CS3456");
		Course CS4567 = new Course(1, "CS4567");
		
		udao.createCourse(CS1234);
		udao.createCourse(CS2345);
		udao.createCourse(CS3456);
		udao.createCourse(CS4567);
		
		udao.setAuthorForCourse(facultyRepository.findByName("Alan"), courseRepository.findByLabel("CS1234"));
		udao.setAuthorForCourse(facultyRepository.findByName("Alan"), courseRepository.findByLabel("CS2345"));
		udao.setAuthorForCourse(facultyRepository.findByName("Ada"), courseRepository.findByLabel("CS3456"));
		udao.setAuthorForCourse(facultyRepository.findByName("Ada"), courseRepository.findByLabel("CS4567"));
	}
	
	@Test
	public void E_testCreateSection() {

		Section SEC4321 = new Section(1, "SEC4321", 50);
		Section SEC5432 = new Section(1, "SEC5432", 50);
		Section SEC6543 = new Section(1, "SEC6543", 50);
		Section SEC7654 = new Section(1, "SEC7654", 50);
		
		udao.createSection(SEC4321);
		udao.createSection(SEC5432);
		udao.createSection(SEC6543);
		udao.createSection(SEC7654);
		
		udao.addSectionToCourse(sectionRepository.findByName("SEC4321"), courseRepository.findByLabel("CS1234"));
		udao.addSectionToCourse(sectionRepository.findByName("SEC5432"), courseRepository.findByLabel("CS1234"));
		udao.addSectionToCourse(sectionRepository.findByName("SEC6543"), courseRepository.findByLabel("CS2345"));
		udao.addSectionToCourse(sectionRepository.findByName("SEC7654"), courseRepository.findByLabel("CS2345"));
		
	}	
	
	
	@Test
	public void F_testAddErollmentRelation() {
		
		udao.enrollStudentInSection(studentRepository.findByName("Alice"), sectionRepository.findByName("SEC4321"));
		udao.enrollStudentInSection(studentRepository.findByName("Alice"), sectionRepository.findByName("SEC5432"));
		udao.enrollStudentInSection(studentRepository.findByName("Bob"), sectionRepository.findByName("SEC5432"));
		udao.enrollStudentInSection(studentRepository.findByName("Charlie"), sectionRepository.findByName("SEC6543"));

		}	
	
	@Test
	public void testfindAllPerson() {
		List<Person> persons = udao.findAllPersons();
		System.out.println("The total number of person is" + persons.size());
	}
	
}
