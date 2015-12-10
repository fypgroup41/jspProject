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
public class OrderBean {

    public OrderBean() {
    }

    private String oId;
    private String uId;
    private String oMode;
    private String oStatus;

    public String getoDate() {
        return oDate;
    }

    public void setoDate(String oDate) {
        this.oDate = oDate;
    }
    private String oDate;

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    private String deliveryDate;

    public OrderBean(String oId, String uId, String oMode, String oStatus,String oDate,String deliveryDate) {
        this.oId = oId;
        this.uId = uId;
        this.oMode = oMode;
        this.oStatus = oStatus;
        this.oDate=oDate;
        this.deliveryDate=deliveryDate;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getoMode() {
        return oMode;
    }

    public void setoMode(String oMode) {
        this.oMode = oMode;
    }

    public String getoStatus() {
        return oStatus;
    }

    public void setoStatus(String oStatus) {
        this.oStatus = oStatus;
    }

}
