package com.lta.springkafkaconsumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.lta.springkafkacommons.NewOrder;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	@Bean
	public ConsumerFactory<String, NewOrder> consumerFactory() {
		Map<String, Object> props = new HashMap<String, Object>();
		props.put( ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put( ConsumerConfig.GROUP_ID_CONFIG, "lesson_group");
		props.put( ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put( ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

		JsonDeserializer<NewOrder> jsonDeserializer = new JsonDeserializer<NewOrder>();
		jsonDeserializer.addTrustedPackages("com.lta.springkafkacommons");
		
		return new DefaultKafkaConsumerFactory<String, NewOrder>(
			props,
			new StringDeserializer(), 
			jsonDeserializer
		);
	}

	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, NewOrder>> 
	  kafkaListenerContainerFactory() {
   
		ConcurrentKafkaListenerContainerFactory<String, NewOrder> factory =
		  new ConcurrentKafkaListenerContainerFactory<String, NewOrder>();
		factory.setConsumerFactory(consumerFactory());
		
		return factory;
	}
}