package com.slife.service;


import com.slife.entity.Shop;

import java.util.List;

/**
 * Created by cq on 18-1-28.
 */
public interface FollowService {
    /**
     * 关注
     * @param userId
     * @param shopId
     * @return
     */
    boolean link(long userId, long shopId);

    /**
     * 取消关注
     *
     * @param userId
     * @param shopId
     * @return
     */
    boolean unlink(long userId, long shopId);

    /**
     * 获取关注列表，不分页
     * @param userId
     * @return
     */
    List<Shop> getShopListByUserId(long userId);
}
