package com.ssafy.sports.controller.rest;

import com.ssafy.sports.model.dto.EquipReservation;
import com.ssafy.sports.model.dto.PlaceReservation;
import com.ssafy.sports.model.service.EquipReservationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/equip/reservation")
@CrossOrigin("*")
public class EquipReservationRestController {

    @Autowired
    EquipReservationService erService;

    @GetMapping("/byUserId/{userId}")
    @Operation(summary = "{userId}에 해당하는 장비 대여 내역을 장비 정보와 함께 조회")
    public List<EquipReservation> selectEquipResByUid(@PathVariable String userId) {
        return erService.selectEquipResByUid(userId);
    }

    @GetMapping("/byResId/{resId}")
    @Operation(summary = "{resId}에 해당하는 장비 예약 내역을 단건 조회")
    public EquipReservation selectPlaceResByRid(@PathVariable int resId) {
        return erService.selectEquipResByRid(resId);
    }

}
