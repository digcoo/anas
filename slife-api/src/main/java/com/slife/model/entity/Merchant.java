package com.slife.model.entity;


import com.baomidou.mybatisplus.annotations.TableName;
import com.slife.base.entity.DataEntity;

import java.io.Serializable;

/**
 * @author tod
 * @date 2018/1/2
 * <p>
 * Email will_tao@126.com
 * <p>
 * Describe: merchant实体映射
 */
@TableName("merchant")
public class Merchant extends DataEntity<Merchant> {

    /**
     * varchar(500) 商家logo url
     */
    private String logo;
    /**
     * varchar(500) 商家名称
     */
    private String name;
    /**
     * varchar(1000) 商家地址
     */
    private String addr;
    /**
     * varchar(15) 商家电话
     */
    private String tel;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
