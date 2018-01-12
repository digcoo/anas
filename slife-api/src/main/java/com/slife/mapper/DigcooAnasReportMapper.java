package com.slife.mapper;

import com.slife.model.DigcooAnasReport;

public interface DigcooAnasReportMapper {

    int deleteByPrimaryKey(Long id);

    int insert(DigcooAnasReport record);

    DigcooAnasReport selectByPrimaryKey(Long id);

    int updateByPrimaryKey(DigcooAnasReport record);
}