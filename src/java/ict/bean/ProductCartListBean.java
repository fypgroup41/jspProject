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
public class ProductCartListBean {

    ArrayList<ProductCartBean> list = new ArrayList();

    public ProductCartListBean(ProductCartBean it) {

        list.add(it);
    }

    public ProductCartListBean() {
    }

    public void addList(ProductCartBean it) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPb().getpId().equals(it.getPb().getpId())) {
                list.get(i).setQty(list.get(i).getQty() + it.getQty());
                return;
            }
        }
        list.add(it);
    }

    public boolean deleteProduct(String pid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPb().getpId().equals(pid)) {
                list.remove(i);
                return true;
            }
        }
                return false;
    }

        public boolean updateProduct(String pid,int qty) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPb().getpId().equals(pid)) {
                list.get(i).setQty(qty);
                return true;
            }
        }
                return false;
    }

    
    public ArrayList<ProductCartBean> getList() {
        return list;
    }
}
