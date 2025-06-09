package kom.yash._stProj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor //These three annotationfrom lombok help reduce the boiler plate code (Automates getters, setters and constructors for the fields)
public class Product {

    @Id
    private int id;
    private String name;
    private String category;
    private String desc;
    private String brand;
    private int price;
    private Date releaseDate;
    private boolean available;
    private int qty;
}
