package com.slife.service;

import java.util.List;

import com.slife.entity.Mall;
import com.slife.vo.MallVO;

/**
 * Created by duyp on 18-1-23.
 */
public interface IMallService {
	
    List<MallVO> findListBykey(String key);

    List<Mall> selectMallsByGeohash(String geohash);
    
    List<MallVO> selectAll();
}
