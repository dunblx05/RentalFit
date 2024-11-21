package com.ssafy.sports.model.dto;

import java.util.List;

public class Place {
    private Integer placeId;
    private String placeName;
    private Integer placePeople;
    private String placeLocation;
    private String placeType;
    private Integer placeCost;
    private String placeImg;

    public Place(Integer placeId, String placeName, Integer placePeople, String placeLocation, String placeType, Integer placeCost, String placeImg) {
        this.placeId = placeId;
        this.placeName = placeName;
        this.placePeople = placePeople;
        this.placeLocation = placeLocation;
        this.placeType = placeType;
        this.placeCost = placeCost;
        this.placeImg = placeImg;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public Integer getPlacePeople() {
        return placePeople;
    }

    public void setPlacePeople(Integer placePeople) {
        this.placePeople = placePeople;
    }

    public String getPlaceLocation() {
        return placeLocation;
    }

    public void setPlaceLocation(String placeLocation) {
        this.placeLocation = placeLocation;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public Integer getPlaceCost() {
        return placeCost;
    }

    public void setPlaceCost(Integer placeCost) {
        this.placeCost = placeCost;
    }

    public String getPlaceImg() {
        return placeImg;
    }

    public void setPlaceImg(String placeImg) {
        this.placeImg = placeImg;
    }

    @Override
    public String toString() {
        return "Place{" +
                "placeId=" + placeId +
                ", placeName='" + placeName + '\'' +
                ", placePeople=" + placePeople +
                ", placeLocation='" + placeLocation + '\'' +
                ", placeType='" + placeType + '\'' +
                ", placeCost=" + placeCost +
                ", placeImg='" + placeImg + '\'' +
                '}';
    }
}
