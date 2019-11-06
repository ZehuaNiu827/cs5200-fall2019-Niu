package edu.northeastern.cs5200.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.models.Course;
import edu.northeastern.cs5200.models.Section;
import edu.northeastern.cs5200.models.Student;

public interface SectionRepository extends CrudRepository<Section, Integer>{
	
	//List<Section> findByCTos(Course course); 
	//List<Section> findByEnrolledStudents(Student student); 	

	@Query("select s from Section s where s.title=:title")
	public Section findByName(@Param("title")String title);
}
