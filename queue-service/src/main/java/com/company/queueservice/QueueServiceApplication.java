package com.company.queueservice;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class QueueServiceApplication {

	//You need to be running an AMQP server that handles sending and receiving ( a broker).
	public static void main(String[] args) {
		SpringApplication.run(QueueServiceApplication.class, args);
	}



	//mapping queue info to Strings
	//the # symbol is wildcard. anything that begins with level-up.update. will be routed to the queue
	public static final String TOPIC_EXCHANGE_NAME = "level-up-exchange";
	public static final String QUEUE_NAME = "level-up-queue";
	public static final String ROUTING_KEY = "level-up.update.#";

	// queue() creates AMQP queue, exchange method creates TopicExchange, and binding() binds the first two together.
	// This binding() show how RabbitTemplate behaves when publishing to an exchange
	// and Spring needs these all to be declared as beans in order to work
	@Bean
	public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(TOPIC_EXCHANGE_NAME);
	}

// the false means that we do not need to have the queue durable between restarts of the RAbbitMQ broker
	@Bean
	Queue queue() {
		return new Queue(QUEUE_NAME, false);
	}


	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}
}
