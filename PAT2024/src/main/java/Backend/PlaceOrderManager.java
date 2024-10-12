/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Objects.CurrentOrder;
import Objects.Member;

import Objects.SaleHistory;
import Objects.StockItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User-Pc
 */
public class PlaceOrderManager {

    private ArrayList<CurrentOrder> currentItems = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<StockItem> stockItems = new ArrayList<>();
    private ArrayList<SaleHistory> salesH = new ArrayList<>();

    public PlaceOrderManager() throws SQLException {
        String query = "SELECT * FROM stocklist.currentOrder;";
        ResultSet rs = DB.query(query);

        while (rs.next()) {
            String itemName = rs.getString("itemName");
            String itemType = rs.getString("itemType");
            double itemPrice = rs.getDouble("itemPrice");
            int itemQuantity = rs.getInt("itemQuantity");
            String customerName = rs.getString("customerName");
            boolean isCollected = rs.getBoolean("isCollected");
            String cardType = rs.getString("cardType");

            // Add new StockItem object to the list
            currentItems.add(new CurrentOrder(itemName, itemType, itemPrice, itemQuantity, customerName, isCollected, cardType));
        }
    }

    public ArrayList<CurrentOrder> getItems() {
        return currentItems;
    }

    public void addItem(String itemName, String itemType, double itemPrice, int itemQuantity, String customerName, boolean isCollected, String cardType) throws SQLException {
        String query = "INSERT INTO stocklist.currentOrder(itemName,itemType,itemPrice,itemQuantity,customerName,isCollected,cardType) "
                + "Values('" + itemName + "','" + itemType + "','" + itemPrice + "','" + itemQuantity + "','" + customerName + "','" + isCollected + "','" + cardType + ");";

        System.out.println(query);
        DB.update(query);

    }

    public void deleteItem(int id) throws SQLException {
        String query = "DELETE FROM stocklist.currentOrder WHERE currentItemID = " + id + ";";
        DB.update(query);
    }

    public String[][] getCurrentItemDataAsTable() {//how can i seperate the data and column names between saleHistoryTable and productTable
        String[][] output = new String[currentItems.size()][7];
        for (int i = 0; i < currentItems.size(); i++) {
            output[i][0] = currentItems.get(i).getItemName();
            output[i][1] = currentItems.get(i).getItemPrice() + "";
            output[i][2] = currentItems.get(i).getItemType();
            output[i][3] = currentItems.get(i).getItemQuantity() + "";
            output[i][4] = currentItems.get(i).getCustomerName();
            output[i][5] = currentItems.get(i).isCollected() + "";
            output[i][6] = currentItems.get(i).getCardType();

        }

        return output;
    }

    public String[] getCurrentOrderColumnNames() {
        String[] output = new String[7];

        output[0] = "itemName";
        output[1] = "itemType";
        output[2] = "itemPrice";
        output[3] = "itemQuantity";
        output[4] = "customerName";
        output[5] = "isCollected";
        output[6] = "cardType";

        return output;
    }

    public boolean memberVerifyer(String customerName) {
        // Loop through the list of members
        for (Member member : members) {

            if (customerName.equals(member.getName())) {
                // Get the membership duration in years
                int membershipDuration = member.getMembershipDuration();

                // Check if the membership is longer than 2 years//To be a official member to get points ,the member has to have joined the cafe for 2 years at least
                if (membershipDuration > 2) {
                    System.out.println("Customer " + customerName + " has been a member for over 2 years.");
                    return true;
                }

            }
            // If customer is not found in the member list
            System.out.println("Customer " + customerName + " is not a member.");
        }

        return false;

    }

    public void deleteOrder(int itemId) {
        String query = "DELETE FROM stocklist.currentOrder WHERE currentItemID = " + itemId + ";";
        try {
            DB.update(query);

        } catch (SQLException ex) {
            Logger.getLogger(PlaceOrderManager.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
