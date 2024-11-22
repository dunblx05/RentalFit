package com.ssafy.sports.model.service;

import com.ssafy.sports.model.dto.EquipOrder;
import com.ssafy.sports.model.dto.EquipOrderDetail;

import java.util.List;

public interface EquipOrderService {
    public List<EquipOrder> selectEquipOrderByUid(String userId);

    public List<EquipOrderDetail> selectEquipOrderDetail(int equipOrderId);
}
