package com.lbh595.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JmsController {

	@Autowired
	@Qualifier("myRabbitJmsTemplate")
	public JmsTemplate jmsTemplate;

	@GetMapping("send")
	public String send() {
		// Send a message with a POJO - the template reuse the message converter
		System.out.println("Sending an email message.");
		jmsTemplate.setPubSubDomain(false);
		jmsTemplate.setDefaultDestinationName("MQ_JMS_TEST");
		jmsTemplate.send("MQ_JMS_TEST",messageCreator);
		return "OK";

	}
	
	MessageCreator messageCreator = new MessageCreator() {
		public Message createMessage(Session session) throws JMSException{
			TextMessage msg = session.createTextMessage("Hello World");
			return msg;
		}
	};

}
