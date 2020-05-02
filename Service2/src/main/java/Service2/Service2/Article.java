package Service2.Service2;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Article {
	private long id;
	private String name;
	private String availability;
	private String description;
	private String auteur;
	private List<Consult> consults = new ArrayList<Consult>();

	public Article() {
		super();

	}

	public Article( String name, String availability, String description, String auteur) {
		super();
		
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
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
	@OneToMany(mappedBy="article")
	@JsonIgnore
	public List<Consult> getConsults() {
		return consults;
	}

	public void setConsults(List<Consult> consults) {
		this.consults = consults;
	}
	
	@Override
	public String toString() {
		return "Document [id=" + id +" name"+name +"auteur"+auteur+"availability"+availability+"description"+description+"]";
	}
	
    
	
	
}
