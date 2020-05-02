package Service4.Service4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class Service4Application {

	public static void main(String[] args) {
		SpringApplication.run(Service4Application.class, args);
	}

}
