import java.io.Serializable;

public class Clothings extends Product implements Serializable {
    private String size;
    private String color;
    public Clothings(String productID, String productName, int noOfAvailableItems, int price, String size, String color) {
        super(productID, productName, noOfAvailableItems, price);
        this.size=size;
        this.color=color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "------Clothing------." + "\n"+
                "Product ID : " +getProductsID() +"\n" +
                "product Name :" +getProductName() + "\n"+
                "No of available Items" + getnoOfAvailableItems()+"\n"+
                "price :" + getPrice()+"\n"+
                "size :" + size + "\n"+
                "color:" + color +"\n";
    }
}
