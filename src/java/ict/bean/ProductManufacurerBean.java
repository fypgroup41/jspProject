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
public class ProductManufacurerBean {

    public ArrayList<ManufacturerBean> getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ArrayList<ManufacturerBean> manufacturer) {
        this.manufacturer = manufacturer;
    }

    public ArrayList<ProductBean> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductBean> products) {
        this.products = products;
    }

    public ProductManufacurerBean() {
    }

    private ArrayList<ManufacturerBean> manufacturer = new ArrayList<ManufacturerBean>();
    private ArrayList<ProductBean> products = new ArrayList<ProductBean>();
    private ArrayList<GiftBean> gifts = new ArrayList<GiftBean>();

    public ArrayList<GiftBean> getGifts() {
        return gifts;
    }

    public void setGifts(ArrayList<GiftBean> gifts) {
        this.gifts = gifts;
    }
}
