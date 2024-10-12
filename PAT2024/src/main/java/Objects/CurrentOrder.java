/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

/**
 *
 * @author User-Pc
 */
public class CurrentOrder {

    private int itemId;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public boolean isIsCollected() {
        return isCollected;
    }

    public void setIsCollected(boolean isCollected) {
        this.isCollected = isCollected;
    }
    private String itemName;
    private String itemType;
    private double itemPrice;
    private int itemQuantity;
    private String customerName;
    private boolean isCollected;
    private String cardType;
    private double totalPrice;
    
    // Constructor
    public CurrentOrder(String itemName, String itemType, double itemPrice, int itemQuantity, String customerName, boolean isCollected, String cardType) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.customerName = customerName;
        this.isCollected = isCollected;
        this.cardType = cardType;
        this.totalPrice = totalPrice;
    }
    public double getTotalPrice(){
        return totalPrice;
    }

    // Getters
    public String getItemName() {
        return itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public String getCardType() {
        return cardType;
    }

    // Setters
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    // Method to display item details
    public String displayItemDetails() {
        return "Item Name: " + itemName
                + "\nItem Type: " + itemType
                + "\nItem Price: $" + itemPrice
                + "\nItem Quantity: " + itemQuantity
                + "\nCustomer Name: " + customerName
                + "\nIs Collected: " + (isCollected ? "Yes" : "No")
                + "\nCard Type: " + cardType;
    }

}
