/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

/**
 *
 * @author test
 */
public class UserBean {
 

    private String uId;
    private String uName;
    private String uPasswd;
    private String uType;
    private String email;
    private int creditAmount;
    private int bounspt;
    private int available;
    private String deliveryAddress;
    private int a_f_t;
    private int transactions;
    private double total_amount;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPasswd() {
        return uPasswd;
    }

    public void setuPasswd(String uPasswd) {
        this.uPasswd = uPasswd;
    }

    public String getuType() {
        return uType;
    }

    public void setuType(String uType) {
        this.uType = uType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(int creditAmount) {
        this.creditAmount = creditAmount;
    }

    public int getBounspt() {
        return bounspt;
    }

    public void setBounspt(int bounspt) {
        this.bounspt = bounspt;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public int getA_f_t() {
        return a_f_t;
    }

    public void setA_f_t(int a_f_t) {
        this.a_f_t = a_f_t;
    }

    public int getTransactions() {
        return transactions;
    }

    public void setTransactions(int transactions) {
        this.transactions = transactions;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public UserBean(String uId, String uName, String uPasswd, String uType, String email, int creditAmount, int bounspt, int available, String deliveryAddress, int a_f_t, int transactions, double total_amount) {
        this.uId = uId;
        this.uName = uName;
        this.uPasswd = uPasswd;
        this.uType = uType;
        this.email = email;
        this.creditAmount = creditAmount;
        this.bounspt = bounspt;
        this.available = available;
        this.deliveryAddress = deliveryAddress;
        this.a_f_t = a_f_t;
        this.transactions = transactions;
        this.total_amount = total_amount;
    }
}
