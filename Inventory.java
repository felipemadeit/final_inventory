import java.io.BufferedWriter;
import java.io.FileWriter;
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

    /**
     * This method create a txt file with the inventory
     * 
     * @param txtName the name of the file 
     * @param  nameInv the name of the inventory
     * @param descrip the description of the inventory
     */

    public void createTXT (String txtName, String nameInv, String descrip) {

        try {

            String pathSave = "inventoryTxtFiles" +  "/" + txtName;
            FileWriter writer = new FileWriter(pathSave);

            writer.write(nameInv+"\n");
            writer.write(descrip +"\n");
            writer.close();

        } catch (IOException e) {

            System.out.println("Error can not create a txt file");
        }

        

    }

    /**
     * This method append to the inventory txt the products
     * 
     * @param txtName the name of the file to append the products
     * @param name the name of the product                                           
     * @param quantity the quantity of the product
     * @param description the description of the product
     * 
     */

     public void appendToTxt(String txtName, String name, int quantity, String description) {
        
        // The path of the file to append the products
        String filePath = txtName;


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
        
            // Write in the file the product data
            writer.write(name + "\n");
            writer.write(quantity + "\n");
            writer.write(description + "\n");
        
            System.out.println("Successfully appended product to " + filePath);
        } catch (IOException e) {
        
            System.err.println("An error occurred while appending to the file: " + e.getMessage());
        }
    }

    /**
     * This method get the data from the user to create an add products to the inventory
     * 
     * @param inventoryName
     * @return an arraylist with Products
     */
    public ArrayList<Product> addProducts (String inventoryName, String fileName) {

        // Create an arraylist to storage the products
        ArrayList<Product> products = new ArrayList<>();

        System.out.println("Enter the quantity of the products");
        int productQuantity = scanner.nextInt();

        // Get quantity times the product data
        for (int i = 0; i < productQuantity; i++) {

            System.out.println("Enter the name of the product: ");
            String productName = scanner.next();


            System.out.println("Please enter the quantity of the " + productName);
            int quantityProd = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Please enter a short description for the " + productName+ " product");
            String productDescription = scanner.nextLine();

            System.out.println(productName);
            System.out.println(quantityProd);
            System.out.println(productDescription);

            String pathFile = "inventoryTxtFiles" + "/" + fileName;

            appendToTxt(pathFile, productName, quantityProd, productDescription);

            // Create a new product 
            Product newProduct = new Product(productName, quantityProd, productDescription);
            
            // Add a the product to the products array
            products.add(newProduct);
            
        }
        // Return the products array
        return products;
    }




    
    
}
