import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        int optionMenu = 0;


        while (optionMenu != 10) {

            // Software menu
            System.out.println("Welcome to the best inventory software");
            System.out.println("What do you want to do today ?");
            System.out.println("1. Create new inventory");
            System.out.println("Select an option: ");

            optionMenu = scanner.nextInt();
            

            switch (optionMenu) {
                case 1 -> {
                    // Create a new inventory
                    System.out.println("Enter the name of the inventory");
                    String nameInv = scanner.next();
                    scanner.nextLine();
                    // The inventory will saved in a txt file
                    String txtName = nameInv + ".txt";

                    System.out.println("Enter a short description to " + nameInv + " inventory");
                    String descrip = scanner.nextLine();

                    System.out.println("Enter the quantity of products to the inventary");
                    int quant = scanner.nextInt();

                    
    

                    //System.out.println(nameInv + " " + txtName + " " + quant + " " + descrip);

                    // Create a ArrayList with the products
                    ArrayList <Product> inventory = new ArrayList<>();

                    // Get quant times the data product
                    for (int i = 0; i < quant; i++) {

                        System.out.println("Enter the name of the " + (i+1) + " product");
                        String productName = scanner.next();
                        scanner.nextLine();

                        System.out.println("Enter the quantity of the " + productName + " product");
                        int productQuant = scanner.nextInt();

                        scanner.nextLine();
                        System.out.println("Enter a short description of " + productName);
                        String productDescrip = scanner.nextLine();

                        // Create a Product object

                        Product product = new Product(productName, productQuant, productDescrip);

                        // Add to the arraylist the new product

                        inventory.add(product);

                        System.out.println("Your " + (i+1) + " product was succesfully created");
  
                    }    

                    // Create a new object inventory with the data
                    Inventory newInventory = new Inventory(nameInv, quant, descrip, inventory);
                    newInventory.createTXT(txtName, nameInv, quant, descrip);

                }
            }
        }

        
        
    }
}