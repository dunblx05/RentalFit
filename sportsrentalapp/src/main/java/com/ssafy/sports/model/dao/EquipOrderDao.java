package com.ssafy.sports.model.dao;

import com.ssafy.sports.model.dto.EquipOrder;
import com.ssafy.sports.model.dto.EquipOrderDetail;
import com.ssafy.sports.model.dto.EquipOrderDetailInfo;
import com.ssafy.sports.model.dto.EquipOrderWithInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EquipOrderDao {
    List<EquipOrder> selectEquipOrderByUid(String userId);

    List<EquipOrderDetailInfo> selectEquipOrderDetailByEOid(int equipId);

    List<EquipOrderDetail> selectEquipOrderDetail(int equipOrderId);

    int insert(EquipOrderWithInfo equipOrderWithInfo);
}
