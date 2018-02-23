package com.example.ms.foodshopping;

/**
 * Created by MS on 19-02-2018.
 */

public class Shopping {

    private Integer id;
    private String shopName;
    private Double amount;
    private Integer year;
    private Integer weekNumber;
    private String regTime;
    private Double TotalWeek;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public Double getTotalWeek() { return TotalWeek; }

    public void setTotalWeek(Double totalWeek) { TotalWeek = totalWeek; }
}
