/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

import java.util.ArrayList;

/**
 *
 * @author a1
 */
public class ProductBean {

    public ProductBean() {
    }

    private String pId;
    private String pName;
    private String description;
    private String mId;
    private String cId;
    private double price;
    private int stockQty;
    private String productPhoto;



    public ProductBean(String pId, String pName, String description, String mId, String cId, double price, int stockQty, String productPhoto) {
        this.pId = pId;
        this.pName = pName;
        this.description = description;
        this.mId = mId;
        this.cId = cId;
        this.price = price;
        this.stockQty = stockQty;
        this.productPhoto = productPhoto;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public String getProductPhoto() {
        return productPhoto;
    }

    public void setProductPhoto(String productPhoto) {
        this.productPhoto = productPhoto;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQty() {
        return stockQty;
    }

    public void setStockQty(int stockQty) {
        this.stockQty = stockQty;
    }

}
