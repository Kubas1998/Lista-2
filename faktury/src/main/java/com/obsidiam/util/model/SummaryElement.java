package com.obsidiam.util.model;

/**
 * SummaryElement model class.
 */
public class SummaryElement {
    private float price,amount;
    private String element;


    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString(){
        return String.format("%s%-20s%-20s","Element: "+element+"","Amount: "+amount, "Element: "+element);
    }
}
