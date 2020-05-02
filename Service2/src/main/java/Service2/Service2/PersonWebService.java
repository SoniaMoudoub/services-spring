package Service2.Service2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;



@RestController
public class PersonWebService {
	private static final Logger log = LoggerFactory.getLogger(PersonWebService.class);
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Error")
	@ExceptionHandler(Exception.class)
	public void error() {
	}
	PersonRepository personRepository;
	
	@Autowired
	public PersonWebService(PersonRepository personRepository) {
		super();
		this.personRepository = personRepository;
		}
	
	@GetMapping("/backendURL")
	public String message() {
		return "Message from the backend";
	}
	
	@GetMapping("/webservice2")
	public String messages(){
		return "Message from the backend";
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/persons")
	@ResponseStatus(HttpStatus.OK) 
	public Iterable<Person> getPersons(){
		return personRepository.findAll();
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/persons")
	@ResponseStatus(HttpStatus.OK) 
	public void addPerson(@RequestBody Person person) throws Exception {
		personRepository.save(person);
	}

}
