package com.slife.mapper;

import com.slife.model.DigcooAnasFeedback;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DigcooAnasFeedbackMapper {

    int deleteByPrimaryKey(Long id);

    int insert(DigcooAnasFeedback record);

    DigcooAnasFeedback selectByPrimaryKey(Long id);

    int updateByPrimaryKey(DigcooAnasFeedback record);
}