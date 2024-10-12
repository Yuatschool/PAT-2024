/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author User-Pc
 */
public class Special {

    private int specialId;
    private String specialName;
    private double specialPrice;
    private String specialDay;
    private String specialType;

    // Constructor to initialize the fields
    public Special(int specialId, String specialName, double specialPrice, String specialType, String specialDay) {
        this.specialId = specialId;
        this.specialName = specialName;
        this.specialPrice = specialPrice;
        this.specialType = specialType;
        this.specialDay = specialDay;
    }

    public String getSpecialType() {
        return specialType;
    }

    public void setSpecialType(String specialType) {
        this.specialType = specialType;
    }

    // Getters
    public int getSpecialId() {
        return specialId;
    }

    public String getSpecialName() {
        return specialName;
    }

    public double getSpecialPrice() {
        return specialPrice;
    }

    public String getSpecialDay() {
        return specialDay;
    }

    // Setters
    public void setSpecialId(int specialId) {
        this.specialId = specialId;
    }

    public void setSpecialName(String specialName) {
        this.specialName = specialName;
    }

    public void setSpecialPrice(double specialPrice) {
        this.specialPrice = specialPrice;
    }

    public void setSpecialDay(String specialDay) {
        this.specialDay = specialDay;
    }

}
