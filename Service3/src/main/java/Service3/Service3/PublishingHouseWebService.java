package Service3.Service3;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;



@RestController
public class PublishingHouseWebService {
	private static final Logger log = LoggerFactory.getLogger(PublishingHouseWebService.class);
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Error")
	@ExceptionHandler(Exception.class)
	public void error() {
	}
	PublishingHouseRepository publishinghouseRepository;
	
	@Autowired
	public PublishingHouseWebService(PublishingHouseRepository  publishinghouseRepository) {
		super();
		this. publishinghouseRepository =  publishinghouseRepository;
		}
	@GetMapping("/backendURL")
	public String message(){
		return "Message from the backend";
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/publishinghouses")
	@ResponseStatus(HttpStatus.OK) 
	public Iterable<PublishingHouse> getPublishingHouses(){
		return publishinghouseRepository.findAll();
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/publishinghouses")
	@ResponseStatus(HttpStatus.OK) 
	public void addPublishinghouses(@RequestBody PublishingHouse publishinghouse) throws Exception {
		publishinghouseRepository.save(publishinghouse);
	}

	

}
