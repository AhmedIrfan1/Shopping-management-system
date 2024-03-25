import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;

public class WestminsterShoppingManager implements ShoppingManager,Serializable{
    protected static ArrayList<Product> productList = new ArrayList<>();//creating a list to store the doctor details
    private Scanner input = new Scanner(System.in);//initialising a scanner to get input
    private String menu;//creating a variable to get user menu





    @Override
    public void addProduct() {
        if(productList.size() <= 50){
            System.out.println("Enter the Product ID please: ");
            String productID = input.next();

            System.out.println("Enter the product's name: ");
            String productName = input.next();

            System.out.println("Enter the no. of available items of this product: ");
            int noOfAvailableItems = input.nextInt();

            System.out.println("Enter the price of the product: ");
            int price = input.nextInt();

            System.out.println("Select 1 if electronic or 2 if clothing");
            int choice = input.nextInt();

            if(choice == 1){
                System.out.println("Enter the brand of the electronic: ");
                String brand = input.next();

                System.out.println("Enter the warranty period in weeks: ");
                String warranty = input.next();

                Product product = new Electronics(productID, productName + "\t", noOfAvailableItems,  price, brand + "\t", warranty + "\t" );
                productList.add(product);


                System.out.println(noOfAvailableItems + " " + brand + " " + productName + " with the price of " + price + " has been added to the system" );
            } else if (choice == 2) {
                System.out.println("Enter the size of the cloth. S/M/L: ");
                String size = input.next();

                System.out.println("Enter the colour of the cloth");
                String colour = input.next();

                Product product = new Clothings(productID, productName , noOfAvailableItems  ,  price, size , colour );
                productList.add(product);


                System.out.println(noOfAvailableItems + " " + size + " " + colour + " " + productName + " with the price of " + price  + " has been added to the system.");

            }
            else{
                System.out.println("The type of the product wasn't entered properly.");
                return;
            }

            //System.out.println("The products were successfully added to the system");

//            System.out.println("The items in the system right now are: ");

//            for (int i = 0; i < productList.size(); i++) {
//                System.out.println((i + 1) + ": " + productList.get(i) + ",");
//            }
//            System.out.println("\n ");



        } else{
            System.out.println("There are already 50 products in the system");//printing an error message if the system has 10 products already
            System.out.println("If you want to add more products, delete a product from the system");
            System.out.println(" ");
        }
    }

    @Override
    public void deleteProduct() {
        for (Product z : productList) {
            System.out.println(z);
        }
        System.out.println("Enter Product ID of the product you want to delete : ");
        String temp_id = input.next();
        boolean productFound = false;

        for (Product product : productList) {
            if (temp_id.equalsIgnoreCase(product.getProductsID())) {
                productList.remove(product);
                productFound = true;
                break;
            }
        }
        if (productFound) {
            System.out.println("Product deleted!");
            System.out.println("Available products :" + productList.size());
            System.out.println(productList);
        } else {
            System.out.println("Product not found.");
        }

    }

       // System.out.println("product deleted!");
        //System.out.println("Available products" +productList.size());
        //System.out.println(productList);

//        if(productList.size() >= 50){
//            System.out.println("Enter the product ID to delete the product from the system.");
//            String product_id = input.next();
//            for(Product product : productList){
//                if(product.getProductsID().equalsIgnoreCase(product_id)){
//                    System.out.println("Do you want to delete "  + product.getProductsID());
//                    System.out.println("Enter yes or no?");
//                    String menu = input.next();
//
//                    if(menu.equalsIgnoreCase("yes")){
//                        if(product.getnoOfAvailableItems() == 1){
//                            productList.remove(product);
//                        }
//                        else if(product.getnoOfAvailableItems() > 1){
//                            System.out.println("There are " + product.getnoOfAvailableItems() + product.getProductName() + "s in the system");
//                            System.out.println("How many of the " + product.getProductName() + "s do you want to delete.");
//                            int count  = input.nextInt();
//                            for(int i = 0; i < count; i++){
//                                productList.remove(product);
//                            }
//                        }
//
//                        System.out.println("------------------------------------------------------------------");
//                        System.out.println(product.get    noOfAvailableItems() + " " + product.getProductName() + " has been successfully deleted from the system");
//                        System.out.println("There are " + productList.size() + " products in the system right now. Thank you ");
//                        System.out.println("------------------------------------------------------------------");
//                        break;
//                    }
//                    else{
//                        return;
//                    }
//                } else if(!(product.getProductsID().equalsIgnoreCase(product_id))){
//                    System.out.println(product_id  + " is not in the system.");
//                    break;
//                }
//            }
//
//        } else{
//            System.out.println("There are no products to delete from the system. : (((");
//        }



    @Override
    public void printProduct() {
        System.out.println("The items in the system right now are: ");

        for(Product x : productList){
            System.out.println(x);
        }

//        if(productList.size() >= 1){
//            for (int i = 0; i < productList.size(); i++) {
//                System.out.println((i + 1) + ": " + productList.get(i) + ",");
//            }
//            System.out.println("\n ");
//        }
//        else{
//            System.out.println("There are no products in the system");
//        }


    }

    @Override
    public void saveFile() throws IOException {
        FileOutputStream f = new FileOutputStream(new File("ProductDetails.txt"));//creates new next file
        ObjectOutputStream o = new ObjectOutputStream(f);

        try{
            for(Product product : productList){//traverses through the text file
                o.writeObject(product);//writes them to the text file
            }
        }catch (EOFException e){
            System.out.println("Reached End of File");
        }catch (IOException e){
            e.printStackTrace();
        }

        f.close();//closes the file
        o.close();//closes the file
        System.out.println("The file has been successfully saved!!..");//prints sucess message
    }

    @Override
    public void loadFile() throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream("ProductDetails.txt"); // new text file to load the details
        ObjectInputStream oi = new ObjectInputStream(fi);
        for(;;){
            try{
                Product product =    (Product) oi.readObject();//reads through the object
                productList.add(product);
            }catch(EOFException e){
                break;
            }
        }
        fi.close();
        oi.close();

        System.out.println("---------------------------------------------------------");
        System.out.println(" Products Loaded Successfully");
    }

        @Override
        public void loadGUI() {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    WestminsterShoppingApplicationGUI westminsterShoppingApplicationGUI = new WestminsterShoppingApplicationGUI();
                    WestminsterShoppingApplicationGUI.loadGUI();
                }
            });
        }


}



