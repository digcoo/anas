package com.slife.service.impl;

import com.slife.base.service.impl.BaseService;
import com.slife.dao.MerchantDao;
import com.slife.entity.Merchant;
import com.slife.service.IMerchantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tod
 * @date 2018/1/2
 * <p>
 * Email will_tao@126.com
 * <p>
 * Describe: merchant service
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MerchantService extends BaseService<MerchantDao, Merchant> implements IMerchantService {


}
