package kom.yash.simpleWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kom.yash.simpleWebApp.services.productService;
import kom.yash.simpleWebApp.models.product;
import java.util.List;

@RestController
public class productController {

    @Autowired
    private productService productService;

    @RequestMapping("/products")
    public List<product> getAllProducts() {
        return productService.getAllProducts();
    }
}