package Service3.Service3;


import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.ResponseEntity;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
//@RibbonClient(name = "ping-a-server", configuration = RibbonConfiguration.class)
public class ReviewWebService {
	private static final Logger log = LoggerFactory.getLogger(ReviewWebService.class);

	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Error")
	@ExceptionHandler(Exception.class)
	public void error() {
	}

	PublishingHouseRepository publishinghouseRepository;
	ReviewRepository reviewRepository;

	@Autowired
	public ReviewWebService(PublishingHouseRepository publishinghouseRepository, ReviewRepository reviewRepository) {
		super();
		this.publishinghouseRepository = publishinghouseRepository;
		this.reviewRepository = reviewRepository;
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/reviews")
	@ResponseStatus(HttpStatus.OK) 
	public Iterable<Review> Reviews() {
		return reviewRepository.findAll();
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/reviews")
	@ResponseStatus(HttpStatus.OK) 
	public void addReview(@RequestBody Review review) throws Exception {
		reviewRepository.save(review);
	}

	@PutMapping("/reviews/{name}")
	public void publish(@PathVariable("name") String name,
			@RequestParam(value = "publishinghouseName",required = true) String publishinghouseName,@RequestParam (value = "subject",required = true) String subject,@RequestBody String subjects)
	      
	        
					throws Exception {

		System.out.println(name);
		System.out.println(publishinghouseName);
		System.out.println(subject);
		System.out.println(subjects);

		List<Review> reviews = reviewRepository.findByName(name);
		Review review =reviews.get(0);

		log.info(review.toString());
		List<PublishingHouse> publishinghouses = publishinghouseRepository.findByName(publishinghouseName);
		PublishingHouse publishinghouse = publishinghouses.get(0);
		log.info(publishinghouse.toString());


			Publish publish = new Publish();
			publish.setSubject(subject);

			publishinghouse.getPublishs().add(publish);
			publish.setPublishinghouse(publishinghouse); ;
			review.getPublishes().add(publish);
			publish.setReview(review);
			log.info(publish.toString());
			publishinghouseRepository.save(publishinghouse);
	
		
			
			
			
	}

	@GetMapping("/frontendURL")
	public String message() {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://localhost:8282/backendURL";
		String result = restTemplate.getForObject(uri, String.class);
		return "Message from the front end while the backend is called: " + result;
	}

	
	@Autowired
	private LoadBalancerClient loadBalancer;
	@GetMapping("/")
	public String hello() {
		ServiceInstance serviceInstance = loadBalancer.choose("Service1");
		System.out.println(serviceInstance.getUri());
		String service1Address = serviceInstance.getUri().toString();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(service1Address, String.class);
		return "I am the service 3. When I ask the service1, the Ribbon load balancer find the host " + serviceInstance.getUri() + ". Then, when a call the service on this host, it returns: " + response.getBody();
		}

}
