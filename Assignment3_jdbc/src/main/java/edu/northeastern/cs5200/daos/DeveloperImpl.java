package edu.northeastern.cs5200.daos;

import java.util.Collection;

import edu.northeastern.cs5200.models.Developer;

public interface DeveloperImpl {
	//inserts properties in developer instance parameter in tables Developer and Person
	void createDeveloper(Developer developer);
	
	//returns all joined records from Developer and Person tables as a Collection of Developer instances.
	Collection<Developer> findAllDevelopers();
	
	//returns a joined record from Developer and Person tables whose id field is equal to the developerId parameter
	Developer findDeveloperById(int developerId);
	
	//returns a joined record from Developer and Person tables whose username field matches the parameter.
	Developer findDeveloperByUsername(String username);
	
	//returns a joined record from Developer and Person tables whose username and password fields match the parameters
	Developer findDeveloperByCredentials(String username, String password);
	
	//updates records in Developer and Person tables whose id field is equal to developerId parameter. 
	//New record field values are set to the values in the developer instance parameter.
	int updateDeveloper(int developerId, Developer developer);
	
	//deletes records from Developer and Person tables whose id field is equal to developerId parameter.
	int deleteDeveloper(int developerId);
}