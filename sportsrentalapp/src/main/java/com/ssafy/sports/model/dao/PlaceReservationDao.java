package com.ssafy.sports.model.dao;

import com.ssafy.sports.model.dto.PlaceReservation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlaceReservationDao {

    /**
     * 사용자의 장소 예약 정보를 사용자 ID를 사용하여 조회한다.
     * @param userId
     * @return
     */
    List<PlaceReservation> selectPlaceResByUid(String userId);

    /**
     * 예약번호를 통해 예약 세부 사항을 조회한다.
     * @param resId
     * @return
     */
    PlaceReservation selectPlaceResByRid(int resId);
}
