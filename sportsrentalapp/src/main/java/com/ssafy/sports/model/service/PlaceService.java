package com.ssafy.sports.model.service;

import java.util.List;
import com.ssafy.sports.model.dto.Place;

public interface PlaceService {

    public List<Place> selectAllPlace() ;

    public Place selectPlaceByPid(int placeId) ;

    public List<Place> selectPlaceByPtype(String placeType) ;
}
