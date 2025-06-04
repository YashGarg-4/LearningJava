package kom.yash.simpleWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import kom.yash.simpleWebApp.services.productService;
import kom.yash.simpleWebApp.models.product;
import java.util.List;

@RestController
public class productController {

    @Autowired
    private productService productService;

    @GetMapping("/products")
    public List<product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/product/{prodId}")
    public product getProductById(@PathVariable int prodId){
        return productService.getProductById(prodId);
    }

    @PostMapping("/products")
    public void addProduct(@RequestBody product prod) {
        productService.addProduct(prod); 
    }

    @PutMapping("/product/{prodId}")
    public void updateProduct(@PathVariable int prodId, @RequestBody product prod) {
        productService.updateProduct(prodId, prod);
    }
        
}