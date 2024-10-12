/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Objects.Special;
import Objects.StockItem;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User-Pc
 */
public class SpecialManager {
     private ArrayList<Special> specials = new ArrayList<>();
       public SpecialManager() throws SQLException {
        String query = "SELECT * FROM stocklist.specials;";
        ResultSet rs = DB.query(query);
        
        while (rs.next()) {
            int specialId = rs.getInt("specialID");
            String specialName = rs.getString("specialName");
           
            double specialPrice = rs.getDouble("specialPrice");
            String specialType = rs.getString("specialType");
            String specialDay = rs.getString("specialDay");

           
            specials.add(new Special(specialId, specialName,specialPrice,specialType,specialDay));
        }
        
        
    }
        public ArrayList<Special> getSpecials() {
        return specials;
        }
   
        public void addSpecial (String specialName, double specialPrice,String specialType, String specialDay){
               String query = "INSERT INTO stocklist.special(specialID,specialName,specialPrice,specialDay ) "
                + "Values('" + specialName + "','" + specialPrice + "',"+ specialType+"','" +specialDay  + ");";

        System.out.println(query);
         try {
             DB.update(query);
         } catch (SQLException ex) {
             Logger.getLogger(SpecialManager.class.getName()).log(Level.SEVERE, null, ex);
         }

        }
      
       public void deleteItem(int id) throws SQLException {
        String query = "DELETE FROM stocklist.special WHERE stockID = " + id + ";";
        DB.update(query);
    }
        public String[][] getSpecialDataAsTable() {
        String[][] output = new String[specials.size()][3];
        for (int i = 0; i < specials.size(); i++) {
            output[i][0] = specials.get(i).getSpecialId() + "";
            output[i][1] = specials.get(i).getSpecialName();
            output[i][2] = specials.get(i).getSpecialPrice()+"";
             output[i][3] = specials.get(i).getSpecialType();
            output[i][4] = specials.get(i).getSpecialDay() + "";
         
        }

        return output;
    }
          public String[] getSpecialColumnNames() {
        String[] output = new String[3];
        output[0] = "specialID";
        output[1] = "specialName";
        output[2] = "specialPrice";
          output[3] = "specialType";
        output[4] = "specialDay";
     
        return output;
    }
          
        
}
