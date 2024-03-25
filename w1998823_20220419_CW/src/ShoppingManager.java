import java.awt.event.ActionEvent;
import java.io.IOException;

public interface ShoppingManager {
    void addProduct();

    void deleteProduct();

    void printProduct();

    void saveFile() throws IOException;

    void loadFile() throws IOException, ClassNotFoundException;

    void loadGUI();
}
