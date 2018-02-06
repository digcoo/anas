package com.slife.utils;


public interface RedisKey {

    String PHONE_CODE_KEY = "shop:phonecode:";

    String PHONE_CODE_COUNT = "shop:phonecode:count:";

    String SHOP_BASE = "shop:shopBase:";

    String USER_FAVOR_ADS = "favor:userid:";

    String ADS_FAVOR_NUM = "ad:id:";

    String USER_FOLLOW_SHOPS = "follow:userid:";

    String SHOP_FOLLOW_NUM = "shop:id:";

    String USER_TICKET = "ticket:uuid:";

    String SHOP_HAS_PUBLISH_NUM = "shop:has:publish:count:";

    String SHOP_CAN_PUBLISH_NUM = "shop:can:publish:count:";

}
