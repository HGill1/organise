/**
 * 
 */
package com.jemmmedia.organise.service.impl.service.impl;

import javax.mail.Message;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.jemmmedia.organise.service.impl.service.SendmailService;

/**
 * @author harjinder
 *
 */
@Service("sendmailService")
public class SendmailServiceImpl implements SendmailService {

	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private SimpleMailMessage alertMailMessage;
	
	@Autowired
	private JavaMailSender jmailSender;
		
	@Override
	public void sendMail(String from, final String to, final String subject, final String msg) throws AddressException {
		
		String[] emailList = to.split(",");
		 
		//SimpleMailMessage message = new SimpleMailMessage();
		
		 final InternetAddress[] addressTo = new InternetAddress[emailList.length];

			for (int i = 0; i < emailList.length; i++)
			{
				addressTo[i] = new InternetAddress(emailList[i]);
			}
		
		 MimeMessagePreparator preparator = new MimeMessagePreparator() {
			 
		        
	            public void prepare(MimeMessage mimeMessage) throws Exception {
	            	
	            	
	            	mimeMessage.setRecipients(Message.RecipientType.TO, 
	            			addressTo);
	                mimeMessage.setFrom(new InternetAddress("noreply@organise.net","Organise"));
	                mimeMessage.setSubject(subject);
	                mimeMessage.setContent(msg,"text/html");
	                
	                //mimeMessage.setText(msg);
	            }
	        };
	        try {
	            this.jmailSender.send(preparator);
	        }
	        catch (MailException ex) {
	            // simply log it and go on...
	            System.err.println(ex.getMessage());            
	        }
	    
 
		/*message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);*/	
	}
	
	public void sendAlertMail(String alert) {
		 
	        SimpleMailMessage mailMessage = new SimpleMailMessage(alertMailMessage);
	        mailMessage.setText(alert);
	        mailSender.send(mailMessage);
	 
	}
	
	
}
