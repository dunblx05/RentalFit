package com.ssafy.sports.model.dto;

import org.springframework.cglib.core.Local;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class EquipReservation {
    private int resId;
    private String userId;
    private int equipId;
    private LocalDateTime resStartTime;
    private int resCost;

    private Equip equip;

    public EquipReservation(){};

    public EquipReservation(int resId, String userId, int equipId, LocalDateTime resStartTime, int resCost, Equip equip) {
        this.resId = resId;
        this.userId = userId;
        this.equipId = equipId;
        this.resStartTime = resStartTime;
        this.resCost = resCost;
        this.equip = equip;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getEquipId() {
        return equipId;
    }

    public void setEquipId(int equipId) {
        this.equipId = equipId;
    }

    public LocalDateTime getResStartTime() {
        return resStartTime;
    }

    public void setResStartTime(LocalDateTime resStartTime) {
        this.resStartTime = resStartTime;
    }

    public int getResCost() {
        return resCost;
    }

    public void setResCost(int resCost) {
        this.resCost = resCost;
    }

    public Equip getEquip() {
        return equip;
    }

    public void setEquip(Equip equip) {
        this.equip = equip;
    }

    @Override
    public String toString() {
        return "EquipReservation{" +
                "resId=" + resId +
                ", userId='" + userId + '\'' +
                ", equipId=" + equipId +
                ", resStartTime=" + resStartTime +
                ", resCost=" + resCost +
                ", equip=" + equip +
                '}';
    }
}
