import java.util.ArrayList;
import java.util.List;

public class ShoppingCart{
    private List<ShoppingCartProduct> shoppingCartProducts;

    public ShoppingCart() {
        this.shoppingCartProducts = new ArrayList<>();
    }
    public void addProduct(Product product) {
        for (ShoppingCartProduct shoppingCartProduct : shoppingCartProducts) {
            if (shoppingCartProduct.getProduct().getProductsID().equals(product.getProductsID())) {
                // Incrementing the quantity based on if product exists
                shoppingCartProduct.incrementQuantity();
                return;
            }
        }

        shoppingCartProducts.add(new ShoppingCartProduct(product, 1));
    }

    public List<ShoppingCartProduct> getCartProducts() {
        return shoppingCartProducts;
    }



}
