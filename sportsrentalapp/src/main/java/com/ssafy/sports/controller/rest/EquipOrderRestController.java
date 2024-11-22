package com.ssafy.sports.controller.rest;


import com.ssafy.sports.model.dto.EquipOrder;
import com.ssafy.sports.model.service.EquipOrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/equip/order")
@CrossOrigin("*")
public class EquipOrderRestController {

    @Autowired
    EquipOrderService equipOrderService;

    @GetMapping("/byUserId/{userId}")
    @Operation(summary = "{userId}에 해당하는 장비 주문 내역을 장비 정보와 함께 조회")
    public List<EquipOrder> selectEquipOrderByUid(@PathVariable String userId) {
        return equipOrderService.selectEquipOrderByUid(userId);
    }


}
