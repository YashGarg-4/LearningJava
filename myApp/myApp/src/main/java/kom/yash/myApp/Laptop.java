package kom.yash.myApp;

import org.springframework.stereotype.Component;

@Component // Using this means this bean (Java Obj) must be managed by the spring boot container and we musn't need to create objects
public class Laptop {

    public void compile(){
        System.out.println("compiling...");
    }
}
