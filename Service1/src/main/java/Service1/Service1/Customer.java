package Service1.Service1;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
	private long id;
	private String name;
	private String category;
	private int bag;
	private List<Rent> rents = new ArrayList<Rent>();
	
	public Customer() {
		super();
		
	}

	public Customer(String name,int bag,String category) {
		super();
		this.name = name;
		this.bag=bag;
		this.category=category;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getBag() {
		return bag;
	}

	public void setBag(int bag) {
		this.bag = bag;
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
	
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	public List<Rent> getRents() {
		return rents;
	}

	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name +"bag "+bag+" category "+category+ ", rents=" + rents + "]";
	}

	
	

}

