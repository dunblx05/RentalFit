package com.ssafy.sports.model.service;

import com.ssafy.sports.model.dao.EquipDao;
import com.ssafy.sports.model.dto.Equip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipServiceImpl implements EquipService{

    @Autowired
    EquipDao eDao;

    @Override
    public List<Equip> selectAllEquip() {
        return eDao.selectAllEquip();
    }

    @Override
    public Equip selectEquipByEid(int equipId) {
        return eDao.selectEquipByEid(equipId);
    }

    @Override
    public List<Equip> selectEquipByType(String type) {
        return eDao.selectEquipByType(type);
    }
}
