package com.ssafy.sports.model.dto;

public class Equip {
    private int equipId;
    private String equipName;
    private String equipType;
    private int equipPrice;
    private String equipImg;

    public Equip(int equipId, String equipName, String equipType, int equipPrice, String equipImg) {
        this.equipId = equipId;
        this.equipName = equipName;
        this.equipType = equipType;
        this.equipPrice = equipPrice;
        this.equipImg = equipImg;
    }

    public int getEquipId() {
        return equipId;
    }

    public void setEquipId(int equipId) {
        this.equipId = equipId;
    }

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }

    public String getEquipType() {
        return equipType;
    }

    public void setEquipType(String equipType) {
        this.equipType = equipType;
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
        return "Equip{" +
                "equipId=" + equipId +
                ", equipName='" + equipName + '\'' +
                ", equipType='" + equipType + '\'' +
                ", equipPrice=" + equipPrice +
                ", equipImg='" + equipImg + '\'' +
                '}';
    }
}
