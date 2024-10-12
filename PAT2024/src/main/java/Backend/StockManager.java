/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Objects.StockItem;
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
            String name = rs.getString("name");
            String type = rs.getString("type");
            double cost = rs.getDouble("costPrice");
            double sellingPrice = rs.getDouble("sellingPrice");
            int points = rs.getInt("points");

            // Add new StockItem object to the list
            items.add(new StockItem(id, name, type, cost, sellingPrice, points));
        }
    }

    // Method to get the list of StockItems
    public ArrayList<StockItem> getItems() {
        return items;
    }


    public void addItem(String name, String type, double cost, double sellingPrice, int points) throws SQLException {
        String query = "INSERT INTO stocklist.stock(name,type,costPrice,sellingPrice,points ) "
                + "Values('" + name + "','" + type + "','" + cost + "','" + sellingPrice + "','" + points + ");";

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
        String query = "DELETE FROM stocklist.stock WHERE stockID = " + id + ";";
        DB.update(query);
         
      
     StockItem i = new StockItem//help pls
        for (StockItem item : i) {
        if (item.getId() == id) {
           
            System.out.println("Stock item with ID " + id + " has been deleted from the list: " + item.getName());
            items.add(item); // Mark the item for removal
        }
        //for loop
        //if stock item id = id
        //delete
    }
    
  
    
}
}
