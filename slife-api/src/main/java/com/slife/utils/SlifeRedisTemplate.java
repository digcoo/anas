package com.slife.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class SlifeRedisTemplate {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 用户收藏取消收藏活动
     * @param userId 用户id
     * @param adId 活动id
     *
     */

    public void collectOrNot(Long userId,Long adId){
        Double score = stringRedisTemplate.opsForZSet().score(RedisKey.USER_FAVOR_ADS+userId,String.valueOf(adId));
        SessionCallback sessionCallback = new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.multi();
                if (score==null){
                    stringRedisTemplate.opsForZSet().add(RedisKey.USER_FAVOR_ADS+userId,adId.toString(),System.currentTimeMillis());
                    stringRedisTemplate.opsForValue().increment(RedisKey.ADS_FAVOR_NUM+adId+":favornum",1);
                }else{
                    stringRedisTemplate.opsForZSet().remove(RedisKey.USER_FAVOR_ADS+userId,String.valueOf(adId));
                    stringRedisTemplate.opsForValue().increment(RedisKey.ADS_FAVOR_NUM+adId+":favornum",-1);
                }
                return redisOperations.exec();
            }
        };
        stringRedisTemplate.execute(sessionCallback);
    }

    /**
     * 用户收藏取消收藏活动
     * @param userId 用户id
     * @param adId 活动id
     *
     */

    public boolean isCollect(Long userId,Long adId){
        Double score = stringRedisTemplate.opsForZSet().score(RedisKey.USER_FAVOR_ADS+userId,String.valueOf(adId));
        return score!=null;
    }

    
    public int getFavorNum(Long adId){
    	String favorNum = stringRedisTemplate.opsForValue().get(RedisKey.ADS_FAVOR_NUM+adId+":favornum");
		return StringUtils.isEmpty(favorNum)? 0 : Integer.parseInt(favorNum);
    }

    /**
     * 用户收藏活动ID列表
     *
     * @param userId
     * @return
     */

    public Set<String> getAllFavorAdIds(Long userId){
        return stringRedisTemplate.opsForZSet().reverseRange(RedisKey.USER_FAVOR_ADS+userId,0,-1);
    }

    /**
     * 分页获取用户收藏活动ID列表
     *
     * @param userId
     * @return
     */

    public Set<String> getFavorAdIdsWithPage(Long userId,Integer index){
        return stringRedisTemplate.opsForZSet().reverseRange(RedisKey.USER_FAVOR_ADS+userId,index==null?0:index,10);
    }

    /**
     * 用户关注取消关注商家
     * @param userId 用户id
     * @param shopId 商家id
     * @return
     */

    public void followOrNot(Long userId,Long shopId){
        Double score = stringRedisTemplate.opsForZSet().score(RedisKey.USER_FOLLOW_SHOPS+userId,String.valueOf(shopId));
        SessionCallback sessionCallback = new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.multi();
                if (score==null){
                    stringRedisTemplate.opsForZSet().add(RedisKey.USER_FOLLOW_SHOPS+userId,shopId.toString(),System.currentTimeMillis());
                    stringRedisTemplate.opsForValue().increment(RedisKey.SHOP_FOLLOW_NUM+shopId+":follownum",1);
                }else{
                    stringRedisTemplate.opsForZSet().remove(RedisKey.USER_FOLLOW_SHOPS+userId,shopId.toString());
                    stringRedisTemplate.opsForValue().increment(RedisKey.SHOP_FOLLOW_NUM+shopId+":follownum",-1);
                }
                return redisOperations.exec();
            }
        };
        stringRedisTemplate.execute(sessionCallback);
    }

    /**
     * 获取用户关注shopID列表
     *
     * @param userId
     * @return
     */

    public Set<String> getAllFollowShopIds(Long userId){
        return stringRedisTemplate.opsForZSet().reverseRange(RedisKey.USER_FOLLOW_SHOPS+userId,0,-1);
    }

    /**
     * 分页获取用户关注shopID列表
     *
     * @param userId
     * @return
     */

    public Set<String> getFollowShopIdsWithPage(Long userId,Integer index){
        return stringRedisTemplate.opsForZSet().reverseRange(RedisKey.USER_FOLLOW_SHOPS+userId,index==null?0:index,10);
    }

    public String setDigcooSessionKey(String sessionKey, String openId) {
        String uuid = UUID.randomUUID().toString();
        String value = String.join(RedisKey.DIGCOO_SESSION_KEY_DELIMITER,openId,sessionKey);
        stringRedisTemplate.opsForValue().set(RedisKey.DIGCOO_SESSION_KEY+uuid, value, 30, TimeUnit.DAYS);
        return uuid;
    }

    public String getDigcooSessionKey(String uuid){
        String value = stringRedisTemplate.opsForValue().get(RedisKey.DIGCOO_SESSION_KEY+uuid);
        //重置缓存有效期
        if(StringUtils.isNotBlank(value)){
            stringRedisTemplate.opsForValue().set(RedisKey.DIGCOO_SESSION_KEY+uuid, value, 30, TimeUnit.DAYS);
        }
        return value;
    }

}
