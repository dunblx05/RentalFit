package com.ssafy.sports.model.dao;

import com.ssafy.sports.model.dto.EquipReservation;
import com.ssafy.sports.model.dto.PlaceReservation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EquipReservationDao {

    /**
     * 사용자의 장비 대여 정보를 사용자 ID를 사용하여 조회한다.
     *
     * @param userId
     * @return
     */
    List<EquipReservation> selectEquipResByUid(String userId);

    /**
     * 예약번호를 통해 장비 예약 세부 사항을 조회한다.
     *
     * @param resId
     * @return
     */
    EquipReservation selectEquipResByRid(int resId);

}
