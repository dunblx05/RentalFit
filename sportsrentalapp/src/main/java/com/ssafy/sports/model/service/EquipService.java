package com.ssafy.sports.model.service;

import com.ssafy.sports.model.dto.Equip;

import java.util.List;

public interface EquipService {

    public List<Equip> selectAllEquip();

    public Equip selectEquipByEid(int equipId);

    public List<Equip> selectEquipByType(String type);

}
