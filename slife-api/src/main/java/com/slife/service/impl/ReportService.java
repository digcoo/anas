package com.slife.service.impl;

import com.slife.base.service.impl.BaseService;
import com.slife.dao.ReportDao;
import com.slife.entity.Report;
import com.slife.service.IReportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vip on 2018/2/5.
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ReportService extends BaseService<ReportDao, Report> implements IReportService {

}
