package com.slife.entity;


import com.baomidou.mybatisplus.annotations.TableName;
import com.slife.base.entity.ApiEntity;

import java.io.Serializable;

/**
 * @author tod
 * @date 2018/1/25
 * <p>
 * Email will_tao@126.com
 * <p>
 * Describe: mall shop count view映射
 */
public class ShopCountPerMallView{

    private static final long serialVersionUID = 1L;

	private Long mallId;

	private Integer nums;

	public Long getMallId() {
		return mallId;
	}

	public void setMallId(Long mallId) {
		this.mallId = mallId;
	}

	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}
}
