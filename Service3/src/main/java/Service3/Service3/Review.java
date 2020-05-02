package Service3.Service3;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Review {
	private long id;
	private String name;
	private String number;
	private List<Publish> publishes= new ArrayList< Publish>();
	
	public Review() {
		super();
		
	}

	public Review(String name, String number) {
		super();
		this.name = name;
		this.number = number;
		
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	@OneToMany(mappedBy="review")
	@JsonIgnore
	public List<Publish>  getPublishes() {
		return publishes;
	}

	public void setPublishes(List<Publish>  publishes) {
		this.publishes = publishes;
	}
	
	@Override
	public String toString() {
		return "Review [id=" + id +" name"+name +"number"+number+"]";
	}
	

}
