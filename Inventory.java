import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {

    Scanner scanner = new Scanner(System.in);
    private String name;
    private int quantityProducts;
    private String description;
    private ArrayList<Product> inventory;

    // Constructors, getter, setter and toString

    public Inventory() {
        this.inventory = new ArrayList<>();
    }

    public Inventory(String name, int quantityProducts, String description, ArrayList<Product> inventory) {
        this.name = name;
        this.quantityProducts = quantityProducts;
        this.description = description;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantityProducts() {
        return quantityProducts;
    }

    public void setQuantityProducts(int quantityProducts) {
        this.quantityProducts = quantityProducts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Product> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Product> inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Inventory{");
        sb.append("name=").append(name);
        sb.append(", quantityProducts=").append(quantityProducts);
        sb.append(", description=").append(description);
        sb.append(", inventory=").append(inventory);
        sb.append('}');
        return sb.toString();
    }

    // Methods of the class

    /**
     * This method create a txt file with the inventory
     * 
     * @param txtName the name of the file
     * @param nameInv the name of the inventory
     * @param descrip the description of the inventory
     */
    public void createTXT(String txtName, String nameInv, String descrip) {

        try {
            // try to create a txt and save the data of the inventory
            String pathSave = "inventoryTxtFiles" + "/" + txtName;
            FileWriter writer = new FileWriter(pathSave);

            writer.write(nameInv + "\n");
            writer.write(descrip + "\n");
            writer.close();

        } catch (IOException e) {
            System.out.println("Error can not create a txt file");
        }
    }

    /**
     * This method append to the inventory txt the products
     * 
     * @param txtName     the name of the file to append the products
     * @param name        the name of the product
     * @param quantity    the quantity of the product
     * @param description the description of the product
     */
    public void appendToTxt(String txtName, String name, int quantity, String description) {

        // The path of the file to append the products
        String filePath = txtName;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {

            // Write in the file the product data
            writer.write(name + "\n");
            writer.write(quantity + "\n");
            writer.write(description + "\n");

            System.out.println("Correctly created product");

        } catch (IOException e) {

            System.err.println("An error occurred while appending to the file: " + e.getMessage());
        }
    }

    /**
     * This method get the data from the user to create an add products to the
     * inventory
     * This method use the appendToTxt method
     * 
     * @param inventoryName
     * @param fileName
     * @return products an arraylist with Products
     */
    public ArrayList<Product> addProducts(String inventoryName, String fileName) {

        // Create an arraylist to storage the products
        ArrayList<Product> products = new ArrayList<>();

        System.out.println("Enter the quantity of the products");
        int productQuantity = scanner.nextInt();
        scanner.nextLine();

        // Get quantity times the product data
        for (int i = 0; i < productQuantity; i++) {

            System.out.println("Enter the name of the product: ");
            String productName = scanner.nextLine();
            System.out.println();

            System.out.println("Please enter the quantity of the " + productName);
            int quantityProd = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            System.out.println("Please enter a short description for the " + productName + " product");
            String productDescription = scanner.nextLine();
            System.out.println();

            String pathFile = "inventoryTxtFiles" + "/" + fileName;

            appendToTxt(pathFile, productName, quantityProd, productDescription);

            // Create a new product
            Product newProduct = new Product(productName, quantityProd, productDescription);

            // Add a the product to the products array
            products.add(newProduct);

            System.out.println();
            System.out.println();

            // If is the last product
            if (i == productQuantity - 1) {
                System.out.println();
                System.out.println("You have added " + productQuantity + " products to your inventory ");
            }
        }

        // Return the products array
        return products;
    }

    public static File[] getListFiles(File folder) {

        File[] txtFiles = new File[0];

        if (folder.isDirectory()) {
            FilenameFilter filterOnlyTxt = (dir, name) -> name.toLowerCase().endsWith(".txt");
            txtFiles = folder.listFiles(filterOnlyTxt);
        }
        if (txtFiles != null) {
            System.out.println("Inventory quantity: " + txtFiles.length);
        } else {
            System.out.println("The folder does not exist or is empty");
        }

        return txtFiles;
    }

    /**
     * This method get the quantity of the inventories 
     * 
     * @return inventoryLoaded 
     */
    public static Inventory chargeQuantityFiles(File txtFile) {

        Inventory inventoryLoaded = null;

        if (txtFile != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(txtFile))) {
                // Read the txt data 
                String inventoryNm = br.readLine();
                String inventoryDs = br.readLine();

                inventoryLoaded = new Inventory();
                inventoryLoaded.setName(inventoryNm);
                inventoryLoaded.setDescription(inventoryDs);

                ArrayList<Product> products = new ArrayList<>();
                String line;

                while ((line = br.readLine()) != null) {
                    String productName = line;
                    int productQuanti = Integer.parseInt(br.readLine());
                    String productDesc = br.readLine();

                    Product productToLoad = new Product(productName, productQuanti, productDesc);
                    products.add(productToLoad);
                }

                inventoryLoaded.setInventory(products);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("The txt has no data");
        }

        return inventoryLoaded;
    }

    /**
     * This method updates the quantity of a specific product
     * 
     * @param productName the name of the product
     * @param newQuantity the new quantity to set
     */
    public void updateProductQuantity(String productName, int newQuantity) {
        for (Product product : inventory) {
            if (product.getName().equalsIgnoreCase(productName)) {
                product.setQuantity(newQuantity);
                System.out.println("Quantity of " + productName + " updated to " + newQuantity);
                return;
            }
        }
        System.out.println("Product not found in inventory");
    }

    /**
     * This method removes a specific product from the inventory
     * 
     * @param productName the name of the product to remove
     */
    public void removeProduct(String productName) {
        for (Product product : inventory) {
            if (product.getName().equalsIgnoreCase(productName)) {
                inventory.remove(product);
                System.out.println("Product " + productName + " removed from inventory");
                return;
            }
        }
        System.out.println("Product not found in inventory");
    }

    /**
     * This method changes the name of the inventory
     * 
     * @param newName the new name to set
     */
    public void updateInventoryName(String newName) {
        this.name = newName;
        System.out.println("Inventory name updated to " + newName);
    }

    /**
     * This method updates the description of the inventory
     * 
     * @param newDescription the new description to set
     */
    public void updateInventoryDescription(String newDescription) {
        this.description = newDescription;
        System.out.println("Inventory description updated to " + newDescription);
    }
}
