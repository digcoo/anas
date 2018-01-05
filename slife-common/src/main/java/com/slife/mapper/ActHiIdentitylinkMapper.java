package com.slife.mapper;

import com.slife.model.ActHiIdentitylink;
import com.slife.model.ActHiIdentitylinkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActHiIdentitylinkMapper {
    long countByExample(ActHiIdentitylinkExample example);

    int deleteByExample(ActHiIdentitylinkExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActHiIdentitylink record);

    int insertSelective(ActHiIdentitylink record);

    List<ActHiIdentitylink> selectByExample(ActHiIdentitylinkExample example);

    ActHiIdentitylink selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActHiIdentitylink record, @Param("example") ActHiIdentitylinkExample example);

    int updateByExample(@Param("record") ActHiIdentitylink record, @Param("example") ActHiIdentitylinkExample example);

    int updateByPrimaryKeySelective(ActHiIdentitylink record);

    int updateByPrimaryKey(ActHiIdentitylink record);
}