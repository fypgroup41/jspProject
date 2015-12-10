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
public class ProductOrderBean {

    public ProductOrderBean() {
    }

    private String oId;
    private String pId;
    private String gId;
    private int qty;
    private double price;

    public ProductOrderBean(String oId, String pId, String gId, int qty, double price) {
        this.oId = oId;
        this.pId = pId;
        this.gId = gId;
        this.qty = qty;
        this.price = price;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getgId() {
        return gId;
    }

    public void setgId(String gId) {
        this.gId = gId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
