package kom.yash.simpleWebApp.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kom.yash.simpleWebApp.models.product;

@Service
public class productService {

    List<product> products = new ArrayList<>(Arrays.asList(
        new product(1, "Laptop", 50000),
        new product(2, "Smartphone", 30000),
        new product(3, "Tablet", 20000)
    ));

    public List<product> getAllProducts(){
        return products;
    }

    public product getProductById(int prodId){
        return products.stream()
                   .filter(p -> p.getProdId() == prodId)
                   .findFirst()
                   .orElse(null); // Return null if not found
    }

    public void addProduct(product prod) {
        products.add(prod);
    }

    public boolean updateProduct(int prodId, product prod) {
    for (int i = 0; i < products.size(); i++) {
        if (products.get(i).getProdId() == prodId) {
            products.get(i).setProdName(prod.getProdName());
            products.get(i).setProdPrice(prod.getProdPrice());
            // Uncomment if you want to update prodId as well:
            // products.get(i).setProdId(prod.getProdId());
            return true;
        }
    }
    return false;
    }
}


