package info.pablogiraldo.sbblog.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "articles")
public class Article implements Serializable, Comparable<Article> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	@Size(min = 2, max = 255)
	private String title;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date day;

	@Size(min = 0, max = 255)
	private String subtitle;
	@Size(min = 0, max = 255)
	private String image;
	@Size(min = 0, max = 255)
	private String link;
	@Size(min = 0, max = 500)
	private String text;

	@ManyToOne
	private Category category;

	public Article() {
	}

	public Article(Long id, @NotEmpty @Length(min = 2, max = 255) String title, Date day,
			@Length(min = 0, max = 255) String subtitle, @Length(min = 0, max = 255) String image,
			@Length(min = 0, max = 255) String link, @Length(min = 0, max = 500) String text, Category category) {
		this.id = id;
		this.title = title;
		this.day = day;
		this.subtitle = subtitle;
		this.image = image;
		this.link = link;
		this.text = text;
		this.category = category;
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

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public int compareTo(Article comparearticle) {
		Long compareids = ((Article) comparearticle).getId();
		return (int) (compareids - this.id);
	}

}
