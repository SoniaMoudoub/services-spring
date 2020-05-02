package Service1.Service1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class BackEndRestWebService {

	private static final Logger log = LoggerFactory.getLogger(BackEndRestWebService.class);

	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Error")
	@ExceptionHandler(Exception.class)
	public void error() {
	}

	CustomerRepository customerRepository;
	ElectronicBookRepository electronicbookRepository;

	@Autowired
	public BackEndRestWebService(CustomerRepository customerRepository,
			ElectronicBookRepository electronicbookRepository) {
		super();
		this.customerRepository = customerRepository;
		this.electronicbookRepository = electronicbookRepository;

	}

	@GetMapping("/backendURL")
	public String message() {
		return "Message from the backend";
	}

	@GetMapping("/electronicbooks")
	public Iterable<ElectronicBook> getBooks() {
		return electronicbookRepository.findAll();
	}

	@PostMapping("/electronicbooks")
	public void addBook(@RequestBody ElectronicBook electronicbook) throws Exception {
		electronicbookRepository.save(electronicbook);
	}

	@PutMapping("/electronicbooks/{cote}")
	public void rent(@PathVariable("cote") String cote,
			@RequestParam(value = "customerName", required = true) String customerName, @RequestBody Dates dates)
			throws Exception {

		System.out.println(cote);
		System.out.println(customerName);
		System.out.println(dates);

		List<ElectronicBook> electronicbooks =electronicbookRepository.findBycote(cote);
		ElectronicBook electronicbook = electronicbooks.get(0);

		log.info(electronicbook.toString());
		List<Customer> customers = customerRepository.findByName(customerName);
		Customer customer = customers.get(0);
		log.info(customer.toString());
  if(electronicbook.getAvailability()==1) {
	  Rent rent = new Rent();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dateFormat.parse(dates.getBegin());
		rent.setBeginDate(date);
		date = dateFormat.parse(dates.getEnd());
		rent.setEndDate(date);

		customer.getRents().add(rent);
		rent.setCustomer(customer);
		;

		electronicbook.getRents().add(rent);
		rent.setBook(electronicbook);
		log.info(rent.toString());

		customerRepository.save(customer);
  }
		

	}
}