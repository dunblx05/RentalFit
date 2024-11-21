package com.ssafy.sports.model.service;

import com.ssafy.sports.model.dto.Place;
import com.ssafy.sports.model.dto.PlaceReservation;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PlaceReservationService {
    public List<PlaceReservation> selectPlaceResByUid(String userId);

    public PlaceReservation selectPlaceResByRid(int resId);

}
