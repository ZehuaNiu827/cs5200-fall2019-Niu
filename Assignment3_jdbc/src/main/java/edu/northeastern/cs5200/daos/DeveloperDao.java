package edu.northeastern.cs5200.daos;

import java.util.ArrayList;
import java.util.Collection;
import java.sql.*;

import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.Phone;

public class DeveloperDao implements DeveloperImpl{

	private static DeveloperDao instance = null;
	private java.sql.Connection connection;
	private DeveloperDao() {

		try{
			connection = edu.northeastern.cs5200.Connection.getConnection();
		}catch (Exception e) {
			e.printStackTrace();
			}
	}
	public static DeveloperDao getInstance() {
		if(instance == null) {
			instance = new DeveloperDao();
		}
		return instance;
	}
	
	
	
	private Statement statement =null;
	private PreparedStatement pStatement = null;
	private ResultSet results = null;
	
	private final String INSERT_PERSON = "INSERT INTO person(id, firstName, lastName, username, password, email, dob) VALUES(?,?,?,?,?,?,?);";
	private final String INSERT_DEVELOPER = "INSERT INTO developer(id, developerkey) VALUES(?,?);";
	private final String INSERT_PHONE = "INSERT INTO phone VALUES(?,?,?,?)";
	private final String INSERT_ADDRESS = "INSERT INTO address VALUES(?,?,?,?,?,?,?,?)";
	private final String FIND_ALL_DEVELOPERS = "SELECT * FROM developer, person, where person.id = developer.id;";
	private final String FIND_DEVELOPER_BY_ID = "SELECT * FROM developer join person on person.id = developer.id and developer.id=?;";
	private final String FIND_DEVELOPER_BY_USERNAME = "SELECT * FROM developer join person on person.id = developer.id and developer.USERNAME=?;";
	private final String FIND_DEVELOPER_BY_CREDENTIAL = "SELECT * FROM developer join person on person.id = developer.id and developer.USERNAME=? and developer.PASSWORD=?;";
	private final String FIND_PHONE = "SELECT * FROM phone where personId = ?";
	private final String FIND_ADDRESS = "SELECT * FROM address where personId = ?";
	
	private final String UPDATE_PERSON = "UPDATE person SET id=?,firstName=?, lastName=?, username=?, password=?, email=?, dob=? where id = ?;";
	private final String UPDATE_DEVELOPER = "UPDATE developer SET developerkey=? where id=?;";
	private final String UPDATE_PHONE = "UPDATE phone SET id=?, phone=?, primary=? where id = ? and personId = ?";
	private final String UPDATE_ADDRESS = "UPDATE address SET id = ?,street1 = ?,street2 = ?,city = ?,state = ?,zip = ?,primary = ? where id = ? and personId = ?";
	private final String DELETE_DEVELOPER = "DELETE FROM person where id =?;" + "DELETE FROM developer where id = ?;";
	private final String DELETE_PHONE = "DELETE FROM phone where personId = ?;";
	private final String DELETE_ADDRESS = "DELETE FROM address where personId = ?;";
	
