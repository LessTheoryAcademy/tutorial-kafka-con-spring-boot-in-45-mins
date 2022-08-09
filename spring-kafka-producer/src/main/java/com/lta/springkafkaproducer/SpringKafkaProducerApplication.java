package com.lta.springkafkaproducer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lta.springkafkacommons.NewOrder;

@SpringBootApplication
public class SpringKafkaProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaProducerApplication.class, args);
	}
	
    @Bean
    CommandLineRunner sender(KafkaOrderSender sender) {

    	return args -> {
		
			String message = "New order...";
		
			NewOrder order = new NewOrder();
			order.setCustomerName("my customer name");
			
			List<Long> productIds = new ArrayList<Long>();
			productIds.add(System.currentTimeMillis());
			productIds.add(7L);
			productIds.add(9L);
			
			order.setProductIds(productIds);

			sender.send(order);
	        
    	};
    }	

}
