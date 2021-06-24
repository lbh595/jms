package com.lbh595.jms;

import javax.jms.ConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import com.rabbitmq.jms.admin.RMQConnectionFactory;

@Configuration
public class JmsConfiguration {
	
	private static final Logger logger = LoggerFactory.getLogger(JmsConfiguration.class);

	@Bean(name = "myRabbitConnectionFactory")
	public ConnectionFactory jmsConnectionFactory() {
		RMQConnectionFactory connectionFactory = new RMQConnectionFactory();
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		connectionFactory.setVirtualHost("/");
		connectionFactory.setHost("localhost");
		connectionFactory.setPort(5672);
		return connectionFactory;
	}
	
	@Bean(name = "myRabbitJmsTemplate")
	public JmsTemplate jmsTemplate(@Qualifier("myRabbitConnectionFactory") ConnectionFactory cf) {
		
		logger.info("Start configure jmsTemplate");
		
		boolean errorFound = false;
		
		if(cf == null) {
			logger.error("ConnectionFactory Error", new IllegalArgumentException("Connection Factory cannot be null."));
			errorFound = true;
		}
		
		if(errorFound) {
			return null;
		}
		JmsTemplate jmsTemplate = new JmsTemplate(cf);
		return jmsTemplate;
	}

}
