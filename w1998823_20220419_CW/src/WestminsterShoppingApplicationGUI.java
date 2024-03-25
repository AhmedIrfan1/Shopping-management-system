import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class WestminsterShoppingApplicationGUI {
    private static DefaultTableModel tableModel;
    private static DefaultTableModel shoppingCartTableModel;
    private static JFrame frame;
    private static JLabel selectedProductLabel;
    private static JTextArea productDetailsTextArea;

    private static JTable shoppingCartTable;

    private static ShoppingCart shoppingCart;


    public static void loadGUI() {
        shoppingCart = new ShoppingCart();
        // Main window
        JFrame frame = new JFrame("Westminster Shopping Centre");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JPanels
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel label = new JLabel("Select Product Category:");

        String[] productTypes = {"All", "Electronics", "Clothes"};
        JComboBox<String> comboBox = new JComboBox<>(productTypes);

        // ActionListener for selection changes
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProductType = (String) comboBox.getSelectedItem();
                System.out.println("Selected Product Type: " + selectedProductType);
            }
        });

        // Shopping Cart button
        JButton shoppingCartButton = new JButton("Shopping Cart");

        shoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openShoppingCartWindow();
            }
        });

        comboPanel.add(label);
        comboPanel.add(comboBox);
        comboPanel.add(shoppingCartButton);

        // Adding to main panel
        panel.add(comboPanel);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Creating Table Model
        tableModel = new DefaultTableModel(new Object[]{"Product ID", "Name", "Price($)", "Info"}, 0);

        for (Product product : WestminsterShoppingManager.productList) {
            Object[] row = {product.getProductsID(), product.getProductName(),
                    product.getnoOfAvailableItems(), product.getPrice()};
            tableModel.addRow(row);
        }

        JTable table = new JTable(tableModel);

        table.setPreferredScrollableViewportSize(new Dimension(500, 150));

        // ListSelectionListener for row selection changes
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    displaySelectedProductDetails(selectedRow);
                }
            }
        });


        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel productDetailsTitleLabel = new JLabel("Product Details");
        panel.add(productDetailsTitleLabel);

        // JTextArea for product details
        productDetailsTextArea = new JTextArea();
        productDetailsTextArea.setEditable(false);

        productDetailsTextArea.setPreferredSize(new Dimension(500, 150));

        panel.add(productDetailsTextArea);

        JButton addToCartButton = new JButton("Add to Cart");

        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected row from the main table
                int selectedRow = table.getSelectedRow();

                if (selectedRow >= 0) {
                    // Get product details for selected row
                    String productId = (String) tableModel.getValueAt(selectedRow, 0);

                    Product selectedProduct = searchProductById(productId);

                    if (selectedProduct != null) {
                        // Adding selected product to the shopping cart
                        shoppingCart.addProduct(selectedProduct);
                        selectedProduct.setnoOfAvailableItems(selectedProduct.getnoOfAvailableItems()-1);
                        JOptionPane.showMessageDialog(frame, "Product added to the shopping cart!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error: Product not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });


        panel.add(addToCartButton);

        frame.getContentPane().add(panel);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void displaySelectedProductDetails(int selectedRow) {
        // retrieve product details from selected row
        String productID = (String) tableModel.getValueAt(selectedRow, 0);

        Product selectedProduct = searchProductById(productID);

        // Display the product details
        if (selectedProduct != null) {
            String productDetails = String.format("Selected Product Details:\n\n" +
                            "Product ID: %s\n" +
                            "Name: %s\n" +
                            "Available Items: %d\n" +
                            "Price: %s",
                    selectedProduct.getProductsID(), selectedProduct.getProductName(), selectedProduct.getnoOfAvailableItems(),
                    selectedProduct.getPrice());
            productDetailsTextArea.setText(productDetails);
        }
    }

    private static Product searchProductById(String productID) {
        for (Product product : WestminsterShoppingManager.productList) {
            if (product.getProductsID() == productID) {
                return product;
            }
        }
        return null;
    }
    private static void openShoppingCartWindow() {
        JFrame shoppingCartFrame = new JFrame("Shopping Cart");
        shoppingCartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        shoppingCartTableModel = new DefaultTableModel(new Object[]{"Product ID", "Name", "Quantity", "Price"}, 0);
        shoppingCartTable = new JTable(shoppingCartTableModel);
        JScrollPane scrollPane = new JScrollPane(shoppingCartTable);

        List<ShoppingCartProduct> cartProducts = shoppingCart.getCartProducts();
        for (ShoppingCartProduct cartProduct : cartProducts) {
            Product product = cartProduct.getProduct();
            int quantity = cartProduct.getQuantity();

            Object[] row = {product.getProductsID(), product.getProductName(), quantity, product.getPrice()};
            shoppingCartTableModel.addRow(row);
        }


        // Adding components
        shoppingCartFrame.getContentPane().add(scrollPane);

        shoppingCartFrame.setSize(400, 300);
        shoppingCartFrame.setLocationRelativeTo(frame);
        shoppingCartFrame.setVisible(true);

    }

}
