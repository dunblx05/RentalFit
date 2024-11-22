package com.ssafy.sports.controller.rest;


import com.ssafy.sports.model.dto.Place;
import com.ssafy.sports.model.service.PlaceService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/place")
@CrossOrigin("*")
public class PlaceRestController {

    @Autowired
    PlaceService pService;

    @GetMapping("")
    @Operation(summary = "전체 장소 조회")
    public List<Place> selectAllPlace() {
    	return pService.selectAllPlace();
    }

    @GetMapping("/pid/{placeId}")
    @Operation(summary = "{placeId}에 해당하는 장소 단건 조회")
    public Place selectPlaceByPid(@PathVariable int placeId) {
    	return pService.selectPlaceByPid(placeId);
    }


    @GetMapping("/type/{placeType}")
    @Operation(summary = "{placeType}에 해당하는 장소 조회")
    public List<Place> selectPlaceByPtype(@PathVariable String placeType) {
    	return pService.selectPlaceByPtype(placeType);
    }

}
