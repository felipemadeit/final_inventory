import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Inventory {

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
     */

    public void createTXT (String txtName, String nameInv, int quant, String descrip) {

        try {

            FileWriter writer = new FileWriter(txtName);

            writer.write(nameInv+"\n");
            writer.write(quant+"\n");
            writer.write(descrip +"\n");
            writer.close();

        } catch (IOException e) {

            System.out.println("Error can not create a txt file");
        }

        

    }

    public void appendToTxt (String filePath, String name, int quantity, String description) {

         try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
             
            writer.write(name + "," + quantity + "," + description);

            writer.newLine();
         } catch (IOException e) {
            System.err.println("An error ocurred while the data was creating");
         }
    }


    
    
}
