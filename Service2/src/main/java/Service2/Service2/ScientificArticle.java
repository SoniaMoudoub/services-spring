package Service2.Service2;

import javax.persistence.Entity;


@Entity
public class ScientificArticle extends Article {
	private String university;

	public ScientificArticle() {
		super();
		
	}

	public ScientificArticle(String university,String name, String availability, String description, String auteur) {
		super( name, availability, description, auteur);
		this .university=university;
	
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}
	@Override
	public String toString() {
		return "Scientific Article[ university="+university + super.toString();
	}
	

}
