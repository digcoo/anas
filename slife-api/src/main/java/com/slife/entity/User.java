package com.slife.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.slife.base.entity.ApiEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by cq on 18-1-20.
 */
@TableName("digcoo_anas_user")
public class User extends ApiEntity<User> {
    private String openId;

    private int type;

    private String wxHeadImg = StringUtils.EMPTY;

    private String headImg = StringUtils.EMPTY;

    private int gender;

    private String mobile = StringUtils.EMPTY;

    private String nick = StringUtils.EMPTY;

    private String wxNick = StringUtils.EMPTY;

    private String country = StringUtils.EMPTY;

    private String province = StringUtils.EMPTY;

    private String city = StringUtils.EMPTY;

    private Date followTime;

    private String qrTicket = StringUtils.EMPTY;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getWxHeadImg() {
        return wxHeadImg;
    }

    public void setWxHeadImg(String wxHeadImg) {
        this.wxHeadImg = wxHeadImg;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getWxNick() {
        return wxNick;
    }

    public void setWxNick(String wxNick) {
        this.wxNick = wxNick;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }

    public String getQrTicket() {
        return qrTicket;
    }

    public void setQrTicket(String qrTicket) {
        this.qrTicket = qrTicket;
    }
}
