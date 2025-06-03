package kom.yash.simpleWebApp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homeController {

    @RequestMapping("/")
    public String greet(){
        return "Welcome to the homepage";
    }

    @RequestMapping("/about")
    public String about(){
        return "Tryna learn Java";
    }
}
