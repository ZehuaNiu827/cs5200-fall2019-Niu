package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class PriviledgeDao implements PrivilegeImpl{

	private static PriviledgeDao instance = null;
	private java.sql.Connection connection;
	private PriviledgeDao() {
		try{
			connection = edu.northeastern.cs5200.Connection.getConnection();
		}catch (Exception e) {
			e.printStackTrace();
			}
	}
	public static PriviledgeDao getInstance() {
		if(instance == null)
			instance = new PriviledgeDao();
		return instance;
	}
	
	private PreparedStatement pStatement = null;
	
	private final String INSERT_WEBSITE_PRIVILEGE = "INSERT INTO Priviledge(id, priviledge, develperId, pageId, websiteId) VALUES(null,?,?,null,?);";
	private final String INSERT_PAGE_PRIVILEGE = "INSERT INTO Priviledge(id, priviledge, develperId, pageId, websiteId) VALUES(null,?,?,?,null);";
	private final String DELETE_WEBSITE_PRIVILEGE ="DELETE FROM Priviledge where developerId=? and websiteId=? and priviledge=?;";
	private final String DELETE_PAGE_PRIVILEGE = "DELETE FROM Priviledge where developerId=? and pageId=? and priviledge=?;";
	private final String DELETE_PAGEPRIVILEGE_BY_PAGEID = "DELETE FROM Priviledge where pageId=?;";
	private final String DELETE_WEBSITEPRIVILEGE_BY_WEBSITE_ID ="DELETE FROM Priviledge where websiteId=?;";
	
	@Override
	public void assignWebsitePriviledge(int developerId, int websiteId, String priviledge) {
		// TODO Auto-generated method stub
		try {
			pStatement = connection.prepareStatement(INSERT_WEBSITE_PRIVILEGE);
			pStatement.setString(1, priviledge);
			pStatement.setInt(2, developerId);
			pStatement.setInt(3, websiteId);
			pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public void assignPagePriviledge(int developerId, int pageId, String priviledge) {
		// TODO Auto-generated method stub
		try {
			pStatement = connection.prepareStatement(INSERT_PAGE_PRIVILEGE);
			pStatement.setString(1, priviledge);
			pStatement.setInt(2, developerId);
			pStatement.setInt(3, pageId);
			pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void deleteWebsitePriviledge(int developerId, int websiteId, String priviledge) {
		// TODO Auto-generated method stub
		try {
			pStatement = connection.prepareStatement(DELETE_WEBSITE_PRIVILEGE);
			pStatement.setInt(1, developerId);
			pStatement.setInt(2, websiteId);
			pStatement.setString(3, priviledge);
			pStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deletePagePriviledge(int developerId, int pageId, String priviledge) {
		// TODO Auto-generated method stub
		try {
			pStatement = connection.prepareStatement(DELETE_PAGE_PRIVILEGE);
			pStatement.setInt(1, developerId);
			pStatement.setInt(2, pageId);
			pStatement.setString(3, priviledge);
			pStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deletePagePriviledgeById(int pageId) {
		// TODO Auto-generated method stub
		try {
			pStatement = connection.prepareStatement(DELETE_PAGEPRIVILEGE_BY_PAGEID);
			pStatement.setInt(1, pageId);
			pStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteWebsitePriviledgeById(int websiteId) {
		// TODO Auto-generated method stub
		try {
			pStatement = connection.prepareStatement(DELETE_WEBSITEPRIVILEGE_BY_WEBSITE_ID);
			pStatement.setInt(1, websiteId);
			pStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}