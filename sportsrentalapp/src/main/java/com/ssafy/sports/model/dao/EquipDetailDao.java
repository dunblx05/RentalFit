package com.ssafy.sports.model.dao;

import com.ssafy.sports.model.dto.EquipOrderDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EquipDetailDao {

    int insert(EquipOrderDetail detail);

}
