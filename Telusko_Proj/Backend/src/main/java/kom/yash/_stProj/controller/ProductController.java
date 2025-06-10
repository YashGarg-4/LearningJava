package kom.yash._stProj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kom.yash._stProj.Service.ProductService;
import kom.yash._stProj.model.Product;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService service;

    @RequestMapping("/")
    public String greet(){
        return "Hello, welcome to the Product API!";
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        // This method will eventually return a list of products
        return service.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProdcutById(@PathVariable int id){
        System.out.println(id);
        return service.getProductById(id);
    }

    @PostMapping("/product")
    public Product addProduct(@RequestBody Product prod) {
        return service.addProduct(prod);
    }

    @PutMapping("/product/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product prod) {
        prod.setId(id);
        return service.updateProduct(prod);
    }
}
