package com.ssafy.sports.model.dto;

import java.time.LocalDateTime;
import java.util.List;

// 장비 주문을 위한 DTO
public class EquipOrderWithInfo {
    private Integer equipOrderId;
    private String userId;
    private LocalDateTime equipOrderTime;

    private List<EquipOrderDetail> details;

    public EquipOrderWithInfo() {
    }

    public int getEquipOrderId() {
        return equipOrderId;
    }

    public void setEquipOrderId(Integer equipOrderId) {
        this.equipOrderId = equipOrderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getEquipOrderTime() {
        return equipOrderTime;
    }

    public void setEquipOrderTime(LocalDateTime equipOrderTime) {
        this.equipOrderTime = equipOrderTime;
    }

    public List<EquipOrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<EquipOrderDetail> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "EquipOrderWithInfo{" +
                "equipOrderId=" + equipOrderId +
                ", userId='" + userId + '\'' +
                ", equipOrderTime=" + equipOrderTime +
                ", details=" + details +
                '}';
    }
}
