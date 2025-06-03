package kom.yash.myApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // Using this means this bean (Java Obj) must be managed by the spring boot container and we musn't need to create objects
public class Dev {

    @Autowired
    Laptop laptop;

    public void build(){
        System.out.println("Working on my first java proj");
        
        laptop.compile();
    }
}
