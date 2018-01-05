package com.slife.mapper;

import com.slife.model.BlogContent;
import com.slife.model.BlogContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogContentMapper {
    long countByExample(BlogContentExample example);

    int deleteByExample(BlogContentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BlogContent record);

    int insertSelective(BlogContent record);

    List<BlogContent> selectByExampleWithBLOBs(BlogContentExample example);

    List<BlogContent> selectByExample(BlogContentExample example);

    BlogContent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BlogContent record, @Param("example") BlogContentExample example);

    int updateByExampleWithBLOBs(@Param("record") BlogContent record, @Param("example") BlogContentExample example);

    int updateByExample(@Param("record") BlogContent record, @Param("example") BlogContentExample example);

    int updateByPrimaryKeySelective(BlogContent record);

    int updateByPrimaryKeyWithBLOBs(BlogContent record);

    int updateByPrimaryKey(BlogContent record);
}