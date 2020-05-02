package Service3.Service3;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Publish {
	private long id;
	private PublishingHouse publishinghouse;
	private Review review;
	private String subject;

	public Publish() {
		super();

	}

	public Publish(String subject) {
		super();
		this.subject = subject;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne
	@JsonIgnore
	public PublishingHouse getPublishinghouse() {
		return publishinghouse;
	}

	public void setPublishinghouse(PublishingHouse publishinghouse) {
		this.publishinghouse = publishinghouse;
	}

	@OneToOne(cascade = CascadeType.ALL)
	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Override
	public String toString() {
		return "Publish [id=" + id + ", Review=" + review + ", Subject=" + subject + "]";
	}

}
