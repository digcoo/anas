package com.slife.service.impl;

import com.slife.dao.FavorDao;
import com.slife.dao.ShopAdDao;
import com.slife.entity.Favor;
import com.slife.entity.ShopAd;
import com.slife.entity.enums.LinkStatus;
import com.slife.enums.HttpCodeEnum;
import com.slife.exception.SlifeException;
import com.slife.service.FavorService;
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
public class FavorServiceImpl implements FavorService {

    @Resource
    private FavorDao favorDao;
    @Resource
    private ShopAdDao shopAdDao;

    @Override
    public boolean link(long userId, long adId) {
        if (userId <= 0) {
            throw new SlifeException(HttpCodeEnum.INVALID_REQUEST);
        }
        ShopAd shopAd = shopAdDao.selectById(adId);
        if (shopAd == null) {
            throw new SlifeException(HttpCodeEnum.AD_NOT_EXISTS);
        }
        Favor favor = favorDao.selectByUserIdAndAdId(userId, adId);
        int addCount;
        if (favor == null) {
            favor = new Favor();
            favor.setUserId(userId);
            favor.setAdId(adId);
            favor.setFavor(new Date());
            favor.setStatus(LinkStatus.LINK.getCode());
            addCount = favorDao.insert(favor);
        } else {
            favor.setFavor(new Date());
            favor.setStatus(LinkStatus.LINK.getCode());
            addCount = favorDao.updateByPrimaryKey(favor);
        }
        return addCount == 1;
    }

    @Override
    public boolean unlink(long userId, long adId) {
        if (userId <= 0) {
            throw new SlifeException(HttpCodeEnum.INVALID_REQUEST);
        }
        Favor originalFavor = favorDao.selectByUserIdAndAdId(userId, adId);
        if (originalFavor == null) {
            throw new SlifeException(HttpCodeEnum.AD_NOT_FAVOR);
        }
        if (originalFavor.getStatus() == LinkStatus.UNLINK.getCode()) {
            throw new SlifeException(HttpCodeEnum.AD_CANCEL_FAVOR);
        }
        originalFavor.setStatus(LinkStatus.UNLINK.getCode());
        int addCount = favorDao.updateByPrimaryKey(originalFavor);
        return addCount == 1;
    }

    @Override
    public List<ShopAd> getShopAdsByUserId(long userId) {
        Map<String, Object> map = new HashedMap();
        map.put("userId", userId);
        map.put("status", LinkStatus.LINK.getCode());
        List<Favor> favors = favorDao.selectByUserId(map);
        if (CollectionUtils.isEmpty(favors)) {
            return Collections.EMPTY_LIST;
        }
        List<Long> ids = favors.stream().map(Favor::getAdId).collect(Collectors.toList());
        List<ShopAd> shopAds = shopAdDao.selectBatchIds(ids);
        return shopAds;
    }

    @Override
    public int total(long adId) {
        return 0;
    }
}
