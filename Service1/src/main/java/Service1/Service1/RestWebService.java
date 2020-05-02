package Service1.Service1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class RestWebService {
	private static final Logger log = LoggerFactory.getLogger(RestWebService.class);
	private int nbBook;

	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Error")
	@ExceptionHandler(Exception.class)
	public void error() {
	}

	CustomerRepository customerRepository;
	DocumentRepository documentRepository;
	
	

	@Autowired
	public RestWebService(CustomerRepository customerRepository, DocumentRepository documentRepository) {
		super();
		this.customerRepository = customerRepository;
		this.documentRepository = documentRepository;
		
		
	}
	@GetMapping("/")
	public String hello() {
		return "Hello i am the first service !";
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/customers")
	@ResponseStatus(HttpStatus.OK) 
	public Iterable<Customer> getCustomers() {
		return customerRepository.findAll();
	}
	
	
	
	
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/customers")
	@ResponseStatus(HttpStatus.OK) 
	public void addCustomer(@RequestBody Customer customer) throws Exception {
		customerRepository.save(customer);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/books")
	@ResponseStatus(HttpStatus.OK) 
	public Iterable<Document> getBooks() {
		return documentRepository.findAll();
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/books")
	@ResponseStatus(HttpStatus.OK) 
	public void addBook(@RequestBody Document document) throws Exception {
		documentRepository.save(document);
	}
	

	@PutMapping("/books/{cote}")
	public void rent(@PathVariable("cote") String cote,
			@RequestParam(value = "customerName", required = true) String customerName, @RequestBody Dates dates)
			throws Exception {

		System.out.println(cote);
		System.out.println(customerName);
		System.out.println(dates);

		List<Document> documents = documentRepository.findBycote(cote);
		Document document = documents.get(0);

		log.info(document.toString());
		List<Customer> customers = customerRepository.findByName(customerName);
		Customer customer = customers.get(0);
		log.info(customer.toString());
		
		
		if (customer.getCategory() == "Student" && customer.getBag() != 2 && document.getAvailability() == 1) {
			nbBook = customer.getBag();
			nbBook++;
			Rent rent = new Rent();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = dateFormat.parse(dates.getBegin());
			rent.setBeginDate(date);
			date = dateFormat.parse(dates.getEnd());
			rent.setEndDate(date);

			customer.getRents().add(rent);
			rent.setCustomer(customer);
			;

			document.getRents().add(rent);
			rent.setBook(document);
			customer.setBag(nbBook);
			document.setAvailability(0);
			log.info(rent.toString());

			customerRepository.save(customer);
		} else {
			if (customer.getCategory() != "Student" && customer.getBag() != 1 && document.getAvailability() == 1) {
				nbBook = customer.getBag();
				nbBook++;
				Rent rent = new Rent();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = dateFormat.parse(dates.getBegin());
				rent.setBeginDate(date);
				date = dateFormat.parse(dates.getEnd());
				rent.setEndDate(date);

				customer.getRents().add(rent);
				rent.setCustomer(customer);
				;

				document.getRents().add(rent);
				rent.setBook(document);
				customer.setBag(nbBook);
				document.setAvailability(0);
				log.info(rent.toString());

				customerRepository.save(customer);
			}
			

		}

	}

	@GetMapping("/frontendURL")
	public String message() {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://localhost:8282/backendURL";
		String result = restTemplate.getForObject(uri, String.class);
		return "Message from the front end while the backend is called: " + result;
	}

	
}
