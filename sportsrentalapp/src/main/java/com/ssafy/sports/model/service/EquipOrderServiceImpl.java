package com.ssafy.sports.model.service;

import com.ssafy.sports.model.dao.EquipDao;
import com.ssafy.sports.model.dao.EquipOrderDao;
import com.ssafy.sports.model.dto.Equip;
import com.ssafy.sports.model.dto.EquipOrder;
import com.ssafy.sports.model.dto.EquipOrderDetail;
import com.ssafy.sports.model.dto.EquipOrderDetailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipOrderServiceImpl implements EquipOrderService{

    @Autowired
    private EquipOrderDao eoDao;

    @Autowired
    private EquipDao eDao;

    @Override
    public List<EquipOrder> selectEquipOrderByUid(String userId) {
        List<EquipOrder> results = eoDao.selectEquipOrderByUid(userId);

        for (EquipOrder result : results) {
            List<EquipOrderDetailInfo> infos = eoDao.selectEquipOrderDetailByEOid(result.getEquipOrderId());
            result.setDetails(infos);
        }

        return results;
    }

    @Override
    public List<EquipOrderDetail> selectEquipOrderDetail(int equipOrderId) {

        List<EquipOrderDetail> results = eoDao.selectEquipOrderDetail(equipOrderId);

        for(EquipOrderDetail result : results) {
            Equip info = eDao.selectEquipByEid(result.getEquipId());
            result.setEquip(info);
        }

        return results;
    }
}