	@Override
	public void createDeveloper(Developer developer) {
		// TODO Auto-generated method stub  
		if(findDeveloperById(developer.getId()) == null) {
			try {
				pStatement = connection.prepareStatement(INSERT_PERSON);
				pStatement.setInt(1, developer.getId());
				pStatement.setString(2, developer.getFirstName());
				pStatement.setString(3, developer.getLastName());
				pStatement.setString(4, developer.getUsername());
				pStatement.setString(5, developer.getPassword());
				pStatement.setString(6, developer.getEmail());
				pStatement.setDate(7, developer.getDob());
				//System.out.println(pStatement);
				pStatement.executeUpdate();
				
				pStatement = connection.prepareStatement(INSERT_DEVELOPER);
				pStatement.setInt(1, developer.getId());
				pStatement.setString(2, developer.getDeveloperKey());
				pStatement.executeUpdate();
				
				if (developer.getPhone() != null) {
					for (int i = 0; i < developer.getPhone().length; i++) {
						pStatement = connection.prepareStatement(INSERT_PHONE);
						pStatement.setInt(1, developer.getPhone()[i].getId());
						pStatement.setString(2, developer.getPhone()[i].getPhone());
						pStatement.setBoolean(3, developer.getPhone()[i].isPrimary());
						pStatement.setInt(4, developer.getId());
						pStatement.executeUpdate();
					}
				}
				
				if (developer.getAddress() != null) {
					for (int i = 0; i < developer.getAddress().length; i++) {
						pStatement = connection.prepareStatement(INSERT_ADDRESS);
						pStatement.setInt(1, developer.getAddress()[i].getId());
						pStatement.setString(2, developer.getAddress()[i].getStreet1());
						pStatement.setString(3, developer.getAddress()[i].getStreet2());
						pStatement.setString(4, developer.getAddress()[i].getCity());
						pStatement.setString(5, developer.getAddress()[i].getState());
						pStatement.setString(6, developer.getAddress()[i].getZip());
						pStatement.setBoolean(7, developer.getAddress()[i].isPrimary());
						pStatement.setInt(8, developer.getAddress()[i].getPersonId());
						//System.out.println(pStatement);
						pStatement.executeUpdate();
					}
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private Phone[] findPhone(int DeveloperId) throws SQLException {
		statement = connection.createStatement();
		results = statement.executeQuery(FIND_PHONE);
		ArrayList<Phone> phone = new ArrayList<Phone>();
		while(results.next()) 
			phone.add(new Phone(results.getInt(1), results.getString(2), results.getBoolean(3), results.getInt(4)));
		results.close();
		return (phone.toArray(new Phone[0]));
	}
	
	private Address[] findAddress(int DeveloperId) throws SQLException {
		statement = connection.createStatement();
		results = statement.executeQuery(FIND_ADDRESS);
		ArrayList<Address> address = new ArrayList<Address>();
		while(results.next()) 
			address.add(new Address(results.getInt(1), results.getString(2), results.getString(3), results.getString(4), results.getString(5), results.getString(6), results.getBoolean(7), results.getInt(8)));
		results.close();
		return (address.toArray(new Address[0]));
	}

	@Override
	public Collection<Developer> findAllDevelopers() {
		// TODO Auto-generated method stub
		Collection<Developer> developer = new ArrayList<Developer>();
		try {
			statement = connection.createStatement();
			results = statement.executeQuery(FIND_ALL_DEVELOPERS);
			while(results.next()) {
				String developerKey = results.getString("developerKey");
				int id = results.getInt("id");
				String firstName = results.getString("firstName");
				String lastName = results.getString("lastName");
				String username = results.getString("username");
				String password = results.getString("password");
				String email = results.getString("email");
				Date dob = results.getDate("dob");
				developer.add(new Developer(developerKey, id, firstName, lastName, username, password, email, dob, findAddress(id), findPhone(id)));
			}
			results.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return developer;
	}

	@Override
	public Developer findDeveloperById(int developerId) {
		// TODO Auto-generated method stub
		Developer developer = null;
		try {
			pStatement = connection.prepareStatement(FIND_DEVELOPER_BY_ID);
			pStatement.setInt(1, developerId);
			results  = pStatement.executeQuery();
			if(results.next()) {
				String developerKey = results.getString("developerKey");
				developerId = results.getInt("id");
				String firstName = results.getString("firstName");
				String lastName = results.getString("lastName");
				String username = results.getString("username");
				String password = results.getString("password");
				String email = results.getString("email");
				Date dob = results.getDate("dob");
				developer = new Developer(developerKey, developerId, firstName, lastName, username, password, email, dob, findAddress(developerId), findPhone(developerId));
			}
			results.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return developer;
	}

	@Override
	public Developer findDeveloperByUsername(String username) {
		// TODO Auto-generated method stub
		Developer developer = null;
		try {
			pStatement = connection.prepareStatement(FIND_DEVELOPER_BY_USERNAME);
			pStatement.setString(1, username);
			results  = pStatement.executeQuery();
			if(results.next()) {
				String developerKey = results.getString("developerKey");
				username = results.getString("username");
				int id = results.getInt("id");
				String firstName = results.getString("firstName");
				String lastName = results.getString("lastName");
				String password = results.getString("password");
				String email = results.getString("email");
				Date dob = results.getDate("dob");
				developer = new Developer(developerKey, id, firstName, lastName, username, password, email, dob, findAddress(id), findPhone(id));
			}
			results.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return developer;
	}

	@Override
	public Developer findDeveloperByCredentials(String username, String password) {
		// TODO Auto-generated method stub
		Developer developer = null;
		try {
			pStatement = connection.prepareStatement(FIND_DEVELOPER_BY_CREDENTIAL);
			pStatement.setString(1, username);
			results  = pStatement.executeQuery();
			if(results.next()) {
				String developerKey = results.getString("developerKey");
				username = results.getString("username");
				password = results.getString("password");
				int id = results.getInt("id");
				String firstName = results.getString("firstName");
				String lastName = results.getString("lastName");
				String email = results.getString("email");
				Date dob = results.getDate("dob");
				developer = new Developer(developerKey, id, firstName, lastName, username, password, email, dob, findAddress(id), findPhone(id));
			}
			results.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return developer;
	}

	@Override
	public int updateDeveloper(int developerId, Developer developer) {
		// TODO Auto-generated method stub
		try {
			pStatement = connection.prepareStatement(UPDATE_PERSON);
			pStatement.setInt(1, developer.getId());
			pStatement.setString(2, developer.getFirstName());
			pStatement.setString(3, developer.getLastName());
			pStatement.setString(4, developer.getUsername());
			pStatement.setString(5, developer.getPassword());
			pStatement.setString(6, developer.getEmail());
			pStatement.setDate(7, developer.getDob());
			pStatement.setInt(8, developerId);
			pStatement.executeUpdate();
			pStatement = connection.prepareStatement(UPDATE_DEVELOPER);
			pStatement.setString(1, developer.getDeveloperKey());
			pStatement.setInt(2, developerId);
			//System.out.println(pStatement);
			pStatement.executeUpdate();
			
			pStatement = connection.prepareStatement(DELETE_PHONE);
			pStatement.setInt(1, developerId);
			pStatement.executeUpdate();
			if(developer.getPhone() != null) {
				for (int i = 0; i < developer.getPhone().length; i++) {
					pStatement = connection.prepareStatement(INSERT_PHONE);
					pStatement.setInt(1, developer.getPhone()[i].getId());
					pStatement.setString(2, developer.getPhone()[i].getPhone());
					pStatement.setBoolean(3, developer.getPhone()[i].isPrimary());
					pStatement.setInt(4, developer.getId());
					//System.out.println(pStatement);
					pStatement.executeUpdate();
					
				}
			}
			
			pStatement = connection.prepareStatement(DELETE_ADDRESS);
			pStatement.setInt(1, developerId);
			pStatement.executeUpdate();
			if (developer.getAddress() != null) {
				for (int i = 0; i < developer.getAddress().length; i++) {
					pStatement = connection.prepareStatement(INSERT_ADDRESS);
					pStatement.setInt(1, developer.getAddress()[i].getId());
					pStatement.setString(2, developer.getAddress()[i].getStreet1());
					pStatement.setString(3, developer.getAddress()[i].getStreet2());
					pStatement.setString(4, developer.getAddress()[i].getCity());
					pStatement.setString(5, developer.getAddress()[i].getState());
					pStatement.setString(6, developer.getAddress()[i].getZip());
					pStatement.setBoolean(7, developer.getAddress()[i].isPrimary());
					pStatement.setInt(8, developer.getId());
					pStatement.executeUpdate();
				}
			}
			
			//pStatement.setObject(11, developer.getAddress());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteDeveloper(int developerId) {
		try {
			pStatement = connection.prepareStatement(DELETE_DEVELOPER);
			pStatement.setInt(1, developerId);
			pStatement.executeUpdate();
			pStatement = connection.prepareStatement(DELETE_PHONE);
			pStatement.setInt(1, developerId);
			pStatement.executeUpdate();
			pStatement = connection.prepareStatement(DELETE_ADDRESS);
			pStatement.setInt(1, developerId);
			pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return 0;
	}

}