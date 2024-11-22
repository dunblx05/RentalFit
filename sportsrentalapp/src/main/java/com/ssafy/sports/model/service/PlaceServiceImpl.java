package com.ssafy.sports.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.sports.model.dao.PlaceDao;
import com.ssafy.sports.model.dto.Place;

@Service
public class PlaceServiceImpl implements PlaceService{

    @Autowired
    private PlaceDao pDao;

	@Override
	public List<Place> selectAllPlace() {
		return pDao.selectAllPlace();
	}

	@Override
	public Place selectPlaceByPid(int placeId) {
		return pDao.selectPlaceByPid(placeId);
	}

	@Override
	public List<Place> selectPlaceByPtype(String placeType) {
		// TODO Auto-generated method stub
		return pDao.selectPlaceByPtype(placeType);
	}


}