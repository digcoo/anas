package com.slife.mapper;

import com.slife.model.ActIdMembershipExample;
import com.slife.model.ActIdMembershipKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActIdMembershipMapper {
    long countByExample(ActIdMembershipExample example);

    int deleteByExample(ActIdMembershipExample example);

    int deleteByPrimaryKey(ActIdMembershipKey key);

    int insert(ActIdMembershipKey record);

    int insertSelective(ActIdMembershipKey record);

    List<ActIdMembershipKey> selectByExample(ActIdMembershipExample example);

    int updateByExampleSelective(@Param("record") ActIdMembershipKey record, @Param("example") ActIdMembershipExample example);

    int updateByExample(@Param("record") ActIdMembershipKey record, @Param("example") ActIdMembershipExample example);
}