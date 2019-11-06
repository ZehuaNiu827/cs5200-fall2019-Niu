package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.models.Faculty;

public interface FacultyRepository extends CrudRepository<Faculty, Integer>{//, JpaSpecificationExecutor<Faculty>{
	@Query("select p from Person p where p.firstName=:firstName")
	public Faculty findByName(@Param("firstName") String firstName);
}
