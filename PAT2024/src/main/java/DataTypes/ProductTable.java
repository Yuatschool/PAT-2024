/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataTypes;

/**
 *
 * @author User-Pc
 */
public class ProductTable {

  
    private int itemId;
    private String itemName;
    private String itemType;
    private double itemPrice;
    private int itemQuantity; // Assuming quantity can be fractional

    // Constructor
    public ProductTable(int itemId, String itemName, String itemType, double itemPrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity; // Assign the passed itemQuantity
    }

    // Getters and setters
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getItemQuantity() {
        return itemQuantity;
    }

    // Use double for the setter as well, to match the type
    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}
