package kom.yash.simpleWebApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kom.yash.simpleWebApp.models.product;

@Repository
public interface ProductRepo extends JpaRepository<product, Integer> { //Gotta include the type of `product` and the type of its ID (Integer in this case)
    // This interface will automatically provide CRUD operations for Product entities
    // No additional methods are needed unless custom queries are required

}
