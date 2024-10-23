/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import DataTypes.Member;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author User-Pc
 */
public class MemberManager {

    // A list to store Member objects
    private ArrayList<Member> members = new ArrayList<>();

    // Constructor that fetches data from the database and populates the members list
    public MemberManager() throws SQLException {
        try {
            // SQL query to select all members from the database
            String query = "SELECT * FROM stocklist.members;";
            ResultSet rs = DB.query(query); // Execute the query

            // Formatter for parsing date strings in 'yyyy-MM-dd' format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Iterate through the result set to extract member data
            while (rs.next()) {
                try {
                    // Retrieve data from the current row in the result set
                    String dateStr = rs.getString("membersPeriod");  // Date as string

                    int id = rs.getInt("memberID");  // Member ID
                    String membersName = rs.getString("membersname");  // Member name
                    LocalDate memberstartDate = LocalDate.parse(dateStr, formatter);  // Convert string date to LocalDate
                    String membersPackage = rs.getString("membersPackage");  // Member package

                    // Add new Member object to the members list
                    members.add(new Member(id, membersName, memberstartDate, membersPackage));

                } catch (DateTimeParseException ex) {
                    // Handle invalid date formats for individual members
                    Logger.getLogger(MemberManager.class.getName()).log(Level.SEVERE, "Invalid date format for memberID: " + rs.getInt("memberID"), ex);
                    JOptionPane.showMessageDialog(null, "Invalid date format for memberID: " + rs.getInt("memberID") + ". Skipping entry.");
                }
            }
        } catch (SQLException ex) {
            // Handle SQL exceptions that may occur while fetching data
            Logger.getLogger(MemberManager.class.getName()).log(Level.SEVERE, "Error loading members from database", ex);
            JOptionPane.showMessageDialog(null, "Error loading members from the database: " + ex.getMessage());
        }
    }

    // Method to return the list of Member objects
    public ArrayList<Member> getMembers() {
        return members;
    }

    // Method to add a new member to the database
    public void addMember(String memberName, LocalDate startDate, String memberPackage) throws SQLException {
        // SQL query to insert a new member into the members table
        String query = "INSERT INTO stocklist.members (membersname, membersPeriod, membersPackage) "
                + "VALUES ('" + memberName + "', '" + startDate + "', '" + memberPackage + "');";

        try {
            // Execute the update query to insert the new member
            DB.update(query);
        } catch (SQLException ex) {
            // Handle SQL exceptions that may occur while adding a member
            Logger.getLogger(MemberManager.class.getName()).log(Level.SEVERE, "Error adding member: " + memberName, ex);
            JOptionPane.showMessageDialog(null, "Error adding member: " + memberName + ": " + ex.getMessage());
        }
    }

 // Method to get the column names for the members table


    public String[] getMemberTableColumnNames() {
        // Define the column names and return them as an array
        String[] output = new String[4];
        output[0] = "memberID";
        output[1] = "memberName";
        output[2] = "membersPeriod";  // Assuming this is the start date (membersPeriod)
        output[3] = "membersPackage";
        return output;
    }

// Method to update a member's name in the database
    public void updateMembers(int memberID, String memberName) throws SQLException {
        // SQL query to update the member's name based on their ID
        String query = "UPDATE stocklist.members SET membersname = '" + memberName + "' WHERE memberID = " + memberID + ";";
        try {
            // Execute the update query to modify the member's data
            DB.update(query);
        } catch (SQLException ex) {
            // Handle SQL exceptions that may occur while updating a member
            Logger.getLogger(MemberManager.class.getName()).log(Level.SEVERE, "Error updating member with ID: " + memberID, ex);
            JOptionPane.showMessageDialog(null, "Error updating member with ID: " + memberID + ": " + ex.getMessage());
        }
    }

// Method to get member data as a 2D array for table display
    public String[][] getMemberDataAsTable() {
        // Create a 2D array with rows equal to the number of members and 4 columns
        String[][] output = new String[members.size()][4];
        for (int i = 0; i < members.size(); i++) {
            // Populate the array with member data
            output[i][0] = String.valueOf(members.get(i).getmemberId());  // Convert member ID to string
            output[i][1] = members.get(i).getName();  // Get member name

            // Format and store the start date in the correct format
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            output[i][2] = members.get(i).getStartDate().format(dateFormatter);  // Store formatted start date

            output[i][3] = members.get(i).getPackage();  // Get member package
        }
        return output;  // Return the populated array
    }

    // Method to delete a member from the database based on their ID
    public void deleteMember(int id) throws SQLException {
        // SQL query to delete a member from the database
        String query = "DELETE FROM stocklist.members WHERE memberID = " + id + ";";
        try {
            // Execute the delete query to remove the member
            DB.update(query);
        } catch (SQLException ex) {
            // Handle SQL exceptions that may occur while deleting a member
            Logger.getLogger(MemberManager.class.getName()).log(Level.SEVERE, "Error deleting member with ID: " + id, ex);
            JOptionPane.showMessageDialog(null, "Error deleting member with ID: " + id + ": " + ex.getMessage());
        }
    }
}
