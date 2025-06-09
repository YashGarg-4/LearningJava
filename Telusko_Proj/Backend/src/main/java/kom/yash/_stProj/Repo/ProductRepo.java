package kom.yash._stProj.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kom.yash._stProj.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    // This interface will automatically provide CRUD operations for Product entities
}
