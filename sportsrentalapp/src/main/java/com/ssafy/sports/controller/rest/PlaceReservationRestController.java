package com.ssafy.sports.controller.rest;


import com.ssafy.sports.model.dto.PlaceReservation;
import com.ssafy.sports.model.service.PlaceReservationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest/place/reservation")
@CrossOrigin("*")
public class PlaceReservationRestController {

    @Autowired
    PlaceReservationService prService;

    @GetMapping("/byUserId/{userId}")
    @Operation(summary = "{userId}에 해당하는 장소 예약 내역을 장소 정보와 함께 조회")
    public List<PlaceReservation> selectPlaceResByUid(@PathVariable String userId) {
        return prService.selectPlaceResByUid(userId);
    }

    @GetMapping("/byResId/{resId}")
    @Operation(summary = "{resId}에 해당하는 장소 예약 내역을 단건 조회")
    public PlaceReservation selectPlaceResByRid(@PathVariable int resId) {
        return prService.selectPlaceResByRid(resId);
    }
    
    @PostMapping
    @Operation(summary="장소 예약 신청")
    public Integer insertPlaceReservation(@RequestBody PlaceReservation res) {
        prService.insertPlaceReservation(res);
        return res.getResId();
    }
    
    @GetMapping("/InToday/{placeId}")
    @Operation(summary="오늘 날짜 기준으로 해당 장소에 대한 모든 예약")
    public List<PlaceReservation> selectResByPidInToday(@PathVariable int placeId){
    	return prService.selectResByPidInToday(placeId);
    }
    
    @GetMapping("/InDate/{placeId}")
    @Operation(summary="특정 날짜 기준으로 해당 장소에 대한 모든 예약")
    public List<PlaceReservation> selectResByPidInDate(@PathVariable int placeId,
    	    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
    	return prService.selectResByPidInDate(placeId, date);
    }
}
