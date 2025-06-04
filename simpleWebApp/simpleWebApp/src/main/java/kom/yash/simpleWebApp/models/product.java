package kom.yash.simpleWebApp.models;

import org.springframework.stereotype.Component;

@Component
public class product {

    private int prodId;
    private String name;
    private int price;

    public product() {}

    public product(int prodId, String name, int price) {
        this.prodId = prodId;
        this.name = name;
        this.price = price;
    }

    public int getProdId() { return prodId; }
    public String getProdName() { return name; }
    public int getProdPrice() { return price; }


    public void setProdId(int prodId) { this.prodId = prodId; }
    public void setProdName(String name) { this.name = name; }
    public void setProdPrice(int price) { this.price = price; }
}