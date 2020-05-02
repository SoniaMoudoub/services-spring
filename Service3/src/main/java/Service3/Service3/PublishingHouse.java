package Service3.Service3;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;




@Entity
public class PublishingHouse {
	private long id;
	private String name;
	private String address;
	private List<Publish> publishes = new ArrayList<Publish>();

	public PublishingHouse() {
		super();
	
	}

	public PublishingHouse(String name ,String address) {
		super();
		this.name = name;
		this.address=address;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@OneToMany(mappedBy="publishinghouse", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	public List<Publish> getPublishs() {
		return publishes;
	}

	public void setPublishs(List<Publish> publishes) {
		this.publishes = publishes;
	}

	
	@Override
	public String toString() {
		return "PublishingHouse [id=" + id + ", name=" + name + "Address"+address+ "Publishs=" +publishes + "]";
	}
	
	

}

