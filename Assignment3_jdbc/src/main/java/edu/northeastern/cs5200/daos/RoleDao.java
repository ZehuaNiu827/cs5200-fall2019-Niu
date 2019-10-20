package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class RoleDao implements RoleImpl{
		
		private static RoleDao instance = null;
		private java.sql.Connection connection;
		private RoleDao() {
			try{
				connection = edu.northeastern.cs5200.Connection.getConnection();
			}catch (Exception e) {
				e.printStackTrace();
				}
		}
		public static RoleDao getInstance() {
			if(instance == null)
				instance = new RoleDao();
			return instance;
		}

		private PreparedStatement pStatement = null;
		
		private static final String[] role = new String[5];
		static {
			role[0] = "owner";
			role[1] = "editor";
			role[2] = "admin";
			role[3] = "reviewer";
			role[4] = "writer";
		}
		private final String INSERT_WEBSITE_ROLE = "INSERT INTO Role(id, role, developerId, pageId, websiteId) VALUES(0,?,?,null,?);";
		private final String INSERT_PAGE_ROLE = "INSERT INTO Role(id, role, developerId, pageId, websiteId) VALUES(0,?,?,?,null);";
		private final String DELETE_WEBSITE_ROLE ="DELETE FROM Role where developerId=? and websiteId=? and role=?;";
		private final String DELETE_PAGE_ROLE = "DELETE FROM Role where developerId=? and pageId=? and role=?;";
		private final String DELETE_WEBSITEROLE_BY_WEBSITEID ="DELETE FROM Role where websiteId=?;";
		private final String DELETE_PAGEROLE_BY_PAGEID = "DELETE FROM Role where pageId=?";
		
		@Override
		public void assignWebsiteRole(int developerId, int websiteId, int roleId) {
			// TODO Auto-generated method stub
			try {
				pStatement = connection.prepareStatement(INSERT_WEBSITE_ROLE);
				pStatement.setString(1, role[roleId]);
				pStatement.setInt(2, developerId);
				pStatement.setInt(3, websiteId);
				//System.out.println(pStatement);
				pStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void assignPageRole(int developerId, int pageId, int roleId) {
			// TODO Auto-generated method stub
			try {
				pStatement = connection.prepareStatement(INSERT_PAGE_ROLE);
				pStatement.setString(1, role[roleId]);
				pStatement.setInt(2, developerId);
				pStatement.setInt(3, pageId);
				pStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void deleteWebsiteRole(int developerId, int websiteId, int roleId) {
			// TODO Auto-generated method stub
			try {
				pStatement = connection.prepareStatement(DELETE_WEBSITE_ROLE);
				pStatement.setInt(1, developerId);
				pStatement.setInt(2, websiteId);
				pStatement.setString(3, role[roleId]);
				pStatement.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		@Override
		public void deletePageRole(int developerId, int pageId, int roleId) {
			// TODO Auto-generated method stub
			try {
				pStatement = connection.prepareStatement(DELETE_PAGE_ROLE);
				pStatement.setInt(1, developerId);
				pStatement.setInt(2, pageId);
				pStatement.setString(3, role[roleId]);
				pStatement.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void deletePageRoleById( int pageId) {
			// TODO Auto-generated method stub
			try {
				pStatement = connection.prepareStatement(DELETE_PAGEROLE_BY_PAGEID);
				pStatement.setInt(1, pageId);
				pStatement.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void deleteWebsiteRoleById(int websiteId) {
			// TODO Auto-generated method stub
			try {
				pStatement = connection.prepareStatement(DELETE_WEBSITEROLE_BY_WEBSITEID);
				pStatement.setInt(1, websiteId);
				pStatement.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}