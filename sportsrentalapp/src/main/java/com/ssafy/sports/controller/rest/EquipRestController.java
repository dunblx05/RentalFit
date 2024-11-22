package com.ssafy.sports.controller.rest;

import com.ssafy.sports.model.dto.Equip;
import com.ssafy.sports.model.service.EquipService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/equip")
@CrossOrigin("*")
public class EquipRestController {

    @Autowired
    EquipService eService;

    @GetMapping("")
    @Operation(summary = "전체 장비 조회")
    public List<Equip> selectAllEquip() {
        return eService.selectAllEquip();
    }

    @GetMapping("/eid/{equipId}")
    @Operation(summary = "{equipId}에 따른 장비 정보를 단건 조회한다.")
    public Equip selectEquipByEid(@PathVariable int equipId) {
        return eService.selectEquipByEid(equipId);
    }

    @GetMapping("/type/{type}")
    @Operation(summary = "{type}에 따른 장비 정보를 조회한다.")
    public List<Equip> selectEquipByType(@PathVariable String type) {
        return eService.selectEquipByType(type);
    }


}
