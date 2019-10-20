package edu.northeastern.cs5200.models;

import javax.persistence.*;
@Entity
@Table(name="Html")
public class HtmlWidget extends Widget{
	public HtmlWidget(int id, String name, int width, int height, String cssStyle, String cssClass, String text,
			int order, int pageId, String html) {
		super(id, name, width, height, cssStyle, cssClass, text, order, pageId);
		this.html = html;
		// TODO Auto-generated constructor stub
	}

	@Column(name = "html")
	private String html;

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
}