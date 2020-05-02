package Service1.Service1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Book {
	private long id;
    private String cote;
    private String name;
    private int availability;
    private String description;
    private String auteur;
	private List<Rent> rents = new ArrayList<Rent>();
	
	public Book() {
		super();
		
	}

	public Book(String cote, String name, int availability, String description, String auteur) {
		super();
		this.cote = cote;
		this.name = name;
		this.availability = availability;
		this.description = description;
		this.auteur = auteur;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getCote() {
		return cote;
	}

	public void setCote(String cote) {
		this.cote = cote;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	@OneToMany(mappedBy="book")
	@JsonIgnore
	public List<Rent> getRents() {
		return rents;
	}

	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id +"cote"+cote+" name"+name +"auteur"+auteur+"availability"+availability+"description"+description+"]";
	}
	
	
	

}
