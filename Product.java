public class Product {

    // Atributtes of the class
    private String name;
    private int quantity;
    private String description;

    // Constructors, getter, setter and toString

    public Product() {
    }

    public Product(String name, int quantity, String description) {
        this.name = name;
        this.quantity = quantity;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product{");
        sb.append("name=").append(name);
        sb.append(", quantity=").append(quantity);
        sb.append(", description=").append(description);
        sb.append('}');
        return sb.toString();
    }

    

    
    
}
