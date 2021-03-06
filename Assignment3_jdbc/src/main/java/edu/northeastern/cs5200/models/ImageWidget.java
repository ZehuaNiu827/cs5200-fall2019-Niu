package edu.northeastern.cs5200.models;
import javax.persistence.*;
@Entity
@Table(name="Image")
public class ImageWidget extends Widget{
	public ImageWidget(int id, String name, int width, int height, String cssStyle, String cssClass, String text,
			int order, int pageId, String src) {
		super(id, name, width, height, cssStyle, cssClass, text, order, pageId);
		this.src = src;
		// TODO Auto-generated constructor stub
	}

	@Column(name = "image")
	private String src;

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}
}