package com.ssafy.sports.model.dao;

import com.ssafy.sports.model.dto.Place;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlaceDao {
    // 장소ID를 통해 장소정보 조회
    Place selectPlaceByPid(int placeId);
}
