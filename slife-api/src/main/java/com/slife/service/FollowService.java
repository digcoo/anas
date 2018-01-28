package com.slife.service;


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
}
