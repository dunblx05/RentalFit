package com.ssafy.sports.model.service;

import com.ssafy.sports.model.dao.EquipDao;
import com.ssafy.sports.model.dao.EquipDetailDao;
import com.ssafy.sports.model.dao.EquipOrderDao;
import com.ssafy.sports.model.dao.UserDao;
import com.ssafy.sports.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EquipOrderServiceImpl implements EquipOrderService{

    @Autowired
    private EquipOrderDao eoDao;

    @Autowired
    private EquipDao eDao;

    @Autowired
    private EquipDetailDao edDao;

    @Autowired
    private UserDao uDao;

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

    @Override
    @Transactional
    public void makeEquipOrder(EquipOrderWithInfo equipOrderWithInfo) {
        equipOrderWithInfo.setEquipOrderId(null);

        eoDao.insert(equipOrderWithInfo);
        List<EquipOrderDetail> details = equipOrderWithInfo.getDetails();

        int quantitySum = 0;

        for(EquipOrderDetail detail : details) {
            detail.setEquipOrderId(equipOrderWithInfo.getEquipOrderId());
            edDao.insert(detail);
            quantitySum += detail.getQuantity();
        }

        User user = uDao.selectById(equipOrderWithInfo.getUserId());
        user.setUserStamps(quantitySum * 10);

        uDao.updateStamp(user);

    }
}
