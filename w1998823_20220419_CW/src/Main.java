import java.io.IOException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scan=new Scanner(System.in);
        WestminsterShoppingManager manager= new WestminsterShoppingManager();
        String mainMenu="""
                            WestminsterShoppingManager
                          ------------------------------
                       1)Add a new Product
                       2)Delete a product
                       3)Print the List
                       4)Save File
                       5)Load File
                       6)Open GUI
                       7)End Program
            """;

        while(true) {
            System.out.println(mainMenu);
            System.out.println("Enter Option(1-7) : ");
            String menu= scan.next();
            switch (menu) {
                case ("1"):
                    manager.addProduct();
                    break;
                case ("2"):
                    manager.deleteProduct();
                    break;
                case ("3"):
                    manager.printProduct();
                    break;
                case ("4"):
                    manager.saveFile();
                    break;
                case ("5"):
                    manager.loadFile();
                    break;
                case ("6"):
                    manager.loadGUI();
                    break;
                case ("7"):
                    System.out.println("Ending Program......");
                    return; // exit the loop and end the program
                default:
                    System.out.println("Invalid option. Please enter a valid option.");
            }
        }
    }
}
