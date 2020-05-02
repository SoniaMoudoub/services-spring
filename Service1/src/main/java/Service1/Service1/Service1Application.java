package Service1.Service1;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Service1Application {
	private static final Logger log = LoggerFactory.getLogger(Service1Application .class);

	public static void main(String[] args) {
		SpringApplication.run(Service1Application .class, args);
	}
 
	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = dateFormat.parse("2020-10-09");
			log.info(date.toString());

			Rent rent = new Rent();
			rent.setBeginDate(date);

			date = dateFormat.parse("2020-12-09");
			rent.setEndDate(date);

			Customer rony = new Customer("Rony",1,"Student");
			rony.getRents().add(rent);
			rent.setCustomer(rony);
			
			Document novel = new Document("north","FT00287","Ce que le jur doit à la nuit",0,"Un livre pour mille empotions","Yasmina KHADRA");
			novel.getRents().add(rent);
			rent.setBook(novel);

			repository.save(rony);

			log.info("-------------------------------");
			log.info("Customers found with findAll():");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			log.info("--------------------------------------------");
			log.info("Customer found with findName('Rony'):");
			repository.findByName("Rony").forEach(customer -> {
				log.info(customer.toString());
			});

		};
	}

}