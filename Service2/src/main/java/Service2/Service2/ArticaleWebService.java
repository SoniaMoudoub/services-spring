package Service2.Service2;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.ResponseEntity;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ArticaleWebService {
	private static final Logger log = LoggerFactory.getLogger(ArticaleWebService.class);
	private String accessfees;

	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Error")
	@ExceptionHandler(Exception.class)
	public void error() {
	}

	PersonRepository personRepository;
	ScientificArticleRepository scientificarticaleRepository;

	@Autowired
	public ArticaleWebService(PersonRepository personRepository,
			ScientificArticleRepository scientificarticaleRepository) {
		super();
		this.personRepository = personRepository;
		this.scientificarticaleRepository = scientificarticaleRepository;
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/articles")
	@ResponseStatus(HttpStatus.OK) 
	public Iterable<ScientificArticle> getScientificArticles() {
		return scientificarticaleRepository.findAll();
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/articles")
	@ResponseStatus(HttpStatus.OK) 
	public void addVehicule(@RequestBody ScientificArticle scientificarticle) throws Exception {
		scientificarticaleRepository.save(scientificarticle);
	}

	@PutMapping("/articles/{name}")
	public void consult(@PathVariable("name") String name,
			@RequestParam(value = "personName", required = true) String personName, @RequestBody Dates dates)
			throws Exception {

		System.out.println(name);
		System.out.println(personName);
		System.out.println(dates);

		List<ScientificArticle> scientificarticles = scientificarticaleRepository.findByname(name);
		ScientificArticle scientificarticle = scientificarticles.get(0);

		log.info(scientificarticle.toString());
		List<Person> persons = personRepository.findByName(personName);
		Person person = persons.get(0);
		log.info(person.toString());

		if (person.getOrganization() == "Descartes" || person.getOrganization() == "Sorbonne") {
			Consult consult = new Consult();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = dateFormat.parse(dates.getBegin());
			consult.setBeginDate(date);
			date = dateFormat.parse(dates.getEnd());
			consult.setEndDate(date);

			person.getConsults().add(consult);
			consult.setPerson(person);
			;
			scientificarticle.getConsults().add(consult);
			consult.setArticle(scientificarticle);
			person.setAccessfees("No Access fees");
			log.info(consult.toString());

			personRepository.save(person);

		}else {
		
				Consult consult = new Consult();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = dateFormat.parse(dates.getBegin());
				consult.setBeginDate(date);
				date = dateFormat.parse(dates.getEnd());
				consult.setEndDate(date);

				person.getConsults().add(consult);
				consult.setPerson(person);
				;
				scientificarticle.getConsults().add(consult);
				consult.setArticle(scientificarticle);
				accessfees=scientificarticle.getAvailability();
				person.setAccessfees(accessfees);
				log.info(consult.toString());

				personRepository.save(person);

			
			
		}

	}

	@GetMapping("/frontendURL")
	public String message() {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://localhost:8282/backendURL";
		String result = restTemplate.getForObject(uri, String.class);
		return "Message from the front end while the backend is called: " + result;
	}
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@GetMapping("/")
	@HystrixCommand(fallbackMethod = "defaultMessage")
	public String hello() {
		List<ServiceInstance> instances = discoveryClient.getInstances("Service1");
		ServiceInstance test = instances.get(0);
		String hostname = test.getHost();
		int port = test.getPort();
		RestTemplate restTemplate = new RestTemplate();
		String service1Address = "http://" + hostname + ":" + port;
		ResponseEntity<String> response = restTemplate.getForEntity(service1Address, String.class);
		return "I am the Service 2. When I ask the Service 1 for a response, it returns: " + response.getBody();
	}
	
	public String defaultMessage() {
		return "Salut !";
	}

}
