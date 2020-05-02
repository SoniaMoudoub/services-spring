package Service1.Service1;

import javax.persistence.Entity;

@Entity
public class Document extends Book {
	private String location;

	public Document() {
		super();

	}

	public Document(String location, String cote, String name, int desponibility, String description, String auteur) {
		super(cote, name, desponibility, description, auteur);
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Document[ location="+location + super.toString();
	}
}


