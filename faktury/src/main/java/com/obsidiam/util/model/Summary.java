package com.obsidiam.util.model;

import java.util.ArrayList;

/**
 * Summary model class.
 */
public class Summary {
    private int id = 0;
    private User seller = new User();
    private User customer = new User();

    private ArrayList<SummaryElement> elements = new ArrayList<>();

    public void addElement(SummaryElement element){
        elements.add(element);
    }

    public int getAmountOfItems(){
        return elements.size();
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public ArrayList<SummaryElement> getElements(){
        return this.elements;
    }

    public String toString(){
        return String.format("%s %-20s", customer.toString(), seller.toString());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
