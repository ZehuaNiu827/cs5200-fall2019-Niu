package edu.northeastern.cs5200.daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;


import edu.northeastern.cs5200.models.*;

public class WidgetDao implements WidgetImpl{

		private static WidgetDao instance = null;
		private java.sql.Connection connection;
		private WidgetDao() {
			try{
				connection = edu.northeastern.cs5200.Connection.getConnection();
			}catch (Exception e) {
				e.printStackTrace();
				}
		}
		
		public static WidgetDao getInstance() {
			if(instance == null) {
				instance = new WidgetDao();
			}
			return instance;
		}
		
		
		private Statement statement = null;
		private PreparedStatement pStatement = null;
		private ResultSet results = null;
		
		private final String INSERT_WIDGET = "INSERT INTO widget VALUES(?,?,?,?,?,?,?,?,?);";
		private final String FIND_ALL_WIDGETS = "SELECT *FROM widget;";
		private final String FIND_WIDGET_BY_ID = "SELECT *FROM widget where id=?;";
		private final String FIND_WIDGET_FOR_PAGE = "SELECT *FROM widget where pageId=?;";
		private final String UPDATE_WIDGET = "UPDATE widget SET id=?, name=?, width=?, height=?, cssStyle=?, cssClass=?, text=?, `order`=?, pageId=? where id=?;";
		private final String DELETE_WIDGET = "DELETE FROM widget where id=?";
		
		
	@Override
	public void createWidgetForPage(int pageId, Widget widget) {
		// TODO Auto-generated method stub
		
		if(findWidgetById(widget.getId()) == null) {
			try {
				pStatement = connection.prepareStatement(INSERT_WIDGET);
				pStatement.setInt(1, widget.getId());
				pStatement.setString(2, widget.getName());
				pStatement.setInt(3, widget.getWidth());
				pStatement.setInt(4, widget.getHeight());
				pStatement.setString(5, widget.getCssClass());
				pStatement.setString(6, widget.getCssStyle());
				pStatement.setString(7, widget.getText());
				pStatement.setInt(8, widget.getOrder());
				pStatement.setInt(9, widget.getPageId());
				//System.out.println(pStatement);
				pStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(widget instanceof YouTubeWidget) {
			try {
				pStatement = connection.prepareStatement("INSERT INTO YouTube(id, url, shareble, expandable) VALUES(?,?,?,?);");
				pStatement.setInt(1, widget.getId());
				pStatement.setString(2,((YouTubeWidget)widget).getUrl());
				pStatement.setBoolean(3, ((YouTubeWidget) widget).isShareble());
				pStatement.setBoolean(4, ((YouTubeWidget) widget).isExpandable());
				
				pStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(widget instanceof HeadingWidget) {
			try {
				pStatement = connection.prepareStatement("INSERT INTO Heading(id, size) VALUES(?,?);");
				pStatement.setInt(1, widget.getId());
				pStatement.setInt(2,((HeadingWidget)widget).getSize());
				//System.out.println(pStatement);
				pStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(widget instanceof ImageWidget) {
			try {
				pStatement = connection.prepareStatement("INSERT INTO Image(id, src) VALUES(?,?);");
				pStatement.setInt(1,  widget.getId());
				pStatement.setString(2,((ImageWidget)widget).getSrc());
				
				pStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(widget instanceof HtmlWidget) {
			try {
				pStatement = connection.prepareStatement("INSERT INTO Html(id, html) VALUES(?,?);");
				pStatement.setInt(1, widget.getId());
				pStatement.setString(2,((HtmlWidget)widget).getHtml());
				
				pStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}

	@Override
	public Collection<Widget> findAllWidgets() {
		Collection<Widget> widget= new ArrayList<Widget>();
		try {
			statement = connection.createStatement();
			results = statement.executeQuery(FIND_ALL_WIDGETS);
			while(results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				int width = results.getInt("width");
				int height = results.getInt("height");
				String cssClass = results.getString("cssClass");
				String cssStyle = results.getString("cssStyle");
				String text = results.getString("text");
				int order = results.getInt("order");
				int pageId = results.getInt("pageId");
				
				widget.add(new Widget(id, name, width, height, cssClass, cssStyle, text, order, pageId));
			}
			results.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return widget;
	}

	@Override
	public Widget findWidgetById(int widgetId) {
		// TODO Auto-generated method stub
		Widget widget = null;
		try {
			pStatement = connection.prepareStatement(FIND_WIDGET_BY_ID);
			pStatement.setInt(1, widgetId);
			results  = pStatement.executeQuery();
			if(results.next()) {
				widgetId = results.getInt("id");
				String name = results.getString("name");
				int width = results.getInt("width");
				int height = results.getInt("height");
				String cssClass = results.getString("cssClass");
				String cssStyle = results.getString("cssStyle");
				String text = results.getString("text");
				int order = results.getInt("order");
				int pageId = results.getInt("pageId");
				
				widget = new Widget(widgetId, name, width, height, cssClass, cssStyle, text, order, pageId);
			}
			results.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return widget;
	}

	@Override
	public Collection<Widget> findWidgetsForPage(int pageId) {
		// TODO Auto-generated method stub
		Collection<Widget> widget = new ArrayList<Widget>();
		try {
			pStatement = connection.prepareStatement(FIND_WIDGET_FOR_PAGE);
			pStatement.setInt(1, pageId);
			results  = pStatement.executeQuery();
			if(results.next()) {
				int widgetId = results.getInt("id");
				String name = results.getString("name");
				int width = results.getInt("width");
				int height = results.getInt("height");
				String cssClass = results.getString("cssClass");
				String cssStyle = results.getString("cssStyle");
				String text = results.getString("text");
				int order = results.getInt("order");
				pageId = results.getInt("pageId");
				
				widget.add(new Widget(widgetId, name, width, height, cssClass, cssStyle, text, order, pageId));
			}
			results.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return widget;
	}

	@Override
	public int updateWidget(int widgetId, Widget widget) {
		// TODO Auto-generated method stub
		try {
			pStatement = connection.prepareStatement(UPDATE_WIDGET);
			pStatement.setInt(1, widget.getId());
			pStatement.setString(2, widget.getName());
			pStatement.setInt(3, widget.getWidth());
			pStatement.setInt(4, widget.getHeight());
			pStatement.setString(5, widget.getCssClass());
			pStatement.setString(6, widget.getCssStyle());
			pStatement.setString(7, widget.getText());
			pStatement.setInt(8, widget.getOrder());
			pStatement.setInt(9, widget.getPageId());
			pStatement.setInt(10, widgetId);
			//System.out.println(pStatement);
			pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteWidget(int widgetId) {
		// TODO Auto-generated method stub
		try {
			pStatement = connection.prepareStatement(DELETE_WIDGET);
			pStatement.setInt(1, widgetId);
			pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}