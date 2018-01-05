package com.slife.mapper;

import com.slife.model.ActRuIdentitylink;
import com.slife.model.ActRuIdentitylinkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActRuIdentitylinkMapper {
    long countByExample(ActRuIdentitylinkExample example);

    int deleteByExample(ActRuIdentitylinkExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActRuIdentitylink record);

    int insertSelective(ActRuIdentitylink record);

    List<ActRuIdentitylink> selectByExample(ActRuIdentitylinkExample example);

    ActRuIdentitylink selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActRuIdentitylink record, @Param("example") ActRuIdentitylinkExample example);

    int updateByExample(@Param("record") ActRuIdentitylink record, @Param("example") ActRuIdentitylinkExample example);

    int updateByPrimaryKeySelective(ActRuIdentitylink record);

    int updateByPrimaryKey(ActRuIdentitylink record);
}