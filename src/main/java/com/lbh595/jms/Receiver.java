package com.lbh595.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.lbh595.jms.domain.Email;

@Component
public class Receiver {

	@JmsListener(destination = "mailbox", containerFactory = "myFactory")
	public void receiveMessage(Email email) {
		System.out.println("Received <" + email + ">");
	}

}
