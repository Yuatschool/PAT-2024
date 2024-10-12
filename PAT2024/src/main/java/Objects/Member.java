/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author User-Pc
 */
public class Member {

    private int id;
    private String name;
    private LocalDate startDate;
    private String memberPackage;

    public Member(int id, String membersName, LocalDate startDate, String memberPackage) {
        this.id = id;
        this.name = membersName;
        this.startDate = startDate;//localDate?
        this.memberPackage = memberPackage;
    }

    public int getMembershipDuration() {
        LocalDate currentDate = LocalDate.now(); // Get current date
        return Period.between(this.startDate, currentDate).getYears(); // Calculate the difference in years
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

    public void setName(String members) {
        this.name = members;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getPackage() {
        return memberPackage;
    }

    public void setPackage(String memberPackage) {
        this.memberPackage = memberPackage;
    }

}
