package com.ssafy.sports.model.service;

import com.ssafy.sports.model.dto.Place;
import com.ssafy.sports.model.dto.PlaceReservation;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface PlaceReservationService {
    public List<PlaceReservation> selectPlaceResByUid(String userId);

    public PlaceReservation selectPlaceResByRid(int resId);
    
    public void insertPlaceReservation(PlaceReservation res);
    
    public List<PlaceReservation> selectResByPidInToday(int placeId);
    
    public List<PlaceReservation> selectResByPidInDate(int placeId, Date date);

}
