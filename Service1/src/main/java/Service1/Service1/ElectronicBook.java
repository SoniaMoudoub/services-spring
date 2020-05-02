package Service1.Service1;

import javax.persistence.Entity;

@Entity
public class ElectronicBook extends Book {
	private String URL;

	public ElectronicBook() {
		super();

	}

	public ElectronicBook(String URL, String cote, String name, int availability, String description, String auteur) {
		super(cote, name, availability, description, auteur);
		this.URL = URL;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}
	
	@Override
	public String toString() {
		return "Electronic Booc[URL="+URL + super.toString();
	}

}
