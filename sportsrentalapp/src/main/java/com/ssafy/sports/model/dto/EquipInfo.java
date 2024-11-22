package com.ssafy.sports.model.dto;

public class EquipInfo {
    private String equipName;
    private String equipImg;
    private int equipPrice;

    public EquipInfo() {
    }

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }

    public String getEquipImg() {
        return equipImg;
    }

    public void setEquipImg(String equipImg) {
        this.equipImg = equipImg;
    }

    public int getEquipPrice() {
        return equipPrice;
    }

    public void setEquipPrice(int equipPrice) {
        this.equipPrice = equipPrice;
    }

    @Override
    public String toString() {
        return "EquipInfo{" +
                "equipName='" + equipName + '\'' +
                ", equipImg='" + equipImg + '\'' +
                ", equipPrice=" + equipPrice +
                '}';
    }
}
