import java.io.Serializable;

public abstract class Product implements Serializable {
    //initializing i
    private String productID;
    private String productName;
    private int noOfAvailableItems;
    private int price;
    private String type;
    public Product(String productID, String productName, int noOfAvailableItems, int price){
        this.productID=productID;
        this.productName=productName;
        this.noOfAvailableItems=noOfAvailableItems;
        this.price=price;
        this.type=type;
    }
    //getters and setters for the attributes

    public void setProductID(String productID) {
        this.productID = productID;
    }
    public String getProductsID(){
        return productID;
    }
    public void setProductName(String ProductName){
        this.productName=productName;
    }
    public String getProductName() {
        return productName;
    }

    public void setnoOfAvailableItems(int noOfAvailableItems){
        this.noOfAvailableItems = noOfAvailableItems;
    }

    public int getnoOfAvailableItems(){
        return noOfAvailableItems;
    }

    public void setPrice(int price){
        this.price=price;
    }
    public int getPrice(){
        return price;
    }
    public void setType(){
        this.type=type;
    }


    public String getType(){
        return type;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID='" + productID + '\'' +
                ", productName='" + productName + '\'' +
                ", noOfAvailableItems=" + noOfAvailableItems +
                ", price=" + price +
                ", type='" + type + '\'' +
                '}';
    }


}
