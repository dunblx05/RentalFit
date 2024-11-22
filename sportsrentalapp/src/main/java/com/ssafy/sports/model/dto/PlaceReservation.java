package com.ssafy.sports.model.dto;

import java.time.LocalDateTime;

public class PlaceReservation {
    private int resId;
    private String userId;
    private int placeId;
    private LocalDateTime resStartTime;
    private LocalDateTime resEndTime;
    private int resCost;

    private Place place;

    public PlaceReservation() {};

    public PlaceReservation(int resId, String userId, int placeId, LocalDateTime resStartTime, LocalDateTime resEndTime, int resCost) {
        this.resId = resId;
        this.userId = userId;
        this.placeId = placeId;
        this.resStartTime = resStartTime;
        this.resEndTime = resEndTime;
        this.resCost = resCost;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public LocalDateTime getResStartTime() {
        return resStartTime;
    }

    public void setResStartTime(LocalDateTime resStartTime) {
        this.resStartTime = resStartTime;
    }

    public LocalDateTime getResEndTime() {
        return resEndTime;
    }

    public void setResEndTime(LocalDateTime resEndTime) {
        this.resEndTime = resEndTime;
    }

    public int getResCost() {
        return resCost;
    }

    public void setResCost(int resCost) {
        this.resCost = resCost;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "PlaceReservation{" +
                "resId=" + resId +
                ", userId='" + userId + '\'' +
                ", placeId=" + placeId +
                ", resStartTime=" + resStartTime +
                ", resEndTime=" + resEndTime +
                ", resCost=" + resCost +
                ", place=" + place +
                '}';
    }
}
