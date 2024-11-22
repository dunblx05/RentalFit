package com.ssafy.sports.model.dto;

public class EquipOrderDetailInfo {

    private int detailId;
    private int equipOrderId;
    private int equipId;
    private int quantity;

    private String equipName;
    private int equipPrice;
    private String equipImg;

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

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }

    public int getEquipPrice() {
        return equipPrice;
    }

    public void setEquipPrice(int equipPrice) {
        this.equipPrice = equipPrice;
    }

    public String getEquipImg() {
        return equipImg;
    }

    public void setEquipImg(String equipImg) {
        this.equipImg = equipImg;
    }

    @Override
    public String toString() {
        return "EquipOrderDetailInfo{" +
                "detailId=" + detailId +
                ", equipOrderId=" + equipOrderId +
                ", equipId=" + equipId +
                ", quantity=" + quantity +
                ", equipName='" + equipName + '\'' +
                ", equipPrice=" + equipPrice +
                ", equipImg='" + equipImg + '\'' +
                '}';
    }
}
