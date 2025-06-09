package kom.yash._stProj.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kom.yash._stProj.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    
}
