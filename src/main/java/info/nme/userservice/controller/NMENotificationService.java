package info.nme.userservice.controller;

import info.nme.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by chakra.bhandari on 25/03/2017.
 */
@Service
public class NMENotificationService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendNotification(User newUser) throws MailException {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(newUser.getEmail());
        mailMessage.setFrom("usernme481@gmail.com");
        mailMessage.setSubject("Email Confirmation");
        mailMessage.setText("Please on the link below \n " +
                " http://localhost:8090/registrations/confirm/"+newUser.getRegistrationConfirmationToken()+ "\n"
        );

        mailSender.send(mailMessage);
    }
}
