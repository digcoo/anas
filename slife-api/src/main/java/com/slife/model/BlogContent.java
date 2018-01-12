package com.slife.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class BlogContent implements Serializable {
    private Long id;

    /**
     * 创建人id
     */
    private Long createId;

    /**
     * 最近修改人id
     */
    private Long updateId;

    /**
     * 修改时间
     */
    private Date createDate;

    /**
     * 创建时间
     */
    private Date updateDate;

    /**
     * 标题
     */
    private String title;

    /**
     * 类型
     */
    private String type;

    /**
     * 标签
     */
    private String tags;

    /**
     * 分类
     */
    private String categories;

    /**
     * 开启评论
     */
    private Integer allowComment;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 作者
     */
    private String author;

    /**
     * 内容
     */
    private String content;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Integer getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(Integer allowComment) {
        this.allowComment = allowComment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}