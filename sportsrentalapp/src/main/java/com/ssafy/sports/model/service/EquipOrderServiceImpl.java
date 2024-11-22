package com.ssafy.sports.model.service;

import com.ssafy.sports.model.dao.EquipOrderDao;
import com.ssafy.sports.model.dto.EquipOrder;
import com.ssafy.sports.model.dto.EquipOrderDetailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipOrderServiceImpl implements EquipOrderService{

    @Autowired
    private EquipOrderDao eoDao;

    @Override
    public List<EquipOrder> selectEquipOrderByUid(String userId) {
        List<EquipOrder> results = eoDao.selectEquipOrderByUid(userId);

        for (EquipOrder result : results) {
            List<EquipOrderDetailInfo> infos = eoDao.selectEquipOrderDetailByEOid(result.getEquipOrderId());
            result.setDetails(infos);
        }

        return results;
    }
}
