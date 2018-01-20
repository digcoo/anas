package com.slife.service.impl;

import com.slife.base.service.impl.BaseService;
import com.slife.dao.BusinessDao;
import com.slife.entity.Business;
import com.slife.service.IBusinessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class BusinessService extends BaseService<BusinessDao, Business> implements IBusinessService{

    @Override
    public List<Business> selectAll() {
        return this.baseMapper.selectAll();
    }
}
