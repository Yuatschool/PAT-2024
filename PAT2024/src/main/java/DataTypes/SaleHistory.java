/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataTypes;

import Backend.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author User-Pc
 */
public class SaleHistory {

    
    private int saleId;
    private LocalDate saleDate;
    private String customerName;
    private boolean isCollected;
    private double totalCost;

    // Constructor
    public SaleHistory(int saleId, LocalDate saleDate, String customerName, boolean isCollected, double totalCost) {
        this.saleId = saleId;  // Adding assignment for saleId
        this.saleDate = saleDate;
        this.customerName = customerName;
        this.isCollected = isCollected;
        this.totalCost = totalCost;
    }

    // Getters and Setters
    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public boolean getIsCollected() {
        return isCollected;
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
