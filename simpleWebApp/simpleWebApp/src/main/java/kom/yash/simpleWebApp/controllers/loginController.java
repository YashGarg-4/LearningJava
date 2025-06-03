package kom.yash.simpleWebApp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class loginController {

    @RequestMapping("/logIn")
    public String login(){
        return "You are trying to login";
    }

    @RequestMapping("/signUp")
    public String signUp(){
        return "You are trying to Sign Up!";
    }

}
