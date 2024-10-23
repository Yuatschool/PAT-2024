/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author User-Pc
 */
public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DB.connect();
        StockManager sm = new StockManager();
        
        sm.addItem("", "", 0, 0, 0);
        
        //sm.deleteItem(1);
    }
}
