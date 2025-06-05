package kom.yash.simpleWebApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import kom.yash.simpleWebApp.models.product;
import kom.yash.simpleWebApp.repo.ProductRepo;

@Service
public class productService {

    @Autowired
    ProductRepo repo;


    public List<product> getAllProducts(){
        return repo.findAll();
    }

    public product getProductById(int prodId){
        return repo.findById(prodId).orElse(new product()); // Return null if not found
    }

    public void addProduct(product prod) {
        repo.save(prod);
    }

    public void updateProduct(int prodId, product prod) {
        repo.save(prod);
    }

    public void deleteProduct(int prodId) {
        repo.deleteById(prodId);
    }
}


