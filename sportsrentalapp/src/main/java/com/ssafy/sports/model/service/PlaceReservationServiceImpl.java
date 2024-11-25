package com.ssafy.sports.model.service;

import com.ssafy.sports.model.dao.PlaceDao;
import com.ssafy.sports.model.dao.PlaceReservationDao;
import com.ssafy.sports.model.dao.UserDao;
import com.ssafy.sports.model.dto.Place;
import com.ssafy.sports.model.dto.PlaceReservation;
import com.ssafy.sports.model.dto.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlaceReservationServiceImpl implements PlaceReservationService {

    @Autowired
    private PlaceReservationDao prDao;

    @Autowired
    private UserDao uDao;
    
    @Autowired
    private PlaceDao pDao;

    @Override
    public List<PlaceReservation> selectPlaceResByUid(String userId) {
        List<PlaceReservation> reservations = prDao.selectPlaceResByUid(userId);

        for (PlaceReservation reservation : reservations) {
            Place place = pDao.selectPlaceByPid(reservation.getPlaceId());
            reservation.setPlace(place);
        }

        return reservations;
    }

    public PlaceReservation selectPlaceResByRid(int resId) {
        PlaceReservation reservation = prDao.selectPlaceResByRid(resId);

        Place place = pDao.selectPlaceByPid(reservation.getPlaceId());
        reservation.setPlace(place);

        return reservation;
    }
   

    int stampPlaceReservation = 50;
	@Override
	public void insertPlaceReservation(PlaceReservation res) {
		res.setResId(0);
		
		prDao.insertPlaceReservation(res);
		
		User user = uDao.selectById(res.getUserId());
		user.setUserStamps(stampPlaceReservation);
		
		uDao.updateStamp(user);
	}

	@Override
	public List<PlaceReservation> selectResByPidInToday(int placeId) {
		List<PlaceReservation> reservations = prDao.selectResByPidInToday(placeId);

        for (PlaceReservation reservation : reservations) {
            Place place = pDao.selectPlaceByPid(reservation.getPlaceId());
            reservation.setPlace(place);
        }

        return reservations;
	}

	@Override
	public List<PlaceReservation> selectResByPidInDate(int placeId, Date date) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("placeId", placeId);
	    params.put("date", new SimpleDateFormat("yyyy-MM-dd").format(date)); // yyyy-MM-dd 형식으로 변환
	    List<PlaceReservation> reservations =prDao.selectResByPidInDate(params);

        for (PlaceReservation reservation : reservations) {
            Place place = pDao.selectPlaceByPid(reservation.getPlaceId());
            reservation.setPlace(place);
        }

        return reservations;
	}

}
