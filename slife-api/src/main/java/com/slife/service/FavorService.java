package com.slife.service;

/**
 * Created by cq on 18-1-28.
 */
public interface FavorService {

    /**
     * 收藏
     * @param userId
     * @param adId
     * @return
     */
    boolean link(long userId,long adId);

    /**
     * 取消收藏
     * @param userId
     * @param adId
     * @return
     */
    boolean unlink(long userId ,long adId);

    /**
     * 收藏总数
     * @param adId
     * @return
     */
    int total(long adId);
}
