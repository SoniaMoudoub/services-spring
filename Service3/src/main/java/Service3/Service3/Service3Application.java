package Service3.Service3;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Service3Application {
	private static final Logger log = LoggerFactory.getLogger(Service3Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Service3Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(PublishingHouseRepository repository) {
		return (args) -> {


			Publish publish = new Publish();
			publish.setSubject("Science");
			PublishingHouse gallimard = new PublishingHouse("Gallimard","05 rue saint germaint");
			gallimard.getPublishs().add(publish);
			publish.setPublishinghouse(gallimard);

			Review datascience = new Review("DataScience","N°24");
			datascience.getPublishes().add(publish);
			publish.setReview(datascience);

			repository.save(gallimard);

			log.info("-------------------------------");
			log.info("publishinghouses found with findAll():");
			for (PublishingHouse publishinghouse : repository.findAll()) {
				log.info(publishinghouse.toString());
			}
			log.info("");

			log.info("--------------------------------------------");
			log.info(" Publishing House  found with findName('gallimard'):");
			repository.findByName("gallimard").forEach(publishinghouse -> {
				log.info(publishinghouse.toString());
			});

		};
	}

}
