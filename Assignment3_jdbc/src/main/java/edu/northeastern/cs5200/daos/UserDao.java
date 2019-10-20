package edu.northeastern.cs5200.daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.models.User;


public class UserDao {

	private static UserDao instance = null;
	private java.sql.Connection connection;
	private UserDao() {

		try{
			connection = edu.northeastern.cs5200.Connection.getConnection();
		}catch (Exception e) {
			e.printStackTrace();
			}
	}
	public static UserDao getInstance() {
		if(instance == null) {
			instance = new UserDao();
		}
		return instance;
	}
	
	
	
	private Statement statement =null;
	private PreparedStatement pStatement = null;
	private ResultSet results = null;
	
	private final String INSERT_PERSON = "INSERT INTO person(id, firstName, lastName, username, password, email, dob) VALUES(?,?,?,?,?,?,?);";
	private final String INSERT_USER= "INSERT INTO user(id, userAgreement) VALUES(?,?);";
	private final String FIND_ALL_USERS = "SELECT * FROM user, person, where person.id = user.id;";
	private final String FIND_USER_BY_ID = "SELECT * FROM user join person on person.id = user.id and user.id=?;";
	private final String FIND_USER_BY_USERNAME = "SELECT * FROM user join person on person.id = user.id and user.USERNAME=?;";
	private final String FIND_USER_BY_CREDENTIAL = "SELECT * FROM user join person on person.id = user.id and user.USERNAME=? and user.PASSWORD=?;";
	
	private final String UPDATE_USER = "UPDATE person SET id=?,firstName=?, lastName=?, username=?, password=?, email=?, dob=? where id = ?;"
											+"UPDATE user SET userAgreement=? where id=?;";
	private final String DELETE_USER = "DELETE FROM user where id =?;" + "DELETE FROM user where id = ?;";
	
	public void createUser(User user) {
		// TODO Auto-generated method stub  
		if(findUserById(user.getId()) == null) {
			try {
				pStatement = connection.prepareStatement(INSERT_PERSON);
				pStatement.setInt(1, user.getId());
				pStatement.setString(2, user.getFirstName());
				pStatement.setString(3, user.getLastName());
				pStatement.setString(4, user.getUsername());
				pStatement.setString(5, user.getPassword());
				pStatement.setString(6, user.getEmail());
				pStatement.setDate(7, user.getDob());
				pStatement.executeUpdate();
				
				pStatement = connection.prepareStatement(INSERT_USER);
				pStatement.setInt(1, user.getId());
				pStatement.setBoolean(2, user.isUserAgreement());
				pStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Collection<User> findAllUsers() {
		// TODO Auto-generated method stub
		Collection<User> user = new ArrayList<User>();
		try {
			statement = connection.createStatement();
			results = statement.executeQuery(FIND_ALL_USERS);
			while(results.next()) {
				Boolean userAgreement = results.getBoolean("userAgreement");
				int id = results.getInt("id");
				String firstName = results.getString("firstName");
				String lastName = results.getString("lastName");
				String username = results.getString("username");
				String password = results.getString("password");
				String email = results.getString("email");
				Date dob = results.getDate("dob");
				user.add(new User(id, firstName, lastName, username, password, email, dob, userAgreement));
			}
			results.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}


	public User findUserById(int userId) {
		// TODO Auto-generated method stub
		User user = null;
		try {
			pStatement = connection.prepareStatement(FIND_USER_BY_ID);
			pStatement.setInt(1, userId);
			results  = pStatement.executeQuery();
			if(results.next()) {
				Boolean userAgreement = results.getBoolean("userAgreement");
				userId = results.getInt("id");
				String firstName = results.getString("firstName");
				String lastName = results.getString("lastName");
				String username = results.getString("username");
				String password = results.getString("password");
				String email = results.getString("email");
				Date dob = results.getDate("dob");
				user = new User(userId, firstName, lastName, username, password, email, dob, userAgreement);
			}
			results.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}


	public User findUserByUsername(String username) {
		// TODO Auto-generated method stub
		User user = null;
		try {
			pStatement = connection.prepareStatement(FIND_USER_BY_USERNAME);
			pStatement.setString(1, username);
			results  = pStatement.executeQuery();
			if(results.next()) {
				Boolean userAgreement = results.getBoolean("userAgreement");
				username = results.getString("username");
				int id = results.getInt("id");
				String firstName = results.getString("firstName");
				String lastName = results.getString("lastName");
				String password = results.getString("password");
				String email = results.getString("email");
				Date dob = results.getDate("dob");
				user = new User(id, firstName, lastName, username, password, email, dob, userAgreement);
			}
			results.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

	public User findUserByCredentials(String username, String password) {
		// TODO Auto-generated method stub
		User user = null;
		try {
			pStatement = connection.prepareStatement(FIND_USER_BY_CREDENTIAL);
			pStatement.setString(1, username);
			results  = pStatement.executeQuery();
			if(results.next()) {
				Boolean userAgreement = results.getBoolean("userAgreement");
				username = results.getString("username");
				password = results.getString("password");
				int id = results.getInt("id");
				String firstName = results.getString("firstName");
				String lastName = results.getString("lastName");
				String email = results.getString("email");
				Date dob = results.getDate("dob");
				user = new User(id, firstName, lastName, username, password, email, dob, userAgreement);
			}
			results.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

	
	public int updateUser(int userId, User user) {
		// TODO Auto-generated method stub
		try {
			pStatement = connection.prepareStatement(UPDATE_USER);
			pStatement.setInt(1, user.getId());
			pStatement.setString(2, user.getFirstName());
			pStatement.setString(3, user.getLastName());
			pStatement.setString(4, user.getUsername());
			pStatement.setString(5, user.getPassword());
			pStatement.setString(6, user.getEmail());
			pStatement.setDate(7, user.getDob());
			pStatement.setInt(8, userId);
			pStatement.setBoolean(9, user.isUserAgreement());
			pStatement.setInt(10, userId);
			//pStatement.setObject(11, user.getAddress());
			pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}


	public int deleteUser(int userId) {
		try {
			pStatement = connection.prepareStatement(DELETE_USER);
			pStatement.setInt(1, userId);
			pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return 0;
	}

}