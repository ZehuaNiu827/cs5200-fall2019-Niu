package edu.northeastern.cs5200.models;
import javax.persistence.*;
@Entity
@Table(name="YouTube")
public class YouTubeWidget extends Widget{
	public YouTubeWidget(int id, String name, int width, int height, String cssStyle, String cssClass, String text,
			int order, int pageId, String url, boolean shareble, boolean expandable) {
		super(id, name, width, height, cssStyle, cssClass, text, order, pageId);
		this.url = url;
		this.shareble = shareble;
		this.expandable = expandable;
		// TODO Auto-generated constructor stub
	}
	@Column(name = "url")
	private String url;
	@Column(name = "shareble")
	private boolean shareble;
	@Column(name = "expandable")
	private boolean expandable;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isShareble() {
		return shareble;
	}
	public void setShareble(boolean shareble) {
		this.shareble = shareble;
	}
	public boolean isExpandable() {
		return expandable;
	}
	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}
	
}