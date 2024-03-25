import java.io.Serializable;

public class Electronics extends Product implements Serializable {
    private String brand;
    private String warranty;
    public Electronics(String productID, String productName, int noOfAvailableItems, int price, String brand, String warranty) {
        super(productID, productName, noOfAvailableItems, price);
        this.brand=brand;
        this.warranty=warranty;
    }
    public void setBrand(String brand){
        this.brand=brand;
    }
    public String getBrand(){
        return brand;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getWarranty() {
        return warranty;
    }

    @Override
    public String toString() {
        return "------Electronics------" + "\n"+
                "Product ID: " +getProductsID() +"\n" +
                "product Name: " +getProductName() + "\n"+
                "No of available Items: " + getnoOfAvailableItems()+"\n"+
                "price: " + getPrice()+"\n"+
                "brand: " + brand + "\n"+
                "warranty:  " +warranty+"weeks"+"\n";
    }
}
