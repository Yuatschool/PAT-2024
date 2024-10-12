/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Objects.CurrentOrder;
import Objects.Member;
import Objects.Product;
import Objects.SaleHistory;
import Objects.StockItem;
import UI.UI;
import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private ArrayList<SaleHistory> saleHist = new ArrayList<>();
    
    public SaleHistoryManager() throws SQLException {
        try {
            String query = "SELECT * FROM stocklist.saleHistory;";
            ResultSet rs = DB.query(query);
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while (rs.next()) {
                String dateStr = rs.getString("membersPeriod");
               
                
                
                String itemName = rs.getString("itemName");
                String itemType = rs.getString("itemType");
                double itemPrice = rs.getDouble("itemPrice");
                LocalDate saleDate = LocalDate.parse(dateStr, formatter);
                String customerName = rs.getString("customerName");
                boolean isCollected = rs.getBoolean("isCollected");
                double totalCost = rs.getDouble("totalCost");

                
              saleHist.add(new SaleHistory(itemName, itemType, itemPrice, saleDate, customerName, isCollected, totalCost));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MemberManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int addSale(String customerName, boolean isCollected, double totalCost) {
    // Insert sale into saleHistory table
    String saleHistoryQuery = "INSERT INTO stocklist.saleHistory(customerName, isCollected, totalCost) "
                + "VALUES('" + customerName + "', " + isCollected + ", " + totalCost + ")";
    
    System.out.println(saleHistoryQuery);
    
    int saleId = -1; // To store the generated sale ID
    
    try {
        // Insert the sale and retrieve the generated saleId
        DB.update(saleHistoryQuery);
        
       
        saleId = saleHist.getsaleId(); // Ensure your DB helper supports retrieving the last inserted key
        System.out.println("Sale added with saleId: " + saleId);
        
    } catch (SQLException ex) {
        Logger.getLogger(SaleHistoryManager.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return saleId; // Return the auto-generated saleId for linking products
}

public void addProductToSale(int saleId, String itemName, String itemType, double itemPrice) {
    // Insert the product into productTable with the saleId
    String productQuery = "INSERT INTO stocklist.productTable(itemName, itemType, itemPrice, saleId) "
                + "VALUES('" + itemName + "', '" + itemType + "', " + itemPrice + ", " + saleId + ")";
    
    System.out.println(productQuery);
    
    try {
        // Insert the product into the productTable
        DB.update(productQuery);
        System.out.println("Product added to saleId: " + saleId);
        
    } catch (SQLException ex) {
        Logger.getLogger(SaleHistoryManager.class.getName()).log(Level.SEVERE, null, ex);
    }
}

  public void loadProductsForSale(int saleId) {
    String query = "SELECT * FROM stocklist.productTable WHERE saleId = " + saleId;
        try {
            DB.update(query);
        } catch (SQLException ex) {
            Logger.getLogger(SaleHistoryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
     public List<Product> loadProductsForSale2(int saleId) {
    List<Product> products = new ArrayList<>(); // List to hold the products related to the sale

    // SQL query to load products for the selected saleId
    String query = "SELECT * FROM stocklist.productTable WHERE saleId = " + saleId;

    try {
        ResultSet rs = DB.query(query); // Assume DB.query() executes the query and returns the result set

        // Loop through the result set and create Product objects
        while (rs.next()) {
            String itemName = rs.getString("itemName");
            String itemType = rs.getString("itemType");
            double itemPrice = rs.getDouble("itemPrice");

            // Create a Product object and add it to the list
            Product product = new Product(itemName, itemType, itemPrice);
            products.add(product);
        }

    } catch (SQLException ex) {
        // Handle SQL exceptions and log the error
        Logger.getLogger(SaleHistoryManager.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Error loading products for saleId " + saleId + ": " + ex.getMessage());
    }

    return products; // Return the list of products for the sale
}

// Member verification for points system
    public void memberVerifyer(String customerName, int totalPoints) {
        for (Member member : member) {
            if (customerName.equals(member.getName())) {
                int membershipDuration = member.getMembershipDuration();
                if (membershipDuration > 2) {
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
        System.out.println("Customer " + customerName + " is not a member.");
    }

// Get current order column names
    public String[] getCurrentOrderColumnNames() {//this is supposed to be for sealehistory table,this data comes after the product data below in the db
        String[] output = new String[4];

        output[0] = "customerName";
        output[1] = "isCollected";
        output[2] = "cardType";
        output[3] = "totalCost";
        return output;
    }

    public String[] getCurrentColumnNames() {//for product table ,also in the db this data come first
        String[] output = new String[4];

        output[0] = "itemName";
        output[1] = "itemType";
        output[2] = "itemPrice";
        output[3] = "itemQuantity";
        return output;
    }

// Delete order by itemId
    public void deleteOrder(int itemId) {
        String query = "DELETE FROM stocklist.currentOrder WHERE currentItemID = " + itemId + ";";
        try {
            DB.update(query);
        } catch (SQLException ex) {
            Logger.getLogger(PlaceOrderManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// Add new item to the order
    public void addItem(String itemName, String itemType, double itemPrice, int itemQuantity, String customerName, boolean isCollected, String cardType) throws SQLException {
        String query = "INSERT INTO stocklist.currentOrder(itemName,itemType,itemPrice,itemQuantity,customerName,isCollected,cardType) "
                + "VALUES('" + itemName + "','" + itemType + "','" + itemPrice + "','" + itemQuantity + "','" + customerName + "','" + isCollected + "','" + cardType + "');";
        DB.update(query);
    }

// Delete item from order
    public void deleteItem(int id) throws SQLException {
        String query = "DELETE FROM stocklist.currentOrder WHERE currentItemID = " + id + ";";
        DB.update(query);
    }
}
