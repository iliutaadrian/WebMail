package com.mail.controller;

import com.mail.model.Email;
import com.mail.service.EmailService;
import com.mail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class IndexController extends CurrentUser{
    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    WebMailController webMailController;

    @RequestMapping("/inbox")
    public String inbox(HttpServletRequest request){
        if(getCurrentUser()==null){
            return "error1";
        }
        else{
            setAtributes(request);
            request.setAttribute("emails", emailService.findAllSender(getCurrentUser().getAddressAccount()));
            return "inbox";
        }
    }

    @RequestMapping("/compose")
    public String compose(HttpServletRequest request){
        if(getCurrentUser()==null){
            return "error1";
        }
        else {
            setAtributes(request);
            return "compose";
        }
    }

    @RequestMapping("/sent")
    public String sent(HttpServletRequest request){
        if(getCurrentUser()==null){
            return "error1";
        }
        else {
            setAtributes(request);
            request.setAttribute("emails", emailService.findAllReceiver(getCurrentUser().getAddressAccount()));
            return "sent";
        }
    }

    @RequestMapping("/change")
    public String change(HttpServletRequest request){
        if(getCurrentUser()==null){
            return "error1";
        }
        else {
            setAtributes(request);
            return "change";
        }
    }

    @RequestMapping("/marked")
    public String marked(HttpServletRequest request){
        if(getCurrentUser()==null){
            return "error1";
        }
        else {
            setAtributes(request);
            request.setAttribute("emails", emailService.findAllMarked(getCurrentUser().getAddressAccount()));
            return "marked";
        }
    }

    @RequestMapping("/anonymous")
    public String anonymous(HttpServletRequest request){
        if(getCurrentUser()==null){
            return "error1";
        }
        else {
            setAtributes(request);
            request.setAttribute("emails", emailService.findAllAnonymous(getCurrentUser().getAddressAccount()));
            return "anonymous";
        }
    }

    @RequestMapping("/logout")
    public String logout(){
        setCurrentUser(null);
        return "index";
    }

    @PostMapping("/change-user")
    public String changeUser(HttpServletRequest request){
        if(userService.login(getCurrentUser().getAddressAccount(), request.getParameter("oldPassword"))){
            getCurrentUser().setPasswordAccount(request.getParameter("newPassword"));
            userService.save(getCurrentUser());

            request.setAttribute("mode", "MODE_OK");
            return "change";
        }

        request.setAttribute("mode", "MODE_ERROR");
        return "change";
    }

    private boolean mailSend(HttpServletRequest request, String sender){
        String[] receivers = request.getParameter("addressReceiver").split("; ");
        boolean ok = true;

        for (String receiver : receivers) {
            if (userService.findByAddressAccount(receiver) != null) {
                emailService.save(new Email(
                        getCurrentUser().getKeyUser(),
                        sender,
                        userService.findByAddressAccount(receiver).getAddressAccount(),
                        request.getParameter("subjectMail"),
                        request.getParameter("bodyMail")
                ));
            } else {
                ok = false;
            }
        }

        if (ok){
            setAtributes(request);
            request.setAttribute("emails", emailService.findAllReceiver(getCurrentUser().getAddressAccount()));
            return true;
        }

        return false;
    }

    @PostMapping("/send-email")
    public String sendEmail(HttpServletRequest request) {
        request.setAttribute("account", getCurrentUser().getAddressAccount());

        if (request.getParameter("anonymous") != null) {
            if(mailSend(request, "anonymous")){
                return "sent";
            }
        }
        else {
            if(mailSend(request, getCurrentUser().getAddressAccount())){
                return "sent";
            }
        }

        request.setAttribute("mode", "MODE_ERROR");
        return "compose";
    }


    @RequestMapping("/delete-mail")
    public String deleteMail(@RequestParam Map<String,String> requestParams, HttpServletRequest request) {
        emailService.delete(Long.valueOf(requestParams.get("idMail")));
        setAtributes(request);

        if(requestParams.get("call").equals("sent")){
            request.setAttribute("emails", emailService.findAllReceiver(getCurrentUser().getAddressAccount()));
            return "sent";
        }


        if(requestParams.get("call").equals("marked")){
            request.setAttribute("emails", emailService.findAllMarked(getCurrentUser().getAddressAccount()));
            return "marked";
        }


        if(requestParams.get("call").equals("anonymous")){
            request.setAttribute("emails", emailService.findAllAnonymous(getCurrentUser().getAddressAccount()));
            return "anonymous";
        }

        request.setAttribute("emails", emailService.findAllSender(getCurrentUser().getAddressAccount()));
        return "inbox";
    }

    @RequestMapping("/marked-mail")
    public String markedMail(@RequestParam Map<String,String> requestParams, HttpServletRequest request) {
        emailService.mark(Long.valueOf(requestParams.get("idMail")));
        setAtributes(request);


        if(requestParams.get("call").equals("marked")){
            request.setAttribute("emails", emailService.findAllMarked(getCurrentUser().getAddressAccount()));
            return "marked";
        }

        request.setAttribute("emails", emailService.findAllSender(getCurrentUser().getAddressAccount()));
        return "inbox";
    }

    @RequestMapping("/details-mail")
    public String detailsMail(@RequestParam Map<String,String> requestParams, HttpServletRequest request) {
        Email email = emailService.findAllByIdMail(Long.valueOf(requestParams.get("idMail")));
        setAtributes(request);
        request.setAttribute("addressReceiver", email.getAddressReceiver());
        request.setAttribute("subjectMail", email.getSubjectMail());
        request.setAttribute("bodyMail", emailService.getMail(email));

        return "details";
    }

    private void setAtributes(HttpServletRequest request){
        request.setAttribute("account", getCurrentUser().getAddressAccount());
        request.setAttribute("profile", getCurrentUser().getPicture());
        request.setAttribute("firstName", getCurrentUser().getFirstName());
        request.setAttribute("lastName", getCurrentUser().getLastName());
        request.setAttribute("phone", getCurrentUser().getPhoneNumber());
    }
}
