/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataTypes;

/**
 *
 * @author User-Pc
 */
public class CurrentOrder {

   private int saleId;                // Unique identifier for the sale
    private String itemName;           // Name of the item
    private String itemType;           // Type/category of the item
    private double itemPrice;          // Price per unit of the item
    private int itemQuantity;          // Quantity of the item ordered
    private String customerName;        // Name of the customer
    private boolean isCollected;        // Whether the item has been collected by the customer
    private String cardType;           // Payment card type used by the customer
    private double totalCost;          // Total price calculated from item price and quantity
   
    // Constructor to initialize a CurrentOrder object
    public CurrentOrder(String itemName, String itemType, double itemPrice, int itemQuantity, String customerName, boolean isCollected, String cardType,double totalCost) {
        this.itemName = itemName;                  // Set item name
        this.itemType = itemType;                  // Set item type
        this.itemPrice = itemPrice;                // Set item price
        this.itemQuantity = itemQuantity;          // Set item quantity
        this.customerName = customerName;          // Set customer name
        this.isCollected = isCollected;            // Set collection status
        this.cardType = cardType;                  // Set payment card type
        this.totalCost = itemPrice * itemQuantity; // Calculate total price
    }

    // Getter for total cost
    public double getTotalCost() {
        return totalCost;                          // Return total cost
    }

    // Setter for total cost
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;                // Set total cost
    }

    // Getter for sale ID
    public int getSaleId() {
        return saleId;                             // Return sale ID
    }

    // Setter for sale ID
    public void setSaleId(int saleId) {
        this.saleId = saleId;                      // Set sale ID
    }

    // Getter for collected status
    public boolean isCollected() {
        return isCollected;                         // Return collection status
    }

    // Setter for collected status
    public void setCollected(boolean isCollected) {
        this.isCollected = isCollected;            // Set collection status
    }

    // Getter for item name
    public String getItemName() {
        return itemName;                           // Return item name
    }

    // Setter for item name
    public void setItemName(String itemName) {
        this.itemName = itemName;                  // Set item name
    }

    // Getter for item type
    public String getItemType() {
        return itemType;                           // Return item type
    }

    // Setter for item type
    public void setItemType(String itemType) {
        this.itemType = itemType;                  // Set item type
    }

    // Getter for item price
    public double getItemPrice() {
        return itemPrice;                          // Return item price
    }

    // Setter for item price
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;                // Set item price
    }

    // Getter for item quantity
    public int getItemQuantity() {
        return itemQuantity;                       // Return item quantity
    }

    // Setter for item quantity
    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;          // Set item quantity
    }

    // Getter for customer name
    public String getCustomerName() {
        return customerName;                       // Return customer name
    }

    // Setter for customer name
    public void setCustomerName(String customerName) {
        this.customerName = customerName;          // Set customer name
    }

    // Getter for card type
    public String getCardType() {
        return cardType;                           // Return card type
    }

    // Setter for card type
    public void setCardType(String cardType) {
        this.cardType = cardType;                  // Set card type
    }

    // Method to display item details
    public String displayItemDetails() {
        return "Item Name: " + itemName
                + "\nItem Type: " + itemType
                + "\nItem Price: $" + itemPrice
                + "\nItem Quantity: " + itemQuantity
                + "\nTotal Price: $" + totalCost   // Display total cost
                + "\nCustomer Name: " + customerName
                + "\nIs Collected: " + (isCollected ? "Yes" : "No")
                + "\nCard Type: " + cardType;
    }
}
