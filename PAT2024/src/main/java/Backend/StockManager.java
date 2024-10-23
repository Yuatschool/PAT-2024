/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import DataTypes.StockItem;
import static Backend.DB.query;
import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User-Pc
 */
public class StockManager {
    // Define the list to store StockItems
    private ArrayList<StockItem> items = new ArrayList<>();

    // Constructor that fetches data from the database
    public StockManager() throws SQLException {
        String query = "SELECT * FROM stocklist.stock;";
        ResultSet rs = DB.query(query);
        
        while (rs.next()) {
            int id = rs.getInt("stockID");
            String name = rs.getString("stockName");
            String type = rs.getString("stockType");
            double costMaterials = rs.getDouble("costPrice");
            double sellingPrice = rs.getDouble("sellingPrice");
            int points = rs.getInt("points");

            // Add new StockItem object to the list
            items.add(new StockItem(id, name, type, costMaterials, sellingPrice, points));
        }
    }

    // Method to get the list of StockItems
    public ArrayList<StockItem> getItems() {
        return items;
    }
        // Method to calculate the total cost of materials
    public double getTotalCostOfMaterials() {
        double totalCost = 0.0;
        
        // Iterate over the list of StockItems and sum the costMaterials
        for (StockItem item : items) {
            totalCost += item.getCost(); // Assuming getCostMaterials() exists in StockItem class
        }

        return totalCost;
    }

    public void addItem(String name, String type, double cost, double sellingPrice, int points) throws SQLException {
        String query = "INSERT INTO stocklist.stock(stockName,stockType,costPrice,sellingPrice,points ) "
                + "Values('" + name + "','" + type + "','" + cost + "','" + sellingPrice + "','" + points + "');";

        System.out.println(query);
        DB.update(query);
        
        StockItem i = new StockItem(points, name, type, cost, cost, points);
        items.add(i);

    }

    public String[] getStockTableColumnNames() {
        String[] output = new String[6];
        output[0] = "stockID";
        output[1] = "name";
        output[2] = "type";
        output[3] = "costPrice";
        output[4] = "sellingPrice";
        output[5] = "points";

        return output;
    }

   
    

    public String[][] getStockItemDataAsTable() {
        String[][] output = new String[items.size()][6];
        for (int i = 0; i < items.size(); i++) {
            output[i][0] = items.get(i).getId() + "";
            output[i][1] = items.get(i).getName();
            output[i][2] = items.get(i).getType();
            output[i][3] = items.get(i).getCost() + "";
            output[i][4] = items.get(i).getPrice() + "";
            output[i][5] = items.get(i).getPoints() + "";
        }

        return output;
    }

    public void deleteItem(int id) throws SQLException {
       // Delete the stock item from the database
    String query = "DELETE FROM stocklist.stock WHERE stockID = " + id + ";";
    DB.update(query);

    // Iterate over the list of items and find the item to delete
    StockItem itemToRemove = null;
    for (StockItem item : items) {
        if (item.getId() == id) {
            itemToRemove = item;
            break;
        }
    }

    // If the item was found, remove it from the list
    if (itemToRemove != null) {
        items.remove(itemToRemove);
        System.out.println("Stock item with ID " + id + " has been deleted from the list: " + itemToRemove.getName());
    } else {
        System.out.println("Stock item with ID " + id + " was not found in the list.");
    }
    
  
    

}
}
