package com.slife.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class DigcooAnasShop implements Serializable {
    /**
     * 用户id
     */
    private int userId;

    /**
     * 商家名称
     */
    private String name;

    /**
     * 背景墙图片，多个用json串格式
     */
    private String picture;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * 地址
     */
    private String addr;

    /**
     * 是否接收电话咨询
     */
    private Byte openMobile;

    /**
     * 所属行业
     */
    private long bisinessId;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 经度
     */
    private Double lng;

    /**
     * 粉丝数(关注数)
     */
    private int followNum;

    /**
     * 营业执照
     */
    private String businessLicense;

    /**
     * 身份证图像页
     */
    private String agentIdentifyCard;

    /**
     * 职位（如店长）
     */
    private String agentPosition;

    /**
     * 个人正面照
     */
    private String agentPortrait;

    /**
     * frozen:冻结,normal:正常,
     */
    private String status;

    private Date gmtCreated;

    private Date gmtUpdated;

    private static final long serialVersionUID = 1L;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Byte getOpenMobile() {
        return openMobile;
    }

    public void setOpenMobile(Byte openMobile) {
        this.openMobile = openMobile;
    }

    public long getBisinessId() {
        return bisinessId;
    }

    public void setBisinessId(long bisinessId) {
        this.bisinessId = bisinessId;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public int getFollowNum() {
        return followNum;
    }

    public void setFollowNum(int followNum) {
        this.followNum = followNum;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getAgentIdentifyCard() {
        return agentIdentifyCard;
    }

    public void setAgentIdentifyCard(String agentIdentifyCard) {
        this.agentIdentifyCard = agentIdentifyCard;
    }

    public String getAgentPosition() {
        return agentPosition;
    }

    public void setAgentPosition(String agentPosition) {
        this.agentPosition = agentPosition;
    }

    public String getAgentPortrait() {
        return agentPortrait;
    }

    public void setAgentPortrait(String agentPortrait) {
        this.agentPortrait = agentPortrait;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtUpdated() {
        return gmtUpdated;
    }

    public void setGmtUpdated(Date gmtUpdated) {
        this.gmtUpdated = gmtUpdated;
    }
}