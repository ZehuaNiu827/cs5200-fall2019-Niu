package edu.northeastern.cs5200.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.models.Course;
import edu.northeastern.cs5200.models.Faculty;

public interface CourseRepository extends CrudRepository<Course, Integer>{
	//@Query("SELECT c FROM Course c"
	//		+ " where c.author_id=:author_id")
	List<Course> findByAuthor(Faculty faculty);
	@Query("select c from Course c where c.label=:label")
	public Course findByLabel(@Param("label") String label);
	
}
