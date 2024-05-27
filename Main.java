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

                    Inventory inventoryO = new Inventory();
    
                    System.out.println("Do you want to add products to your " +nameInv + " inventory ?" );
                    System.out.println("1. Add Products");
                    System.out.println("2. I don´t want to add products");

                    int productsOption = scanner.nextInt();

                    // Create a new object inventory with the data
                    inventoryO.setName(nameInv);
                    inventoryO.setDescription(descrip);

                    // Create the inventory txt
                    inventoryO.createTXT(txtName, nameInv, descrip);


                    if (productsOption == 1) {

                        inventoryO.setInventory(inventoryO.addProducts(nameInv, txtName));

                    } else {
                        
                        System.out.println("Your " + nameInv + " inventory was created succesfully");
                    }
   

                    

                   
                    

                }
            }
        }

        
        
    }
}