package com.ssafy.sports.model.service;

import com.ssafy.sports.model.dto.EquipReservation;
import com.ssafy.sports.model.dto.PlaceReservation;

import java.util.List;

public interface EquipReservationService {
    public List<EquipReservation> selectEquipResByUid(String userId);

    public EquipReservation selectEquipResByRid(int resId);
}
