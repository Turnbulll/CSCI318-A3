package CSCI318.Order;

import CSCI318.Order.Model.Order;
import CSCI318.Order.Repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OrderApplication {

        private static final Logger log = LoggerFactory.getLogger(OrderApplication.class);
        
	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);       
	}
        
        @Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
       
}
