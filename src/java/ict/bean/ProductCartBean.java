/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

import java.util.ArrayList;

/**
 *
 * @author test
 */
public class ProductCartBean {

    public ProductBean getPb() {
        return pb;
    }

    public int getQty() {
        return qty;
    }

    public void setPb(ProductBean pb) {
        this.pb = pb;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public ProductCartBean() {
    }

    public ProductCartBean(ProductBean pb, int qty) {
        this.pb = pb;
        this.qty = qty;
    }


    ProductBean pb;
    int qty;

}
