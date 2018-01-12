package com.slife.mapper;

import com.slife.model.BlogContent;

public interface BlogContentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(BlogContent record);

    BlogContent selectByPrimaryKey(Long id);

    int updateByPrimaryKey(BlogContent record);
}