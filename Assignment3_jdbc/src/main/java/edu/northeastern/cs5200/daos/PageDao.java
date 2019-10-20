package edu.northeastern.cs5200.daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.models.Page;


public class PageDao implements PageImpl{
	
	private static PageDao instance = null;
	private java.sql.Connection connection;
	private PageDao() {
		try{
			connection = edu.northeastern.cs5200.Connection.getConnection();
		}catch (Exception e) {
			e.printStackTrace();
			}
	}
	public static PageDao getInstance() {
		if(instance == null) {
			instance = new PageDao();
		}
		return instance;
	}
	
	private Statement statement = null;
	private PreparedStatement pStatement = null;
	private ResultSet results = null;
		
	private final String INSERT_PAGE = "INSERT INTO page(id, title, description, created, updated, views, websiteID) VALUES(?,?,?,?,?,?,?);";
	private final String FIND_ALL_PAGES = "SELECT * FROM page;";
	private final String FIND_PAGE_BY_ID = "SELECT * FROM page where id=?;";
	private final String FIND_PAGE_FOR_WEBSITE= "SELECT * FROM page where websiteID=?";
	private final String UPDATE_PAGE = "UPDATE page SET id=?, title=?, description=?, created=?, updated=?, views=?, websiteID=? where id=?";
	private final String DELETE_PAGE = "DELETE FROM page where id=?";
	
	@Override
	public void createPageForWebsite(int websiteId, Page page) {
		// TODO Auto-generated method stub
		if(findPageById(page.getId()) == null) {
			try {
				pStatement = connection.prepareStatement(INSERT_PAGE);
				pStatement.setInt(1, page.getId());
				pStatement.setString(2, page.getTitle());
				pStatement.setString(3, page.getDescription());
				pStatement.setDate(4, page.getCreated());
				pStatement.setDate(5, page.getUpdated());
				pStatement.setInt(6, page.getViews());
				pStatement.setInt(7, page.getWebsiteId());
				//System.out.println(pStatement);
				pStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public Collection<Page> findAllPages() {
		// TODO Auto-generated method stub
		Collection<Page> page = new ArrayList<Page>();
		try {
			statement = connection.createStatement();
			results = statement.executeQuery(FIND_ALL_PAGES);
			while(results.next()) {
				int id = results.getInt("id");
				String title = results.getString("title");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int views = results.getInt("views");
				int websiteId = results.getInt("websiteId");
				page.add(new Page(id, title, description, created, updated, views, websiteId));
			}
			results.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}

	@Override
	public Page findPageById(int pageId) {
		// TODO Auto-generated method stub
		Page page = null;
		try {
			pStatement = connection.prepareStatement(FIND_PAGE_BY_ID);
			pStatement.setInt(1, pageId);
			results = pStatement.executeQuery();
			if(results.next()) {
				pageId = results.getInt("id");
				String title = results.getString("title");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int views = results.getInt("views");
				int websiteId = results.getInt("websiteId");
				
				page = new Page(pageId, title, description, created, updated, views, websiteId);
			}
			results.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return page;
	}

	@Override
	public Collection<Page> findPagesForWebsite(int websiteId) {
		// TODO Auto-generated method stub
		Collection<Page> page = new ArrayList<Page>();
		try {
			pStatement = connection.prepareStatement(FIND_PAGE_FOR_WEBSITE);
			pStatement.setInt(1, websiteId);
			results = pStatement.executeQuery();
			//System.out.println(pStatement);
			while(results.next()) {
				int id = results.getInt("id");
				String title = results.getString("title");
				String description = results.getString("description");
				Date created = results.getDate("created");
				Date updated = results.getDate("updated");
				int views = results.getInt("views");
				websiteId = results.getInt("websiteId");
				page.add(new Page(id, title, description, created, updated, views, websiteId));
			}
			results.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}

	@Override
	public int updatePage(int pageId, Page page) {
		// TODO Auto-generated method stub
		try {
			pStatement = connection.prepareStatement(UPDATE_PAGE);
			pStatement.setInt(1, page.getId());
			pStatement.setString(2, page.getTitle());
			pStatement.setString(3, page.getDescription());
			pStatement.setDate(4, page.getCreated());
			pStatement.setDate(5, page.getUpdated());
			pStatement.setInt(6, page.getViews());
			pStatement.setInt(7, page.getWebsiteId());
			pStatement.setInt(8, pageId);
			pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deletePage(int pageId) {
		// TODO Auto-generated method stub
		try {
			pStatement = connection.prepareStatement(DELETE_PAGE);
			pStatement.setInt(1, pageId);
			pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}