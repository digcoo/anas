package com.slife.mapper;

import com.slife.model.ActHiComment;
import com.slife.model.ActHiCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActHiCommentMapper {
    long countByExample(ActHiCommentExample example);

    int deleteByExample(ActHiCommentExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActHiComment record);

    int insertSelective(ActHiComment record);

    List<ActHiComment> selectByExampleWithBLOBs(ActHiCommentExample example);

    List<ActHiComment> selectByExample(ActHiCommentExample example);

    ActHiComment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActHiComment record, @Param("example") ActHiCommentExample example);

    int updateByExampleWithBLOBs(@Param("record") ActHiComment record, @Param("example") ActHiCommentExample example);

    int updateByExample(@Param("record") ActHiComment record, @Param("example") ActHiCommentExample example);

    int updateByPrimaryKeySelective(ActHiComment record);

    int updateByPrimaryKeyWithBLOBs(ActHiComment record);

    int updateByPrimaryKey(ActHiComment record);
}