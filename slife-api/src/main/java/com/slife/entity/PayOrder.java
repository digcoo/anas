package com.slife.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.slife.base.entity.ApiEntity;

import java.io.Serializable;
import java.util.Date;

@TableName("digcoo_anas_pay_order")
public class PayOrder extends ApiEntity<PayOrder> {
    @Override
    protected Serializable pkVal() {
        return id;
    }

    private static final long serialVersionUID = 1L;

    private String  itemName;

    private  int quantity;

    private  int  price;

    private  long total;

    private int payStatus;

    private String callback;

    private String attach;

    private  String prepayId;

    private long  userId;

    private String openId;

    private Date expireDate;

    private Date payDate;

    private String transactionId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
