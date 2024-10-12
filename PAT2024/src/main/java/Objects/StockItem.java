/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import java.util.logging.Logger;

/**
 *
 * @author User-Pc
 */
public class StockItem {
    private int id ;
    private String name;
    private String type;
    private double cost;
    private double price;
    private int points;

    public StockItem(int id, String name, String type, double cost, double price, int points) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.price = price;
        this.points = points;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
       this.type = type;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    public String toString(){
        return "Item: " + name;
    }
    
    
}
