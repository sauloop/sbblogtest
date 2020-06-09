package info.pablogiraldo.sbblog.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "articles")
public class Article implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	@Length(min = 2, max = 255)
	private String title;
	@Length(min = 0, max = 255)
	private String subtitle;
	@Length(min = 0, max = 255)
	private String image;
	@Length(min = 0, max = 255)
	private String link;
	@Length(min = 0, max = 500)
	private String text;

	public Article() {
	}

	public Article(Long id, @NotEmpty String title, String subtitle, String image, String link, String text) {
		super();
		this.id = id;
		this.title = title;
		this.subtitle = subtitle;
		this.image = image;
		this.link = link;
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
