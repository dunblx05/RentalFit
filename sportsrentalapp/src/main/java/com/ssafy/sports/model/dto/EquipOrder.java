package com.ssafy.sports.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class EquipOrder {
    private int equipOrderId;
    private String userId;
    private LocalDateTime equipOrderTime;

    List<EquipOrderDetailInfo> details;

    public List<EquipOrderDetailInfo> getDetails() {
        return details;
    }

    public void setDetails(List<EquipOrderDetailInfo> details) {
        this.details = details;
    }

    public EquipOrder() {
    }

    public int getEquipOrderId() {
        return equipOrderId;
    }

    public void setEquipOrderId(int equipOrderId) {
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

    @Override
    public String toString() {
        return "EquipOrder{" +
                "equipOrderId=" + equipOrderId +
                ", userId='" + userId + '\'' +
                ", equipOrderTime=" + equipOrderTime +
                '}';
    }
}
