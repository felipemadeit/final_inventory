import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static HashMap<String, Integer> users = new HashMap<>();
    static String currentUser;

    public static void main(String[] args) {

        // Add some predefined users (for testing purposes)
        users.put("user1", 0);
        users.put("user2", 0);

        // Perform login
        if (!login()) {
            System.out.println("Invalid login. Exiting program.");
            return;
        }

        int optionMenu = 0;

        while (optionMenu != 3) {

            // Software menu
            System.out.println();
            System.out.println("What do you want to do today, " + currentUser + "?");
            System.out.println();
            System.out.println("1. Create new inventory");
            System.out.println();
            System.out.println("2. View and modify inventories");
            System.out.println();
            System.out.println("3. Exit");
            System.out.println("Select an option: ");

            // Get the user option 
            optionMenu = scanner.nextInt();
            scanner.nextLine();

            switch (optionMenu) {

                // The first case is for creating a new inventory 
                case 1 -> {

                    // Create a new inventory
                    System.out.println("Enter the name of the inventory");
                    String nameInv = scanner.nextLine();
                    System.out.println();

                    // The inventory will be saved in a txt file
                    String txtName = nameInv + ".txt";

                    System.out.println("Enter a short description for the " + nameInv + " inventory");
                    String descrip = scanner.nextLine();
                    System.out.println();

                    Inventory inventoryO = new Inventory();
    
                    // After creating the inventory the user can choose whether to add products or not
                    System.out.println("Do you want to add products to your " + nameInv + " inventory?");
                    System.out.println();
                    System.out.println("1. Add Products");
                    System.out.println();
                    System.out.println("2. I don't want to add products");
                    System.out.println();

                    int productsOption = scanner.nextInt();
                    scanner.nextLine();

                    // Create a new object inventory with the data
                    inventoryO.setName(nameInv);
                    inventoryO.setDescription(descrip);

                    // Create the inventory txt
                    inventoryO.createTXT(txtName, nameInv, descrip);

                    if (productsOption == 1) {
                        inventoryO.setInventory(inventoryO.addProducts(nameInv, txtName));
                    } else {
                        System.out.println("Your " + nameInv + " inventory was created successfully");
                    }

                    // Update the user's inventory count
                    users.put(currentUser, users.get(currentUser) + 1);
                }

                case 2 -> {
                    
                    // Array to save the inventories
                    List<Inventory> loadedInventories = new ArrayList<>();

                    Inventory inventoriesToLoad = new Inventory();

                    File folder = new File("inventoryTxtFiles");
                    File[] listTxtFiles = inventoriesToLoad.getListFiles(folder);

                    if (listTxtFiles != null) {
                        for (File txtFile : listTxtFiles) {
                            Inventory newInventory = inventoriesToLoad.chargeQuantityFiles(txtFile);
                            if (newInventory != null) {
                                // Add the inventory to the list 
                                loadedInventories.add(newInventory);
                            }
                        }
                        System.out.println("Inventories: ");
                        System.out.println();
                        for (int i = 0; i < loadedInventories.size(); i++) {
                            Inventory inventory = loadedInventories.get(i);
                            System.out.println((i + 1) + ". Inventory name: " + inventory.getName());
                        }
                        System.out.println("Select an inventory to view or modify (Enter the number): ");
                        int inventoryIndex = scanner.nextInt() - 1;
                        scanner.nextLine();

                        if (inventoryIndex >= 0 && inventoryIndex < loadedInventories.size()) {
                            Inventory selectedInventory = loadedInventories.get(inventoryIndex);
                            System.out.println("Selected Inventory: " + selectedInventory.getName());
                            System.out.println("Description: " + selectedInventory.getDescription());
                            System.out.println("Products:");
                            for (Product product : selectedInventory.getInventory()) {
                                System.out.println("    -> " + product.getName());
                                System.out.println("        Description: " + product.getDescription());
                                System.out.println("        Quantity: " + product.getQuantity());
                                System.out.println();
                            }

                            int modifyOption = 0;
                            while (modifyOption != 6) {
                                System.out.println("What would you like to do?");
                                System.out.println("1. Add a product");
                                System.out.println("2. Remove a product");
                                System.out.println("3. Update product quantity");
                                System.out.println("4. Change inventory name");
                                System.out.println("5. Change inventory description");
                                System.out.println("6. Go back");
                                modifyOption = scanner.nextInt();
                                scanner.nextLine();

                                switch (modifyOption) {
                                    case 1 -> {
                                        // Add a new product
                                        System.out.println("Enter product name: ");
                                        String productName = scanner.nextLine();
                                        System.out.println("Enter product quantity: ");
                                        int productQuantity = scanner.nextInt();
                                        scanner.nextLine();
                                        System.out.println("Enter product description: ");
                                        String productDescription = scanner.nextLine();

                                        selectedInventory.getInventory().add(new Product(productName, productQuantity, productDescription));
                                        selectedInventory.appendToTxt("inventoryTxtFiles/" + selectedInventory.getName() + ".txt", productName, productQuantity, productDescription);
                                        System.out.println("Product added successfully.");
                                    }
                                    case 2 -> {
                                        // Remove a product
                                        System.out.println("Enter product name to remove: ");
                                        String productName = scanner.nextLine();
                                        selectedInventory.removeProduct(productName);
                                        updateInventoryFile(selectedInventory);
                                    }
                                    case 3 -> {
                                        // Update product quantity
                                        System.out.println("Enter product name to update quantity: ");
                                        String productName = scanner.nextLine();
                                        System.out.println("Enter new quantity: ");
                                        int newQuantity = scanner.nextInt();
                                        scanner.nextLine();
                                        selectedInventory.updateProductQuantity(productName, newQuantity);
                                        updateInventoryFile(selectedInventory);
                                    }
                                    case 4 -> {
                                        // Change inventory name
                                        System.out.println("Enter new inventory name: ");
                                        String newName = scanner.nextLine();
                                        selectedInventory.updateInventoryName(newName);
                                        updateInventoryFile(selectedInventory);
                                    }
                                    case 5 -> {
                                        // Change inventory description
                                        System.out.println("Enter new inventory description: ");
                                        String newDescription = scanner.nextLine();
                                        selectedInventory.updateInventoryDescription(newDescription);
                                        updateInventoryFile(selectedInventory);
                                    }
                                    case 6 -> System.out.println("Going back to main menu.");
                                    default -> System.out.println("Invalid option. Please try again.");
                                }
                            }
                        } else {
                            System.out.println("Invalid inventory selection.");
                        }
                    } else {
                        System.out.println("There are no files in the folder 'inventoryTxtFiles'");
                    }
                    break;
                }
            }
        }
    }

    public static boolean login() {
        System.out.println("Please select an option: ");
        System.out.println("1. Create User");
        System.out.println("2. Login");
        int optionUser = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left-over

        switch (optionUser) {
            case 1:
                System.out.println("Enter your username: ");
                String newUserName = scanner.nextLine();
                if (users.containsKey(newUserName)) {
                    System.out.println("Username already exists. Try logging in.");
                } else {
                    users.put(newUserName, 0);
                    System.out.println(newUserName + " has been registered.");
                }
                return login(); // Call login again to either create another user or login
            case 2:
                System.out.println("Enter username: ");
                String username = scanner.nextLine();

                if (users.containsKey(username)) {
                    currentUser = username;
                    return true;
                } else {
                    System.out.println("Username not found.");
                    return false;
                }
            default:
                System.out.println("Invalid option. Please try again.");
                return login();
        }
    }

    // Method to update the inventory file after any modification
    public static void updateInventoryFile(Inventory inventory) {
        try {
            FileWriter writer = new FileWriter("inventoryTxtFiles/" + inventory.getName() + ".txt");
            writer.write(inventory.getName() + "\n");
            writer.write(inventory.getDescription() + "\n");
            for (Product product : inventory.getInventory()) {
                writer.write(product.getName() + "," + product.getQuantity() + "," + product.getDescription() + "\n");
            }
            writer.close();
            System.out.println("Inventory file updated successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while updating the inventory file.");
            e.printStackTrace();
        }
    }
}
