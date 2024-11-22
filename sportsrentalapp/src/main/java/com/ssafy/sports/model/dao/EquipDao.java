package com.ssafy.sports.model.dao;

import com.ssafy.sports.model.dto.Equip;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EquipDao {
    List<Equip> selectAllEquip();

    Equip selectEquipByEid(int equipId);

    List<Equip> selectEquipByType(String type);

}
