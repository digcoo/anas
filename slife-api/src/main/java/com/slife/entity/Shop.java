package com.slife.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.slife.base.entity.ApiEntity;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tod
 * @date 2018/1/2
 * <p>
 * Email will_tao@126.com
 * <p>
 * Describe: shop实体映射
 */
@TableName("digcoo_anas_shop")
public class Shop extends ApiEntity<Shop> {

    private static final long serialVersionUID = 1L;

    private Long userId;

    /**
     * 商家名称
     */
    private String name;

    /**
     * 背景墙图片，多个用json串格式
     */
    private String picture;

    /**
     * 商家logo图片
     */
    private String logo;

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
    private Long businessId;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 经度
     */
    private Double lng;

    /**
     * geo编码
     */
    private String geohash;

    /**
     * 粉丝数(关注数)
     */
    private Integer followNum;

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
     * 0:冻结,1:正常,
     */
    private Byte status;

    /**
     * 店铺类型
     */
    private String shopType;

    /**
     * 购物中心
     */
    private long mallId;

    /**
     * 楼层
     */
    private String floor;

    /**
     * 门牌号
     */
    private String room;

    /**
     * 审核状态
     */
    private int  auditState;



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
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

    public String getGeohash() {
        return geohash;
    }

    public void setGeohash(String geohash) {
        this.geohash = geohash;
    }

    public Integer getFollowNum() {
        return followNum;
    }

    public void setFollowNum(Integer followNum) {
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }


    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public long getMallId() {
        return mallId;
    }

    public void setMallId(long mallId) {
        this.mallId = mallId;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getAuditState() {
        return auditState;
    }

    public void setAuditState(int auditState) {
        this.auditState = auditState;
    }
}
