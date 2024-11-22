package com.ssafy.sports.model.dto;

public class EquipOrderDetail {
    private int detailId;
    private int equipOrderId;
    private int equipId;
    private int quantity;

    private Equip equip;

    public EquipOrderDetail() {
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getEquipOrderId() {
        return equipOrderId;
    }

    public void setEquipOrderId(int equipOrderId) {
        this.equipOrderId = equipOrderId;
    }

    public int getEquipId() {
        return equipId;
    }

    public void setEquipId(int equipId) {
        this.equipId = equipId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Equip getEquip() {
        return equip;
    }

    public void setEquip(Equip equip) {
        this.equip = equip;
    }

    @Override
    public String toString() {
        return "EquipOrderDetail{" +
                "detailId=" + detailId +
                ", equipOrderId=" + equipOrderId +
                ", equipId=" + equipId +
                ", quantity=" + quantity +
                ", equip=" + equip +
                '}';
    }
}
