package com.ssafy.sports.controller.rest;


import com.ssafy.sports.model.dto.PlaceReservation;
import com.ssafy.sports.model.service.PlaceReservationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
