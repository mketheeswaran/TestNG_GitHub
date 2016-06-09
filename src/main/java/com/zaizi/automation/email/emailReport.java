package com.zaizi.automation.email;

import java.util.Properties;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zaizi.automation.alfresco.core.info.TestCaseProperties;


public class emailReport {

	public static final Logger LOGGER = LogManager
			.getLogger(emailReport.class.getName());

	public  void sendEmail2() {

		
		
		
	    final String username = "testngzaizi@gmail.com";
	    final String password = "testngzaizi123";

	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");
	    
	    
	    Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

	    Session session = Session.getInstance(props, auth);

	    try {

	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(username));
	        message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(TestCaseProperties.EMAIL));
	        message.setSubject("Reports of Automation test");
	        message.setText("PFA");
    
	        MimeBodyPart messageBodyPart = new MimeBodyPart();
	        Multipart multipart = new MimeMultipart();
	        messageBodyPart = new MimeBodyPart();	    
	        String fileName = "Reports.zip";
	        DataSource source = new FileDataSource(TestCaseProperties.OUTPUT_ZIP_FILE);
	        messageBodyPart.setDataHandler(new DataHandler(source));
	        messageBodyPart.setFileName(fileName);
	        multipart.addBodyPart(messageBodyPart);

	        message.setContent(multipart);
	        LOGGER.info("Sending");
	        Transport.send(message);
	        LOGGER.info("Done");

	    } catch (MessagingException e) {
	        e.printStackTrace();
	    }
	  }



}
