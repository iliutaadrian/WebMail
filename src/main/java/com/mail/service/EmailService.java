package com.mail.service;

import com.mail.model.Email;
import com.mail.model.EmailMode;
import com.mail.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {
    @Autowired
    EmailRepository emailRepository;

    @Autowired
    Encryption encryption;

    public void save(Email email){
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("HH:mm : dd-MM-yyyy");
        email.setDateSend(sdf.format(dt));
        email.setBodyMail(encryption.crypt(email.getBodyMail(), email.getKeyMail()));
        email.setMarkedMail(0);

        email.setEmailMode(EmailMode.MODE_INBOX);
        emailRepository.save(email);

        if(!email.getAddressSender().equals("anonymous"))
            emailRepository.save(new Email(email, EmailMode.MODE_SENT));
    }

    public List<Email> findAllSender(String addressReceiver) {
        List<Email> emails = new ArrayList<>();
        for(Email email : emailRepository.findAll()){
            if(email.getAddressReceiver().equals(addressReceiver) &&
                    email.getEmailMode()==EmailMode.MODE_INBOX &&
                    !email.getAddressSender().equals("anonymous")){
                email.setBodyMail(encryption.decrypt(email.getBodyMail(), email.getKeyMail()));
                emails.add(email);
            }
        }

        return emails;
    }

    public List<Email> findAllReceiver(String addressSender) {
        List<Email> emails = new ArrayList<>();
        for(Email email : emailRepository.findAll()){
            if(email.getAddressSender().equals(addressSender) &&
                    email.getEmailMode()==EmailMode.MODE_SENT){
                email.setBodyMail(encryption.decrypt(email.getBodyMail(), email.getKeyMail()));
                emails.add(email);
            }
        }

        return emails;
    }

    public List<Email> findAllMarked(String address) {
        List<Email> emails = new ArrayList<>();
        for(Email email : emailRepository.findAll()){
            if(email.getMarkedMail()==1 && email.getAddressReceiver().equals(address)){
                email.setBodyMail(encryption.decrypt(email.getBodyMail(), email.getKeyMail()));
                emails.add(email);
            }
        }

        return emails;
    }

    public List<Email> findAllAnonymous(String address) {
        List<Email> emails = new ArrayList<>();
        for(Email email : emailRepository.findAll()){
            if(email.getAddressSender().equals("anonymous")&&
                    email.getAddressReceiver().equals(address)&&
                    email.getEmailMode() == EmailMode.MODE_INBOX){
                email.setBodyMail(encryption.decrypt(email.getBodyMail(), email.getKeyMail()));
                emails.add(email);
            }
        }

        return emails;
    }

    public void delete(Long id) {
        emailRepository.delete(id);
    }

    public void mark(Long idMail) {
        Email email = emailRepository.findAllByIdMail(idMail);

        if(email.getMarkedMail()==0){
            email.setMarkedMail(1);
        }
        else{
            email.setMarkedMail(0);
        }
        emailRepository.save(email);
    }

    public Email findAllByIdMail(Long idMail){
        return emailRepository.findAllByIdMail(idMail);
    }

    public String getMail(Email email) {
        return encryption.decrypt(email.getBodyMail(), email.getKeyMail());
    }
}
