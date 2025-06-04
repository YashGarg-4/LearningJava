package kom.yash.simpleWebApp.services;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import kom.yash.simpleWebApp.models.product;

@Service
public class productService {

    List<product> products = Arrays.asList(
        new product(1, "Laptop", 50000),
        new product(2, "Smartphone", 30000),
        new product(3, "Tablet", 20000)
    );

    public List<product> getAllProducts(){
        return products;
    }

    public product getProductById(int prodId){
        return products.stream()
                        .filter(p -> p.getProdId() == prodId)
                        .findFirst().get();
    }
}
