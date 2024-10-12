/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.sql.SQLException;

/**
 *
 * @author User-Pc
 */
public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DB.connect();
        StockManager sm = new StockManager();
        
        sm.addItem("Coke", "Drink", 15, 20, 3);
        
        //sm.deleteItem(1);
    }
}
