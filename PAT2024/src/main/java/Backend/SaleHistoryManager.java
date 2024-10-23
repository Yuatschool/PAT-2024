/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import DataTypes.CurrentOrder;
import DataTypes.Member;
import DataTypes.ProductTable;
import DataTypes.SaleHistory;
import DataTypes.StockItem;
import UI.UI;
import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User-Pc
 */
public class SaleHistoryManager {
   
   // List to hold sale history objects

    private ArrayList<SaleHistory> saleHist = new ArrayList<>();
    private ArrayList<ProductTable> products = new ArrayList<>();

  // Constructor that loads sale history from the database upon creation of the object
public SaleHistoryManager() throws SQLException {
    this.members = new ArrayList<>();
    try {
        // SQL query to select all records from the saleHistory table
        String query = "SELECT * FROM stocklist.saleHistorytable;";
        ResultSet rs = DB.query(query);  // Execute the query and get the result set

        // Date formatter to convert date strings into LocalDate objects
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Loop through the result set and add each sale history entry to the saleHist list
        while (rs.next()) {
            // Extract data from the result set for each sale
            String dateStr = rs.getString("saleDate");  // Changed from "membersPeriod" to "saleDate"
            int saleId = rs.getInt("saleId");
            LocalDate saleDate = LocalDate.parse(dateStr, formatter);  // Convert date string to LocalDate
            String customerName = rs.getString("customerName");
            boolean isCollected = rs.getBoolean("isCollected");
            double totalCost = rs.getDouble("totalPrice");

            // Add a new SaleHistory object to the saleHist list with the extracted data
            saleHist.add(new SaleHistory(saleId, saleDate, customerName, isCollected, totalCost));
        }
    } catch (SQLException ex) {
        Logger.getLogger(MemberManager.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
public ArrayList<SaleHistory> getSales(){
        return saleHist;
    }

// Method to add a sale
public void addSale(LocalDate saleDate, String customerName, boolean isCollected, double totalPrice, String cardType) {
    // Query to insert a new sale into the saleHistory table
    String saleHistoryQuery = "INSERT INTO stocklist.saleHistory(customerName, isCollected, totalCost, saleDate, cardType) "
            + "VALUES('" + customerName + "', '" + isCollected + "', '" + totalPrice + "', '" + saleDate + "', '" + cardType + "')";
    
    System.out.println(saleHistoryQuery);  // Print the query for debugging purposes

    // Execute the query
    try {
        DB.update(saleHistoryQuery); // Assuming DB.update executes the INSERT query
    } catch (SQLException ex) {
        Logger.getLogger(SaleHistoryManager.class.getName()).log(Level.SEVERE, null, ex);
    }
}

// Method to add a product to a specific sale by linking the product to a saleId
public void addProductToSale(String itemName, String itemType, double itemPrice) {
    // SQL query to insert a product into the productTable and associate it with a saleId
    String productQuery = "INSERT INTO stocklist.productTable(itemName, itemType, itemPrice, saleId) "
            + "VALUES('" + itemName + "', '" + itemType + "', '" + itemPrice + "', '"  + "')";
    
    // Output the query string to the console for debugging
    System.out.println(productQuery);

    // Execute the query
    try {
        DB.update(productQuery); // Assuming DB.update executes the INSERT query
    } catch (SQLException ex) {
        Logger.getLogger(SaleHistoryManager.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
    
     public ArrayList<SaleHistory> loadSaleHistoryTable() {
       
// SQL query to select sales from the saleHistory table
String query = "SELECT * FROM stocklist.salehistorytable;";

try {
    // Execute the query to retrieve sale history details
    ResultSet rs = DB.query(query);  // DB.query() executes the SELECT query and returns a result set

    // Loop through the result set and create SaleHistory objects
    while (rs.next()) {
        try {
            int saleId = rs.getInt("saleId");
            String customerName = rs.getString("customerName");
            boolean isCollected = rs.getBoolean("isCollected");
            LocalDate saleDate = rs.getDate("saleDate").toLocalDate();  // Convert SQL Date to LocalDate
            String cardType = rs.getString("cardType");
            double totalPrice = rs.getDouble("totalPrice");

            // Create a SaleHistory object using the retrieved data
            SaleHistory sale = new SaleHistory(saleId, saleDate, customerName, isCollected, totalPrice);
            saleHist.add(sale);  // Add the sale to the list
        } catch (SQLException ex) {
            Logger.getLogger(SaleHistoryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

} catch (SQLException ex) {
    Logger.getLogger(SaleHistoryManager.class.getName()).log(Level.SEVERE, null, ex);
}

// Return the list of sales
return saleHist;
     }
    // Method to load all products associated with a specific saleId (used for displaying related products)
    public ArrayList<ProductTable> loadProductsForSale2(int saleId) {
        // List to hold the products associated with the given saleId
        ArrayList<ProductTable> products = new ArrayList<>();

        // SQL query to select products related to a specific saleId
        String query = "SELECT * FROM stocklist.producttable WHERE saleId = " + saleId;

        try {
            // Execute the query to retrieve product details
            ResultSet rs = DB.query(query);  // DB.query() executes the SELECT query and returns a result set

            // Loop through the result set and create Product objects
            while (rs.next()) {
                int itemId = rs.getInt("itemId");
                String itemName = rs.getString("itemName");
                String itemType = rs.getString("itemType");
                double itemPrice = rs.getDouble("itemPrice");

                // Create a new ProductTable object and add it to the products list
                ProductTable product = new ProductTable(itemId, itemName, itemType, itemPrice);
                products.add(product);  // Add product to list
            }

        } catch (SQLException ex) {
            // Handle SQL exceptions and log the error for debugging
            Logger.getLogger(SaleHistoryManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error loading products for saleId " + saleId + ": " + ex.getMessage());
        }

        // Return the list of products associated with the sale
        return products;
    }

    public String[][] getSaleHistDataAsTable() {
        String[][] output = new String[saleHist.size()][4];
        for (int i = 0; i < saleHist.size(); i++) {
            output[i][0] = saleHist.get(i).getSaleId() + "";
            output[i][1] = saleHist.get(i).getSaleDate() + "";

            output[i][2] = saleHist.get(i).getCustomerName();
            output[i][3] = saleHist.get(i).getIsCollected() + "";

        }

        return output;
    }

    public String[][] getProductDataAsTable() {
        String[][] output = new String[products.size()][5];
        for (int i = 0; i < products.size(); i++) {
            output[i][0] = products.get(i).getItemId() + "";
            output[i][1] = products.get(i).getItemName();

            output[i][2] = products.get(i).getItemType();
            output[i][3] = products.get(i).getItemPrice()+"";
             output[i][4] = products.get(i).getItemQuantity()+"";
            

        }

        return output;
    }

    private ArrayList<Member> members;

    // Method to verify if a customer is a member and check for discounts based on points
    public void memberVerifyer(String customerName, int totalPoints) {
        // Loop through all members and check if the customer is a member
        for (Member member : members) {
            if (customerName.equals(member.getName())) {  // If the customer is found in the member list
                int membershipDuration = member.getMembershipDuration();  // Get how long the member has been with us
                if (membershipDuration > 2) {
                    // If the customer has been a member for more than 2 years, check points for a discount
                    System.out.println("Customer " + customerName + " has been a member for over 2 years.");
                    if (totalPoints >= 100) {
                        System.out.println("Customer has enough points for a discount.");
                    } else {
                        System.out.println("Customer has insufficient points for a discount.");
                    }
                } else {
                    System.out.println("Customer " + customerName + " has been a member for less than 2 years.");
                }
                return;
            }
        }
        // If the customer is not found in the member list, display a message
        System.out.println("Customer " + customerName + " is not a member.");
    }

    // Method to get the column names for the sale history table (for displaying data in the UI)
    public String[] getSaleColumnNames() {
        // Array holding the column names for the saleHistory table
        String[] output = new String[4];
        output[0] = "customerName";
        output[1] = "isCollected";
        output[2] = "cardType";
        output[3] = "totalCost";
        return output;
    }

    // Method to get the column names for the product table (for displaying data in the UI)
    public String[] getProductColumnNames() {
        // Array holding the column names for the productTable
        String[] output = new String[4];
        output[0] = "itemName";
        output[1] = "itemType";
        output[2] = "itemPrice";
        output[3] = "itemQuantity";
        return output;
    }

    public void deleteItem(int id) {
        String query = "DELETE FROM stocklist.salehisttable WHERE currentItemID = " + id + ";";
        try {
            DB.update(query);
        } catch (SQLException ex) {
            Logger.getLogger(SaleHistoryManager.class.getName()).log(Level.SEVERE, null, ex);
        }


 
}
}
