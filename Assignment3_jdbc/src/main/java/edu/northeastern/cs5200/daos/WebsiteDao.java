package edu.northeastern.cs5200.daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import edu.northeastern.cs5200.models.Website;

public class WebsiteDao implements WebsiteImpl{

	private static WebsiteDao instance = null;
	private java.sql.Connection connection;
	private WebsiteDao() {
		try{
			connection = edu.northeastern.cs5200.Connection.getConnection();
		}catch (Exception e) {
			e.printStackTrace();
			}
	}
	public static WebsiteDao getInstance() {
		if(instance == null) {
			instance = new WebsiteDao();
		}
		return instance;
	}
	
	private Statement statement = null;
	private PreparedStatement pStatement = null;
	private ResultSet results = null;
	
	private final String INSERT_WEBSITE = "INSERT INTO website(id, name, description, created, updated, visits, developerId) VALUES(?,?,?,?,?,?,?);";
	private final String FIND_ALL_WEBSITES = "SELECT * FROM website;";
	private final String FIND_WEBSITES_BY_DEVELOPERID = "SELECT * FROM website where developerId=?;";
	private final String FIND_WEBSITE_BY_ID = "SELECT * FROM website where id=?;";
	
	private final String UPDATE_WEBSITE = "UPDATE website SET id=?, name=?, description=?, created=?, updated=?, visits=?, developer=? where id = ?;";
	private final String DELETE_WEBSITE = "DELETE FROM website where id=?;";
	
	@Override
	public void createWebsiteForDeveloper(int developerId, Website website) {
		// TODO Auto-generated method stub
		if(findWebsiteById(website.getId()) == null) {
			try {
				pStatement = connection.prepareStatement(INSERT_WEBSITE);
				pStatement.setInt(1, website.getId());
				pStatement.setString(2, website.getName());
				pStatement.setString(3, website.getDescription());
				pStatement.setDate(4, website.getCreated());
				pStatement.setDate(5, website.getUpdated());
				pStatement.setInt(6, website.getVisits());
				pStatement.setInt(7, website.getDeveloperId());
				pStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public Collection<Website> findAllWebsites() {
		Collection<Website> website = new ArrayList<Website>();
		try {
			statement = connection.createStatement();
			results = statement.executeQuery(FIND_ALL_WEBSITES);
			while(results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int visits = results.getInt("visits");
				int developerId = results.getInt("developerId");
				website.add(new Website(id, name, description, created, updated, visits, developerId));
			}
			results.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return website;
	}

	@Override
	public Collection<Website> findWebsitesForDeveloper(int developerId) {
		// TODO Auto-generated method stub
		Collection<Website> website = new ArrayList<Website>();
		try {
			statement = connection.createStatement();
			results = statement.executeQuery(FIND_WEBSITES_BY_DEVELOPERID);
			while(results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int visits = results.getInt("visits");
				developerId = results.getInt("developerId");
				website.add(new Website(id, name, description, created, updated, visits, developerId));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return website;
	}

	@Override
	public Website findWebsiteById(int websiteId) {
		// TODO Auto-generated method stub
		Website website = null;
		try {
			pStatement = connection.prepareStatement(FIND_WEBSITE_BY_ID);
			pStatement.setInt(1, websiteId);
			results  = pStatement.executeQuery();
			if(results.next()) {
				websiteId = results.getInt("id");
				String name = results.getString("name");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int visits = results.getInt("visits");
				int developerId = results.getInt("developerId");
				
				website = new Website(websiteId, name, description, created, updated, visits, developerId);
			}
			results.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return website;
		
	}

	@Override
	public int updateWebsite(int websiteId, Website website) {
		// TODO Auto-generated method stub
		try {
			pStatement = connection.prepareStatement(UPDATE_WEBSITE);
			pStatement.setInt(1, website.getId());
			pStatement.setString(2, website.getName());
			pStatement.setString(3, website.getDescription());
			pStatement.setDate(4, website.getCreated());
			pStatement.setDate(5, website.getUpdated());
			pStatement.setInt(6, website.getVisits());
			pStatement.setInt(7, website.getDeveloperId());
			pStatement.setInt(8, websiteId);
			pStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int deleteWebsite(int websiteId) {
		// TODO Auto-generated method stub
		try {
			pStatement = connection.prepareStatement(DELETE_WEBSITE);
			pStatement.setInt(1, websiteId);
			pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}