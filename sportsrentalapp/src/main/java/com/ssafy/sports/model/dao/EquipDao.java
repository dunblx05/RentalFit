package com.ssafy.sports.model.dao;

import com.ssafy.sports.model.dto.Equip;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EquipDao {
    Equip selectEquipByEid(int equipId);
}
