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
public class GiftCartListBean {

    ArrayList<GiftCartBean> list = new ArrayList();

    public GiftCartListBean(GiftCartBean it) {

        list.add(it);
    }

    public GiftCartListBean() {
    }

    public void addList(GiftCartBean it) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getGb().getgId().equals(it.getGb().getgId())) {
                list.get(i).setQty(list.get(i).getQty() + it.getQty());
                return;
            }
        }
        list.add(it);
    }

    public boolean deleteProduct(String gId) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getGb().getgId().equals(gId)) {
                list.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateProduct(String gId, int qty) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getGb().getgId().equals(gId)) {
                list.get(i).setQty(qty);
                return true;
            }
        }
        return false;
    }

    public ArrayList<GiftCartBean> getList() {
        return list;
    }
}
