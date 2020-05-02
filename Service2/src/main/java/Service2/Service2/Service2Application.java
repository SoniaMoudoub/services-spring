package Service2.Service2;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class Service2Application {

	private static final Logger log = LoggerFactory.getLogger(Service2Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Service2Application.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(PersonRepository repository) {
		return (args) -> {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = dateFormat.parse("2020-10-09");
			log.info(date.toString());

			Consult consult = new Consult();
			consult.setBeginDate(date);

			date = dateFormat.parse("2020-12-09");
			consult.setEndDate(date);

			Person jasmine = new Person("Jasmine","Descartes","90I0","No access fees");
			jasmine.getConsults().add(consult);
			consult.setPerson(jasmine);

			ScientificArticle datascience = new ScientificArticle("Sorbonne","Data Science","Available","Un livre pour mille sensations ","Yasmina KHADRA");
			datascience.getConsults().add(consult);
			consult.setArticle(datascience);

			repository.save(jasmine);

			log.info("-------------------------------");
			log.info("Persons found with findAll():");
			for (Person person : repository.findAll()) {
				log.info(person.toString());
			}
			log.info("");

			log.info("--------------------------------------------");
			log.info("Person found with findName('Tintin'):");
			repository.findByName("Tintin").forEach(person -> {
				log.info(person.toString());
			});

		};
	}

}