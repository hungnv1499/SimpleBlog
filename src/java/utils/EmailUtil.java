/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.Serializable;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author DELL
 */
public class EmailUtil implements Serializable {

    private static final String formatString = "^[_a-z0-9-\\+]+(\\.[_a-z0-9-\\+]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*\\.([a-z]{2,6})$";
    private static final String account = "denpinxanhh@gmail.com";
    private static final String password = "zaQ@1234";

    public static boolean validateEMail(String email) throws Exception {
        boolean isValid = false;
        try {
            isValid = email.matches(formatString);
        } catch (Exception e) {
            isValid = false;
        }
        return isValid;
    }

    public static void sendEmail(String email, int code) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(account, password);
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(account));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.setSubject("[SimpleBlog] Authentication account");
        message.setText("Your code: " + code);
        Transport.send(message);
    }
}
