package com.ssafy.sports.model.dto;

public class Equip {
    private int equipId;
    private String equipName;
    private String equipPlayType;
    private String equipUseType;
    private int equipCost;
    private int equipResTime;
    private String equipImg;

    public Equip(int equipId, String equipName, String equipPlayType, String equipUseType, int equipCost, int equipResTime, String equipImg) {
        this.equipId = equipId;
        this.equipName = equipName;
        this.equipPlayType = equipPlayType;
        this.equipUseType = equipUseType;
        this.equipCost = equipCost;
        this.equipResTime = equipResTime;
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

    public String getEquipPlayType() {
        return equipPlayType;
    }

    public void setEquipPlayType(String equipPlayType) {
        this.equipPlayType = equipPlayType;
    }

    public String getEquipUseType() {
        return equipUseType;
    }

    public void setEquipUseType(String equipUseType) {
        this.equipUseType = equipUseType;
    }

    public int getEquipCost() {
        return equipCost;
    }

    public void setEquipCost(int equipCost) {
        this.equipCost = equipCost;
    }

    public int getEquipResTime() {
        return equipResTime;
    }

    public void setEquipResTime(int equipResTime) {
        this.equipResTime = equipResTime;
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
                ", equipPlayType='" + equipPlayType + '\'' +
                ", equipUseType='" + equipUseType + '\'' +
                ", equipCost=" + equipCost +
                ", equipResTime=" + equipResTime +
                ", equipImg='" + equipImg + '\'' +
                '}';
    }
}
