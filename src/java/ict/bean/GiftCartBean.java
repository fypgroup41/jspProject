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
public class GiftCartBean {

    public GiftCartBean() {
    }

    public GiftCartBean(GiftBean gb, int qty) {
        this.gb = gb;
        this.qty = qty;
    }

    public GiftBean getGb() {
        return gb;
    }

    public void setGb(GiftBean gb) {
        this.gb = gb;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    GiftBean gb;
    int qty;

}
