package com.ssafy.sports.model.service;

import com.ssafy.sports.model.dao.EquipDao;
import com.ssafy.sports.model.dao.EquipReservationDao;
import com.ssafy.sports.model.dto.Equip;
import com.ssafy.sports.model.dto.EquipReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipReservationServiceImpl implements EquipReservationService{

    @Autowired
    private EquipReservationDao erDao;

    @Autowired
    private EquipDao eDao;

    @Override
    public List<EquipReservation> selectEquipResByUid(String userId) {
        List<EquipReservation> reservations = erDao.selectEquipResByUid(userId);

        for(EquipReservation reservation : reservations) {
            Equip equip = eDao.selectEquipByEid(reservation.getEquipId());
            reservation.setEquip(equip);
        }

        return reservations;
    }

    @Override
    public EquipReservation selectEquipResByRid(int resId) {
        EquipReservation reservation = erDao.selectEquipResByRid(resId);

        Equip equip = eDao.selectEquipByEid(reservation.getEquipId());
        reservation.setEquip(equip);

        return reservation;
    }
}
