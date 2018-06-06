package com.mail.service;

import com.mail.model.Users;
import com.mail.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.Properties;
import java.util.Random;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    Encryption encryption;

    public void save(Users users){
        if(users.getPicture().isEmpty()){
            users.setPicture("http://slogreengoods.com/wp-content/uploads/profile-pic-generic.jpg");
        }

        users.setKeyUser(String.valueOf(new Random().nextInt(100)));

        users.setPasswordAccount(encryption.crypt(users.getPasswordAccount(), users.getKeyUser()));
        users.setLuckyNumber(encryption.crypt(users.getLuckyNumber(), users.getKeyUser()));

        userRepository.save(users);
    }

    public boolean login(String address, String password) {
        Users user = findByAddressAccount(address);

        return user != null && encryption.crypt(password, user.getKeyUser()).equals(user.getPasswordAccount());

    }

    public Users findByAddressAccount(String address){
        return userRepository.findByAddressAccount(address);
    }

    public void sendEmail(String receiver, Users users){
        send(receiver, encryption.decrypt(users.getPasswordAccount(), users.getKeyUser()), users.getAddressAccount());
    }

    private static void send(String addressReceiver, String password, String address){
        String from = "webmail995@yahoo.com";
        String pass ="fortech123";
        String host = "smtp.mail.yahoo.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", pass);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties);

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(addressReceiver));
            message.setSubject("Password Recovery");

            message.setText("You password for email address: "+ address +" is: " + password + ". We suggest changing it, after log in");

            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
