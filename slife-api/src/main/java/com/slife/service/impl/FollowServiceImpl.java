package com.slife.service.impl;

import com.slife.dao.FollowDao;
import com.slife.dao.ShopDao;
import com.slife.entity.Follow;
import com.slife.entity.Shop;
import com.slife.entity.enums.LinkStatus;
import com.slife.enums.HttpCodeEnum;
import com.slife.exception.SlifeException;
import com.slife.service.FollowService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by cq on 18-1-28.
 */
@Component
public class FollowServiceImpl implements FollowService {

    @Resource
    private FollowDao followDao;

    @Resource
    private ShopDao shopDao;

    @Override
    public boolean link(long userId, long shopId) {
        if (userId <= 0 || shopId <= 0) {
            throw new SlifeException(HttpCodeEnum.INVALID_REQUEST);
        }
        Shop shop = shopDao.selectById(shopId);
        if (shop == null) {
            throw new SlifeException(HttpCodeEnum.SHOP_NOT_EXISTS);
        }
        Follow follow = followDao.selectByUserIdAndShopId(userId ,shopId);
        int addCount;
        if(follow == null){
            follow = new Follow();
            follow.setShopId(shopId);
            follow.setUserId(userId);
            follow.setFollowTime(new Date());
            follow.setStatus(LinkStatus.LINK.getCode());
            addCount = followDao.insert(follow);
        }else{
            follow.setStatus(LinkStatus.LINK.getCode());
            follow.setFollowTime(new Date());
            addCount = followDao.updateByPrimaryKey(follow);
        }
        return addCount == 1;
    }

    @Override
    public boolean unlink(long userId, long shopId) {
        if (userId <= 0 || shopId <= 0) {
            throw new SlifeException(HttpCodeEnum.INVALID_REQUEST);
        }
        Follow follow = followDao.selectByUserIdAndShopId(userId, shopId);
        if (follow == null) {
            throw new SlifeException(HttpCodeEnum.SHOP_NOT_FOLLOW);
        }
        if (follow.getStatus() == LinkStatus.UNLINK.getCode()) {
            throw new SlifeException(HttpCodeEnum.SHOP_UNLINK_FOLLOW);
        }
        follow.setStatus(LinkStatus.UNLINK.getCode());
        int editCount = followDao.updateByPrimaryKey(follow);
        return editCount == 1;
    }

    public List<Shop> getShopListByUserId(long userId){
        Map<String ,Object> map = new HashedMap();
        map.put("userId",userId);
        map.put("status",LinkStatus.LINK.getCode());
        List<Follow> follows = followDao.selectByUserId(map);
        if(CollectionUtils.isEmpty(follows)){
            return Collections.EMPTY_LIST;
        }
        List<Long> shopIds = follows.stream().map(Follow::getShopId).collect(Collectors.toList());
        List<Shop> shops = shopDao.selectBatchIds(shopIds);
        return shops;
    }
}
