package com.mail.controller;

import com.mail.model.Users;
import com.mail.service.EmailService;
import com.mail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebMailController extends CurrentUser{
    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    IndexController indexController;

    @PostMapping("/login-user")
    public String loginUser(HttpServletRequest request){
        if(userService.login(
                request.getParameter("addressAccount"),
                request.getParameter("passwordAccount")
        )){
            setCurrentUser(userService.findByAddressAccount(request.getParameter("addressAccount")));
            indexController.setCurrentUser(getCurrentUser());
            return indexController.inbox(request);
        }

        request.setAttribute("mode", "MODE_ERROR");
        return "login";
    }

    @PostMapping("/register-user")
    public String registerUser(HttpServletRequest request) {
        if(userService.findByAddressAccount(request.getParameter("addressAccount"))==null) {
            if(!request.getParameter("addressAccount").isEmpty() &&
                    !request.getParameter("passwordAccount").isEmpty()&&
                    !request.getParameter("firstName").isEmpty()&&
                    !request.getParameter("lastName").isEmpty()&&
                    !request.getParameter("phoneNumber").isEmpty()&&
                    !request.getParameter("luckyNumber").isEmpty()
                    ) {
                userService.save(new Users(
                        request.getParameter("addressAccount"),
                        request.getParameter("passwordAccount"),
                        request.getParameter("firstName"),
                        request.getParameter("lastName"),
                        request.getParameter("phoneNumber"),
                        request.getParameter("luckyNumber"),
                        request.getParameter("profilePicture")
                ));
                request.setAttribute("mode", "MODE_OK");
                return "register";
            }
        }
        request.setAttribute("mode", "MODE_ERROR");

        return "register";
    }

    @PostMapping("/forgot-user")
    public String forgotUser(HttpServletRequest request){
        Users user = userService.findByAddressAccount(request.getParameter("addressAccount"));

        if(user!=null){
            if(!request.getParameter("firstName").isEmpty()&&
                    !request.getParameter("lastName").isEmpty()&&
                    !request.getParameter("phoneNumber").isEmpty()&&
                    !request.getParameter("luckyNumber").isEmpty()
                    ) {
                if (user.getFirstName().equals(request.getParameter("firstName")) &&
                        user.getLastName().equals(request.getParameter("lastName")) &&
                        user.getPhoneNumber().equals(request.getParameter("phoneNumber"))
                        ) {
                    userService.sendEmail(request.getParameter("recoveryEmail"), user);

                    request.setAttribute("mode", "MODE_OK");
                    return "forgot";
                }
            }
        }

        request.setAttribute("mode","MODE_ERROR");
        return "forgot";
    }

    @RequestMapping("/")
    public String welcome(){
        return "index";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @RequestMapping("/forgot")
    public String forgot(){
        return "forgot";
    }
}