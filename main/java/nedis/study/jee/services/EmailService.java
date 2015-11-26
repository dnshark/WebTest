package nedis.study.jee.services;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

/**
 * @author nedis
 * @version 1.0
 */
public interface EmailService {

	void sendVerificationEmail(String destinationEmail,String name,String fromEmail,String fromName,
							   String subject,String content) throws javax.mail.MessagingException;
}
