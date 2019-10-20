package edu.northeastern.cs5200.models;

import javax.persistence.*;
@Entity
@Table(name = "Heading")
public class HeadingWidget extends Widget{
	public HeadingWidget(int id, String name, int width, int height, String cssStyle, String cssClass, String text,
			int order, int pageId, int size) {
		super(id, name, width, height, cssStyle, cssClass, text, order, pageId);
		this.size = size;
		// TODO Auto-generated constructor stub
	}

	@Column(name = "size")
	private int size = 2;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}