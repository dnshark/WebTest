package nedis.study.jee.services.impl;

import org.springframework.stereotype.Service;

import nedis.study.jee.services.EmailService;

/**
 * @author nedis
 * @version 1.0
 */
@Service("emailService")
public class EmailServiceStub implements EmailService {

	@Override
	public void sendVerificationEmail() {
		//Do nothing
	}

}
