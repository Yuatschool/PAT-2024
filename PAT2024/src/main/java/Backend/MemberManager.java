/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Objects.Member;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User-Pc
 */
public class MemberManager {

    private ArrayList<Member> members = new ArrayList<>();

    // Constructor that fetches data from the database
    public MemberManager() throws SQLException {
        try {
            String query = "SELECT * FROM stocklist.stock;";
            ResultSet rs = DB.query(query);
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while (rs.next()) {
                String dateStr = rs.getString("membersPeriod");
               
                
                int id = rs.getInt("memberID");
                String membersName = rs.getString("membersname");
                LocalDate memberstartDate = LocalDate.parse(dateStr, formatter);
                String membersackage = rs.getString("membersPackage");

                // Add new StockItem object to the list
                //DateTimeFormatter ("dd/MM/yyyy")
                //LocalDate.parse(startDate, dtf);
              members.add(new Member(id, membersName, memberstartDate, membersackage));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MemberManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to get the list of StockItems
    public ArrayList<Member> getMembers() {
        return members;
    }

    public void addMember(String memberName, LocalDate startDate, String memberPackage) throws SQLException {
        String query = "INSERT INTO stocklist.members(member) "
                + "Values('" + memberName + "','" + startDate + "'," + memberPackage + "';)";

        System.out.println(query);
        DB.update(query);

    }

    public String[] getMemberTableColumnNames() {
        String[] output = new String[6];
        output[0] = "memberID";
        output[1] = "memberName";
        output[2] = "membersPeriod";
        output[3] = "membersPackage";

        return output;
    }

    public void updateMembers(int memberID, String memberName) throws SQLException {//finish this when done with everything

        //add more parameters
        String query = "UPDATE stockList.members SET name=" + memberName + " WHERE memberID = " + memberID + ";";

        // Prepare the SQL statement
        DB.update(query);//

    }

    public String[][] getMemberDataAsTable() {
        String[][] output = new String[members.size()][3];
        for (int i = 0; i < members.size(); i++) {
            output[i][0] = members.get(i).getId() + "";
            output[i][1] = members.get(i).getName();
            output[i][2] = members.get(i).getStartDate() + "";
            output[i][3] = members.get(i).getPackage() + "";

        }

        return output;
    }

    public void deleteMember(int id) throws SQLException {
        String query = "DELETE FROM stocklist.members WHERE memberID = " + id + ";";
        DB.update(query);
    }
}
