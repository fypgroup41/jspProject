/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

/**
 *
 * @author a1
 */
public class GiftBean {

    public GiftBean() {
    }

    public GiftBean(String gId, String gName, double pt, String description, String catId, int stockQty, String giftPhoto, String mId) {
        this.gId = gId;
        this.gName = gName;
        this.pt = pt;
        this.description = description;
        this.catId = catId;
        this.stockQty = stockQty;
        this.giftPhoto = giftPhoto;
        this.mId = mId;
    }

    public String getgId() {
        return gId;
    }

    public void setgId(String gId) {
        this.gId = gId;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public double getPt() {
        return pt;
    }

    public void setPt(double pt) {
        this.pt = pt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public int getStockQty() {
        return stockQty;
    }

    public void setStockQty(int stockQty) {
        this.stockQty = stockQty;
    }

    public String getGiftPhoto() {
        return giftPhoto;
    }

    public void setGiftPhoto(String giftPhoto) {
        this.giftPhoto = giftPhoto;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    private String gId;
    private String gName;
    private double pt;
    private String description;
    private String catId;
    private int stockQty;
    private String giftPhoto;
    private String mId;
}
