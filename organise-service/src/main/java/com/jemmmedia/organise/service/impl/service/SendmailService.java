package com.jemmmedia.organise.service.impl.service;

import javax.mail.internet.AddressException;

public interface SendmailService {

	//void sendMail(String from, String to, String subject, String msg);

	//void sendMail(String from, String[] to, String subject, String msg) throws AddressException;

	void sendMail(String from, String to, String subject, String msg)
			throws AddressException;

}
