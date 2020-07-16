package pl.kzochowski.knowledgeTest.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@Slf4j
@Service
public class MailSenderService {

    //todo refactor, create application email, knowledgeTest@gmail.com, properties add to application.yml

    public boolean sendNewAccountEmail(String email) {
        Session session = createGmailSession();
        String mailSubject = "New account registration";
        String mailMessage = "Hello,\n new account has been rejestrated!";

        try {
            Message message = createMessage(session, email, mailSubject, mailMessage);
            Transport.send(message);
            log.info("New account registration email sent, recipient: {}", email);
        } catch (MessagingException e) {
            throw new UserRegistrationMailException(email);
        }
        return true;
    }

    private Session createGmailSession() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("zochowski.konrad1@gmail.com", "grochowa");
            }
        });
    }

    private Message createMessage(Session session, String recipient, String mailSubject, String mailMessage) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("zochowski.konrad1@gmail.com"));
        message.setSubject(mailSubject);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(mailMessage, "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);
        return message;
    }

    static class UserRegistrationMailException extends RuntimeException {
        public UserRegistrationMailException(String email) {
            super(String.format("Sending new account registration email has failed! Recipient: %s", email));
        }
    }
}
