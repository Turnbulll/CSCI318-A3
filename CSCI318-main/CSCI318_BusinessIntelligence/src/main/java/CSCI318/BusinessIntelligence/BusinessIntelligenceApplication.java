package CSCI318.BusinessIntelligence;

import CSCI318.BusinessIntelligence.Model.OrderEvent;
import CSCI318.BusinessIntelligence.Model.ProductQuantity;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BusinessIntelligenceApplication {

    private static final Logger log = LoggerFactory.getLogger(BusinessIntelligenceApplication.class);
    
	public static void main(String[] args) {
		SpringApplication.run(BusinessIntelligenceApplication.class, args);
	}
}
