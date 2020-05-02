package Service2.Service2;

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
public class Person {
	
	private long id;
	private String name;
	private String organization;
	private String orgidcard;
	private String accessfees;
	private List<Consult> consults = new ArrayList<Consult>();
	
	public Person() {
		super();
	}

	public Person(String name,String organization,String orgidcard,String accessfees ) {
		super();
		this.name = name;
		this.organization = organization;
		this.orgidcard = orgidcard;
		this.accessfees = accessfees;
		
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
    
	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getOrgidcard() {
		return orgidcard;
	}

	public void setOrgidcard(String orgidcard) {
		this.orgidcard = orgidcard;
	}
	

	public String getAccessfees() {
		return accessfees;
	}

	public void setAccessfees(String accessfees) {
		this.accessfees = accessfees;
	}

	@OneToMany(mappedBy="person", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	public List<Consult> getConsults() {
		return consults;
	}

	public void setConsults(List<Consult> consults) {
		this.consults = consults;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "Organization"+organization+"orgidcard"+orgidcard+" accessfees "+accessfees+", consults=" + consults + "]";
	}
	
}