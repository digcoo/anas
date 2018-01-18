/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.slife.base.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.slife.constant.Global;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 数据Entity类
 *
 * @param <T>
 */
public abstract class ApiEntity<T extends Model> extends BaseEntity<T> {

    private static final long serialVersionUID = 1L;


    /**
     * 创建日期
     */
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    protected Date createDate;


    /**
     * 更新日期
      */
    @TableField(value = "update_date", fill = FieldFill.INSERT_UPDATE)
    protected Date updateDate;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public ApiEntity() {
        super();

    }

    public ApiEntity(Long id) {
        super(id);
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

}
