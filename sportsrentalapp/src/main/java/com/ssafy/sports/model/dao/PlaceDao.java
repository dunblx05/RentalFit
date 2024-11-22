package com.ssafy.sports.model.dao;

import com.ssafy.sports.model.dto.Place;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlaceDao {
    // 전체 장소정보 조회
	List<Place> selectAllPlace();
    // 장소ID를 통해 장소정보 조회
    Place selectPlaceByPid(int placeId);
    // 장소 분류를 통해 장소정보 조회
	List<Place> selectPlaceByPtype(String placeType);
}
